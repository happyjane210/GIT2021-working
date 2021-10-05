package com.git.myworkspace.opendata.air;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component("air/controller")
@RestController
@RequestMapping(value = "/opendata/air")
public class AirController {

	// 의존 주입
	private AirSigunguHourRepository repo;
	private final String cachName = "air-current";

	@Autowired
	public AirController(AirSigunguHourRepository repo) {
		this.repo = repo;
	}

	// 최근 25개의 데이터를 조회 , 현재 시간 기준으로 역정렬
	// 예) 서울 25개 구의 가장 최근 시간의 데이터
	@Cacheable(value = cachName, key = "'all'") // 캐싱하기
	@GetMapping(value = "/sido/current")
	public List<AirSigunguHour> getAirSidoCurrent() {

		// 여러개의 필드로 정렬
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.DESC, "dataTime"));
		orders.add(new Order(Sort.Direction.ASC, "cityName"));

		return repo.findAll(PageRequest.of(0, 25, Sort.by(orders))).toList(); // 0번째 페이지에서 25개씩 출력

	}

	// 캐싱은 매서드의 리턴 객체가 캐시되는것, 디비의 데이터 테이블 아님!!

	// 특정 구의 최근 12개의 데이터를 조회
	// 예) 강남구, 최근 12개(12시간)의 데이터
	// 예) WHERE city_name='강남구' ORDER BY data_time DESC LIMIT 12
	// @Cacheable(value = "캐시이름", key = "키이름") 경로가 될 매개변수는 앞에 #을 붙여줌(pathVariable)
	@Cacheable(value = cachName, key = "#city")
	@GetMapping(value = "/sido/current/{city}")
	public List<AirSigunguHour> getAirSidoCurrent(@PathVariable String city) {
		Pageable page = PageRequest.of(0, 25, Sort.by("dataTime").descending());
		return repo.findByCityName(page, city);
	}
}

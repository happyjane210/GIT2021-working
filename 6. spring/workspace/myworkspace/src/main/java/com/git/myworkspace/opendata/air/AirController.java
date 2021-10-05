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

	// ���� ����
	private AirSigunguHourRepository repo;
	private final String cachName = "air-current";

	@Autowired
	public AirController(AirSigunguHourRepository repo) {
		this.repo = repo;
	}

	// �ֱ� 25���� �����͸� ��ȸ , ���� �ð� �������� ������
	// ��) ���� 25�� ���� ���� �ֱ� �ð��� ������
	@Cacheable(value = cachName, key = "'all'") // ĳ���ϱ�
	@GetMapping(value = "/sido/current")
	public List<AirSigunguHour> getAirSidoCurrent() {

		// �������� �ʵ�� ����
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.DESC, "dataTime"));
		orders.add(new Order(Sort.Direction.ASC, "cityName"));

		return repo.findAll(PageRequest.of(0, 25, Sort.by(orders))).toList(); // 0��° ���������� 25���� ���

	}

	// ĳ���� �ż����� ���� ��ü�� ĳ�õǴ°�, ����� ������ ���̺� �ƴ�!!

	// Ư�� ���� �ֱ� 12���� �����͸� ��ȸ
	// ��) ������, �ֱ� 12��(12�ð�)�� ������
	// ��) WHERE city_name='������' ORDER BY data_time DESC LIMIT 12
	// @Cacheable(value = "ĳ���̸�", key = "Ű�̸�") ��ΰ� �� �Ű������� �տ� #�� �ٿ���(pathVariable)
	@Cacheable(value = cachName, key = "#city")
	@GetMapping(value = "/sido/current/{city}")
	public List<AirSigunguHour> getAirSidoCurrent(@PathVariable String city) {
		Pageable page = PageRequest.of(0, 25, Sort.by("dataTime").descending());
		return repo.findByCityName(page, city);
	}
}

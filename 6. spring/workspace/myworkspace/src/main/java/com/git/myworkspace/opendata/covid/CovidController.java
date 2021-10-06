package com.git.myworkspace.opendata.covid;

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

@Component("covid/controller")
@RestController
@RequestMapping(value = "/opendata/covid")
public class CovidController {

	// ���� ����
	private CovidSidoDailyRepository repo;
	private final String cachName = "covid-daily";

	@Autowired
	public CovidController(CovidSidoDailyRepository repo) {
		this.repo = repo;
	}

	// 1. ���� ������ ��ȸ
	// page size�� 19��, ������ std_day desc
	@Cacheable(value = cachName, key = "'all'")
	@GetMapping(value = "/sido/daily")
	public List<CovidSidoDaily> getCovidSidoDailies() {

		// �������� �ʵ�� ����
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.DESC, "stdDay"));
		orders.add(new Order(Sort.Direction.ASC, "gubun"));

		return repo.findAll(PageRequest.of(0, 19, Sort.by(orders))).toList(); // 0��° ���������� 19���� ���
	}

	// 2. Ư�� �õ��� ������ ��ȸ
	// �˻������� �ʵ�� (gubun) , pagesize (limit)�� 7��, ������ std_day desc
	// ��) WHERE gubun='����' ORDER BY std_day DESC LIMIT 7;
	@Cacheable(value = cachName, key = "#gubun")
	@GetMapping(value = "/sido/daily/{gubun}")
	public List<CovidSidoDaily> getCovidSidoDailies(@PathVariable String gubun) {

		Pageable page = PageRequest.of(0, 7, Sort.by("stdDay").descending());

		return repo.findByGubun(page, gubun);
	}

}

package com.git.myworkspace.opendata.air;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirSigunguHourRepository extends JpaRepository<AirSigunguHour, Long> {

	// findBy필드명
	// 필드명은 Pascal-Case로 대소문자 잘 맞춰야함
	// 예) 필드명 findBy 메서드명 findByCityName
	// -> WHERE city_name = :city ORDER BY ... LIMIT ...
	List<AirSigunguHour> findByCityName(Pageable page, String city);
}
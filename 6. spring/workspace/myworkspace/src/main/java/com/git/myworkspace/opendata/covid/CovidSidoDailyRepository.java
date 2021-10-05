package com.git.myworkspace.opendata.covid;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidSidoDailyRepository extends JpaRepository<CovidSidoDaily, Long> {

	// findBy ÇÊµå¸í , Pascal-Case
	List<CovidSidoDaily> findBySidoGubun(Pageable page, String gubun);

}

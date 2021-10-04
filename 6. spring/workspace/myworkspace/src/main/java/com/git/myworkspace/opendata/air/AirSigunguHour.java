package com.git.myworkspace.opendata.air;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = @Index(name = "idx_air_sigungu_hour_1", columnList = "sidoName, cityName"))
@IdClass(AirSigunguHourId.class)
public class AirSigunguHour {

	// 시간, 시도, 시군구에 유일한 데이터만 존재해야함
	// 예) 20210930 16:00, 서울, 강남구 측정된 데이터는 유일하게 1건 존재해야함

	// 시간, 지역1, 지역2
	// 2021-09-30 14:00, 서울, 강남구
	// 24, 3, 2, 3, 2

	// dataTime, sidoName, cityName 결합하면 유일한 PK 만들어 낼 수 있음
	// 유일성, 최소성, 대표성이 나올수 있게 해야함
	@Id
	private String dataTime;
	@Id
	private String sidoName; // 밀도가 커봤자 5%, 분포도가 커봤자 20%, 인덱스 제외
	@Id
	private String cityName;

	// 값
	private String pm10Value;
	private String pm25Value;

}

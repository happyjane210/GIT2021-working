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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(indexes = @Index(name = "idx_air_sigungu_hour_1", columnList = "sidoName, cityName"))
@IdClass(AirSigunguHourId.class)
public class AirSigunguHour {

	// 시간 시도, 사군구에 유일한데이터만 존재해야함
	// 20210930 16:00, 서울, 강남구 측정된 데이터는 유일하게 1건 존재해야함
	// 해당 아이디 값에 데이터가 있으면 select 함 insert 안함 <중복처리>
	@Id
	private String dataTime;
	@Id
	private String sidoName;
	@Id
	private String cityName;
	// 값
	private String pm10Value;
	private String pm25Value;
}
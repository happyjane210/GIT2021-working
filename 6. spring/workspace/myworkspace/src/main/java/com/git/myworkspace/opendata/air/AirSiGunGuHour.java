package com.git.myworkspace.opendata.air;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = @Index(name = "idx_air_si_gun_gu_hour_", columnList = "sidoName, cityName"))
public class AirSiGunGuHour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY);
	private long id;

	private String dataTime;
	private String sidoName;
	private String cityName;

	// °ª
	private String pm10Value;
	private String pm25Value;
	private String coValue;
	private String so2Value;
	private String o3Value;
	private String no2Value;
}

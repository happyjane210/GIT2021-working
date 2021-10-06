package com.git.myworkspace.opendata.covid;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidSidoDailyId implements Serializable {

	// 이게뭐지 1L는뭐지 ?? 그냥 있는것
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stdDay;
	private String gubun;

}

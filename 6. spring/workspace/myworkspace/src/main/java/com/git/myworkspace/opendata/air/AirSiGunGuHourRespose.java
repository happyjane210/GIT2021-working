package com.git.myworkspace.opendata.air;

import lombok.Data;

public class AirSiGunGuHourRespose {
	private Response response;

	@Data
	public class Response {
		private Header header;
		private Object body;
	}

	@Data
	public class Header {
		private String resultCode;
		private String resultMsg;
	}

	@Data
	public class Body {
		private Object items;
	}

	@Data
	public class Items {
		private Object[] item;
	}

	@Data
	public class Item {

		// OLAP Cube 형식으로 데이터
		// 지역, 카테고리, 시간
		private String sidoName;
		private String cityName;
		private String dataTime;
	}

}

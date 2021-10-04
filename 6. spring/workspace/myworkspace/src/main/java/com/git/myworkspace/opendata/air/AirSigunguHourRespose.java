package com.git.myworkspace.opendata.air;

import java.util.List;

// 데이터구조에 해당하는 필드 만들기

import lombok.Data;

@Data
public class AirSigunguHourRespose {

	// 클래스 안에 클래스 따로 선언함 (이너 클래스)

	private Response response;
	// AirSiGunGuHourRespose라는 전체 응답 객체 안에 response 필드 안에는 Header와 body필드가 있음
	// 전체 객체 타입 : Response

	@Data
	public class Response {
		private Header header;
		private Body body;
	}

	@Data
	public class Header {
		private String resultCode;
		private String resultMsg;
	}
	// Header필드 안에 resultCode와 resultMsg

	@Data
	public class Body { // body에 해당하는 필드
		private Items items;
	}

	@Data
	public class Items {
		private List<Item> item; // item이라는 필드는 배열객체 []
	}

	@Data
	public class Item {

		// OLAP Cube 형식으로 데이터
		// 지역, 카테고리, 시간, 값
		private String dataTime;
		private String sidoName;
		private String cityName;
		private String pm10Value;
		private String pm25Value;
		private String coValue;
		private String so2Value;
		private String o3Value;
		private String no2Value;

	}

//	<response>
//	<header>
//		<resultCode>00</resultCode>
//		<resultMsg>NORMAL_CODE</resultMsg>
//	</header>
//	<body>
//		<items>
//			<item>
//				<khaiValue/>
//				<so2Value>0.004</so2Value>
//				<coValue>0.4</coValue>
//				<cityName>강남구</cityName>
//				<cityNameEng>Gangnam-gu</cityNameEng>
//				<pm10Value>37</pm10Value>
//				<dataTime>2021-10-05 02:00</dataTime>
//				<no2Value>0.005</no2Value>
//				<districtNumSeq>001</districtNumSeq>
//				<o3Value>0.040</o3Value>
//				<pm25Value>14</pm25Value>
//				<sidoName>서울</sidoName>
//			</item>

}

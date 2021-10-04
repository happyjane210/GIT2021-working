package com.git.myworkspace.opendata.air;

import java.util.List;

// �����ͱ����� �ش��ϴ� �ʵ� �����

import lombok.Data;

@Data
public class AirSigunguHourRespose {

	// Ŭ���� �ȿ� Ŭ���� ���� ������ (�̳� Ŭ����)

	private Response response;
	// AirSiGunGuHourRespose��� ��ü ���� ��ü �ȿ� response �ʵ� �ȿ��� Header�� body�ʵ尡 ����
	// ��ü ��ü Ÿ�� : Response

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
	// Header�ʵ� �ȿ� resultCode�� resultMsg

	@Data
	public class Body { // body�� �ش��ϴ� �ʵ�
		private Items items;
	}

	@Data
	public class Items {
		private List<Item> item; // item�̶�� �ʵ�� �迭��ü []
	}

	@Data
	public class Item {

		// OLAP Cube �������� ������
		// ����, ī�װ�, �ð�, ��
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
//				<cityName>������</cityName>
//				<cityNameEng>Gangnam-gu</cityNameEng>
//				<pm10Value>37</pm10Value>
//				<dataTime>2021-10-05 02:00</dataTime>
//				<no2Value>0.005</no2Value>
//				<districtNumSeq>001</districtNumSeq>
//				<o3Value>0.040</o3Value>
//				<pm25Value>14</pm25Value>
//				<sidoName>����</sidoName>
//			</item>

}

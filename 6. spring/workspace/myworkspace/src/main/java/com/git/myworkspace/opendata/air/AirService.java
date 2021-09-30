package com.git.myworkspace.opendata.air;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

// 서비스 컴포넌트
// 1. 외부 시스템 통신
// 2. 데이터 트랙젝션 처리
@Service
public class AirService {
	private final String SERVICE_KEY = "itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D";

	@SuppressWarnings("deprecation")

	// 시군구별 대기 질 시간단위 조회
	// 5초마다 실행(js, setInterval)
	// fixedRate 가장 처음에 실행되고 간격별로 실행됨
	@Scheduled(fixedRate = 1000 * 5)
	public void requestAirSigunguHour() throws IOException {
		System.out.println(new Date().toLocaleString());

		// 1. 요청 URL 만들기
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584");
		builder.append("/ArpltnStatsSvc");
		builder.append("/getCtprvnMesureSidoList");
		builder.append("?sidoName=" + URLEncoder.encode("서울", "UTF-8"));
		builder.append("&serchCondition=HOUR");
		builder.append("&serchCondition=HOUR");
		builder.append("&pageNo=1&numOfRow=25");
		builder.append("&serviceKey=" + SERVICE_KEY);

		System.out.println(builder.toString());

		// 2. URL 객체 생성
		URL url = new URL(builder.toString());

		// 3. HTtp 접속 생성
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[] 배열로 데이터를 읽어옴
		byte[] result = con.getInputStream().readAllBytes();

		// 5. 문자열 반환
		String data = new String(result, "UTF-8");
		System.out.println(data);
		// ------------------ 데이터 요청하고 XML 받아오기 끝 ---------------------------

		// ------------------XML -> JSON -> Object(JAVA) 시작---------------------------
		JSONObject jsonObj = XML.toJSONObject(data);
		String json = jsonObj.toString(2);
		System.out.println(json);
		
		// JSON(문자열) -> Java(object)
		AirSiGunGuHourRespose response = new Gson.fromJson(json, AirSiGunGuHourRespose.class);
		System.out.println(response);
		
		// 강동구 데이터
		AirSiGunGuHourRespose.Item item = response.getResponse().getBody().getItem.
		// ------------------XML -> JSON -> Object(JAVA) 끝---------------------------

	}
}

package com.git.myworkspace.opendata.air;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

// 서비스 컴포넌트
// 1. 외부 시스템 통신
// 2. 데이터 트랙젝션 처리
@Service
public class AirService {

	private final String SERVICE_KEY = "itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D";

	// spring에 의한 의존성 주입
	private AirSigunguHourRepository repo;

	@Autowired
	public AirService(AirSigunguHourRepository repo) {
		this.repo = repo;
	}

	// 시군구별 대기질 시간단위 조회
	// fixedRate 가장 처음에 실행되고 간격별로 실행됨
	// 5초마다 실행( = js의 setInterval과 같음)
	// @Scheduled(fixedRate = 1000 * 5)

	// 매시 30분마다 실행, 1시 30분, 2시 30분
	// cron = "초 분 시 일 월 년"
	// @Scheduled(cron = "0 30 * * * *")

	// 정각 2시간마다 실행
	// @Scheduled(cron = "0 0 */2 * * *")

	// 1시간마다 실행
	@Scheduled(fixedRate = 1000 * 60 * 60 * 1)

	public void requestAir() throws IOException {
		String[] sidoNames = { "서울", "경기" };// java 고정값 배역
		for (String sidoName : sidoNames) {
			requestAirSiGunGuHour(sidoName);
		}
	}

	@SuppressWarnings("deprecation")
	public void requestAirSiGunGuHour(String sido) throws IOException { // throws IOException 최상위 컬렉션에 예외처리
		System.out.println(new Date().toLocaleString());

		// ------------------ 데이터 요청하고 XML 받아오기 시작 ---------------------------

		// http://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureSidoLIst?sidoName=서울&searchCondition=HOUR&pageNo=1&numOfRows=25&serviceKey=itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D
		// 문자열을 빌더방식으로 생성하는 클래스

		// 1. 요청 URL 구조 만들기
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584"); // 호스트 게이트웨이
		builder.append("/ArpltnStatsSvc"); // 서비스 호출
		builder.append("/getCtprvnMesureSidoLIst"); // 기능( 시도-시군구별조회 예. 서울-강남구~중랑구 )
		// 여기서 부터 매개변수
		builder.append("?sidoName=" + URLEncoder.encode(sido, "UTF-8")); // 서울, 경기
		builder.append("&serchCondition=HOUR"); // 1시간 단위
		builder.append("&pageNo=1&numOfRow=100"); // 출력 구 개수
		builder.append("&serviceKey=" + SERVICE_KEY); // 서비스키

		System.out.println(builder.toString());// 문자열 찍기

		// 2. URL 객체 생성
		URL url = new URL(builder.toString());

		// 3. HTTp 접속 생성
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[] 배열로 데이터를 읽어옴
		byte[] result = con.getInputStream().readAllBytes();

		// 5. 바이트 배열(byte[])을 문자열(XML)로 반환
		String data = new String(result, "UTF-8");
		System.out.println(data);
		// ------------------ 데이터 요청하고 XML 받아오기 끝 ---------------------------

		//

		// ------------------XML -> JSON -> Object(JAVA) 시작---------------------------
		// XML(문자열) -> JSON(문자열)
		String json = XML.toJSONObject(data).toString(2);
//		System.out.println(json);

		// JSON(문자열) -> Java(object) 변환 변환할 객체 타입
		AirSigunguHourRespose response = new Gson().fromJson(json, AirSigunguHourRespose.class);
		System.out.println(response);

		// 강동구 데이터
//		AirSigunguHourRespose.Item item = response.getResponse().getBody().getItems().getItem().get(1);
//		System.out.println(item);

		// ------------------XML -> JSON -> Object(JAVA) 끝---------------------------

		//

		// ------------------응답객체 -> entity 객체 변환 시작---------------------------

		List<AirSigunguHour> list = new ArrayList<AirSigunguHour>();
		for (AirSigunguHourRespose.Item item : response.getResponse().getBody().getItems().getItem()) {

			AirSigunguHour record = AirSigunguHour.builder().dataTime(item.getDataTime()).sidoName(item.getSidoName())
					.cityName(item.getCityName()).pm10Value(item.getPm10Value()).pm25Value(item.getPm25Value()).build();

			list.add(record);
		}

		// ------------------응답객체 -> entity 객체 변환 끝---------------------------

		//

		// ------------------entity 객체 -> repository로 저장 시작---------------------------

		repo.saveAll(list);

		// ------------------entity 객체 -> repository로 저장 시작---------------------------
	}
}

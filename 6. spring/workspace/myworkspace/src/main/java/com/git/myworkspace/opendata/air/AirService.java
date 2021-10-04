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

// ���� ������Ʈ
// 1. �ܺ� �ý��� ���
// 2. ������ Ʈ������ ó��
@Service
public class AirService {

	private final String SERVICE_KEY = "itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D";

	// spring�� ���� ������ ����
	private AirSigunguHourRepository repo;

	@Autowired
	public AirService(AirSigunguHourRepository repo) {
		this.repo = repo;
	}

	// �ñ����� ����� �ð����� ��ȸ
	// fixedRate ���� ó���� ����ǰ� ���ݺ��� �����
	// 5�ʸ��� ����( = js�� setInterval�� ����)
	// @Scheduled(fixedRate = 1000 * 5)

	// �Ž� 30�и��� ����, 1�� 30��, 2�� 30��
	// cron = "�� �� �� �� �� ��"
	// @Scheduled(cron = "0 30 * * * *")

	// ���� 2�ð����� ����
	// @Scheduled(cron = "0 0 */2 * * *")

	// 1�ð����� ����
	@Scheduled(fixedRate = 1000 * 60 * 60 * 1)

	public void requestAir() throws IOException {
		String[] sidoNames = { "����", "���" };// java ������ �迪
		for (String sidoName : sidoNames) {
			requestAirSiGunGuHour(sidoName);
		}
	}

	@SuppressWarnings("deprecation")
	public void requestAirSiGunGuHour(String sido) throws IOException { // throws IOException �ֻ��� �÷��ǿ� ����ó��
		System.out.println(new Date().toLocaleString());

		// ------------------ ������ ��û�ϰ� XML �޾ƿ��� ���� ---------------------------

		// http://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureSidoLIst?sidoName=����&searchCondition=HOUR&pageNo=1&numOfRows=25&serviceKey=itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D
		// ���ڿ��� ����������� �����ϴ� Ŭ����

		// 1. ��û URL ���� �����
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584"); // ȣ��Ʈ ����Ʈ����
		builder.append("/ArpltnStatsSvc"); // ���� ȣ��
		builder.append("/getCtprvnMesureSidoLIst"); // ���( �õ�-�ñ�������ȸ ��. ����-������~�߶��� )
		// ���⼭ ���� �Ű�����
		builder.append("?sidoName=" + URLEncoder.encode(sido, "UTF-8")); // ����, ���
		builder.append("&serchCondition=HOUR"); // 1�ð� ����
		builder.append("&pageNo=1&numOfRow=100"); // ��� �� ����
		builder.append("&serviceKey=" + SERVICE_KEY); // ����Ű

		System.out.println(builder.toString());// ���ڿ� ���

		// 2. URL ��ü ����
		URL url = new URL(builder.toString());

		// 3. HTTp ���� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[] �迭�� �����͸� �о��
		byte[] result = con.getInputStream().readAllBytes();

		// 5. ����Ʈ �迭(byte[])�� ���ڿ�(XML)�� ��ȯ
		String data = new String(result, "UTF-8");
		System.out.println(data);
		// ------------------ ������ ��û�ϰ� XML �޾ƿ��� �� ---------------------------

		//

		// ------------------XML -> JSON -> Object(JAVA) ����---------------------------
		// XML(���ڿ�) -> JSON(���ڿ�)
		String json = XML.toJSONObject(data).toString(2);
//		System.out.println(json);

		// JSON(���ڿ�) -> Java(object) ��ȯ ��ȯ�� ��ü Ÿ��
		AirSigunguHourRespose response = new Gson().fromJson(json, AirSigunguHourRespose.class);
		System.out.println(response);

		// ������ ������
//		AirSigunguHourRespose.Item item = response.getResponse().getBody().getItems().getItem().get(1);
//		System.out.println(item);

		// ------------------XML -> JSON -> Object(JAVA) ��---------------------------

		//

		// ------------------���䰴ü -> entity ��ü ��ȯ ����---------------------------

		List<AirSigunguHour> list = new ArrayList<AirSigunguHour>();
		for (AirSigunguHourRespose.Item item : response.getResponse().getBody().getItems().getItem()) {

			AirSigunguHour record = AirSigunguHour.builder().dataTime(item.getDataTime()).sidoName(item.getSidoName())
					.cityName(item.getCityName()).pm10Value(item.getPm10Value()).pm25Value(item.getPm25Value()).build();

			list.add(record);
		}

		// ------------------���䰴ü -> entity ��ü ��ȯ ��---------------------------

		//

		// ------------------entity ��ü -> repository�� ���� ����---------------------------

		repo.saveAll(list);

		// ------------------entity ��ü -> repository�� ���� ����---------------------------
	}
}

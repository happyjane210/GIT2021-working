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

// ���� ������Ʈ
// 1. �ܺ� �ý��� ���
// 2. ������ Ʈ������ ó��
@Service
public class AirService {
	private final String SERVICE_KEY = "itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D";

	@SuppressWarnings("deprecation")

	// �ñ����� ��� �� �ð����� ��ȸ
	// 5�ʸ��� ����(js, setInterval)
	// fixedRate ���� ó���� ����ǰ� ���ݺ��� �����
	@Scheduled(fixedRate = 1000 * 5)
	public void requestAirSigunguHour() throws IOException {
		System.out.println(new Date().toLocaleString());

		// 1. ��û URL �����
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584");
		builder.append("/ArpltnStatsSvc");
		builder.append("/getCtprvnMesureSidoList");
		builder.append("?sidoName=" + URLEncoder.encode("����", "UTF-8"));
		builder.append("&serchCondition=HOUR");
		builder.append("&serchCondition=HOUR");
		builder.append("&pageNo=1&numOfRow=25");
		builder.append("&serviceKey=" + SERVICE_KEY);

		System.out.println(builder.toString());

		// 2. URL ��ü ����
		URL url = new URL(builder.toString());

		// 3. HTtp ���� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[] �迭�� �����͸� �о��
		byte[] result = con.getInputStream().readAllBytes();

		// 5. ���ڿ� ��ȯ
		String data = new String(result, "UTF-8");
		System.out.println(data);
		// ------------------ ������ ��û�ϰ� XML �޾ƿ��� �� ---------------------------

		// ------------------XML -> JSON -> Object(JAVA) ����---------------------------
		JSONObject jsonObj = XML.toJSONObject(data);
		String json = jsonObj.toString(2);
		System.out.println(json);
		
		// JSON(���ڿ�) -> Java(object)
		AirSiGunGuHourRespose response = new Gson.fromJson(json, AirSiGunGuHourRespose.class);
		System.out.println(response);
		
		// ������ ������
		AirSiGunGuHourRespose.Item item = response.getResponse().getBody().getItem.
		// ------------------XML -> JSON -> Object(JAVA) ��---------------------------

	}
}

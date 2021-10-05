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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

// ���� ������Ʈ
// 1. �ܺ� �ý��� ���
// 2. ������ Ʈ����� ó��
@Service
public class AirService {

	private final String SERVICE_KEY = "MpDyWV6HEoFo9g4egJTrU4TtGVfOSTIJ9btDLrA6cHIy9f%2BTZhGFh6PGe48A0LOjl7tRCGTkBKfOHUgIsR6UvA%3D%3D";

	private AirSigunguHourRepository repo;

	@Autowired
	public AirService(AirSigunguHourRepository repo) {
		this.repo = repo;
	}

	// �ñ����� ����� �ð����� ��ȸ
	// 1�ð����� ����(js, setInterval)
	// fixedRate: ���� ó���� ����ǰ� ���ݺ��� �����
	// @Scheduled(fixedRate = 1000 * 60 * 60 * 1)

	// ���� 2�ð����� ����
//	@Scheduled(cron = "0 0 */2 * * *")	

	// �Ž� 30�п� ����, 1�� 30��, 2�� 30��
	// �� �ð��� �Ǿ�߸� �����
	// cron="�� �� �� �� �� ��"
	// cron="0 30 * * * *"
	@Scheduled(cron = "0 30 * * * *")
	@CacheEvict(value = "air-current", allEntries = true)
	// ���� �ֽ��� �����͸� �ҷ��;� �ϴϱ� �����Ͱ� �¹����� �ʵ��� ���� ��ȸ�� ������ ����
	// �ش� ĳ���̸��� ��� Ű�� ����, value = ĳ���̸�
	public void requestAir() throws IOException {
		// String[] sidoNames = { "����", "���" };
		String[] sidoNames = { "����" };
		for (String sidoName : sidoNames) {
			requestAirSiGunGuHour(sidoName);
		}
	}

	@SuppressWarnings("deprecation")
	public void requestAirSiGunGuHour(String sido) throws IOException {
		System.out.println(new Date().toLocaleString());

		/* ---------------------- ������ ��û�ϰ� XML �޾ƿ��� ���� ----------------- */
		// http://apis.data.go.kr/B552584/ArpltnStatsSvc/getCtprvnMesureSidoLIst?sidoName=����&searchCondition=HOUR&pageNo=1&numOfRows=25&serviceKey=8x9EEMlvpXLrqor89PreIVvrNAtT2rkM%2Be6FOns1GkNS6aQdSlFL0BpFU4e%2F5GoeKa9t1Y1ztK6wfP90DIO%2Ftw%3D%3D
		// StringBuilder ���ڿ��� ����������� �����ϴ� Ŭ����
		// 1. ��û URL �����
		StringBuilder builder = new StringBuilder();
		builder.append("http://apis.data.go.kr/B552584"); // ȣ��Ʈ / ����Ʈ����
		builder.append("/ArpltnStatsSvc"); // ����
		builder.append("/getCtprvnMesureSidoLIst"); // ���(�õ�-�ñ�������ȸ ��) ����-������...�߶���)
		builder.append("?sidoName=" + URLEncoder.encode(sido, "UTF-8")); // �õ�(����, ���...) (�ѱ��� �־ UTF-8�� �о�ߴ�)
		builder.append("&searchCondition=HOUR"); // 1�ð�����
		builder.append("&pageNo=1&numOfRows=100"); // �ñ��� ����
		builder.append("&serviceKey=" + SERVICE_KEY); // ����Ű

		System.out.println(builder.toString());

		// 2. URL ��ü ����
		URL url = new URL(builder.toString());

		// 3.Http ���� ���� URL��ü�� ����Ÿ�� �̸� (Http �� �ִ°� �ƴ϶� FTP ���� �������ݵ� �־) HTTP�� ���� �Ϸ���
		// url�� HTTP���ӿ� ��ü�� �ٲ�� ��
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[]�迭�� �����͸� �о�� ( �����͸� �д� ���� �������� ��� byte ������ 1byte�� �д´�. )
		byte[] result = con.getInputStream().readAllBytes();

		// 5. byte[] -> ���ڿ�(XML) ��ȯ
		String data = new String(result, "UTF-8");
		/* ---------------------- ������ ��û�ϰ� XML �޾ƿ��� �� ----------------- */

		/* ---------------------- XML -> JSON -> Object(Java) ���� ----------------- */
		// XML(���ڿ�) -> JSON(���ڿ�)
		String json = XML.toJSONObject(data).toString(2);
		System.out.println(json);

		// JSON(���ڿ�) -> Java(object)
		AirSigunguHourResponse response = new Gson().fromJson(json, AirSigunguHourResponse.class);
//		System.out.println(response);

//		// ������ ������
//		AirSigunguHourResponse.Item item = response.getResponse().getBody().getItems().getItem().get(1);
//		System.out.println(item);
		/* ---------------------- XML -> JSON -> Object(Java) �� ----------------- */

		/* ---------------------- ���� ��ü -> ��ƼƼ ���� ----------------- */
		List<AirSigunguHour> list = new ArrayList<AirSigunguHour>();
		for (AirSigunguHourResponse.Item item : response.getResponse().getBody().getItems().getItem()) {

			AirSigunguHour record = AirSigunguHour.builder().dataTime(item.getDataTime()).sidoName(item.getSidoName())
					.cityName(item.getCityName()).pm10Value(item.getPm10Value()).pm25Value(item.getPm25Value()).build();

			list.add(record);
		}
		/* ---------------------- ���� ��ü -> ��ƼƼ �� ----------------- */

		/* ---------------------- ��ƼƼ��ü -> �������͸��� ���� ���� ----------------- */
		repo.saveAll(list);
		/* ---------------------- ��ƼƼ��ü -> �������͸��� ���� �� ----------------- */
	}
}
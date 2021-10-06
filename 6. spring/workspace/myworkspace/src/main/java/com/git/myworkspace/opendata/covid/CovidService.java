package com.git.myworkspace.opendata.covid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class CovidService {

	// ����Ű
	private final String SERVICE_KEY = "itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D";

	// ����������
	private CovidSidoDailyRepository repo;

	@Autowired
	public CovidService(CovidSidoDailyRepository repo) {
		this.repo = repo;
	}

	// �õ��� �ڷγ� ��Ȳ �Ϻ���ȸ
	// ��:0-59 | ��:0-59 | ��:0-23 | ��:1-31 | ��:1-12 | ����:0-6 | ��:�������� 2021
	// @Scheduled(cron="�� �� �� �� �� (����) ��")
	// ���� ���� 10�� 5�� ������ ����, ������ ����
//	@Scheduled(cron = "0 5 10 * * *")

// ���õ�����
	@Scheduled(fixedRate = 1000 * 60 * 60 * 1)

	// ���� �ֽ��� �����͸� �ҷ��;� �ϴϱ� �����Ͱ� �¹����� �ʵ��� ���� ��ȸ�� ������ ����
	// �ش� ĳ���̸��� ��� Ű�� ����, value = ĳ���̸�
	@CacheEvict(value = "covid-daily", allEntries = true)
	public void requestCovidSidoDaily() throws IOException {

		/* ---------------------- ������ ��û�ϰ� XML �޾ƿ��� ���� ----------------- */

		// 1. ��û URL �����
		StringBuilder builder = new StringBuilder();
		builder.append("http://openapi.data.go.kr/openapi"); // ȣ��Ʈ / ����Ʈ����
		builder.append("/service/rest/Covid19"); // ����
		builder.append("/getCovid19SidoInfStateJson"); // ��� (�ڷγ�19 �õ��� ���� ��Ȳ json)
		builder.append("?serviceKey=" + SERVICE_KEY); // ����Ű
		builder.append("&pageNo=1&numOfRows=10"); // ������ ��ȣ 1�� / �Խù� ���� 10��
		builder.append("&startCreateDt=20211001"); // �˻��� ������ ���� ���� , �ʼ��� �ƴѵ� start �� end �ϳ��� �־�� ������ ��ȸ��

//		System.out.println(builder.toString());

		// 2. URL ��ü ����
		URL url = new URL(builder.toString());

		// 3. Http�� �����Ϸ��� url�� http ���ӿ� ��ü�� �ٲ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 4. byte[] �迭�� �����͸� �о��
		byte[] result = con.getInputStream().readAllBytes();

		// 5. byte[] -> ���ڿ� (XML) ��ȯ
		String data = new String(result, "UTF-8");

		/* ---------------------- ������ ��û�ϰ� XML �޾ƿ��� �� ----------------- */

		/* ---------------------- XML -> JSON -> Object(Java) ���� ----------------- */

		// XML(���ڿ�) -> JSON(���ڿ�)
		String json = XML.toJSONObject(data).toString(2); // �� 2��???
//		System.out.println(json);

		// JSON(���ڿ�) -> Java(object) ��ü
		CovidSidoDailyResponse response = new Gson().fromJson(json, CovidSidoDailyResponse.class);
//		System.out.println(response);

		/* ---------------------- XML -> JSON -> Object(Java) �� ----------------- */

		//

		/* ---------------------- ���� ��ü -> entity ��ƼƼ ��ü ���� ----------------- */

		List<CovidSidoDaily> list = new ArrayList<CovidSidoDaily>();
		for (CovidSidoDailyResponse.Item item : response.getResponse().getBody().getItems().getItem()) {

			CovidSidoDaily record = CovidSidoDaily.builder().stdDay(item.getStdDay()).gubun(item.getGubun())
					.gubunEn(item.getGubunEn()).overFlowCnt(item.getOverFlowCnt()).localOccCnt(item.getLocalOccCnt())
					.build();

			list.add(record);
		}

		/* ---------------------- ���� ��ü -> entity ��ƼƼ ��ü �� ----------------- */

		//

		/* ---------------------- ��ƼƼ��ü -> �������͸��� ���� ���� ----------------- */
		repo.saveAll(list);
		/* ---------------------- ��ƼƼ��ü -> �������͸��� ���� ���� ----------------- */

	}

	// http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=itBvLQRBMyMkMogfGhCxuSntdkkbcJtmhySH2jR9GD%2FcayyuW4%2FprkAKC0YvgzVTgbo8732gHiA%2Fo%2BZFWZrZAg%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20211001&endCreateDt=20211005

}

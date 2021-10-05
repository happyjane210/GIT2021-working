package com.git.myworkspace.opendata.covid;

import java.util.List;

import lombok.Data;

@Data
public class CovidSidoDailyResponse {
	private Response response;

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

	@Data
	public class Body {
		private Items items;
	}

	@Data
	public class Items {
		private List<Item> item;
	}

	@Data
	public class Item {
		// ������, (����)����, �ؿ�����, �����߻�
		// �ʼ�
		private String stdDay;
		private String gubun;
		private String gubunEn;
		private String overFlowCnt;
		private String localOccCnt;
		// ����
		private String defCnt;
		private String deathCnt;
		private String incDec;
	}

//    <deathCnt>15</deathCnt>			 	����ڼ�
//    <defCnt>6120</defCnt> 				Ȯ���ڼ�
//    <gubun>�˿�</gubun> 					���ñ��� **
//    <gubunEn>Lazaretto</gubunEn>			���п��� *
//    <incDec>9</incDec>   					���ϴ�� ������

//    <isolClearCnt>5917</isolClearCnt> 	�ݸ�������
//    <isolIngCnt>188</isolIngCnt>			�ݸ��� ȯ�ڼ�

//    <localOccCnt>0</localOccCnt>			�����߻��� **
//    <overFlowCnt>9</overFlowCnt>			�ؿ� ���Լ� **
//    <stdDay>2021�� 10�� 04�� 00��</stdDay>	�����Ͻ� **

}

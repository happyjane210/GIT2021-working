package exercise;

public class AccountExample {
	public static void main(String[] args) {
		
		//�׽�Ʈ ���̽��� �ڵ�� ¥�� -> �׽�Ʈ �ڵ�
		// ���� �׽�Ʈ���� ���� -> ��� �ڵ��� �ż��带 �׽�Ʈ
		
		// �׽�Ʈ ���̽� ���� 
		// actor : ������
		// event flow :  �ܰ�׿�(balance) �ܰ��� ����
		// pre-condition(��������) : ����.
		// expected result:
		// 0-100�� ������ ���� ���������� �ܰ���� ����
		// �׿��� ���� ������������ �ܰ�� ��ȭ �������

		// given - �׽�Ʈ ȯ�� �غ� (�׽�Ʈ�� ������, �׽�Ʈ�� ��ü)
		Account account = new Account();
		int[] testValues = { 10000, -100, 2000000, 30000000 };
		
		// when - �׽�Ʈ �����ͷ� �׽�Ʈ ���̽� ����
		account.setBalance(testValues[0]);
		
		// �������� - ������ ��
		int expectedResult = testValues[0];
		// ������ - get�޼���� ������ ��
		int acturalResult = account.getBalance();
		
		// then - ������(expected result)�� ������� (actual result)�� ��
		// ������: 10000
		if (acturalResult == expectedResult) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}
		
		
		// ��� �ڵ��� �ż��带 ��� ������ ��� test coverage �� 100%
		
		//�������� - �̰�� ������
		expectedResult = account.getBalance();
		
		//when - �׽�Ʈ �����ͷ� �׽�Ʈ ���̽� ����
		account.setBalance(testValues[1]);
		
		// then - ���� ������� ���� ����� ��
		if (account.getBalance() == expectedResult) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}
		

		// 0 ~ 1,000,000
		// ��谪 �׽�Ʈ�� �ؾ���
		// -1, 0, 1
		// 999,999, 1,000,000, 1,000,001
		
		
		// �������� - �̰�� ������
		expectedResult = account.getBalance();

		// when - �׽�Ʈ �����ͷ� �׽�Ʈ ���̽� ����
		account.setBalance(testValues[2]);
		
		// then - ���������� ���� ����� ��
		if(account.getBalance() == expectedResult) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}
		
	
		
		// when - �׽�Ʈ �����ͷ� �׽�Ʈ ���̽� ����
		account.setBalance(testValues[3]);

		// �������� - ������ ��
		expectedResult = testValues[3];

		// then - ������(expected result)�� �������(actual result)�� ��
		// ������: 10000
		if (account.getBalance() == expectedResult) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}
	}
}

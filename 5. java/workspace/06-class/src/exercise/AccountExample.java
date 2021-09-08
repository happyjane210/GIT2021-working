package exercise;

public class AccountExample {
	public static void main(String[] args) {
		
		Account account = new Account();
		int[] testValues = { 10000, -100, 200000, 30000000 };
		
		// when - �׽�Ʈ �����ͷ� �׽�Ʈ ���̽� ����
		account.setBalance(testValues[0]);
		
		int expectedResult = testValues[0];
		
		int acturalResult = account.getBalance();
		
		
		if (account.getBalance() == expectedResult) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}
		
		
		expectedResult = account.getBalance();
		
		
		// ���� �����
		int expectaeValue = account.getBalance();

		// when - �׽�Ʈ �����ͷ� �׽�Ʈ ���̽� ����
		account.setBalance(-100);
		
		// then - ���������� ���� �Ȱ��� ��
		if(account.getBalance() == expectaeValue) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}
		
	
		
		account.setBalance(200000);
		if(account.getBalance() == expectaeValue) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}
		
		
		account.setBalance(30000000);
		
		if(account.getBalance() == expectaeValue) {
			System.out.println("���̽� ��� - pass");
		} else {
			System.out.println("���̽� ���� - fail");
		}
	}
}

package exercise;

public class AccountExample {
	public static void main(String[] args) {
		
		Account account = new Account();
		int[] testValues = { 10000, -100, 200000, 30000000 };
		
		// when - 테스트 데이터로 테스트 케이스 실행
		account.setBalance(testValues[0]);
		
		int expectedResult = testValues[0];
		
		int acturalResult = account.getBalance();
		
		
		if (account.getBalance() == expectedResult) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}
		
		
		expectedResult = account.getBalance();
		
		
		// 예상 결과값
		int expectaeValue = account.getBalance();

		// when - 테스트 데이터로 테스트 케이스 실행
		account.setBalance(-100);
		
		// then - 예상결과값과 실제 셜과를 비교
		if(account.getBalance() == expectaeValue) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}
		
	
		
		account.setBalance(200000);
		if(account.getBalance() == expectaeValue) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}
		
		
		account.setBalance(30000000);
		
		if(account.getBalance() == expectaeValue) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}
	}
}

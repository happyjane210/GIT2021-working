package exercise;

public class AccountExample {
	public static void main(String[] args) {
		
		//테스트 케이스를 코드로 짜면 -> 테스트 코드
		// 단위 테스트에서 수행 -> 대상 코드의 매서드를 테스트
		
		// 테스트 케이스 내용 
		// actor : 개발자
		// event flow :  잔고액에(balance) 잔고값을 대입
		// pre-condition(사전조건) : 없음.
		// expected result:
		// 0-100만 사이의 값을 대입했을때 잔고액이 변경
		// 그외의 값을 대입했을때는 잔고액 변화 없어야함

		// given - 테스트 환경 준비 (테스트용 데이터, 테스트용 객체)
		Account account = new Account();
		int[] testValues = { 10000, -100, 2000000, 30000000 };
		
		// when - 테스트 데이터로 테스트 케이스 실행
		account.setBalance(testValues[0]);
		
		// 예상결과값 - 대입한 값
		int expectedResult = testValues[0];
		// 실제값 - get메서드로 꺼내온 값
		int acturalResult = account.getBalance();
		
		// then - 예상결과(expected result)와 실제결과 (actual result)를 비교
		// 예상결과: 10000
		if (acturalResult == expectedResult) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}
		
		
		// 대상 코드의 매서드를 모두 수행한 경우 test coverage 가 100%
		
		//예상결과값 - 이경우 이전값
		expectedResult = account.getBalance();
		
		//when - 테스트 데이터로 테스트 케이스 실행
		account.setBalance(testValues[1]);
		
		// then - 예상 결과값과 실제 결과를 비교
		if (account.getBalance() == expectedResult) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}
		

		// 0 ~ 1,000,000
		// 경계값 테스트를 해야함
		// -1, 0, 1
		// 999,999, 1,000,000, 1,000,001
		
		
		// 예상결과값 - 이경우 이전값
		expectedResult = account.getBalance();

		// when - 테스트 데이터로 테스트 케이스 실행
		account.setBalance(testValues[2]);
		
		// then - 예상결과값과 실제 결과를 비교
		if(account.getBalance() == expectedResult) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}
		
	
		
		// when - 테스트 데이터로 테스트 케이스 실행
		account.setBalance(testValues[3]);

		// 예상결과값 - 대입한 값
		expectedResult = testValues[3];

		// then - 예상결과(expected result)와 실제결과(actual result)를 비교
		// 예상결과: 10000
		if (account.getBalance() == expectedResult) {
			System.out.println("케이스 통과 - pass");
		} else {
			System.out.println("케이스 실패 - fail");
		}
	}
}

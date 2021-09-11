package chap6_4;

public class Calculator {

	// filed
	
	// Constructor 생성자
	
	// method 메서드
	void poweOn() {
		System.out.println("전원을 켭니다.");
	}
	
	int plus(int x, int y) {
		int result = x + y;
		return result;		// 결과값을 호출부로 돌려준다, return 값은 int
	}
	
	int minus(int x, int y) {
		int result = x - y;
		return result;
	}
	
	double divide(int x, int y) {	// double 타입의 값을 얻기위해 정수를 실수타입으로 바꾸어 계산
		double result = (double) x / (double) y;	// (타입)변수 : 강제 타입 변환
		return result;		// return 값은 double
	}
	
	int multy(int x, int y) {
		int result = x * y;
		return result;
	}
	
	void powerOff() {
		System.out.println("전원을 끕니다.");
	}
}

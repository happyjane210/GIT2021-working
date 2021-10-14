package static_;

public class Calculator {
	
	// static 멤버만 있음 , 기능적인 관점
	// static 필드, 메서드
	
	// 객체로 찍어내는 클래스가 아님
	// 필드값, 메서드를 기능적인 관점에서 실행하는 클래스
	// 꼭 static에 넣는 건 아니지만 주로 static변수에 사용함
	// static 변수는 공용적인 사용.
	// 다른데서 수정 못하게 하는게 일반적임
	final static double pi = 3.141592;
	
	static int plus(int a, int b) {
		return a + b;
	}
	
	static int minus(int a, int b) {
		return a - b;
	}
	
	static double getAreaCircle(int r) {
		return r * r * pi;
	}
}

// Member는 필요할때마다 찍어내서 쓰는애들 , 데이터적인 관점


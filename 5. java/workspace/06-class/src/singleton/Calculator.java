package singleton;

public class Calculator {
	
	// static 멤버만 있음 , 기능적인 관점
	// static 필드, 메서드
	
	// 객체로 찍어내는 클래스가 아님
	// 필드값, 메서드를 기능적인 관점에서 실행하는 클래스
	static double pi = 3.141592;
	
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


package singleton;

public class Calculator {
	
	// static 멤버만 있음 , 기능적인 관점
	// static 필드, 메서드
	
	// 객체로 찍어내는 클래스가 아님
	// 필드값, 메서드를 기능적인 관점에서 실행하는 클래스
	
	// 2. private static 필드로 객체를 생성함, 프로그램이 실행될때 변수 초기화가 일어남
	private static Calculator calc = new Calculator();
	
	// 싱글턴 클래스로 만들어 외부에서 객체 생성을 못하게 암
	private final static double PI = 3.141592;
	
	// 1. 기본생성자를 외부에서 접근 못하게함
	private Calculator() {
		
	}
	
	// 3.  외부에서 private static으로 생성한 객체를 접근할 수 있게함
	public static Calculator getInstance() {
		return calc;    // singleton
	}
	
	int plus(int a, int b) {
		return a + b;
	}
	
	int minus(int a, int b) {
		return a - b;
	}
	
	double getAreaCircle(int r) {
		return r * r * PI;
	}
}

// Member는 필요할때마다 찍어내서 쓰는애들 , 데이터적인 관점


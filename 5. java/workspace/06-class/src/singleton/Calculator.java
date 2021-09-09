package singleton;

//객체로 찍어내는 클래스가 아님
//메서드를 기능적인 관점에서 실행하는 클래스

//싱글턴(singleton) 클래스로 만들어 외부에서 객체 생성을 못하게 함
//객체접근 메서드를 이용해서 사전에 생성된 객체만 접근해서 사용하게 함

//패턴 (Pattern)
//- Context(문맥, 상황) -> Solution(해결책)
//- 두개가 결합되어있는 것을 Pattern

public class Calculator {
	
	// static 멤버만 있음 , 기능적인 관점
	// static 필드, 메서드
	
	// 객체로 찍어내는 클래스가 아님
	// 필드값, 메서드를 기능적인 관점에서 실행하는 클래스
	
	// 2. private static 필드로 객체를 생성함, 프로그램이 실행될때 변수 초기화가 일어남
	//private static Calculator calc = new Calculator();
	private static Calculator calc;

	
	// 1. 기본생성자를 외부에서 접근 못하게함
	private Calculator() {
		
	}
	
	
	
	// 3.  외부에서 private static으로 생성한 객체를 접근할 수 있게함
	public static Calculator getInstance() {
		// Calculator 객체를 사용할 시점에 메모리 공간을 초기화 함
		if (calc == null) {
			calc = new Calculator();
		}
		return calc;    // singleton
	}
	
	
	// 싱글턴 클래스로 만들어 외부에서 객체 생성을 못하게 암
	private final double PI = 3.141592;
	
	
	public int plus(int a, int b) {
		return a + b;
	}
	
	public int minus(int a, int b) {
		return a - b;
	}
	
	public double getAreaCircle(int r) {
		return r * r * this.PI;
	}
}

// Member는 필요할때마다 찍어내서 쓰는애들 , 데이터적인 관점


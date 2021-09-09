package singleton;

public class CalculatorExample {
	public static void main(String[] args) {
		// 인스턴스(객체) 셍성안하고 바로 사용가능
		// 주로 자주쓰는 값이나 기능들을 static으로 정의하여 사용가능
		
		// 외부에서 객체생성안됨 new Calculator 불가
		Calculator calc = Calculator.getInstance();
		System.out.println(calc);
		System.out.println(calc.getAreaCircle(5));
		
		//singleton.Calculator@1175e2db  객체에 대한 해시코드
		
		// 객체 접근 매서드를 이용해서 사전에 생성된 객체만 접근해서 사용하게 함.
		Calculator calc1 = Calculator.getInstance();
		System.out.println(calc1);
		System.out.println(calc.getAreaCircle(10));
		
		//singleton.Calculator@1175e2db  객체에 대한 해시코드
		
//		System.out.println(Calculator.PI);
//		System.out.println(Calculator.plus(10, 5));
//		
//		System.out.println(Calculator.getAreaCircle(5));
		
		// 객체를 생성해서 쓰는게 큰 의미가 없음
		// Calculator calc = new Calculator();
		
	}
}

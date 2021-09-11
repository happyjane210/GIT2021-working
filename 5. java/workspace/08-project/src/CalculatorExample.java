
public class CalculatorExample {
	public static void main(String[] args) {
		// 인터페이스틑 객체를 생성할 수 없음
		// Calculator c = new Calculator();
		// 목업 클래스로 일단 사용
		
		// 클래스 구현이 완료됨  ->  구현된걸로 갈아낌
		//Calculator c = new CalculatorImpV1();
		
		// 구현 클래스 를 업에이트함  ->  고친 걸로 갈아낌
		Calculator c = new CalculatorImpV2();
		
		// 이부분, 가져다쓰는 코드는 고칠 필요가 없음.
		System.out.println(c.plus(5, 10));
		System.out.println(c.minus(10, 1));
		System.out.println(c.areaCircle(5));
	}
}

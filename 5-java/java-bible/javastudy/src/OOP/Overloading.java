package OOP;

public class Overloading {
	public static void main(String[] args) {
		// overloading 같은 이름의 매소드를 만들수있다.
		// 대신 매개변수의 타입이 다르거나
		// 매개변수의 갯수가 달라야한다.
		
		System.out.println(12);  //12
		System.out.println(true);  // true
		System.out.println("김셩");  // 김석
		System.out.println("A");    // A
		System.out.println(0.241);   // 0.241
		// 타입이 다른데도 그대로 호출됨, 자바를 설계할 때 이렇게 인식하도록 설계됨
		// 들어가는 것에 따라 다른 값이 나오도록 설계 => 오버로딩 메소드
		
		// 원랜 이렇게 써야함, 하지만 같은 이름으로 다른값을 출력할 수 있도록 해줌 = 오버로딩
//		System.out.printInt(12);  //12
//		System.out.printBoolean(true);  // true
//		System.out.printString("김셩");  // 김석
//		System.out.printChar("A");    // A
//		System.out.printDoubel(0.241);   // 0.241
	}
}

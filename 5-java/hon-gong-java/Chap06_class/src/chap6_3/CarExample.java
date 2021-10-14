package chap6_3;

public class CarExample {
	// 생성자를 호출해서 객체 생성
	// 객체의 매개변수가 없으면, 기본 생성자를 호출하는 것임
	public static void main(String[] args) {
		Car myCar = new Car("granger", "white", 3000);   //메개값을 지정해줘야함
		System.out.println(myCar);
	}
	
}
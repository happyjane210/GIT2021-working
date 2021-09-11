package chap6_3;

public class Car {

	// filed
	// Constructor 기본 생성자

	//생성자 선언
 	public Car (String model, String color, int cc) {	
 		System.out.println(model);
 		System.out.println(color);
 		System.out.println(cc);
 		System.out.println(model + " 모델의 " + color + " 색상과 배기량 " + cc + "의 자동차가 생성됨");
	}

	
}

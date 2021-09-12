package chap6_4;

public class Computer {
	//filed
	
	//Constructor
	
	//Method
	// Computer 라는 객체가 있어야 쓸수 있는 메서드 (computer 클래스 안에 있기 때문?)
	// 배열 변수를 매개값으로 제공
	public int sum1(int[] values) {  // sum1매서드의 매개변수값을 모두 더해서 리턴값으로 보내줌
		int sum = 0;
		for(int i = 0 ; i < values.length ; i++) {
			sum += values[i];
		}
		return sum;
	}
	
	// int ... values == values를 배열 타입으로 인식함
	// ... 으로 된 매개변수를 제공 : 배열변수와 같음
	int sum2(int ... values) {
		int sum = 0;
		for(int i = 0 ; i < values.length ; i++) {
			sum += values[i];
		}
		return sum;
	}
	
}

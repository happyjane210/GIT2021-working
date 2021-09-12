package chap6_4;

public class CarExample2 {

	public static void main(String[] args) {
		
		Car2 car = new Car2();
		
		// 결과 값을 받지 않을 때
		car.keyTurnOn();
		car.run();
		// 결과 값을 받을 때
		int speed = car.getSpeed();
		System.out.println("현재속도: " + speed +"km/h");

	}

}

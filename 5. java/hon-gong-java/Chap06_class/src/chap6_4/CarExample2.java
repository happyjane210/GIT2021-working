package chap6_4;

public class CarExample2 {

	public static void main(String[] args) {
		
		Car2 car = new Car2();
		
		// ��� ���� ���� ���� ��
		car.keyTurnOn();
		car.run();
		// ��� ���� ���� ��
		int speed = car.getSpeed();
		System.out.println("����ӵ�: " + speed +"km/h");

	}

}

package chap6_4;

public class CarExample {
	public static void main(String[] args) {
		
		Car car = new Car();
		
		car.setGas(5);
		
		boolean gasState = car.isLeftGas();
		if(gasState) {
			System.out.println("����մϴ�");
			car.run();
		}
		
		if(car.isLeftGas()) {
			System.out.println("gas�� ������ �ʿ䰡 �����ϴ�.");
		} else {
			System.out.println("gas�� �����ϼ���.");
		}
	}
}


public class CalculatorExample {
	public static void main(String[] args) {
		// �������̽��z ��ü�� ������ �� ����
		// Calculator c = new Calculator();
		// ��� Ŭ������ �ϴ� ���
		
		// Ŭ���� ������ �Ϸ��  ->  �����Ȱɷ� ���Ƴ�
		//Calculator c = new CalculatorImpV1();
		
		// ���� Ŭ���� �� ������Ʈ��  ->  ��ģ �ɷ� ���Ƴ�
		Calculator c = new CalculatorImpV2();
		
		// �̺κ�, �����پ��� �ڵ�� ��ĥ �ʿ䰡 ����.
		System.out.println(c.plus(5, 10));
		System.out.println(c.minus(10, 1));
		System.out.println(c.areaCircle(5));
	}
}

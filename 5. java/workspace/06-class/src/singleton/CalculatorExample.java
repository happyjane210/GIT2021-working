package singleton;

public class CalculatorExample {
	public static void main(String[] args) {
		// �ν��Ͻ�(��ü) �ļ����ϰ� �ٷ� ��밡��
		// �ַ� ���־��� ���̳� ��ɵ��� static���� �����Ͽ� ��밡��
		System.out.println(Calculator.pi);
		System.out.println(Calculator.plus(10, 5));
		
		System.out.println(Calculator.getAreaCircle(5));
		
		// ��ü�� �����ؼ� ���°� ū �ǹ̰� ����
		// Calculator calc = new Calculator();
		
	}
}

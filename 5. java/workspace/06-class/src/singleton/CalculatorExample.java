package singleton;

public class CalculatorExample {
	public static void main(String[] args) {
		// �ν��Ͻ�(��ü) �ļ����ϰ� �ٷ� ��밡��
		// �ַ� ���־��� ���̳� ��ɵ��� static���� �����Ͽ� ��밡��
		
		// �ܺο��� ��ü�����ȵ� new Calculator �Ұ�
		Calculator calc = Calculator.getInstance();
		System.out.println(calc);
		System.out.println(calc.getAreaCircle(5));
		
		//singleton.Calculator@1175e2db  ��ü�� ���� �ؽ��ڵ�
		
		// ��ü ���� �ż��带 �̿��ؼ� ������ ������ ��ü�� �����ؼ� ����ϰ� ��.
		Calculator calc1 = Calculator.getInstance();
		System.out.println(calc1);
		System.out.println(calc.getAreaCircle(10));
		
		//singleton.Calculator@1175e2db  ��ü�� ���� �ؽ��ڵ�
		
//		System.out.println(Calculator.PI);
//		System.out.println(Calculator.plus(10, 5));
//		
//		System.out.println(Calculator.getAreaCircle(5));
		
		// ��ü�� �����ؼ� ���°� ū �ǹ̰� ����
		// Calculator calc = new Calculator();
		
	}
}

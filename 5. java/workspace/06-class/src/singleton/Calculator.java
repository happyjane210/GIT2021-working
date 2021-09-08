package singleton;

public class Calculator {
	
	// static ����� ���� , ������� ����
	// static �ʵ�, �޼���
	
	// ��ü�� ���� Ŭ������ �ƴ�
	// �ʵ尪, �޼��带 ������� �������� �����ϴ� Ŭ����
	
	// 2. private static �ʵ�� ��ü�� ������, ���α׷��� ����ɶ� ���� �ʱ�ȭ�� �Ͼ
	private static Calculator calc = new Calculator();
	
	// �̱��� Ŭ������ ����� �ܺο��� ��ü ������ ���ϰ� ��
	private final static double PI = 3.141592;
	
	// 1. �⺻�����ڸ� �ܺο��� ���� ���ϰ���
	private Calculator() {
		
	}
	
	// 3.  �ܺο��� private static���� ������ ��ü�� ������ �� �ְ���
	public static Calculator getInstance() {
		return calc;    // singleton
	}
	
	int plus(int a, int b) {
		return a + b;
	}
	
	int minus(int a, int b) {
		return a - b;
	}
	
	double getAreaCircle(int r) {
		return r * r * PI;
	}
}

// Member�� �ʿ��Ҷ����� ���� ���¾ֵ� , ���������� ����


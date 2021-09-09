package singleton;

//��ü�� ���� Ŭ������ �ƴ�
//�޼��带 ������� �������� �����ϴ� Ŭ����

//�̱���(singleton) Ŭ������ ����� �ܺο��� ��ü ������ ���ϰ� ��
//��ü���� �޼��带 �̿��ؼ� ������ ������ ��ü�� �����ؼ� ����ϰ� ��

//���� (Pattern)
//- Context(����, ��Ȳ) -> Solution(�ذ�å)
//- �ΰ��� ���յǾ��ִ� ���� Pattern

public class Calculator {
	
	// static ����� ���� , ������� ����
	// static �ʵ�, �޼���
	
	// ��ü�� ���� Ŭ������ �ƴ�
	// �ʵ尪, �޼��带 ������� �������� �����ϴ� Ŭ����
	
	// 2. private static �ʵ�� ��ü�� ������, ���α׷��� ����ɶ� ���� �ʱ�ȭ�� �Ͼ
	//private static Calculator calc = new Calculator();
	private static Calculator calc;

	
	// 1. �⺻�����ڸ� �ܺο��� ���� ���ϰ���
	private Calculator() {
		
	}
	
	
	
	// 3.  �ܺο��� private static���� ������ ��ü�� ������ �� �ְ���
	public static Calculator getInstance() {
		// Calculator ��ü�� ����� ������ �޸� ������ �ʱ�ȭ ��
		if (calc == null) {
			calc = new Calculator();
		}
		return calc;    // singleton
	}
	
	
	// �̱��� Ŭ������ ����� �ܺο��� ��ü ������ ���ϰ� ��
	private final double PI = 3.141592;
	
	
	public int plus(int a, int b) {
		return a + b;
	}
	
	public int minus(int a, int b) {
		return a - b;
	}
	
	public double getAreaCircle(int r) {
		return r * r * this.PI;
	}
}

// Member�� �ʿ��Ҷ����� ���� ���¾ֵ� , ���������� ����


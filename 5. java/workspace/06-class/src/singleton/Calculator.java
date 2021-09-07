package singleton;

public class Calculator {
	
	// static ����� ���� , ������� ����
	// static �ʵ�, �޼���
	
	// ��ü�� ���� Ŭ������ �ƴ�
	// �ʵ尪, �޼��带 ������� �������� �����ϴ� Ŭ����
	static double pi = 3.141592;
	
	static int plus(int a, int b) {
		return a + b;
	}
	
	static int minus(int a, int b) {
		return a - b;
	}
	
	static double getAreaCircle(int r) {
		return r * r * pi;
	}
}

// Member�� �ʿ��Ҷ����� ���� ���¾ֵ� , ���������� ����


package chap6_4;

import java.util.Scanner;

public class Calculator {

	// filed
	
	// Constructor ������
	
	// method �޼���
	void poweOn() {
		System.out.println("������ �մϴ�.");
	}
	
	int plus(int x, int y) {
		int result = x + y;
		return result;		// ������� ȣ��η� �����ش�, return ���� int
	}
	
	int minus(int x, int y) {
		int result = x - y;
		return result;
	}
	
	double divide(int x, int y) {	// double Ÿ���� ���� ������� ������ �Ǽ�Ÿ������ �ٲپ� ���
		double result = (double) x / (double) y;	// (Ÿ��)���� : ���� Ÿ�� ��ȯ
		return result;		// return ���� double
	}
	
	int multy(int x, int y) {
		int result = x * y;
		return result;
	}
	
	void powerOff() {
		System.out.println("������ ���ϴ�.");
	}
	
	
	public int inputX(Scanner scanner) {
		System.out.println("���� x��(��) �Է��ϼ���");
		System.out.print("�Է�> ");
		
		int x = scanner.nextInt();
		return x;
	}
	
	public int inputY(Scanner scanner) {
		System.out.println("���� y��(��) �Է��ϼ���");
		System.out.println("�Է�> ");
		
		int y = scanner.nextInt();
		return y;
	}
	
	
}

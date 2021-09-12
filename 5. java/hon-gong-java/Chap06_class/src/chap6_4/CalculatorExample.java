package chap6_4;

import java.util.Scanner;

public class CalculatorExample {
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		Scanner scanner = new Scanner(System.in);
		boolean run = true;

		// ���⸦ Ű�ºκ�
		System.out.println("on/off�� ������ �����ϼ���");
		System.out.print("�Է�> ");		
		
		while (run) {
			
			String power = scanner.next();
			
			// String �� ���  power.equals("on")
			if (power.equals("on")) {
				// calc. ��ü�� ���� �� �޼��� ȣ�� ����
				calc.poweOn();
			} else if (power.equals("off")) {
				calc.powerOff();
				break;
			} else {
				System.out.println("������ ���ּ���.");
				break;
			} 
			
			System.out.println("---------------------------");
			System.out.println("CALCULATOR: plus | minus | divide | multy");
			System.out.println("��� ����� �Է��ϼ���");
			System.out.println("---------------------------");
			System.out.print("�Է�> ");
			
			String cal = scanner.next();
			
			// ��� �����ϴ� �κ�
			if(cal.equals("plus")) {
				
				System.out.println("���� x��(��) �Է��ϼ���");
				System.out.print("�Է�> ");
				
				int x = scanner.nextInt();
				
				System.out.println("���� y��(��) �Է��ϼ���");
				System.out.print("�Է�> ");
				
				int y = scanner.nextInt();

				int resultP = calc.plus(x, y);
				System.out.println("plus result: " + resultP);
				
				System.out.println("����Ϸ��� on / �����Ϸ��� off �� �Է��ϼ���.");
				System.out.print("�Է�> ");
				continue;
				
			} else if (cal.equals("minus")) {
				
				System.out.println("���� x��(��) �Է��ϼ���");
				System.out.print("�Է�> ");
				
				int x = scanner.nextInt();
				
				System.out.println("���� y��(��) �Է��ϼ���");
				System.out.print("�Է�> ");
				
				int y = scanner.nextInt();
				
				int resultM = calc.minus(x, y);
				
				System.out.println("minus result: " + resultM);
				
				System.out.println("����Ϸ��� on / �����Ϸ��� off �� �Է��ϼ���.");
				System.out.print("�Է�> ");
				continue;
				
			} else if (cal.equals("divide")) {

				System.out.println("���� x��(��) �Է��ϼ���");
				System.out.print("�Է�> ");
				
				int x = scanner.nextInt();
				
				System.out.println("���� y��(��) �Է��ϼ���");
				System.out.print("�Է�> ");
				
				int y = scanner.nextInt();
				
				double resultD = calc.divide(x, y);
				
				System.out.println("divide result: " + resultD);
				
				System.out.println("����Ϸ��� on / �����Ϸ��� off �� �Է��ϼ���.");
				System.out.print("�Է�> ");
				continue;
				
			} else if (cal.equals("multy")) {
				
				System.out.println("���� x��(��) �Է��ϼ���");
				System.out.print("�Է�> ");
				
				int x = scanner.nextInt();
				
				System.out.println("���� y��(��) �Է��ϼ���");
				System.out.print("�Է�> ");
				
				int y = scanner.nextInt();
				
				int resultMU = calc.multy(x, y);
				
				System.out.println("multy result: " + resultMU);
				
				System.out.println("����Ϸ��� on / �����Ϸ��� off �� �Է��ϼ���.");
				System.out.print("�Է�> ");
				continue;
				
			} else if (cal.equals("off")) {
				calc.powerOff();
				break;
			} else {
				System.out.println("��� ����� Ʋ�Ƚ��ϴ�.");
				System.out.println("����Ϸ��� on / �����Ϸ��� off �� �Է��ϼ���.");
				System.out.print("�Է�> ");
				continue;
			}
		}
		
		
		

		
		
		

		
		// �Ű������� int, ������ byteŸ������ �����ϸ�,
		// byte Ÿ���� �ڵ����� int �� �ٲ�
//		byte x = 10;
//		byte y = 4;
//		double result2 = calc.divide(x, y);
//		System.out.println("result2: " + result2);
		
		//
		// �ڵ��� �ߺ��� ����
		
	}
}

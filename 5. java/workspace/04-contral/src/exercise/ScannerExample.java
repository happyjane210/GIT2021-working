package exercise;

import java.util.Scanner;
// Scanner ������ ���� ���콺 Ŀ���ø��� ctrl + 1 => quick fix

public class ScannerExample {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----���ڸ� �Է��ϼ��� / *���� �ȵ�----");
		System.out.print("> ");
		
		String input = "";
		input = scanner.next();  // ���ڿ� �Է¹��� �� ����
		
		System.out.println("�Է� ��� : " + input);
		System.out.println("  ");
		
		//--------------------------------
				
		System.out.println("----������ �Է��ϼ���  / * ����, �Ǽ� �ȵ�----");
		System.out.print("> ");
		
		int num = 0;
		num = scanner.nextInt();  // ���� �Է� ���� �� ����
		
		System.out.println("�Է� ��� : " + num);
		
		
	}
}

package practice;
import java.util.Scanner;

public class practice1 {

	public static void main(String[] args) {
		boolean run = true;
		int balance = 0;   // ������ ������ ��
		Scanner scanner = new Scanner(System.in);
		//scanner.nextInt();  ��������� ���ڸ� �ԷĹ��� �� �ְ� ���ִ� �޼ҵ�
		// scanner js�� prompt ��� �����ϸ��
		
		while(run) {
			System.out.println("---------------------");
			System.out.println("1.���� | 2.��� | 3.�ܰ� | 4.����");
			System.out.println("---------------------");
			System.out.print("����>");  // println �� ���ͱ��, print �� �����ٿ� ����
			int input = scanner.nextInt();  // input ���� �ȿ� ����� �Է°���
			
			// 1. �� scanner�� ��������
			if(input == 1) {
				System.out.print("���ݾ�>");
				balance = scanner.nextInt(); // ���������� �ѹ����� �Է� ����
				continue;
			} else if(input == 2) {
				System.out.print("��ݾ�>");
				balance = balance - scanner.nextInt();
				continue;
			} else if(input == 3) {
				System.out.println("�ܰ�> " + balance);
				continue;
			} else if (input == 4) {
				System.out.print("����");
				run = false;
			}
			
		}

	}

}

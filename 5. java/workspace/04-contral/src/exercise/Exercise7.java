package exercise;

import java.util.Scanner;

public class Exercise7 {
	public static void main(String[] args) {
		boolean run = true;
		
		// �ܰ���
		int balance = 0;
		Scanner scanner = new Scanner(System.in);  // scanner ������ ���ο� ��ĳ�� ����
		
		// run �� true�� ���� �ݺ�       println ������ �Ѿ  print �� �ȳѾ
		while (run) {
			System.out.println("------------------------------");
			System.out.println("1.���� | 2.��� | 3.�ܰ� | 4.����");
			System.out.println("------------------------------");
			System.out.print("����> ");
			
			// �ۼ���ġ, �Է°� ����
			switch (scanner.nextByte()) {
			// �Է°��� ���� ����, ���, �ܰ�, ��� ���� ����
			// �����϶�
			case 1:
				System.out.print("���ݾ�> ");
				// �Է¹��� ���� �ܰ� �߰�
				balance += scanner.nextInt();
				break;
			// ����϶�
			case 2:
				System.out.print("��ݾ�> ");
				// �Է¹��� ���� �ܰ��� ����
				balance -= scanner.nextInt();
				break;
			// �ܰ� ���
			case 3:
				System.out.println("�ܾ�> " + balance);
				break;
			// �Է°� 4, �����϶�, run false�� ����
			case 4:
				run = false;
				break;
			}  // switch ��
		}    // while ����
		System.out.println("���α׷��� �����մϴ�.");
	}
}

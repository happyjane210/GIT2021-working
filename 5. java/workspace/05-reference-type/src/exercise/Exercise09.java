package exercise;

import java.util.Scanner;

public class Exercise09 {
	public static void main(String[] args) {
		boolean run = true;

		int studentNum = 0;
		int[] scores = null;

		Scanner scanner = new Scanner(System.in); // System.in(standard input stream, Ű���� �Է�)

		while (run) {
			System.out.println("---------------------------");
			System.out.println("1.�л��� | 2.�����Է� | 3.��������Ʈ | 4.�м� | 5.����");
			System.out.println("---------------------------");
			System.out.print("����> ");

			// ���� �Է°��� �ܼ�â���� �Է� ����
			int selectNo = scanner.nextInt();

			switch (selectNo) {
			case 1:
				// �л����� �Է¹���
				// �Է��� �л��� ��ŭ �迭ũ�⸦ �ʱ�ȭ
				break;
			case 2:
				// �迭ũ�⸸ŭ �ݺ��ؼ� ������ �Է� ����
				break;
			case 3:
				// �迭ũ�⸸ŭ �ݺ��ؼ� ���� ����� ���
				break;
			case 4:
				// �ְ������� ������� ���
				break;
			case 5:
				// run false�� �ݺ��� Ż��
				run = false;
				break;
			}
		}

		System.out.println("���α׷� ����");
	}
}

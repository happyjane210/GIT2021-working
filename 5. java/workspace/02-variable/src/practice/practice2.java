package practice;
import java.util.Scanner;

public class practice2 {

	public static void main(String[] args) {
		boolean run = true;
		int studentNum = 0;
		int[] scores = null;
		Scanner scanner = new Scanner(System.in);   
		
		while(run) {
			System.out.println("------------------------");
			System.out.println("1.�л��� | 2.�����Է� | 3.��������Ʈ | 4.�м� | 5.����");
			System.out.println("------------------------");
			System.out.print("����> ");
			
			int selectNo = scanner.nextInt();
			
			if (selectNo == 1) {
				System.out.print("�л���> ");
				studentNum = scanner.nextInt();
				scores = new int[studentNum];  // studentNum �Է��� ����ŭ �л� �� �迭 ����
				continue;
				
			} else if (selectNo == 2) {
				for(int i=0; i<studentNum ;i++) {
					System.out.print("scores[" + i + "]> ");
					scores[i] =scanner.nextInt();
				}
				continue;
			} else if (selectNo == 3) {
				for(int i=0; i<studentNum ;i++) {
					System.out.println("score[" + i + "]> " + scores[i]);
				}
				continue;
			} else if (selectNo == 4) {
				int max = scores[0];   // max ������ for �� �ȿ��� ������ i����ŭ ������ ����, ���������� ����� �����
				
				for(int i=0; i<studentNum; i++) {
					if(max < scores[i]) {
						max = scores[i];
					} else if (max > scores[i]) {
						max = max;
					}
					
				// ���� ����  int max2 = 0;     // [10, 23, 15, 25, 40]
				//for (int num : scores) {
				//	max2 = max2 < num ? num : max2;
				//}
				}
				System.out.println("�ְ� ����" + max);
				
				int sum = 0;
				for(int num : scores) {
					sum += num;
				}
				int avg = sum / studentNum;
				System.out.println("��� ����" + avg);
				continue;

			} else if (selectNo == 5) {
				run = false;
			}
		}
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
	} 
	
}

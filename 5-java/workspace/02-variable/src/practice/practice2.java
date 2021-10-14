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
			System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
			System.out.println("------------------------");
			System.out.print("선택> ");
			
			int selectNo = scanner.nextInt();
			
			if (selectNo == 1) {
				System.out.print("학생수> ");
				studentNum = scanner.nextInt();
				scores = new int[studentNum];  // studentNum 입력한 수만큼 학생 수 배열 만듬
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
				int max = scores[0];   // max 변수를 for 문 안에서 돌리면 i값만큼 변수가 생김, 전역번수로 만들어 줘야함
				
				for(int i=0; i<studentNum; i++) {
					if(max < scores[i]) {
						max = scores[i];
					} else if (max > scores[i]) {
						max = max;
					}
					
				// 위랑 같음  int max2 = 0;     // [10, 23, 15, 25, 40]
				//for (int num : scores) {
				//	max2 = max2 < num ? num : max2;
				//}
				}
				System.out.println("최고 점수" + max);
				
				int sum = 0;
				for(int num : scores) {
					sum += num;
				}
				int avg = sum / studentNum;
				System.out.println("평균 점수" + avg);
				continue;

			} else if (selectNo == 5) {
				run = false;
			}
		}
		System.out.println("프로그램이 종료되었습니다.");
	} 
	
}

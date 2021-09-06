package practice;
import java.util.Scanner;

public class practice1 {

	public static void main(String[] args) {
		boolean run = true;
		int balance = 0;   // 예금을 저장할 곳
		Scanner scanner = new Scanner(System.in);
		//scanner.nextInt();  사용자한테 숫자를 입렬받을 수 있게 해주는 메소드
		// scanner js의 prompt 라고 생각하면됨
		
		while(run) {
			System.out.println("---------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("---------------------");
			System.out.print("선택>");  // println 은 엔터기능, print 는 같은줄에 쓰임
			int input = scanner.nextInt();  // input 변수 안에 사용자 입력값들어감
			
			// 1. 이 scanner에 들어왔을때
			if(input == 1) {
				System.out.print("예금액>");
				balance = scanner.nextInt(); // 있을때마다 한번씩만 입력 받음
				continue;
			} else if(input == 2) {
				System.out.print("출금액>");
				balance = balance - scanner.nextInt();
				continue;
			} else if(input == 3) {
				System.out.println("잔고> " + balance);
				continue;
			} else if (input == 4) {
				System.out.print("종료");
				run = false;
			}
			
		}

	}

}

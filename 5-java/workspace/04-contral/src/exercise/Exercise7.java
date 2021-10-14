package exercise;

import java.util.Scanner;

public class Exercise7 {
	public static void main(String[] args) {
		boolean run = true;
		
		// 잔고변수
		int balance = 0;
		Scanner scanner = new Scanner(System.in);  // scanner 변수로 새로운 스캐너 생성
		
		// run 이 true일 동안 반복       println 다음줄 넘어감  print 줄 안넘어감
		while (run) {
			System.out.println("------------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("------------------------------");
			System.out.print("선택> ");
			
			// 작성위치, 입력값 받음
			switch (scanner.nextByte()) {
			// 입력값에 따라 예금, 출금, 잔고, 출력 로직 수행
			// 예금일때
			case 1:
				System.out.print("예금액> ");
				// 입력받은 값을 잔고에 추가
				balance += scanner.nextInt();
				break;
			// 출금일때
			case 2:
				System.out.print("출금액> ");
				// 입력받은 값을 잔고에서 꺼냄
				balance -= scanner.nextInt();
				break;
			// 잔고 출력
			case 3:
				System.out.println("잔액> " + balance);
				break;
			// 입력값 4, 종료일때, run false로 나감
			case 4:
				run = false;
				break;
			}  // switch 문
		}    // while 문ㅓ
		System.out.println("프로그램을 종료합니다.");
	}
}

package chap6_4;

import java.util.Scanner;

public class CalculatorExample {
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		Scanner scanner = new Scanner(System.in);
		boolean run = true;

		// 계산기를 키는부분
		System.out.println("on/off로 전원을 제어하세요");
		System.out.print("입력> ");		
		
		while (run) {
			
			String power = scanner.next();
			
			// String 비교 방법  power.equals("on")
			if (power.equals("on")) {
				// calc. 객체로 접근 후 메서드 호출 선언
				calc.poweOn();
			} else if (power.equals("off")) {
				calc.powerOff();
				break;
			} else {
				System.out.println("전원을 켜주세요.");
				break;
			} 
			
			System.out.println("---------------------------");
			System.out.println("CALCULATOR: plus | minus | divide | multy");
			System.out.println("계산 방식을 입력하세요");
			System.out.println("---------------------------");
			System.out.print("입력> ");
			
			String cal = scanner.next();
			
			// 계산 진행하는 부분
			if(cal.equals("plus")) {
				
				System.out.println("숫자 x을(를) 입력하세요");
				System.out.print("입력> ");
				
				int x = scanner.nextInt();
				
				System.out.println("숫자 y을(를) 입력하세요");
				System.out.print("입력> ");
				
				int y = scanner.nextInt();

				int resultP = calc.plus(x, y);
				System.out.println("plus result: " + resultP);
				
				System.out.println("계속하려면 on / 종료하려면 off 를 입력하세요.");
				System.out.print("입력> ");
				continue;
				
			} else if (cal.equals("minus")) {
				
				System.out.println("숫자 x을(를) 입력하세요");
				System.out.print("입력> ");
				
				int x = scanner.nextInt();
				
				System.out.println("숫자 y을(를) 입력하세요");
				System.out.print("입력> ");
				
				int y = scanner.nextInt();
				
				int resultM = calc.minus(x, y);
				
				System.out.println("minus result: " + resultM);
				
				System.out.println("계속하려면 on / 종료하려면 off 를 입력하세요.");
				System.out.print("입력> ");
				continue;
				
			} else if (cal.equals("divide")) {

				System.out.println("숫자 x을(를) 입력하세요");
				System.out.print("입력> ");
				
				int x = scanner.nextInt();
				
				System.out.println("숫자 y을(를) 입력하세요");
				System.out.print("입력> ");
				
				int y = scanner.nextInt();
				
				double resultD = calc.divide(x, y);
				
				System.out.println("divide result: " + resultD);
				
				System.out.println("계속하려면 on / 종료하려면 off 를 입력하세요.");
				System.out.print("입력> ");
				continue;
				
			} else if (cal.equals("multy")) {
				
				System.out.println("숫자 x을(를) 입력하세요");
				System.out.print("입력> ");
				
				int x = scanner.nextInt();
				
				System.out.println("숫자 y을(를) 입력하세요");
				System.out.print("입력> ");
				
				int y = scanner.nextInt();
				
				int resultMU = calc.multy(x, y);
				
				System.out.println("multy result: " + resultMU);
				
				System.out.println("계속하려면 on / 종료하려면 off 를 입력하세요.");
				System.out.print("입력> ");
				continue;
				
			} else if (cal.equals("off")) {
				calc.powerOff();
				break;
			} else {
				System.out.println("계산 방식이 틀렸습니다.");
				System.out.println("계속하려면 on / 종료하려면 off 를 입력하세요.");
				System.out.print("입력> ");
				continue;
			}
		}
		
		
		

		
		
		

		
		// 매개변수는 int, 변수를 byte타입으로 저장하면,
		// byte 타입이 자동으로 int 로 바뀜
//		byte x = 10;
//		byte y = 4;
//		double result2 = calc.divide(x, y);
//		System.out.println("result2: " + result2);
		
		//
		// 코드의 중복이 많음
		
	}
}

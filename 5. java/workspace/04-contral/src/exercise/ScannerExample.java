package exercise;

import java.util.Scanner;
// Scanner 빨간줄 위에 마우스 커서올리고 ctrl + 1 => quick fix

public class ScannerExample {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----문자를 입력하세요 / *띄어쓰기 안됨----");
		System.out.print("> ");
		
		String input = "";
		input = scanner.next();  // 문자열 입력받을 수 있음
		
		System.out.println("입력 결과 : " + input);
		System.out.println("  ");
		
		//--------------------------------
				
		System.out.println("----정수를 입력하세요  / * 문자, 실수 안됨----");
		System.out.print("> ");
		
		int num = 0;
		num = scanner.nextInt();  // 숫자 입력 받을 수 있음
		
		System.out.println("입력 결과 : " + num);
		
		
	}
}

package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BanckApplication {
	//Map 여러가지 형태의 Map 가능한 타입 (interface)
	// HashMap  ,  HashTable  ,  TreeMap
	// 대입하는 자료구조에 따라 같은 매서드를 호출하더라도 
	// 내부적인 처리방식이 다름
	
	// 계좌목록 Map 객체
	// Map<키타입, 값타입> 변수명 = new HashMap<키타입, 값타입>();
	
	private static Map<String, Account> accounts = new HashMap<String, Account>();
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("-------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("-------------------------------------");
			System.out.println("선택> ");
			
			Account account = new Account();
			
			int selectNo = scanner.nextInt();
			
			if (selectNo == 1) {
				createAccount();
			} else if (seletNo == 2) {
				accountList();
			} else if (selectNo == 3) {
				deposit();
			} else if (selectNo == 4) {
				withdraw();
			} else if (selectNo == 5) {
				run = false;
			} else if (selectNo > 5 || selectNo < 1) {
				System.out.println("1-5를 입력하세요");
			}
		}
		
		System.out.println("프로그램 종료");
	}
}

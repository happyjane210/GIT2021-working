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
	
	//Account account = new Account();
	private static Map<String, Account> accounts = new HashMap<String, Account>();
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		boolean run = true;
		Account account = new Account(null, null, 0);
		
		while (run) {
			System.out.println("-------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("-------------------------------------");
			System.out.print("선택> ");
			
			
			
			int selectNo = scanner.nextInt();
			
			if (selectNo == 1) {
				System.out.println("--------");
				System.out.println("계좌생성");
				System.out.println("--------");
				
	
				
				System.out.print("계좌번호: ");
				String ano = scanner.next();
				
				System.out.print("계좌주: ");
				String owner = scanner.next();
				
				System.out.print("초기입금금액: ");
				int balance = scanner.nextInt();
				
				accounts.put(ano, new Account(ano, owner, balance));
				
				System.out.println("결과: 계좌가 생성되었습니다.");
				
				System.out.println(account.getAno());
				System.out.println(account.getOwner());
				System.out.println(account.getBalance());
				System.out.println(accounts.size());
				
				continue;
			} else if (selectNo == 2) {
				
				System.out.println("--------");
				System.out.println("계좌목록");
				System.out.println("--------");
				
				for (String id : accounts.keySet()) {
					String ano = accounts.get(id).getAno();
					String owner = accounts.get(id).getOwner();
					int balance = accounts.get(id).getBalance();
					
					System.out.printf("%s %s %d%n", ano, owner, balance);
				}
				
				
			} else if (selectNo == 3) {
				
				System.out.println("--------");
				System.out.println("예금");
				System.out.println("--------");
				
				System.out.print("계좌번호: ");
				String ano = scanner.next();
				
				for (String id : accounts.keySet()) {
					account = accounts.get(ano);
				}
				
				System.out.print("예금액: ");
				account.setBalance(scanner.nextInt());
				System.out.println(account.getBalance());
				System.out.println("결과: 예금이 성공되었습니다.");
				
				
			} else if (selectNo == 4) {
				
				System.out.println("--------");
				System.out.println("출금");
				System.out.println("--------");
				
				System.out.print("계좌번호: ");
				String ano = scanner.next();
				
				for (String id : accounts.keySet()) {
					account = accounts.get(ano);
				}
				
				System.out.print("출금액: ");
				account.withdraw(scanner.nextInt());
				System.out.println(account.getBalance());
				
				System.out.println("결과: 출금이 성공되었습니다.");
				
			} else if (selectNo == 5) {
				System.out.println("프로그램 종료");
				run = false;
			} else if (selectNo > 5 || selectNo < 1) {
				System.out.println("1-5를 입력하세요");
			}
			
		}
		
	}









	
}

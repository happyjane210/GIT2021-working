package exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class BanckApplication {
	//Map �������� ������ Map ������ Ÿ�� (interface)
	// HashMap  ,  HashTable  ,  TreeMap
	// �����ϴ� �ڷᱸ���� ���� ���� �ż��带 ȣ���ϴ��� 
	// �������� ó������� �ٸ�
	
	// ���¸�� Map ��ü
	// Map<ŰŸ��, ��Ÿ��> ������ = new HashMap<ŰŸ��, ��Ÿ��>();
	
	//Account account = new Account();
	private static Map<String, Account> accounts = new HashMap<String, Account>();
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		boolean run = true;
		Account account = new Account(null, null, 0);
		
		while (run) {
			System.out.println("-------------------------------------");
			System.out.println("1.���»��� | 2.���¸�� | 3.���� | 4.��� | 5.����");
			System.out.println("-------------------------------------");
			System.out.print("����> ");
			
			
			
			int selectNo = scanner.nextInt();
			
			if (selectNo == 1) {
				System.out.println("--------");
				System.out.println("���»���");
				System.out.println("--------");
				
	
				
				System.out.print("���¹�ȣ: ");
				String ano = scanner.next();
				
				System.out.print("������: ");
				String owner = scanner.next();
				
				System.out.print("�ʱ��Աݱݾ�: ");
				int balance = scanner.nextInt();
				
				accounts.put(ano, new Account(ano, owner, balance));
				
				System.out.println("���: ���°� �����Ǿ����ϴ�.");
				
				System.out.println(account.getAno());
				System.out.println(account.getOwner());
				System.out.println(account.getBalance());
				System.out.println(accounts.size());
				
				continue;
			} else if (selectNo == 2) {
				
				System.out.println("--------");
				System.out.println("���¸��");
				System.out.println("--------");
				
				for (String id : accounts.keySet()) {
					String ano = accounts.get(id).getAno();
					String owner = accounts.get(id).getOwner();
					int balance = accounts.get(id).getBalance();
					
					System.out.printf("%s %s %d%n", ano, owner, balance);
				}
				
				
			} else if (selectNo == 3) {
				
				System.out.println("--------");
				System.out.println("����");
				System.out.println("--------");
				
				System.out.print("���¹�ȣ: ");
				String ano = scanner.next();
				
				for (String id : accounts.keySet()) {
					account = accounts.get(ano);
				}
				
				System.out.print("���ݾ�: ");
				account.setBalance(scanner.nextInt());
				System.out.println(account.getBalance());
				System.out.println("���: ������ �����Ǿ����ϴ�.");
				
				
			} else if (selectNo == 4) {
				
				System.out.println("--------");
				System.out.println("���");
				System.out.println("--------");
				
				System.out.print("���¹�ȣ: ");
				String ano = scanner.next();
				
				for (String id : accounts.keySet()) {
					account = accounts.get(ano);
				}
				
				System.out.print("��ݾ�: ");
				account.withdraw(scanner.nextInt());
				System.out.println(account.getBalance());
				
				System.out.println("���: ����� �����Ǿ����ϴ�.");
				
			} else if (selectNo == 5) {
				System.out.println("���α׷� ����");
				run = false;
			} else if (selectNo > 5 || selectNo < 1) {
				System.out.println("1-5�� �Է��ϼ���");
			}
			
		}
		
	}









	
}

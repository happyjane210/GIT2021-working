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
	
	private static Map<String, Account> accounts = new HashMap<String, Account>();
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("-------------------------------------");
			System.out.println("1.���»��� | 2.���¸�� | 3.���� | 4.��� | 5.����");
			System.out.println("-------------------------------------");
			System.out.println("����> ");
			
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
				System.out.println("1-5�� �Է��ϼ���");
			}
		}
		
		System.out.println("���α׷� ����");
	}
}

package polymorphism;

import override.Admin;
import override.Member;
import override.User;

// �θ�ü�� �ڽİ�ü�� �ְ� ������ �ż��带 ����Ҽ�����

public class UserExample2 {
	public static void main(String[] args) {
		// �Ϲݻ����
		User user = new User();
		user.setId("hong");
		user.setName("ȫ�浿");
		user.setPhone("010123345");
		user.printUserInfo();
		// prarm: User user <- User user
		sendMessage(user);
				
		// ������
		Admin admin = new Admin();
		// ��ӹ��� User�� �޼��� �� �ʵ带 �״�� ��밡����
		admin.setId("john");
		admin.setName("John Smith");
		admin.setPhone("0299872341");
		admin.printUserInfo();
		//�߰� �ʵ� �� �޼��� ���, �μ���ȣ
		admin.setDeptNo("10001");
		// param: User user <- Admin admin
		sendMessage(admin);
				
		// ����� ���
		Member member = new Member();
		// ��ӹ��� User�� �ż��� �� �ʵ带 �״�� ��밡����
		member.setId("kim");
		member.setName("������");
		member.setPhone("010979789");
		member.printUserInfo();
		// �߰� �ʵ� �� �޼��� ���, ����Ʈ
		member.setPoint(100000);
		member.printUserInfo();
		// param: User user <- Member member
		sendMessage(member);
	}
	
	//							�Ű������� Ŭ���� Ÿ��
	public static void sendMessage(User user) {
		// ������� ����ó �о �޼����b ����
		System.out.println(" ");
		System.out.println(user.getPhone() + ": �������� �޽����� �����ϴ�.");
	
		
	}
}
package casting;

// �θ�ü�� �ڽİ�ü�� �ְ� ������ �ż��带 ����Ҽ�����

public class UserExample {
	public static void main(String[] args) {
		// �ϳ��� ��ü�� �������� Ŭ������ �����ؼ� �����
		
		// �Ϲݻ����
		User user = new User();
		user.setId("hong");
		user.setName("ȫ�浿");
		user.setPhone("010123345");
		user.printUserInfo();
		
		// �θ� Ŭ���� ��ü�� �ڽ� Ŭ���� ��ü�� ������ �� ����
		// �߿��� ���� �θ� Ŭ������ �ʵ�, �޼��常 ��밡����.
		
		// ������
		user = new Admin();
		// ��ӹ��� User�� �޼��� �� �ʵ带 �״�� ��밡����
		user.setId("john");
		user.setName("John Smith");
		user.setPhone("0299872341");
		user.printUserInfo();
		//�߰� �ʵ� �� �޼��� ���, �μ���ȣ
		//user.setDeptNo("10001");
		Admin admin2 = (Admin) user; 
		
		// ����� ���
		user = new Member();
		// ��ӹ��� User�� �ż��� �� �ʵ带 �״�� ��밡����
		user.setId("kim");
		user.setName("������");
		user.setPhone("010979789");
		user.printUserInfo();   // �ڽİ�ü�� ������ �ż��尡 ȣ���
		// �߰� �ʵ� �� �޼��� ���, ����Ʈ
		//user.setPoint(100000);
		//user.printUserInfo();
		
		// ������ Ÿ�ӿ����� ������ �߻����� ������ �����ϸ� ������
		// Admin  <- Member
		// instanceof ������ ���� �ش�Ŭ������ ��ü�� �´��� Ȯ��
		// user��ü�� Admin Ÿ���� �ν��Ͻ����� Ȯ��
		// ������ 
		if(user instanceof Admin) {
			Admin admin3 = (Admin) user;
		}
		// ���Ŭ�������� �ֻ��� �θ� object Ŭ������
		// extends �� �Ⱦ��� ������ ���������� extends �Ǿ��ִ� ����
		Object object = new Object();
		obj = user;
		
		// r������ ���� ��ü ��ȯ�� �� ���� instance of �����
		if(obj instanceof Admin) {
			Admin admin4 = (Admin) obj;
			System.out.println(admin4);
		}
		
	}
}

package super_contructor;

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
		Admin admin = new Admin();
		// ��ӹ��� User�� �޼��� �� �ʵ带 �״�� ��밡����
		admin.setId("john");
		admin.setName("John Smith");
		admin.setPhone("0299872341");
		admin.printUserInfo();
		//�߰� �ʵ� �� �޼��� ���, �μ���ȣ
		//user.setDeptNo("10001");
		
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
	}
}

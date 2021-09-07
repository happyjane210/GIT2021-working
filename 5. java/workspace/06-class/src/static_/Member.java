package static_;

// Member Ŭ���� ����
public class Member {
	// static �ʵ�
	// Member Ŭ���� ��ü���� ��� ����� �� �ִ� ���� ��
	final static String SERVICE_NAME = "����� ����";   // ������
	static int memberCount = 0;  // ������
	
	String name;
	String id;
	String password;
	int age;
	// ������ int long double ���̾�
	
	// ������ ����
	// �̸��� id�� �ʱ�ȭ�ϴ� ������
	Member(String name, String id) {
		this.name = name;
		this.id = id;
		memberCount++;   // Member ��ü�� ������ �� ȸ������ ������Ŵ
	}
	
	// static �޼���
	// ��ü �������� ȣ���ؼ� ����� �� �ִ� �޼���
	static void printServiceName() {
		// static �������� this ���Ұ�
		// this�� ������ ��ü�� ����Ŵ
		// �Ұ� System.out.println(this.serviceName);
		
		// static ������ ��ü������ �ƴ� Ŭ���� ����(�ż��� ����)�� ������
		System.out.println(SERVICE_NAME);
	}
	
	// �ڹٿ��� �Լ��� ����� Ǯ� �����
	// static �޼��忡�� non-static �ʵ带 ���� �� ���� -> this.name X
	// �Ű������� ���� �޾Ƽ� ó���ؾ���
	static void printNameWithServiceName(String name) {
		System.out.println(SERVICE_NAME + ": " + name);
	}
}

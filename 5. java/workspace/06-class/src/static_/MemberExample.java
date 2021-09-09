package static_;

public class MemberExample {
	public static void main(String[] args) {
		
		// new ��ü �ʿ���� �ٷ� ȣ���ؼ� ��������
		// class.method(...)
		System.out.println(Member.SERVICE_NAME);
		Member.printServiceName();
		
		
		
		// �̸�, id�� �Ű������� �޾Ƽ� ��ü ����
		// �ش��ϴ� �����ڸ� ����
		Member m1 = new Member("ȫ�浿", "hong");
		Member m2 = new Member("���ڹ�", "java");
	
		// static ������ �����ϴ� ����� = Ŭ������.������
		System.out.println(Member.SERVICE_NAME + ", �̸�: " + m1.name + ", id: " + m1.id);
		System.out.println(Member.SERVICE_NAME + ", �̸�: " + m2.name + ", id: " + m2.id);
		
		
		
		Member.printNameWithServiceName(m1.name);
		Member.printNameWithServiceName(m2.name);
		
		System.out.println("ȸ�ͼ�: " + Member.memberCount);
		
		
		
		singleton.Calculator calc = singleton.Calculator.getInstance();
		System.out.println(calc.getAreaCircle(25));
	}
}

package exercise;

public class MemberExample {
	public static void main(String[] args) {
		// �̸�, id�� �Ű������� �޾Ƽ� ��ü ����
		// �ش��ϴ� �����ڸ� ����
		Member m1 = new Member("ȫ�浿", "hong");
		Member m2 = new Member("���ڹ�", "java");
	
		System.out.println(m1.name + " " + m1.id);
		System.out.println(m2.name + " " + m2.id);
	}
}

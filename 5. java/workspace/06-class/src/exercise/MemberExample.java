package exercise;

//Ŭ������ ����Ʈ
//import constructor.Student;

public class MemberExample {
	public static void main(String[] args) {
		// �̸�, id�� �Ű������� �޾Ƽ� ��ü ����
		// �ش��ϴ� �����ڸ� ����
		Member m1 = new Member("ȫ�浿", "hong");
		Member m2 = new Member("���ڹ�", "java");
		
		// ��Ű�� ��, 2 Ŭ������ ���
		//constructor.Student student = new constructor.Student();
		
		public Stirng getName() {
			return this.name;
		}
		
		public void setAge(int age) {
			this.age;
		}
		
		System.out.println(m1.getName());
	
		System.out.println(m1.name + " " + m1.id);
		System.out.println(m2.name + " " + m2.id);
	}
}

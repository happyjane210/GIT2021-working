package exercise;

//1. Ŭ������ ����Ʈ - �ٸ���Ű���� ���� Ŭ�������� ���� Ŭ������ ����Ʈ �Ұ�
import constructor.sub.Student;

public class MemberExample {
	public static void main(String[] args) {
		// �̸�, id�� �Ű������� �޾Ƽ� ��ü ����
		// �ش��ϴ� �����ڸ� ����
		Member m1 = new Member("ȫ�浿", "hondsfg");
		Member m2 = new Member("���ڹ�", "java");
		Member m3 = new Member("hong", "hang123");
		m1.setAge(20);    // �̷��� ��°� �����ų������
		m1.setAbcd("ABCD");
		m1.setPassword("PASSWORD");
	
		
		
		// ��Ű�� ��, 2 Ŭ������ ���
		//constructor.Student student = new constructor.Student();
		Student student = new Student();
		System.out.println(student.age);
		
		// 2. Ŭ������ ��� -  ��Ű������� �ۼ�
		constructor.Student student2 = new constructor.Student();
		
		System.out.println(m1.getName() + ", " + m1.getId() + ", " + m1.getAge() + ", " +  m1.getAbcd()  + ", " + m1.getPassword() + ", " + m1.getClass() );
		System.out.println(m2.getName() + ", " + m2.getId() + ", " + m2.getClass() );
		System.out.println(m3.getName() + ", " + m3.getId() + ", " + m3.getClass() );

	}
}

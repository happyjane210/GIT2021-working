package constructor;

public class StudentExample {
	public static void main(String[] args) {
		
	
		
		// new Ŭ������()
		// Ŭ������() -> ������(Constructor)
		// Student()
		
		// �����ڴ� Ŭ������� ������ �̸��� �ż���(�Լ�)
		// �ַ� ��ü�� ������ �� �ʱ�ȭ�� ó����
		
		// Ŭ������(  ) -> �Ű������� ���� ������
		// �⺻������(default constructor)
		// �⺻�����ڴ� �������� �ʾƵ� Ŭ������ �����.
		
		// new ������ �ż���
		// ������ �ż��带 �����Ͽ� ��ü�� ������
		
		// Student() ��� ������ �ż��带 �����ؼ�
		// student Ŭ���� ������ ���ο� (new) ��ü�� �����
		// student Ŭ���� ������ s1 ������ �����ض�
		
		// �ڹٿ����� ���� ���� �ʴ� ���
		// ��ü�� �����ϰ� �ʵ忡 ���� ����
		Student s1 = new Student();   // student ��ü�� �������
		s1.name = "ghdrl";
		s1.age = 20;
		s1.semester = 2;
		s1.major = "�濵��";
		
		// �ڹٿ��� ��ü�� �����ϴ� �Ѱ��� ���
		// �����ڷ� �ʵ带 �ʱ�ȭ�Ͽ� ����
		Student s2 = new Student("smith", 30);   // student ��ü�� �������
		System.out.println(s2.name + " " + s2.age);
		
		// �ٸ� ������� ��ü ����
		Student s3 = new Student("������", 26, 3, "�ڹ���");   // student ��ü�� �������
		System.out.println(s3.name + " " + s3.age + " " + s3.semester + " " + s3.major);

	}
}

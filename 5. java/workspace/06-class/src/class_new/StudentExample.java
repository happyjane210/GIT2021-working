package class_new;

public class StudentExample {
	public static void main(String[] args) {
		// new Ŭ������ ()
		// : �ν��Ͻ�(instance)�� ����: instantiation  -> Ŭ���� ������ (��ü)�޸� ������ ����
		// �ν��Ͻ� == ��ü(object)  �ν��Ͻ��� ��ü�� ����
		// Student s1 = new Student(); 
		
		// Student s1 = ������ �ν��Ͻ�
		// Ŭ������ �ν��Ͻ����� = new Ŭ������()
		
		// ȫ�浿�̶�� �л�����
		Student s1 = new Student();  // �ʵ�: ȫ�浿 ������ , �ʵ忡 ����: �ν��Ͻ�������.�ʵ��
		s1.name = "ȫ�浿";
		s1.age = 20;
		s1.semester = 2;
		s1.major = "�İ�";
		
		System.out.println(s1.name + " " + s1.age);
		//�ż���: ȫ�浿 �л� ������û ��� , �޼��� ����: �ν��Ͻ�������.�ż����
		s1.joinCourse();
		
		//---------------------------------
		
		// ���̽� �л� ����
		Student s2 = new Student();  // �ʵ�: ���̽� ������
		s2.name = "Smith";
		s2.age = 21;
		s2.semester = 3;
		s2.major = "�濵��";
		
		System.out.println(s2.name + " " + s2.age);
		//�ż���: ���̽� �л� ������û ���
		s2.joinCourse();
		
	}
}

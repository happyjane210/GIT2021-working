package constructor;


public class Student {
	

	String name;    //�̸�
	int age;        // ����
	int semester;
	String major;
	
	// *** �����ε� (overloading) :  ��ü�� �ż��尡 �پ��� ���¸� ������ ��
	// �ż��� �̸��� �����ϰ� �Ű������� ����, Ÿ��, ������ �ٸ� �޼��带 �����ϴ� ��
	// �ż��� ��ũ��ó (method signature)
	// : �ż��� �̸� +  �Ű�����
	
	// => ��ü���� ���α׷����� ������ ������ ����
	// ������: �پ��� ���¸� ������.

	
	
	// �Ű������� ���� �⺻ �����ڴ� Ŭ������ �����
	//������ (Constructor)
	// ��ü ������ �ʱ�ȭ ���� ���
	public Student() {
		// �ƹ��͵� ó������
		// ��ü ������ ��
	}
	
	// �����ڸ� ���� �����, �⺻�����ڴ� ���ŵ�, �� �����ڰ� �־����
	// �̸��� ���̸� �Ű������� �޾Ƽ�
	// ��ü(�ν��Ͻ�)�� �����ϴ� ������ �ż���
	Student(String name, int age) {
		this.name = name;
		this.age = age;
		
	}
	// this: ������� ��ü �ʵ忡 ����
	
	Student(String name, String major) {
		
	};
	
	Student(int age, String name) {
		
	}
	
	// �̸�, ����, �б�, �а� �ް� �ʵ� �ʱ�ȭ �� ��ü ����
	Student(String name, int age, int semester, String major) {
		this.name = name;
		this.age = age;
		this.semester = semester;
		this.major = major;
	};
	
	void joinCourse() {
		
	}
}

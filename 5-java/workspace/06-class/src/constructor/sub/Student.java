package constructor.sub;

public class Student {
	
	// default ����������
	// �ܺ���Ű�������� ���Ұ�
	String name;
	
	// public ����������
	// ��� ��Ű���� Ŭ�������� ��밡��
	public int age;
	
	// protected ����������
	// �ܺ� ��Ű������ ���Ұ��ε� �� Ŭ������ ��ӹ޾Ƽ�(exteds) ����ϸ� ��밡��
	protected int semester;
	String major;
	
	// �����ε�(Overloading)
	// �ż��� �ñ״�ó (method signature)
	// : �ż��� �̸� + �Ű�����
	// �ż��� �̸��� �����ϰ� �Ű������� ����, Ÿ��, ������ �ٸ� �޼��带 �����ϴ� ��
	
	// ��ü���� ���α׷����� ������ ������ ����
	// ������(polymorphism) = �پ��� ���¸� ������.
	// �ż��� �����ε�
	// = ��ü�� �ż��尡 �پ��� ���¸� ������ ��
	
	// �Ű������� ���� �⺻ �����ڴ� Ŭ������ �����
	// �ٸ� �����ڸ� �������� ���� ������ �����ؾ� ��
	public Student () {
		// �ƹ��͵� ó������
		// ��ü ������ ��
	}
	
	
	// �����ڸ� ���Ƿ� �����, �⺻ �����ڴ� ���ŵ�
	// �̸��� ���̸� �Ű������� �޾Ƽ�
	// ��ü(�ν��Ͻ�)�� �����ϴ� ������ �޼���
	Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	Student(String name, String major) {
		
	}
	
	Student(int age, String name) {
		
	}
	
	// �̸�, ����, �б�, �а� �ް� �ʵ� �ʱ�ȭ �� ��ü ����
	Student(String name, int age, int semester, String major) {
		this.name = name;
		this.age = age;
		this.semester = semester;
		this.major = major;
	}
	
	protected void joinCourse() {
		
	}
	
}

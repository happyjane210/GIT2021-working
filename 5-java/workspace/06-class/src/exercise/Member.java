package exercise;

import constructor.sub.Student;

// Member Ŭ���� ����
// exdends Ȯ��
// Member �ڽ� , extends Student �θ�
public class Member extends Student {
	
	// �ڹٿ��� �⺻������ �ʵ�� private ���������ڷ� ����
	// �ڹٿ��� �ʵ带 public, protected ������ �� ��
	// getter, setter�� �ܺο� ������ �ʵ带 property��� ��
	private String name;
	private String id;
	private String password;
	private int age;
	private String abcd;
	
	// �ʵ带 �����ϰ� ���ִ� �޼��带 �ۼ�

	// get�ʵ��: �ʵ��� ���� �������� �޼���
	// Getter
	// public �ʵ�Ÿ�� get�ʵ��() {
	// return this.�ʵ��;
	// }
	public String getName() {
		//return this.name;
		return "[" + this.name + "]";
	}
	
	
	// set�ʵ��: �ʵ��� ���� �����ϴ� �޼���
	// Setter
	// public void set�ʵ��(�ʵ�Ÿ�� ������) {
	// this.�ʵ�� = ������;
	// }
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	


	


	// �����ڸ� ����
	// �̸��� id�� �ʱ�ȭ�ϴ� ������
	Member(String name, String id) {
		this.name = name;
		this.id = id;
		// ��ӹ޾Ƽ� ��밡��
		this.semester = 1;   // protected�� ������ �ʵ�
		this.joinCourse();  //proetected�� ������ �޼���
	}
	





	public String getId() {
		return id;
	}
	
	public void setId(String id ) {
		this.id = id;
	}
	
	
	
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	public String getAbcd() {
		return abcd;
	}
	
	public void setAbcd(String abcd) {
		this.abcd = abcd;
	}
	
	

	

	



}
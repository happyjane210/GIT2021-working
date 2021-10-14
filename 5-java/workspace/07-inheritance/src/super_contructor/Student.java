package super_contructor;

public class Student extends Person {

	private int studentNo;
	
	public Student(String name, String phone) {
		super(name, phone);
		this.studentNo = studentNo;
	}
	
	// �⺻ �����ڰ� ����������
	// public Student() {
	// super(); ����... �����߻� -> ��������� �����ڸ� ��������
	// }
	
	public int getStudentNo() {
		return studentNo;
	}
	
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}
	
}

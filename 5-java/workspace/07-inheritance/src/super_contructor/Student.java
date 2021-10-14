package super_contructor;

public class Student extends Person {

	private int studentNo;
	
	public Student(String name, String phone) {
		super(name, phone);
		this.studentNo = studentNo;
	}
	
	// 기본 생성자가 내부적으로
	// public Student() {
	// super(); 못함... 에러발생 -> 명시적으로 생성자를 만들어야함
	// }
	
	public int getStudentNo() {
		return studentNo;
	}
	
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}
	
}

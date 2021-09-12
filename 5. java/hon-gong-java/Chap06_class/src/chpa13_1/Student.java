package chpa13_1;

public class Student {
	// filed
	public int sno;
	public String name;
	
	// constructor 생성자
	public Student(int sno, String name) {
		this.sno = sno;
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		// 학생 객체 인지 조회
		if(obj instanceof Student) {
			// Student 객체라면 Student로 타입 변환
			Student student = (Student) obj;
			return sno == student.sno && name.equals(student.name);
		} else {
			return false;
		}
	}
}

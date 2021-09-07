package class_new;

// 클래스 이름은 Pascal case
// 

public class Student {
	
	// 필드(field) 2개 선언
	// 클래스 내부 변수
	// 객체 관점에서는 데이터나 값이 저장되는 곳
	String name;    //이름
	int age;        // 나이
	int semester;
	String major;
	
	//생성자 (Constructor)
	// 객체 생성시 초기화 역할 담당
	Student() {
		//초기화
	}
	
	// 매서드(Method) 
	// 객체의 기능에 해당하는 함수
	// 클래스 내부에 있으면 메서드, 그냥 함수만 있으면 함수
	void joinCourse() {
		// 수강신청 처리
	}
}

package constructor;


public class Student {
	

	String name;    //이름
	int age;        // 나이
	int semester;
	String major;
	
	// *** 오버로딩 (overloading) :  객체의 매서드가 다양한 형태를 가지는 것
	// 매서드 이름은 동일하고 매개변수의 개수, 타입, 순서가 다른 메서드를 선언하는 것
	// 매서드 시크니처 (method signature)
	// : 매서드 이름 +  매개변수
	
	// => 객체지향 프로그래밍의 다형성 원리를 적용
	// 다형성: 다양한 형태를 가진다.

	
	
	// 매개변수가 없는 기본 생성자는 클래스에 내장됨
	//생성자 (Constructor)
	// 객체 생성시 초기화 역할 담당
	public Student() {
		// 아무것도 처리안함
		// 객체 생성만 함
	}
	
	// 생성자를 새로 만들면, 기본생성자는 제거됨, 빈 생성자가 있어야함
	// 이름과 나이를 매개변수로 받아서
	// 객체(인스턴스)를 생성하는 생성자 매서드
	Student(String name, int age) {
		this.name = name;
		this.age = age;
		
	}
	// this: 만들어질 객체 필드에 접근
	
	Student(String name, String major) {
		
	};
	
	Student(int age, String name) {
		
	}
	
	// 이름, 나이, 학기, 학과 받고 필드 초기화 및 객체 생성
	Student(String name, int age, int semester, String major) {
		this.name = name;
		this.age = age;
		this.semester = semester;
		this.major = major;
	};
	
	void joinCourse() {
		
	}
}

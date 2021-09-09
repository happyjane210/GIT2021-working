package constructor.sub;

public class Student {
	
	// default 접근제한자
	// 외부패키지에서는 사용불가
	String name;
	
	// public 접근제한자
	// 모든 패키지의 클래스에서 사용가능
	public int age;
	
	// protected 접근제한자
	// 외부 패키지에서 사용불가인데 이 클래스를 상속받아서(exteds) 사용하면 사용가능
	protected int semester;
	String major;
	
	// 오버로딩(Overloading)
	// 매서드 시그니처 (method signature)
	// : 매서드 이름 + 매개변수
	// 매서드 이름은 동일하고 매개변수의 개수, 타입, 순서가 다른 메서드를 선언하는 것
	
	// 객체지향 프로그래밍의 다형성 원리를 적용
	// 다형성(polymorphism) = 다양한 형태를 가진다.
	// 매서드 오버로딩
	// = 객체의 매서드가 다양한 형태를 가지는 것
	
	// 매개변수가 없는 기본 생성자는 클래스에 내장됨
	// 다른 생성자를 선언했을 때는 별도로 선언해야 함
	public Student () {
		// 아무것도 처리안함
		// 객체 생성만 함
	}
	
	
	// 생성자를 임의로 만들면, 기본 생성자는 제거됨
	// 이름과 나이를 매개변수르 받아서
	// 객체(인스턴스)를 생성하는 생성자 메서드
	Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	Student(String name, String major) {
		
	}
	
	Student(int age, String name) {
		
	}
	
	// 이름, 나이, 학기, 학과 받고 필드 초기화 및 객체 생서
	Student(String name, int age, int semester, String major) {
		this.name = name;
		this.age = age;
		this.semester = semester;
		this.major = major;
	}
	
	protected void joinCourse() {
		
	}
	
}

package class_new;

public class StudentExample {
	public static void main(String[] args) {
		// new 클래스명 ()
		// : 인스턴스(instance)를 생성: instantiation  -> 클래스 구조의 (객체)메모리 공간을 생성
		// 인스턴스 == 객체(object)  인스턴스는 객체와 같다
		// Student s1 = new Student(); 
		
		// Student s1 = 생성된 인스턴스
		// 클래스명 인스턴스변수 = new 클래스명()
		
		// 홍길동이라는 학생정보
		Student s1 = new Student();  // 필드: 홍길동 데이터 , 필드에 접근: 인스턴스변수명.필드명
		s1.name = "홍길동";
		s1.age = 20;
		s1.semester = 2;
		s1.major = "컴공";
		
		System.out.println(s1.name + " " + s1.age);
		//매서드: 홍길동 학생 수강신청 기능 , 메서드 접근: 인스턴스변수명.매서드명
		s1.joinCourse();
		
		//---------------------------------
		
		// 스미스 학생 정보
		Student s2 = new Student();  // 필드: 스미스 데이터
		s2.name = "Smith";
		s2.age = 21;
		s2.semester = 3;
		s2.major = "경영학";
		
		System.out.println(s2.name + " " + s2.age);
		//매서드: 스미스 학생 수강신청 기능
		s2.joinCourse();
		
	}
}

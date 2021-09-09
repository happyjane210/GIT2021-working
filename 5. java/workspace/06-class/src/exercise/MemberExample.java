package exercise;

//1. 클래스를 임포트 - 다른패키지의 같은 클래스명을 가진 클래스는 임포트 불가
import constructor.sub.Student;

public class MemberExample {
	public static void main(String[] args) {
		// 이름, id를 매개변수로 받아서 객체 생성
		// 해당하는 생성자를 선언
		Member m1 = new Member("홍길동", "hondsfg");
		Member m2 = new Member("김자바", "java");
		Member m3 = new Member("hong", "hang123");
		m1.setAge(20);    // 이렇게 출력값 변경시킬수있음
		m1.setAbcd("ABCD");
		m1.setPassword("PASSWORD");
	
		
		
		// 패키지 명, 2 클래스를 사용
		//constructor.Student student = new constructor.Student();
		Student student = new Student();
		System.out.println(student.age);
		
		// 2. 클래스를 사용 -  패키지명까지 작성
		constructor.Student student2 = new constructor.Student();
		
		System.out.println(m1.getName() + ", " + m1.getId() + ", " + m1.getAge() + ", " +  m1.getAbcd()  + ", " + m1.getPassword() + ", " + m1.getClass() );
		System.out.println(m2.getName() + ", " + m2.getId() + ", " + m2.getClass() );
		System.out.println(m3.getName() + ", " + m3.getId() + ", " + m3.getClass() );

	}
}

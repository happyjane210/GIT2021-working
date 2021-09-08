package exercise;

//클래스를 입포트
//import constructor.Student;

public class MemberExample {
	public static void main(String[] args) {
		// 이름, id를 매개변수로 받아서 객체 생성
		// 해당하는 생성자를 선언
		Member m1 = new Member("홍길동", "hong");
		Member m2 = new Member("김자바", "java");
		
		// 패키지 명, 2 클래스를 사용
		//constructor.Student student = new constructor.Student();
		
		public Stirng getName() {
			return this.name;
		}
		
		public void setAge(int age) {
			this.age;
		}
		
		System.out.println(m1.getName());
	
		System.out.println(m1.name + " " + m1.id);
		System.out.println(m2.name + " " + m2.id);
	}
}

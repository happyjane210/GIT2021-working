package exercise;

public class MemberExample {
	public static void main(String[] args) {
		// 이름, id를 매개변수로 받아서 객체 생성
		// 해당하는 생성자를 선언
		Member m1 = new Member("홍길동", "hong");
		Member m2 = new Member("김자바", "java");
	
		System.out.println(m1.name + " " + m1.id);
		System.out.println(m2.name + " " + m2.id);
	}
}

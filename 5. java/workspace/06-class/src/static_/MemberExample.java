package static_;

public class MemberExample {
	public static void main(String[] args) {
		
		// new 객체 필요없이 바로 호출해서 쓸수있음
		// class.method(...)
		System.out.println(Member.SERVICE_NAME);
		Member.printServiceName();
		
		
		
		// 이름, id를 매개변수로 받아서 객체 생성
		// 해당하는 생성자를 선언
		Member m1 = new Member("홍길동", "hong");
		Member m2 = new Member("김자바", "java");
	
		// static 변수에 접근하는 방법은 = 클래스명.변수명
		System.out.println(Member.SERVICE_NAME + ", 이름: " + m1.name + ", id: " + m1.id);
		System.out.println(Member.SERVICE_NAME + ", 이름: " + m2.name + ", id: " + m2.id);
		
		
		
		Member.printNameWithServiceName(m1.name);
		Member.printNameWithServiceName(m2.name);
		
		System.out.println("회훤수: " + Member.memberCount);
		
		
		
		singleton.Calculator calc = singleton.Calculator.getInstance();
		System.out.println(calc.getAreaCircle(25));
	}
}

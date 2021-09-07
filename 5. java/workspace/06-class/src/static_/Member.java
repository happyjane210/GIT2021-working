package static_;

// Member 클래스 선언
public class Member {
	// static 필드
	// Member 클래스 객체에서 모두 사용할 수 있는 변수 값
	final static String SERVICE_NAME = "배달의 민족";   // 고정값
	static int memberCount = 0;  // 변동값
	
	String name;
	String id;
	String password;
	int age;
	// 숫자형 int long double 많이씀
	
	// 생성자 선언
	// 이름과 id를 초기화하는 생성자
	Member(String name, String id) {
		this.name = name;
		this.id = id;
		memberCount++;   // Member 객체를 생성할 때 회원수를 증가시킴
	}
	
	// static 메서드
	// 객체 생성없이 호출해서 사용할 수 있는 메서드
	static void printServiceName() {
		// static 변수에는 this 사용불가
		// this는 생성된 객체를 가르킴
		// 불가 System.out.println(this.serviceName);
		
		// static 변수는 객체공간이 아닌 클래스 공간(매서드 공간)에 생성됨
		System.out.println(SERVICE_NAME);
	}
	
	// 자바에선 함수명 길더라도 풀어서 써야함
	// static 메서드에는 non-static 필드를 읽을 수 없음 -> this.name X
	// 매개변수로 값을 받아서 처리해야함
	static void printNameWithServiceName(String name) {
		System.out.println(SERVICE_NAME + ": " + name);
	}
}

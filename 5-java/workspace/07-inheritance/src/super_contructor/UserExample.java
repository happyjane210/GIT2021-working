package super_contructor;

public class UserExample {
	public static void main(String[] args) {
		// 하나의 객체에 여러개의 클래스를 대입해서 사용함
		
		// 일반사용자
		User user = new User();
		user.setId("hong");
		user.setName("홍길동");
		user.setPhone("010123345");
		user.printUserInfo();
		
		// 부모 클래스 객체에 자식 클래스 객체를 대입할 수 있음
		// 중요한 것은 부모 클래스의 필드, 메서드만 사용가능함.
		
		// 관리자
		Admin admin = new Admin();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		admin.setId("john");
		admin.setName("John Smith");
		admin.setPhone("0299872341");
		admin.printUserInfo();
		//추가 필드 및 메서드 사용, 부서번호
		//user.setDeptNo("10001");
		
		// 멤버십 멤버
		Member member = new Member();
		// 상속받은 User의 매서드 및 필드를 그대로 사용가능함
		member.setId("kim");
		member.setName("김쿠팡");
		member.setPhone("010979789");
		member.printUserInfo();
		// 추가 필드 및 메서드 사용, 포인트
		member.setPoint(100000);
		member.printUserInfo();
	}
}

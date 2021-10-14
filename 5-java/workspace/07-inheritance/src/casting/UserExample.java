package casting;

// 부모객체에 자식객체를 넣고 지정된 매서드를 사용할수있음

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
		user = new Admin();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		user.setId("john");
		user.setName("John Smith");
		user.setPhone("0299872341");
		user.printUserInfo();
		//추가 필드 및 메서드 사용, 부서번호
		//user.setDeptNo("10001");
		Admin admin2 = (Admin) user; 
		
		// 멤버십 멤버
		user = new Member();
		// 상속받은 User의 매서드 및 필드를 그대로 사용가능함
		user.setId("kim");
		user.setName("김쿠팡");
		user.setPhone("010979789");
		user.printUserInfo();   // 자식객체의 재정의 매서드가 호출됨
		// 추가 필드 및 메서드 사용, 포인트
		//user.setPoint(100000);
		//user.printUserInfo();
		
		// 컴파일 타임에서는 오류가 발생하지 않지만 실행하면 오류남
		// Admin  <- Member
		// instanceof 연산을 통해 해당클래스와 객체가 맞는지 확인
		// user객체가 Admin 타입의 인스턴스인지 확인
		// 맞으면 
		if(user instanceof Admin) {
			Admin admin3 = (Admin) user;
		}
		// 모든클래스들의 최상위 부모가 object 클래스임
		// extends 를 안쓰고 있지만 내부적으로 extends 되어있는 상태
		Object object = new Object();
		obj = user;
		
		// r강제로 형식 객체 변환을 할 때는 instance of 써야함
		if(obj instanceof Admin) {
			Admin admin4 = (Admin) obj;
			System.out.println(admin4);
		}
		
	}
}

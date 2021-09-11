package polymorphism;

import override.Admin;
import override.Member;
import override.User;

// 부모객체에 자식객체를 넣고 지정된 매서드를 사용할수있음

public class UserExample2 {
	public static void main(String[] args) {
		// 일반사용자
		User user = new User();
		user.setId("hong");
		user.setName("홍길동");
		user.setPhone("010123345");
		user.printUserInfo();
		// prarm: User user <- User user
		sendMessage(user);
				
		// 관리자
		Admin admin = new Admin();
		// 상속받은 User의 메서드 및 필드를 그대로 사용가능함
		admin.setId("john");
		admin.setName("John Smith");
		admin.setPhone("0299872341");
		admin.printUserInfo();
		//추가 필드 및 메서드 사용, 부서번호
		admin.setDeptNo("10001");
		// param: User user <- Admin admin
		sendMessage(admin);
				
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
		// param: User user <- Member member
		sendMessage(member);
	}
	
	//							매개변수가 클래스 타입
	public static void sendMessage(User user) {
		// 사용자의 연락처 읽어서 메세지륿 보냄
		System.out.println(" ");
		System.out.println(user.getPhone() + ": 공지사항 메시지를 보냅니다.");
	
		
	}
}

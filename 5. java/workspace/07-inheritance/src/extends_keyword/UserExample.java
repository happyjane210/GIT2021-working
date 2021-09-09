package extends_keyword;

public class UserExample {
	public static void main(String[] args) {
		// 일반사용자
		User user = new User();
		user.setId("hong");
		user.setName("smith");
		user.setPhone("020123452");
		
		// 관리자
		Admin admin = new Admin();
		admin.setiId("john");
		admin.setName("John");
		admin.setPhone("0299871");
		admin.prinUserInfo();
		//우가 필드 및 메서드 사용, 부서번호
		user.
	}
}

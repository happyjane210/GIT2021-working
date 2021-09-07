package exercise;

public class MemberService {
	// login(member)
	boolean login(Member member) {
		if(member.id == "hong" && member.password == "12345") {
			return true;
		}
		return false;
	}
	
	// 오버로딩 : 매서드 이름은 동일하고 매개변수의 타입, 개수, 순서가 달라야함
	// login(String, String)
	boolean login(String id, String password) {
		return id == "hong" && password == "12345" ? true : false;   //위 if문이랑 같음
	}
	
	void logout (String id) {
		System.out.println("로그아웃 되었습니다.");
	}
}

package exercise;

public class MemberService {
	
	// login(member)
	// Member 객체를 메서드 파라미터로 받음
	// Dependency: 의존성이 있음
	// Dependency<<Usage>>: 구현이나 기능처리에 다른 클래스가 필요함
	public boolean login(Member member) {
		if(member.getId() == "hong" && member.getPassword() == "12345") {
			return true;
		}
		return false;
	}
	
	// 오버로딩 : 매서드 이름은 동일하고 매개변수의 타입, 개수, 순서가 달라야함
	// login(String, String)
	public boolean login(String id, String password) {
		return id == "hong" && password == "12345" ? true : false;   //위 if문이랑 같음
	}
	
	public void logout (String id) {
		System.out.println("로그아웃 되었습니다.");
	}
}

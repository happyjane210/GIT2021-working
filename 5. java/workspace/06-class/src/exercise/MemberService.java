package exercise;

public class MemberService {
	// login(member)
	boolean login(Member member) {
		if(member.id == "hong" && member.password == "12345") {
			return true;
		}
		return false;
	}
	
	// �����ε� : �ż��� �̸��� �����ϰ� �Ű������� Ÿ��, ����, ������ �޶����
	// login(String, String)
	boolean login(String id, String password) {
		return id == "hong" && password == "12345" ? true : false;   //�� if���̶� ����
	}
	
	void logout (String id) {
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
	}
}

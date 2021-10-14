package exercise;

public class MemberService {
	
	// login(member)
	// Member ��ü�� �޼��� �Ķ���ͷ� ����
	// Dependency: �������� ����
	// Dependency<<Usage>>: �����̳� ���ó���� �ٸ� Ŭ������ �ʿ���
	public boolean login(Member member) {
		if(member.getId() == "hong" && member.getPassword() == "12345") {
			return true;
		}
		return false;
	}
	
	// �����ε� : �ż��� �̸��� �����ϰ� �Ű������� Ÿ��, ����, ������ �޶����
	// login(String, String)
	public boolean login(String id, String password) {
		return id == "hong" && password == "12345" ? true : false;   //�� if���̶� ����
	}
	
	public void logout (String id) {
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
	}
}

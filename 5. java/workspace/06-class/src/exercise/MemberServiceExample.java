package exercise;

public class MemberServiceExample {
	public static void main(String[] args) {
		
		MemberService memberService = new MemberService();
		
		// �α��� ���� ���̽�
		// �׽�Ʈ ������ ����
		Member member = new Member("ȫ�浿", "hong");
		member.password = "12345";
		
		// �α��� �׽�Ʈ ���̽� ����
		boolean result = memberService.login(member);
		
		if (result) {
			System.out.println("�α��� �Ǿ����ϴ�");
			// �α׾ƿ� �׽�Ʈ ���̽� ����
			memberService.logout("hong");
		} else {
			System.out.println("id �Ǵ� password�� ��ġ���� �ʽ��ϴ�.");
		}
	}
}

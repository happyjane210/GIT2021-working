package map;

import java.util.HashMap;
import java.util.Map;

public class MemberExample2 {

	public static void main(String[] args) {

		// string �� �״�� ������ ���ڷ� ������ Integer�� ����� int �ȵ�
		// Integer int �� ���� ��üŸ�� (-> hashcode, equals �ż��尡 ����) ���������δ� ���ڰ� �񱳶� �Ȱ���
		
		// Generic ���ʸ�:  Map<ŰŸ��, ��Ÿ��>
		Map<Integer, Member> members = new HashMap<Integer, Member>();
		
		// Map<Member, Contact>
		// Member: id, name, age
		// contact: home, company, mobile

//		String name = "hong";
//		System.out.println(name.hashCode());
	}

}
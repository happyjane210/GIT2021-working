package map;

import java.util.HashMap;
import java.util.Map;

public class MemberExample {
	public static void main(String[] args) {
		// Map�� Ű�� �ߺ��� ����
		Map<String, Member> members = new HashMap<String, Member>();
		
		// 1. ��ü �߰�
		Member hong = new Member("hong", "password123", "hong", 30);
		members.put("hong", hong);
		
		Member smith = new Member("john", "abcd1234", "john smith", 30);
		members.put("john", smith);
		
		// ����Ű�� �� ��ü�� ������ �� ��ü�� ���� ������(������ �Ͼ)
		Member smith2 = new Member("john123", "asd234", "john", 25);
		members.put("john", smith2);
		
		
		//2. �ɿ��� ������ ���� ��ȸ
		// �ʺ���.get Ű��
		Member m = members.get("hong");
		System.out.println(m.getName() + ", " + m.getAge());
		
		// 2-1. �� ��� ��ȸ   key�� ����� Ű�� ���·� ��ȯ
		// �ɺ���.keySet() -> ���� key����� Set ������ �ڷᱸ���� ��ȯ
		// set: �ߺ����� ���� �ڷᱸ��
		for(String id : members.keySet()) {
			String name = members.get(id).getName();
			int age = members.get(id).getAge();
			
			System.out.println(id + ": " + name + ": " + age);
		}
	}
}

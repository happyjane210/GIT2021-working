package chap6_3.exercise;

public class Member {
	String name;
	String id;


	Member(String name, String id) {
		
		System.out.println(this.name);
		System.out.println(this.id);
	}
}

class MemberExample {
	Member user1 = new Member("ȫ�浿", "hong");
}

package exercise;

import constructor.Student;

// Member 클래스 선언
public class Member extends Student {
	
	private String name;
	private String id;
	private String password;
	private int age;
	// 숫자형 int long double 많이씀
	
	public String getName() {
		return this.name;
	}
	
	public String getName1() {
		return this.id;
	}
	
	public String getName2() {
		return this.password;
	}
	
	
	
	Member(String name, String id, int semester) {
		this.name = name;
		this.id = id;
		this.semester = 1;
	}
}

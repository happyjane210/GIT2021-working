package chap6_3;

public class Korean {
	// filed
	String nation = "Korean";
	String name;
	String ssn;
	
	// Constructor  생성자 선언
	Korean(String name, String ssn) {
		this.name = name;   // 내가 나라고 하듯이
		this.ssn = ssn;
		//객체.필드 = 매개변수
	}
}

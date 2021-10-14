package chap6_3;

public class ThisBook {
	//filed
	String sort = "book";
	String title;
	String color;
	int index;
	
	// constructor  생성자
	ThisBook() {
	}
	
	ThisBook(String title) {	// ThisBook()여기서 선언한 변수만 변수명 쓰고,
		this(title, "grey", 3);	// 선언 안하면 값으로 써줘야함, String: 문자열 int: 정수 
	}
	
	ThisBook(String title, String color) {
		this(title, color, 4);	// 컬러까지 선언함
	}
	
	ThisBook(String title, String color, int index) {
		this.title = title;		// 공통실행코드
		this.color = color;
		this.index = index;
	}

}

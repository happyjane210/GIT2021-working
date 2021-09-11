package chap6_3;

public class Book {
	//filed
	String sort = "book";
	String title;
	String color;
	int index;
	
	// constructor  생성자
	Book() {
	}
	
	Book(String title) {
		this.title = title;
		System.out.println("book2.title: " + this.title);	//안네의 일기
	}
	
	Book(String title, String color) {
		this.title = title;
		this.color = color;
	}
	
	Book(String title, String color, int index) {
		this.title = title;
		this.color = color;
		this.index = index;
	}

}

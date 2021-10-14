package chap6_3.exercise;

public class Board {
	String title;
	String content;
	String writer;
	String date;
	int hitcount;
	
	Board(String title, String content){
		this(title, content, "로그인한 회원아이디", "현재 컴퓨터 날짜", 1);
	}
	
	Board(String title, String content, String writer){
		this(title, content, writer, "현재 컴퓨터 날짜", 2);
	}
	
	Board(String title, String content, String writer, String date){
		this(title, content, writer, date, 3);
	}
	
	Board(String title, String content, String writer, String date, int hitcount) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.date = date;
		this.hitcount = hitcount;
	}
	
}


class BoardExample {
	public static void main(String[] args) {
		Board b1 = new Board("제목", "내용");
		System.out.println(b1.title + " | " + b1.content);
		System.out.println(b1.writer);
		System.out.println(b1.date);
		System.out.println(b1.hitcount);
		
		System.out.println("---------------");
		
		Board b2 = new Board("제목", "내용", "로그인한 회원아이디");
		System.out.println(b2.title + " | " + b2.content);
		System.out.println(b2.writer);
		System.out.println(b2.date);
		System.out.println(b2.hitcount);
		
		System.out.println("---------------");
		
		Board b3 = new Board("제목", "내용", "로그인한 회원아이디", "현재 컴퓨터 날짜");
		System.out.println(b3.title + " | " + b3.content);
		System.out.println(b3.writer);
		System.out.println(b3.date);
		System.out.println(b3.hitcount);
		
		System.out.println("---------------");
		
		Board b4 = new Board("제목", "내용", "로그인한 회원아이디", "현재 컴퓨터 날짜", 4);
		System.out.println(b4.title + " | " + b4.content);
		System.out.println(b4.writer);
		System.out.println(b4.date);
		System.out.println(b4.hitcount);
		
	}
	
}

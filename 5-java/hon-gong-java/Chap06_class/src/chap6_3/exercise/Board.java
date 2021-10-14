package chap6_3.exercise;

public class Board {
	String title;
	String content;
	String writer;
	String date;
	int hitcount;
	
	Board(String title, String content){
		this(title, content, "�α����� ȸ�����̵�", "���� ��ǻ�� ��¥", 1);
	}
	
	Board(String title, String content, String writer){
		this(title, content, writer, "���� ��ǻ�� ��¥", 2);
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
		Board b1 = new Board("����", "����");
		System.out.println(b1.title + " | " + b1.content);
		System.out.println(b1.writer);
		System.out.println(b1.date);
		System.out.println(b1.hitcount);
		
		System.out.println("---------------");
		
		Board b2 = new Board("����", "����", "�α����� ȸ�����̵�");
		System.out.println(b2.title + " | " + b2.content);
		System.out.println(b2.writer);
		System.out.println(b2.date);
		System.out.println(b2.hitcount);
		
		System.out.println("---------------");
		
		Board b3 = new Board("����", "����", "�α����� ȸ�����̵�", "���� ��ǻ�� ��¥");
		System.out.println(b3.title + " | " + b3.content);
		System.out.println(b3.writer);
		System.out.println(b3.date);
		System.out.println(b3.hitcount);
		
		System.out.println("---------------");
		
		Board b4 = new Board("����", "����", "�α����� ȸ�����̵�", "���� ��ǻ�� ��¥", 4);
		System.out.println(b4.title + " | " + b4.content);
		System.out.println(b4.writer);
		System.out.println(b4.date);
		System.out.println(b4.hitcount);
		
	}
	
}

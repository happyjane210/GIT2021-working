package chap6_3;

public class ThisBookExample {
	public static void main(String[] args) {
		ThisBook book1 = new ThisBook();			// 기본값 객체
		System.out.println("book1.sort: " + book1.sort);		//book
		System.out.println("book1.title: " + book1.title);	//null
		System.out.println("book1.color: " + book1.color);	//null
		System.out.println("book1.color: " + book1.color);	// 0
		
		System.out.println("-------");
		
		ThisBook book2 = new ThisBook("안네의 일기");	// String 매개변수 객체
		System.out.println("book2.sort: " + book2.sort);		//book
		System.out.println("book2.title: " + book2.title);	//안네의 일기
		System.out.println("book2.color: " + book2.color);	//null
		System.out.println("book2.index: " + book2.index);	//0
		
		System.out.println("-------");
		
		ThisBook book3 = new ThisBook("노인과 바다", "blue");
		System.out.println("book3.sort: " + book3.sort);		//book
		System.out.println("book3.title: " + book3.title);	//노인과 바다
		System.out.println("book3.color: " + book3.color);	//blue
		System.out.println("book3.index: " + book3.index);	//0
		
		System.out.println("-------");
		
		ThisBook book4 = new ThisBook("혼공자", "red", 1);
		System.out.println("book4.sort: " + book4.sort);		//book
		System.out.println("book4.title: " + book4.title);	//혼공자
		System.out.println("book4.color: " + book4.color);	//red
		System.out.println("book4.index: " + book4.index);	//1
	}
}

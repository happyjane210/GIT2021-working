package chap6_3;

public class BookExample {
	public static void main(String[] args) {
		Book book1 = new Book();			// �⺻�� ��ü
		System.out.println("book1.sort: " + book1.sort);		//book
		System.out.println("book1.title: " + book1.title);	//null
		System.out.println("book1.color: " + book1.color);	//null
		System.out.println("book1.color: " + book1.color);	// 0
		
		System.out.println("-------");
		
		Book book2 = new Book("�ȳ��� �ϱ�");	// String �Ű����� ��ü
		System.out.println("book2.sort: " + book2.sort);		//book
		//System.out.println(book2.title);	//�ȳ��� �ϱ�
		System.out.println("book2.color: " + book2.color);	//null
		System.out.println("book2.index: " + book2.index);	//0
		
		System.out.println("-------");
		
		Book book3 = new Book("���ΰ� �ٴ�", "blue");
		System.out.println("book3.sort: " + book3.sort);		//book
		System.out.println("book3.title: " + book3.title);	//���ΰ� �ٴ�
		System.out.println("book3.color: " + book3.color);	//blue
		System.out.println("book3.index: " + book3.index);	//0
		
		System.out.println("-------");
		
		Book book4 = new Book("ȥ����", "red", 1);
		System.out.println("book4.sort: " + book4.sort);		//book
		System.out.println("book4.title: " + book4.title);	//ȥ����
		System.out.println("book4.color: " + book4.color);	//red
		System.out.println("book4.index: " + book4.index);	//1
	}
}

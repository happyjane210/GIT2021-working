package chap6_3;

public class ThisBook {
	//filed
	String sort = "book";
	String title;
	String color;
	int index;
	
	// constructor  ������
	ThisBook() {
	}
	
	ThisBook(String title) {	// ThisBook()���⼭ ������ ������ ������ ����,
		this(title, "grey", 3);	// ���� ���ϸ� ������ �������, String: ���ڿ� int: ���� 
	}
	
	ThisBook(String title, String color) {
		this(title, color, 4);	// �÷����� ������
	}
	
	ThisBook(String title, String color, int index) {
		this.title = title;		// ��������ڵ�
		this.color = color;
		this.index = index;
	}

}

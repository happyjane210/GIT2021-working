package OOP;

public class Human5 {	
	//							����Ŭ����   ������Ű��   ���Ŭ����   ��Ÿ
	public int age;			//     o          o         o       o
	private int id;			//     o          o         o       x
	private String name;	//     o          o         x       x
	private int password;	//     o          x         x       x
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	// �� �����̺����� �δ� �� ĸ��ȭ
	// 
	//     void: ��ȯŸ���� ���� , return ��������
	public void setAge(int age) {  // ����
		this.age = age;
	}
	
	//     int : ��ȯŸ���� int ����
	public int getAge() {  // ����
		return this.age;
	}
	
	
}

class Man {
	public static void main(String[] args) {
		Human5 human = new Human5();
		human.setAge(12);
		
		int leeAge = human.getAge();
	}
	
}
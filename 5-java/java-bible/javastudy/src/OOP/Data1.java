package OOP;

public class Data1 {   // Ŭ���� �̸��� Ÿ���� �ȴ�. (����Ÿ��)
	int age;
	String name;
	
	// ������ ����
	public Data1() {
		
	}
	
	// �Ű������ִ� ������ ����
	public Data1(int age, String name) {
		this.age = age;
		this.name = name;
	}
	 
	// �ż��� ����    �޾ƿ�Ÿ��:Ŭ������  �Ű����� �̸� �ƹ��ų�
	public String changeName(Data1 data) { // int , String ó�� Ÿ���� Ŭ������ ��, Ÿ�Խ�Ʈ��Ʈ �������̽� �����ϴ°Ŷ� ����
		data.name = "hong";
		data.age = 12;
		return data.name;
	}
}


class Dog {
	int age;
	String name;
	
	public static void wal() {   // �Լ��� �Լ��� ȣ���Ѵ�.
		System.out.println("wal!!!");
	}
}



class Human {
	public static void main(String[] args) {
		
		// ��ü�� ���� , �� ��ü�� �Ű������� �ѱ�                     
		//Data1          Data1    �����ؾ���    Data1 data1 = new Dog(0, "");   �̰� �Ұ�
		Data1 data1 = new Data1(26, "jane");
		
		//            ��ü�̸� �����������
		String newName = data1.changeName(data1);
		
		
		System.out.println(newName);
		
		
		
		
		
		
	}
}

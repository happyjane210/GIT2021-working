package map;

public class Human {   // �������� ����, �����͸� �� ������ �����Ұ���
	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + ", city=" + city + ", money=" + money + "]";
	}

	String name;
	int age;
	String city;
	int money;
	
	public Human(String name, int age, String city, int money) {
		this.name = name;
		this.age = age;
		this.city = city;
		this.money = money;
		
	}
}

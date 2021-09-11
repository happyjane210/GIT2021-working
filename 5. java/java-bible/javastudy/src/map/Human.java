package map;

public class Human {   // 데이터의 형식, 데이터를 맵 단위에 저장할거임
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

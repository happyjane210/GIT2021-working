package OOP;

public class Animal {  // �θ�
	// ������ Ư¡
	int legs;
	int ears;
	String bark;
	
	public void bark() {
		System.out.println(bark);
	}
	
	// �żҵ�� �ٲ�     AnimalŸ���� animal�Ű�����
//	public String bark(Animal animal) {
//		if(animal.legs == 4) {
//			return "miu";
//		} else if(animal.legs == 0) {
//			return "bogulbogul";
//		} else {
//			return "spiderman!";
//		}
//	};
	
}

//int legs;
//int ears;
//String bark;
//�̰� ���� �ʾƵ� �ٷ� ��������	


// ��� Ư¡�� ���� ���ŷӱ� ������ Ŭ������ ���  extends Animal
class Cat extends Animal{    // �ڽ�

	// ������ �Լ�
	public Cat( String bark) {

		this.bark = bark;
	}
	
}

class Fish extends Animal{
	public Fish(String bark) {

		this.bark = bark;
	}
}

class Spider extends Animal{
	public Spider(String bark) {

		this.bark = bark;
	}
}

// ������ ���� ���δ�
class House {
	public static void main(String[] args) {
		

		Cat cat = new Cat("miu");
		cat.bark();
		
		Fish fish = new Fish("bogulbogul");
		fish.bark();
		
		Spider spider = new Spider("spiderman!");
		spider.bark();

		
	}
}

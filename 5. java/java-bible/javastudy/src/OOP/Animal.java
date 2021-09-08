package OOP;

public class Animal {  // 부모
	// 동물의 특징
	int legs;
	int ears;
	String bark;
	
	public void bark() {
		System.out.println(bark);
	}
	
	// 매소드로 바꿈     Animal타입의 animal매개변수
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
//이걸 쓰지 않아도 바로 쓸수있음	


// 모든 특징을 쓰기 번거롭기 때문에 클래스를 상속  extends Animal
class Cat extends Animal{    // 자식

	// 생성자 함수
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

// 동물을 집에 들인다
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

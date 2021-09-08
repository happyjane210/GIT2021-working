// 오버라이딩 : 상속받은 부모의 매서드 내용을 자식단위에서 바꿀수있음 (이름은 같음)

package OOP;
// 한 패키지 안에 같은 이름의 클래스 있으면 안됨!! 바꿔줘야함

public class Animal2 {  // 부모
	// 동물의 특징
	int legs;
	int ears;
	String bark;
	
	// 상속을 받으면 매서드도 바꿔줌 , 매서드의 내용을 바꾸는 것을 오버라이딩이라고함 (이름은 같음)
	public void bark() {
		System.out.println(bark);
	}

}



class Cat1 extends Animal2{    // 자식


	public Cat1( String bark) {
		this.bark = bark;   // this : 클래스 자기자신을 참조하고 있음
	}
	
	public void bark() {   // 오버라이딩
		System.out.println(bark + legs + ears);
		super.bark();  // super : 상속한 부모 매서드를 참조해서 여기서 호출
	}
	
}


class House1 {
	public static void main(String[] args) {
		

		Cat1 cat = new Cat1("miu");
		cat.bark();
		
	}
}

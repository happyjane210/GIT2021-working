// import: 다른 패키지들끼리 클래스를 불러오는 것

package forimport;

import java.util.Scanner;  
//     폴더 경로 java>util>Scanner 클래스를 불러옴 

import OOP.Animal;
import OOP.Human5;
import OOP.Overloading;

public class Forimport {
	// 객체를 만들기 위해 클래스를 임포트
	
	Animal animal = new Animal();
	
	Overloading over = new Overloading();
	
	//Scanner scanner = new Scanner();   
	
	public static void main(String[] args) {
		System.out.println();  // sysout 은 모든 매서드 안에서만 실행됨, 클래스 영역에서는 못씀
	}
	
	public static void main1(String[] args) {
		// 객체는 main안에 써줘도 되는데, 객체로 뭔가 실행하려면 main안에 써줘야함
		Human5 human = new Human5();
		human.age =123;
		human.setId(1234);
		human.name = "dd";
		human.password = "34232";
		
		
	}
	
class women {
	public static void main(String[] args) {
		Human5 human = new Human5();
		human.setAge(12);
		human.getAge(); // 등록된 값을 가져옴
}
}

package OOP;

public class Human5 {	
	//							통일클래스   통일패키지   상속클래스   기타
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
	// 다 프라이빗으로 싸는 걸 캡슐화
	// 
	//     void: 반환타입이 없음 , return 생략가능
	public void setAge(int age) {  // 세터
		this.age = age;
	}
	
	//     int : 반환타입이 int 정수
	public int getAge() {  // 게터
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
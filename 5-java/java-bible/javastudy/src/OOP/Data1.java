package OOP;

public class Data1 {   // 클래스 이름이 타입이 된다. (참조타입)
	int age;
	String name;
	
	// 생성자 생성
	public Data1() {
		
	}
	
	// 매개변수있는 생성자 생성
	public Data1(int age, String name) {
		this.age = age;
		this.name = name;
	}
	 
	// 매서드 만듬    받아올타입:클래스명  매개변수 이름 아무거나
	public String changeName(Data1 data) { // int , String 처럼 타입을 클래스로 옴, 타입스트립트 인터페이스 참조하는거랑 같음
		data.name = "hong";
		data.age = 12;
		return data.name;
	}
}


class Dog {
	int age;
	String name;
	
	public static void wal() {   // 함수가 함수를 호출한다.
		System.out.println("wal!!!");
	}
}



class Human {
	public static void main(String[] args) {
		
		// 객체를 만듬 , 이 객체를 매개변수로 넘김                     
		//Data1          Data1    동일해야함    Data1 data1 = new Dog(0, "");   이건 불가
		Data1 data1 = new Data1(26, "jane");
		
		//            객체이름 참조해줘야함
		String newName = data1.changeName(data1);
		
		
		System.out.println(newName);
		
		
		
		
		
		
	}
}

package OOP;

public class ObjEx1 {  // 클래스 영역
	
	// 인스턴스 변수, 객체 변수 , 객체 만들때 쓰는 필드  , 템플릿 
	int age;
	String name;
	
	static private int age2; // 클래식 변수, 객체 생성 안해도 갖다 쓸수 있음, 클래스 전용메모리에 저장
	
	public static void main(String[] args) {  // 매서드 영역
		
		
		// 지역 변수, 그냥 갖다 쓸 수 있음 메서드 안에서만 쓰고, 메서드가 끝나면 없어짐
		int age;
		String name;
		age = 12;
		name = "smith";
		
		
		// 인스턴스 변수, 객체 변수, 클래스 영역에서 끌고온 지역변수
		// 객체 생성
		ObjEx1 obj = new ObjEx1();     // 객체가 생성되면 메모리에 저장
		obj.age = 12;   // 인스턴스 변수
		obj.name = "jane";
		
		
		//  static 클래스 변수 , 객체를 만들지 않고 클래스에서 바로 끌고옴
		ObjEx1.age2 = 12;
	}
}

package OOP;

public class ObjArr {
	int age;
	String name;
	
	public ObjArr(int age, String name) {
		this.age = age;     // this(클래스 자체를 가리킴): ObjArr 의 age
		this.name = name;
	}
}

//-------------------------------------------------

class ObjArrEx {
	public static void main(String[] args) {
		// 하나의 객체 저장
		ObjArr objArr = new ObjArr(12,"이주은");
		
		// 여러개 객체를 배열에 저장   objArr2 배열의 이름
		ObjArr[] objArr2 = {new ObjArr(14, "이성원"), new ObjArr(30, "김이박"), new ObjArr(14, "윤원") };
		// 클래스명[] 배열명 = { 0, 1, 2 }
		
		System.out.println(objArr.name + " " + objArr.age);
		System.out.println(objArr2[0].name + " " + objArr2[0].age);
		System.out.println(objArr2[1].name + " " + objArr2[1].age);
	}
}

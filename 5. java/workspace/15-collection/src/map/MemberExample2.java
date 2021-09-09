package map;

import java.util.HashMap;
import java.util.Map;

public class MemberExample2 {

	public static void main(String[] args) {

		// string 은 그대로 쓰지만 숫자로 쓰려면 Integer로 써야함 int 안됨
		// Integer int 에 대한 객체타입 (-> hashcode, equals 매서드가 내장) 내부적으로는 숫자값 비교랑 똑같음
		
		// Generic 제너릭:  Map<키타입, 값타입>
		Map<Integer, Member> members = new HashMap<Integer, Member>();
		
		// Map<Member, Contact>
		// Member: id, name, age
		// contact: home, company, mobile

//		String name = "hong";
//		System.out.println(name.hashCode());
	}

}
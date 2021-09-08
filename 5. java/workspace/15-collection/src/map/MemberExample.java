package map;

import java.util.HashMap;
import java.util.Map;

public class MemberExample {
	public static void main(String[] args) {
		// Map은 키가 중복이 없음
		Map<String, Member> members = new HashMap<String, Member>();
		
		// 1. 객체 추가
		Member hong = new Member("hong", "password123", "hong", 30);
		members.put("hong", hong);
		
		Member smith = new Member("john", "abcd1234", "john smith", 30);
		members.put("john", smith);
		
		// 같은키의 값 객체를 넣으면 값 객체가 덮어 씌워짐(수정이 일어남)
		Member smith2 = new Member("john123", "asd234", "john", 25);
		members.put("john", smith2);
		
		
		//2. 맴에서 ㅂ개의 값을 조회
		// 맵변수.get 키값
		Member m = members.get("hong");
		System.out.println(m.getName() + ", " + m.getAge());
		
		// 2-1. 맵 목록 조회   key의 목록을 키셋 형태로 반환
		// 맴변수.keySet() -> 맴의 key목록을 Set 형식의 자료구조로 반환
		// set: 중복값이 없는 자료구조
		for(String id : members.keySet()) {
			String name = members.get(id).getName();
			int age = members.get(id).getAge();
			
			System.out.println(id + ": " + name + ": " + age);
		}
	}
}

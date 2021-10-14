package chpa13_1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapExample {
	public static void main(String[] args) {
		
		//  키가 strign 값이 int
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		// 맵에 55라는 키가 이주은인 객체 생성
		map.put("이주은", 55);
		map.put("홍길동", 90);	// 90 없어지고 8738으로 바뀜
		map.put("유비", 42);
		map.put("홍길동", 8738);  // 홍길동 두번저장 , 키가 같으면 중복저장되지 않음
		
		System.out.println("총 Entry수: " + map.size());	// 총 Entry수: 3
		
		//                               "홍길동" 키로 저장된 값value를 불러옴
		System.out.println("홍길동 " + map.get("홍길동"));		//홍길동 8738
		System.out.println();

		
		
		// 계좌 리스트 뽑기 , keySet 이용
		Set<String> keySet = map.keySet();
		// key의 반복자를 얻어냄
		Iterator<String> keyIterator = keySet.iterator();
		
		// 가져올 키가 있을 때까지 while문 반복
		while(keyIterator.hasNext()) {
			String key = keyIterator.next();  // key를 하나 가져옴
			Integer value = map.get(key);		// key 로 저장된 int 객체(value)를 얻을 수 있음
			System.out.println("\t" + key + ":" + value);		// "\t" 들여쓰기
		}
		System.out.println();
		
		
		
		// 객체 없애기
		map.remove("홍길동");
		System.out.println("총 Entry수: " + map.size());	
		
		
		
		// 리스트 뽑기 , entrySet 이용
		// mapEntry 가 저장된 set 컬렉션을 얻음
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		// mapEntry에 대한 반복자 얻어냄
		Iterator <Map.Entry<String, Integer>> entryIterator = entrySet.iterator(); 
		
		while(entryIterator.hasNext()) {
			// 하나의 mapEntry객체를 얻어냄
			Map.Entry<String, Integer> entry = entryIterator.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("\t" + key + ":" + value);
		}
		System.out.println();
		
		// 전체 객체 삭제
		map.clear();
		System.out.println("총 Entry수: " + map.size());	
		
	}
}


// ctrl + shift + O  : 자동 임포트
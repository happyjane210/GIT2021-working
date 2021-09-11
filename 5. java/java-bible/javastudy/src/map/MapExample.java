package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


//Map<Key, Value> human = new HashMap<>();

public class MapExample {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		Map<Integer, Human> human = new HashMap<>();
		
		// 키값은 boolean, Integer, String 가능
		human.put(1, new Human("김길동", 123, "서울", 1534000));
		// hong 이라는 키값으로 new Human 이라는 객체가 들어감  => HashMap에 저장함
		human.put(2, new Human("이길동", 122343, "대전", 15000));
		human.put(3, new Human("박길동", 132342, "대구", 1500320));
		human.put(4, new Human("최길동", 111233, "부산", 15023400));
		human.put(5, new Human("고길동", 1313, "서울", 15023400));
		human.put(6, new Human("홍길동", 1873, "서울", 1523424000));
		
		// 꺼내올땐 메서드가 필요함
		Human human1 = human.get("김");  // 키를 통해서 값을 가져옴
		
		// 자동으로 toString() 이 붙어있음
		// toString()을 generate 해줘야함
		
		// human.size()   배열의 .length 와 같다  0부터 시작함
		// for 숫자로 
//		for(int i = 0; i < human.size(); i++) {
//			System.out.print("숫자를 입력하세요> ");
//			System.out.println(human.get(scanner.nextInt()));
//		}
		
//		System.out.print("숫자를 입력하세요> ");
//		System.out.println(human.get(scanner.nextInt()));
		
		// 키 값을 찾는 for문 , (자바스크립트 for-each문과 같은 기능),  처음부터 끝까지 반복,
		for (Integer id : human.keySet()) {
			if(id == 5) {  // 키가 5번만 호출함
				System.out.println(human.get(id));
			}
			//System.out.println(human.get(id));
		}
		
	}
}

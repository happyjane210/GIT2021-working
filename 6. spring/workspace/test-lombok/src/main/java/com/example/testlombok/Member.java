package com.example.testlombok;

import lombok.Data;		// ctrl + 1 : import lombok

@Data
// 롬복 플러그인이 java코드를 컴파일 할 때(저장할때)
// 롬복 어노테이션들(@Data)이 있는 클래스, 인터페이스, 필드, 메서드들을 탐색
// getter, setter, equals, hashcode, toString 메서드가
// 오른편에 자동으로 만들어짐
public class Member {
	private int id;
	private String name;
}

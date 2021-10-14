package com.example.testlombok;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestLombokApplicationTests {

	@Test
	void contextLoads() {
		Member member = new Member();
		member.setId(1); // 입력한 값
		member.setName("hong");

		// 테스트 코드를 짜는것
		assertEquals(1, member.getId()); // 꺼내온 값
		assertEquals("hong", member.getName());
	}

}
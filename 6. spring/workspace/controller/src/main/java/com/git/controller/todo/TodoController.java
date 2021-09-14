// REST API  :  REST 방식으로 접근 할 수 있는 인터페이스 제공하는 프로그램

package com.git.controller.todo;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
	// HashMap 정렬이 안됨 : get(key) -> 0(1)
	// ConcurrentSkipListMap 키 기준으로 정렬이 되어있음 : get(key) -> 0(long) 이진트리 정렬
	public ConcurrentSkipListMap<Long, Todo> todos = new ConcurrentSkipListMap<Long, Todo>();

	// id값 생성에 사용할 변수
	public AtomicLong maxId = new AtomicLong();

	// todo 목록조회
	// GET / todos
	@GetMapping(value = "/todos")
	public Collection<Todo> getTodos() {
		// 숫자 1개 증가하고 가져오기
		// Long currentId = maxId.incrementAndGet();
		// 빌더패턴으로 객체 생성 - 생성자처럼 매개변수 순서를 안지켜도 됨
		// Todo todo = Todo.builder().id(currentId).memo("test1").createdTime(new
		// Date().getTime()).build();
		// 맵에 생성한 객체를 넣어줌
		// todoMap.put(currentId, todo);
		return todos.descendingMap().values(); // descendingMap 역정렬
	}

	// todo 1건 추가
	// POST / todos {"memo" : "테스트입니다"} - 들어옴
	@PostMapping(value = "/todos") // res 객체로 만들어서 controller의 메소드에 넣어줌
	public Todo postTodo(@RequestBody Todo todo, HttpServletResponse res) {
		// 데이터 검증 로직을 추가
		if (todo.getMemo() == null || todo.getMemo().isEmpty()) {
			// 메모가 빈값이면 에러
			// 클라이언트에서 메모값 없이 보내거나 빈값으로 보낸것 = 클라이언트 오류 코드 Bad Request (400)
			// res.setStatus(400) SC_BAD_REQUEST = 400

			// Dispatcher Servlet이 생성한 응답객체에 status코드를 넣어줌
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return null;

		}

		// id값을 생성
		Long currentId = maxId.incrementAndGet();

		// 입력받은 데이터로 todo객체를 생성
		// id값과 생성일시는 서버에서 생성한 것으로 처리함
		// html 테그가 있으면 날려버림(script에서 문제가 발생함)
		Todo todoItme = Todo.builder().id(currentId)
				.memo(todo.getMemo().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.createdTime(new Date().getTime()).build();

		// todo 목록객체 추가
		todos.put(currentId, todoItme);

		// 리소스 생성됨
		// res.setStatus(201) = SC_CREATED
		res.setStatus(HttpServletResponse.SC_CREATED);

		// 추가된 객체를 반환
		return todoItme;
	}

}

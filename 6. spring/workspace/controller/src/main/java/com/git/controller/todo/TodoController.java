// REST API  :  REST 방식으로 접근 할 수 있는 인터페이스 제공하는 프로그램

package com.git.controller.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
	// HashMap 정렬이 안됨 : get(key) -> 0(1)
	// ConcurrentSkipListMap 키 기준으로 정렬이 되어있음 : get(key) -> 0(long) 이진트리 정렬
	// private ConcurrentSkipListMap<Long, Todo> todos = new
	// ConcurrentSkipListMap<Long, Todo>();
	private SortedMap<Long, Todo> todos = Collections
			.synchronizedSortedMap(new TreeMap<Long, Todo>(Collections.reverseOrder()));
	// id값 생성에 사용할 변수
	private AtomicLong maxId = new AtomicLong();

	// todo 목록조회
	// GET / todos
	@GetMapping(value = "/todos")
	public List<Todo> getTodos() {
		// 숫자 1개 증가하고 가져오기
		// Long currentId = maxId.incrementAndGet();
		// 빌더패턴으로 객체 생성 - 생성자처럼 매개변수 순서를 안지켜도 됨
		// Todo todo = Todo.builder().id(currentId).memo("test1").createdTime(new
		// Date().getTime()).build();
		// 맵에 생성한 객체를 넣어줌
		// todoMap.put(currentId, todo);
		// descendingMap 역정렬
		return new ArrayList<Todo>(todos.values()); // 맵 값 목록 , ArrayList: 자바의 배열
	}

	// -----ADD---------------------------------------------------

	// todo 1건 추가
	// POST / todos {"memo" : "테스트입니다"} - 들어옴
	@PostMapping(value = "/todos") // res 객체로 만들어서 controller의 메소드에 넣어줌
	public Todo addTodo(@RequestBody Todo todo, HttpServletResponse res) {
		// 데이터 검증 로직을 추가
		if (todo.getMemo() == null || todo.getMemo().isEmpty()) {
			// 메모가 빈값이면 에러
			// 클라이언트에서 메모값 없이 보내거나 빈값으로 보낸것 = 클라이언트 오류 코드 Bad Request (400)
			// res.setStatus(400) SC_BAD_REQUEST = 400

			// Dispatcher Servlet이 생성한 응답객체에 status코드를 넣어줌
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 테그를 다 지웠더니 빈문자열
		// 만약 <script> 테그 제거 후 빈값만 남는다면, 400 status code 리턴
		String memo = getPlainText(todo.getMemo());
		if (memo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// id값을 생성
		Long currentId = maxId.incrementAndGet();

		// 입력받은 데이터로 todo객체를 생성
		// id값과 생성일시는 서버에서 생성한 것으로 처리함
		// html 테그가 있으면 날려버림(script에서 문제가 발생함)
		Todo todoItme = Todo.builder().id(currentId).memo(getPlainText(todo.getMemo()))
				.createdTime(new Date().getTime()).build();

		// todo 목록객체 추가
		todos.put(currentId, todoItme);

		// 리소스 생성됨
		// res.setStatus(201) = SC_CREATED
		res.setStatus(HttpServletResponse.SC_CREATED);

		// 추가된 객체를 반환
		return todoItme;
	}

	// ------REMOVE-----------------------------------

	// todo 1건 삭제
	// delete / todos /1 -> id가 1인 항목을 삭제해라
	// id값이 path variable로
	@DeleteMapping(value = "/todos/{id}")
	public boolean removeTodo(@PathVariable long id, HttpServletResponse res) {

		// 해당 id의 데이터 1건을 가져옴
		Todo todo = todos.get(Long.valueOf(id));
		if (todo == null) { // 해당데이터가 없으면 - 목록에 없는 숫자 입력하면
			// res.setStatus(404)
			// NOT FOUND : 해당 경로에 리소스가 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// **실제 삭제 수행
		todos.remove(Long.valueOf(id));

		return true;
	}

	// ------EDIT-----------------------------------

	// todo 1건 수정
	// PUT / todos/1 {"memo":"..."} , 연락처 관리는 여기에 수정목록 추가함
	@PutMapping(value = "/todos/{id}")
	public Todo modifyTodo(@PathVariable long id, @RequestBody Todo todo, HttpServletResponse res) {
		// 해당 id의 데이터 1건을 가져옴
		Todo findItem = todos.get(Long.valueOf(id));
		if (findItem == null) { // 해당데이터가 없으면 - 목록에 없는 숫자 입력하면
			// res.setStatus(404)
			// NOT FOUND : 해당 경로에 리소스가 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 데이터 검증 로직을 추가
		// 메모가 빈값이면 에러
		if (todo.getMemo() == null || todo.getMemo().isEmpty()) {
			// 클라이언트에서 메모값 없이 보내거나 빈값으로 보낸것 = 클라이언트 오류 코드 Bad Request (400)
			// res.setStatus(400) SC_BAD_REQUEST = 400

			// Dispatcher Servlet이 생성한 응답객체에 status코드를 넣어줌
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 데이터를 변경
		// 만약 <script> 테그 제거 후 빈값만 남는다면, 400 status code 리턴
		String memo = getPlainText(todo.getMemo());
		if (memo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		findItem.setMemo(memo);

		return findItem; // 변경된 데이터 리턴
	}

	// html 테그를 제거하는 메서드
	private String getPlainText(String text) {
		return text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}

}

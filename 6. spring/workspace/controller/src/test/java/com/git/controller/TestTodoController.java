// 단위 테스트 코드

package com.git.controller;

// 값이 같은지를 비교하는 static 메서드
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.git.controller.todo.Todo;
import com.git.controller.todo.TodoController;

@SpringBootTest
public class TestTodoController {

	// 스프링에서 IoC 컨테이너 (싱글턴 객체관리자)
	// IoC : Inversion of Concern - 제어의 역전 (관심의 분리)
	// 객체를 사용하려면 객체를 생성해야함
	// 객체 생성은 Spring 에서 해주고, 사용은 의존성주입으로 주입받아서 사용함
	// 의존성 주입 (Dependency Injection) : 객체를 사용하는 곳 외부에서 객체를 넘겨받는 것
	// 주고 필드, 메서드 매개변수로 주입을 받는다.

	// IoC 컨테이너에 있는 TodoController 싱글턴 객체를 해당 필드에 주입함
	// TodoController controller = TodoController.getInstance();
	// IoC 컨테이너에서 관리되는 싱글턴 객체를 Bean 객체라고 함
//	@Autowired
//	TodoController controller;

	// 현재 테스트 클래스에 매서드들이 수행되기전 최초로 한번 수행되는 메서드

	// test case (테스트 케이스) : 할일추가
	// event flows(처리 흐름) : 할일 1건을 추가함
	// pre-condition (사전조건) : 따로 없음
	// expected result (예상결과) : 할일 목록에 추가한 데이터가 존재해야함

	// =======ADD=================================================

	@Test
	void addTodo() {

		// given - 테스트데이터 및 객체 준비
		TodoController controller = new TodoController();

		Todo expected = Todo.builder().id(1).memo("test").createdTime(1).build();

		// when - 실제 테스트케이스 수행
		// ServletResponse 객체는 가짜 (Mock)를 넣어줌
		controller.addTodo(expected, new MockHttpServletResponse());

		// then - 예상결과와 실제결과를 비교

		// 1. 데이터를 일단 꺼내옴
		// - 전체목록에서 추가한 애를 가져옴
		List<Todo> todos = controller.getTodos();
		Todo actual = todos.get(0); // arrayList.get(인덱스)

		// 예상결과 데이터와 실제 데이터를 비교함
		assertEquals(expected.getId(), actual.getId()); // assertEquals: 값이 같은지를 비교
		assertEquals(expected.getMemo(), actual.getMemo());

	}

	// =======REMOVE=================================================

	// test case (테스트 케이스) : 할일 삭제
	// event flows(처리 흐름) : 할일 1건을 삭제함
	// pre-condition (사전조건) : 할 일 데이터 최소 1건 이상 있어야함
	// expected result (예상결과) : 할일 목록에 삭제한 데이터가 존재하면 안됨
	@Test
	void removeTodo() {
		// given - 테스트 데이터 및 객체 준비
		// 사전조건이 있다면 사전조건을 준비하는 단계
		// 여기서는 1건 추가가 되어있어야함
		TodoController controller = new TodoController();

		Todo testItem = Todo.builder().memo("test").build();
		controller.addTodo(testItem, new MockHttpServletResponse());

		List<Todo> beforeTodos = controller.getTodos();
		assertEquals(1, beforeTodos.size()); // 삭제 전에는 목록 크기가 1

		// when - 테스트 케이스의 event flows를 수행
		controller.removeTodo(1, new MockHttpServletResponse()); // id가 1인 todo 1건을 삭제

		// then - 예상결과와 실제결과를 비교
		// 목록을 조회했을 때 목록의 크기가 0이어야함
		List<Todo> aftertTodos = controller.getTodos();
		assertEquals(0, aftertTodos.size()); // 삭제 후에는 목록 크기가 0

	}

	// =======EDIT=================================================

	// test case (테스트 케이스) : 할일 수정
	// event flows(처리 흐름) : 할일 1건을 수정함
	// * basic flow (정상흐름)
	// _____1. 할 일 1건에 대해서 메모값을 수정함
	// * alternative flow (예외흐름, 오류처리) :
	// _____1. 목록에 없는 id값으로 삭제를 실행함 - 400
	// _____2. 메모값을 빈값 또는 데이터 객체를 안보냈음 - 404
	// pre-condition (사전조건) : 할 일 데이터 최소 1건 이상 있어야함
	// expected result (예상결과) : 할일 목록에 수정한 메모값이 출력되야함
	@Test
	void modifyTodo() {

		// given 샘플데이터 넣기
		// 사전조건이 있다면 사전조건을 준비하는 단계
		// 여기서는 1건 추가가 되어있어야함
		TodoController controller = new TodoController();

		Todo testItem = Todo.builder().memo("test").build();
		controller.addTodo(testItem, new MockHttpServletResponse());

		String expectedResult = "modify test memo";
		Todo modifyData = Todo.builder().memo(expectedResult).build();

		HttpServletResponse res = new MockHttpServletResponse();

		// * basic flow - 정상적인 상황
		// when - 테스트 케이스의 event flows를 수행
		// id가 1인 todo에 memo를 수정
		// ** Map key 값, id값, 1부터 시작
		controller.modifyTodo(1, modifyData, res);

		// then - 예상결과와 실제결과를 비교
		// 목록을 조회했을 때 해당 아이템의 메모값이 예상 결과와 일치해야함
		List<Todo> todos = controller.getTodos();
		// ** List = 배열 index, 0부터 시작
		assertEquals(expectedResult, todos.get(0).getMemo());

		// * alternative flow - 1. 없는 id값으로 삭제를 실행함 (400)
		// when
		Todo resultTodoId = controller.modifyTodo(2, modifyData, res);

		// then - 예상결과와 실제결과를 비교
		// => 반환객체가 null, status code 404
		assertNull(resultTodoId);
		assertEquals(HttpServletResponse.SC_NOT_FOUND, res.getStatus());

		// -------------

		// * alternative flow - 2-1. 메모값이 null인 경우 (404)
		// when
		Todo resultTodoMemoNull = controller.modifyTodo(1, new Todo(), res);

		// then - 예상결과와 실제결과를 비교
		// => 반환객체가 null, status code 400
		assertNull(resultTodoMemoNull);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());

		// -------------

		// * alternative flow - 2-2. 메모값이 빈값[""]인 경우 (404)
		// when
		Todo resultTodoMemoEmpty = controller.modifyTodo(1, Todo.builder().memo("").build(), res);

		// then - 예상결과와 실제결과를 비교
		// => 반환객체가 null, status code 400
		assertNull(resultTodoMemoEmpty);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());
	}

}

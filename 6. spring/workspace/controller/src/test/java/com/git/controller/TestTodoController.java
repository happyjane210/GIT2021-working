// ���� �׽�Ʈ �ڵ�

package com.git.controller;

// ���� �������� ���ϴ� static �޼���
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

	// ���������� IoC �����̳� (�̱��� ��ü������)
	// IoC : Inversion of Concern - ������ ���� (������ �и�)
	// ��ü�� ����Ϸ��� ��ü�� �����ؾ���
	// ��ü ������ Spring ���� ���ְ�, ����� �������������� ���Թ޾Ƽ� �����
	// ������ ���� (Dependency Injection) : ��ü�� ����ϴ� �� �ܺο��� ��ü�� �Ѱܹ޴� ��
	// �ְ� �ʵ�, �޼��� �Ű������� ������ �޴´�.

	// IoC �����̳ʿ� �ִ� TodoController �̱��� ��ü�� �ش� �ʵ忡 ������
	// TodoController controller = TodoController.getInstance();
	// IoC �����̳ʿ��� �����Ǵ� �̱��� ��ü�� Bean ��ü��� ��
//	@Autowired
//	TodoController controller;

	// ���� �׽�Ʈ Ŭ������ �ż������ ����Ǳ��� ���ʷ� �ѹ� ����Ǵ� �޼���

	// test case (�׽�Ʈ ���̽�) : �����߰�
	// event flows(ó�� �帧) : ���� 1���� �߰���
	// pre-condition (��������) : ���� ����
	// expected result (������) : ���� ��Ͽ� �߰��� �����Ͱ� �����ؾ���

	// =======ADD=================================================

	@Test
	void addTodo() {

		// given - �׽�Ʈ������ �� ��ü �غ�
		TodoController controller = new TodoController();

		Todo expected = Todo.builder().id(1).memo("test").createdTime(1).build();

		// when - ���� �׽�Ʈ���̽� ����
		// ServletResponse ��ü�� ��¥ (Mock)�� �־���
		controller.addTodo(expected, new MockHttpServletResponse());

		// then - �������� ��������� ��

		// 1. �����͸� �ϴ� ������
		// - ��ü��Ͽ��� �߰��� �ָ� ������
		List<Todo> todos = controller.getTodos();
		Todo actual = todos.get(0); // arrayList.get(�ε���)

		// ������ �����Ϳ� ���� �����͸� ����
		assertEquals(expected.getId(), actual.getId()); // assertEquals: ���� �������� ��
		assertEquals(expected.getMemo(), actual.getMemo());

	}

	// =======REMOVE=================================================

	// test case (�׽�Ʈ ���̽�) : ���� ����
	// event flows(ó�� �帧) : ���� 1���� ������
	// pre-condition (��������) : �� �� ������ �ּ� 1�� �̻� �־����
	// expected result (������) : ���� ��Ͽ� ������ �����Ͱ� �����ϸ� �ȵ�
	@Test
	void removeTodo() {
		// given - �׽�Ʈ ������ �� ��ü �غ�
		// ���������� �ִٸ� ���������� �غ��ϴ� �ܰ�
		// ���⼭�� 1�� �߰��� �Ǿ��־����
		TodoController controller = new TodoController();

		Todo testItem = Todo.builder().memo("test").build();
		controller.addTodo(testItem, new MockHttpServletResponse());

		List<Todo> beforeTodos = controller.getTodos();
		assertEquals(1, beforeTodos.size()); // ���� ������ ��� ũ�Ⱑ 1

		// when - �׽�Ʈ ���̽��� event flows�� ����
		controller.removeTodo(1, new MockHttpServletResponse()); // id�� 1�� todo 1���� ����

		// then - �������� ��������� ��
		// ����� ��ȸ���� �� ����� ũ�Ⱑ 0�̾����
		List<Todo> aftertTodos = controller.getTodos();
		assertEquals(0, aftertTodos.size()); // ���� �Ŀ��� ��� ũ�Ⱑ 0

	}

	// =======EDIT=================================================

	// test case (�׽�Ʈ ���̽�) : ���� ����
	// event flows(ó�� �帧) : ���� 1���� ������
	// * basic flow (�����帧)
	// _____1. �� �� 1�ǿ� ���ؼ� �޸��� ������
	// * alternative flow (�����帧, ����ó��) :
	// _____1. ��Ͽ� ���� id������ ������ ������ - 400
	// _____2. �޸��� �� �Ǵ� ������ ��ü�� �Ⱥ����� - 404
	// pre-condition (��������) : �� �� ������ �ּ� 1�� �̻� �־����
	// expected result (������) : ���� ��Ͽ� ������ �޸��� ��µǾ���
	@Test
	void modifyTodo() {

		// given ���õ����� �ֱ�
		// ���������� �ִٸ� ���������� �غ��ϴ� �ܰ�
		// ���⼭�� 1�� �߰��� �Ǿ��־����
		TodoController controller = new TodoController();

		Todo testItem = Todo.builder().memo("test").build();
		controller.addTodo(testItem, new MockHttpServletResponse());

		String expectedResult = "modify test memo";
		Todo modifyData = Todo.builder().memo(expectedResult).build();

		HttpServletResponse res = new MockHttpServletResponse();

		// * basic flow - �������� ��Ȳ
		// when - �׽�Ʈ ���̽��� event flows�� ����
		// id�� 1�� todo�� memo�� ����
		// ** Map key ��, id��, 1���� ����
		controller.modifyTodo(1, modifyData, res);

		// then - �������� ��������� ��
		// ����� ��ȸ���� �� �ش� �������� �޸��� ���� ����� ��ġ�ؾ���
		List<Todo> todos = controller.getTodos();
		// ** List = �迭 index, 0���� ����
		assertEquals(expectedResult, todos.get(0).getMemo());

		// * alternative flow - 1. ���� id������ ������ ������ (400)
		// when
		Todo resultTodoId = controller.modifyTodo(2, modifyData, res);

		// then - �������� ��������� ��
		// => ��ȯ��ü�� null, status code 404
		assertNull(resultTodoId);
		assertEquals(HttpServletResponse.SC_NOT_FOUND, res.getStatus());

		// -------------

		// * alternative flow - 2-1. �޸��� null�� ��� (404)
		// when
		Todo resultTodoMemoNull = controller.modifyTodo(1, new Todo(), res);

		// then - �������� ��������� ��
		// => ��ȯ��ü�� null, status code 400
		assertNull(resultTodoMemoNull);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());

		// -------------

		// * alternative flow - 2-2. �޸��� ��[""]�� ��� (404)
		// when
		Todo resultTodoMemoEmpty = controller.modifyTodo(1, Todo.builder().memo("").build(), res);

		// then - �������� ��������� ��
		// => ��ȯ��ü�� null, status code 400
		assertNull(resultTodoMemoEmpty);
		assertEquals(HttpServletResponse.SC_BAD_REQUEST, res.getStatus());
	}

}

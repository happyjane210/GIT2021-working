// REST API  :  REST ������� ���� �� �� �ִ� �������̽� �����ϴ� ���α׷�

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
	// HashMap ������ �ȵ� : get(key) -> 0(1)
	// ConcurrentSkipListMap Ű �������� ������ �Ǿ����� : get(key) -> 0(long) ����Ʈ�� ����
	// private ConcurrentSkipListMap<Long, Todo> todos = new
	// ConcurrentSkipListMap<Long, Todo>();
	private SortedMap<Long, Todo> todos = Collections
			.synchronizedSortedMap(new TreeMap<Long, Todo>(Collections.reverseOrder()));
	// id�� ������ ����� ����
	private AtomicLong maxId = new AtomicLong();

	// todo �����ȸ
	// GET / todos
	@GetMapping(value = "/todos")
	public List<Todo> getTodos() {
		// ���� 1�� �����ϰ� ��������
		// Long currentId = maxId.incrementAndGet();
		// ������������ ��ü ���� - ������ó�� �Ű����� ������ �����ѵ� ��
		// Todo todo = Todo.builder().id(currentId).memo("test1").createdTime(new
		// Date().getTime()).build();
		// �ʿ� ������ ��ü�� �־���
		// todoMap.put(currentId, todo);
		// descendingMap ������
		return new ArrayList<Todo>(todos.values()); // �� �� ��� , ArrayList: �ڹ��� �迭
	}

	// -----ADD---------------------------------------------------

	// todo 1�� �߰�
	// POST / todos {"memo" : "�׽�Ʈ�Դϴ�"} - ����
	@PostMapping(value = "/todos") // res ��ü�� ���� controller�� �޼ҵ忡 �־���
	public Todo addTodo(@RequestBody Todo todo, HttpServletResponse res) {
		// ������ ���� ������ �߰�
		if (todo.getMemo() == null || todo.getMemo().isEmpty()) {
			// �޸� ���̸� ����
			// Ŭ���̾�Ʈ���� �޸� ���� �����ų� ������ ������ = Ŭ���̾�Ʈ ���� �ڵ� Bad Request (400)
			// res.setStatus(400) SC_BAD_REQUEST = 400

			// Dispatcher Servlet�� ������ ���䰴ü�� status�ڵ带 �־���
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// �ױ׸� �� �������� ���ڿ�
		// ���� <script> �ױ� ���� �� �󰪸� ���´ٸ�, 400 status code ����
		String memo = getPlainText(todo.getMemo());
		if (memo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// id���� ����
		Long currentId = maxId.incrementAndGet();

		// �Է¹��� �����ͷ� todo��ü�� ����
		// id���� �����Ͻô� �������� ������ ������ ó����
		// html �ױװ� ������ ��������(script���� ������ �߻���)
		Todo todoItme = Todo.builder().id(currentId).memo(getPlainText(todo.getMemo()))
				.createdTime(new Date().getTime()).build();

		// todo ��ϰ�ü �߰�
		todos.put(currentId, todoItme);

		// ���ҽ� ������
		// res.setStatus(201) = SC_CREATED
		res.setStatus(HttpServletResponse.SC_CREATED);

		// �߰��� ��ü�� ��ȯ
		return todoItme;
	}

	// ------REMOVE-----------------------------------

	// todo 1�� ����
	// delete / todos /1 -> id�� 1�� �׸��� �����ض�
	// id���� path variable��
	@DeleteMapping(value = "/todos/{id}")
	public boolean removeTodo(@PathVariable long id, HttpServletResponse res) {

		// �ش� id�� ������ 1���� ������
		Todo todo = todos.get(Long.valueOf(id));
		if (todo == null) { // �ش絥���Ͱ� ������ - ��Ͽ� ���� ���� �Է��ϸ�
			// res.setStatus(404)
			// NOT FOUND : �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// **���� ���� ����
		todos.remove(Long.valueOf(id));

		return true;
	}

	// ------EDIT-----------------------------------

	// todo 1�� ����
	// PUT / todos/1 {"memo":"..."} , ����ó ������ ���⿡ ������� �߰���
	@PutMapping(value = "/todos/{id}")
	public Todo modifyTodo(@PathVariable long id, @RequestBody Todo todo, HttpServletResponse res) {
		// �ش� id�� ������ 1���� ������
		Todo findItem = todos.get(Long.valueOf(id));
		if (findItem == null) { // �ش絥���Ͱ� ������ - ��Ͽ� ���� ���� �Է��ϸ�
			// res.setStatus(404)
			// NOT FOUND : �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// ������ ���� ������ �߰�
		// �޸� ���̸� ����
		if (todo.getMemo() == null || todo.getMemo().isEmpty()) {
			// Ŭ���̾�Ʈ���� �޸� ���� �����ų� ������ ������ = Ŭ���̾�Ʈ ���� �ڵ� Bad Request (400)
			// res.setStatus(400) SC_BAD_REQUEST = 400

			// Dispatcher Servlet�� ������ ���䰴ü�� status�ڵ带 �־���
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// �����͸� ����
		// ���� <script> �ױ� ���� �� �󰪸� ���´ٸ�, 400 status code ����
		String memo = getPlainText(todo.getMemo());
		if (memo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		findItem.setMemo(memo);

		return findItem; // ����� ������ ����
	}

	// html �ױ׸� �����ϴ� �޼���
	private String getPlainText(String text) {
		return text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}

}

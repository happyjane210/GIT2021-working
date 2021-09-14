// REST API  :  REST ������� ���� �� �� �ִ� �������̽� �����ϴ� ���α׷�

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
	// HashMap ������ �ȵ� : get(key) -> 0(1)
	// ConcurrentSkipListMap Ű �������� ������ �Ǿ����� : get(key) -> 0(long) ����Ʈ�� ����
	public ConcurrentSkipListMap<Long, Todo> todos = new ConcurrentSkipListMap<Long, Todo>();

	// id�� ������ ����� ����
	public AtomicLong maxId = new AtomicLong();

	// todo �����ȸ
	// GET / todos
	@GetMapping(value = "/todos")
	public Collection<Todo> getTodos() {
		// ���� 1�� �����ϰ� ��������
		// Long currentId = maxId.incrementAndGet();
		// ������������ ��ü ���� - ������ó�� �Ű����� ������ �����ѵ� ��
		// Todo todo = Todo.builder().id(currentId).memo("test1").createdTime(new
		// Date().getTime()).build();
		// �ʿ� ������ ��ü�� �־���
		// todoMap.put(currentId, todo);
		return todos.descendingMap().values(); // descendingMap ������
	}

	// todo 1�� �߰�
	// POST / todos {"memo" : "�׽�Ʈ�Դϴ�"} - ����
	@PostMapping(value = "/todos") // res ��ü�� ���� controller�� �޼ҵ忡 �־���
	public Todo postTodo(@RequestBody Todo todo, HttpServletResponse res) {
		// ������ ���� ������ �߰�
		if (todo.getMemo() == null || todo.getMemo().isEmpty()) {
			// �޸� ���̸� ����
			// Ŭ���̾�Ʈ���� �޸� ���� �����ų� ������ ������ = Ŭ���̾�Ʈ ���� �ڵ� Bad Request (400)
			// res.setStatus(400) SC_BAD_REQUEST = 400

			// Dispatcher Servlet�� ������ ���䰴ü�� status�ڵ带 �־���
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return null;

		}

		// id���� ����
		Long currentId = maxId.incrementAndGet();

		// �Է¹��� �����ͷ� todo��ü�� ����
		// id���� �����Ͻô� �������� ������ ������ ó����
		// html �ױװ� ������ ��������(script���� ������ �߻���)
		Todo todoItme = Todo.builder().id(currentId)
				.memo(todo.getMemo().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.createdTime(new Date().getTime()).build();

		// todo ��ϰ�ü �߰�
		todos.put(currentId, todoItme);

		// ���ҽ� ������
		// res.setStatus(201) = SC_CREATED
		res.setStatus(HttpServletResponse.SC_CREATED);

		// �߰��� ��ü�� ��ȯ
		return todoItme;
	}

}

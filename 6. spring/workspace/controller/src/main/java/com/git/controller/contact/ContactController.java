package com.git.controller.contact;

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
public class ContactController {

	// ���� Map, ���������� ���̰�
	private SortedMap<Long, Contact> contacts = Collections
			.synchronizedSortedMap(new TreeMap<Long, Contact>(Collections.reverseOrder()));

	// id �� ������ ����� ����
	private AtomicLong maxId = new AtomicLong();

	// -----GET---------------------------------------------------
	// contact �����ȸ
	// GET / contacts
	@GetMapping(value = "/contacts")
	public List<Contact> getContacts() {
		return new ArrayList<Contact>(contacts.values());

	}

	// -----ADD---------------------------------------------------

	// contact 1�� �߰�
	// POST / contacts �� ����ó �Է�
	@PostMapping(value = "/contacts")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) {
		// ������ ���� ����
		if (contact.getName() == null || contact.getName().isEmpty() || contact.getPhone() == null
				|| contact.getPhone().isEmpty() || contact.getEmail() == null || contact.getEmail().isEmpty()
				|| contact.getMemo() == null || contact.getMemo().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// id���� ����
		Long currentId = maxId.incrementAndGet();
		// �Է¹��� �����ͷ� contact��ü�� ����
		// id���� �����Ͻô� �������� ������ ������ ó����
		// html �ױװ� ������ ��������(script���� ������ �߻���)
		@SuppressWarnings("deprecation")
		Contact contactItme = Contact.builder().id(currentId)
				.name(contact.getName().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.phone(contact.getPhone().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.email(contact.getEmail().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.memo(contact.getMemo().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.createdTime(new Date().getTime()).build();

		// contact ��ϰ�ü �߰�
		contacts.put(currentId, contactItme);
		// ���ҽ� ������
		// res.setStatus(201) = SC_CREATED
		res.setStatus(HttpServletResponse.SC_CREATED);
		// �߰��� ��ü�� ��ȯ
		return contactItme;

	}

	// ------REMOVE-----------------------------------

	// contact 1�� ����
	// delete / contact /1 -> id�� 1�� �׸��� �����ض�
	// id���� path variable��
	@DeleteMapping(value = "/contacts/{id}")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) {

		// �ش� id�� ������ 1���� ������
		Contact contact = contacts.get(Long.valueOf(id));
		if (contact == null) { // �ش絥���Ͱ� ������ - ��Ͽ� ���� ���� �Է��ϸ�
			// res.setStatus(404)
			// NOT FOUND : �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// **���� ���� ����
		contacts.remove(Long.valueOf(id));
		return true;
	}

	// ------EDIT-----------------------------------
	// contact 1�� ����
	// PUT/contact/1 {"memo":"..."} , ����ó ������ ���⿡ ������� �߰���
	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res) {

		// �ش� id�� ������ 1���� ������
		Contact findItem = contacts.get(Long.valueOf(id));

		if (findItem == null) { // �ش絥���Ͱ� ������ - ��Ͽ� ���� ���� �Է��ϸ�
			// res.setStatus(404)
			// NOT FOUND : �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// ������ ���� ������ �߰�
		// �޸� ���̸� ����
		if (contact.getName() == null || contact.getName().isEmpty() || contact.getPhone() == null
				|| contact.getPhone().isEmpty() || contact.getEmail() == null || contact.getEmail().isEmpty()) {
			// Ŭ���̾�Ʈ���� �޸� ���� �����ų� ������ ������ = Ŭ���̾�Ʈ ���� �ڵ� Bad Request (400)
			// res.setStatus(400) SC_BAD_REQUEST = 400

			// Dispatcher Servlet�� ������ ���䰴ü�� status�ڵ带 �־���
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		if (contact.getMemo() == null || contact.getMemo().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// �����͸� ����
		findItem.setName(contact.getName());
		findItem.setPhone(contact.getPhone());
		findItem.setEmail(contact.getEmail());
		findItem.setMemo(contact.getMemo());

		return findItem;
	}

}

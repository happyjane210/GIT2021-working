package com.git.myworkspace.contactLine;

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
public class ContactLineController {

	// ���� ���������� ���̰�
	private SortedMap<Long, ContactLine> contactlines = Collections
			.synchronizedSortedMap(new TreeMap<Long, ContactLine>(Collections.reverseOrder()));

	// id �� ������ ����� ����
	private AtomicLong maxId = new AtomicLong();

	// contact �����ȸ
	// GET / linecontacts
	@GetMapping(value = "/linecontacts")
	public List<ContactLine> getContactLines() {
		return new ArrayList<ContactLine>(contactlines.values());
	}

	// -----ADD---------------------------------------------------

	// contactline 1�� �߰�
	// POST / linecontacts ������ó �Է�
	@PostMapping(value = "/linecontacts")
	public ContactLine addContact(@RequestBody ContactLine contactline, HttpServletResponse res) {

		// ������ ����
		if (contactline.getName() == null || contactline.getName().isEmpty() || contactline.getPhone() == null
				|| contactline.getPhone().isEmpty() || contactline.getEmail() == null
				|| contactline.getEmail().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// id���� ����
		Long currentId = maxId.incrementAndGet();

		// html �ױװ� ������ ��������(script���� ������ �߻���)
		@SuppressWarnings("deprecation")
		ContactLine contactlineItem = ContactLine.builder().id(currentId)
				.name(contactline.getName().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.phone(contactline.getPhone().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.email(contactline.getEmail().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.createdTime(new Date().getTime()).build();

		// contact ��ϰ�ü �߰�
		contactlines.put(currentId, contactlineItem);

		// ���ҽ� ���� res.setStatus(201) = SC_CREATED
		res.setStatus(HttpServletResponse.SC_CREATED);

		// �߰��� ��ü�� ��ȯ
		return contactlineItem;
	}

	// ------REMOVE-----------------------------------

	// contact 1�� ����
	// delete / linecontacts / 1 => id�� 1�� �׸� ����

	@DeleteMapping(value = "/linecontacts/{id}")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) {

		// �ش� id�� ������ 1���� ������
		ContactLine contactline = contactlines.get(Long.valueOf(id));
		if (contactline == null) { // ��Ͽ� ���� ���� �Է��ϸ�
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// ���� ���� ����
		contactlines.remove(Long.valueOf(id));
		return true;
	}

	// ------EDIT-----------------------------------
	// contactline 1�� ����
	// PUT / linecontacts / 1 {"name":"ddd"}
	@PutMapping(value = "/linecontacts/{id}")
	public ContactLine modifyContact(@PathVariable long id, @RequestBody ContactLine contactline,
			HttpServletResponse res) {

		// �ش� id�� ������ 1���� ������
		ContactLine findItem = contactlines.get(Long.valueOf(id));

		if (findItem == null) { // �ش絥���Ͱ� ������ - ��Ͽ� ���� ���� �Է��ϸ�
			// res.setStatus(404) - NOT FOUND : �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// ������ ���� ������ �߰� - �����ϴ� �޸� ���̸� ����
		if (contactline.getName() == null || contactline.getName().isEmpty() || contactline.getPhone() == null
				|| contactline.getPhone().isEmpty() || contactline.getEmail() == null
				|| contactline.getEmail().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		findItem.setName(contactline.getName());
		findItem.setPhone(contactline.getPhone());
		findItem.setEmail(contactline.getEmail());

		return findItem;
	}

}

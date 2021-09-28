package com.git.myworkspace.contact;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.git.myworkspace.lib.TextProcesser;

@RestController
public class ContactController {

	// ContactRepository�� �������� �Ű������� ������ ����
	private ContactRepository repo;

	// ������ ����
	@Autowired
	public ContactController(ContactRepository repo) {
		this.repo = repo;
	}

	// -----GET---------------------------------------------------
	// contact �����ȸ
	// GET / contacts
	@GetMapping(value = "/contacts")
	public List<Contact> getContacts() throws InterruptedException {
		// ��� ������ ��ȸ , ������
		return repo.findAll(Sort.by("id").descending());

	}

	// ----------- paging ó�� -------------
	// ex) GET/contacts/paging?page=0&size=3

	@GetMapping("/contacts/paging")
	public Page<Contact> getContactPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	// -----ADD---------------------------------------------------

	// contact 1�� �߰�
	// POST / contacts �� ����ó �Է�
	@PostMapping(value = "/contacts")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) throws InterruptedException {

		// ������ ���� ����
		if (TextProcesser.isEmptyText(contact.getName()) || TextProcesser.isEmptyText(contact.getPhone())
				|| TextProcesser.isEmptyText(contact.getEmail()) || TextProcesser.isEmptyText(contact.getMemo())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// ��ü ����
		Contact contactItem = Contact.builder().name(contact.getName()).phone(contact.getPhone())
				.email(contact.getEmail()).memo(contact.getMemo()).createdTime(new Date().getTime()).build();

		// insert into contact(...) values(...)
		Contact contactSaved = repo.save(contactItem);

		// ���ҽ� ����
		res.setStatus(HttpServletResponse.SC_CREATED);

		// �߰��� ��ü�� ��ȯ
		return contactSaved;

	}

	// ------REMOVE-----------------------------------

	// contact 1�� ����
	// delete / contact /1 -> id�� 1�� �׸��� �����ض�
	// id���� path variable��
	@DeleteMapping(value = "/contacts/{id}")
	public boolean removeContact(@PathVariable Long id, HttpServletResponse res) throws InterruptedException {

		// �ش� id�� ������ 1���� ������
		Optional<Contact> contact = repo.findById(id);
		if (contact.isEmpty()) { // �ش絥���Ͱ� ������ - ��Ͽ� ���� ���� �Է��ϸ�
			// res.setStatus(404)
			// NOT FOUND : �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// **���� ���� ����
		// delete from contact where id = ?
		repo.deleteById(id);

		return true;
	}

	// ------EDIT-----------------------------------
	// contact 1�� ����
	// PUT/contact/1 {"memo":"..."} , ����ó ������ ���⿡ ������� �߰���
	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res)
			throws InterruptedException {

		// �ش� id�� ������ 1���� ������
		Optional<Contact> contactItem = repo.findById(id);

		if (contactItem.isEmpty()) { // �ش絥���Ͱ� ������ - ��Ͽ� ���� ���� �Է��ϸ�
			// res.setStatus(404)
			// NOT FOUND : �ش� ��ο� ���ҽ��� ����
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// ������ ���� ������ �߰�, ���Է��� �����ϸ� ����
		if (TextProcesser.isEmptyText(contact.getName()) || TextProcesser.isEmptyText(contact.getPhone())
				|| TextProcesser.isEmptyText(contact.getEmail()) || TextProcesser.isEmptyText(contact.getMemo())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Contact contactToSave = contactItem.get();

		// �����͸� ����
		contactItem.get().setName(contact.getName());
		contactItem.get().setPhone(contact.getPhone());
		contactItem.get().setEmail(contact.getEmail());
		contactItem.get().setMemo(contact.getMemo());

		Contact contactSaved = repo.save(contactToSave);

		return contactSaved;
	}

}

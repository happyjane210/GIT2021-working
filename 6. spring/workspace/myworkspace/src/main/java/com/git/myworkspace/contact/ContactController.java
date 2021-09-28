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

	// ContactRepository를 생성자의 매개변수로 의존성 주입
	private ContactRepository repo;

	// 의존성 주입
	@Autowired
	public ContactController(ContactRepository repo) {
		this.repo = repo;
	}

	// -----GET---------------------------------------------------
	// contact 목록조회
	// GET / contacts
	@GetMapping(value = "/contacts")
	public List<Contact> getContacts() throws InterruptedException {
		// 모든 데이터 조회 , 역정렬
		return repo.findAll(Sort.by("id").descending());

	}

	// ----------- paging 처리 -------------
	// ex) GET/contacts/paging?page=0&size=3

	@GetMapping("/contacts/paging")
	public Page<Contact> getContactPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	// -----ADD---------------------------------------------------

	// contact 1건 추가
	// POST / contacts 새 연락처 입력
	@PostMapping(value = "/contacts")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) throws InterruptedException {

		// 데이터 검증 로직
		if (TextProcesser.isEmptyText(contact.getName()) || TextProcesser.isEmptyText(contact.getPhone())
				|| TextProcesser.isEmptyText(contact.getEmail()) || TextProcesser.isEmptyText(contact.getMemo())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 객체 생성
		Contact contactItem = Contact.builder().name(contact.getName()).phone(contact.getPhone())
				.email(contact.getEmail()).memo(contact.getMemo()).createdTime(new Date().getTime()).build();

		// insert into contact(...) values(...)
		Contact contactSaved = repo.save(contactItem);

		// 리소스 생성
		res.setStatus(HttpServletResponse.SC_CREATED);

		// 추가된 객체를 반환
		return contactSaved;

	}

	// ------REMOVE-----------------------------------

	// contact 1건 삭제
	// delete / contact /1 -> id가 1인 항목을 삭제해라
	// id값이 path variable로
	@DeleteMapping(value = "/contacts/{id}")
	public boolean removeContact(@PathVariable Long id, HttpServletResponse res) throws InterruptedException {

		// 해당 id의 데이터 1건을 가져옴
		Optional<Contact> contact = repo.findById(id);
		if (contact.isEmpty()) { // 해당데이터가 없으면 - 목록에 없는 숫자 입력하면
			// res.setStatus(404)
			// NOT FOUND : 해당 경로에 리소스가 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// **실제 삭제 수행
		// delete from contact where id = ?
		repo.deleteById(id);

		return true;
	}

	// ------EDIT-----------------------------------
	// contact 1건 수정
	// PUT/contact/1 {"memo":"..."} , 연락처 관리는 여기에 수정목록 추가함
	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res)
			throws InterruptedException {

		// 해당 id의 데이터 1건을 가져옴
		Optional<Contact> contactItem = repo.findById(id);

		if (contactItem.isEmpty()) { // 해당데이터가 없으면 - 목록에 없는 숫자 입력하면
			// res.setStatus(404)
			// NOT FOUND : 해당 경로에 리소스가 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 데이터 검증 로직을 추가, 빈값입력후 수정하면 에러
		if (TextProcesser.isEmptyText(contact.getName()) || TextProcesser.isEmptyText(contact.getPhone())
				|| TextProcesser.isEmptyText(contact.getEmail()) || TextProcesser.isEmptyText(contact.getMemo())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Contact contactToSave = contactItem.get();

		// 데이터를 변경
		contactItem.get().setName(contact.getName());
		contactItem.get().setPhone(contact.getPhone());
		contactItem.get().setEmail(contact.getEmail());
		contactItem.get().setMemo(contact.getMemo());

		Contact contactSaved = repo.save(contactToSave);

		return contactSaved;
	}

}

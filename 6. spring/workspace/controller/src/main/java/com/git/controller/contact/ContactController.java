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

	// 정렬 Map, 위에서부터 쌓이게
	private SortedMap<Long, Contact> contacts = Collections
			.synchronizedSortedMap(new TreeMap<Long, Contact>(Collections.reverseOrder()));

	// id 값 생성에 사용할 변수
	private AtomicLong maxId = new AtomicLong();

	// -----GET---------------------------------------------------
	// contact 목록조회
	// GET / contacts
	@GetMapping(value = "/contacts")
	public List<Contact> getContacts() {
		return new ArrayList<Contact>(contacts.values());

	}

	// -----ADD---------------------------------------------------

	// contact 1건 추가
	// POST / contacts 새 연락처 입력
	@PostMapping(value = "/contacts")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) {
		// 데이터 검증 로직
		if (contact.getName() == null || contact.getName().isEmpty() || contact.getPhone() == null
				|| contact.getPhone().isEmpty() || contact.getEmail() == null || contact.getEmail().isEmpty()
				|| contact.getMemo() == null || contact.getMemo().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// id값을 생성
		Long currentId = maxId.incrementAndGet();
		// 입력받은 데이터로 contact객체를 생성
		// id값과 생성일시는 서버에서 생성한 것으로 처리함
		// html 테그가 있으면 날려버림(script에서 문제가 발생함)
		@SuppressWarnings("deprecation")
		Contact contactItme = Contact.builder().id(currentId)
				.name(contact.getName().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.phone(contact.getPhone().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.email(contact.getEmail().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.memo(contact.getMemo().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.createdTime(new Date().getTime()).build();

		// contact 목록객체 추가
		contacts.put(currentId, contactItme);
		// 리소스 생성됨
		// res.setStatus(201) = SC_CREATED
		res.setStatus(HttpServletResponse.SC_CREATED);
		// 추가된 객체를 반환
		return contactItme;

	}

	// ------REMOVE-----------------------------------

	// contact 1건 삭제
	// delete / contact /1 -> id가 1인 항목을 삭제해라
	// id값이 path variable로
	@DeleteMapping(value = "/contacts/{id}")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) {

		// 해당 id의 데이터 1건을 가져옴
		Contact contact = contacts.get(Long.valueOf(id));
		if (contact == null) { // 해당데이터가 없으면 - 목록에 없는 숫자 입력하면
			// res.setStatus(404)
			// NOT FOUND : 해당 경로에 리소스가 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// **실제 삭제 수행
		contacts.remove(Long.valueOf(id));
		return true;
	}

	// ------EDIT-----------------------------------
	// contact 1건 수정
	// PUT/contact/1 {"memo":"..."} , 연락처 관리는 여기에 수정목록 추가함
	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable long id, @RequestBody Contact contact, HttpServletResponse res) {

		// 해당 id의 데이터 1건을 가져옴
		Contact findItem = contacts.get(Long.valueOf(id));

		if (findItem == null) { // 해당데이터가 없으면 - 목록에 없는 숫자 입력하면
			// res.setStatus(404)
			// NOT FOUND : 해당 경로에 리소스가 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 데이터 검증 로직을 추가
		// 메모가 빈값이면 에러
		if (contact.getName() == null || contact.getName().isEmpty() || contact.getPhone() == null
				|| contact.getPhone().isEmpty() || contact.getEmail() == null || contact.getEmail().isEmpty()) {
			// 클라이언트에서 메모값 없이 보내거나 빈값으로 보낸것 = 클라이언트 오류 코드 Bad Request (400)
			// res.setStatus(400) SC_BAD_REQUEST = 400

			// Dispatcher Servlet이 생성한 응답객체에 status코드를 넣어줌
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		if (contact.getMemo() == null || contact.getMemo().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 데이터를 변경
		findItem.setName(contact.getName());
		findItem.setPhone(contact.getPhone());
		findItem.setEmail(contact.getEmail());
		findItem.setMemo(contact.getMemo());

		return findItem;
	}

}

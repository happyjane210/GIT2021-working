package com.git.controller.contactLine;

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

	// 정렬 위에서부터 쌓이게
	private SortedMap<Long, ContactLine> contactlines = Collections
			.synchronizedSortedMap(new TreeMap<Long, ContactLine>(Collections.reverseOrder()));

	// id 값 생성에 사용할 변수
	private AtomicLong maxId = new AtomicLong();

	// contact 목록조회
	// GET / linecontacts
	@GetMapping(value = "/linecontacts")
	public List<ContactLine> getContactLines() {
		return new ArrayList<ContactLine>(contactlines.values());
	}

	// -----ADD---------------------------------------------------

	// contactline 1건 추가
	// POST / linecontacts 새연락처 입력
	@PostMapping(value = "/linecontacts")
	public ContactLine addContact(@RequestBody ContactLine contactline, HttpServletResponse res) {

		// 데이터 검증
		if (contactline.getName() == null || contactline.getName().isEmpty() || contactline.getPhone() == null
				|| contactline.getPhone().isEmpty() || contactline.getEmail() == null
				|| contactline.getEmail().isEmpty()) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// id값을 생성
		Long currentId = maxId.incrementAndGet();

		// html 테그가 있으면 날려버림(script에서 문제가 발생함)
		@SuppressWarnings("deprecation")
		ContactLine contactlineItem = ContactLine.builder().id(currentId)
				.name(contactline.getName().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.phone(contactline.getPhone().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.email(contactline.getEmail().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""))
				.createdTime(new Date().getTime()).build();

		// contact 목록객체 추가
		contactlines.put(currentId, contactlineItem);

		// 리소스 생성 res.setStatus(201) = SC_CREATED
		res.setStatus(HttpServletResponse.SC_CREATED);

		// 추가된 객체를 반환
		return contactlineItem;
	}

	// ------REMOVE-----------------------------------

	// contact 1건 삭제
	// delete / linecontacts / 1 => id가 1인 항목 삭제

	@DeleteMapping(value = "/linecontacts/{id}")
	public boolean removeContact(@PathVariable long id, HttpServletResponse res) {

		// 해당 id의 데이터 1건을 가져온
		ContactLine contactline = contactlines.get(Long.valueOf(id));
		if (contactline == null) { // 목록에 없는 숫자 입력하면
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// 실제 삭제 수행
		contactlines.remove(Long.valueOf(id));
		return true;
	}

	// ------EDIT-----------------------------------
	// contactline 1건 수정
	// PUT / linecontacts / 1 {"name":"ddd"}
	@PutMapping(value = "/linecontacts/{id}")
	public ContactLine modifyContact(@PathVariable long id, @RequestBody ContactLine contactline,
			HttpServletResponse res) {

		// 해당 id의 데이터 1건을 가져옴
		ContactLine findItem = contactlines.get(Long.valueOf(id));

		if (findItem == null) { // 해당데이터가 없으면 - 목록에 없는 숫자 입력하면
			// res.setStatus(404) - NOT FOUND : 해당 경로에 리소스가 없음
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 데이터 검증 로직을 추가 - 수정하는 메모가 빈값이면 에러
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
package com.git.myworkspace.photo;

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
public class PhotoController {

	private PhotoRepository repo;

	// 의존성 주입
	@Autowired
	public PhotoController(PhotoRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/photos")
	public List<Photo> getPhotos() throws InterruptedException {
		// repository.findAll();
		// select * from photo;
		// 기본적으로 PK 순정렬 (asc, ascending) 되고있는상황
		// 1, 2, 3///
		// return repo.findAll();

		// id 컬럼 역정렬
		// Sort.by("정렬칼럼").descending() 역정렬
		// Sort.by("정렬칼럼").ascending() 순정렬
		return repo.findAll(Sort.by("id").descending());
	}

	// ----------- paging 처리, 한페이지 2개, 1번째 페이지 ----------

	// 0번째 페이지에, 사진 2개씩
	// ex) GET/photos/paging?page=0&size=2
	@GetMapping("/photos/paging")
	public Page<Photo> getPhotoPaging(@RequestParam int page, @RequestParam int size) {
		// findAll()함수에 Pageable page 객체를 넣음
		// page 객체에 PageRequest.of() 함수로 page, size, Sort.by("id").descending() 를 넣음
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	@PostMapping(value = "/photos")
	public Photo addPhoto(@RequestBody Photo photo, HttpServletResponse res) throws InterruptedException {

		// 타이틀 빈값
		if (TextProcesser.isEmptyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 파일 url이 빈값
		if (TextProcesser.isEmptyText(photo.getPhotoUrl())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 객체 생성
		Photo photoItem = Photo.builder().title(photo.getTitle())
				.description(TextProcesser.getPlainText(photo.getDescription())).photoUrl(photo.getPhotoUrl())
				.fileType(photo.getFileType()).fileName(photo.getFileType()).createdTime(new Date().getTime()).build();

		// insert into photo(...) values(...)
		Photo photoSaved = repo.save(photoItem);

		// 리소스 생성됨
		res.setStatus(HttpServletResponse.SC_CREATED);

		// 추가된 객체를 반환
		return photoSaved;
	}

	@DeleteMapping(value = "/photos/{id}")
	public boolean removePhotos(@PathVariable Long id, HttpServletResponse res) throws InterruptedException {

		// id에 해당하는 객체 가 없으면
		// Optional null-safe 용으로 자바 1.8에 나온 방식
		// 옵셔널 객체를 얻어옴
		Optional<Photo> photo = repo.findById(id);
		if (photo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// 삭제 수행
		// delete from photo where id = ?
		repo.deleteById(id);

		return true;
	}

	@PutMapping(value = "/photos/{id}")
	public Photo modifyPhotos(@PathVariable long id, @RequestBody Photo photo, HttpServletResponse res)
			throws InterruptedException {

		// id에 해당하는 객체가 없으면
		Optional<Photo> photoItem = repo.findById(id);
		if (photoItem.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// 타이틀이 빈값
		if (TextProcesser.isEmptyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 파일 URL이 빈값
		if (TextProcesser.isEmptyText(photo.getPhotoUrl())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		Photo photoToSave = photoItem.get();

		photoItem.get().setTitle(photo.getTitle());
		photoItem.get().setDescription(TextProcesser.getPlainText(photo.getDescription()));
		photoItem.get().setPhotoUrl(photo.getPhotoUrl());
		photoItem.get().setFileType(photo.getFileType());
		photoItem.get().setFileName(photo.getFileType());

		// repository.save(entity)
		// id가 있으면 UPDATE, 없으면 INSERT
		// UPDATE
		// SET title=?, description=?
		// where ...
		Photo photoSaved = repo.save(photoToSave);

		return photoSaved;
	}

	@GetMapping(value = "/photos/{photoId}/comments")
	public PhotoComment addPhotoComment(@PathVariable long photoId, @RequestBody PhotoComment comment) {
		// 값검증...
		// content 가 빈값인지
		// photoId 해당 id 데이터가 있는지 확인

		return null;

	}

}
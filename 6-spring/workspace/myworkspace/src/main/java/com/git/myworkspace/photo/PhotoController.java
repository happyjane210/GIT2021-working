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
	private PhotoCommentRepository cmtRepo;

	// 의존 주입 처리
	@Autowired
	public PhotoController(PhotoRepository repo, PhotoCommentRepository cmtRepo) {
		this.repo = repo;
		this.cmtRepo = cmtRepo;
	}

	@GetMapping(value = "/photos")
	public List<Photo> getPhotos() throws InterruptedException {
		// repository.findAll();
		// select * from photo;
		// 湲곕낯�쟻�쑝濡� PK �닚�젙�젹 (asc, ascending) �릺怨좎엳�뒗�긽�솴
		// 1, 2, 3///
		// return repo.findAll();

		// id 而щ읆 �뿭�젙�젹
		// Sort.by("�젙�젹移쇰읆").descending() �뿭�젙�젹
		// Sort.by("�젙�젹移쇰읆").ascending() �닚�젙�젹
		return repo.findAll(Sort.by("id").descending());
	}

	// ----------- paging 泥섎━, �븳�럹�씠吏� 2媛�, 1踰덉㎏ �럹�씠吏� ----------

	// 0踰덉㎏ �럹�씠吏��뿉, �궗吏� 2媛쒖뵫
	// ex) GET/photos/paging?page=0&size=2
	@GetMapping("/photos/paging")
	public Page<Photo> getPhotoPaging(@RequestParam int page, @RequestParam int size) {
		// findAll()�븿�닔�뿉 Pageable page 媛앹껜瑜� �꽔�쓬
		// page 媛앹껜�뿉 PageRequest.of() �븿�닔濡� page, size, Sort.by("id").descending() 瑜� �꽔�쓬
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	@PostMapping(value = "/photos")
	public Photo addPhoto(@RequestBody Photo photo, HttpServletResponse res) throws InterruptedException {

		// ���씠�� 鍮덇컪
		if (TextProcesser.isEmptyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// �뙆�씪 url�씠 鍮덇컪
		if (TextProcesser.isEmptyText(photo.getPhotoUrl())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// 媛앹껜 �깮�꽦
		Photo photoItem = Photo.builder().title(photo.getTitle())
				.description(TextProcesser.getPlainText(photo.getDescription())).photoUrl(photo.getPhotoUrl())
				.fileType(photo.getFileType()).fileName(photo.getFileType()).createdTime(new Date().getTime()).build();

		// insert into photo(...) values(...)
		Photo photoSaved = repo.save(photoItem);

		// 由ъ냼�뒪 �깮�꽦�맖
		res.setStatus(HttpServletResponse.SC_CREATED);

		// 異붽��맂 媛앹껜瑜� 諛섑솚
		return photoSaved;
	}

	@DeleteMapping(value = "/photos/{id}")
	public boolean removePhotos(@PathVariable Long id, HttpServletResponse res) throws InterruptedException {

		// id�뿉 �빐�떦�븯�뒗 媛앹껜 媛� �뾾�쑝硫�
		// Optional null-safe �슜�쑝濡� �옄諛� 1.8�뿉 �굹�삩 諛⑹떇
		// �샃�뀛�꼸 媛앹껜瑜� �뼸�뼱�샂
		Optional<Photo> photo = repo.findById(id);
		if (photo.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}

		// �궘�젣 �닔�뻾
		// delete from photo where id = ?
		repo.deleteById(id);

		return true;
	}

	@PutMapping(value = "/photos/{id}")
	public Photo modifyPhotos(@PathVariable long id, @RequestBody Photo photo, HttpServletResponse res)
			throws InterruptedException {

		// id�뿉 �빐�떦�븯�뒗 媛앹껜媛� �뾾�쑝硫�
		Optional<Photo> photoItem = repo.findById(id);
		if (photoItem.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// ���씠���씠 鍮덇컪
		if (TextProcesser.isEmptyText(photo.getTitle())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

		// �뙆�씪 URL�씠 鍮덇컪
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
		// id媛� �엳�쑝硫� UPDATE, �뾾�쑝硫� INSERT
		// UPDATE
		// SET title=?, description=?
		// where ...
		Photo photoSaved = repo.save(photoToSave);

		return photoSaved;
	}

	// 포토 하위에 댓글 추가
	@PostMapping(value = "/photos/{photoId}/comments")
	public PhotoComment addPhotoComment(@PathVariable long photoId, @RequestBody PhotoComment comment) {
		// 값 검증
		// content 가 빈값인지
		// photoId 해당 데이터가 있는지 확인 (FK외래키 제약조건으로 처리가능)

		// 상위테이블
		comment.setPhotoId(photoId);
		comment.setCreatedTime(new Date().getTime());
		
		// 저장하고 저장한 객체 리턴
		return cmtRepo.save(comment);

	}
	
	// 포토 하위 댓글 목록 조회
	// GET  /photos/{photoId}/comments
	@GetMapping(value = "/photos/{photoId}/comments")
	public List<PhotoComment> getComments(@PathVariable long photoId) {
		
		// 상위테이블 id로 하위테이블 목록 조회
		// select * from photo_comment where photo_id = :photoId
		return cmtRepo.findByPhotoId(photoId);
	}

	
	
}
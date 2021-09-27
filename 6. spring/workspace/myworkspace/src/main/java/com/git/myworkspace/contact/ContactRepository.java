package com.git.myworkspace.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// contact ���̺� �����ϴ� ��ü

//��Ӱ���
// ContactRepository -> JpaRepository -> PagingAndSortingRepository -> CrudRepository

//JpaRepository ���� ������ ó���� ���� �⺻���� �޼������ ����Ǿ�����
//JpaRepository<Contact, Long>
//           <��ƼƼŸ��, idŸ��>
//��ƼƼ(����Ʈ�����Ͼ� SE, �����Ͱ�ü) == ���̺�(DB, �����Ͱ�ü)

//photo ���̺� ������ �� �ִ� �⺻���� �޼������ ����� �� ����

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}

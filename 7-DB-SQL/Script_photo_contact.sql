-- ��Ű�� ����
create schema myworkspace;

-- ���� ����â���� ����� ��Ű�� ����
-- myworkspace ��� ��Ű�� �������� �۾��� ��.
-- ���� ����, �����ָ�, select * from myworkspace.photo;
-- �Ź� ����� ���� �����������.
set schema 'myworkspace';

-- pk(primary key) : ��Ű
-- ���ϼ��� �ּҼ��� �����ϴ� �÷�
-- ���ϼ�: ��ü ���̺� �����Ϳ��� ������ ���� ����(�����id, �̸���, �ֹε�Ϲ�ȣ)
-- �ּҼ�: �ּ��� �÷� ������ ũ��� �����Ǿ�����
create table photo (
	-- �÷��� �÷�Ÿ�� [�׿� �ɼ�]
	-- generated by default as identity : �ڵ� �����Ǵ� �� (1,2,3,4...)
	id int8 generated by default as identity, 
	created_time int8 not null, 
	-- description varchar(255) : �������� ���ڿ� 255 ����Ʈ
	description varchar(255), 
	file_name varchar(255), 
	file_type varchar(255), 
	photo_url TEXT, 
	title varchar(255), 
	primary key (id)
)


--//------------------------------------------------------



--(CREATE)
-- �����͸� 1�� �߰� 
-- insert into ���̺���(�÷�1, �÷�2...) values(��1, ��2...)
insert into photo(created_time, title, description)
-- > ������ �߰�
values(1, 'test', 'DBeaver');



--//------------------------------------------------------



--(READ)
-- select �÷���� from ���̺���; *�� ��ü
-- ���̺� �����͸� ��ü ��ȸ

select * from photo;
-- Ư�� �÷�(����ĭ)�� ��ȸ
select id, title from photo;
-- photo ���̺� ��ü ��� ��ȸ
select 
	id, 
	created_time, 
	description, 
	file_name, 
	file_type, 
	photo_url, 
	title 
from photo;



-- clustered index�� ���� : ������ ���� ������ �ε��� ������ �°�
cluster photo using photo_pkey;



-- SQL ������ (��||��)���� : ORDER BY
-- Spring ���ɾ� :    repo.findAll(Sort.by("id").descending());  &&   POSTMAN GET �����ȸ
-- ORDER BY �÷��� ���Ĺ�� (desc, asc)  - ����, ����
-- id 6->1 ����
select * from photo order by id desc;  
-- title ����(������) , id ���� 1->6
select * from photo order by title desc, id asc;



-- 1. ����¡ ��ȸ

--  1.1 ������ ���� ��ȸ  - �ڵ�����
-- POSTMAN GET /photos/paging?page=2&size=2   
-- ������ ��ȸ�ϸ� �ڵ����� ��ü id ī��Ʈ�ϴ� ���ɾ� ����  select count( )
select count(*) from photo;
select count(id) from photo;
-- �Ѵ� ������� 6 �ε� count(*) �� �� ���� ��

-- 1.2 ������ (��||��) ���� ��ȸ  - �ڵ����� ���ɾ� ����
--  limit : ǥ���� ������ ����?  offset : �ǳ� �� ������ ����?

-- ������ 6, 5   , (offset 0*2 ��������)  - �������ϰ�, ������ 2�� ������
select * from photo 
order by id desc 
limit 2;

-- ������ 4, 3  ,  offset 2  - 2�� �ǳʶٰ� 2�� ������
select * from photo 
order by id desc 
limit 2 offset 1*2;

-- ������ 2, 1  ,  offset 4  - 4�� �ǳʶٰ� 2�� ������
select * from photo 
order by id desc 
limit 2 offset 2*2;



--//------------------------------------------------------



-- (UPDATE)
update photo set 
	created_time=1, 
	description='���ο� ����', 
	file_name='���ϸ�', 
	file_type='...', 
	photo_url='...', 
	title='���ο�����' 
where id=5;



--//------------------------------------------------------



--(DELETE)
-- Ư�� ������ ����
-- **** where ������ ������ ��ü �����Ͱ� ������, ������ ��
delete from photo where id = 16;

-- ���̺� ������ �ʱ�ȭ, �α� log �� ��� �ȵ�, �����Ұ�
truncate table photo restart identity;






--//=========================== CONTACT =======================================


------------(CREATE)-------------------------
insert into contact (created_time, email, memo, "name" , phone) 
values (1, 'dbeaver@gmail.com', 'This is DBeaver', 'DBeaver', '000-0000-0000');




------------(READ)-------------------------
select * from contact;

select
	id, 
	created_time,
	email,
	memo,
	"name",
	phone
from contact;

select "name" from contact;


-- clustered index�� ���� : ������ ���� ������ �ε��� ������ �°�
cluster contact using contact_pkey;



-- 1. ����¡ ��ȸ

--  1.1 ������ ���� ��ȸ  - �ڵ�����
-- POSTMAN GET /contact/paging?page=0&size=3
select count(*) from contact;
select count(id) from contact;
-- �Ѵ� ������� 9

-- 1.2 ������ (��||��) ���� ��ȸ  - �ڵ����� ���ɾ� ����
--  limit : ǥ���� ������ ����?  offset : �ǳ� �� ������ ����?

-- ������ 6, 5   , (offset 0*2 ��������)  - �������ϰ�, ������ 2�� ������
select * from contact 
order by id desc 
limit 3;

-- ������ 4, 3  ,  offset 2  - 2�� �ǳʶٰ� 2�� ������
select * from contact 
order by id desc 
limit 3 offset 1*3;

-- ������ 2, 1  ,  offset 4  - 4�� �ǳʶٰ� 2�� ������
select * from contact 
order by id desc 
limit 3 offset 2*3;




------------(UPDATE)-------------------------
update contact set
	created_time=2,
	email='DDD@naver.com',
	memo='UPDATE',
	"name"='UPDATE',
	phone='xxx-xxxx-xxxx'
where id = 3;




------------(DELETE)-------------------------
-- **** where ������ ������ ��ü �����Ͱ� ������, ������ ��
delete from contact where id = 15;

-- ������ ������, ���̺� ������ �ʱ�ȭ, �α� log �� ��� �ȵ�, �����Ұ�
truncate table contact restart identity;




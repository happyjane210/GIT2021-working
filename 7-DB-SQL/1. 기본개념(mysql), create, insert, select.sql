-- ��Ű�� ����
create schema myworkspace;

-- ���� ����â���� ����� ��Ű�� ����
use myworkspace;

create table photo (
	id bigint not null auto_increment, 
	created_time bigint not null, 
	-- description varchar(255) : �������� ���ڿ� 255 ����Ʈ
	description varchar(255), 
	file_name varchar(255), 
	file_type varchar(255), 
	photo_url BLOB, 
	title varchar(255), 
	primary key (id) engine=InnoDB
)





-- �����͸� 1�� �߰�
-- insert into ���̺��(�÷�1, �÷�2...) values(��1, ��2...)
insert into photo(created_time, photo_url, title)
-- > ������ �߰�
values(1, '..dd.', 'test');



-- select �÷���� from ���̺��; *�� ��ü

-- ���̺� �����͸� ��ü ��ȸ
select * from photo;
-- Ư�� �÷�(����ĭ)�� ��ȸ
select id, photo_url , title from photo;

-- Ư�� ������ ��ȸ
-- selec �÷���� from ���̺�� where ���ǽ� -> (equal���ǽ�)
-- equal ���ǽ�: �÷��� = ��   / map.get(1) �̶� ����

 -- id�� 1�� ���� ������
select * from photo where id = 1;
-- id�� 1���� ũ�ų� ���� ���� ������
select * from photo where id >= 1;

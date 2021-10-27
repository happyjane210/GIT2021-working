set schema 'myworkspace';

select * from photo order by id asc;
select * from photo_comment c ;


-- inner join
-- ���̺� ������ �´� ���ڵ峢�� �����Ͽ� ������
-- ������ ���� ���ʿ� �ִ� ���̺�
select p.id, p.title, c."content" 
from photo p inner join photo_comment c on p.id = c.photo_id;

-- ���� (���� photo) ���̺��� ��� ����
select p.id, p.title, c."content" 
from photo p left join photo_comment c on p.id = c.photo_id;



----��۸� ��ȸ-----
select * from photo_comment where photo_id = 1;


----id�� ��� ���� ��ȸ-----
select count(*) from photo_comment where photo_id = 3;

----�������̺�� ��۰��� �Բ�----- inline subquery
select p.id, p.created_time, p.title,
(select count(*) from photo_comment cmt where photo_id = p.id) comment_cnt
from photo p order by id asc;


truncate table photo_comment restart identity;
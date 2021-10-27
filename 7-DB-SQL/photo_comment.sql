set schema 'myworkspace';

select * from photo order by id asc;
select * from photo_comment c ;


-- inner join
-- 테이블간 조건이 맞는 레코드끼리 결합하여 보여줌
-- 기준은 가장 왼쪽에 있는 테이블
select p.id, p.title, c."content" 
from photo p inner join photo_comment c on p.id = c.photo_id;

-- 왼쪽 (상위 photo) 테이블이 모두 나옴
select p.id, p.title, c."content" 
from photo p left join photo_comment c on p.id = c.photo_id;



----댓글만 조회-----
select * from photo_comment where photo_id = 1;


----id별 댓글 개수 조회-----
select count(*) from photo_comment where photo_id = 3;

----상위테이블과 댓글개수 함께----- inline subquery
select p.id, p.created_time, p.title,
(select count(*) from photo_comment cmt where photo_id = p.id) comment_cnt
from photo p order by id asc;


truncate table photo_comment restart identity;
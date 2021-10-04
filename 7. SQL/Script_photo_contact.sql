-- 스키마 생성
create schema myworkspace;

-- 현재 쿼리창에서 사용할 스키마 선택
-- myworkspace 라는 스키마 공간에서 작업을 함.
-- 범위 지정, 안해주면, select * from myworkspace.photo;
-- 매번 점찍고 범위 지정해줘야함.
set schema 'myworkspace';

-- pk(primary key) : 주키
-- 유일성과 최소성을 보장하는 컬럼
-- 유일성: 전체 테이블 데이터에서 유일한 값을 가짐(사용자id, 이메일, 주민등록번호)
-- 최소성: 최소의 컬럼 개수와 크기로 구성되어있음
create table photo (
	-- 컬럼명 컬럼타입 [그외 옵션]
	-- generated by default as identity : 자동 증가되는 값 (1,2,3,4...)
	id int8 generated by default as identity, 
	created_time int8 not null, 
	-- description varchar(255) : 가변길이 문자열 255 바이트
	description varchar(255), 
	file_name varchar(255), 
	file_type varchar(255), 
	photo_url TEXT, 
	title varchar(255), 
	primary key (id)
)


--//------------------------------------------------------



--(CREATE)
-- 데이터를 1건 추가 
-- insert into 테이블명(컬럼1, 컬럼2...) values(값1, 값2...)
insert into photo(created_time, title, description)
-- > 데이터 추가
values(1, 'test', 'DBeaver');



--//------------------------------------------------------



--(READ)
-- select 컬럼목록 from 테이블명; *는 전체
-- 테이블 데이터를 전체 조회

select * from photo;
-- 특정 컬럼(세로칸)만 조회
select id, title from photo;
-- photo 테이블 전체 목록 조회
select 
	id, 
	created_time, 
	description, 
	file_name, 
	file_type, 
	photo_url, 
	title 
from photo;



-- clustered index로 변경 : 데이터 정렬 순서를 인덱스 순서에 맞게
cluster photo using photo_pkey;



-- SQL 데이터 (역||순)정렬 : ORDER BY
-- Spring 명령어 :    repo.findAll(Sort.by("id").descending());  &&   POSTMAN GET 목록조회
-- ORDER BY 컬럼명 정렬방법 (desc, asc)  - 역순, 정순
-- id 6->1 정렬
select * from photo order by id desc;  
-- title 역순(ㄷㄴㄱ) , id 정순 1->6
select * from photo order by title desc, id asc;



-- 1. 페이징 조회

--  1.1 데이터 개수 조회  - 자동생성
-- POSTMAN GET /photos/paging?page=2&size=2   
-- 페이지 조회하면 자동으로 전체 id 카운트하는 명령어 실행  select count( )
select count(*) from photo;
select count(id) from photo;
-- 둘다 결과값은 6 인데 count(*) 를 더 많이 씀

-- 1.2 데이터 (역||순) 정렬 조회  - 자동으로 명령어 생성
--  limit : 표시할 데이터 개수?  offset : 건너 뛸 데이터 개수?

-- 데이터 6, 5   , (offset 0*2 생략가능)  - 역정렬하고, 데이터 2개 가져와
select * from photo 
order by id desc 
limit 2;

-- 데이터 4, 3  ,  offset 2  - 2개 건너뛰고 2개 가져와
select * from photo 
order by id desc 
limit 2 offset 1*2;

-- 데이터 2, 1  ,  offset 4  - 4개 건너뛰고 2개 가져와
select * from photo 
order by id desc 
limit 2 offset 2*2;



--//------------------------------------------------------



-- (UPDATE)
update photo set 
	created_time=1, 
	description='새로운 설명', 
	file_name='파일명', 
	file_type='...', 
	photo_url='...', 
	title='새로운제목' 
where id=5;



--//------------------------------------------------------



--(DELETE)
-- 특정 데이터 삭제
-- **** where 조건이 없으면 전체 데이터가 삭제됨, 조심할 것
delete from photo where id = 16;

-- 테이블 데이터 초기화, 로그 log 에 기록 안됨, 복구불가
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


-- clustered index로 변경 : 데이터 정렬 순서를 인덱스 순서에 맞게
cluster contact using contact_pkey;



-- 1. 페이징 조회

--  1.1 데이터 개수 조회  - 자동생성
-- POSTMAN GET /contact/paging?page=0&size=3
select count(*) from contact;
select count(id) from contact;
-- 둘다 결과값은 9

-- 1.2 데이터 (역||순) 정렬 조회  - 자동으로 명령어 생성
--  limit : 표시할 데이터 개수?  offset : 건너 뛸 데이터 개수?

-- 데이터 6, 5   , (offset 0*2 생략가능)  - 역정렬하고, 데이터 2개 가져와
select * from contact 
order by id desc 
limit 3;

-- 데이터 4, 3  ,  offset 2  - 2개 건너뛰고 2개 가져와
select * from contact 
order by id desc 
limit 3 offset 1*3;

-- 데이터 2, 1  ,  offset 4  - 4개 건너뛰고 2개 가져와
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
-- **** where 조건이 없으면 전체 데이터가 삭제됨, 조심할 것
delete from contact where id = 15;

-- 데이터 날리기, 테이블 데이터 초기화, 로그 log 에 기록 안됨, 복구불가
truncate table contact restart identity;





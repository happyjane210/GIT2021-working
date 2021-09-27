-- 스키마 생성
create schema myworkspace;

-- 현재 쿼리창에서 사용할 스키마 선택
use myworkspace;

create table photo (
	id bigint not null auto_increment, 
	created_time bigint not null, 
	-- description varchar(255) : 가변길이 문자열 255 바이트
	description varchar(255), 
	file_name varchar(255), 
	file_type varchar(255), 
	photo_url BLOB, 
	title varchar(255), 
	primary key (id) engine=InnoDB
)





-- 데이터를 1건 추가
-- insert into 테이블명(컬럼1, 컬럼2...) values(값1, 값2...)
insert into photo(created_time, photo_url, title)
-- > 데이터 추가
values(1, '..dd.', 'test');



-- select 컬럼목록 from 테이블명; *는 전체

-- 테이블 데이터를 전체 조회
select * from photo;
-- 특정 컬럼(세로칸)만 조회
select id, photo_url , title from photo;

-- 특정 데이터 조회
-- selec 컬럼목록 from 테이블명 where 조건식 -> (equal조건식)
-- equal 조건식: 컬럼명 = 값   / map.get(1) 이랑 같음

 -- id가 1인 행의 데이터
select * from photo where id = 1;
-- id가 1보다 크거나 같은 행의 데이터
select * from photo where id >= 1;

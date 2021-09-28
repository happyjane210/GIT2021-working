-- 2021-09-28 월

-- 기본적으로 Primary Key (PK) 로 정렬
-- Clusted Index 로 정렬
-- Index(목치) : 특정 칼럼 또는 칼럼들이 별도의 데이터 공간을 가지고 있고, 실제 데이터를 가리키고 있는 자료구조
select * from photo;


-- DDL (Data Definition Language)
-- CREATE, TRUNCATE, ALTER .. 
-- 데이터구조를 정의하거나 변경하는 언어

-- DML (Data Manipulation Languege)
-- 데이터 조작 언어
-- INSERT, UPDATE, DELETE


-- ** delete 와 truncate 다른점
-- delete 명령어는 트랜잭션(transaction, 논리적인 처리단위) 로그 log 에 기록됨
-- truncate 는 테이블 구조 초기와,  로그 log 에 기록 안됨, 복구불가

-- 전체 테이블 데이터 삭제 및 구조 초기화
truncate table photo;

-- 자동증가값 초기화  restart identity
truncate table photo restart identity;
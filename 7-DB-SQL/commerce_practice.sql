-- 스키마 생성
create schema commerce;

-- 현재 쿼리창에서 사용할 스키마 선택
-- myworkspace 라는 스키마 공간에서 작업을 함.
-- 범위 지정, 안해주면, select * from myworkspace.photo;
-- 매번 점찍고 범위 지정해줘야함.
set schema 'commerce';


-- Foreign Key (외래)
-- 다른 테이블의 PK를 참조하고 있는 컬럼 제약조건 (Constraint)
-- 외래키 컬럼에는 참조하고 있는 테이블에 있는 PK 값만 들어갈 수 있다.
-- 다른 값이 들어가면 제약조건 에러발생 (pk에 없는 id값)


-- product테이블에 값을 넣어줌 - 판매제품 데이터 등록
INSERT INTO commerce.product (price,product_code,product_name,sales_product_id) VALUES
	 (10000,'P00000001','마스크 50매',NULL),
	 (7000,'P00000002','물티슈 80매',NULL),
	 (5000,'P00000003','손소독제 500ml',NULL),
	(14000,'P00000004','콜롬비아 200g',NULL);

	
	
	
-- !!! 테이블 내용 삭제 !!!
truncate table commerce_order restart identity;	
truncate table commerce_order_detail restart identity;
truncate table commerce.product restart identity;




--주문 기본정보 1건과, 주문제품정보 2건을 저장
insert into commerce_order...;
insert into commerce_order_detail...;
insert into commerce_order_detail...;

	



-- 판매 제품 조회
select * from commerce.product;

-- 주문정보 조회
select * from commerce_order;

-- 선택 주문한 제품 조회
select * from commerce_order_detail;

	

	
-- id값 기준으로 정렬함 앞쪽:주문정보  뒷쪽 :주문제품정보
-- on co.id = cod.commerce_order_id; 두 아이디는 동일한 아이디임
select * 
from commerce_order co 
		join commerce_order_detail cod
			on co.id = cod.commerce_order_id;
		

-- 제품별로 매출 가격 합계조회
select p.id, sum(cod.quantity * cod.unit_price) total 
from product p 
		join commerce_order_detail cod on p.id = cod.product_id 
group by p.id ;


-- 관리자 - 제품이름별로 매출 가격 합계조회
select p.id, p.product_name, total.total
from product p join
(select p.id, sum(cod.quantity * cod.unit_price) total 
from product p 
		join commerce_order_detail cod on p.id = cod.product_id 
group by p.id) total on p.id = total.id;



	
	
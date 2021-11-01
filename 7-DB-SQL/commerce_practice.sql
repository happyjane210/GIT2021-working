-- ��Ű�� ����
create schema commerce;

-- ���� ����â���� ����� ��Ű�� ����
-- myworkspace ��� ��Ű�� �������� �۾��� ��.
-- ���� ����, �����ָ�, select * from myworkspace.photo;
-- �Ź� ����� ���� �����������.
set schema 'commerce';


-- Foreign Key (�ܷ�)
-- �ٸ� ���̺��� PK�� �����ϰ� �ִ� �÷� �������� (Constraint)
-- �ܷ�Ű �÷����� �����ϰ� �ִ� ���̺� �ִ� PK ���� �� �� �ִ�.
-- �ٸ� ���� ���� �������� �����߻� (pk�� ���� id��)


-- product���̺� ���� �־��� - �Ǹ���ǰ ������ ���
INSERT INTO commerce.product (price,product_code,product_name,sales_product_id) VALUES
	 (10000,'P00000001','����ũ 50��',NULL),
	 (7000,'P00000002','��Ƽ�� 80��',NULL),
	 (5000,'P00000003','�ռҵ��� 500ml',NULL),
	(14000,'P00000004','�ݷҺ�� 200g',NULL);

	
	
	
-- !!! ���̺� ���� ���� !!!
truncate table commerce_order restart identity;	
truncate table commerce_order_detail restart identity;
truncate table commerce.product restart identity;




--�ֹ� �⺻���� 1�ǰ�, �ֹ���ǰ���� 2���� ����
insert into commerce_order...;
insert into commerce_order_detail...;
insert into commerce_order_detail...;

	



-- �Ǹ� ��ǰ ��ȸ
select * from commerce.product;

-- �ֹ����� ��ȸ
select * from commerce_order;

-- ���� �ֹ��� ��ǰ ��ȸ
select * from commerce_order_detail;

	

	
-- id�� �������� ������ ����:�ֹ�����  ���� :�ֹ���ǰ����
-- on co.id = cod.commerce_order_id; �� ���̵�� ������ ���̵���
select * 
from commerce_order co 
		join commerce_order_detail cod
			on co.id = cod.commerce_order_id;
		

-- ��ǰ���� ���� ���� �հ���ȸ
select p.id, sum(cod.quantity * cod.unit_price) total 
from product p 
		join commerce_order_detail cod on p.id = cod.product_id 
group by p.id ;


-- ������ - ��ǰ�̸����� ���� ���� �հ���ȸ
select p.id, p.product_name, total.total
from product p join
(select p.id, sum(cod.quantity * cod.unit_price) total 
from product p 
		join commerce_order_detail cod on p.id = cod.product_id 
group by p.id) total on p.id = total.id;



	
	
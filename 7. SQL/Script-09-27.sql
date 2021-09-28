-- 2021-09-28 ��



-- DDL (Data Definition Language)
-- CREATE, TRUNCATE, ALTER .. 
-- �����ͱ����� �����ϰų� �����ϴ� ���

-- DML (Data Manipulation Languege)
-- ������ ���� ���
-- INSERT, UPDATE, DELETE


-- ** delete �� truncate �ٸ���
-- delete ��ɾ�� Ʈ�����(transaction, ������ ó������) �α� log �� ��ϵ�
-- truncate �� ���̺� ���� �ʱ�ȭ,  �α� log �� ��� �ȵ�, �����Ұ�



-- ��ü ���̺� ������ ���� �� ���� �ʱ�ȭ
truncate table photo;

-- �ڵ������� �ʱ�ȭ  restart identity
truncate table photo restart identity;



-- �⺻������ Primary Key (PK) �� ����
-- Clusted Index �� ����
-- Index(��ġ) : Ư�� Į�� �Ǵ� Į������ ������ ������ ������ ������ �ְ�, ���� �����͸� ����Ű�� �ִ� �ڷᱸ��
select * from photo;
select * from photo where id =2;


-- clstered index �� ���� : ������ ���� ������ �ε��� ������ �°�
cluster photo using photo_pkey;




-- 2021-09-28 ��

-- �⺻������ Primary Key (PK) �� ����
-- Clusted Index �� ����
-- Index(��ġ) : Ư�� Į�� �Ǵ� Į������ ������ ������ ������ ������ �ְ�, ���� �����͸� ����Ű�� �ִ� �ڷᱸ��
select * from photo;


-- DDL (Data Definition Language)
-- CREATE, TRUNCATE, ALTER .. 
-- �����ͱ����� �����ϰų� �����ϴ� ���

-- DML (Data Manipulation Languege)
-- ������ ���� ���
-- INSERT, UPDATE, DELETE


-- ** delete �� truncate �ٸ���
-- delete ��ɾ�� Ʈ�����(transaction, ������ ó������) �α� log �� ��ϵ�
-- truncate �� ���̺� ���� �ʱ��,  �α� log �� ��� �ȵ�, �����Ұ�

-- ��ü ���̺� ������ ���� �� ���� �ʱ�ȭ
truncate table photo;

-- �ڵ������� �ʱ�ȭ  restart identity
truncate table photo restart identity;
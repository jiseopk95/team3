�� /WEB-INF/doc/dbms/member_c.sql
-------------------------------------------------------------------------------------
- ���̺� ����
-- member �������� FK�� ����� blog ���̺� ���� �����մϴ�.
DROP TABLE contents;
DROP TABLE member;

CREATE TABLE member (
  mno       NUMBER(6) NOT NULL, -- ȸ�� ��ȣ, ���ڵ带 �����ϴ� �÷� 
  id           VARCHAR(20)   NOT NULL UNIQUE, -- ���̵�, �ߺ� �ȵ�, ���ڵ带 ���� 
  passwd    VARCHAR(60)   NOT NULL, -- �н�����, ������ ����
  mname    VARCHAR(20)   NOT NULL, -- ����, �ѱ� 10�� ���� ����
  tel          VARCHAR(14)   NOT NULL, -- ��ȭ��ȣ
  zipcode   VARCHAR(5)        NULL, -- ������ȣ, 12345
  address1  VARCHAR(80)       NULL, -- �ּ� 1
  address2  VARCHAR(50)       NULL, -- �ּ� 2
  mdate     DATE             NOT NULL, -- ������    
  PRIMARY KEY (mno)             -- �ѹ� ��ϵ� ���� �ߺ� �ȵ�
);

COMMENT ON TABLE MEMBER is 'ȸ��';
COMMENT ON COLUMN MEMBER.MNO is 'ȸ�� ��ȣ';
COMMENT ON COLUMN MEMBER.ID is '���̵�';
COMMENT ON COLUMN MEMBER.PASSWD is '�н�����';
COMMENT ON COLUMN MEMBER.MNAME is '����';
COMMENT ON COLUMN MEMBER.TEL is '��ȭ��ȣ';
COMMENT ON COLUMN MEMBER.ZIPCODE is '������ȣ';
COMMENT ON COLUMN MEMBER.ADDRESS1 is '�ּ�1';
COMMENT ON COLUMN MEMBER.ADDRESS2 is '�ּ�2';
COMMENT ON COLUMN MEMBER.MDATE is '������';


1. ���

1) id �ߺ� Ȯ��
SELECT COUNT(id) as cnt
FROM member
WHERE id='user1';

 cnt
 ---
   0   �� �ߺ� ���� ����.
   
2) ���
- �Ϸù�ȣ ���� ��������
- MAX(mno): mno �÷��� ���߿� ���� ū ���� ����
- ���ڵ尡 ������ MAX �Լ��� ����� NULL�̵˴ϴ�.
SELECT MAX(mno) as mno FROM member;
 MNO
 ------
 NULL

 - NULL ���� ��Ģ������ ����� NULL�̵˴ϴ�. �ǹ� ����.
SELECT MAX(mno)+1 as mno FROM member;
 MNO
 ------
 NULL

 - NVL(��, ��ȯ�� ��): ���� NULL�̸� ��ȯ�� ������ ����, 
   NULL�� 0���� ����
SELECT NVL(MAX(mno), 0) as mno FROM member;
 MNO
 ------
   0
   
- NULL�� ó���ϸ鼭 ���ο� �ִ밪 ���� SQL
SELECT NVL(MAX(mno), 0)+1 as mno FROM member;
 MNO
 ------
   1

-- ���� ȸ�� ����
INSERT INTO member(mno, id, passwd, mname, tel, zipcode, address1, address2, mdate)
VALUES ((SELECT NVL(MAX(mno), 0)+1 as mno FROM member),
'user1', '1234', '�մ���', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);

INSERT INTO member(mno, id, passwd, mname, tel, zipcode, address1, address2, mdate)
VALUES ((SELECT NVL(MAX(mno), 0)+1 as mno FROM member),
'user2', '1234', '�մ���', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);

INSERT INTO member(mno, id, passwd, mname, tel, zipcode, address1, address2, mdate)
VALUES ((SELECT NVL(MAX(mno), 0)+1 as mno FROM member),
'user3', '1234', '�մ���', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);

-- �׷캰 ����ȸ�� ����
INSERT INTO member(mno, id, passwd, mname, tel, zipcode, address1, address2, mdate)
VALUES ((SELECT NVL(MAX(mno), 0)+1 as mno FROM member),
'team1', '1234', '������', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);

INSERT INTO member(mno, id, passwd, mname, tel, zipcode, address1, address2, mdate)
VALUES ((SELECT NVL(MAX(mno), 0)+1 as mno FROM member),
'team2', '1234', '���ۺ�������', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);

INSERT INTO member(mno, id, passwd, mname, tel, zipcode, address1, address2, mdate)
VALUES ((SELECT NVL(MAX(mno), 0)+1 as mno FROM member),
'team3', '1234', '��������', '000-0000-0000', '12345', '����� ���α�', '��ö��', sysdate);


2. ���
- �˻��� ���� �ʴ� ���, ��ü ��� ���

SELECT mno, id, passwd, mname, tel, zipcode, address1, address2, mdate
FROM member
ORDER BY mno ASC;

 mno id    passwd mname tel           zipcode address1 address2 mdate
 --- ----- ------ ----- ------------- ------- -------- -------- ---------------------
   1 user1 1234   �մ���   000-0000-0000 12345   ����� ���α�  ��ö��      2016-01-12 12:48:52.0
   2 user2 1234   �մ���   000-0000-0000 12345   ����� ���α�  ��ö��      2016-01-12 12:48:53.0
   3 user3 1234   �մ���   000-0000-0000 12345   ����� ���α�  ��ö��      2016-01-12 12:48:54.0

   
3. ��ȸ

1) user1 ��� ���� ����
SELECT mno, id, passwd, mname, tel, zipcode, address1, address2, mdate
FROM member
WHERE mno = 1;

 MNO ID    PASSWD MNAME TEL           ZIPCODE ADDRESS1 ADDRESS2 MDATE
 --- ----- ------ ----- ------------- ------- -------- -------- ---------------------
   1 user1 1234   �մ���   000-0000-0000 12345   ����� ���α�  ��ö��      2016-05-24 12:59:35.0

SELECT mno, id, passwd, mname, tel, zipcode, address1, address2, mdate
FROM member
WHERE id = 'user1';

 MNO ID    PASSWD MNAME TEL           ZIPCODE ADDRESS1 ADDRESS2 MDATE
 --- ----- ------ ----- ------------- ------- -------- -------- ---------------------
   1 user1 1234   �մ���   000-0000-0000 12345   ����� ���α�  ��ö��      2017-03-22 22:13:31.0

   
4. ����
UPDATE member 
SET mname='�Ʒι�', tel='111-1111-1111', zipcode='00000',
      address1='��⵵', address2='���ֽ�'
WHERE mno=1;


5. �н����� ����
1) �н����� �˻�
SELECT COUNT(mno) as cnt
FROM member
WHERE mno=1 AND passwd='1234';

2) �н����� ����
UPDATE member
SET passwd='0000'
WHERE mno=1;


6. ����
1) ��� ����
DELETE FROM member;

2) Ư�� ȸ�� ����
DELETE FROM member
WHERE mno=4;


7. �α���
SELECT COUNT(mno) as cnt
FROM member
WHERE id='user1' AND passwd='1234';
 cnt
 ---
   0


* ������Ʈ ����� �߰� �÷� �ȳ�: �̸���, ����, ȸ�� ����, ����, ���
  

-------------------------------------------------------------------------------------

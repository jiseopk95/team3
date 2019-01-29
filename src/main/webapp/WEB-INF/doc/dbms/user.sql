DROP TABLE member;
DROP TABLE member_login;
DROP TABLE manager;
DROP TABLE manager_login;
DROP TABLE pet;
DROP TABLE contents;
DROP TABLE member;

/**
 * ȸ�����̺�
 */
CREATE TABLE member (
  memberno NUMBER(10) NOT NULL,
  id       VARCHAR2(20)  UNIQUE NOT NULL,
  passwd    VARCHAR2(20)  NOT NULL,
  name    VARCHAR2(20)   NOT NULL,
  phone          VARCHAR2(20)   NOT NULL,
  email       VARCHAR2(20)  UNIQUE NOT NULL,
  zipcode   VARCHAR(5)        NULL, -- �����ȣ
  address1  VARCHAR(80)       NULL, -- �ּ�
  address2  VARCHAR(50)       NULL, -- ���ּ�
  rdate      DATE       NOT NULL,
  PRIMARY KEY (memberno)                
);

COMMENT ON TABLE member is 'ȸ��';
COMMENT ON COLUMN member.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN member.id is '���̵�';
COMMENT ON COLUMN member.passwd is '�н�����';
COMMENT ON COLUMN member.name is '�̸�';
COMMENT ON COLUMN member.phone is '��ȭ��ȣ';
COMMENT ON COLUMN member.email is '�̸���';
COMMENT ON COLUMN member.zipcode is '�����ȣ';
COMMENT ON COLUMN member.address1 is '�ּ�';
COMMENT ON COLUMN member.address2 is '�� �ּ�';
COMMENT ON COLUMN member.rdate is '���� ��¥';

1) ȸ�����
INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user', '1234', '�Ʒι�', '000-1234-5678', 'abc123@gmail.com','1234','����� ���α�','�ֵ���ũ', sysdate);

2) ȸ���˻�
SELECT memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate
FROM member
ORDER BY memberno ASC;

5) �н����� ����
5-1 ���� �н����� �˻�
SELECT count(*) as cnt
FROM member
WHERE memberno = 1 AND passwd='1234';

5-2 �н����� ����
UPDATE member
SET passwd='5678'
WHERE memberno=1;

6) ȸ�� ���� ���� 
UPDATE member
SET id='user1', passwd='4567', name='������', phone='010-1234-5678', email='cdf123@gmail.com',zipcode='4561',address1='����� ���α�',address2='������ �Ÿ�'
WHERE memberno=1;

7. ȸ�� ���� 
DELETE FROM member
WHERE memberno = 1;
DELETE FROM member
WHERE memberno = 3;
DELETE FROM member
WHERE memberno = 4;


8. �α��� ����
SELECT count(*) as cnt
FROM member
WHERE id = 'user1' AND passwd='12345' 
   
SELECT count(*) as cnt
FROM member
WHERE id = 'user2' AND passwd='12345'
   

Email ������ �̿��� ���̵� ��ȸ

SELECT memberno,id,name,email,rdate
FROM member
WHERE email = 'abc@naver.com' AND name='������';

===========================================================================================================

CREATE TABLE manager (
  managerno NUMBER(10) NOT NULL, -- ������ ��ȣ, ���ڵ带 �����ϴ� �÷� 
  kind        VARCHAR2(20)    NOT NULL, -- M: ������, N: ���� ���� ���� ������, A: �������� �亯 ������, B: �̿� ������, D: ���� ������
  id       VARCHAR2(20)  UNIQUE NOT NULL, -- �ѹ� ��ϵ� id �ߺ� �ȵ�   
  passwd    VARCHAR2(20)  NOT NULL, -- �н�����, ������ ���� 
  name    VARCHAR2(20)   NOT NULL, -- ����, �ѱ� 10�� ���� ����
  position    VARCHAR2(20)   NOT NULL, -- ����
  phone       VARCHAR2(20)   NOT NULL, -- ��ȭ��ȣ
  email       VARCHAR2(20)  UNIQUE NOT NULL, -- �ѹ� ��ϵ� email�� �ߺ� �ȵ�
  zipcode   VARCHAR(5)        NULL, -- �����ȣ
  address1  VARCHAR(80)       NULL, -- �ּ�
  address2  VARCHAR(50)       NULL, -- �� �ּ� 
  files      VARCHAR2(1000)      NULL,
  thumbs       VARCHAR2(1000)      NULL,
  filesizes      VARCHAR2(1000)    NULL,
  rdate      DATE       NOT NULL, -- ������
  PRIMARY KEY (managerno)
);

COMMENT ON TABLE manager is '������';
COMMENT ON COLUMN manager.managerno is '������ ��ȣ';
COMMENT ON COLUMN manager.kind is '����';
COMMENT ON COLUMN manager.id is '���̵�';
COMMENT ON COLUMN manager.passwd is '�н�����';
COMMENT ON COLUMN manager.name is '�̸�';
COMMENT ON COLUMN manager.position is '����';
COMMENT ON COLUMN manager.phone is '��ȭ��ȣ';
COMMENT ON COLUMN manager.email is '�̸���';
COMMENT ON COLUMN manager.zipcode is '�����ȣ';
COMMENT ON COLUMN manager.address1 is '�ּ�';
COMMENT ON COLUMN manager.address2 is '���ּ�';
COMMENT ON COLUMN manager.files is '�̹��� ����';
COMMENT ON COLUMN manager.thumbs is '�̸����� �̹���';
COMMENT ON COLUMN manager.filesizes is '�̹��� ������';
COMMENT ON COLUMN manager.rdate is '���Գ�¥';


2) ���
INSERT INTO manager(managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate)
VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
'M','manager', '1234', '�Ʒι�','��ǥ�̻�' ,'010-1234-5458', 'abc1453@gmail.com','12345', '����� ���α� ���ε�', '123-45','pet1.jpg','pet1_m.jpg','2.5',sysdate);

1) ���
SELECT managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate
FROM manager
ORDER BY managerno ASC;


CREATE TABLE manager_login (
  manager_loginno NUMBER(10) NOT NULL, -- �α��� ��� ��ȣ, ���ڵ带 �����ϴ� �÷� 
  managerno NUMBER(10) NOT NULL,    -- ������ ��ȣ, ���ڵ带 �����ϴ� �÷� 
  ip         VARCHAR2(20)   NOT NULL, 
  rdate      DATE       NOT NULL, -- �α��� ��¥
  PRIMARY KEY (manager_loginno),
  FOREIGN KEY (managerno) REFERENCES manager(managerno)
);

COMMENT ON TABLE manager_login is '�α��� ���';
COMMENT ON COLUMN manager_login.manager_loginno is '�α��� ��� ��ȣ';
COMMENT ON COLUMN manager_login.managerno is '������ ��ȣ';
COMMENT ON COLUMN manager_login.ip is 'ip �ּ�';
COMMENT ON COLUMN manager_login.rdate is '�α��� ��¥';

1) �α��� ��� ��ȸ
SELECT manager_loginno,managerno, ip, rdate
FROM manager_login
ORDER BY manager_loginno ASC;

2) �α��� ��� ����
INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','123-456-123', sysdate);

INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','269-456-603', sysdate);

INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','163-815-133', sysdate);

CREATE TABLE member_login (
  member_loginno NUMBER(10) NOT NULL, -- �α��� ��� ��ȣ, ���ڵ带 �����ϴ� �÷� 
  memberno NUMBER(10) NOT NULL,    -- ȸ�� ��ȣ, ���ڵ带 �����ϴ� �÷� 
  ip         VARCHAR2(20)   NOT NULL, 
  rdate      DATE       NOT NULL, -- �α��� ��¥
  PRIMARY KEY (member_loginno),
  FOREIGN KEY (memberno) REFERENCES member(memberno)
);

COMMENT ON TABLE member_login is '�α��� ��� ��ȣ';
COMMENT ON COLUMN member_login.member_loginno is '�α��� ��ȣ';
COMMENT ON COLUMN member_login.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN member_login.ip is 'ip �ּ�';
COMMENT ON COLUMN member_login.rdate is '�α��� ��¥';

1) �α��� ��� ��ȸ
SELECT member_loginno,memberno, ip, rdate
FROM member_login
ORDER BY member_loginno ASC;

2) �α��� ��� ����
INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'1','123-415-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'2','123-516-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'3','151-456-123', sysdate);

DROP TABLE pet;

CREATE TABLE pet(
  petno NUMBER(10) NOT NULL, -- �ݷ����� ��ȣ, ���ڵ带 �����ϴ� �÷� 
  memberno NUMBER(10) NOT NULL, -- �ݷ����� ��ȣ, ���ڵ带 �����ϴ� �÷� 
  name    VARCHAR2(20)   NOT NULL, -- �̸�, �ѱ� 10�� ���� ����
  age    VARCHAR2(20)   NOT NULL, -- ����, �ѱ� 10�� ���� ����
  gender    VARCHAR2(20)   NOT NULL, -- ����, �ѱ� 10�� ���� ����
  pet_type    VARCHAR2(20)   NOT NULL, -- ���� ����, �ѱ� 10�� ���� ����
  neutralization    VARCHAR2(20)   NOT NULL, -- �߼�ȭ ����, �ѱ� 10�� ���� ����
  weight    FLOAT(10)   NOT NULL, -- ������, �ѱ� 10�� ���� ����
  files      VARCHAR2(1000)      NULL,
  thumbs       VARCHAR2(1000)      NULL,
  filesizes      VARCHAR2(1000)    NULL,
  PRIMARY KEY (petno),
  FOREIGN KEY (memberno) REFERENCES member(memberno)
);

COMMENT ON TABLE pet is '�ݷ�����';
COMMENT ON COLUMN pet.petno is '������ȣ';
COMMENT ON COLUMN pet.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN pet.name is '�����̸�';
COMMENT ON COLUMN pet.age is '����';
COMMENT ON COLUMN pet.gender is '����';
COMMENT ON COLUMN pet.pet_type is '��������';
COMMENT ON COLUMN pet.neutralization is '�߼�ȭ����';
COMMENT ON COLUMN pet.weight is '������';
COMMENT ON COLUMN pet.files is '�̹��� ����';
COMMENT ON COLUMN pet.thumbs is '�̸����� �̹���';
COMMENT ON COLUMN pet.filesizes is '�̹��� ������';

1) ���� ���

-- ���ϵ� �߰��� �� --
INSERT INTO pet(petno,memberno,name, age, gender, pet_type, neutralization, weight,files,thumbs,filesizes)
VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet),
'2','�ƶ���','10', '����', '����', 'Y', '2','pet1.jpg','pet1_m.jpg','2.5');


2) ���� ��ȸ
SELECT petno,memberno,name, age, gender, pet_type, neutralization, weight,files,thumbs,filesizes
FROM pet
ORDER BY petno ASC;

SELECT memberno, id, passwd, name, phone,email, zipcode, address1, address2, rdate
    FROM member
        WHERE name LIKE '%�Ʒ�%'
    ORDER BY memberno DESC;
    
/**
 * ���κ� ���� ��ȸ
 */
    SELECT petno,memberno,name, age, gender, pet_type, neutralization, weight,files,thumbs,filesizes
    FROM pet
    WHERE memberno = 2;

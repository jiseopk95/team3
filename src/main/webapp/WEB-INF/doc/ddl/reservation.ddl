Animal Hospital Reservation System
������Ʈ�� : AHR_system
��Ű���� : dev.mvc.ahr
����� : Spring ��
��� : Oracle
���� : ahr
DROP TABLE animalstory CASCADE CONSTRAINTS;
DROP TABLE chart CASCADE CONSTRAINTS;
DROP TABLE manager CASCADE CONSTRAINTS;
DROP TABLE reservation CASCADE CONSTRAINTS;
DROP TABLE pet CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
select * from tab
/**********************************/
/* Table Name: ȸ�� */
/**********************************/
1. ���̺� ����
   - COMMENT ����
   select * from member
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

2. ���
drop table member;
--INSERT INTO member(memberno,kind, id, passwd, name, phone, email,address,auth,confirm, rdate)
--VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
--'M', 'master', '1234', '������', '000-1234-5678', 'master@gmail.com','��⵵ ������','AXD0123456789012','Y', sysdate);
--
INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user', '1234', '�Ʒι�', '000-1234-5678', 'abc123@gmail.com','1234','����� ���α�','�ֵ���ũ', sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'sy', '12345', '��', '000-1234-5678', 'abc456@gmail.com','1234','��⵵ ������','�ֵ���ũ',sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'hw', '123456', '��', '000-5678-1234', 'abc000@gmail.com','1234','��⵵ �ϻ��','�ֵ���ũ',sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user1', '1234567', '�Ʒι�', '000-5678-1234', 'ab450@gmail.com','1234','����� ���α�','�ֵ���ũ',sysdate);

 SELECT petno FROM pet WHERE memberno=4;
/**********************************/
/* Table Name: �ݷ����� */
/**********************************/
 1. ���̺� ����
   - COMMENT ����
 drop table pet;
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
delete from pet;
select * from pet order by petno;
2. ��� 
 INSERT INTO pet(petno, name, age, gender, pet_type, neutralization, weight, memberno) 
 VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet), '�ʷ���', 3, '����', '��Ƽ��', 'Y', 4.5, 2);
 
 INSERT INTO pet(petno, name, age, gender, pet_type, neutralization, weight, memberno) 
 VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet), '������', 4, '����', '�����', 'Y', 4.5, 2);
 
 INSERT INTO pet(petno, name, age, gender, pet_type, neutralization, weight, memberno) 
 VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet), '����', 5, '����', 'Ǫ��', 'Y', 4.5, 3);
 
 INSERT INTO pet(petno, name, age, gender, pet_type, neutralization, weight, memberno) 
 VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet), '����', 12, '����', '����', 'Y', 4.5, 4);
 
 3. �˻�
 select name from pet where memberno = 2 and petno=1; /* �ʷ���, ������ */
 select name from pet where memberno = 4; /* ���� */
 
/**********************************/
/* Table Name: ���� */
/**********************************/
1. ���̺� ����
   - COMMENT ����
 drop table reservation
CREATE TABLE reservation(
		reservationno                 		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(50)		 NOT NULL,
		label                         		VARCHAR2(50)		 NOT NULL,
		resdate                       		VARCHAR2(50)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		restime                       		VARCHAR2(50)		 NOT NULL,
		content                       		CLOB		 NOT NULL,
		restype                       		NUMBER(10)			 NOT NULL,
		name													VARCHAR2(50)		 NOT NULL,
		petno                         		NUMBER(10)		 NULL ,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (petno) REFERENCES pet (petno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE reservation is '����';
COMMENT ON COLUMN reservation.reservationno is '����۹�ȣ';
COMMENT ON COLUMN reservation.title is '����';
COMMENT ON COLUMN reservation.label is '���̺�';
COMMENT ON COLUMN reservation.resdate is '���೯¥';
COMMENT ON COLUMN reservation.restime is '����ð�';
COMMENT ON COLUMN reservation.content is '����';
COMMENT ON COLUMN reservation.restype is '����';
COMMENT ON COLUMN reservation.name is '�����̸�';
COMMENT ON COLUMN reservation.petno is '������ȣ';
COMMENT ON COLUMN reservation.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN reservation.rdate is '�Է³�¥'; 
select * from col;
delete from reservation;
select * from reservation where restype=1;
 ���� 3:00
 ���� 6:00
 ���� 10:30
select restime from reservation where restype=1 AND resdate='2019-01-15';
 ���� 11:30
 ���� 3:00
select restime from reservation where restype=2 AND resdate='2019-01-15';
 ���� 11:30
 ���� 12:30
select restime from reservation where restype=1 AND resdate='2019-01-16';
 ���� 10:00
 ���� 10:30
select restime from reservation where restype=2 AND resdate='2019-01-16';
 ���� 11:30
 
 select restime from reservation where restype=3;
select petno, name  from pet;
2. ��� 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'ĿƮ', '�ʷ��� �̿��', '2018-12-12', '���� 10:00', '��������', 2, '�ʷ���', 1, 2, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'���� �˻�', '�ʷ��� ����', '2018-12-12', '���� 3:00', '���� ������� ���並 �ؿ�', 1, '�ʷ���', 1, 2, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'������ �йб�', '������ �̿�', '2018-12-12', '���� 11:00', '���� �� ª�� �ٵ���ּ���', 2, '�ʷ���', 1, 2,  sysdate);
 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'��������', '���� ��������', '2018-01-12', '���� 6:00', '���� �������� ����', 1, '����', 3, 4,  sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'���� �ٵ��', '���� �̿�', '2018-12-15', '���� 7:00', '���� ���� ���� �ణ �ٵ���ּ���', 2, '����', 3, 4, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'�� ��������', '���� ����', '2018-12-15', '���� 10:30', '�縻���� �Ծ����� Ȯ��', 1, '������', 2, 3, sysdate);
 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'�� �б�', '���� �̿�', '2018-12-15', '���� 11:30', '�����ǰ� �־��', 2, '������', 2, 4, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'�� ��������', '���� ����', '2019-01-16', '���� 10:30', '�縻���� �Ծ����� Ȯ��', 1, '����', 2, 4, sysdate);
 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'�� �б�', '���� �̿�', '2019-01-16', '���� 11:30', '�����ǰ� �־��', 2, '����', 2, 4, sysdate);
 
3. ���
-- ����� ����Ʈ > �� ���ฮ��Ʈ �� ����ִ� ����ð� Ȯ�ο� ��� (����ð� ������������ ����)
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation 
WHERE resdate = '2018-12-15'
ORDER BY restime;

-- ��ü ��� > Ư�� ���� ��� (���೯¥ ��������, �ð� ������������ ����)
select reservationno, title, label, resdate, restime, memberno
from reservation
where substr(resdate, 1, 7) = '2018-12' and memberno = 2
order by resdate desc, restime asc

SELECT r.reservationno, r.restype, r.title, r.resdate, r.restime, r.content, r.name, r.petno, r.rdate, r.memberno
FROM reservation r, member m
WHERE r.memberno = m.memberno AND r.memberno = 2 AND substr(r.resdate, 6, 2) = '01'
ORDER BY r.resdate asc, r.restime asc

-- ��ü ��� > ������
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation 
WHERE substr(resdate, 1, 7) = '2018-12'
ORDER BY resdate desc

-- ȸ��Ķ���� > ȸ��Ķ���� ȸ����ȣ���� ���̰��ؾ���.
SELECT r.reservationno, r.title, r.label, r.resdate, r.restime, r.content, r.restype, r.petname, r.petno, r.rdate, r.memberno
FROM reservation r, member m
WHERE r.memberno = m.memberno AND r.memberno = 2;
-- ȸ��Ķ���� > Ư�� ���� ���
SELECT r.reservationno, r.title, r.label, r.resdate, r.restime, r.content, r.restype, r.petno, r.rdate
FROM reservation r, member m
WHERE r.memberno = m.memberno
AND r.memberno = 2
AND substr(resdate, 1, 7) = '2018-12'; -- 12����
-- ȸ��Ķ���� > Ư�� ���� ���
SELECT r.reservationno, r.title, r.label, r.resdate, r.restime, r.content, r.restype, r.petno, r.rdate
FROM reservation r, member m
WHERE r.memberno = m.memberno
AND r.memberno = 2
AND r.resdate = '2018-12-15'; -- 12�� 12��

-- �Ƿ�Ķ���� > �Ƿ�Ķ���� ��ü����
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '�Ƿ�';
-- �Ƿ�Ķ���� > Ư�� ���� ���
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '�Ƿ�'
AND substr(resdate, 1, 7) = '2018-12'; -- 12����
-- �Ƿ�Ķ���� > Ư�� ���� ���
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '�Ƿ�'
AND resdate = '2018-12-12'; -- 12�� 12��

-- ��ü ��� > �̿� Ķ���� ��ü����
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '�̿�';
-- �̿�Ķ���� > Ư�� ���� ���
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '�̿�'
AND substr(resdate, 1, 7) = '2018-12'; -- 12����
-- �̿�Ķ���� > Ư�� ���� ���
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '�̿�'
AND resdate = '2018-12-12'; -- 12�� 12��

-- ����¡
SELECT rownum, reservationno, title
FROM reservation

4. ��ȸ
���� ����
select * from reservation where reservationno = 1

RESERVATIONNO TITLE  LABEL   RESDATE    RESTIME CONTENT        RESTYPE RDATE                 PETNO
 ------------- ------ ------- ---------- ------- -------------- ------- --------------------- -----
             1 ĿƮ�� ���� �ʷ��� �̿�� 2018-12-12 13:00   ��������, ���� �κ� ���� �̿�      2018-12-11 17:17:52.0     1

5. ����
'���� �˻�', '�ʷ��� ����', '2018-12-12', '15:00', '���� ������� ���並 �ؿ�', '�Ƿ�', '1'
UPDATE reservation 
SET title = '��������', label = '�ʷ��� ����', resdate = '2018-12-13', restime = '15:00', content ='�ʼ����� �����մϴ�.', restype = '�Ƿ�', petno='1', memberno='2'
WHERE reservationno=1;

select * from reservation where reservationno = 1;
RESERVATIONNO TITLE LABEL  RESDATE    RDATE                 RESTIME CONTENT     RESTYPE RESITEMNO PETNO MEMBERNO
 ------------- ----- ------ ---------- --------------------- ------- ----------- ------- --------- ----- --------
             1 ��������  �ʷ��� ���� 2018-12-13 2018-12-12 17:26:13.0 15:00   �ʼ����� �����մϴ�. �Ƿ�           NULL     1        2

 
6. ����
-- �Ѱ� ����
DELETE FROM reservation WHERE reservationno = 1;


/**********************************/
/* Table Name: ������ */
/**********************************/
1. ���̺� ����
   - COMMENT ����

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



2. ��� 
INSERT INTO manager(managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate)
VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
'M','manager', '1234', '�Ʒι�','��ǥ�̻�' ,'010-1234-5458', 'abc1453@gmail.com','12345', '����� ���α� ���ε�', '123-45','pet1.jpg','pet1_m.jpg','2.5',sysdate);

--INSERT INTO manager(managerno,kind, id, passwd, name, phone, email,auth,confirm, rdate)
--VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
--'B','manager1', '12345', '�̿������', '000-1234-5458', 'abc1453@gmail.com','AXD0123455129012','Y', sysdate);
--
--INSERT INTO manager(managerno,kind, id, passwd, name, phone, email,auth,confirm, rdate)
--VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
--'D','manager2', '12354', '����������', '000-1215-5678', 'abc51@gmail.com','AXD0128456789012','Y', sysdate);
--
--INSERT INTO manager(managerno,kind, id, passwd, name, phone, email,auth,confirm, rdate)
--VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
--'A','manager3', '12534', '�亯������', '000-1224-5658', 'ab263@gmail.com','AXD0123456154012','Y', sysdate);


/**********************************/
/* Table Name: ���� ��Ʈ */
/**********************************/
1. ���̺� ����
   - COMMENT ����
 chartno, managerno, petno, name, title, symptom, sick, medicine, stay, etc, rdate
CREATE TABLE chart(
		chartno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		managerno                     		NUMBER(10)		 NOT NULL ,
		title                         		VARCHAR2(50)		 NOT NULL,
		sick                          		VARCHAR2(20)		 NOT NULL,
		medicine                      		VARCHAR2(50)		 NULL,
		rdate                         		DATE		 NOT NULL,
		stay                          		VARCHAR2(10)		 NOT NULL,
		etc                           		CLOB		 NULL,
		petno                         		NUMBER(10)		 NOT NULL ,
		petname												VARCHAR2(50)		 NOT NULL,
		name													VARCHAR2(50)		 NOT NULL,
  FOREIGN KEY (petno) REFERENCES pet (petno),
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE chart is '���� ��Ʈ';
COMMENT ON COLUMN chart.chartno is '��Ʈ ��ȣ';
COMMENT ON COLUMN chart.title is '����';
COMMENT ON COLUMN chart.sick is '�� �̸�';
COMMENT ON COLUMN chart.medicine is '������';
COMMENT ON COLUMN chart.rdate is '��ϳ�¥';
COMMENT ON COLUMN chart.stay is '�Կ�';
COMMENT ON COLUMN chart.etc is '��Ÿ';
COMMENT ON COLUMN chart.managerno is '�����ڹ�ȣ';
COMMENT ON COLUMN chart.petname is '�����̸�';
COMMENT ON COLUMN chart.name is '��ȣ���̸�';
COMMENT ON COLUMN chart.petno is '������ȣ';

2. ��� 
drop table chart
select * from pet;
select p.name, p.memberno, m.name from pet p, member m where p.memberno = m.memberno
select age, gender, pet_type, NEUTRALIZATION, weight from pet where petno=2
select name, 
PETNO NAME AGE GENDER PET_TYPE NEUTRALIZATION WEIGHT FILES THUMBS FILESIZES MEMBERNO
 ----- ---- --- ------ -------- -------------- ------ ----- ------ --------- --------
     1 �ʷ���  3   ��      ��Ƽ��      �߼�ȭ            4.5kg  NULL  NULL   NULL             2
     2 ����   5   ��      Ǫ��       �߼�ȭ            4.5kg  NULL  NULL   NULL             3
     3 ����   12  ��      ����       �߼�ȭ            4.5kg  NULL  NULL   NULL             4
delete from chart;
select name, petno from pet

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
1, '�ʷ���', '��', '�ʷ��� ������Ʈ', '��������', '����������', 'N', '�����ǹ߰�', 1, sysdate);

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
2, '������', '��', '������ ������Ʈ', '�縻���� ����', 'N', 'N', '���� �����Ͽ� ������', 1, sysdate);

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
3, '����', '��', '���� ������Ʈ', '����', '�����', 'N', '�����࿡ �˷�������', 1, sysdate);

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
4, '����', '�Ʒι�', '���� ������Ʈ', '�縻���� ����', 'N', 'N', '���� �����Ͽ� ������', 1, sysdate);

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
4, '����', '�Ʒι�', '���� ������Ʈ', '��������', '����������', 'N', '����������', 1, sysdate);

3. ��� --+ 
-- ��ü ���
SELECT chartno, title, symptom, sick, medicine, resdate, rdate, stay, etc, managerno, petno
FROM chart
WHERE petno = 2 AND memberno = (select memberno from member where petno = 2)
ORDER BY chartno;
4. ��ȸ --+
-- Ư�������� ��ü ���
SELECT c.chartno, c.title, c.symptom, c.sick, c.medicine, c.resdate, c.rdate, c.stay, c.etc, c.managerno, c.name
FROM chart c, pet p 
WHERE c.petno = 2
ORDER BY rdate DESC;

select chartno, petno, title, symptom, sick, medicine, name
from chart
where petno = (select petno from pet where memberno = 2) -- => ���߰��� ���ͼ� �˻� �ȵ�

select chartno, petno, title, sick, medicine, name, substr(rdate, 1, 7) -- => �ϳ��� ��� �������� �˻��� �� ���� �̸��� ������ ���� ���� �� ����
from chart
where petno = 3 

SELECT chartno, managerno, petno, petname, name, title, sick, medicine, stay, etc, substr(rdate, 1, 8)
FROM chart 
ORDER BY petno

-- chart create�� �� �⺻ �������� 
select p.petno, p.name, p.age, m.name, m.phone from pet p, member m where p.petno = 2 AND m.memberno = 3
-- chart list : ��絿���� �� ����
SELECT chartno, managerno, petno, name, title, symptom, sick, medicine, stay, etc, rdate FROM chart ORDER BY petno 
SELECT chartno, managerno, petno, petname, name, title, sick, medicine, stay, etc, substr(rdate, 1, 8) rdate
    FROM chart 
    WHERE chartno = 3
-- �˻� 1 : �����̸��� ��ȣ���̸����� 
select chartno, petno, title, symptom, sick, medicine, name
from chart
where petno = (select petno from pet where name = '����') AND name = '�Ʒι�'
-- �˻� 2 : ��ȣ�� �̸����� �˻��Ҷ� -> �ش�� ���� ��ΰ� ����
select chartno, petno, title, symptom, sick, medicine, name
from chart
where name = '��'

name = '����' AND 


-> �ذ��
1. chart�� ������ �� pet_list���� ����ؼ� petno�� �Ѱ��༭ petno������ ��Ʈ�� �����ǰ� �Ѵ�.
2. �����̸��� ���̸� �Բ� �˻��ǰ� �Ѵ�. -> �ذ� ������ ���̰� ���� ���ŵž��Ѵ�.
3. �����̸��� ��ȣ���� �̸��� �Բ� �˻��ǰ� �Ѵ�. -> �����̸��� ��ȣ���� �̸��� ��� ���� ����� ���������� ������ ���ɼ���?

-- ��ü ���ڵ��
SELECT count(chartno)
FROM chart;

5. ����
UPDATE chart 
SET title='���� ������Ʈ', symptom='����', sick='������ ��Ŵ', medicine='N', resdate='2019-01-20', stay='N', etc='���������Ͽ� ������'
WHERE chartno=1 AND petno=2;
CHARTNO TITLE   SYMPTOM SICK   MEDICINE RESDATE    RDATE                 STAY ETC        MANAGERNO PETNO
 ------- ------- ------- ------ -------- ---------- --------------------- ---- ---------- --------- -----
       1 ���� ������Ʈ ����      ������ ��Ŵ N        2019-01-20 2018-12-12 17:59:19.0 N    ���������Ͽ� ������         2     2

6. ���� --
-- �Ѱ� ����
DELETE FROM chart 
WHERE chartno = 1 AND petno=2; 
-- FK������ ���� > 3�� ������ ��Ʈ�� ��� ����
DELETE FROM chart
WHERE petno=3;


/**********************************/
/* Table Name: �ִϸ��̾߱� */
/**********************************/
1. ���̺� ����
   - COMMENT ����

CREATE TABLE animalstory(
		anino                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		anitype                       		VARCHAR2(10)		 NOT NULL,
		title                         		VARCHAR2(100)		 NOT NULL,
		content                       		CLOB		 NOT NULL,
		files                         		VARCHAR2(1000)		 NULL ,
		thumb                         		VARCHAR2(1000)		 NULL ,
		sizes                          		VARCHAR2(1000)		 NULL ,
		rdate                         		DATE		 NOT NULL,
		cnt                           		NUMBER(10)		 NOT NULL,
		managerno                     		NUMBER(10)		 NULL ,
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE animalstory is '�ִϸ��̾߱�';
COMMENT ON COLUMN animalstory.anino is '�۹�ȣ';
COMMENT ON COLUMN animalstory.anitype is '����';
COMMENT ON COLUMN animalstory.title is '����';
COMMENT ON COLUMN animalstory.content is '����';
COMMENT ON COLUMN animalstory.files is '�����̸�';
COMMENT ON COLUMN animalstory.thumb is '������̸�';
COMMENT ON COLUMN animalstory.sizes is '����ũ��';
COMMENT ON COLUMN animalstory.rdate is '�ۼ���';
COMMENT ON COLUMN animalstory.cnt is '��ȸ��';
COMMENT ON COLUMN animalstory.managerno is '�����ڹ�ȣ';

2. ��� 
INSERT INTO animalstory(anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'��', '��å�� ������ �ƹ��ų� �ֿ� �Դ� ������', 'Q. �ƹ��ų� �ֿ� �Դ� ������, ��� �ؾ� �ϳ���? A. ��κ� Ÿ�� ��Ž������ ���ɼ��� ����. ', 
'dog.jpg', 'dog_thumb.jpg', 0, 0, 2, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'��', '�и��Ҿ����� ����� ġ���', 'Q. �и��Ҿ����� ���� A. ���� ���������̴�. ', 
'dog2.jpg', 'dog2_thumb.jpg', 0, 0, 3, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'�����', '��� ������ ���ĸ� �� �ܾ����', 'Q. ��� ������ ���ĸ� �� �ܾ���� A. �������� ��� ���� ����� ��ǰ!��, �߻� ��ȯ�ϱ�', 
'cat.jpg', 'cat_thumb.jpg', 0, 0, 2, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'�����', '������� ����, �翬�� ��?', 
'Q. ������� ����, �翬�� ��? A. ������ ������ ���� ������ �ּ������� �ٿ��� �Ѵ�.', 
'cat2.jpg', 'cat2_thumb.jpg', 0, 0, 3, sysdate);
 
3. ���
-- ��ü ���
SELECT anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate
FROM animalstory;
-- �� ��ϸ� ����
SELECT anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate
FROM animalstory
WHERE anitype = '��';

4. ��ȸ
-- �Ѱ� ��ȸ
SELECT anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate
FROM animalstory
WHERE anino = 3;
-- ��ü ���ڵ��
SELECT count(anino)
FROM animalstory;
-- FK������ ���ڵ� ��
SELECT count(anino)
FROM animalstory
WHERE managerno = 2;
 
5. ����
UPDATE animalstory
SET title = '�ְ��� ���� ���� ��ġ��', content = '������ ���� ���� ������ �νĽ�Ű��, ���ο��� ���Ǹ� ����̰� �Ѵ�.'
WHERE anino = 2;
 
6. ����
-- �Ѱ� ����
DELETE FROM animalstory
WHERE anino = 2;

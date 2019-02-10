-- ����
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE member_login CASCADE CONSTRAINTS;
DROP TABLE manager CASCADE CONSTRAINTS;
DROP TABLE manager_login CASCADE CONSTRAINTS;
DROP TABLE pet CASCADE CONSTRAINTS;
-- ����
DROP TABLE animalstory CASCADE CONSTRAINTS;
DROP TABLE chart CASCADE CONSTRAINTS;
DROP TABLE reservation CASCADE CONSTRAINTS;

-- ���
DROP TABLE eventusers CASCADE CONSTRAINTS;
DROP TABLE event CASCADE CONSTRAINTS;
DROP TABLE present CASCADE CONSTRAINTS;
DROP TABLE style CASCADE CONSTRAINTS;

-- �̿�
drop table survey CASCADE CONSTRAINTS;
drop table surveyparty CASCADE CONSTRAINTS;
drop table surveyitem CASCADE CONSTRAINTS

-- �ؿ�
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE question CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE categrp CASCADE CONSTRAINTS;
DROP TABLE answer CASCADE CONSTRAINTS;

select * from tab;
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
  zipcode   VARCHAR2(5)        NULL, -- �����ȣ
  address1  VARCHAR2(80)       NULL, -- �ּ�
  address2  VARCHAR2(50)       NULL, -- ���ּ�
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
select * from member
1) ȸ�����
INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'Mmaster', '1234', '������', '000-1234-5678', 'master@gmail.com','1234','����� ���α�','�ֵ���ũ', sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user', '1234', '�Ʒι�', '000-1234-5678', 'abc123@gmail.com','1234','����� ���α�','�ֵ���ũ', sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user1', '1234', '������', '000-4567-5678', 'abc100@gmail.com','1534','����� ������','�ֵ���ũ 12', sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user2', '1234', '�մ���', '000-1456-5678', 'abc150@gmail.com','1215','����� ���ʱ�','�ֵ���ũ 545', sysdate);

/* manager */

CREATE TABLE manager (
  managerno NUMBER(10) NOT NULL, -- ������ ��ȣ, ���ڵ带 �����ϴ� �÷� 
  kind        VARCHAR2(20)    NOT NULL, -- M: ������(1��), N: ���� ���� ���� ������, A: �������� �亯 ������, B: �̿� ������, D: ���� ������
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

1) ���
INSERT INTO manager(managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate)
VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
'M','master', '123', '�Ʒι�','��ǥ�̻�' ,'010-1234-5458', 'abc1453@gmail.com','12345', '����� ���α� ���ε�', '123-45','pet1.jpg','pet1_m.jpg','2.5',sysdate);

INSERT INTO manager(managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate)
VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
'A','manager1', '1234', '������','�̿�����' ,'010-1234-5458', 'abc123@gmail.com','14565', '����� ���ʱ�', '1235-45','pet1.jpg','pet1_m.jpg','3.0',sysdate);


/** 
 * ������ �α��� ��� ���̺�
 */

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

1) �α��� ��� ����
INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','123-456-123', sysdate);

INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','269-456-603', sysdate);

INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','163-815-133', sysdate);

/** 
 * ȸ�� �α��� ��� ���̺�
 */

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


1) �α��� ��� ����
INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'1','123-415-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'2','123-516-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'3','151-456-123', sysdate);

/**
 * ���� ���̺� 
 */

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

INSERT INTO pet(petno,memberno,name, age, gender, pet_type, neutralization, weight,files,thumbs,filesizes)
VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet),
'3','�Ʒ���','5', '����', '��Ƽ��', 'N', '3','pet2.jpg','pet2_m.jpg','3');

/* ���� */
CREATE TABLE reservation(
		reservationno                 		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(50)		 NOT NULL,
		label                         		VARCHAR2(50)		 NOT NULL,
		resdate                       		VARCHAR2(50)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		restime                       		VARCHAR2(50)		 NOT NULL,
		content                       		CLOB		 NOT NULL,
		restype                       		NUMBER(10)			 NOT NULL,
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
COMMENT ON COLUMN reservation.petno is '������ȣ';
COMMENT ON COLUMN reservation.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN reservation.rdate is '�Է³�¥'; 
select * from reservation
1. ��� 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'ĿƮ', '�ʷ��� �̿��', '2018-12-12', '���� 10:00', '��������', 2, '�ƶ���', 1, 2, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'���� �˻�', '�ʷ��� ����', '2018-12-12', '���� 3:00', '���� ������� ���並 �ؿ�', 1, '�Ʒ���', 2, 3, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'������ �йб�', '������ �̿�', '2018-12-12', '���� 11:00', '���� �� ª�� �ٵ���ּ���', 2, '�ƶ���', 1, 2,  sysdate);


/* ������Ʈ */
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
		name														VARCHAR2(50)		 NOT NULL,
		phone															VARCHAR2(50)		 NOT NULL,
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
COMMENT ON COLUMN chart.phone is '��ȣ�� ��ȭ��ȣ';
COMMENT ON COLUMN chart.petno is '������ȣ';

2. ��� 
INSERT INTO chart(chartno, petno, petname, name, phone, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
1, '�ʷ���', '��', '000-1234-5678', '�ʷ��� ������Ʈ', '��������', '����������', 'N', '�����ǹ߰�', 2, 1, sysdate);

INSERT INTO chart(chartno, petno, petname, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
2, '������', '��', '000-1234-5678', '������ ������Ʈ', '�縻���� ����', 'N', 'N', '���� �����Ͽ� ������', 2, 1, sysdate);

INSERT INTO chart(chartno, petno, petname, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
3, '����', '��', '000-5678-1234', '���� ������Ʈ', '����', '�����', 'N', '�����࿡ �˷�������', 3, 1, sysdate);

/* �ִϸֽ��丮 */

CREATE TABLE animalstory(
		anino                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		anitype                       		VARCHAR2(10)		 NOT NULL,
		title                         		VARCHAR2(200)		 NOT NULL,
		content                       		CLOB		 NOT NULL,
		files                         		VARCHAR2(1000)		 NULL ,
		thumbs                         		VARCHAR2(1000)		 NULL ,
		sizes                          		VARCHAR2(1000)		 NULL ,
		rdate                         		DATE		 NOT NULL,
		managerno                     		NUMBER(10)		 NULL ,
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE animalstory is '�ִϸ��̾߱�';
COMMENT ON COLUMN animalstory.anino is '�۹�ȣ';
COMMENT ON COLUMN animalstory.anitype is '����';
COMMENT ON COLUMN animalstory.title is '����';
COMMENT ON COLUMN animalstory.content is '����';
COMMENT ON COLUMN animalstory.files is '�����̸�';
COMMENT ON COLUMN animalstory.thumbs is '������̸�';
COMMENT ON COLUMN animalstory.sizes is '����ũ��';
COMMENT ON COLUMN animalstory.rdate is '�ۼ���';
COMMENT ON COLUMN animalstory.managerno is '�����ڹ�ȣ';
delete from animalstory
2. ��� 
INSERT INTO animalstory(anino, anitype, title, content, files, thumbs, sizes, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'��', '��å�� ������ �ƹ��ų� �ֿ� �Դ� ������', 'Q. �ƹ��ų� �ֿ� �Դ� ������, ��� �ؾ� �ϳ���? A. ��κ� Ÿ�� ��Ž������ ���ɼ��� ����. ', 
'dog.jpg', 'dog_thumb.jpg', 0, 1, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumbs, sizes, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'��', '�и��Ҿ����� ����� ġ���', 'Q. �и��Ҿ����� ���� A. ���� ���������̴�. ', 
'dog2.jpg', 'dog2_thumb.jpg', 0, 1, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumbs, sizes, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'�����', '��� ������ ���ĸ� �� �ܾ����', 'Q. ��� ������ ���ĸ� �� �ܾ���� A. �������� ��� ���� ����� ��ǰ!��, �߻� ��ȯ�ϱ�', 
'cat.jpg', 'cat_thumb.jpg', 0, 1, sysdate);


/**********************************/
/* Table Name: �������� */
/**********************************/

1. ���̺� ����
CREATE TABLE survey(
surveyno                      	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
survey_title                  	VARCHAR2(1000)	 NOT NULL,
seqno                         	NUMBER(10)	 NOT NULL,
startdate                     	VARCHAR2(50)	 NOT NULL,
enddate                       	VARCHAR2(50)	 NOT NULL,
rdate                         	DATE	 NOT NULL,
q_cnt                  NUMBER(6)       DEFAULT 0          NOT NULL,
managerno                    NUMBER(10)	 NULL ,
FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE survey is '��������';
COMMENT ON COLUMN survey.surveyno is '�������� ��ȣ';
COMMENT ON COLUMN survey.survey_title is '�������� Ÿ��Ʋ';
COMMENT ON COLUMN survey.seqno is '��¼���';
COMMENT ON COLUMN survey.startdate is '���۳�';
COMMENT ON COLUMN survey.enddate is '���ᳯ';
COMMENT ON COLUMN survey.rdate is '�ۼ�����';
COMMENT ON COLUMN survey.q_cnt is '������';
COMMENT ON COLUMN survey.managerno is '�����ڹ�ȣ';

2. ���     
INSERT INTO survey(surveyno,managerno,survey_title,seqno,startdate,enddate,rdate)
VALUES((SELECT NVL(max(surveyno),0)+1 as surveyno from survey),1,'����� �������̿븸������ ���� ����',1,'2018-01-02','2018-01-08',sysdate);	
INSERT INTO survey(surveyno,managerno,survey_title,seqno,startdate,enddate,rdate)
VALUES((SELECT NVL(max(surveyno),0)+1 as surveyno from survey),1,'����� �������̿뽺Ÿ�Ͽ� ���� ����',1,'2018-01-02','2018-01-08',sysdate);	

/**********************************/
/* Table Name: �������� �׸� */
/**********************************/
CREATE TABLE surveyitem(
surveyitemno                  	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
seqno                         	NUMBER(10)	 NOT NULL,
question                      	VARCHAR2(50)	 NOT NULL,
surveyno                      	NUMBER(10)	 NULL ,
thumbs              VARCHAR2(1000)                             NULL ,
  files                   VARCHAR2(1000)                            NULL ,
sizes                  VARCHAR2(1000)                            NULL ,
itemcnt                        NUMBER(7) DEFAULT 0 NOT NULL,
FOREIGN KEY (surveyno) REFERENCES survey (surveyno)
);

COMMENT ON TABLE surveyitem is '�������� �׸�';
COMMENT ON COLUMN surveyitem.surveyitemno is '�������� �׸� ��ȣ';
COMMENT ON COLUMN surveyitem.seqno is '��¼���';
COMMENT ON COLUMN surveyitem.question is '��������';
COMMENT ON COLUMN surveyitem.surveyno is '�������� ��ȣ';
COMMENT ON COLUMN surveyitem.thumbs is 'Thumb ����';
COMMENT ON COLUMN surveyitem.files is '���ϵ��� �̸�';
COMMENT ON COLUMN surveyitem.itemcnt is 'üũ �ο�';
COMMENT ON COLUMN surveyitem.sizes is '���ϵ��� ũ��';

2. ��� 
INSERT INTO surveyitem(surveyitemno,surveyno,seqno,question,thumbs,files, sizes)
values((select NVL(max(surveyitemno),0)+1 as surveyitemno from surveyitem),10,1,'����� ���ϴ� �̿� ��Ÿ����?','fall_m.jpg', 'fall.jpg', 0);	

/**********************************/
/* Table Name: �������� ���� */
/**********************************/
CREATE TABLE surveyparty(
surveypartyno                 	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
surveyitemno                  	NUMBER(10)	 NULL ,
rdate                         	DATE	 NOT NULL,
memberno                      	NUMBER(10)	 NULL ,
surveyno                      	NUMBER(10)	 NULL ,
  FOREIGN KEY (surveyitemno) REFERENCES surveyitem (surveyitemno),
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (surveyno) REFERENCES survey (surveyno)
);

COMMENT ON TABLE surveyparty is '�������� ����';
COMMENT ON COLUMN surveyparty.surveypartyno is '�������� ���� ��ȣ';
COMMENT ON COLUMN surveyparty.surveyitemno is '�������� �׸� ��ȣ';
COMMENT ON COLUMN surveyparty.rdate is '������¥';
COMMENT ON COLUMN surveyparty.memberno is 'ȸ����ȣ';


2. ��� 
INSERT INTO surveyparty(surveypartyno,surveyno,surveyitemno,memberno,rdate)
values((select NVL(max(surveypartyno),0)+1 as surveypartyno from surveyparty),12,9,1,sysdate);

/**********************************/
/* Table Name: ī�װ� �׷� */
/**********************************/

CREATE TABLE categrp(
    categrpno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    classification                    VARCHAR2(1)    DEFAULT 1     NOT NULL,
    name                              VARCHAR2(50)     NOT NULL,
    seqno                             NUMBER(7)    DEFAULT 0     NOT NULL,
    visible                           VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
    rdate                             DATE     NOT NULL
);

COMMENT ON TABLE categrp is 'ī�װ� �׷�';
COMMENT ON COLUMN categrp.categrpno is 'ī�װ� �׷� ��ȣ';
COMMENT ON COLUMN categrp.classification is 'ī�װ� �׷� �з�';
COMMENT ON COLUMN categrp.name is '�̸�';
COMMENT ON COLUMN categrp.seqno is '��� ����';
COMMENT ON COLUMN categrp.visible is '��� ���';
COMMENT ON COLUMN categrp.rdate is '�׷� ������';

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((select NVL(max(categrpno),0)+1 as categrpno from categrp), 1, '�Ƿ�', 1, 'Y', sysdate);

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((select NVL(max(categrpno),0)+1 as categrpno from categrp), 2, '�̿�', 2, 'Y', sysdate);


/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE category(
categoryno                    	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
categrpno                  NUMBER(10)                               NOT NULL ,
title                         	VARCHAR2(100)	 NOT NULL,
cnt                  NUMBER(6)       DEFAULT 0          NOT NULL,
seqno               NUMBER(3)        DEFAULT 1         NOT NULL,
rdate                DATE  NOT NULL,
 FOREIGN KEY (categrpno) REFERENCES categrp (categrpno)
);

SELECT c.categrpno, c.name,
    t.categoryno, t.categrpno, t.title,t.seqno, t.cnt
    FROM categrp c, category t
    WHERE c.categrpno = t.categrpno
    ORDER BY c.categrpno ASC, t.seqno ASC

COMMENT ON TABLE category is 'ī�װ�';
COMMENT ON COLUMN category.categoryno is 'ī�װ���ȣ';
COMMENT ON COLUMN category.categrpno is 'ī�װ� �׷� ��ȣ';
COMMENT ON COLUMN category.title is '�Խ��� �̸�';
COMMENT ON COLUMN category.cnt is '��ϵ� �� ��';
COMMENT ON COLUMN category.seqno is '��� ����';
COMMENT ON COLUMN category.rdate is '��ϳ�¥';



-- ����
INSERT INTO category(categoryno, categrpno, title, cnt, seqno, rdate)
VALUES((select NVL(max(categoryno),0)+1 as categoryno from category), 1, '��������', 0, 1, sysdate);
             
INSERT INTO category(categoryno, categrpno, title, cnt, seqno, rdate)
VALUES((select NVL(max(categoryno),0)+1 as categoryno from category), 2, '�����̿�', 0, 2, sysdate);

/**********************************/
/* Table Name: �ı� */
/**********************************/
CREATE TABLE review(
reviewno                      	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
name                          	VARCHAR2(30)	 NOT NULL,
title                         	VARCHAR2(100)	 NOT NULL,
contents                      	CLOB	 NOT NULL,
rdate                         	DATE	 NOT NULL,
files                         	VARCHAR2(30)	 NULL ,
filesize                      	VARCHAR2(1000)	 NULL ,
thumbs                        	VARCHAR2(1000)	 NULL ,
num                           	NUMBER(10)	  NULL,
score                         	NUMBER(10)	 NOT NULL,
word                         VARCHAR2(100)   NULL,
categoryno                    	NUMBER(10)	 NULL ,
memberno                      	NUMBER(10)	 NULL ,
  FOREIGN KEY (categoryno) REFERENCES category (categoryno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);


COMMENT ON TABLE review is '�ı�';
COMMENT ON COLUMN review.reviewno is '�ı��ȣ';
COMMENT ON COLUMN review.name is '�۾���';
COMMENT ON COLUMN review.title is '����';
COMMENT ON COLUMN review.contents is '����';
COMMENT ON COLUMN review.rdate is '��¥';
COMMENT ON COLUMN review.files is '����';
COMMENT ON COLUMN review.filesize is '����ũ��';
COMMENT ON COLUMN review.thumbs is '�̸�����';
COMMENT ON COLUMN review.num is '��ȸ��';
COMMENT ON COLUMN review.score is '����';
COMMENT ON COLUMN review.word is '�˻���';
COMMENT ON COLUMN review.categoryno is 'ī�װ���ȣ';
COMMENT ON COLUMN review.memberno is 'ȸ����ȣ';

-- �ı� ���
INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '���ؿ�', '�������� �ı�', '����ģ����.....',sysdate,
             'fall.jpg', 0, 'fall_m.jpg', 0 , 3, 1 , 1);
             
INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '�Ʒι�', '���� �˻������ �����', '�˻���� �ı�',sysdate,
             'dog.jpg', 0, 'dog_m.jpg', 0 , 4, 1 , 2);

INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '�մ���', '�ǻ缱������ �ʹ� ģ���ؿ�', '���� ���� ����',sysdate,
             '', 0, '', 0 , 5, 1 , 3);
             
             /**********************************/
/* Table Name: ���� */
/**********************************/
CREATE TABLE question(
questionno                    	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
title                         	VARCHAR2(100)	 NOT NULL,
rdate                         	DATE	 NOT NULL,
content                       	CLOB	 NOT NULL,
name                          	VARCHAR2(30)	 NULL,
files                         	VARCHAR2(30)	 NULL ,
filesize                      	VARCHAR2(1000)	 NULL ,
num                           	NUMBER(10)	       NULL,
passwd                            	VARCHAR2(30)	 NULL ,
visible                           	 VARCHAR2(1)    DEFAULT 'Y' NOT NULL,
replycnt              NUMBER(7)        DEFAULT 0       NOT NULL,
grpno                 NUMBER(7)                              NOT NULL,
indent                NUMBER(2)        DEFAULT 0       NOT NULL,
ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
categoryno                    	NUMBER(10)	 NULL ,
memberno                      	NUMBER(10)	 NULL ,
managerno                      	NUMBER(10)	 NULL ,
  FOREIGN KEY (categoryno) REFERENCES category (categoryno),
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE question is '����';
COMMENT ON COLUMN question.questionno is '������ȣ';
COMMENT ON COLUMN question.title is '����';
COMMENT ON COLUMN question.rdate is '��¥';
COMMENT ON COLUMN question.content is '����';
COMMENT ON COLUMN question.name is '�۾���';
COMMENT ON COLUMN question.files is '����';
COMMENT ON COLUMN question.filesize is '����ũ��';
COMMENT ON COLUMN question.num is '��ȸ��';
COMMENT ON COLUMN question.passwd is '��й�ȣ';
COMMENT ON COLUMN question.visible is '��б� ����';
COMMENT ON COLUMN question.replycnt is '��ۼ�';
COMMENT ON COLUMN question.grpno is '�׷��ȣ';
COMMENT ON COLUMN question.indent is '�亯����';
COMMENT ON COLUMN question.ansnum is '�亯 ����';
COMMENT ON COLUMN question.categoryno is 'ī�װ���ȣ';
COMMENT ON COLUMN question.managerno is '�Ŵ�����ȣ';
COMMENT ON COLUMN question.memberno is 'ȸ����ȣ';


-- ���� ���
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '������ ���� �ð��� ��� �ǳ���', sysdate, '����ģ����.....',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 1, 1);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '����', sysdate, '��������',
             '','',  0, '' , 'Y', 0 , 3, 1 , 2);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '�̿� ���� ����', sysdate, '�̿� ���� ����.....',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 2 , 3);
             
/**********************************/
/* Table Name: ��� */
/**********************************/
CREATE TABLE answer(
answerno                      	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
reviewno                    	NUMBER(10)	 NULL ,
title                         	VARCHAR2(100)	 NOT NULL,
name                          	VARCHAR2(30)	 NOT NULL,
emoticon                      	VARCHAR2(30)	 NULL ,
content                       	CLOB	 NOT NULL,
rdate                         	DATE	 NOT NULL,
memberno                     	NUMBER(10)	 NULL ,
  FOREIGN KEY (reviewno) REFERENCES review (reviewno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE answer is '���';
COMMENT ON COLUMN answer.answerno is '��۹�ȣ';
COMMENT ON COLUMN answer.reviewno is '�ı��ȣ';
COMMENT ON COLUMN answer.title is '����';
COMMENT ON COLUMN answer.name is '�۾���';
COMMENT ON COLUMN answer.emoticon is '�̸�Ƽ��';
COMMENT ON COLUMN answer.content is '����';
COMMENT ON COLUMN answer.rdate is '��¥';
COMMENT ON COLUMN answer.memberno is 'ȸ����ȣ';

-- �亯 ���
INSERT INTO answer(answerno, questionno, title, name, emoticon, content, rdate, managerno)
VALUES((SELECT NVL(MAX(answerno), 0)+1 as answerno FROM answer), 1, '�亯', '������',
             'emoticon.jpg','������ ���� �亯�Դϴ�.', sysdate, 1);
             
INSERT INTO answer(answerno, questionno, title, name, emoticon, content, rdate, managerno)
VALUES((SELECT NVL(MAX(answerno), 0)+1 as answerno FROM answer), 2, '�亯', '������',
             'emoticon.jpg','����2�� ���� �亯�Դϴ�.', sysdate, 1);
             
INSERT INTO answer(answerno, questionno, title, name, emoticon, content, rdate, managerno)
VALUES((SELECT NVL(MAX(answerno), 0)+1 as answerno FROM answer), 1, '�亯', '������',
             'emoticon.jpg','�̿� ����1�� ���� �亯�Դϴ�.', sysdate, 2);
             
             

/**********************************/
/* Table Name: �̿뽺Ÿ�� */
/**********************************/
CREATE TABLE style(
		styleno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		managerno                     		NUMBER(10)		 NULL ,
		title                         		VARCHAR2(50)		 NOT NULL,
		name                          		VARCHAR2(50)		 NOT NULL,
		rname                         		VARCHAR2(20)		 NOT NULL,
		like1                         		NUMBER(10)		 NOT NULL,
		email                         		VARCHAR2(20)		 NULL ,
		content                       		CLOB		 NOT NULL,
		cnt                           		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		image                         		VARCHAR2(1000)		 NULL ,
		image_name                    		VARCHAR2(1000)		 NULL ,
		thumb                         		VARCHAR2(1000)		 NULL ,
		sizes                          		VARCHAR2(1000)		 DEFAULT 0		 NULL ,
		pay                           		NUMBER(10)		 NOT NULL,
		times                         		NUMBER(10)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE style is '�̿뽺Ÿ��';
COMMENT ON COLUMN style.styleno is '��Ÿ�Ϲ�ȣ';
COMMENT ON COLUMN style.managerno is '�����ڹ�ȣ';
COMMENT ON COLUMN style.title is '����';
COMMENT ON COLUMN style.name is '��Ÿ���̸�';
COMMENT ON COLUMN style.rname is '�ۼ���';
COMMENT ON COLUMN style.like1 is '���ƿ��';
COMMENT ON COLUMN style.email is '�̸���';
COMMENT ON COLUMN style.content is '����';
COMMENT ON COLUMN style.cnt is '��ȸ��';
COMMENT ON COLUMN style.image is '�̹���';
COMMENT ON COLUMN style.image_name is '�����̹�����';
COMMENT ON COLUMN style.thumb is '�����';
COMMENT ON COLUMN style.sizes is '�̹���ũ��';
COMMENT ON COLUMN style.pay is '����';
COMMENT ON COLUMN style.times is '�ҿ�ð�';
COMMENT ON COLUMN style.rdate is '��ϳ�¥';

/*���*/
 INSERT INTO style(styleno, managerno, title, name, rname, like1, email, content,cnt,image,image_name,thumb,sizes,pay,times,rdate)
VALUES ((SELECT NVL(MAX(styleno), 0)+1 as styleno FROM style),
1,1,'�̿�簡 ��õ�ϴ� ��������','��������','�̿��1',0,'beauty@naver.com','������ �� �ʹ� �Ϳ�����',0, 'bear.jpg', 'bear01.jpg', 'bear_m.jpg',0, 50000, 3, sysdate);

INSERT INTO style(styleno, managerno, title, name, rname, like1, email, content,cnt,image,image_name,thumb,sizes,pay,times,rdate)
VALUES ((SELECT NVL(MAX(styleno), 0)+1 as styleno FROM style),
1,2,'���ڰ� �������� �Ϳ��� ������','��������','�̿��2',0,'beauty2@naver.com','���� ���� �а� �Ӹ��и� �����־� ������ ���⸦ ���ø��� �ϴ� ���Դϴ�',0, 'cut.jpg', 'cut01.jpg', 'cut_m.jpg',0, 60000, 3, sysdate);

/**********************************/
/* Table Name: �̺�Ʈ���� */
/**********************************/
CREATE TABLE present(
		presentno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		info                          		VARCHAR2(50)		 NOT NULL,
		end_date                       VARCHAR2(30)	   NOT NULL
);

COMMENT ON TABLE present is '�̺�Ʈ����';
COMMENT ON COLUMN present.presentno is '������ȣ';
COMMENT ON COLUMN present.info is '��������';
COMMENT ON COLUMN present.end_date is '����������';

/*���*/
INSERT INTO present(presentno, info,end_date)
VALUES ((SELECT NVL(MAX(presentno), 0)+1 as presentno FROM present),'ĿƮ20%�������ִ� ����', '2019-3-31');

INSERT INTO present(presentno, info,end_date)
VALUES ((SELECT NVL(MAX(presentno), 0)+1 as presentno FROM present),'���� ������ ��������', '2019-4-30');

/**********************************/
/* Table Name: �̺�Ʈ ��� */
/**********************************/
CREATE TABLE event(
		eventno                       		NUMBER(10)		 NOT NULL	PRIMARY KEY,
		managerno                     		NUMBER(10)		 NULL,
		presentno                     		NUMBER(10)		 NULL,
		title                         		VARCHAR2(50)		 NOT NULL,
		content                       		CLOB		 NOT NULL,
		period_start                  		VARCHAR2(30)		 NOT NULL,
		period_end                    		VARCHAR2(30)		 NOT NULL,
		writer                        		VARCHAR2(10)		 NOT NULL,
		usercnt                       		NUMBER(10)		 NOT NULL,
		image                         		VARCHAR2(1000)		 NULL ,
		image_size                    		VARCHAR2(1000)		 NULL ,
		thumb                         		VARCHAR2(1000)		 NULL ,
		windate                       		VARCHAR2(20)		 NOT NULL,
		wincnt                        		NUMBER(10)		 NULL ,
		winner                        		VARCHAR2(1000)		 NULL ,
		rdate                         		DATE		 NOT NULL,
  FOREIGN KEY (managerno) REFERENCES manager (managerno),
  FOREIGN KEY (presentno) REFERENCES present (presentno)
);

COMMENT ON TABLE event is '�̺�Ʈ ���';
COMMENT ON COLUMN event.eventno is '�̺�Ʈ ��� ��ȣ';
COMMENT ON COLUMN event.managerno is '�����ڹ�ȣ';
COMMENT ON COLUMN event.presentno is '������ȣ';
COMMENT ON COLUMN event.title is '�̺�Ʈ����';
COMMENT ON COLUMN event.content is '�̺�Ʈ����';
COMMENT ON COLUMN event.period_start is '�̺�Ʈ���۳�';
COMMENT ON COLUMN event.period_end is '�̺�Ʈ���ᳯ';
COMMENT ON COLUMN event.writer is '�ۼ���';
COMMENT ON COLUMN event.usercnt is '�ִ������ο���';
COMMENT ON COLUMN event.image is '�̹���';
COMMENT ON COLUMN event.image_size is '�̹���ũ��';
COMMENT ON COLUMN event.thumb is '�����';
COMMENT ON COLUMN event.windate is '��÷��ǥ��';
COMMENT ON COLUMN event.wincnt is '��÷�ο���';
COMMENT ON COLUMN event.winner is '��÷�ڸ�';
COMMENT ON COLUMN event.rdate is '��ϳ�¥';

/*���*/
--��÷�� 3��/30�� 
INSERT INTO event(eventno, managerno, presentno, title, content, period_start, period_end, writer, usercnt, 
image, image_size, thumb, windate,wincnt,winner,rdate)
VALUES ((SELECT NVL(MAX(eventno), 0)+1 as eventno FROM event),
1,1,'ĿƮ 20% ���� �̺�Ʈ','�̺�Ʈ ���� ��������!������ �Ⱓ�ȿ� �湮�Ͻø� ĿƮ20%�����ص帳�ϴ�.','2018-12-12','2018-12-25','�̿��1', 30, 'cupon.jpg', 0, 'cupon_m.jpg', '2018-12-31', 3, '������', sysdate);
INSERT INTO event(eventno, managerno, presentno, title, content, period_start, period_end, writer, usercnt, 
image, image_size, thumb, windate,wincnt,winner,rdate)
VALUES ((SELECT NVL(MAX(eventno), 0)+1 as eventno FROM event),
1,1,'ĿƮ 20% ���� �̺�Ʈ','�̺�Ʈ ���� ��������!������ �Ⱓ�ȿ� �湮�Ͻø� ĿƮ20%�����ص帳�ϴ�.','2018-12-12','2018-12-25','�̿��1', 30, 'cupon.jpg', 0, 'cupon_m.jpg', '2018-12-31', 3, '������', sysdate);

/**********************************/
/* Table Name: �̺�Ʈ ������ */
/**********************************/  
CREATE TABLE eventuser(
		eventuserno                   		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno                      		NUMBER(10)		 NULL ,
		eventno                       		NUMBER(10)		 NULL ,
		joindate                      		DATE		 NOT NULL,
		win                           		VARCHAR2(10)	default '0',
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (eventno) REFERENCES event (eventno)
);

COMMENT ON TABLE eventuser is '�̺�Ʈ ������';
COMMENT ON COLUMN eventuser.eventuserno is '�̺�Ʈ ������ ��ȣ';
COMMENT ON COLUMN eventuser.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN eventuser.eventno is '�̺�Ʈ ��� ��ȣ';
COMMENT ON COLUMN eventuser.joindate is '������¥';
COMMENT ON COLUMN eventuser.win is '��÷����';

/*���*/
--(��÷N > �������)
INSERT INTO eventuser(eventuserno, memberno, eventno, joindate)
VALUES ((SELECT NVL(MAX(eventuserno), 0)+1 as eventuserno FROM eventuser),1,2,sysdate);
INSERT INTO eventuser(eventuserno, memberno, eventno, joindate)
VALUES ((SELECT NVL(MAX(eventuserno), 0)+1 as eventuserno FROM eventuser),2,2,sysdate);
INSERT INTO eventuser(eventuserno, memberno, eventno, joindate)
VALUES ((SELECT NVL(MAX(eventuserno), 0)+1 as eventuserno FROM eventuser),3,2,sysdate);

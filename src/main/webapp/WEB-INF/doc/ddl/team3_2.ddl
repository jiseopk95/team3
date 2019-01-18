DROP TABLE TABLE_20 CASCADE CONSTRAINTS;
DROP TABLE answer CASCADE CONSTRAINTS;
DROP TABLE question CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE choice CASCADE CONSTRAINTS;
DROP TABLE serveyparty CASCADE CONSTRAINTS;
DROP TABLE surveyitem CASCADE CONSTRAINTS;
DROP TABLE survey CASCADE CONSTRAINTS;
DROP TABLE eventuser CASCADE CONSTRAINTS;
DROP TABLE event CASCADE CONSTRAINTS;
DROP TABLE TABLE_21 CASCADE CONSTRAINTS;
DROP TABLE chart CASCADE CONSTRAINTS;
DROP TABLE style CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE animalstory CASCADE CONSTRAINTS;
DROP TABLE member_login CASCADE CONSTRAINTS;
DROP TABLE manager_login CASCADE CONSTRAINTS;
DROP TABLE manager CASCADE CONSTRAINTS;
DROP TABLE reservation CASCADE CONSTRAINTS;
DROP TABLE pet CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ȸ�� */
/**********************************/
CREATE TABLE member(
		memberno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		kind                          		VARCHAR2(10)		 NOT NULL,
		id                            		VARCHAR2(20)		 NOT NULL,
		password                      		VARCHAR2(20)		 NOT NULL,
		name                          		VARCHAR2(20)		 NOT NULL,
		phone                         		VARCHAR2(20)		 NOT NULL,
		email                         		VARCHAR2(10)		 NULL ,
		auth                          		VARCHAR(20)		 NULL ,
		date                          		DATE		 NOT NULL
);

DROP SEQUENCE member_memberno_SEQ;

CREATE SEQUENCE member_memberno_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER member_memberno_TRG
BEFORE INSERT ON member
FOR EACH ROW
BEGIN
IF :NEW.memberno IS NOT NULL THEN
  SELECT member_memberno_SEQ.NEXTVAL INTO :NEW.memberno FROM DUAL;
END IF;
END;

COMMENT ON TABLE member is 'ȸ��';
COMMENT ON COLUMN member.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN member.kind is '����';
COMMENT ON COLUMN member.id is '���̵�';
COMMENT ON COLUMN member.password is '��й�ȣ';
COMMENT ON COLUMN member.name is '�̸�';
COMMENT ON COLUMN member.phone is '��ȭ��ȣ';
COMMENT ON COLUMN member.email is '�̸���';
COMMENT ON COLUMN member.auth is '������ȣ';
COMMENT ON COLUMN member.date is '���Գ�¥';


/**********************************/
/* Table Name: �ݷ����� */
/**********************************/
CREATE TABLE pet(
		petno                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(100)		 NOT NULL,
		age                           		VARCHAR2(19)		 NOT NULL,
		gender                        		VARCHAR2(19)		 NOT NULL,
		pet_type                      		VARCHAR2(19)		 NOT NULL,
		neutralization                		VARCHAR2(10)		 NOT NULL,
		weight                        		VARCHAR2(10)		 NOT NULL,
		file                          		VARCHAR2(1000)		 NULL ,
		thumbs                        		VARCHAR2(1000)		 NULL ,
		filesizes                     		VARCHAR2(1000)		 NULL ,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

DROP SEQUENCE pet_petno_SEQ;

CREATE SEQUENCE pet_petno_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER pet_petno_TRG
BEFORE INSERT ON pet
FOR EACH ROW
BEGIN
IF :NEW.petno IS NOT NULL THEN
  SELECT pet_petno_SEQ.NEXTVAL INTO :NEW.petno FROM DUAL;
END IF;
END;

COMMENT ON TABLE pet is '�ݷ�����';
COMMENT ON COLUMN pet.petno is '������ȣ';
COMMENT ON COLUMN pet.name is '�����̸�';
COMMENT ON COLUMN pet.age is '��������';
COMMENT ON COLUMN pet.gender is '��������';
COMMENT ON COLUMN pet.pet_type is '��������';
COMMENT ON COLUMN pet.neutralization is '�߼�ȭ��������';
COMMENT ON COLUMN pet.weight is '������';
COMMENT ON COLUMN pet.file is '��������';
COMMENT ON COLUMN pet.thumbs is '�̸�����';
COMMENT ON COLUMN pet.filesizes is '����ũ��';
COMMENT ON COLUMN pet.memberno is 'ȸ����ȣ';


/**********************************/
/* Table Name: ���� */
/**********************************/
CREATE TABLE reservation(
		reservationno                 		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(50)		 NOT NULL,
		label                         		VARCHAR2(50)		 NOT NULL,
		resdate                       		VARCHAR2(50)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		restime                       		VARCHAR2(50)		 NOT NULL,
		content                       		VARCHAR2(1000)		 NOT NULL,
		restype                       		VARCHAR2(10)		 NOT NULL,
		managerno                     		NUMBER(10)		 NULL ,
		resitemno                     		NUMBER(10)		 NULL ,
		petno                         		NUMBER(10)		 NULL ,
  FOREIGN KEY (petno) REFERENCES pet (petno)
);

COMMENT ON TABLE reservation is '����';
COMMENT ON COLUMN reservation.reservationno is '����۹�ȣ';
COMMENT ON COLUMN reservation.title is '����';
COMMENT ON COLUMN reservation.label is '���̺�';
COMMENT ON COLUMN reservation.resdate is '���೯¥';
COMMENT ON COLUMN reservation.rdate is '�Է³�¥';
COMMENT ON COLUMN reservation.restime is '����ð�';
COMMENT ON COLUMN reservation.content is '����';
COMMENT ON COLUMN reservation.restype is '����';
COMMENT ON COLUMN reservation.managerno is '�����ڹ�ȣ';
COMMENT ON COLUMN reservation.resitemno is '�����׸� ��ȣ';
COMMENT ON COLUMN reservation.petno is '������ȣ';


/**********************************/
/* Table Name: ������ */
/**********************************/
CREATE TABLE manager(
		managerno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		kind                          		VARCHAR2(10)		 NOT NULL,
		id                            		VARCHAR2(10)		 NOT NULL,
		password                      		VARCHAR2(10)		 NOT NULL,
		name                          		VARCHAR2(10)		 NOT NULL,
		phone                         		VARCHAR2(10)		 NOT NULL,
		email                         		VARCHAR2(10)		 NULL ,
		auth                          		VARCHAR2(10)		 NULL ,
		ldate                         		DATE		 NOT NULL,
		reservationno                 		NUMBER(10)		 NULL ,
  FOREIGN KEY (reservationno) REFERENCES reservation (reservationno)
);

DROP SEQUENCE manager_managerno_SEQ;

CREATE SEQUENCE manager_managerno_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER manager_managerno_TRG
BEFORE INSERT ON manager
FOR EACH ROW
BEGIN
IF :NEW.managerno IS NOT NULL THEN
  SELECT manager_managerno_SEQ.NEXTVAL INTO :NEW.managerno FROM DUAL;
END IF;
END;

COMMENT ON TABLE manager is '������';
COMMENT ON COLUMN manager.managerno is '�����ڹ�ȣ';
COMMENT ON COLUMN manager.kind is '����';
COMMENT ON COLUMN manager.id is '���̵�';
COMMENT ON COLUMN manager.password is '��й�ȣ';
COMMENT ON COLUMN manager.name is '�̸�';
COMMENT ON COLUMN manager.phone is '��ȭ��ȣ';
COMMENT ON COLUMN manager.email is '�̸���';
COMMENT ON COLUMN manager.auth is '������ȣ';
COMMENT ON COLUMN manager.ldate is '���Գ�¥';
COMMENT ON COLUMN manager.reservationno is '����۹�ȣ';


/**********************************/
/* Table Name: �����ڷα��γ��� */
/**********************************/
CREATE TABLE manager_login(
		loginno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		managerno                     		NUMBER(10)		 NULL ,
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

DROP SEQUENCE manager_login_loginno_SEQ;

CREATE SEQUENCE manager_login_loginno_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER manager_login_loginno_TRG
BEFORE INSERT ON manager_login
FOR EACH ROW
BEGIN
IF :NEW.loginno IS NOT NULL THEN
  SELECT manager_login_loginno_SEQ.NEXTVAL INTO :NEW.loginno FROM DUAL;
END IF;
END;

COMMENT ON TABLE manager_login is '�����ڷα��γ���';
COMMENT ON COLUMN manager_login.loginno is '�α��γ�����ȣ';
COMMENT ON COLUMN manager_login.managerno is '�����ڹ�ȣ';


/**********************************/
/* Table Name: ȸ���α��γ��� */
/**********************************/
CREATE TABLE member_login(
		loginno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE member_login is 'ȸ���α��γ���';
COMMENT ON COLUMN member_login.loginno is '�α��γ�����ȣ';
COMMENT ON COLUMN member_login.memberno is 'ȸ����ȣ';


/**********************************/
/* Table Name: �ִϸ��̾߱� */
/**********************************/
CREATE TABLE animalstory(
		anino                         		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		anitype                       		VARCHAR2(10)		 NOT NULL,
		title                         		VARCHAR2(50)		 NOT NULL,
		content                       		VARCHAR2(3000)		 NOT NULL,
		files                         		VARCHAR2(1000)		 NULL ,
		thumb                         		VARCHAR2(1000)		 NULL ,
		size                          		VARCHAR2(1000)		 NULL ,
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
COMMENT ON COLUMN animalstory.size is '����ũ��';
COMMENT ON COLUMN animalstory.rdate is '�ۼ���';
COMMENT ON COLUMN animalstory.cnt is '��ȸ��';
COMMENT ON COLUMN animalstory.managerno is '�����ڹ�ȣ';


/**********************************/
/* Table Name: ī�װ� */
/**********************************/
CREATE TABLE category(
		categoryno                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno                      		NUMBER(10)		 NULL ,
		title                         		VARCHAR2(100)		 NOT NULL
);

DROP SEQUENCE category_categoryno_SEQ;

CREATE SEQUENCE category_categoryno_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER category_categoryno_TRG
BEFORE INSERT ON category
FOR EACH ROW
BEGIN
IF :NEW.categoryno IS NOT NULL THEN
  SELECT category_categoryno_SEQ.NEXTVAL INTO :NEW.categoryno FROM DUAL;
END IF;
END;

COMMENT ON TABLE category is 'ī�װ�';
COMMENT ON COLUMN category.categoryno is 'ī�װ���ȣ';
COMMENT ON COLUMN category.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN category.title is '����';


/**********************************/
/* Table Name: �̿뽺Ÿ�� */
/**********************************/
CREATE TABLE style(
		styleno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		categoryno                    		NUMBER(10)		 NULL ,
		title                         		VARCHAR2(20)		 NOT NULL,
		name                          		VARCHAR(20)		 NOT NULL,
		rname                         		VARCHAR(20)		 NOT NULL,
		like1                         		NUMBER(10)		 NULL ,
		email                         		NUMBER(10)		 NULL ,
		content                       		VARCHAR2(3000)		 NOT NULL,
		cnt                           		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		image                         		VARCHAR2(1000)		 NULL ,
		image_name                    		VARCHAR2(1000)		 NULL ,
		thumb                         		VARCHAR2(1000)		 NULL ,
		size                          		VARCHAR2(1000)		 DEFAULT 0		 NULL ,
		userno                        		NUMBER(10)		 NULL ,
		pay                           		NUMBER(10)		 NULL ,
		times                         		NUMBER(10)		 NULL ,
		rdate                         		DATE		 NOT NULL,
		managerno                     		NUMBER(10)		 NULL ,
  FOREIGN KEY (managerno) REFERENCES manager (managerno),
  FOREIGN KEY (categoryno) REFERENCES category (categoryno)
);

COMMENT ON TABLE style is '�̿뽺Ÿ��';
COMMENT ON COLUMN style.styleno is '��Ÿ�Ϲ�ȣ';
COMMENT ON COLUMN style.categoryno is 'ī�װ���ȣ';
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
COMMENT ON COLUMN style.size is '�̹���ũ��';
COMMENT ON COLUMN style.userno is 'ȸ����ȣ';
COMMENT ON COLUMN style.pay is '����';
COMMENT ON COLUMN style.times is '�ҿ�ð�';
COMMENT ON COLUMN style.rdate is '��ϳ�¥';
COMMENT ON COLUMN style.managerno is '�����ڹ�ȣ';


/**********************************/
/* Table Name: ���� ��Ʈ */
/**********************************/
CREATE TABLE chart(
		chartno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		managerno                     		NUMBER(10)		 NULL ,
		title                         		VARCHAR2(50)		 NOT NULL,
		symptom                       		VARCHAR2(3000)		 NOT NULL,
		sick                          		VARCHAR2(20)		 NOT NULL,
		medicine                      		VARCHAR2(50)		 NOT NULL,
		resdate                       		VARCHAR2(20)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		stay                          		VARCHAR2(10)		 NOT NULL,
		etc                           		VARCHAR2(100)		 NOT NULL,
		petno                         		NUMBER(10)		 NULL ,
  FOREIGN KEY (petno) REFERENCES pet (petno),
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE chart is '���� ��Ʈ';
COMMENT ON COLUMN chart.chartno is '��Ʈ ��ȣ';
COMMENT ON COLUMN chart.managerno is '�����ڹ�ȣ';
COMMENT ON COLUMN chart.title is '����';
COMMENT ON COLUMN chart.symptom is '����';
COMMENT ON COLUMN chart.sick is '�� �̸�';
COMMENT ON COLUMN chart.medicine is '������';
COMMENT ON COLUMN chart.resdate is '���� ��¥';
COMMENT ON COLUMN chart.rdate is '��ϳ�¥';
COMMENT ON COLUMN chart.stay is '�Կ�';
COMMENT ON COLUMN chart.etc is '��Ÿ';
COMMENT ON COLUMN chart.petno is '������ȣ';


/**********************************/
/* Table Name: �̺�Ʈ ���� */
/**********************************/
CREATE TABLE TABLE_21(

);

COMMENT ON TABLE TABLE_21 is '�̺�Ʈ ����';


/**********************************/
/* Table Name: �̺�Ʈ ��� */
/**********************************/
CREATE TABLE event(
		eventno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		managerno                     		NUMBER(10)		 NULL ,
		title                         		VARCHAR2(50)		 NOT NULL,
		content                       		VARCHAR2(3000)		 NOT NULL,
		period_start                  		VARCHAR2(30)		 NOT NULL,
		period_end                    		VARCHAR2(30)		 NULL ,
		writer                        		VARCHAR2(10)		 NOT NULL,
		usercnt                       		NUMBER(10)		 NOT NULL,
		image                         		VARCHAR2(1000)		 NULL ,
		image_size                    		VARCHAR2(1000)		 NULL ,
		thumb                         		VARCHAR2(1000)		 NULL ,
		present                       		VARCHAR2(50)		 NOT NULL,
		windate                       		VARCHAR2(20)		 NOT NULL,
		wincnt                        		INTEGER(10)		 NULL ,
		winner                        		VARCHAR2(50)		 NULL ,
		rdate                         		DATE		 NOT NULL,
  FOREIGN KEY () REFERENCES TABLE_21 ()
);

COMMENT ON TABLE event is '�̺�Ʈ ���';
COMMENT ON COLUMN event.eventno is '�̺�Ʈ ��� ��ȣ';
COMMENT ON COLUMN event.managerno is '�����ڹ�ȣ';
COMMENT ON COLUMN event.title is '�̺�Ʈ����';
COMMENT ON COLUMN event.content is '�̺�Ʈ����';
COMMENT ON COLUMN event.period_start is '�̺�Ʈ���۳�';
COMMENT ON COLUMN event.period_end is '�̺�Ʈ���ᳯ';
COMMENT ON COLUMN event.writer is '�ۼ���';
COMMENT ON COLUMN event.usercnt is '�ִ������ο���';
COMMENT ON COLUMN event.image is '�̹���';
COMMENT ON COLUMN event.image_size is '�̹���ũ��';
COMMENT ON COLUMN event.thumb is '�����';
COMMENT ON COLUMN event.present is '��÷����';
COMMENT ON COLUMN event.windate is '��÷��ǥ��';
COMMENT ON COLUMN event.wincnt is '��÷�ο���';
COMMENT ON COLUMN event.winner is '��÷�ڸ�';
COMMENT ON COLUMN event.rdate is '��ϳ�¥';


/**********************************/
/* Table Name: �̺�Ʈ ������ */
/**********************************/
CREATE TABLE eventuser(
		eventuserno                   		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		win                           		VARCHAR2(10)		 NOT NULL,
		present                       		VARCHAR2(50)		 NULL ,
		eventno                       		NUMBER(10)		 NULL ,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (eventno) REFERENCES event (eventno)
);

COMMENT ON TABLE eventuser is '�̺�Ʈ ������';
COMMENT ON COLUMN eventuser.eventuserno is '�̺�Ʈ ������ ��ȣ';
COMMENT ON COLUMN eventuser.win is '��÷����';
COMMENT ON COLUMN eventuser.present is '������';
COMMENT ON COLUMN eventuser.eventno is '�̺�Ʈ ��� ��ȣ';
COMMENT ON COLUMN eventuser.memberno is 'ȸ����ȣ';


/**********************************/
/* Table Name: �������� */
/**********************************/
CREATE TABLE survey(
		serveyno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		Q_cnt                         		NUMBER(10)		 NOT NULL,
		survey_title                  		VARCHAR2(50)		 NOT NULL,
		managerno                     		INTEGER(10)		 NULL ,
		seqno                         		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE survey is '��������';
COMMENT ON COLUMN survey.serveyno is '�������� ��ȣ';
COMMENT ON COLUMN survey.Q_cnt is '������';
COMMENT ON COLUMN survey.survey_title is '�������� Ÿ��Ʋ';
COMMENT ON COLUMN survey.managerno is '�����ڹ�ȣ';
COMMENT ON COLUMN survey.seqno is '��¼���';


/**********************************/
/* Table Name: �������� �׸� */
/**********************************/
CREATE TABLE surveyitem(
		serveyitemno                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		question                      		VARCHAR2(50)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		startdate                     		VARCHAR2(10)		 NOT NULL,
		enddate                       		VARCHAR2(10)		 NOT NULL,
		serveyno                      		NUMBER(10)		 NULL ,
		seqno                         		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (serveyno) REFERENCES survey (serveyno)
);

COMMENT ON TABLE surveyitem is '�������� �׸�';
COMMENT ON COLUMN surveyitem.serveyitemno is '�������� �׸� ��ȣ';
COMMENT ON COLUMN surveyitem.question is '��������';
COMMENT ON COLUMN surveyitem.rdate is '�ۼ�����';
COMMENT ON COLUMN surveyitem.startdate is '���۳�';
COMMENT ON COLUMN surveyitem.enddate is '���ᳯ';
COMMENT ON COLUMN surveyitem.serveyno is '�������� ��ȣ';
COMMENT ON COLUMN surveyitem.seqno is '��¼���';


/**********************************/
/* Table Name: �������� ���� */
/**********************************/
CREATE TABLE serveyparty(
		serveypartyno                 		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		serveyitemno                  		NUMBER(10)		 NULL ,
		rdate                         		DATE		 NOT NULL,
		memberno                      		INT(10)		 NULL ,
  FOREIGN KEY (serveyitemno) REFERENCES surveyitem (serveyitemno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE serveyparty is '�������� ����';
COMMENT ON COLUMN serveyparty.serveypartyno is '�������� ���� ��ȣ';
COMMENT ON COLUMN serveyparty.serveyitemno is '�������� �׸� ��ȣ';
COMMENT ON COLUMN serveyparty.rdate is '������¥';
COMMENT ON COLUMN serveyparty.memberno is 'ȸ����ȣ';


/**********************************/
/* Table Name: ������ �׸� */
/**********************************/
CREATE TABLE choice(
		choiceno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		A1                            		VARCHAR2(50)		 NOT NULL,
		A2                            		VARCHAR2(50)		 NOT NULL,
		A3                            		VARCHAR2(50)		 NOT NULL,
		A4                            		VARCHAR2(50)		 NOT NULL,
		serveyitemno                  		NUMBER(10)		 NULL ,
  FOREIGN KEY (serveyitemno) REFERENCES surveyitem (serveyitemno)
);

COMMENT ON TABLE choice is '������ �׸�';
COMMENT ON COLUMN choice.choiceno is '�׸��ȣ';
COMMENT ON COLUMN choice.A1 is '��������1';
COMMENT ON COLUMN choice.A2 is '��������2';
COMMENT ON COLUMN choice.A3 is '��������3';
COMMENT ON COLUMN choice.A4 is '��������4';
COMMENT ON COLUMN choice.serveyitemno is '�������� �׸� ��ȣ';


/**********************************/
/* Table Name: �ı� */
/**********************************/
CREATE TABLE review(
		reviewno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(30)		 NOT NULL,
		title                         		VARCHAR2(100)		 NOT NULL,
		content                       		VARCHAR2(3000)		 NULL ,
		rdate                         		DATETIME		 NULL ,
		files                         		VARCHAR2(30)		 NULL ,
		filesize                      		VARCHAR2(1000)		 NULL ,
		thumbs                        		VARCHAR2(1000)		 NULL ,
		mno                           		NUMBER(10)		 NULL ,
		num                           		NUMBER(10)		 NULL ,
		score                         		NUMBER(10)		 NULL ,
		thumbs                        		VARCHAR2(30)		 NULL ,
		categoryno                    		NUMBER(10)		 NULL ,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (categoryno) REFERENCES category (categoryno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

DROP SEQUENCE review_reviewno_SEQ;

CREATE SEQUENCE review_reviewno_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER review_reviewno_TRG
BEFORE INSERT ON review
FOR EACH ROW
BEGIN
IF :NEW.reviewno IS NOT NULL THEN
  SELECT review_reviewno_SEQ.NEXTVAL INTO :NEW.reviewno FROM DUAL;
END IF;
END;

COMMENT ON TABLE review is '�ı�';
COMMENT ON COLUMN review.reviewno is '�ı��ȣ';
COMMENT ON COLUMN review.name is '�۾���';
COMMENT ON COLUMN review.title is '����';
COMMENT ON COLUMN review.content is '����';
COMMENT ON COLUMN review.rdate is '��¥';
COMMENT ON COLUMN review.files is '����';
COMMENT ON COLUMN review.filesize is '����ũ��';
COMMENT ON COLUMN review.thumbs is '�̸�����';
COMMENT ON COLUMN review.mno is 'ȸ����ȣ';
COMMENT ON COLUMN review.num is '��ȸ��';
COMMENT ON COLUMN review.score is '����';
COMMENT ON COLUMN review.thumbs is '�̸�����';
COMMENT ON COLUMN review.categoryno is 'ī�װ���ȣ';
COMMENT ON COLUMN review.memberno is 'ȸ����ȣ';


/**********************************/
/* Table Name: ���� */
/**********************************/
CREATE TABLE question(
		questionno                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(100)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		content                       		VARCHAR2(3000)		 NOT NULL,
		name                          		VARCHAR2(30)		 NOT NULL,
		files                         		VARCHAR2(30)		 NULL ,
		thumbs                        		VARCHAR2(30)		 NULL ,
		filesize                      		VARCHAR2(1000)		 NULL ,
		num                           		NUMBER(10)		 NOT NULL,
		categoryno                    		NUMBER(10)		 NULL ,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (categoryno) REFERENCES category (categoryno)
);

DROP SEQUENCE question_questionno_SEQ;

CREATE SEQUENCE question_questionno_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER question_questionno_TRG
BEFORE INSERT ON question
FOR EACH ROW
BEGIN
IF :NEW.questionno IS NOT NULL THEN
  SELECT question_questionno_SEQ.NEXTVAL INTO :NEW.questionno FROM DUAL;
END IF;
END;

COMMENT ON TABLE question is '����';
COMMENT ON COLUMN question.questionno is '������ȣ';
COMMENT ON COLUMN question.title is '����';
COMMENT ON COLUMN question.rdate is '��¥';
COMMENT ON COLUMN question.content is '����';
COMMENT ON COLUMN question.name is '�۾���';
COMMENT ON COLUMN question.files is '����';
COMMENT ON COLUMN question.thumbs is '�̸�����';
COMMENT ON COLUMN question.filesize is '����ũ��';
COMMENT ON COLUMN question.num is '��ȸ��';
COMMENT ON COLUMN question.categoryno is 'ī�װ���ȣ';
COMMENT ON COLUMN question.memberno is 'ȸ����ȣ';


/**********************************/
/* Table Name: �亯 */
/**********************************/
CREATE TABLE answer(
		answerno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		questionno                    		NUMBER(10)		 NULL ,
		title                         		VARCHAR2(100)		 NOT NULL,
		name                          		VARCHAR2(30)		 NOT NULL,
		emoticon                      		VARCHAR2(30)		 NULL ,
		content                       		VARCHAR2(3000)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
  FOREIGN KEY (questionno) REFERENCES question (questionno)
);

DROP SEQUENCE answer_answerno_SEQ;

CREATE SEQUENCE answer_answerno_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER answer_answerno_TRG
BEFORE INSERT ON answer
FOR EACH ROW
BEGIN
IF :NEW.answerno IS NOT NULL THEN
  SELECT answer_answerno_SEQ.NEXTVAL INTO :NEW.answerno FROM DUAL;
END IF;
END;

COMMENT ON TABLE answer is '�亯';
COMMENT ON COLUMN answer.answerno is '�亯��ȣ';
COMMENT ON COLUMN answer.questionno is '������ȣ';
COMMENT ON COLUMN answer.title is '����';
COMMENT ON COLUMN answer.name is '�۾���';
COMMENT ON COLUMN answer.emoticon is '�̸�Ƽ��';
COMMENT ON COLUMN answer.content is '����';
COMMENT ON COLUMN answer.rdate is '��¥';


/**********************************/
/* Table Name: �̺�Ʈ ��÷ */
/**********************************/
CREATE TABLE TABLE_20(
		memberno                      		NUMBER(10)		 NULL ,
		eventno                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (eventno) REFERENCES event (eventno)
);

COMMENT ON TABLE TABLE_20 is '�̺�Ʈ ��÷';
COMMENT ON COLUMN TABLE_20.memberno is 'ȸ����ȣ';
COMMENT ON COLUMN TABLE_20.eventno is '�̺�Ʈ ��� ��ȣ';



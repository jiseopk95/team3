DROP TABLE review;
DROP TABLE question;
DROP TABLE category;

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


COMMENT ON TABLE category is 'ī�װ�';
COMMENT ON COLUMN category.categoryno is 'ī�װ���ȣ';
COMMENT ON COLUMN category.categrpno is 'ī�װ� �׷� ��ȣ';
COMMENT ON COLUMN category.title is '�Խ��� �̸�';
COMMENT ON COLUMN category.cnt is '��ϵ� �� ��';
COMMENT ON COLUMN category.seqno is '��� ����';
COMMENT ON COLUMN category.rdate is '��ϳ�¥';

-- ����
INSERT INTO category(categoryno, title,rdate)
VALUES(1, '��������',sysdate);
             
INSERT INTO category(categoryno, title,rdate)
VALUES(2, '�����̿�',sysdate);


/** member ���̺�*/
CREATE TABLE member(
	memberno NUMBER(10)	 NOT NULL	 PRIMARY KEY
);
INSERT INTO  member(memberno)
VALUES(1);

SELECT categoryno, title, rdate
FROM category
ORDER BY categoryno ASC;

DROP TABLE review;
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
             
INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '���', '�̿���� �ı�', '�̿� ���� ����',sysdate,
             'style.jpg', 0, 'style_m.jpg', 0 , 5, 2 , 4);
             
 INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '�̸�', '�������� �ı�', '���۵��� �Ϳ���',sysdate,
             'style1.jpg', 0, 'style1_m.jpg', 0 , 5, 2 , 5);
             
-- �ı� ����
DELETE FROM review
WHERE reviewno=1

-- �ı� ����
UPDATE review
SET title='�ܿ�', contents='���ݵ� �ո����̰�...',
     thumbs='snow_t.jpg', files='snow.jpg', sizes=1500
WHERE reviewno=1;

-- ��ü ���
SELECT reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, word, memberno
FROM review
ORDER BY reviewno ASC;

-- �˻�
SELECT reviewno,
          categoryno, memberno, title, score, thumbs, files, filesize, num, rdate, 
          word
FROM review
WHERE categoryno=1 AND title LIKE '%�ٴ�%'
ORDER BY reviewno DESC;

/**********************************/
/* Table Name: ���� */
/**********************************/
CREATE TABLE question(
questionno                    	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
title                         	VARCHAR2(100)	 NOT NULL,
rdate                         	DATE	 NOT NULL,
content                       	CLOB	 NOT NULL,
name                          	VARCHAR2(30)	 NOT NULL,
files                         	VARCHAR2(30)	 NULL ,
thumbs                        	VARCHAR2(30)	 NULL ,
filesize                      	VARCHAR2(1000)	 NULL ,
num                           	NUMBER(10)	       NULL,
passwd                            	VARCHAR2(30)	 NULL ,
visible                           	 CHAR(1)    DEFAULT 'Y' NOT NULL,
categoryno                    	NUMBER(10)	 NULL ,
memberno                      	NUMBER(10)	 NULL ,
  FOREIGN KEY (categoryno) REFERENCES category (categoryno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

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
COMMENT ON COLUMN question.passwd is '��й�ȣ';
COMMENT ON COLUMN question.visible is '��б� ����';
COMMENT ON COLUMN question.categoryno is 'ī�װ���ȣ';
COMMENT ON COLUMN question.memberno is 'ȸ����ȣ';


CREATE TABLE question(
questionno                    	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
title                         	VARCHAR2(100)	 NOT NULL,
rdate                         	DATE	 NOT NULL,
content                       	CLOB	 NOT NULL,
name                          	VARCHAR2(30)	 NOT NULL,
files                         	VARCHAR2(30)	 NULL ,
filesize                      	VARCHAR2(1000)	 NULL ,
num                           	NUMBER(10)	       NULL,
passwd                            	VARCHAR2(30)	 NULL ,
visible                           	 CHAR(1)    DEFAULT 'Y' NOT NULL,
categoryno                    	NUMBER(10)	 NULL ,
memberno                      	NUMBER(10)	 NULL ,
  FOREIGN KEY (categoryno) REFERENCES category (categoryno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
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
COMMENT ON COLUMN question.categoryno is 'ī�װ���ȣ';
COMMENT ON COLUMN question.memberno is 'ȸ����ȣ';


-- ���� ���
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '������ ���� �ð��� ��� �ǳ���', sysdate, '����ģ����.....',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 1 , 1);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '����', sysdate, '��������',
             '','',  0, '' , 'Y', 0 , 3, 1 , 2);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '�̿� ���� ����', sysdate, '�̿� ���� ����.....',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 2 , 3);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '���ݹ���', sysdate, '�����̶� �ҿ�ð��� �ñ��ؿ�',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 2 , 4);
                                                                                                                      
-- ���� ����
DELETE FROM question
WHERE questionno=1

-- �������� ����
UPDATE review
SET title='�ܿ�', contents='���ݵ� �ո����̰�...',
     thumbs='snow_t.jpg', files='snow.jpg', sizes=1500
WHERE reviewno=1;

-- ��б� ���� ����
UPDATE review
SET visible='N', passwd='abc'
WHERE reviewno=1;

-- ��ü ���
SELECT questionno, title, rdate, content, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno
FROM question
ORDER BY questionno ASC;

SELECT questionno, title, rdate, content, name, files,filesize, num, passwd, visible , categoryno, memberno
FROM question
ORDER BY questionno ASC;

--��й�ȣ Ȯ��
  SELECT COUNT(*) as cnt
  FROM question
  WHERE questionno = 1 AND passwd=1234;


/**********************************/
/* Table Name: �亯 */
/**********************************/
CREATE TABLE answer(
answerno                      	NUMBER(10)	 NOT NULL	 PRIMARY KEY,
questionno                    	NUMBER(10)	 NULL ,
title                         	VARCHAR2(100)	 NOT NULL,
name                          	VARCHAR2(30)	 NOT NULL,
emoticon                      	VARCHAR2(30)	 NULL ,
content                       	CLOB	 NOT NULL,
rdate                         	DATE	 NOT NULL,
managerno                     	NUMBER(10)	 NULL ,
  FOREIGN KEY (questionno) REFERENCES question (questionno),
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE answer is '�亯';
COMMENT ON COLUMN answer.answerno is '�亯��ȣ';
COMMENT ON COLUMN answer.questionno is '������ȣ';
COMMENT ON COLUMN answer.title is '����';
COMMENT ON COLUMN answer.name is '�۾���';
COMMENT ON COLUMN answer.emoticon is '�̸�Ƽ��';
COMMENT ON COLUMN answer.content is '����';
COMMENT ON COLUMN answer.rdate is '��¥';
COMMENT ON COLUMN answer.managerno is '�����ڹ�ȣ';

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
             
INSERT INTO answer(answerno, questionno, title, name, emoticon, content, rdate, managerno)
VALUES((SELECT NVL(MAX(answerno), 0)+1 as answerno FROM answer), 2, '�亯', '������',
             'emoticon.jpg','�̿� ����2�� ���� �亯�Դϴ�.', sysdate, 2);             
             
-- �亯 ����
DELETE FROM answer
WHERE answerno=1

-- �亯���� ����
UPDATE answer
SET title='�亯', contents='������ �亯�Դϴ�.',
WHERE answerno=1;


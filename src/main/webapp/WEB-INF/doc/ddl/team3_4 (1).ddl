DROP TABLE review;
DROP TABLE question;
DROP TABLE category;

/**********************************/
/* Table Name: 카테고리 */
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


COMMENT ON TABLE category is '카테고리';
COMMENT ON COLUMN category.categoryno is '카테고리번호';
COMMENT ON COLUMN category.categrpno is '카테고리 그룹 번호';
COMMENT ON COLUMN category.title is '게시판 이름';
COMMENT ON COLUMN category.cnt is '등록된 글 수';
COMMENT ON COLUMN category.seqno is '출력 순서';
COMMENT ON COLUMN category.rdate is '등록날짜';

-- 삽입
INSERT INTO category(categoryno, title,rdate)
VALUES(1, '동물병원',sysdate);
             
INSERT INTO category(categoryno, title,rdate)
VALUES(2, '동물미용',sysdate);


/** member 테이블*/
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
/* Table Name: 후기 */
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


COMMENT ON TABLE review is '후기';
COMMENT ON COLUMN review.reviewno is '후기번호';
COMMENT ON COLUMN review.name is '글쓴이';
COMMENT ON COLUMN review.title is '제목';
COMMENT ON COLUMN review.contents is '내용';
COMMENT ON COLUMN review.rdate is '날짜';
COMMENT ON COLUMN review.files is '파일';
COMMENT ON COLUMN review.filesize is '파일크기';
COMMENT ON COLUMN review.thumbs is '미리보기';
COMMENT ON COLUMN review.num is '조회수';
COMMENT ON COLUMN review.score is '평점';
COMMENT ON COLUMN review.word is '검색어';
COMMENT ON COLUMN review.categoryno is '카테고리번호';
COMMENT ON COLUMN review.memberno is '회원번호';

-- 후기 등록
INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '안해원', '예방접종 후기', '정말친절한.....',sysdate,
             'fall.jpg', 0, 'fall_m.jpg', 0 , 3, 1 , 1);
             
INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '아로미', '어제 검사받으러 갔어요', '검사받은 후기',sysdate,
             'dog.jpg', 0, 'dog_m.jpg', 0 , 4, 1 , 2);

INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '왕눈이', '의사선생님이 너무 친절해요', '내용 내용 내용',sysdate,
             '', 0, '', 0 , 5, 1 , 3);
             
INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '김모씨', '미용관련 후기', '미용 관련 내용',sysdate,
             'style.jpg', 0, 'style_m.jpg', 0 , 5, 2 , 4);
             
 INSERT INTO review(reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), '이모씨', '곰돌이컷 후기', '동글동글 귀여운',sysdate,
             'style1.jpg', 0, 'style1_m.jpg', 0 , 5, 2 , 5);
             
-- 후기 삭제
DELETE FROM review
WHERE reviewno=1

-- 후기 수정
UPDATE review
SET title='겨울', contents='가격도 합리적이고...',
     thumbs='snow_t.jpg', files='snow.jpg', sizes=1500
WHERE reviewno=1;

-- 전체 목록
SELECT reviewno, name, title, contents, rdate, files, filesize, thumbs, num, score, categoryno, word, memberno
FROM review
ORDER BY reviewno ASC;

-- 검색
SELECT reviewno,
          categoryno, memberno, title, score, thumbs, files, filesize, num, rdate, 
          word
FROM review
WHERE categoryno=1 AND title LIKE '%바다%'
ORDER BY reviewno DESC;

/**********************************/
/* Table Name: 질문 */
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

COMMENT ON TABLE question is '질문';
COMMENT ON COLUMN question.questionno is '질문번호';
COMMENT ON COLUMN question.title is '제목';
COMMENT ON COLUMN question.rdate is '날짜';
COMMENT ON COLUMN question.content is '내용';
COMMENT ON COLUMN question.name is '글쓴이';
COMMENT ON COLUMN question.files is '파일';
COMMENT ON COLUMN question.thumbs is '미리보기';
COMMENT ON COLUMN question.filesize is '파일크기';
COMMENT ON COLUMN question.num is '조회수';
COMMENT ON COLUMN question.passwd is '비밀번호';
COMMENT ON COLUMN question.visible is '비밀글 여부';
COMMENT ON COLUMN question.categoryno is '카테고리번호';
COMMENT ON COLUMN question.memberno is '회원번호';


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

COMMENT ON TABLE question is '질문';
COMMENT ON COLUMN question.questionno is '질문번호';
COMMENT ON COLUMN question.title is '제목';
COMMENT ON COLUMN question.rdate is '날짜';
COMMENT ON COLUMN question.content is '내용';
COMMENT ON COLUMN question.name is '글쓴이';
COMMENT ON COLUMN question.files is '파일';
COMMENT ON COLUMN question.filesize is '파일크기';
COMMENT ON COLUMN question.num is '조회수';
COMMENT ON COLUMN question.passwd is '비밀번호';
COMMENT ON COLUMN question.visible is '비밀글 여부';
COMMENT ON COLUMN question.categoryno is '카테고리번호';
COMMENT ON COLUMN question.memberno is '회원번호';


-- 질문 등록
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '공휴일 진료 시간이 어떻게 되나요', sysdate, '정말친절한.....',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 1 , 1);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '질문', sysdate, '질문내용',
             '','',  0, '' , 'Y', 0 , 3, 1 , 2);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '미용 관련 질문', sysdate, '미용 질문 내용.....',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 2 , 3);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '가격문의', sysdate, '가격이랑 소요시간이 궁금해요',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 2 , 4);
                                                                                                                      
-- 질문 삭제
DELETE FROM question
WHERE questionno=1

-- 질문내용 수정
UPDATE review
SET title='겨울', contents='가격도 합리적이고...',
     thumbs='snow_t.jpg', files='snow.jpg', sizes=1500
WHERE reviewno=1;

-- 비밀글 여부 수정
UPDATE review
SET visible='N', passwd='abc'
WHERE reviewno=1;

-- 전체 목록
SELECT questionno, title, rdate, content, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno
FROM question
ORDER BY questionno ASC;

SELECT questionno, title, rdate, content, name, files,filesize, num, passwd, visible , categoryno, memberno
FROM question
ORDER BY questionno ASC;

--비밀번호 확인
  SELECT COUNT(*) as cnt
  FROM question
  WHERE questionno = 1 AND passwd=1234;


/**********************************/
/* Table Name: 답변 */
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

COMMENT ON TABLE answer is '답변';
COMMENT ON COLUMN answer.answerno is '답변번호';
COMMENT ON COLUMN answer.questionno is '질문번호';
COMMENT ON COLUMN answer.title is '제목';
COMMENT ON COLUMN answer.name is '글쓴이';
COMMENT ON COLUMN answer.emoticon is '이모티콘';
COMMENT ON COLUMN answer.content is '내용';
COMMENT ON COLUMN answer.rdate is '날짜';
COMMENT ON COLUMN answer.managerno is '관리자번호';

-- 답변 등록
INSERT INTO answer(answerno, questionno, title, name, emoticon, content, rdate, managerno)
VALUES((SELECT NVL(MAX(answerno), 0)+1 as answerno FROM answer), 1, '답변', '관리자',
             'emoticon.jpg','질문에 대한 답변입니다.', sysdate, 1);
             
INSERT INTO answer(answerno, questionno, title, name, emoticon, content, rdate, managerno)
VALUES((SELECT NVL(MAX(answerno), 0)+1 as answerno FROM answer), 2, '답변', '관리자',
             'emoticon.jpg','질문2에 대한 답변입니다.', sysdate, 1);
             
INSERT INTO answer(answerno, questionno, title, name, emoticon, content, rdate, managerno)
VALUES((SELECT NVL(MAX(answerno), 0)+1 as answerno FROM answer), 1, '답변', '관리자',
             'emoticon.jpg','미용 질문1에 대한 답변입니다.', sysdate, 2);
             
INSERT INTO answer(answerno, questionno, title, name, emoticon, content, rdate, managerno)
VALUES((SELECT NVL(MAX(answerno), 0)+1 as answerno FROM answer), 2, '답변', '관리자',
             'emoticon.jpg','미용 질문2에 대한 답변입니다.', sysdate, 2);             
             
-- 답변 삭제
DELETE FROM answer
WHERE answerno=1

-- 답변내용 수정
UPDATE answer
SET title='답변', contents='수정된 답변입니다.',
WHERE answerno=1;


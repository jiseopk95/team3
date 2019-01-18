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
/* Table Name: 회원 */
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

COMMENT ON TABLE member is '회원';
COMMENT ON COLUMN member.memberno is '회원번호';
COMMENT ON COLUMN member.kind is '권한';
COMMENT ON COLUMN member.id is '아이디';
COMMENT ON COLUMN member.password is '비밀번호';
COMMENT ON COLUMN member.name is '이름';
COMMENT ON COLUMN member.phone is '전화번호';
COMMENT ON COLUMN member.email is '이메일';
COMMENT ON COLUMN member.auth is '인증번호';
COMMENT ON COLUMN member.date is '가입날짜';


/**********************************/
/* Table Name: 반려동물 */
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

COMMENT ON TABLE pet is '반려동물';
COMMENT ON COLUMN pet.petno is '동물번호';
COMMENT ON COLUMN pet.name is '동물이름';
COMMENT ON COLUMN pet.age is '동물나이';
COMMENT ON COLUMN pet.gender is '동물성별';
COMMENT ON COLUMN pet.pet_type is '동물종류';
COMMENT ON COLUMN pet.neutralization is '중성화수술여부';
COMMENT ON COLUMN pet.weight is '몸무게';
COMMENT ON COLUMN pet.file is '동물사진';
COMMENT ON COLUMN pet.thumbs is '미리보기';
COMMENT ON COLUMN pet.filesizes is '파일크기';
COMMENT ON COLUMN pet.memberno is '회원번호';


/**********************************/
/* Table Name: 예약 */
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

COMMENT ON TABLE reservation is '예약';
COMMENT ON COLUMN reservation.reservationno is '예약글번호';
COMMENT ON COLUMN reservation.title is '제목';
COMMENT ON COLUMN reservation.label is '레이블';
COMMENT ON COLUMN reservation.resdate is '예약날짜';
COMMENT ON COLUMN reservation.rdate is '입력날짜';
COMMENT ON COLUMN reservation.restime is '예약시간';
COMMENT ON COLUMN reservation.content is '내용';
COMMENT ON COLUMN reservation.restype is '종류';
COMMENT ON COLUMN reservation.managerno is '관리자번호';
COMMENT ON COLUMN reservation.resitemno is '예약항목 번호';
COMMENT ON COLUMN reservation.petno is '동물번호';


/**********************************/
/* Table Name: 관리자 */
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

COMMENT ON TABLE manager is '관리자';
COMMENT ON COLUMN manager.managerno is '관리자번호';
COMMENT ON COLUMN manager.kind is '권한';
COMMENT ON COLUMN manager.id is '아이디';
COMMENT ON COLUMN manager.password is '비밀번호';
COMMENT ON COLUMN manager.name is '이름';
COMMENT ON COLUMN manager.phone is '전화번호';
COMMENT ON COLUMN manager.email is '이메일';
COMMENT ON COLUMN manager.auth is '인증번호';
COMMENT ON COLUMN manager.ldate is '가입날짜';
COMMENT ON COLUMN manager.reservationno is '예약글번호';


/**********************************/
/* Table Name: 관리자로그인내역 */
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

COMMENT ON TABLE manager_login is '관리자로그인내역';
COMMENT ON COLUMN manager_login.loginno is '로그인내역번호';
COMMENT ON COLUMN manager_login.managerno is '관리자번호';


/**********************************/
/* Table Name: 회원로그인내역 */
/**********************************/
CREATE TABLE member_login(
		loginno                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		memberno                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE member_login is '회원로그인내역';
COMMENT ON COLUMN member_login.loginno is '로그인내역번호';
COMMENT ON COLUMN member_login.memberno is '회원번호';


/**********************************/
/* Table Name: 애니멀이야기 */
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

COMMENT ON TABLE animalstory is '애니멀이야기';
COMMENT ON COLUMN animalstory.anino is '글번호';
COMMENT ON COLUMN animalstory.anitype is '종류';
COMMENT ON COLUMN animalstory.title is '제목';
COMMENT ON COLUMN animalstory.content is '내용';
COMMENT ON COLUMN animalstory.files is '파일이름';
COMMENT ON COLUMN animalstory.thumb is '썸네일이름';
COMMENT ON COLUMN animalstory.size is '파일크기';
COMMENT ON COLUMN animalstory.rdate is '작성일';
COMMENT ON COLUMN animalstory.cnt is '조회수';
COMMENT ON COLUMN animalstory.managerno is '관리자번호';


/**********************************/
/* Table Name: 카테고리 */
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

COMMENT ON TABLE category is '카테고리';
COMMENT ON COLUMN category.categoryno is '카테고리번호';
COMMENT ON COLUMN category.memberno is '회원번호';
COMMENT ON COLUMN category.title is '제목';


/**********************************/
/* Table Name: 미용스타일 */
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

COMMENT ON TABLE style is '미용스타일';
COMMENT ON COLUMN style.styleno is '스타일번호';
COMMENT ON COLUMN style.categoryno is '카테고리번호';
COMMENT ON COLUMN style.title is '제목';
COMMENT ON COLUMN style.name is '스타일이름';
COMMENT ON COLUMN style.rname is '작성자';
COMMENT ON COLUMN style.like1 is '좋아요수';
COMMENT ON COLUMN style.email is '이메일';
COMMENT ON COLUMN style.content is '내용';
COMMENT ON COLUMN style.cnt is '조회수';
COMMENT ON COLUMN style.image is '이미지';
COMMENT ON COLUMN style.image_name is '저장이미지명';
COMMENT ON COLUMN style.thumb is '썸네일';
COMMENT ON COLUMN style.size is '이미지크기';
COMMENT ON COLUMN style.userno is '회원번호';
COMMENT ON COLUMN style.pay is '가격';
COMMENT ON COLUMN style.times is '소요시간';
COMMENT ON COLUMN style.rdate is '등록날짜';
COMMENT ON COLUMN style.managerno is '관리자번호';


/**********************************/
/* Table Name: 진료 차트 */
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

COMMENT ON TABLE chart is '진료 차트';
COMMENT ON COLUMN chart.chartno is '차트 번호';
COMMENT ON COLUMN chart.managerno is '관리자번호';
COMMENT ON COLUMN chart.title is '제목';
COMMENT ON COLUMN chart.symptom is '증상';
COMMENT ON COLUMN chart.sick is '병 이름';
COMMENT ON COLUMN chart.medicine is '투여약';
COMMENT ON COLUMN chart.resdate is '예약 날짜';
COMMENT ON COLUMN chart.rdate is '등록날짜';
COMMENT ON COLUMN chart.stay is '입원';
COMMENT ON COLUMN chart.etc is '기타';
COMMENT ON COLUMN chart.petno is '동물번호';


/**********************************/
/* Table Name: 이벤트 선물 */
/**********************************/
CREATE TABLE TABLE_21(

);

COMMENT ON TABLE TABLE_21 is '이벤트 선물';


/**********************************/
/* Table Name: 이벤트 목록 */
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

COMMENT ON TABLE event is '이벤트 목록';
COMMENT ON COLUMN event.eventno is '이벤트 목록 번호';
COMMENT ON COLUMN event.managerno is '관리자번호';
COMMENT ON COLUMN event.title is '이벤트제목';
COMMENT ON COLUMN event.content is '이벤트내용';
COMMENT ON COLUMN event.period_start is '이벤트시작날';
COMMENT ON COLUMN event.period_end is '이벤트만료날';
COMMENT ON COLUMN event.writer is '작성자';
COMMENT ON COLUMN event.usercnt is '최대응모인원수';
COMMENT ON COLUMN event.image is '이미지';
COMMENT ON COLUMN event.image_size is '이미지크기';
COMMENT ON COLUMN event.thumb is '썸네일';
COMMENT ON COLUMN event.present is '당첨선물';
COMMENT ON COLUMN event.windate is '당첨발표일';
COMMENT ON COLUMN event.wincnt is '당첨인원수';
COMMENT ON COLUMN event.winner is '당첨자명';
COMMENT ON COLUMN event.rdate is '등록날짜';


/**********************************/
/* Table Name: 이벤트 참여자 */
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

COMMENT ON TABLE eventuser is '이벤트 참여자';
COMMENT ON COLUMN eventuser.eventuserno is '이벤트 참여자 번호';
COMMENT ON COLUMN eventuser.win is '당첨여부';
COMMENT ON COLUMN eventuser.present is '선물함';
COMMENT ON COLUMN eventuser.eventno is '이벤트 목록 번호';
COMMENT ON COLUMN eventuser.memberno is '회원번호';


/**********************************/
/* Table Name: 설문조사 */
/**********************************/
CREATE TABLE survey(
		serveyno                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		Q_cnt                         		NUMBER(10)		 NOT NULL,
		survey_title                  		VARCHAR2(50)		 NOT NULL,
		managerno                     		INTEGER(10)		 NULL ,
		seqno                         		NUMBER(10)		 NOT NULL,
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
);

COMMENT ON TABLE survey is '설문조사';
COMMENT ON COLUMN survey.serveyno is '설문조사 번호';
COMMENT ON COLUMN survey.Q_cnt is '문제수';
COMMENT ON COLUMN survey.survey_title is '설문조사 타이틀';
COMMENT ON COLUMN survey.managerno is '관리자번호';
COMMENT ON COLUMN survey.seqno is '출력순서';


/**********************************/
/* Table Name: 설문조사 항목 */
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

COMMENT ON TABLE surveyitem is '설문조사 항목';
COMMENT ON COLUMN surveyitem.serveyitemno is '설문조사 항목 번호';
COMMENT ON COLUMN surveyitem.question is '질문내용';
COMMENT ON COLUMN surveyitem.rdate is '작성일자';
COMMENT ON COLUMN surveyitem.startdate is '시작날';
COMMENT ON COLUMN surveyitem.enddate is '만료날';
COMMENT ON COLUMN surveyitem.serveyno is '설문조사 번호';
COMMENT ON COLUMN surveyitem.seqno is '출력순서';


/**********************************/
/* Table Name: 설문조사 참여 */
/**********************************/
CREATE TABLE serveyparty(
		serveypartyno                 		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		serveyitemno                  		NUMBER(10)		 NULL ,
		rdate                         		DATE		 NOT NULL,
		memberno                      		INT(10)		 NULL ,
  FOREIGN KEY (serveyitemno) REFERENCES surveyitem (serveyitemno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);

COMMENT ON TABLE serveyparty is '설문조사 참여';
COMMENT ON COLUMN serveyparty.serveypartyno is '설문조사 참여 번호';
COMMENT ON COLUMN serveyparty.serveyitemno is '설문조사 항목 번호';
COMMENT ON COLUMN serveyparty.rdate is '참여날짜';
COMMENT ON COLUMN serveyparty.memberno is '회원번호';


/**********************************/
/* Table Name: 문제별 항목 */
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

COMMENT ON TABLE choice is '문제별 항목';
COMMENT ON COLUMN choice.choiceno is '항목번호';
COMMENT ON COLUMN choice.A1 is '사지선다1';
COMMENT ON COLUMN choice.A2 is '사지선다2';
COMMENT ON COLUMN choice.A3 is '사지선다3';
COMMENT ON COLUMN choice.A4 is '사지선다4';
COMMENT ON COLUMN choice.serveyitemno is '설문조사 항목 번호';


/**********************************/
/* Table Name: 후기 */
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

COMMENT ON TABLE review is '후기';
COMMENT ON COLUMN review.reviewno is '후기번호';
COMMENT ON COLUMN review.name is '글쓴이';
COMMENT ON COLUMN review.title is '제목';
COMMENT ON COLUMN review.content is '내용';
COMMENT ON COLUMN review.rdate is '날짜';
COMMENT ON COLUMN review.files is '파일';
COMMENT ON COLUMN review.filesize is '파일크기';
COMMENT ON COLUMN review.thumbs is '미리보기';
COMMENT ON COLUMN review.mno is '회원번호';
COMMENT ON COLUMN review.num is '조회수';
COMMENT ON COLUMN review.score is '평점';
COMMENT ON COLUMN review.thumbs is '미리보기';
COMMENT ON COLUMN review.categoryno is '카테고리번호';
COMMENT ON COLUMN review.memberno is '회원번호';


/**********************************/
/* Table Name: 질문 */
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
COMMENT ON COLUMN question.categoryno is '카테고리번호';
COMMENT ON COLUMN question.memberno is '회원번호';


/**********************************/
/* Table Name: 답변 */
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

COMMENT ON TABLE answer is '답변';
COMMENT ON COLUMN answer.answerno is '답변번호';
COMMENT ON COLUMN answer.questionno is '질문번호';
COMMENT ON COLUMN answer.title is '제목';
COMMENT ON COLUMN answer.name is '글쓴이';
COMMENT ON COLUMN answer.emoticon is '이모티콘';
COMMENT ON COLUMN answer.content is '내용';
COMMENT ON COLUMN answer.rdate is '날짜';


/**********************************/
/* Table Name: 이벤트 당첨 */
/**********************************/
CREATE TABLE TABLE_20(
		memberno                      		NUMBER(10)		 NULL ,
		eventno                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (memberno) REFERENCES member (memberno),
  FOREIGN KEY (eventno) REFERENCES event (eventno)
);

COMMENT ON TABLE TABLE_20 is '이벤트 당첨';
COMMENT ON COLUMN TABLE_20.memberno is '회원번호';
COMMENT ON COLUMN TABLE_20.eventno is '이벤트 목록 번호';



-- 소윤
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE member_login CASCADE CONSTRAINTS;
DROP TABLE manager CASCADE CONSTRAINTS;
DROP TABLE manager_login CASCADE CONSTRAINTS;
DROP TABLE pet CASCADE CONSTRAINTS;
-- 지섭
DROP TABLE animalstory CASCADE CONSTRAINTS;
DROP TABLE chart CASCADE CONSTRAINTS;
DROP TABLE reservation CASCADE CONSTRAINTS;

-- 희원
DROP TABLE eventusers CASCADE CONSTRAINTS;
DROP TABLE event CASCADE CONSTRAINTS;
DROP TABLE present CASCADE CONSTRAINTS;
DROP TABLE style CASCADE CONSTRAINTS;

-- 미영
drop table survey CASCADE CONSTRAINTS;
drop table surveyparty CASCADE CONSTRAINTS;
drop table surveyitem CASCADE CONSTRAINTS

-- 해원
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE question CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE categrp CASCADE CONSTRAINTS;
DROP TABLE answer CASCADE CONSTRAINTS;

select * from tab;
/**
 * 회원테이블
 */
CREATE TABLE member (
  memberno NUMBER(10) NOT NULL,
  id       VARCHAR2(20)  UNIQUE NOT NULL,
  passwd    VARCHAR2(20)  NOT NULL,
  name    VARCHAR2(20)   NOT NULL,
  phone          VARCHAR2(20)   NOT NULL,
  email       VARCHAR2(20)  UNIQUE NOT NULL,
  zipcode   VARCHAR2(5)        NULL, -- 우편번호
  address1  VARCHAR2(80)       NULL, -- 주소
  address2  VARCHAR2(50)       NULL, -- 상세주소
  rdate      DATE       NOT NULL,
  PRIMARY KEY (memberno)                
);

COMMENT ON TABLE member is '회원';
COMMENT ON COLUMN member.memberno is '회원 번호';
COMMENT ON COLUMN member.id is '아이디';
COMMENT ON COLUMN member.passwd is '패스워드';
COMMENT ON COLUMN member.name is '이름';
COMMENT ON COLUMN member.phone is '전화번호';
COMMENT ON COLUMN member.email is '이메일';
COMMENT ON COLUMN member.zipcode is '우편번호';
COMMENT ON COLUMN member.address1 is '주소';
COMMENT ON COLUMN member.address2 is '상세 주소';
COMMENT ON COLUMN member.rdate is '가입 날짜';
select * from member
1) 회원등록
INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'Mmaster', '1234', '마스터', '000-1234-5678', 'master@gmail.com','1234','서울시 종로구','솔데스크', sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user', '1234', '아로미', '000-1234-5678', 'abc123@gmail.com','1234','서울시 종로구','솔데스크', sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user1', '1234', '개구리', '000-4567-5678', 'abc100@gmail.com','1534','서울시 강남구','솔데스크 12', sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user2', '1234', '왕눈이', '000-1456-5678', 'abc150@gmail.com','1215','서울시 서초구','솔데스크 545', sysdate);

/* manager */

CREATE TABLE manager (
  managerno NUMBER(10) NOT NULL, -- 관리자 번호, 레코드를 구분하는 컬럼 
  kind        VARCHAR2(20)    NOT NULL, -- M: 마스터(1명만), N: 인증 되지 않은 관리자, A: 질의응답 답변 관리자, B: 미용 관리자, D: 병원 관리자
  id       VARCHAR2(20)  UNIQUE NOT NULL, -- 한번 등록된 id 중복 안됨   
  passwd    VARCHAR2(20)  NOT NULL, -- 패스워드, 영숫자 조합 
  name    VARCHAR2(20)   NOT NULL, -- 성명, 한글 10자 저장 가능
  position    VARCHAR2(20)   NOT NULL, -- 직급
  phone       VARCHAR2(20)   NOT NULL, -- 전화번호
  email       VARCHAR2(20)  UNIQUE NOT NULL, -- 한번 등록된 email은 중복 안됨
  zipcode   VARCHAR(5)        NULL, -- 우편번호
  address1  VARCHAR(80)       NULL, -- 주소
  address2  VARCHAR(50)       NULL, -- 상세 주소 
  files      VARCHAR2(1000)      NULL,
  thumbs       VARCHAR2(1000)      NULL,
  filesizes      VARCHAR2(1000)    NULL,
  rdate      DATE       NOT NULL, -- 가입일
  PRIMARY KEY (managerno)
);

COMMENT ON TABLE manager is '관리자';
COMMENT ON COLUMN manager.managerno is '관리자 번호';
COMMENT ON COLUMN manager.kind is '권한';
COMMENT ON COLUMN manager.id is '아이디';
COMMENT ON COLUMN manager.passwd is '패스워드';
COMMENT ON COLUMN manager.name is '이름';
COMMENT ON COLUMN manager.position is '직급';
COMMENT ON COLUMN manager.phone is '전화번호';
COMMENT ON COLUMN manager.email is '이메일';
COMMENT ON COLUMN manager.zipcode is '우편번호';
COMMENT ON COLUMN manager.address1 is '주소';
COMMENT ON COLUMN manager.address2 is '상세주소';
COMMENT ON COLUMN manager.files is '이미지 파일';
COMMENT ON COLUMN manager.thumbs is '미리보기 이미지';
COMMENT ON COLUMN manager.filesizes is '이미지 사이즈';
COMMENT ON COLUMN manager.rdate is '가입날짜';

1) 등록
INSERT INTO manager(managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate)
VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
'M','master', '123', '아로미','대표이사' ,'010-1234-5458', 'abc1453@gmail.com','12345', '서울시 종로구 종로동', '123-45','pet1.jpg','pet1_m.jpg','2.5',sysdate);

INSERT INTO manager(managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate)
VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
'A','manager1', '1234', '개구리','미용팀장' ,'010-1234-5458', 'abc123@gmail.com','14565', '서울시 서초구', '1235-45','pet1.jpg','pet1_m.jpg','3.0',sysdate);


/** 
 * 관리자 로그인 기록 테이블
 */

CREATE TABLE manager_login (
  manager_loginno NUMBER(10) NOT NULL, -- 로그인 기록 번호, 레코드를 구분하는 컬럼 
  managerno NUMBER(10) NOT NULL,    -- 관리자 번호, 레코드를 구분하는 컬럼 
  ip         VARCHAR2(20)   NOT NULL, 
  rdate      DATE       NOT NULL, -- 로그인 날짜
  PRIMARY KEY (manager_loginno),
  FOREIGN KEY (managerno) REFERENCES manager(managerno)
);

COMMENT ON TABLE manager_login is '로그인 기록';
COMMENT ON COLUMN manager_login.manager_loginno is '로그인 기록 번호';
COMMENT ON COLUMN manager_login.managerno is '관리자 번호';
COMMENT ON COLUMN manager_login.ip is 'ip 주소';
COMMENT ON COLUMN manager_login.rdate is '로그인 날짜';

1) 로그인 기록 생성
INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','123-456-123', sysdate);

INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','269-456-603', sysdate);

INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','163-815-133', sysdate);

/** 
 * 회원 로그인 기록 테이블
 */

CREATE TABLE member_login (
  member_loginno NUMBER(10) NOT NULL, -- 로그인 기록 번호, 레코드를 구분하는 컬럼 
  memberno NUMBER(10) NOT NULL,    -- 회원 번호, 레코드를 구분하는 컬럼 
  ip         VARCHAR2(20)   NOT NULL, 
  rdate      DATE       NOT NULL, -- 로그인 날짜
  PRIMARY KEY (member_loginno),
  FOREIGN KEY (memberno) REFERENCES member(memberno)
);

COMMENT ON TABLE member_login is '로그인 기록 번호';
COMMENT ON COLUMN member_login.member_loginno is '로그인 번호';
COMMENT ON COLUMN member_login.memberno is '회원 번호';
COMMENT ON COLUMN member_login.ip is 'ip 주소';
COMMENT ON COLUMN member_login.rdate is '로그인 날짜';


1) 로그인 기록 생성
INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'1','123-415-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'2','123-516-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'3','151-456-123', sysdate);

/**
 * 동물 테이블 
 */

CREATE TABLE pet(
  petno NUMBER(10) NOT NULL, -- 반려동물 번호, 레코드를 구분하는 컬럼 
  memberno NUMBER(10) NOT NULL, -- 반려동물 번호, 레코드를 구분하는 컬럼 
  name    VARCHAR2(20)   NOT NULL, -- 이름, 한글 10자 저장 가능
  age    VARCHAR2(20)   NOT NULL, -- 나이, 한글 10자 저장 가능
  gender    VARCHAR2(20)   NOT NULL, -- 성별, 한글 10자 저장 가능
  pet_type    VARCHAR2(20)   NOT NULL, -- 동물 종류, 한글 10자 저장 가능
  neutralization    VARCHAR2(20)   NOT NULL, -- 중성화 여부, 한글 10자 저장 가능
  weight    FLOAT(10)   NOT NULL, -- 몸무게, 한글 10자 저장 가능
  files      VARCHAR2(1000)      NULL,
  thumbs       VARCHAR2(1000)      NULL,
  filesizes      VARCHAR2(1000)    NULL,
  PRIMARY KEY (petno),
  FOREIGN KEY (memberno) REFERENCES member(memberno)
);

COMMENT ON TABLE pet is '반려동물';
COMMENT ON COLUMN pet.petno is '동물번호';
COMMENT ON COLUMN pet.memberno is '회원번호';
COMMENT ON COLUMN pet.name is '동물이름';
COMMENT ON COLUMN pet.age is '나이';
COMMENT ON COLUMN pet.gender is '성별';
COMMENT ON COLUMN pet.pet_type is '동물종류';
COMMENT ON COLUMN pet.neutralization is '중성화여부';
COMMENT ON COLUMN pet.weight is '몸무게';
COMMENT ON COLUMN pet.files is '이미지 파일';
COMMENT ON COLUMN pet.thumbs is '미리보기 이미지';
COMMENT ON COLUMN pet.filesizes is '이미지 사이즈';

1) 동물 등록
-- 파일도 추가한 것 --
INSERT INTO pet(petno,memberno,name, age, gender, pet_type, neutralization, weight,files,thumbs,filesizes)
VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet),
'2','아랑이','10', '수컷', '시츄', 'Y', '2','pet1.jpg','pet1_m.jpg','2.5');

INSERT INTO pet(petno,memberno,name, age, gender, pet_type, neutralization, weight,files,thumbs,filesizes)
VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet),
'3','아롱이','5', '암컷', '말티즈', 'N', '3','pet2.jpg','pet2_m.jpg','3');

/* 예약 */
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

COMMENT ON TABLE reservation is '예약';
COMMENT ON COLUMN reservation.reservationno is '예약글번호';
COMMENT ON COLUMN reservation.title is '제목';
COMMENT ON COLUMN reservation.label is '레이블';
COMMENT ON COLUMN reservation.resdate is '예약날짜';
COMMENT ON COLUMN reservation.restime is '예약시간';
COMMENT ON COLUMN reservation.content is '내용';
COMMENT ON COLUMN reservation.restype is '종류';
COMMENT ON COLUMN reservation.petno is '동물번호';
COMMENT ON COLUMN reservation.memberno is '회원번호';
COMMENT ON COLUMN reservation.rdate is '입력날짜'; 
select * from reservation
1. 등록 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'커트', '초롱이 미용실', '2018-12-12', '오전 10:00', '곰돌이컷', 2, '아랑이', 1, 2, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'구토 검사', '초롱이 병원', '2018-12-12', '오후 3:00', '어제 저녁부터 구토를 해요', 1, '아롱이', 2, 3, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'춘향이 털밀기', '춘향이 미용', '2018-12-12', '오전 11:00', '몸쪽 털 짧게 다듬어주세요', 2, '아랑이', 1, 2,  sysdate);


/* 진료차트 */
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

COMMENT ON TABLE chart is '진료 차트';
COMMENT ON COLUMN chart.chartno is '차트 번호';
COMMENT ON COLUMN chart.title is '제목';
COMMENT ON COLUMN chart.sick is '병 이름';
COMMENT ON COLUMN chart.medicine is '투여약';
COMMENT ON COLUMN chart.rdate is '등록날짜';
COMMENT ON COLUMN chart.stay is '입원';
COMMENT ON COLUMN chart.etc is '기타';
COMMENT ON COLUMN chart.managerno is '관리자번호';
COMMENT ON COLUMN chart.petname is '동물이름';
COMMENT ON COLUMN chart.name is '보호자이름';
COMMENT ON COLUMN chart.phone is '보호자 전화번호';
COMMENT ON COLUMN chart.petno is '동물번호';

2. 등록 
INSERT INTO chart(chartno, petno, petname, name, phone, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
1, '초롱이', '소', '000-1234-5678', '초롱이 진료차트', '예방접종', 'ㅇㅇ접종약', 'N', '아토피발견', 2, 1, sysdate);

INSERT INTO chart(chartno, petno, petname, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
2, '춘향이', '소', '000-1234-5678', '춘향이 진료차트', '양말조각 먹음', 'N', 'N', '구토 유발하여 제거함', 2, 1, sysdate);

INSERT INTO chart(chartno, petno, petname, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
3, '토토', '해', '000-5678-1234', '토토 진료차트', '감기', '감기약', 'N', 'ㅇㅇ약에 알러지반응', 3, 1, sysdate);

/* 애니멀스토리 */

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

COMMENT ON TABLE animalstory is '애니멀이야기';
COMMENT ON COLUMN animalstory.anino is '글번호';
COMMENT ON COLUMN animalstory.anitype is '종류';
COMMENT ON COLUMN animalstory.title is '제목';
COMMENT ON COLUMN animalstory.content is '내용';
COMMENT ON COLUMN animalstory.files is '파일이름';
COMMENT ON COLUMN animalstory.thumbs is '썸네일이름';
COMMENT ON COLUMN animalstory.sizes is '파일크기';
COMMENT ON COLUMN animalstory.rdate is '작성일';
COMMENT ON COLUMN animalstory.managerno is '관리자번호';
delete from animalstory
2. 등록 
INSERT INTO animalstory(anino, anitype, title, content, files, thumbs, sizes, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'개', '산책만 나가면 아무거나 주워 먹는 강아지', 'Q. 아무거나 주워 먹는 강아지, 어떻게 해야 하나요? A. 대부분 타고난 식탐쟁이일 가능성이 높다. ', 
'dog.jpg', 'dog_thumb.jpg', 0, 1, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumbs, sizes, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'개', '분리불안증의 증상과 치료법', 'Q. 분리불안증의 원인 A. 개는 무리동물이다. ', 
'dog2.jpg', 'dog2_thumb.jpg', 0, 1, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumbs, sizes, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'고양이', '비싼 가구랑 소파를 막 긁어놔요', 'Q. 비싼 가구랑 소파를 막 긁어놔요 A. “집안의 모든 것은 고양이 용품!”, 발상 전환하기', 
'cat.jpg', 'cat_thumb.jpg', 0, 1, sysdate);


/**********************************/
/* Table Name: 설문조사 */
/**********************************/

1. 테이블 구조
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

COMMENT ON TABLE survey is '설문조사';
COMMENT ON COLUMN survey.surveyno is '설문조사 번호';
COMMENT ON COLUMN survey.survey_title is '설문조사 타이틀';
COMMENT ON COLUMN survey.seqno is '출력순서';
COMMENT ON COLUMN survey.startdate is '시작날';
COMMENT ON COLUMN survey.enddate is '만료날';
COMMENT ON COLUMN survey.rdate is '작성일자';
COMMENT ON COLUMN survey.q_cnt is '문제수';
COMMENT ON COLUMN survey.managerno is '관리자번호';

2. 등록     
INSERT INTO survey(surveyno,managerno,survey_title,seqno,startdate,enddate,rdate)
VALUES((SELECT NVL(max(surveyno),0)+1 as surveyno from survey),1,'당신의 강아지미용만족도에 대한 조사',1,'2018-01-02','2018-01-08',sysdate);	
INSERT INTO survey(surveyno,managerno,survey_title,seqno,startdate,enddate,rdate)
VALUES((SELECT NVL(max(surveyno),0)+1 as surveyno from survey),1,'당신의 강아지미용스타일에 대한 조사',1,'2018-01-02','2018-01-08',sysdate);	

/**********************************/
/* Table Name: 설문조사 항목 */
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

COMMENT ON TABLE surveyitem is '설문조사 항목';
COMMENT ON COLUMN surveyitem.surveyitemno is '설문조사 항목 번호';
COMMENT ON COLUMN surveyitem.seqno is '출력순서';
COMMENT ON COLUMN surveyitem.question is '질문내용';
COMMENT ON COLUMN surveyitem.surveyno is '설문조사 번호';
COMMENT ON COLUMN surveyitem.thumbs is 'Thumb 파일';
COMMENT ON COLUMN surveyitem.files is '파일들의 이름';
COMMENT ON COLUMN surveyitem.itemcnt is '체크 인원';
COMMENT ON COLUMN surveyitem.sizes is '파일들의 크기';

2. 등록 
INSERT INTO surveyitem(surveyitemno,surveyno,seqno,question,thumbs,files, sizes)
values((select NVL(max(surveyitemno),0)+1 as surveyitemno from surveyitem),10,1,'당신이 원하는 미용 스타일은?','fall_m.jpg', 'fall.jpg', 0);	

/**********************************/
/* Table Name: 설문조사 참여 */
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

COMMENT ON TABLE surveyparty is '설문조사 참여';
COMMENT ON COLUMN surveyparty.surveypartyno is '설문조사 참여 번호';
COMMENT ON COLUMN surveyparty.surveyitemno is '설문조사 항목 번호';
COMMENT ON COLUMN surveyparty.rdate is '참여날짜';
COMMENT ON COLUMN surveyparty.memberno is '회원번호';


2. 등록 
INSERT INTO surveyparty(surveypartyno,surveyno,surveyitemno,memberno,rdate)
values((select NVL(max(surveypartyno),0)+1 as surveypartyno from surveyparty),12,9,1,sysdate);

/**********************************/
/* Table Name: 카테고리 그룹 */
/**********************************/

CREATE TABLE categrp(
    categrpno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    classification                    VARCHAR2(1)    DEFAULT 1     NOT NULL,
    name                              VARCHAR2(50)     NOT NULL,
    seqno                             NUMBER(7)    DEFAULT 0     NOT NULL,
    visible                           VARCHAR2(1)    DEFAULT 'Y'     NOT NULL,
    rdate                             DATE     NOT NULL
);

COMMENT ON TABLE categrp is '카테고리 그룹';
COMMENT ON COLUMN categrp.categrpno is '카테고리 그룹 번호';
COMMENT ON COLUMN categrp.classification is '카테고리 그룹 분류';
COMMENT ON COLUMN categrp.name is '이름';
COMMENT ON COLUMN categrp.seqno is '출력 순서';
COMMENT ON COLUMN categrp.visible is '출력 모드';
COMMENT ON COLUMN categrp.rdate is '그룹 생성일';

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((select NVL(max(categrpno),0)+1 as categrpno from categrp), 1, '의료', 1, 'Y', sysdate);

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((select NVL(max(categrpno),0)+1 as categrpno from categrp), 2, '미용', 2, 'Y', sysdate);


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

SELECT c.categrpno, c.name,
    t.categoryno, t.categrpno, t.title,t.seqno, t.cnt
    FROM categrp c, category t
    WHERE c.categrpno = t.categrpno
    ORDER BY c.categrpno ASC, t.seqno ASC

COMMENT ON TABLE category is '카테고리';
COMMENT ON COLUMN category.categoryno is '카테고리번호';
COMMENT ON COLUMN category.categrpno is '카테고리 그룹 번호';
COMMENT ON COLUMN category.title is '게시판 이름';
COMMENT ON COLUMN category.cnt is '등록된 글 수';
COMMENT ON COLUMN category.seqno is '출력 순서';
COMMENT ON COLUMN category.rdate is '등록날짜';



-- 삽입
INSERT INTO category(categoryno, categrpno, title, cnt, seqno, rdate)
VALUES((select NVL(max(categoryno),0)+1 as categoryno from category), 1, '동물병원', 0, 1, sysdate);
             
INSERT INTO category(categoryno, categrpno, title, cnt, seqno, rdate)
VALUES((select NVL(max(categoryno),0)+1 as categoryno from category), 2, '동물미용', 0, 2, sysdate);

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
             
             /**********************************/
/* Table Name: 질문 */
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
COMMENT ON COLUMN question.replycnt is '댓글수';
COMMENT ON COLUMN question.grpno is '그룹번호';
COMMENT ON COLUMN question.indent is '답변차수';
COMMENT ON COLUMN question.ansnum is '답변 순서';
COMMENT ON COLUMN question.categoryno is '카테고리번호';
COMMENT ON COLUMN question.managerno is '매니저번호';
COMMENT ON COLUMN question.memberno is '회원번호';


-- 질문 등록
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '공휴일 진료 시간이 어떻게 되나요', sysdate, '정말친절한.....',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 1, 1);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '질문', sysdate, '질문내용',
             '','',  0, '' , 'Y', 0 , 3, 1 , 2);
             
INSERT INTO question(questionno, title, rdate, contente, name, files, thumbs, filesize, num, passwd, visible , categoryno, memberno)
VALUES((SELECT NVL(MAX(questionno), 0)+1 as questionno FROM question), '미용 관련 질문', sysdate, '미용 질문 내용.....',
             'fall.jpg','fall_m.jpg',  0, '' , 'Y', 0 , 3, 2 , 3);
             
/**********************************/
/* Table Name: 댓글 */
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

COMMENT ON TABLE answer is '댓글';
COMMENT ON COLUMN answer.answerno is '댓글번호';
COMMENT ON COLUMN answer.reviewno is '후기번호';
COMMENT ON COLUMN answer.title is '제목';
COMMENT ON COLUMN answer.name is '글쓴이';
COMMENT ON COLUMN answer.emoticon is '이모티콘';
COMMENT ON COLUMN answer.content is '내용';
COMMENT ON COLUMN answer.rdate is '날짜';
COMMENT ON COLUMN answer.memberno is '회원번호';

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
             
             

/**********************************/
/* Table Name: 미용스타일 */
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

COMMENT ON TABLE style is '미용스타일';
COMMENT ON COLUMN style.styleno is '스타일번호';
COMMENT ON COLUMN style.managerno is '관리자번호';
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
COMMENT ON COLUMN style.sizes is '이미지크기';
COMMENT ON COLUMN style.pay is '가격';
COMMENT ON COLUMN style.times is '소요시간';
COMMENT ON COLUMN style.rdate is '등록날짜';

/*등록*/
 INSERT INTO style(styleno, managerno, title, name, rname, like1, email, content,cnt,image,image_name,thumb,sizes,pay,times,rdate)
VALUES ((SELECT NVL(MAX(styleno), 0)+1 as styleno FROM style),
1,1,'미용사가 추천하는 곰돌이컷','곰돌이컷','미용사1',0,'beauty@naver.com','곰돌이 컷 너무 귀여워요',0, 'bear.jpg', 'bear01.jpg', 'bear_m.jpg',0, 50000, 3, sysdate);

INSERT INTO style(styleno, managerno, title, name, rname, like1, email, content,cnt,image,image_name,thumb,sizes,pay,times,rdate)
VALUES ((SELECT NVL(MAX(styleno), 0)+1 as styleno FROM style),
1,2,'사자가 떠오르는 귀여운 사자컷','곰돌이컷','미용사2',0,'beauty2@naver.com','몸의 털은 밀고 머리털만 남겨주어 사자의 갈기를 떠올리게 하는 컷입니다',0, 'cut.jpg', 'cut01.jpg', 'cut_m.jpg',0, 60000, 3, sysdate);

/**********************************/
/* Table Name: 이벤트선물 */
/**********************************/
CREATE TABLE present(
		presentno                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		info                          		VARCHAR2(50)		 NOT NULL,
		end_date                       VARCHAR2(30)	   NOT NULL
);

COMMENT ON TABLE present is '이벤트선물';
COMMENT ON COLUMN present.presentno is '선물번호';
COMMENT ON COLUMN present.info is '선물내용';
COMMENT ON COLUMN present.end_date is '선물만료일';

/*등록*/
INSERT INTO present(presentno, info,end_date)
VALUES ((SELECT NVL(MAX(presentno), 0)+1 as presentno FROM present),'커트20%할인해주는 쿠폰', '2019-3-31');

INSERT INTO present(presentno, info,end_date)
VALUES ((SELECT NVL(MAX(presentno), 0)+1 as presentno FROM present),'간식 선착순 무료증정', '2019-4-30');

/**********************************/
/* Table Name: 이벤트 목록 */
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

COMMENT ON TABLE event is '이벤트 목록';
COMMENT ON COLUMN event.eventno is '이벤트 목록 번호';
COMMENT ON COLUMN event.managerno is '관리자번호';
COMMENT ON COLUMN event.presentno is '선물번호';
COMMENT ON COLUMN event.title is '이벤트제목';
COMMENT ON COLUMN event.content is '이벤트내용';
COMMENT ON COLUMN event.period_start is '이벤트시작날';
COMMENT ON COLUMN event.period_end is '이벤트만료날';
COMMENT ON COLUMN event.writer is '작성자';
COMMENT ON COLUMN event.usercnt is '최대응모인원수';
COMMENT ON COLUMN event.image is '이미지';
COMMENT ON COLUMN event.image_size is '이미지크기';
COMMENT ON COLUMN event.thumb is '썸네일';
COMMENT ON COLUMN event.windate is '당첨발표일';
COMMENT ON COLUMN event.wincnt is '당첨인원수';
COMMENT ON COLUMN event.winner is '당첨자명';
COMMENT ON COLUMN event.rdate is '등록날짜';

/*등록*/
--당첨자 3명/30명 
INSERT INTO event(eventno, managerno, presentno, title, content, period_start, period_end, writer, usercnt, 
image, image_size, thumb, windate,wincnt,winner,rdate)
VALUES ((SELECT NVL(MAX(eventno), 0)+1 as eventno FROM event),
1,1,'커트 20% 할인 이벤트','이벤트 응모 누르세요!지정된 기간안에 방문하시면 커트20%할인해드립니다.','2018-12-12','2018-12-25','미용사1', 30, 'cupon.jpg', 0, 'cupon_m.jpg', '2018-12-31', 3, '가주인', sysdate);
INSERT INTO event(eventno, managerno, presentno, title, content, period_start, period_end, writer, usercnt, 
image, image_size, thumb, windate,wincnt,winner,rdate)
VALUES ((SELECT NVL(MAX(eventno), 0)+1 as eventno FROM event),
1,1,'커트 20% 할인 이벤트','이벤트 응모 누르세요!지정된 기간안에 방문하시면 커트20%할인해드립니다.','2018-12-12','2018-12-25','미용사1', 30, 'cupon.jpg', 0, 'cupon_m.jpg', '2018-12-31', 3, '나주인', sysdate);

/**********************************/
/* Table Name: 이벤트 참여자 */
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

COMMENT ON TABLE eventuser is '이벤트 참여자';
COMMENT ON COLUMN eventuser.eventuserno is '이벤트 참여자 번호';
COMMENT ON COLUMN eventuser.memberno is '회원번호';
COMMENT ON COLUMN eventuser.eventno is '이벤트 목록 번호';
COMMENT ON COLUMN eventuser.joindate is '참여날짜';
COMMENT ON COLUMN eventuser.win is '당첨여부';

/*등록*/
--(당첨N > 비어있음)
INSERT INTO eventuser(eventuserno, memberno, eventno, joindate)
VALUES ((SELECT NVL(MAX(eventuserno), 0)+1 as eventuserno FROM eventuser),1,2,sysdate);
INSERT INTO eventuser(eventuserno, memberno, eventno, joindate)
VALUES ((SELECT NVL(MAX(eventuserno), 0)+1 as eventuserno FROM eventuser),2,2,sysdate);
INSERT INTO eventuser(eventuserno, memberno, eventno, joindate)
VALUES ((SELECT NVL(MAX(eventuserno), 0)+1 as eventuserno FROM eventuser),3,2,sysdate);

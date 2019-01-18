Animal Hospital Reservation System
프로젝트명 : AHR_system
패키지명 : dev.mvc.ahr
사용기술 : Spring 등
디비 : Oracle
디비명 : ahr
DROP TABLE animalstory CASCADE CONSTRAINTS;
DROP TABLE chart CASCADE CONSTRAINTS;
DROP TABLE manager CASCADE CONSTRAINTS;
DROP TABLE reservation CASCADE CONSTRAINTS;
DROP TABLE pet CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
select * from tab
/**********************************/
/* Table Name: 회원 */
/**********************************/
1. 테이블 구조
   - COMMENT 기재
   select * from member
CREATE TABLE member (
  memberno NUMBER(10) NOT NULL,
  id       VARCHAR2(20)  UNIQUE NOT NULL,
  passwd    VARCHAR2(20)  NOT NULL,
  name    VARCHAR2(20)   NOT NULL,
  phone          VARCHAR2(20)   NOT NULL,
  email       VARCHAR2(20)  UNIQUE NOT NULL,
  zipcode   VARCHAR(5)        NULL, -- 우편번호
  address1  VARCHAR(80)       NULL, -- 주소
  address2  VARCHAR(50)       NULL, -- 상세주소
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

2. 등록
drop table member;
--INSERT INTO member(memberno,kind, id, passwd, name, phone, email,address,auth,confirm, rdate)
--VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
--'M', 'master', '1234', '마스터', '000-1234-5678', 'master@gmail.com','경기도 수원시','AXD0123456789012','Y', sysdate);
--
INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user', '1234', '아로미', '000-1234-5678', 'abc123@gmail.com','1234','서울시 종로구','솔데스크', sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'sy', '12345', '소', '000-1234-5678', 'abc456@gmail.com','1234','경기도 수원시','솔데스크',sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'hw', '123456', '해', '000-5678-1234', 'abc000@gmail.com','1234','경기도 일산시','솔데스크',sysdate);

INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user1', '1234567', '아로미', '000-5678-1234', 'ab450@gmail.com','1234','서울시 종로구','솔데스크',sysdate);

 SELECT petno FROM pet WHERE memberno=4;
/**********************************/
/* Table Name: 반려동물 */
/**********************************/
 1. 테이블 구조
   - COMMENT 기재
 drop table pet;
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
delete from pet;
select * from pet order by petno;
2. 등록 
 INSERT INTO pet(petno, name, age, gender, pet_type, neutralization, weight, memberno) 
 VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet), '초롱이', 3, '암컷', '몰티즈', 'Y', 4.5, 2);
 
 INSERT INTO pet(petno, name, age, gender, pet_type, neutralization, weight, memberno) 
 VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet), '춘향이', 4, '암컷', '고양이', 'Y', 4.5, 2);
 
 INSERT INTO pet(petno, name, age, gender, pet_type, neutralization, weight, memberno) 
 VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet), '토토', 5, '암컷', '푸들', 'Y', 4.5, 3);
 
 INSERT INTO pet(petno, name, age, gender, pet_type, neutralization, weight, memberno) 
 VALUES ((SELECT NVL(MAX(petno), 0)+1 as petno FROM pet), '예삐', 12, '암컷', '시츄', 'Y', 4.5, 4);
 
 3. 검색
 select name from pet where memberno = 2 and petno=1; /* 초롱이, 춘향이 */
 select name from pet where memberno = 4; /* 예삐 */
 
/**********************************/
/* Table Name: 예약 */
/**********************************/
1. 테이블 구조
   - COMMENT 기재
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

COMMENT ON TABLE reservation is '예약';
COMMENT ON COLUMN reservation.reservationno is '예약글번호';
COMMENT ON COLUMN reservation.title is '제목';
COMMENT ON COLUMN reservation.label is '레이블';
COMMENT ON COLUMN reservation.resdate is '예약날짜';
COMMENT ON COLUMN reservation.restime is '예약시간';
COMMENT ON COLUMN reservation.content is '내용';
COMMENT ON COLUMN reservation.restype is '종류';
COMMENT ON COLUMN reservation.name is '동물이름';
COMMENT ON COLUMN reservation.petno is '동물번호';
COMMENT ON COLUMN reservation.memberno is '회원번호';
COMMENT ON COLUMN reservation.rdate is '입력날짜'; 
select * from col;
delete from reservation;
select * from reservation where restype=1;
 오후 3:00
 오후 6:00
 오전 10:30
select restime from reservation where restype=1 AND resdate='2019-01-15';
 오전 11:30
 오후 3:00
select restime from reservation where restype=2 AND resdate='2019-01-15';
 오전 11:30
 오후 12:30
select restime from reservation where restype=1 AND resdate='2019-01-16';
 오전 10:00
 오전 10:30
select restime from reservation where restype=2 AND resdate='2019-01-16';
 오전 11:30
 
 select restime from reservation where restype=3;
select petno, name  from pet;
2. 등록 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'커트', '초롱이 미용실', '2018-12-12', '오전 10:00', '곰돌이컷', 2, '초롱이', 1, 2, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'구토 검사', '초롱이 병원', '2018-12-12', '오후 3:00', '어제 저녁부터 구토를 해요', 1, '초롱이', 1, 2, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation),
 							'춘향이 털밀기', '춘향이 미용', '2018-12-12', '오전 11:00', '몸쪽 털 짧게 다듬어주세요', 2, '초롱이', 1, 2,  sysdate);
 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'예방접종', '예삐 예방접종', '2018-01-12', '오후 6:00', '예삐 예방접종 예약', 1, '토토', 3, 4,  sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'몸털 다듬기', '예삐 미용', '2018-12-15', '오후 7:00', '예삐 몸쪽 털을 약간 다듬어주세요', 2, '토토', 3, 4, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'위 엑스레이', '토토 진료', '2018-12-15', '오전 10:30', '양말조각 먹었는지 확인', 1, '춘향이', 2, 3, sysdate);
 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'털 밀기', '토토 미용', '2018-12-15', '오전 11:30', '아토피가 있어요', 2, '춘향이', 2, 4, sysdate);
 							
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'위 엑스레이', '토토 진료', '2019-01-16', '오전 10:30', '양말조각 먹었는지 확인', 1, '예삐', 2, 4, sysdate);
 
 INSERT INTO reservation(reservationno, title, label, resdate, restime, content, restype, name, petno, memberno, rdate) 
 VALUES ((SELECT NVL(MAX(reservationno), 0)+1 as reservationno FROM reservation), 
 							'털 밀기', '토토 미용', '2019-01-16', '오전 11:30', '아토피가 있어요', 2, '예삐', 2, 4, sysdate);
 
3. 목록
-- 예약용 리스트 > 총 예약리스트 중 비어있는 예약시간 확인에 사용 (예약시간 오름차순으로 정렬)
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation 
WHERE resdate = '2018-12-15'
ORDER BY restime;

-- 전체 목록 > 특정 달의 목록 (예약날짜 내림차순, 시간 오름차순으로 정렬)
select reservationno, title, label, resdate, restime, memberno
from reservation
where substr(resdate, 1, 7) = '2018-12' and memberno = 2
order by resdate desc, restime asc

SELECT r.reservationno, r.restype, r.title, r.resdate, r.restime, r.content, r.name, r.petno, r.rdate, r.memberno
FROM reservation r, member m
WHERE r.memberno = m.memberno AND r.memberno = 2 AND substr(r.resdate, 6, 2) = '01'
ORDER BY r.resdate asc, r.restime asc

-- 전체 목록 > 수정전
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation 
WHERE substr(resdate, 1, 7) = '2018-12'
ORDER BY resdate desc

-- 회원캘린더 > 회원캘린더 회원번호별로 보이게해야함.
SELECT r.reservationno, r.title, r.label, r.resdate, r.restime, r.content, r.restype, r.petname, r.petno, r.rdate, r.memberno
FROM reservation r, member m
WHERE r.memberno = m.memberno AND r.memberno = 2;
-- 회원캘린더 > 특정 달의 목록
SELECT r.reservationno, r.title, r.label, r.resdate, r.restime, r.content, r.restype, r.petno, r.rdate
FROM reservation r, member m
WHERE r.memberno = m.memberno
AND r.memberno = 2
AND substr(resdate, 1, 7) = '2018-12'; -- 12월달
-- 회원캘린더 > 특정 날의 목록
SELECT r.reservationno, r.title, r.label, r.resdate, r.restime, r.content, r.restype, r.petno, r.rdate
FROM reservation r, member m
WHERE r.memberno = m.memberno
AND r.memberno = 2
AND r.resdate = '2018-12-15'; -- 12월 12일

-- 의료캘린더 > 의료캘린더 전체보기
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '의료';
-- 의료캘린더 > 특정 달의 목록
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '의료'
AND substr(resdate, 1, 7) = '2018-12'; -- 12월달
-- 의료캘린더 > 특정 날의 목록
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '의료'
AND resdate = '2018-12-12'; -- 12월 12일

-- 전체 목록 > 미용 캘린더 전체보기
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '미용';
-- 미용캘린더 > 특정 달의 목록
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '미용'
AND substr(resdate, 1, 7) = '2018-12'; -- 12월달
-- 미용캘린더 > 특정 날의 목록
SELECT reservationno, title, label, resdate, restime, content, restype, petno, rdate
FROM reservation
WHERE restype = '미용'
AND resdate = '2018-12-12'; -- 12월 12일

-- 페이징
SELECT rownum, reservationno, title
FROM reservation

4. 조회
위에 있음
select * from reservation where reservationno = 1

RESERVATIONNO TITLE  LABEL   RESDATE    RESTIME CONTENT        RESTYPE RDATE                 PETNO
 ------------- ------ ------- ---------- ------- -------------- ------- --------------------- -----
             1 커트와 염색 초롱이 미용실 2018-12-12 13:00   곰돌이컷, 갈색 부분 염색 미용      2018-12-11 17:17:52.0     1

5. 수정
'구토 검사', '초롱이 병원', '2018-12-12', '15:00', '어제 저녁부터 구토를 해요', '의료', '1'
UPDATE reservation 
SET title = '접종예약', label = '초롱이 접종', resdate = '2018-12-13', restime = '15:00', content ='필수접종 예약합니다.', restype = '의료', petno='1', memberno='2'
WHERE reservationno=1;

select * from reservation where reservationno = 1;
RESERVATIONNO TITLE LABEL  RESDATE    RDATE                 RESTIME CONTENT     RESTYPE RESITEMNO PETNO MEMBERNO
 ------------- ----- ------ ---------- --------------------- ------- ----------- ------- --------- ----- --------
             1 접종예약  초롱이 접종 2018-12-13 2018-12-12 17:26:13.0 15:00   필수접종 예약합니다. 의료           NULL     1        2

 
6. 삭제
-- 한건 삭제
DELETE FROM reservation WHERE reservationno = 1;


/**********************************/
/* Table Name: 관리자 */
/**********************************/
1. 테이블 구조
   - COMMENT 기재

CREATE TABLE manager (
  managerno NUMBER(10) NOT NULL, -- 관리자 번호, 레코드를 구분하는 컬럼 
  kind        VARCHAR2(20)    NOT NULL, -- M: 마스터, N: 인증 되지 않은 관리자, A: 질의응답 답변 관리자, B: 미용 관리자, D: 병원 관리자
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



2. 등록 
INSERT INTO manager(managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate)
VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
'M','manager', '1234', '아로미','대표이사' ,'010-1234-5458', 'abc1453@gmail.com','12345', '서울시 종로구 종로동', '123-45','pet1.jpg','pet1_m.jpg','2.5',sysdate);

--INSERT INTO manager(managerno,kind, id, passwd, name, phone, email,auth,confirm, rdate)
--VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
--'B','manager1', '12345', '미용관계자', '000-1234-5458', 'abc1453@gmail.com','AXD0123455129012','Y', sysdate);
--
--INSERT INTO manager(managerno,kind, id, passwd, name, phone, email,auth,confirm, rdate)
--VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
--'D','manager2', '12354', '병원관계자', '000-1215-5678', 'abc51@gmail.com','AXD0128456789012','Y', sysdate);
--
--INSERT INTO manager(managerno,kind, id, passwd, name, phone, email,auth,confirm, rdate)
--VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
--'A','manager3', '12534', '답변관계자', '000-1224-5658', 'ab263@gmail.com','AXD0123456154012','Y', sysdate);


/**********************************/
/* Table Name: 진료 차트 */
/**********************************/
1. 테이블 구조
   - COMMENT 기재
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
COMMENT ON COLUMN chart.petno is '동물번호';

2. 등록 
drop table chart
select * from pet;
select p.name, p.memberno, m.name from pet p, member m where p.memberno = m.memberno
select age, gender, pet_type, NEUTRALIZATION, weight from pet where petno=2
select name, 
PETNO NAME AGE GENDER PET_TYPE NEUTRALIZATION WEIGHT FILES THUMBS FILESIZES MEMBERNO
 ----- ---- --- ------ -------- -------------- ------ ----- ------ --------- --------
     1 초롱이  3   여      몰티즈      중성화            4.5kg  NULL  NULL   NULL             2
     2 토토   5   남      푸들       중성화            4.5kg  NULL  NULL   NULL             3
     3 예삐   12  여      시츄       중성화            4.5kg  NULL  NULL   NULL             4
delete from chart;
select name, petno from pet

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
1, '초롱이', '소', '초롱이 진료차트', '예방접종', 'ㅇㅇ접종약', 'N', '아토피발견', 1, sysdate);

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
2, '춘향이', '소', '춘향이 진료차트', '양말조각 먹음', 'N', 'N', '구토 유발하여 제거함', 1, sysdate);

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
3, '토토', '해', '토토 진료차트', '감기', '감기약', 'N', 'ㅇㅇ약에 알러지반응', 1, sysdate);

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
4, '예삐', '아로미', '예삐 진료차트', '양말조각 먹음', 'N', 'N', '구토 유발하여 제거함', 1, sysdate);

INSERT INTO chart(chartno, petno, petname, name, title, sick, medicine, stay, etc, managerno, rdate)
VALUES ((SELECT NVL(MAX(chartno), 0)+1 as chartno FROM chart),
4, '예삐', '아로미', '예삐 진료차트', '예방접종', 'ㅇㅇ접종약', 'N', 'ㅇㅇ접종함', 1, sysdate);

3. 목록 --+ 
-- 전체 목록
SELECT chartno, title, symptom, sick, medicine, resdate, rdate, stay, etc, managerno, petno
FROM chart
WHERE petno = 2 AND memberno = (select memberno from member where petno = 2)
ORDER BY chartno;
4. 조회 --+
-- 특정동물의 전체 목록
SELECT c.chartno, c.title, c.symptom, c.sick, c.medicine, c.resdate, c.rdate, c.stay, c.etc, c.managerno, c.name
FROM chart c, pet p 
WHERE c.petno = 2
ORDER BY rdate DESC;

select chartno, petno, title, symptom, sick, medicine, name
from chart
where petno = (select petno from pet where memberno = 2) -- => 다중값이 나와서 검색 안됨

select chartno, petno, title, sick, medicine, name, substr(rdate, 1, 7) -- => 하나의 대상만 나오지만 검색할 때 같은 이름의 동물이 많이 나올 수 있음
from chart
where petno = 3 

SELECT chartno, managerno, petno, petname, name, title, sick, medicine, stay, etc, substr(rdate, 1, 8)
FROM chart 
ORDER BY petno

-- chart create할 때 기본 인적사항 
select p.petno, p.name, p.age, m.name, m.phone from pet p, member m where p.petno = 2 AND m.memberno = 3
-- chart list : 모든동물이 다 나옴
SELECT chartno, managerno, petno, name, title, symptom, sick, medicine, stay, etc, rdate FROM chart ORDER BY petno 
SELECT chartno, managerno, petno, petname, name, title, sick, medicine, stay, etc, substr(rdate, 1, 8) rdate
    FROM chart 
    WHERE chartno = 3
-- 검색 1 : 동물이름과 보호자이름으로 
select chartno, petno, title, symptom, sick, medicine, name
from chart
where petno = (select petno from pet where name = '예삐') AND name = '아로미'
-- 검색 2 : 보호자 이름으로 검색할때 -> 해당된 동물 모두가 나옴
select chartno, petno, title, symptom, sick, medicine, name
from chart
where name = '소'

name = '예삐' AND 


-> 해결법
1. chart를 생성할 때 pet_list에서 출발해서 petno를 넘겨줘서 petno마다의 차트가 생성되게 한다.
2. 동물이름과 나이를 함께 검색되게 한다. -> 해가 지나면 나이가 같이 갱신돼야한다.
3. 동물이름과 보호자의 이름을 함께 검색되게 한다. -> 동물이름과 보호자의 이름이 모두 같은 경우의 동명이인이 존재할 가능성은?

-- 전체 레코드수
SELECT count(chartno)
FROM chart;

5. 수정
UPDATE chart 
SET title='토토 진료차트', symptom='복통', sick='때수건 삼킴', medicine='N', resdate='2019-01-20', stay='N', etc='구토유발하여 제거함'
WHERE chartno=1 AND petno=2;
CHARTNO TITLE   SYMPTOM SICK   MEDICINE RESDATE    RDATE                 STAY ETC        MANAGERNO PETNO
 ------- ------- ------- ------ -------- ---------- --------------------- ---- ---------- --------- -----
       1 토토 진료차트 복통      때수건 삼킴 N        2019-01-20 2018-12-12 17:59:19.0 N    구토유발하여 제거함         2     2

6. 삭제 --
-- 한건 삭제
DELETE FROM chart 
WHERE chartno = 1 AND petno=2; 
-- FK에따른 삭제 > 3번 동물의 차트를 모두 삭제
DELETE FROM chart
WHERE petno=3;


/**********************************/
/* Table Name: 애니멀이야기 */
/**********************************/
1. 테이블 구조
   - COMMENT 기재

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

COMMENT ON TABLE animalstory is '애니멀이야기';
COMMENT ON COLUMN animalstory.anino is '글번호';
COMMENT ON COLUMN animalstory.anitype is '종류';
COMMENT ON COLUMN animalstory.title is '제목';
COMMENT ON COLUMN animalstory.content is '내용';
COMMENT ON COLUMN animalstory.files is '파일이름';
COMMENT ON COLUMN animalstory.thumb is '썸네일이름';
COMMENT ON COLUMN animalstory.sizes is '파일크기';
COMMENT ON COLUMN animalstory.rdate is '작성일';
COMMENT ON COLUMN animalstory.cnt is '조회수';
COMMENT ON COLUMN animalstory.managerno is '관리자번호';

2. 등록 
INSERT INTO animalstory(anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'개', '산책만 나가면 아무거나 주워 먹는 강아지', 'Q. 아무거나 주워 먹는 강아지, 어떻게 해야 하나요? A. 대부분 타고난 식탐쟁이일 가능성이 높다. ', 
'dog.jpg', 'dog_thumb.jpg', 0, 0, 2, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'개', '분리불안증의 증상과 치료법', 'Q. 분리불안증의 원인 A. 개는 무리동물이다. ', 
'dog2.jpg', 'dog2_thumb.jpg', 0, 0, 3, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'고양이', '비싼 가구랑 소파를 막 긁어놔요', 'Q. 비싼 가구랑 소파를 막 긁어놔요 A. “집안의 모든 것은 고양이 용품!”, 발상 전환하기', 
'cat.jpg', 'cat_thumb.jpg', 0, 0, 2, sysdate);

INSERT INTO animalstory(anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate)
VALUES ((SELECT NVL(MAX(anino), 0)+1 as anino FROM animalstory),
'고양이', '고양이의 구토, 당연한 것?', 
'Q. 고양이의 구토, 당연한 것? A. 적절한 대응을 통해 문제를 최소한으로 줄여야 한다.', 
'cat2.jpg', 'cat2_thumb.jpg', 0, 0, 3, sysdate);
 
3. 목록
-- 전체 목록
SELECT anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate
FROM animalstory;
-- 개 목록만 보기
SELECT anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate
FROM animalstory
WHERE anitype = '개';

4. 조회
-- 한건 조회
SELECT anino, anitype, title, content, files, thumb, sizes, cnt, managerno, rdate
FROM animalstory
WHERE anino = 3;
-- 전체 레코드수
SELECT count(anino)
FROM animalstory;
-- FK에따른 레코드 수
SELECT count(anino)
FROM animalstory
WHERE managerno = 2;
 
5. 수정
UPDATE animalstory
SET title = '애견의 나쁜 버릇 고치기', content = '간식을 잡은 손을 개에게 인식시키고, 주인에게 주의를 기울이게 한다.'
WHERE anino = 2;
 
6. 삭제
-- 한건 삭제
DELETE FROM animalstory
WHERE anino = 2;

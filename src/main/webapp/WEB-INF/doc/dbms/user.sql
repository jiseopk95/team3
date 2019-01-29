DROP TABLE member;
DROP TABLE member_login;
DROP TABLE manager;
DROP TABLE manager_login;
DROP TABLE pet;
DROP TABLE contents;
DROP TABLE member;

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

1) 회원등록
INSERT INTO member(memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate)
VALUES ((SELECT NVL(MAX(memberno), 0)+1 as memberno FROM member),
'user', '1234', '아로미', '000-1234-5678', 'abc123@gmail.com','1234','서울시 종로구','솔데스크', sysdate);

2) 회원검색
SELECT memberno,id, passwd, name, phone, email, zipcode,address1,address2, rdate
FROM member
ORDER BY memberno ASC;

5) 패스워드 변경
5-1 기존 패스워드 검사
SELECT count(*) as cnt
FROM member
WHERE memberno = 1 AND passwd='1234';

5-2 패스워드 변경
UPDATE member
SET passwd='5678'
WHERE memberno=1;

6) 회원 정보 수정 
UPDATE member
SET id='user1', passwd='4567', name='개발자', phone='010-1234-5678', email='cdf123@gmail.com',zipcode='4561',address1='서울시 종로구',address2='젊음의 거리'
WHERE memberno=1;

7. 회원 삭제 
DELETE FROM member
WHERE memberno = 1;
DELETE FROM member
WHERE memberno = 3;
DELETE FROM member
WHERE memberno = 4;


8. 로그인 관련
SELECT count(*) as cnt
FROM member
WHERE id = 'user1' AND passwd='12345' 
   
SELECT count(*) as cnt
FROM member
WHERE id = 'user2' AND passwd='12345'
   

Email 정보를 이용한 아이디 조회

SELECT memberno,id,name,email,rdate
FROM member
WHERE email = 'abc@naver.com' AND name='개발자';

===========================================================================================================

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


2) 등록
INSERT INTO manager(managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate)
VALUES ((SELECT NVL(MAX(managerno), 0)+1 as managerno FROM manager),
'M','manager', '1234', '아로미','대표이사' ,'010-1234-5458', 'abc1453@gmail.com','12345', '서울시 종로구 종로동', '123-45','pet1.jpg','pet1_m.jpg','2.5',sysdate);

1) 목록
SELECT managerno,kind, id, passwd, name,position, phone, email,zipcode,address1,address2,files,thumbs,filesizes,rdate
FROM manager
ORDER BY managerno ASC;


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

1) 로그인 기록 조회
SELECT manager_loginno,managerno, ip, rdate
FROM manager_login
ORDER BY manager_loginno ASC;

2) 로그인 기록 생성
INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','123-456-123', sysdate);

INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','269-456-603', sysdate);

INSERT INTO manager_login(manager_loginno,managerno,ip,rdate)
VALUES((SELECT NVL(MAX(manager_loginno), 0)+1 as manager_loginno FROM manager_login),'1','163-815-133', sysdate);

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

1) 로그인 기록 조회
SELECT member_loginno,memberno, ip, rdate
FROM member_login
ORDER BY member_loginno ASC;

2) 로그인 기록 생성
INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'1','123-415-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'2','123-516-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'3','151-456-123', sysdate);

DROP TABLE pet;

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


2) 동물 조회
SELECT petno,memberno,name, age, gender, pet_type, neutralization, weight,files,thumbs,filesizes
FROM pet
ORDER BY petno ASC;

SELECT memberno, id, passwd, name, phone,email, zipcode, address1, address2, rdate
    FROM member
        WHERE name LIKE '%아로%'
    ORDER BY memberno DESC;
    
/**
 * 개인별 동물 조회
 */
    SELECT petno,memberno,name, age, gender, pet_type, neutralization, weight,files,thumbs,filesizes
    FROM pet
    WHERE memberno = 2;

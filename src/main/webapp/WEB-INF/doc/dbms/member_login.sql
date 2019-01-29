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
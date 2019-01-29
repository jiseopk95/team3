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

1) �α��� ��� ��ȸ
SELECT member_loginno,memberno, ip, rdate
FROM member_login
ORDER BY member_loginno ASC;

2) �α��� ��� ����
INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'1','123-415-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'2','123-516-123', sysdate);

INSERT INTO member_login(member_loginno,memberno,ip,rdate)
VALUES((SELECT NVL(MAX(member_loginno), 0)+1 as member_loginno FROM member_login),'3','151-456-123', sysdate);
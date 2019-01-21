�� /doc/dbms/categrp_c.sql(ddl)
-----------------------------------------------------------------------------------
1) DDL
/**********************************/
/* Table Name: ī�װ� �׷� */
/**********************************/
DROP TABLE categrp;

CREATE TABLE categrp(
    categrpno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    classification                    CHAR(1)    DEFAULT 1     NOT NULL,
    name                              VARCHAR2(50)     NOT NULL,
    seqno                             NUMBER(7)    DEFAULT 0     NOT NULL,
    visible                           CHAR(1)    DEFAULT 'Y'     NOT NULL,
    rdate                             DATE     NOT NULL
);

COMMENT ON TABLE categrp is 'ī�װ� �׷�';
COMMENT ON COLUMN categrp.categrpno is 'ī�װ� �׷� ��ȣ';
COMMENT ON COLUMN categrp.classification is 'ī�װ� �׷� �з�';
COMMENT ON COLUMN categrp.name is '�̸�';
COMMENT ON COLUMN categrp.seqno is '��� ����';
COMMENT ON COLUMN categrp.visible is '��� ���';
COMMENT ON COLUMN categrp.rdate is '�׷� ������';
  

2) insert
- classification: 1-Blog, 2-Gallery, 3-Product
- visible: Y, N

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES(1, 1, '����', 1, 'Y', sysdate);

- ERROR PK �ߺ���.
INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES(1, 2, '����', 1, 'Y', sysdate);

DELETE FROM categrp;

3) �Ϸù�ȣ�� �ڵ� ����
SELECT categrpno FROM categrp;

 CATEGRPNO
 ---------------

SELECT MAX(categrpno) FROM categrp; 

 MAX(CATEGRPNO)
 ----------------------
           NULL
           
SELECT MAX(categrpno) + 1 FROM categrp;           
           
 MAX(CATEGRPNO)+1
 --------------------------
             NULL

- null�� �÷��� ���� 0���� ������ ����
SELECT NVL(MAX(categrpno), 0) + 1 FROM categrp;

 NVL(MAX(CATEGRPNO),0)+1 <-- �÷����� �ʹ� ŭ
 -----------------------------------
                       1
                       
SELECT NVL(MAX(categrpno), 0) + 1 as categrpno FROM categrp;                       

 CATEGRPNO
 ---------------
         1

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(categrpno), 0) + 1 as categrpno FROM categrp), 1, '����', 1, 'Y', sysdate);

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(categrpno), 0) + 1 as categrpno FROM categrp), 2, '�����̿�', 2, 'Y', sysdate);

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(categrpno), 0) + 1 as categrpno FROM categrp), 3, '����Ʈ', 1, 'Y', sysdate);
         

4) ���
SELECT categrpno, classification, name, seqno, visible, rdate 
FROM categrp
ORDER BY categrpno ASC;

 CATEGRPNO CLASSIFICATION NAME SEQNO VISIBLE RDATE
 --------- -------------- ---- ----- ------- ---------------------
         1 1              ����       1 Y       2018-05-16 15:05:08.0
         2 2              ����       1 Y       2018-05-16 15:05:50.0
         3 3              ����Ʈ      1 Y       2018-05-16 15:08:10.0

5) ��ȸ
SELECT categrpno, classification, name, seqno, visible, rdate 
FROM categrp
WHERE categrpno = 1;
  
6) ����
UPDATE categrp
SET classification=1, name='��ȭ', seqno = 1, visible='Y'
WHERE categrpno = 3;

SELECT categrpno, classification, name, seqno, visible, rdate 
FROM categrp
ORDER BY categrpno ASC;

 CATEGRPNO CLASSIFICATION NAME SEQNO VISIBLE RDATE
 --------- -------------- ---- ----- ------- ---------------------
         1 1              ����       1 Y       2018-05-16 15:05:08.0
         2 2              ����       1 Y       2018-05-16 15:05:50.0
         3 1              ��ȭ       1 Y       2018-05-16 15:08:10.0

7) ����
DELETE FROM categrp
WHERE categrpno = 4;

SELECT categrpno, classification, name, seqno, visible, rdate 
FROM categrp
ORDER BY categrpno ASC;
 CATEGRPNO CLASSIFICATION NAME SEQNO VISIBLE RDATE
 --------- -------------- ---- ----- ------- ---------------------
         1 1              ����       1 Y       2018-05-16 15:05:08.0
         2 2              ����       1 Y       2018-05-16 15:05:50.0

        
-- ��� ���� ����, 10 -> 1
UPDATE categrp SET seqno = seqno - 1 WHERE categrpno=1;

-- ��¼��� ����, 1 -> 10
UPDATE categrp SET seqno = seqno + 1 WHERE categrpno=1;

-- ��� ���������� ��ü ���
SELECT categrpno, name, seqno
FROM categrp
ORDER BY seqno ASC;

  
-----------------------------------------------------------------------------------
 
 

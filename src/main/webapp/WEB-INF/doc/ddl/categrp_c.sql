▷ /doc/dbms/categrp_c.sql(ddl)
-----------------------------------------------------------------------------------
1) DDL
/**********************************/
/* Table Name: 카테고리 그룹 */
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

COMMENT ON TABLE categrp is '카테고리 그룹';
COMMENT ON COLUMN categrp.categrpno is '카테고리 그룹 번호';
COMMENT ON COLUMN categrp.classification is '카테고리 그룹 분류';
COMMENT ON COLUMN categrp.name is '이름';
COMMENT ON COLUMN categrp.seqno is '출력 순서';
COMMENT ON COLUMN categrp.visible is '출력 모드';
COMMENT ON COLUMN categrp.rdate is '그룹 생성일';
  

2) insert
- classification: 1-Blog, 2-Gallery, 3-Product
- visible: Y, N

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES(1, 1, '여행', 1, 'Y', sysdate);

- ERROR PK 중복됨.
INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES(1, 2, '여행', 1, 'Y', sysdate);

DELETE FROM categrp;

3) 일련번호의 자동 생성
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

- null인 컬럼의 값을 0으로 변경후 연산
SELECT NVL(MAX(categrpno), 0) + 1 FROM categrp;

 NVL(MAX(CATEGRPNO),0)+1 <-- 컬럼명이 너무 큼
 -----------------------------------
                       1
                       
SELECT NVL(MAX(categrpno), 0) + 1 as categrpno FROM categrp;                       

 CATEGRPNO
 ---------------
         1

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(categrpno), 0) + 1 as categrpno FROM categrp), 1, '여행', 1, 'Y', sysdate);

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(categrpno), 0) + 1 as categrpno FROM categrp), 2, '동물미용', 2, 'Y', sysdate);

INSERT INTO categrp(categrpno, classification, name, seqno, visible, rdate)
VALUES((SELECT NVL(MAX(categrpno), 0) + 1 as categrpno FROM categrp), 3, '리조트', 1, 'Y', sysdate);
         

4) 목록
SELECT categrpno, classification, name, seqno, visible, rdate 
FROM categrp
ORDER BY categrpno ASC;

 CATEGRPNO CLASSIFICATION NAME SEQNO VISIBLE RDATE
 --------- -------------- ---- ----- ------- ---------------------
         1 1              여행       1 Y       2018-05-16 15:05:08.0
         2 2              여행       1 Y       2018-05-16 15:05:50.0
         3 3              리조트      1 Y       2018-05-16 15:08:10.0

5) 조회
SELECT categrpno, classification, name, seqno, visible, rdate 
FROM categrp
WHERE categrpno = 1;
  
6) 수정
UPDATE categrp
SET classification=1, name='영화', seqno = 1, visible='Y'
WHERE categrpno = 3;

SELECT categrpno, classification, name, seqno, visible, rdate 
FROM categrp
ORDER BY categrpno ASC;

 CATEGRPNO CLASSIFICATION NAME SEQNO VISIBLE RDATE
 --------- -------------- ---- ----- ------- ---------------------
         1 1              여행       1 Y       2018-05-16 15:05:08.0
         2 2              여행       1 Y       2018-05-16 15:05:50.0
         3 1              영화       1 Y       2018-05-16 15:08:10.0

7) 삭제
DELETE FROM categrp
WHERE categrpno = 4;

SELECT categrpno, classification, name, seqno, visible, rdate 
FROM categrp
ORDER BY categrpno ASC;
 CATEGRPNO CLASSIFICATION NAME SEQNO VISIBLE RDATE
 --------- -------------- ---- ----- ------- ---------------------
         1 1              여행       1 Y       2018-05-16 15:05:08.0
         2 2              여행       1 Y       2018-05-16 15:05:50.0

        
-- 출력 순서 상향, 10 -> 1
UPDATE categrp SET seqno = seqno - 1 WHERE categrpno=1;

-- 출력순서 하향, 1 -> 10
UPDATE categrp SET seqno = seqno + 1 WHERE categrpno=1;

-- 출력 순서에따른 전체 목록
SELECT categrpno, name, seqno
FROM categrp
ORDER BY seqno ASC;

  
-----------------------------------------------------------------------------------
 
 

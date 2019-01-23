package dev.mvc.review;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ReviewVO {
/*
reviewno                        NUMBER(10)   NOT NULL  PRIMARY KEY,
name                            VARCHAR2(30)   NOT NULL,
title                           VARCHAR2(100)  NOT NULL,
contents                        CLOB   NOT NULL,
rdate                           DATE   NOT NULL,
files                           VARCHAR2(30)   NULL ,
filesize                        VARCHAR2(1000)   NULL ,
thumbs                          VARCHAR2(1000)   NULL ,
num                             NUMBER(10)   NOT NULL,
score                           NUMBER(10)   NOT NULL,
categoryno                      NUMBER(10)   NULL ,
memberno                        NUMBER(10)   NULL ,
  FOREIGN KEY (categoryno) REFERENCES category (categoryno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
*/
/** �ı� ��ȣ*/
 private int reviewno;
 /** �۾���*/
 private String name;
 /** ����*/
 private String title;
 /** ����*/
 private String contents;
 /** �����*/
 private String rdate;
 /** ����*/
 private String files="";
 /** ���� ũ��*/
 private String filesize="";
 /** �̸�����*/
 private String thumbs;
 /**��ȸ�� */
 private int num;
 /** ����*/
 private int score;
 /** ī�װ� ��ȣ*/
 private int categoryno;
 /** ȸ����ȣ*/
 private int memberno;
 
 

  public ReviewVO() {

  }
  
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  �������� ���� ���ε�
  */  
  private List<MultipartFile> filesMF;



  public int getReviewno() {
    return reviewno;
  }



  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
  }



  public String getName() {
    return name;
  }



  public void setName(String name) {
    this.name = name;
  }



  public String getTitle() {
    return title;
  }



  public void setTitle(String title) {
    this.title = title;
  }



  public String getContents() {
    return contents;
  }



  public void setContents(String contents) {
    this.contents = contents;
  }



  public String getRdate() {
    return rdate;
  }



  public void setRdate(String rdate) {
    this.rdate = rdate;
  }



  public String getFiles() {
    return files;
  }



  public void setFiles(String files) {
    this.files = files;
  }



  public String getFilesize() {
    return filesize;
  }



  public void setFilesize(String filesize) {
    this.filesize = filesize;
  }



  public String getThumbs() {
    return thumbs;
  }



  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }



  public int getNum() {
    return num;
  }



  public void setNum(int num) {
    this.num = num;
  }



  public int getScore() {
    return score;
  }



  public void setScore(int score) {
    this.score = score;
  }



  public int getCategoryno() {
    return categoryno;
  }



  public void setCategoryno(int categoryno) {
    this.categoryno = categoryno;
  }



  public int getMemberno() {
    return memberno;
  }



  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  
  public List<MultipartFile> getFilesMF() {
    return filesMF;
  }

  public void setFilesMF(List<MultipartFile> filesMF) {
    this.filesMF = filesMF;
  }

  
}

  




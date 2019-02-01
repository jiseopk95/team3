package dev.mvc.answer;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AnswerVO {
/*  CREATE TABLE answer(
      answerno                        NUMBER(10)   NOT NULL  PRIMARY KEY,
      reviewno                      NUMBER(10)   NULL ,
      title                           VARCHAR2(100)  NOT NULL,
      name                            VARCHAR2(30)   NOT NULL,
      emoticon                        VARCHAR2(30)   NULL ,
      content                         CLOB   NOT NULL,
      rdate                           DATE   NOT NULL,
      memberno                      NUMBER(10)   NULL ,
        FOREIGN KEY (reviewno) REFERENCES review (reviewno),
        FOREIGN KEY (memberno) REFERENCES member (memberno)
      );*/
  
/** 댓글 번호*/
 private int answerno;
 /** 후기 번호*/
 private int reviewno;
 /** 제목*/
 private String title;
 /** 글쓴이*/
 private String name;
 /** 이모티콘*/
 private String emoticon;
 /** 내용*/
 private String content;
 /** 등록일*/
 private String rdate;
 /** 회원번호*/
 private int memberno;
 
 

  public AnswerVO() {

  }



  public int getAnswerno() {
    return answerno;
  }



  public void setAnswerno(int answerno) {
    this.answerno = answerno;
  }



  public int getReviewno() {
    return reviewno;
  }



  public void setReviewno(int reviewno) {
    this.reviewno = reviewno;
  }



  public String getTitle() {
    return title;
  }



  public void setTitle(String title) {
    this.title = title;
  }



  public String getName() {
    return name;
  }



  public void setName(String name) {
    this.name = name;
  }



  public String getEmoticon() {
    return emoticon;
  }



  public void setEmoticon(String emoticon) {
    this.emoticon = emoticon;
  }



  public String getContent() {
    return content;
  }



  public void setContent(String content) {
    this.content = content;
  }



  public String getRdate() {
    return rdate;
  }



  public void setRdate(String rdate) {
    this.rdate = rdate;
  }



  public int getMemberno() {
    return memberno;
  }



  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  
  
  
}

  




package dev.mvc.question;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class QuestionVO {
 /* CREATE TABLE question(
      questionno                      NUMBER(10)   NOT NULL  PRIMARY KEY,
      title                           VARCHAR2(100)  NOT NULL,
      rdate                           DATE   NOT NULL,
      content                         CLOB   NOT NULL,
      name                            VARCHAR2(30)   NOT NULL,
      files                           VARCHAR2(30)   NULL ,
      thumbs                          VARCHAR2(30)   NULL ,
      filesize                        VARCHAR2(1000)   NULL ,
      num                             NUMBER(10)   NOT NULL,
      passwd                              VARCHAR2(30)   NULL ,
      visible                              CHAR(1)    DEFAULT 'Y' NOT NULL,
      categoryno                      NUMBER(10)   NULL ,
      memberno                        NUMBER(10)   NULL ,
        FOREIGN KEY (categoryno) REFERENCES category (categoryno),
        FOREIGN KEY (memberno) REFERENCES member (memberno)
      );*/
  
  
/** 질문 번호*/
 private int questionno;
 /** 제목*/
 private String title;
 /** 등록일*/
 private String rdate;
 /** 내용*/
 private String content;
 /** 글쓴이*/
 private String name;
 /** 파일*/
 private String files="";
 /** 미리보기*/
 private String thumbs;
 /** 파일 크기*/
 private String filesize="";
 /**조회수 */
 private int num;
 /**비밀번호*/
 private String passwd;
 /** 평점*/
 private String visible;
 /** 답변 갯수 */
 private int replycnt;
 /** 그룹 번호 */ 
 private int grpno;
 /** 답변 차수 */
 private int indent;
 /** 답변 순서 */
 private int ansnum;
 /** 카테고리 번호*/
 private int categoryno;
 /** 회원번호*/
 private int memberno;
 
 

  public QuestionVO() {

  }



  public int getQuestionno() {
    return questionno;
  }



  public void setQuestionno(int questionno) {
    this.questionno = questionno;
  }



  public String getTitle() {
    return title;
  }



  public void setTitle(String title) {
    this.title = title;
  }



  public String getRdate() {
    return rdate;
  }



  public void setRdate(String rdate) {
    this.rdate = rdate;
  }



  public String getContent() {
    return content;
  }



  public void setContent(String content) {
    this.content = content;
  }



  public String getName() {
    return name;
  }



  public void setName(String name) {
    this.name = name;
  }



  public String getFiles() {
    return files;
  }



  public void setFiles(String files) {
    this.files = files;
  }



  public String getThumbs() {
    return thumbs;
  }



  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }



  public String getFilesize() {
    return filesize;
  }



  public void setFilesize(String filesize) {
    this.filesize = filesize;
  }



  public int getNum() {
    return num;
  }



  public void setNum(int num) {
    this.num = num;
  }



  public String getPasswd() {
    return passwd;
  }



  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }



  public String getVisible() {
    return visible;
  }



  public void setVisible(String visible) {
    this.visible = visible;
  }

/**
  * @return the replycnt
  */
 public int getReplycnt() {
   return replycnt;
 }

 /**
  * @param replycnt the replycnt to set
  */
 public void setReplycnt(int replycnt) {
   this.replycnt = replycnt;
 }

 /**
  * @return the grpno
  */
 public int getGrpno() {
   return grpno;
 }

 /**
  * @param grpno the grpno to set
  */
 public void setGrpno(int grpno) {
   this.grpno = grpno;
 }

 /**
  * @return the indent
  */
 public int getIndent() {
   return indent;
 }

 /**
  * @param indent the indent to set
  */
 public void setIndent(int indent) {
   this.indent = indent;
 }

 /**
  * @return the ansnum
  */
 public int getAnsnum() {
   return ansnum;
 }

 /**
  * @param ansnum the ansnum to set
  */
 public void setAnsnum(int ansnum) {
   this.ansnum = ansnum;
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
  


  
}

  




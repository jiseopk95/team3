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
  
  
/** ���� ��ȣ*/
 private int questionno;
 /** ����*/
 private String title;
 /** �����*/
 private String rdate;
 /** ����*/
 private String content;
 /** �۾���*/
 private String name;
 /** ����*/
 private String files="";
 /** �̸�����*/
 private String thumbs;
 /** ���� ũ��*/
 private String filesize="";
 /**��ȸ�� */
 private int num;
 /**��й�ȣ*/
 private String passwd;
 /** ����*/
 private String visible;
 /** �亯 ���� */
 private int replycnt;
 /** �׷� ��ȣ */ 
 private int grpno;
 /** �亯 ���� */
 private int indent;
 /** �亯 ���� */
 private int ansnum;
 /** ī�װ� ��ȣ*/
 private int categoryno;
 /** ȸ����ȣ*/
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

  




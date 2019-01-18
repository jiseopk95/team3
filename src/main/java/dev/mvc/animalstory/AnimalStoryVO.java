package dev.mvc.animalstory;

/*
 CREATE TABLE animalstory(
anino                           NUMBER(10)   NOT NULL  PRIMARY KEY,
anitype                         VARCHAR2(10)   NOT NULL,
title                           VARCHAR2(100)  NOT NULL,
content                         CLOB   NOT NULL,
files                           VARCHAR2(1000)   NULL ,
thumb                           VARCHAR2(1000)   NULL ,
sizes                           VARCHAR2(1000)   NULL ,
rdate                           DATE   NOT NULL,
cnt                             NUMBER(10)   NOT NULL,
managerno                       NUMBER(10)   NULL ,
  FOREIGN KEY (managerno) REFERENCES manager (managerno)
); 
 */
public class AnimalStoryVO {

//  �ִϸֽ��丮 �۹�ȣ
  private int anino;
//  ����Ÿ��
  private String anitype;
//  ������
  private String title;
//  �۳���
  private String content;
//  �̹��� Ȥ�� ����
  private String files;
//  �����
  private String thumb;
//  ���� ������
  private String size;
//  ��ȸ��
  private int cnt;
//  �� ����� ������ ��ȣ
  private int managerno;
  private String rdate;
  
  public AnimalStoryVO() {
    
  }

  public int getAnino() {
    return anino;
  }

  public void setAnino(int anino) {
    this.anino = anino;
  }

  public String getAnitype() {
    return anitype;
  }

  public void setAnitype(String anitype) {
    this.anitype = anitype;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFiles() {
    return files;
  }

  public void setFiles(String files) {
    this.files = files;
  }

  public String getThumb() {
    return thumb;
  }

  public void setThumb(String thumb) {
    this.thumb = thumb;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public int getManagerno() {
    return managerno;
  }

  public void setManagerno(int managerno) {
    this.managerno = managerno;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
}

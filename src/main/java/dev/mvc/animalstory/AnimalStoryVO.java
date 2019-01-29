package dev.mvc.animalstory;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/*
 CREATE TABLE animalstory(
anino                           NUMBER(10)   NOT NULL  PRIMARY KEY,
anitype                         VARCHAR2(10)   NOT NULL,
title                           VARCHAR2(100)  NOT NULL,
content                         CLOB   NOT NULL,
files                           VARCHAR2(1000)   NULL ,
thumbs                           VARCHAR2(1000)   NULL ,
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
//  ���ε�� �̹��� Ȥ�� ����
  private String files;
  /** Preview ���� �̹��� 200 X 150, �ڵ� ������ */
  private String thumbs = "";
  
  /** ���� �̹����߿� ù��° Preview �̹��� ����, 200 X 150 */
  private String thumb = "";
//  ���ε�� ���� ������
  private String sizes;
//  ��ȸ��
  private int cnt;
//  �� ����� ������ ��ȣ
  private int managerno;
//  �ۼ���
  private String manager;
//  �ۼ���
  private String rdate;
  
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  �������� ���� ���ε�
  */  
  private List<MultipartFile> filesMF;
  
  /** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String sizesLabel;
  
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

  public String getThumbs() {
    return thumbs;
  }

  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }

  public String getThumb() {
    return thumb;
  }

  public void setThumb(String thumb) {
    this.thumb = thumb;
  }

  public String getSizes() {
    return sizes;
  }

  public void setSizes(String sizes) {
    this.sizes = sizes;
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

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  public List<MultipartFile> getFilesMF() {
    return filesMF;
  }

  public void setFilesMF(List<MultipartFile> filesMF) {
    this.filesMF = filesMF;
  }

  public String getSizesLabel() {
    return sizesLabel;
  }

  public void setSizesLabel(String sizesLabel) {
    this.sizesLabel = sizesLabel;
  }
  
  
}

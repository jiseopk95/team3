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

//  애니멀스토리 글번호
  private int anino;
//  동물타입
  private String anitype;
//  글제목
  private String title;
//  글내용
  private String content;
//  업로드될 이미지 혹은 파일
  private String files;
  /** Preview 소형 이미지 200 X 150, 자동 생성됨 */
  private String thumbs = "";
  
  /** 여러 이미지중에 첫번째 Preview 이미지 저장, 200 X 150 */
  private String thumb = "";
//  업로드된 파일 사이즈
  private String sizes;
//  조회수
  private int cnt;
//  글 등록한 관계자 번호
  private int managerno;
//  작성자
  private String manager;
//  작성일
  private String rdate;
  
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  여러개의 파일 업로드
  */  
  private List<MultipartFile> filesMF;
  
  /** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
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

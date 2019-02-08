package dev.mvc.beauty;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BeautyVO {
/*  styleno                           NUMBER(10)     NOT NULL    PRIMARY KEY,
    categoryno                        NUMBER(10)     NULL ,
    managerno                         NUMBER(10)     NULL ,
    title                             VARCHAR2(50)     NOT NULL,
    name                              VARCHAR2(50)     NOT NULL,
    rname                             VARCHAR2(20)     NOT NULL,
    like1                             NUMBER(10)     NOT NULL,
    email                             VARCHAR2(20)     NULL ,
    content                           CLOB     NOT NULL,
    cnt                               NUMBER(10)     DEFAULT 0     NOT NULL,
    image                             VARCHAR2(1000)     NULL ,
    image_name                        VARCHAR2(1000)     NULL ,
    thumb                             VARCHAR2(1000)     NULL ,
    sizes                             VARCHAR2(1000)     DEFAULT 0     NULL ,
    pay                               NUMBER(10)     NOT NULL,
    times                             NUMBER(10)     NOT NULL,
    rdate                             DATE     NOT NULL,*/
  
  private String id = "";
  
  private int styleno;
  private int categoryno;  //사실상 안쓰게됐음.
  private int managerno;
  private String title="";
  private String name="";
  private String rname="";
  private int like1;
  private String email="";
  private String content="";
  private int cnt;
  private String image="";
  private String image_name="";
  private String thumb="";
  private String sizes="";
  private int pay;
  private int times;
  private String rdate="";
  
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  여러개의 파일 업로드
  */  
  private List<MultipartFile> filesMF;

  /** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String sizesLabel;

  
  public BeautyVO() {
  
  }

  public int getStyleno() {
    return styleno;
  }

  public void setStyleno(int styleno) {
    this.styleno = styleno;
  }

  public int getCategoryno() {
    return categoryno;
  }

  public void setCategoryno(int categoryno) {
    this.categoryno = categoryno;
  }

  public int getManagerno() {
    return managerno;
  }

  public void setManagerno(int managerno) {
    this.managerno = managerno;
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

  public String getRname() {
    return rname;
  }

  public void setRname(String rname) {
    this.rname = rname;
  }

  public int getLike1() {
    return like1;
  }

  public void setLike1(int like1) {
    this.like1 = like1;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getCnt() {
    return cnt;
  }
 
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getImage_name() {
    return image_name;
  }

  public void setImage_name(String image_name) {
    this.image_name = image_name;
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

  public int getPay() {
    return pay;
  }

  public void setPay(int pay) {
    this.pay = pay;
  }

  public int getTimes() {
    return times;
  }

  public void setTimes(int times) {
    this.times = times;
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
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  
}
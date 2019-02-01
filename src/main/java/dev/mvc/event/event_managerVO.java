package dev.mvc.event;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class event_managerVO {
 
//manager table
  private int managerno;
  private String kind;
  private String id;
  private String name;
  
  public int getManagerno() {
    return managerno;
  }
  public void setManagerno(int managerno) {
    this.managerno = managerno;
  }
  public String getKind() {
    return kind;
  }
  public void setKind(String kind) {
    this.kind = kind;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  
  /* eventno                           NUMBER(10)     NOT NULL PRIMARY KEY,
  managerno                         NUMBER(10)     NULL,
  presentno                         NUMBER(10)     NULL,
  title                             VARCHAR2(50)     NOT NULL,
  content                           CLOB     NOT NULL,
  period_start                      VARCHAR2(30)     NOT NULL,
  period_end                        VARCHAR2(30)     NOT NULL,
  writer                            VARCHAR2(10)     NOT NULL,
  usercnt                           NUMBER(10)     NOT NULL,
  image                             VARCHAR2(1000)     NULL ,
  image_size                        VARCHAR2(1000)     NULL ,
  thumb                             VARCHAR2(1000)     NULL ,
  windate                           VARCHAR2(20)     NOT NULL,
  wincnt                            NUMBER(10)     NULL ,
  winner                            VARCHAR2(1000)     NULL ,
  rdate                             DATE     NOT NULL,*/
  
  private int eventno;
/*  private int managerno;*/
  private int presentno;
  private String title="";
  private String content="";
  private String period_start="";
  private String period_end="";
  private String writer="";
  private int usercnt;
  private String image="";
  private String image_size="";
  private String thumb="";
  private String windate="";
  private int wincnt;
  private String winner="";
  private String rdate="";
  
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  여러개의 파일 업로드
  */  
  private List<MultipartFile> filesMF;

  /** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String sizesLabel;
  
  
  public event_managerVO() {

  }
  

  public int getEventno() {
    return eventno;
  }
  public void setEventno(int eventno) {
    this.eventno = eventno;
  }
  public int getPresentno() {
    return presentno;
  }
  public void setPresentno(int presentno) {
    this.presentno = presentno;
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
  public String getPeriod_start() {
    return period_start;
  }
  public void setPeriod_start(String period_start) {
    this.period_start = period_start;
  }
  public String getPeriod_end() {
    return period_end;
  }
  public void setPeriod_end(String period_end) {
    this.period_end = period_end;
  }
  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }
  public int getUsercnt() {
    return usercnt;
  }
  public void setUsercnt(int usercnt) {
    this.usercnt = usercnt;
  }
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }
  public String getImage_size() {
    return image_size;
  }
  public void setImage_size(String image_size) {
    this.image_size = image_size;
  }
  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  public String getWindate() {
    return windate;
  }
  public void setWindate(String windate) {
    this.windate = windate;
  }
  public int getWincnt() {
    return wincnt;
  }
  public void setWincnt(int wincnt) {
    this.wincnt = wincnt;
  }
  public String getWinner() {
    return winner;
  }
  public void setWinner(String winner) {
    this.winner = winner;
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
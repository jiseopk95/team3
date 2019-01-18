package dev.mvc.reservation;

/*CREATE TABLE reservation(
reservationno                   NUMBER(10)   NOT NULL  PRIMARY KEY,
restype                         VARCHAR2(10)   NOT NULL,
title                           VARCHAR2(50)   NOT NULL,
label                           VARCHAR2(50)   NOT NULL,
resdate                         VARCHAR2(50)   NOT NULL,
restime                         VARCHAR2(50)   NOT NULL,
content                         CLOB   NOT NULL,
petno                           NUMBER(10)   NULL ,
memberno                        NUMBER(10)   NULL ,
rdate                           DATE   NOT NULL,
  FOREIGN KEY (petno) REFERENCES pet (petno),
  FOREIGN KEY (memberno) REFERENCES member (memberno)
);*/
public class ReservationVO {

//  예약글번호
  private int reservationno;
//  제목
  private String title;
//  달력에 표시되는 항목
  private String label;
// 예약된 날짜
  private String resdate;
//  예약된 시간
  private String restime;
//  내용
  private String content;
//예약타입 - 의료 / 미용
  private String restype;
//  반려동물 이름
  private String name;
//  예약된 반려동물 번호
  private int petno;
//  예약된 반려동물 주인 번호
  private int memberno;
//  예약글 작성일
  private String rdate;
  
  public ReservationVO() {
    
  }

  public int getReservationno() {
    return reservationno;
  }

  public void setReservationno(int reservationno) {
    this.reservationno = reservationno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getResdate() {
    return resdate;
  }

  public void setResdate(String resdate) {
    this.resdate = resdate;
  }

  public String getRestime() {
    return restime;
  }

  public void setRestime(String restime) {
    this.restime = restime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getRestype() {
    return restype;
  }

  public void setRestype(String restype) {
    this.restype = restype;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPetno() {
    return petno;
  }

  public void setPetno(int petno) {
    this.petno = petno;
  }

  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
}

package dev.mvc.chart;
/*
CREATE TABLE chart(
chartno                         NUMBER(10)   NOT NULL  PRIMARY KEY,
managerno                       NUMBER(10)   NULL ,
title                           VARCHAR2(50)   NOT NULL,
symptom                         CLOB   NOT NULL,
sick                            VARCHAR2(20)   NOT NULL,
medicine                        VARCHAR2(50)   NOT NULL,
resdate                         VARCHAR2(20)   NOT NULL,
rdate                           DATE   NOT NULL,
stay                            VARCHAR2(10)   NOT NULL,
etc                             VARCHAR2(100)  NOT NULL,
petno                           NUMBER(10)   NULL ,
 FOREIGN KEY (petno) REFERENCES pet (petno),
 FOREIGN KEY (managerno) REFERENCES manager (managerno)
);
*/
public class ChartVO {
  
//  차트 글번호
  private int chartno;
//등록한 관계자번호
  private int managerno;
//차트 대상인 반려동물 번호
  private int petno;
//  동물이름 
  private String petname;
//  제목
  private String title;
//  병명
  private String sick;
//  투여약
  private String medicine;
//  입원여부
  private String stay;
//  기타
  private String etc;
//  보호자 이름
  private String name;
//  등록일
  private String rdate;
  
  public ChartVO() {
    
  }

  public int getChartno() {
    return chartno;
  }

  public void setChartno(int chartno) {
    this.chartno = chartno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSick() {
    return sick;
  }

  public void setSick(String sick) {
    this.sick = sick;
  }

  public String getMedicine() {
    return medicine;
  }

  public void setMedicine(String medicine) {
    this.medicine = medicine;
  }

  public String getStay() {
    return stay;
  }

  public void setStay(String stay) {
    this.stay = stay;
  }

  public String getEtc() {
    return etc;
  }

  public void setEtc(String etc) {
    this.etc = etc;
  }

  public int getManagerno() {
    return managerno;
  }

  public void setManagerno(int managerno) {
    this.managerno = managerno;
  }

  public int getPetno() {
    return petno;
  }

  public void setPetno(int petno) {
    this.petno = petno;
  }

  public String getPetname() {
    return petname;
  }

  public void setPetname(String petname) {
    this.petname = petname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
}

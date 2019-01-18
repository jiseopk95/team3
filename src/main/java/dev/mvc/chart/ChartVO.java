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
  
//  ��Ʈ �۹�ȣ
  private int chartno;
//����� �����ڹ�ȣ
  private int managerno;
//��Ʈ ����� �ݷ����� ��ȣ
  private int petno;
//  �����̸� 
  private String petname;
//  ����
  private String title;
//  ����
  private String sick;
//  ������
  private String medicine;
//  �Կ�����
  private String stay;
//  ��Ÿ
  private String etc;
//  ��ȣ�� �̸�
  private String name;
//  �����
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

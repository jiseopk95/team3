package dev.mvc.event;
/*presentno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
info                              VARCHAR2(50)     NOT NULL*/
public class event_presentVO {
private int presentno;
private String info = "";
private String end_date = "";

public event_presentVO() {

}
public int getPresentno() {
  return presentno;
}
public void setPresentno(int presentno) {
  this.presentno = presentno;
}
public String getInfo() {
  return info;
}
public void setInfo(String info) {
  this.info = info;
}
public String getEnd_date() {
  return end_date;
}
public void setEnd_date(String end_date) {
  this.end_date = end_date;
}
}
package dev.mvc.member_login;

public class Member_loginVO {
  private int member_loginno;
  private int memberno;
  private String ip = "";
  private String rdate ="";
  
  public Member_loginVO() {
  }
  
  public int getMember_loginno() {
    return member_loginno;
  }
  public void setMember_loginno(int member_loginno) {
    this.member_loginno = member_loginno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
}
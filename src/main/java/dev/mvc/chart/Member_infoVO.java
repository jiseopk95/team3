package dev.mvc.chart;

public class Member_infoVO {

//  주인 번호
  private int memberno;
//  주인 이름
  private String name;
//  폰 번호
  private String phone;
  
  public Member_infoVO() {
    
  }
  
  public int getMemberno() {
    return memberno;
  }

  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  
}

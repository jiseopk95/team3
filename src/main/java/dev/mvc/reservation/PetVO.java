package dev.mvc.reservation;

public class PetVO {

//  반려동물 번호
  private int petno;
//  주인 번호
  private int memberno;
//  주인 이름
  private String mname;
//  반려동물 이름
  private String name;
  
  public PetVO() {
    
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

  public String getMname() {
    return mname;
  }

  public void setMname(String mname) {
    this.mname = mname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  
}

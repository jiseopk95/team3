package dev.mvc.reservation;

public class PetVO {

//  �ݷ����� ��ȣ
  private int petno;
//  ���� ��ȣ
  private int memberno;
//  ���� �̸�
  private String mname;
//  �ݷ����� �̸�
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

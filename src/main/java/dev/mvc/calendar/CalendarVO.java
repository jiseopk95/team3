package dev.mvc.calendar;

public class CalendarVO {
  
  /* reservation���̺� */
  
//  ����۹�ȣ - calendarno
  private int reservationno; 
//  ����Ÿ�� - �Ƿ� / �̿�
  private String restype;
//  �����̸� - name
  private String name;
//���� - title
  private String title;
//  �޷¿� ǥ�õǴ� �׸� - label
  private String label;
// ����� ��¥ - labeldate
  private String resdate;
//  ����� �ð� 
  private String restime;
//  ���� - content
  private String content;
//  ����� �ݷ����� ��ȣ
  private int petno;
//  ����� �ݷ����� ���� ��ȣ
  private int memberno;
//  ����� �ۼ���
  private String rdate;
  
  /* member���̺� */
  private String mname;
  private String phone;
  private String email;
  
  /* pet���̺� */
  private String pname;
  private String age;
  private String gender;
  private String pet_type;
  private String neutralization;
  private String weight;
  
  
  public CalendarVO() {
    
  }

  public int getReservationno() {
    return reservationno;
  }

  public void setReservationno(int reservationno) {
    this.reservationno = reservationno;
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

  public String getMname() {
    return mname;
  }

  public void setMname(String mname) {
    this.mname = mname;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getPet_type() {
    return pet_type;
  }

  public void setPet_type(String pet_type) {
    this.pet_type = pet_type;
  }

  public String getNeutralization() {
    return neutralization;
  }

  public void setNeutralization(String neutralization) {
    this.neutralization = neutralization;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }
  
  
  
}

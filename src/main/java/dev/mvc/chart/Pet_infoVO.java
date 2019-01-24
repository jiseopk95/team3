package dev.mvc.chart;

public class Pet_infoVO {

//  반려동물 번호
  private int petno;
//  주인 번호
  private int memberno;
//  반려동물 이름
  private String name;
//  나이
  private String age;
//  성별
  private String gender;
//  품종
  private String pet_type;
//  중성화
  private String neutralization;
//  몸무게
  private float weight;
  
  public Pet_infoVO() {
    
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public float getWeight() {
    return weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }
  
  
  
}

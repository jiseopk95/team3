package dev.mvc.chart;

public class Pet_infoVO {

//  �ݷ����� ��ȣ
  private int petno;
//  ���� ��ȣ
  private int memberno;
//  �ݷ����� �̸�
  private String name;
//  ����
  private String age;
//  ����
  private String gender;
//  ǰ��
  private String pet_type;
//  �߼�ȭ
  private String neutralization;
//  ������
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

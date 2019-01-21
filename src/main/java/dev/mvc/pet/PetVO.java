package dev.mvc.pet;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PetVO {
  /*
  mno INT NOT NULL AUTO_INCREMENT, -- ȸ�� ��ȣ, ���ڵ带 �����ϴ� �÷� 
  id           VARCHAR(20)   NOT NULL UNIQUE, -- ���̵�, �ߺ� �ȵ�, ���ڵ带 ���� 
  passwd    VARCHAR(20)   NOT NULL, -- �н�����, ������ ����
  mname    VARCHAR(20)   NOT NULL, -- ����, �ѱ� 10�� ���� ����
  tel          VARCHAR(14)   NOT NULL, -- ��ȭ��ȣ
  zipcode   VARCHAR(5)        NULL, -- �����ȣ, 12345
  address1  VARCHAR(80)       NULL, -- �ּ� 1
  address2  VARCHAR(50)       NULL, -- �ּ� 2
  mdate     DATETIME            NOT NULL, -- ������    
  PRIMARY KEY (mno)             -- �ѹ� ��ϵ� ���� �ߺ� �ȵ� 
  */

  private int petno;
  private int memberno;
  private String name = "";
  private String age = "";
  private String gender = "";
  private String pet_type = "";
  private String neutralization = "";
  private Double weight;
  private String files = "";
  private String thumbs = "";
  private String filesizes = "";
  private String id_save = "";
  /** 
  Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
  DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
  �ϳ��� ���� ���ε�
*/  
// private MultipartFile filesMF;

/** 
Spring Framework���� �ڵ� ���ԵǴ� ���ε� ���� ��ü,
DBMS �� ���� �÷��� �������� �ʰ� ���� �ӽ� ���� ����.
�������� ���� ���ε�
*/  
  private List<MultipartFile> filesMF;

/** size1�� �ĸ� ���� ��¿� ����, ���� �÷��� �������� ����. */
  private String sizesLabel;
  
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
  public Double getWeight() {
    return weight;
  }
  public void setWeight(Double weight) {
    this.weight = weight;
  }
  public String getFiles() {
    return files;
  }
  public void setFiles(String files) {
    this.files = files;
  }
  public String getThumbs() {
    return thumbs;
  }
  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }
  public String getFilesizes() {
    return filesizes;
  }
  public void setFilesizes(String filesizes) {
    this.filesizes = filesizes;
  }
  public String getId_save() {
    return id_save;
  }
  public void setId_save(String id_save) {
    this.id_save = id_save;
  }
  public List<MultipartFile> getFilesMF() {
    return filesMF;
  }
  public void setFilesMF(List<MultipartFile> filesMF) {
    this.filesMF = filesMF;
  }
  public String getSizesLabel() {
    return sizesLabel;
  }
  public void setSizesLabel(String sizesLabel) {
    this.sizesLabel = sizesLabel;
  }
  
  

}
package dev.mvc.pet;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PetVO {
  /*
  mno INT NOT NULL AUTO_INCREMENT, -- 회원 번호, 레코드를 구분하는 컬럼 
  id           VARCHAR(20)   NOT NULL UNIQUE, -- 아이디, 중복 안됨, 레코드를 구분 
  passwd    VARCHAR(20)   NOT NULL, -- 패스워드, 영숫자 조합
  mname    VARCHAR(20)   NOT NULL, -- 성명, 한글 10자 저장 가능
  tel          VARCHAR(14)   NOT NULL, -- 전화번호
  zipcode   VARCHAR(5)        NULL, -- 우편번호, 12345
  address1  VARCHAR(80)       NULL, -- 주소 1
  address2  VARCHAR(50)       NULL, -- 주소 2
  mdate     DATETIME            NOT NULL, -- 가입일    
  PRIMARY KEY (mno)             -- 한번 등록된 값은 중복 안됨 
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
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  하나의 파일 업로드
*/  
// private MultipartFile filesMF;

/** 
Spring Framework에서 자동 주입되는 업로드 파일 객체,
DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
여러개의 파일 업로드
*/  
  private List<MultipartFile> filesMF;

/** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
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
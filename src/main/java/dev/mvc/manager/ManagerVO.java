package dev.mvc.manager;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ManagerVO {
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

  /** ������ ��ȣ */
  private int managerno;
  /** ���̵� */
  private String id = "";
  /** �н����� */
  private String passwd = "";
  /** ���� */
  private String kind = "";
  /** ������ ���� */
  private String name = "";
  
  private String position = "";
  
  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  /** ��ȭ ��ȣ */
  private String phone = "";
  
  private String email = "";
  /** ���� ��ȣ */
  private String zipcode = "";
  /** �ּ� 1 */
  private String address1 = "";
  /** �ּ� 2 */
  private String address2 = "";
  /** ������ */
  private String rdate = "";
  
  /** ��ϵ� �н����� */
  private String old_passwd = "";
  /** id ���� ���� */
  private String id_save = "";
  /** passwd ���� ���� */
  private String passwd_save = "";
  /** �̵��� �ּ� ���� */
  private String url_address = "";
  
  private String files = "";
  private String thumbs = "";
  private String filesizes = "";
  
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
  public int getManagerno() {
    return managerno;
  }
  public void setManagerno(int managerno) {
    this.managerno = managerno;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
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
  public String getZipcode() {
    return zipcode;
  }
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
  public String getAddress1() {
    return address1;
  }
  public void setAddress1(String address1) {
    this.address1 = address1;
  }
  public String getAddress2() {
    return address2;
  }
  public void setAddress2(String address2) {
    this.address2 = address2;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getOld_passwd() {
    return old_passwd;
  }
  public void setOld_passwd(String old_passwd) {
    this.old_passwd = old_passwd;
  }
  public String getId_save() {
    return id_save;
  }
  public void setId_save(String id_save) {
    this.id_save = id_save;
  }
  public String getPasswd_save() {
    return passwd_save;
  }
  public void setPasswd_save(String passwd_save) {
    this.passwd_save = passwd_save;
  }
  public String getUrl_address() {
    return url_address;
  }
  public void setUrl_address(String url_address) {
    this.url_address = url_address;
  }
  public String getKind() {
    return kind;
  }
  public void setKind(String kind) {
    this.kind = kind;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  
  

}
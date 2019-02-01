package dev.mvc.user;

public class member_userVO{
  
  private int eventuserno;
  private int e_memberno;
  private int eventno;
  private String joindate="";
  private String win=""; 
  
  public member_userVO() {
    
  }
  public int getEventuserno() {
    return eventuserno;
  }
  public void setEventuserno(int eventuserno) {
    this.eventuserno = eventuserno;
  }
  public int getE_memberno() {
    return e_memberno;
  }
  public void setE_memberno(int e_memberno) {
    this.e_memberno = e_memberno;
  }
  public int getEventno() {
    return eventno;
  }
  public void setEventno(int eventno) {
    this.eventno = eventno;
  }
  public String getJoindate() {
    return joindate;
  }
  public void setJoindate(String joindate) {
    this.joindate = joindate;
  }
  public String getWin() {
    return win;
  }
  public void setWin(String win) {
    this.win = win;
  }
  
  
  
  
  /** 회원 번호 */
  private int memberno;
  /** 아이디 */
  private String id = "";
  /** 회원 성명 */
  private String name = "";
  
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
  
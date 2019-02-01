package dev.mvc.user;
/*eventuserno                       NUMBER(10)     NOT NULL    PRIMARY KEY,
memberno                          NUMBER(10)     NULL ,
eventno                           NUMBER(10)     NULL ,
joindate                          DATE     NOT NULL,
win                               VARCHAR2(10)    NULL,
FOREIGN KEY (memberno) REFERENCES member (memberno),
FOREIGN KEY (eventno) REFERENCES event (eventno)
);

COMMENT ON TABLE eventuser is '이벤트 참여자';
COMMENT ON COLUMN eventuser.eventuserno is '이벤트 참여자 번호';
COMMENT ON COLUMN eventuser.memberno is '회원번호';
COMMENT ON COLUMN eventuser.eventno is '이벤트 목록 번호';
COMMENT ON COLUMN eventuser.joindate is '참여날짜';
COMMENT ON COLUMN eventuser.win is '당첨여부';*/

public class UserVO{
  
  private int eventuserno;
  private int memberno;
  private int eventno;
  private String joindate="";
  private String win=""; 
  
  public UserVO() {
    
  }
  public int getEventuserno() {
    return eventuserno;
  }
  public void setEventuserno(int eventuserno) {
    this.eventuserno = eventuserno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
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
  
  
}
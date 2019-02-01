package dev.mvc.animalstory;

public class EventVO {
  
  private int evnetno;
  private int eventuserno;
  private int memberno;
  private String joindate;
  private String win;
  
  public EventVO() {
    
  }

  public int getEvnetno() {
    return evnetno;
  }

  public void setEvnetno(int evnetno) {
    this.evnetno = evnetno;
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

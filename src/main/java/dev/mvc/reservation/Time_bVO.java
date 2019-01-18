package dev.mvc.reservation;

public class Time_bVO {

//  시간글 번호
  private int restype;
//  미용 예약 가능 시간
  private String restime;
  
  public Time_bVO() {
    
  }

  public int getRestype() {
    return restype;
  }

  public void setRestype(int restype) {
    this.restype = restype;
  }

  public String getRestime() {
    return restime;
  }

  public void setRestime(String restime) {
    this.restime = restime;
  }

  
}


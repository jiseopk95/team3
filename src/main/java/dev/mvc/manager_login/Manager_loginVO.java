package dev.mvc.manager_login;

public class Manager_loginVO {
  private int manager_loginno;
  private int managerno;
  private String ip = "";
  private String rdate ="";
  
  public Manager_loginVO() {
  }

  public int getManager_loginno() {
    return manager_loginno;
  }

  public void setManager_loginno(int manager_loginno) {
    this.manager_loginno = manager_loginno;
  }

  public int getManagerno() {
    return managerno;
  }

  public void setManagerno(int managerno) {
    this.managerno = managerno;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  
  
}
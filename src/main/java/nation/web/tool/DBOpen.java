package nation.web.tool;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBOpen {
  
  // MySQL Connection Pool 기반 DBMS 접속 
  public Connection getConnection(){
    Connection con = null;          // DBMS 연결 객체

    try{
      // blog_v2jq 는 web.xml의 registerPool 변수의 값이어야 함 
      // <param-name>registerPool</param-name> <!-- 변수명 -->
      // <param-value>blog_v6jq</param-value>    <!-- 변수값 -->
      String poolName = "jdbc:apache:commons:dbcp:blog_v6jq";
      con = DriverManager.getConnection(poolName);
      // System.out.println("Connection Mode 2: " + con.hashCode());

    }catch(Exception e){
      e.printStackTrace();
    }
    
    return con; // Void methods cannot return a value
  }
  
  public Connection getConnectionNormal(){
    Connection con = null; // 메모리가 할당되지 않음.
    
    try{
      String jdbc = "oracle.jdbc.driver.OracleDriver"; // MySQL 연결 Drvier 
      String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
      String user = "ahr"; 
      String pass = "1234";
      
      Class.forName(jdbc); // memory로 드라이버 클래스를 로딩함.
      con = DriverManager.getConnection(url, user, pass); // MySQL 연결
      //System.out.println("Connection Mode 1: " + con.hashCode());
      
    }catch(Exception e){
      e.printStackTrace();
    }
    
    return con;
  }
  
  public static void main(String[] args) {
    DBOpen dbopen = new DBOpen();
    System.out.println(dbopen.getConnectionNormal());
    
  }
  
  
}
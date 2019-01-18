package nation.web.tool;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBOpen {
  
  // MySQL Connection Pool ��� DBMS ���� 
  public Connection getConnection(){
    Connection con = null;          // DBMS ���� ��ü

    try{
      // blog_v2jq �� web.xml�� registerPool ������ ���̾�� �� 
      // <param-name>registerPool</param-name> <!-- ������ -->
      // <param-value>blog_v6jq</param-value>    <!-- ������ -->
      String poolName = "jdbc:apache:commons:dbcp:blog_v6jq";
      con = DriverManager.getConnection(poolName);
      // System.out.println("Connection Mode 2: " + con.hashCode());

    }catch(Exception e){
      e.printStackTrace();
    }
    
    return con; // Void methods cannot return a value
  }
  
  public Connection getConnectionNormal(){
    Connection con = null; // �޸𸮰� �Ҵ���� ����.
    
    try{
      String jdbc = "oracle.jdbc.driver.OracleDriver"; // MySQL ���� Drvier 
      String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
      String user = "ahr"; 
      String pass = "1234";
      
      Class.forName(jdbc); // memory�� ����̹� Ŭ������ �ε���.
      con = DriverManager.getConnection(url, user, pass); // MySQL ����
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
package dev.mvc.calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nation.web.tool.DBClose;
import nation.web.tool.DBOpen;
import dev.mvc.calendar.CalendarVO;

public class CalendarDAO {
  
//  Ķ������ ���� ���̺��
  private String table_name = "reservation";
  
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  public CalendarDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
  
  /** 
   * �������� �׷��ȣ�� ���ؼ� ��ü ����� ������.
   * @return
   * @throws SQLException
   */
  public ArrayList list(int memberno) throws SQLException {
    Connection con = dbopen.getConnectionNormal();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = null;
    ArrayList list = null; // dto ����� ����

    list = new ArrayList();

    sql = new StringBuffer();

    sql.append(" SELECT reservationno, restype, resdate, label, title, content ");
    sql.append(" FROM " + table_name);
    sql.append(" WHERE memberno = " + memberno);
    sql.append(" ORDER BY reservationno DESC;");

    pstmt = con.prepareStatement(sql.toString());
    
    rs = pstmt.executeQuery(); // SELECT ���� ����

    while (rs.next() == true) {
      CalendarVO calendarVO = new CalendarVO();
      
      if(rs.getString("restype").equals("1")) {
        calendarVO.setRestype("�Ƿ�");
      } else if(rs.getString("restype").equals("2")) {
        calendarVO.setRestype("�̿�");
      }
      calendarVO.setReservationno(rs.getInt("reservationno"));// DBMS --> JAVA
      calendarVO.setMemberno(rs.getInt("memberno"));
      calendarVO.setResdate(rs.getString("resdate"));
      calendarVO.setLabel(rs.getString("label"));
      calendarVO.setTitle(rs.getString("title"));
      calendarVO.setContent(rs.getString("content"));

      list.add(calendarVO);

    }

    dbclose.close(con, pstmt, rs);

    return list;
  }
  
  /**
   * ��¥�� �°� ���̺��� ���� - ȸ����ȣ���� ���� 
   * @param date
   * @param memberno
   * @return
   * @throws SQLException
   */
  public ArrayList listLabel(String date, int memberno) throws SQLException {
    Connection con = dbopen.getConnectionNormal();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = sql = new StringBuffer();
    ArrayList list = new ArrayList();
    
    if(memberno != 1) {
      sql.append(" SELECT reservationno, restype, resdate, label, title, content");
      sql.append(" FROM " + table_name);
      sql.append(" WHERE resdate=? AND memberno=?");  // 2013-10-15
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, date);
      pstmt.setInt(2, memberno);
      
    } else {
      sql.append(" SELECT r.reservationno, r.restype, r.resdate, r.label, r.title, r.content, p.name");
      sql.append(" FROM reservation r, pet p");
      sql.append(" WHERE r.resdate=? AND r.petno = p.petno");  // 2013-10-15
      
      pstmt = con.prepareStatement(sql.toString());
      pstmt.setString(1, date);
    }

    rs = pstmt.executeQuery(); // SELECT ���� ����
    
    while (rs.next() == true) {
      CalendarVO calendarVO = new CalendarVO();
      
      if(rs.getString("restype").equals("1")) {
        calendarVO.setRestype("�Ƿ�");
      } else if(rs.getString("restype").equals("2")) {
        calendarVO.setRestype("�̿�");
      }
      
      calendarVO.setReservationno(rs.getInt("reservationno"));// DBMS --> JAVA
      calendarVO.setResdate(rs.getString("resdate"));
      calendarVO.setLabel(rs.getString("label"));
      calendarVO.setTitle(rs.getString("title"));
      calendarVO.setContent(rs.getString("content"));
      if(memberno == 1) {
        calendarVO.setName(rs.getString("name"));
      }
      list.add(calendarVO);

    }

    dbclose.close(con, pstmt, rs);

    return list;
  }
}

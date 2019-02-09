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
  
//  캘린더로 쓰일 테이블명
  private String table_name = "reservation";
  
  private DBOpen dbopen = null;
  private DBClose dbclose = null;
  
  public CalendarDAO() {
    dbopen = new DBOpen();
    dbclose = new DBClose();
  }
  
  /** 
   * 페이지와 그룹번호에 의해서 전체 목록을 가져옴.
   * @return
   * @throws SQLException
   */
  public ArrayList list(int memberno) throws SQLException {
    Connection con = dbopen.getConnectionNormal();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    StringBuffer sql = null;
    ArrayList list = null; // dto 목록을 저장

    list = new ArrayList();

    sql = new StringBuffer();

    sql.append(" SELECT reservationno, restype, resdate, label, title, content ");
    sql.append(" FROM " + table_name);
    sql.append(" WHERE memberno = " + memberno);
    sql.append(" ORDER BY reservationno DESC;");

    pstmt = con.prepareStatement(sql.toString());
    
    rs = pstmt.executeQuery(); // SELECT 쿼리 실행

    while (rs.next() == true) {
      CalendarVO calendarVO = new CalendarVO();
      
      if(rs.getString("restype").equals("1")) {
        calendarVO.setRestype("의료");
      } else if(rs.getString("restype").equals("2")) {
        calendarVO.setRestype("미용");
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
   * 날짜에 맞게 레이블을 넣음 - 회원번호별로 추출 
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

    rs = pstmt.executeQuery(); // SELECT 쿼리 실행
    
    while (rs.next() == true) {
      CalendarVO calendarVO = new CalendarVO();
      
      if(rs.getString("restype").equals("1")) {
        calendarVO.setRestype("의료");
      } else if(rs.getString("restype").equals("2")) {
        calendarVO.setRestype("미용");
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

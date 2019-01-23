<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Calendar, java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="nation.web.tool.Tool" %>
<%@ page import="dev.mvc.calendar.CalendarDAO, dev.mvc.calendar.CalendarVO" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자용 캘린더</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
#calendar_wrap {
  margin: 10px auto;
  text-align:center;
  width: 75%;
  border: none;
}
#calendar_wrap a {
  color: #000000 !important;
}
#calendar_wrap a:hover{
  text-decoration: none !important;
  color: #000000;
  font-weight: bold;
}
/* 모든 TABLE 태그에 적용됨. */
table{
  width: 100%;
  margin: 0px auto;
  border: none; /* 선 스타일 실선 지정*/
  border-collapse: collapse; /* TH, TD의 선을 단일선으로 병합 */ 
  margin-left: auto;
  margin-right:auto;
}
.weekdays {
  background-color: #865da0;
}
.weekdays td{
  color: white;
  height: 25px;
}
.month {
  height: 60px;
}
.month td a {
  color: #343434;
  font-size: 20px;
}
.month td {
  font-size: 25px;
  color:#343434;
}
td{
  text-align: center; /* 문자열 가운데 정렬 */  
}
/* 출력되는 A 태그 기본 모양 */
.calendar_a a:link{ 
  text-decoration: none !important; /* 밑줄 삭제 */
  color: #000000;
}
/* A 태그에 마우스가 위치했을 때 */
.calendar_a a:hover{
  text-decoration: none !important;
  background-color: #d9ecff;
  color: #000000;
  font-weight: bold;
}
/* A 태그가 클릭된적이 있는 경우의 모양 */
.calendar_a a:visited {
  text-decoration: none !important;
  color: #000000;
}
.calendar_td {
  border: 0px;
  margin-top: 0px;
  padding-top: 0px; 
  border-width: 1.5px;    /* 태그의 위쪽선 두께*/ 
  border-style:solid;    /* 실선*/
  border-color:#d9d9d9;  /* 선의 색깔 */
  color: #3e3e3e;        /* 제목*/ 
  height: 70px;
  font-size: 15px;
}
</style>

<script type="text/javascript">
  function read(reservationno) {
    
    var url = '../reservation/read.do?reservationno=' + reservationno; 
    var win = window.open(url, '예약 보기', 'width=500px, height=500px');
    
    var x = (screen.width - 500) / 2; // 1000 - 500 = 500 / 2 = 250
    var y = (screen.height - 350) / 2; // 800 - 350 = 450 / 2 = 225
    
    win.moveTo(x, y); //  화면을 가운데로 이동
  }
  
  function reservation(a, b, c) {
    b = b+1;
    if(a == "sun") {
      alert("일요일은 병원 휴일입니다.");
    }
    if(b.toString().length < 2) {
      b = "0" + b;
    }
    if(c.toString().length < 2) {
      c = "0" + c;
    }
    var resdate = a.toString() + "-" + b.toString() + "-" + c.toString();
    var reservation_check = confirm( resdate + "의 날짜로 예약하시겠습니까?");
    if(reservation_check) {
    	location.href="../reservation/create.do?memberno=${param.memberno}&resdate=" + resdate;
    }
  }
</script>
</head>
<body>
<%
Calendar cal = Calendar.getInstance();
int memberno = Integer.parseInt(request.getParameter("memberno"));
int year = 0;
int month = 0;

if (request.getParameter("y") != null){
  year = Integer.parseInt(request.getParameter("y"));  
}else{
  year = cal.get(Calendar.YEAR); // 현재 년도 추출
}

//1월 0부터 시작
if (request.getParameter("m") != null){
  month = Integer.parseInt(request.getParameter("m")) - 1;
}else{
  month = cal.get(Calendar.MONTH); // 현재 월 추출 0 ~  
}
      
// 시작요일 확인
// - Calendar MONTH는 0-11까지임
cal.set(year, month, 1);
int bgnWeek = cal.get(Calendar.DAY_OF_WEEK);

// 다음/이전월 계산
// - MONTH 계산시 표기월로 계산하기 때문에 +1을 한 상태에서 계산함
int prevYear = year;
int prevMonth = (month + 1) - 1;
int nextYear = year;
int nextMonth = (month  + 1) + 1;

// 1월인 경우 이전년 12월로 지정
if (prevMonth < 1) {
  prevYear--;
  prevMonth = 12;
}

// 12월인 경우 다음년 1월로 지정
if (nextMonth > 12) {
  nextYear++;
  nextMonth = 1;
}

String year_list = Integer.toString(cal.get(Calendar.YEAR));
String month_list = Integer.toString(cal.get(Calendar.MONTH) + 1);
if (month_list.length() < 2) {
  month_list = "0" + month_list;
}
String today = year_list + "-" + month_list;
%>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<div style='width: 80%; margin:0px auto; text-align: center; margin-top: 5%; margin-bottom: 10%'>

<button type="button" id="create_reservation" class="btn btn-primary" style="float: right;" onclick="location.href='../reservation/my_list.do?memberno=<%=memberno%>&date=<%=today%>'">예약 리스트</button>
<%-- <button type="button" id="create_reservation" class="btn btn-primary" style="float: right;" onclick="location.href='../reservation/create.do?memberno=<%=memberno%>'">예약&일정 등록하기</button> --%>

<table id="calendar_wrap">
  <tr class="month">
    <td>
      <a href="./calendar_t.jsp?y=<%=prevYear%>&m=<%=prevMonth%>&memberno=<%=memberno%>">&#10094;</a> 
      <%=year%>년 <%=month+1%>월 
      <a href="./calendar_t.jsp?y=<%=nextYear%>&m=<%=nextMonth%>&memberno=<%=memberno%>">&#10095;</a>
    </td>
  </tr>	
  <tr>
	  <td>
		  <table class='calendar'>
			  <tr class="weekdays" style="background-color:#c68cff;">
					<td width='14%'>일</td>
					<td width='14%'>월</td>
					<td width='14%'>화</td>
					<td width='14%'>수</td>
					<td width='14%'>목</td>
					<td width='14%'>금</td>
					<td width='16%'>토</td>
				</tr>
				<tr>
				<%
        // 시작요일까지 이동
        for (int i=1; i<bgnWeek; i++){
          out.println("<td class='calendar_td' style='border-left:none;'>&nbsp;</td>");
        }

        // 첫날~마지막날까지 처리
        // - 날짜를 하루씩 이동하여 월이 바뀔때까지 그린다
        String str="";
        ArrayList list = null;
        CalendarDAO dao = new CalendarDAO();
        StringBuffer sb = null;
    
        while (cal.get(Calendar.MONTH) == month) {
          if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            out.println("<td class='calendar_td' valign='top' style='color:#9f9f9f; text-align: right; border-right:none;'><a href='javascript:reservation(" + cal.get(Calendar.YEAR) + "," + cal.get(Calendar.MONTH) + "," + cal.get(Calendar.DATE) +")'>" + cal.get(Calendar.DATE) + "</a>");
          }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            out.println("<td class='calendar_td' valign='top' style='color:#9f9f9f; text-align: right; border-left:none;'><a href='javascript:reservation(\"sun\")'>" + cal.get(Calendar.DATE) + "</a>");
          }else{
            out.println("<td class='calendar_td' valign='top' style='text-align: right;'><a href='javascript:reservation(" + cal.get(Calendar.YEAR) + "," + cal.get(Calendar.MONTH) + "," + cal.get(Calendar.DATE) +")'>" + cal.get(Calendar.DATE) + "</a>");
          }
        
          // ------------------------------------------------------------------------
          // 2010-01-01에 해당하는 일정만 DBMS에서 가져옵니다.
          // ------------------------------------------------------------------------
          // str = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DATE);
          str = Tool.getDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
          list = dao.listLabel(str, memberno); 
          
      
          if (list != null){ 
            sb = new StringBuffer();
            for(int i=0; i<list.size(); i++){
              CalendarVO calendarVO = (CalendarVO)list.get(i);
              sb.append("<img src='"+request.getContextPath()+"/calendar/images/bu5.gif'>");
              sb.append("<a href='javascript:read("+calendarVO.getReservationno()+")'>"+calendarVO.getName()+ " - " + calendarVO.getRestype() +"</a><br>");            
            }
        
          }
          out.println("<br><div class='calendar_a' style='color:#00AA00; text-align: left;'>" + sb.toString() + "</div></td>");
          // ------------------------------------------------------------------------
      
          // 한달의 마지막 날이 아니면서 토요일인 경우 다음줄로 생성
          // System.out.println(cal.getActualMaximum ( Calendar.DAY_OF_MONTH ));
          if ((cal.getActualMaximum ( Calendar.DAY_OF_MONTH ) != cal.get(Calendar.DATE))) {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){ // 토요일인 경우
              out.println("</tr><tr>");
            }
          }

          // 날짜 증가시키기
          cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
          // 끝날부터 토요일까지 빈칸으로 처리
      
        }
        //System.out.println("cal.get(Calendar.MONTH): " + cal.get(Calendar.MONTH));
        //System.out.println("cal.get(Calendar.DATE): " + cal.get(Calendar.DATE));
        //System.out.println("cal.get(Calendar.DATE): " + cal.get(Calendar.DATE));
    
        if (cal.get(Calendar.DATE) == 1 && (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
          // 한달의 마지막 날짜 토요일이면 아무일도 안함
        }else{
          for (int i=cal.get(Calendar.DAY_OF_WEEK); i<=7; i++) out.println("<td class='calendar_td' style='border-right:none;'>&nbsp;</td>");
        }

%>
		  	</tr>
	  	</table>
  	</td>
	</tr>
</table>
</DIV>

<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
</body>
</html>





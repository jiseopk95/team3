<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Calendar" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>마이 예약리스트</title>

<%
/* 예약날짜에 오늘날짜가 기본으로 떠라 */
Calendar cal = Calendar.getInstance();
String year = Integer.toString(cal.get(Calendar.YEAR));
int this_year = cal.get(Calendar.YEAR);
int last_year = cal.get(Calendar.YEAR) - 1;
String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
if (month.length() < 2) {
  month = "0" + month;
}
String today = year + "-" + month;
%>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
/* 출력되는 A 태그 기본 모양 */
a:link{ 
  text-decoration: none !important; /* 밑줄 삭제 */
  color: #000000;
}
/* A 태그에 마우스가 위치했을 때 */
a:hover{
  text-decoration: none !important;
  color: #0080ff;
  color: #000000;
  font-weight: bold;
}
/* A 태그가 클릭된적이 있는 경우의 모양 */
a:visited {
  text-decoration: none !important;
  color: #000000;
}
#month_table{
  margin-top: 30px;
  margin-bottom: 70px;
  width: 50%; 
  
}
</style>
<script type="text/javascript">
  $(function() {
    /* 1번 사용자(매니저)가 아니면 예약 버튼 출력 */
  	  if(${memberno} == 1) { 
  	    $('#calendarButton').append('<button type="button" id="create_reservation" class="btn btn-primary" style="float: right;" onclick="reservation(${memberno});">일정 등록하기</button>');
  	    $('#calendarButton').append('<button type="button" id="calendar" class="btn btn-primary" style="float: right;" onclick="location.href=\'../calendar/calendar_t.jsp?memberno=${memberno }\'">관리자 캘린더 보기</button>');
  	    
  	  } else { 
  	    $('#calendarButton').append('<button type="button" id="create_reservation" class="btn btn-primary" style="float: right;" onclick="reservation(${memberno});">예약&일정 등록하기</button>');
  	    $('#calendarButton').append('<button type="button" id="calendar" class="btn btn-primary" style="float: right; margin-right: 10px;" onclick="location.href=\'../calendar/calendar.jsp?memberno=${memberno }\'">캘린더 보기</button>');
  	  }
    
    // 월별로 예약사항 보기
    	
    	for(var i = 1; i <= 12; i++) {
    	  var month = i.toString();
    	  if(month.length < 2) {
    	    month = "0"+month;
    	  }
    	  $('#month').append("<td><a id='paging' href='./my_list.do?memberno=${param.memberno }&date=<%=this_year%>-" + month + "'>" + i + "월</a></td>");
    	}
  });
  
  
  
  function update(reservationno, memberno, resdate) {
    
    var url = './update.do?reservationno=' + reservationno + '&memberno=' + memberno + '&resdate=' + resdate; 
    var win = window.open(url, '예약 수정', 'width=1000px, height=500px');
    
    var x = (screen.width - 500) / 2; // 1000 - 500 = 500 / 2 = 250
    var y = (screen.height - 350) / 2; // 800 - 350 = 450 / 2 = 225
    
    win.moveTo(x, y); //  화면을 가운데로 이동
  }
  
  function deleteOne(reservationno) {
    $.ajax({
      url: "./delete.do",
      type: "get",
      cache: false,
      async: true,
      dataType: "json",
      data: "reservationno=" + reservationno,
      success: function(rdata) {
      var delete_check = confirm(rdata.name + "의 예약을 삭제하시겠습니까?\n"
          																	+"-----------------------------------------------------"
                                  					+ "\n예약글 : " + rdata.title
                                  					+ "\n예약날짜 : " + rdata.resdate
                                  					+ "\n예약시간 : " + rdata.restime);
      
      if(delete_check) {
        delete_proc(rdata.reservationno);
      } else {
        alert("사용자가 삭제를 취소했습니다.");
        location.reload();
      }
    }
    });
  }
  
  function delete_proc(reservationno) {
    $.ajax({
      url: "./delete.do",
      type: "post",
      cache: false,
      async: true,
      dataType: "json",
      data: "reservationno=" + reservationno,
      success: function(rdata) {
        location.reload();
        alert(rdata.msgs);
      }
    });
  }
  
  function reservation(memberno) {
    alert("예약을 위해 캘린더로 이동합니다.");
    if(memberno == 1) {
      location.href="../calendar/calendar_t.jsp?memberno=" + memberno;
    } else {
      location.href="../calendar/calendar.jsp?memberno=" + memberno;
    }
    
  }
</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' >
<DIV class='content'  style='width: 100%; margin:0px auto; text-align: center; margin-top: 5%; margin-bottom: 20%'>
<div class="title_line">마이 리스트</div>
  <div id="calendarButton">
  </div>
  <br>
    <table id="month_table">
      <tr id="month">
      </tr>
    </table>
  <TABLE class='table'>
    <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 25%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
  </colgroup>
  <thead>  
  <TR>
    <TH style='text-align: center ;'>번호</TH>
    <TH style='text-align: center ;'>타입</TH>
    <TH style='text-align: center ;'>예약동물</TH>
    <TH style='text-align: center ;'>예약</TH>
    <TH style='text-align: center ;'>예약일</TH>
    <TH style='text-align: center ;'>예약시간</TH>
    <TH style='text-align: center ;'>기타</TH>
  </TR>
  </thead>
    <tbody id='tbody_panel' data-nowPage='0' data-endPage='0'>
      <c:forEach var="reservationVO" items="${list }">
        <tr>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.reservationno }</td>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.restype }</td>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.name }</td>
          <td style='vertical-align: middle; text-align: center ;'><a href="./read.do?reservationno=${reservationVO.reservationno }">${reservationVO.title }</a></td>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.resdate }</td>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.restime }</td>
          <td style='vertical-align: middle; text-align: center ;'>
            <a href="javascript:update(${reservationVO.reservationno }, ${reservationVO.memberno }, ${reservationVO.resdate });"><img alt="수정이미지" src="./images/update.png" title="수정" style="width:20px; height:20px;"></a>
            <a href="javascript:deleteOne(${reservationVO.reservationno } )"><img alt="삭제이미지" src="./images/delete.png" title="삭제" style="width:20px; height:20px;"></a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </TABLE>
</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>




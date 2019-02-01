<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>예약페이지</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>
</head>
<body>
<%
Calendar cal = Calendar.getInstance();
String year = Integer.toString(cal.get(Calendar.YEAR));
String month = Integer.toString(cal.get(Calendar.MONTH + 1));
String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
if (month.length() < 2) {
  month = "0" + month;
}
if (day.length() < 2) {
  day = "0" + day;
}
String today = year + "-" + month + "-" + day;
System.out.println(today);
%>
<DIV class='container'>
<DIV class='content'>
<h1>스케줄 작성</h1>
  <FORM name='frm' method='POST' action='./create_schedule.do'
               enctype="multipart/form-data" class="form-horizontal">
               
      <input type='hidden' name='memberno' id='memberno' value='${memberno }'>
    
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='수기 스케줄 제목' placeholder="저장할 내용의 제목을 입력해주세요" required="required" style='width: 80%;'>
        </div>
      </div>   
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">레이블</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='label' id='label' value='수기 스케줄 레이블'  placeholder="달력에 표시될 제목을 입력해주세요" required="required" style='width: 80%;'>
        </div>
      </div>
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">날짜</label>
        <div class="col-md-11">
          <input type='date' class="form-control input-lg" name='bday' id='resdate' value="<%=today %>" required="required">
        </div>
      </div>
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">시간</label>
        <div class="col-md-11">
          <input type='time' step=900 class="form-control input-lg" name='restime' id='restime' required="required">
        </div>
      </div>
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='content' id='content'  placeholder="스케줄 내용을 입력해주세요" rows='10'>스케줄 내용</textarea>
        </div>
      </div>
      <DIV style='text-align: right;'>
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='#'">취소[개발중]</button>
      </DIV>
    </FORM>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html>




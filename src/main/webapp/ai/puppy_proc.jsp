<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
  *{
    font-size: 26px;
  
  }
</style>
        
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' style="width:80%;" >
<DIV class='content' style='margin-top: 5%; margin-bottom:5%'>
 <aside style="text-align:center; font-weight: bold; font-size:35px; ">반려견 수명 예측 시스템</aside><br>
 <br>
<DIV style='font-size: 30px;margin-bottom:10px;'>수명 예측 결과입니다.</DIV>

검사 데이터: <%=request.getParameter("data") %><br><br>
진단 결과: <span style="font-size:20px;font-weight:bold;color:red;"><%=request.getParameter("res") %>년</span> 생존할것으로 예측됩니다.<br><br>
<A href='./puppy_form.jsp'><span style="font-weight:bold;color:blue;">평가 계속</span></A>


</DIV> <!-- content END -->
</DIV> <!-- container END -->

</body>
</html>

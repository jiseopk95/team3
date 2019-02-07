<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Pet Doctor</title> <!-- 주소창 타이틀 -->

<link href="./css/style.css" rel='Stylesheet' type='text/css'>

<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

<!-- jQuery -->
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<link href="fotorama.css" rel="stylesheet">
<script src="fotorama.js"></script>
</head>

<body>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<BR>
<!-- Header ( index 파랑색 칸 부분 ) -->
<div style='background-color:  #FFFFFF;'>
  <div class="container" style='margin:0px auto;' >
    <img style='margin-top:5%; margin-left:41%; width: 17%; height: 17%; text-align: center; ' src="./css/images/pet3.png" >  <!-- 관련 이미지 넣거나 넣지 않음 -->
    <h1 style='margin-top:2%; color:#000000; text-align: center; font-size: 35px;' >Animal Total System</h1>
    <h2 style='margin-top:1%; color:#505050; text-align: center; font-size: 24px; '>Hospital & Beauty</h2>
    <br> <br> <br> 
  </div>
</div>
   
<!-- 내용 넣는 공간 여기부터 -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<div style="margin: 0px auto; float:center; text-align: center; padding-left: 33%;">
  <div class="fotorama" style="margin: 0px auto; float:center; text-align: center;">
        <a href="./menu/images/pet1.jpg"><img src="./menu/images/pet1.jpg" ></a>
        <a href="./menu/images/food2.jpg"><img src="./menu/images/food2_t.jpg" ></a>
        <a href="./menu/images/food4.jpg"><img src="./menu/images/food4_t.jpg" ></a>
        <a href="./menu/images/food5.jpg"><img src="./menu/images/food5_t.jpg" ></a>
  </div>
</div>

<br><br><br> <br> <br>
 
<div style="width: 85%;height:350px;margin:0px auto; text-align: center;">
  <div style=' float: left; width:65%; '>
    <div style='width:50%; height:300px; padding:30px !important; float:left; '> 
      <div style='width: 75%; border-bottom: dotted 1px #777777; float: right; margin-top: 2%;'></div>
      <c:import url="/animalstory/index_animal.do" /> 
    </div>
    <div style='width:47%;  padding:30px !important; float:right; '>
      <div style='width: 80%; border-bottom: dotted 1px #777777; float: right; margin-top: 2.5%;'></div>
      <c:import url="/review/index_list.do" />
    </div>
  </div>

  <div style=' float: right;width:25%; margin-top: 2%; '>
    <div style='font-weight: bold;text-align: CENTER; font-size: 20px; color: #000000;'>
       CUSTOMER SERVICE <br><br>
      <a href=' ${pageContext.request.contextPath}/review/list.do'><img  style='width:35%;'  src="./css/images/qna.png"/></a>
      <img style='width:35%; margin-left: 3%;' src="./css/images/call.png"/>
    </div>
  </div>
</div>

 <br>

<!-- 여기까지 -->
<div style="float: center;">
<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
</div>
   </body>
</html>

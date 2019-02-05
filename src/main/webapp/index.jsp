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
<div style='width: 80%; margin:0px auto; text-align: center; margin-top: 5%; margin-bottom: 10%'> <!-- top, buttom 사이에 10%씩 위아래로 공백 주기 -->

<div class="fotorama" style='margin-left: 25%;' >
      <a href="./menu/images/pet1.jpg"><img src="./menu/images/pet1.jpg" ></a>
      <a href="./menu/images/food2.jpg"><img src="./menu/images/food2_t.jpg" ></a>
      <a href="./menu/images/food4.jpg"><img src="./menu/images/food4_t.jpg" ></a>
      <a href="./menu/images/food5.jpg"><img src="./menu/images/food5_t.jpg" ></a>
</div>

<br><br><br> <br> <br>
 
<%-- <div style=' float: left; font-size: 9em; width:60%; '>
<div style='border:1px solid #BDBDBD !important; width:50%; height:300px; padding:30px !important; float:left; /* margin-bottom: 10%; */ '> <!-- top, buttom 사이에 10%씩 위아래로 공백 주기 -->
<c:import url="/animalstory/index_animal.do" /> 
</div>
<div style='border:1px solid #BDBDBD !important; width:47%;  padding:30px !important; float:right; /* margin-bottom: 10%; */ '> <!-- top, buttom 사이에 10%씩 위아래로 공백 주기 -->
<c:import url="/review/index_list.do" />
</div>
</div> --%>

<div style=' float: left; font-size: 9em; width:60%; '>
<div style='width:50%; height:300px; padding:30px !important; float:left; /* margin-bottom: 10%; */ '> <!-- top, buttom 사이에 10%씩 위아래로 공백 주기 -->
<div style='width: 66%; border-bottom: dotted 1px #777777; float: right; margin-top: 3.5%;'></div>
<c:import url="/animalstory/index_animal.do" /> 
</div>
<div style='width:47%;  padding:30px !important; float:right; /* margin-bottom: 10%; */ '> <!-- top, buttom 사이에 10%씩 위아래로 공백 주기 -->
<div style='width: 74%; border-bottom: dotted 1px #777777; float: right; margin-top: 3.5%;'></div>
<c:import url="/review/index_list.do" />
</div>
</div>


<div style='font-weight: bold; text-align: right;font-size: 25px; margin-right: 7%; color: #000000;'>
CUSTOMER SERVICE <br>
<!-- <div style='width: 26%; border-bottom: dotted 1px #777777; float: right; margin-top: 1.3%;'></div> -->
</div>

<div style=' padding:30px !important; width:30%; float:right;/* margin-bottom: 10%; */ '> <!-- top, buttom 사이에 10%씩 위아래로 공백 주기 -->
 <a href=' ${pageContext.request.contextPath}/review/list.do'><img  style='width: 45%; float:left;'  src="./css/images/qna.png"/></a>
 <img style='width: 45%; float:right; margin-left: 5%;' src="./css/images/call.png"/>
</div>


<br><br><br><br><br>

</div>

 <br>

<!-- 여기까지 -->
<div style="float: center;">
<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
</div>
   </body>
</html>

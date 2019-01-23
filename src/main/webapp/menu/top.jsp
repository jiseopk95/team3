<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>PET 24</title> <!-- 주소창 타이틀 -->
    <!-- <link href="./css/style.css" rel='Stylesheet' type='text/css'> -->
    <!-- Bootstrap core CSS -->
    <link href=" ${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href=" ${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- Plugin CSS -->
    <link href=" ${pageContext.request.contextPath}/resources/vendor/magnific-popup/magnific-popup.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href=" ${pageContext.request.contextPath}/resources/css/freelancer.min.css" rel="stylesheet">
   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

   <!-- Bootstrap -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    
</head>

<body id="page-top">
<div class="small-top" style="height: 36px;background-color:#2455c5 !important;">
  <div class="top-container">
    <ul>
      <li class="top-li">
        <a href=' ${pageContext.request.contextPath}/member/login.do'>Login</a>
      </li>
      <li class="top-li">
        <a href=' ${pageContext.request.contextPath}/member/create.do'>Join</a>
      </li>
    </ul>
  </div>
</div>
<!-- Navigation --> <!-- 메뉴바 ( 사용하는 링크로 채우기 ) -->
   <nav class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase shadow"   id="mainNav" style="background-color: white !important; color: black;">
      <div class="container" style='width: 100%;'>
        <img alt="동물병원 아이콘" src="${pageContext.request.contextPath}/menu/images/ahs.png" style="width: 3%; height: 3%; margin-right: 10px;"><a class="navbar-brand js-scroll-trigger" href=' ${pageContext.request.contextPath}/index.jsp'>PET 24</a> <!-- 상단 왼쪽에 작게 나오는 이름 -->
        <button class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fas fa-bars" ></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href=' ${pageContext.request.contextPath}/calendar/calendar.jsp?memberno=2'>예약&캘린더</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href=' ${pageContext.request.contextPath}/calendar/calendar.jsp?memberno=2'>의료</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href=' ${pageContext.request.contextPath}/calendar/calendar.jsp?memberno=2'>미용</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href=' ${pageContext.request.contextPath}/calendar/calendar.jsp?memberno=2'>카테고리</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href=' ${pageContext.request.contextPath}/calendar/calendar.jsp?memberno=2'>설문조사</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
   <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

    <!-- Custom scripts for this template -->
    <script src="js/freelancer.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

   </body>
</html>
  
  
  
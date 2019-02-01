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

   <!-- Bootstrap -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
 
  </head>
  
<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->

<!-- Header ( index 파랑색 칸 부분 ) -->
     <div class="masthead text-black text-center" style='width: 100%; background-color: #eeece1a1; height:450px;'>
      <div class="container" style='margin:0px auto;' >
      <img class="img-fluid mb-5 d-block mx-auto" style='margin-top:5%; width: 20%; height: 20%;' src="./resources/img/dog1.png" >  <!-- 관련 이미지 넣거나 넣지 않음 -->
        <h1 class="text-uppercase mb-0" style='margin-top:5%; font-weight: bold; color:#2c3e50;' >Animal Total System</h1>
        <h2 class="font-weight-light mb-0 "  style='color:#454545;'>Hospital & Beauty</h2>
      </div>
    </div>
   
<!-- 내용 넣는 공간 여기부터 -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<div style='width: 80%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'> <!-- top, buttom 사이에 10%씩 위아래로 공백 주기 -->
<c:import url="/animalstory/index_animal.do" /> 
<c:import url="./review/index_list.do" />
</div>
<!-- 여기까지 -->

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
    
<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
   </body>
</html>
  
  
  
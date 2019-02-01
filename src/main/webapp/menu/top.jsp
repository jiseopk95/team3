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
    
    <script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/jquery.cookie.js"></script>
    
 </head>

<body id="page-top">

   <nav style="background-color:#2c3e50; color: #FFFFFF; ">
   <div style='margin-left: 15%; '>
   <br>
   <a style="color: #FFFFFF; font-size: 50px;font-weight:bold; margin-right:2%;" href= '${pageContext.request.contextPath}/index.jsp'>Pet 24</a> <!-- 상단 왼쪽에 작게 나오는 이름 -->
        <c:choose>
          <c:when test="${sessionScope.id == null}">
            <li class="top-li">
               <a href=' ${pageContext.request.contextPath}/member/login.do'>Login</a>
               <a href=' ${pageContext.request.contextPath}/member/create.do'>Join</a>
           </li>
          </c:when>
         <c:otherwise>
      <li class="top-li">
        <a href=' ${pageContext.request.contextPath}/member/logout.do'>Logout</a>
      </li>
      </c:otherwise>
      </c:choose>
      <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; "href='${pageContext.request.contextPath}/index.jsp'>HOME  &nbsp</a> 
        
         <!-- 회원 로그인시 -->
       
         <c:choose>
             <c:when test="${sessionScope.memberno != null}">
               <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; " href=' ${pageContext.request.contextPath}/member/logout.do'>예약&캘린더 &nbsp</a>
           
         
              <div class="dropdown" >
                 <a class=" dropdown-toggle"  style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%;"  data-toggle="dropdown" href="${pageContext.request.contextPath}/question/list.do?categoryno=2">커뮤니티&nbsp</a> 
               <div class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
                   <LI><a class="dropdown-item" href="${pageContext.request.contextPath}/question/list.do?categoryno=2">진료QnA</a></LI>
                   <LI><a class="dropdown-item" href="${pageContext.request.contextPath}/question/list.do?categoryno=4">미용QnA</a></LI>
                   <LI><a class="dropdown-item" href="${pageContext.request.contextPath}/review/list.do?categoryno=1">진료후기</a></LI>
                  <LI> <a class="dropdown-item" href="${pageContext.request.contextPath}/review/list.do?categoryno=3">미용후기</a></LI>
                   <LI><a class="dropdown-item" href="#">설문조사 항목</a>
               </div>
              </div> 
              
              <div class="dropdown"> 
              <a class="dropdown-toggle"  style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%;"data-toggle="dropdown"  href="#">소식공간</a> 
               <div class="dropdown-menu" >
                  <LI> <a class="dropdown-item" href="#">미용스타일</a></LI>
                  <LI>  <a class="dropdown-item" href="#">이벤트상품</a></LI>
                    
                    <LI><a class="dropdown-item" href="#">이벤트관리</a></LI>
                  <LI> <a class="dropdown-item" href="#">애니멀스토리</a></LI>
               </div>
          </div>  
             
             <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; " href=' ${pageContext.request.contextPath}/pet/create.do'>동물등록 &nbsp</a>
             
             <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; " href=' ${pageContext.request.contextPath}/pet/mypage_select.jsp'>MYPAGE &nbsp</a>
             </c:when>
           </c:choose>



           <!--회원 로그인 끝  -->
           
           
           
             <!-- 관리자 로그인 -->
         <c:choose>
        <c:when test="${sessionScope.managerno != null}">
          <div class="dropdown">
              <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; " data-toggle="dropdown" aria-expanded=”true” href="#">진료</a> 
               <div class="dropdown-menu" >
                   <a class="dropdown-item" href="#">예약&캘린더</a>
                   <a class="dropdown-item" href="#">의료차트</a>
               </div>
           </li> 
          </div>
            
          <div class="dropdown">
               <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; "  data-toggle="dropdown" >목록관리</a> 
               <div class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
                  <LI> <a class="dropdown-item" href="#">회원목록</a></LI>
                  <LI> <a class="dropdown-item" href="#">직원목록</a></LI>
                   <LI><a class="dropdown-item" href="#">동물목록</a></LI>
                   <LI><a class="dropdown-item" href="#">회원로그인기록</a></LI>
                  <LI> <a class="dropdown-item" href="#">관리자로그인기록</a></LI>
               </div>
          </div>
            
          <div class="dropdown">
                <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; "  data-toggle="dropdown" href="${pageContext.request.contextPath}/question/list.do?categoryno=2">커뮤니티</a> 
               <div class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
                  <LI> <a class="dropdown-item" href="${pageContext.request.contextPath}/question/list.do?categoryno=2">진료QnA</a></LI>
                  <LI> <a class="dropdown-item" href="${pageContext.request.contextPath}/question/list.do?categoryno=4">미용QnA</a></LI>
                  <LI> <a class="dropdown-item" href="${pageContext.request.contextPath}/review/list.do?categoryno=1">진료후기</a></LI>
                  <LI> <a class="dropdown-item" href="${pageContext.request.contextPath}/review/list.do?categoryno=3">미용후기</a></LI>
                 <LI>  <a class="dropdown-item" href="#">설문조사 항목</a>
               </div>
          </div>
          
          <div class="dropdown">
              <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; "  data-toggle="dropdown" aria-expanded=”true” href="#">소식공간</a> 
               <div class="dropdown-menu" >
                  <LI> <a class="dropdown-item" href="#">미용스타일</a></LI>
                 <LI>  <a class="dropdown-item" href="#">이벤트상품</a></LI>
                  <LI> <a class="dropdown-item" href="#">이벤트관리</a></LI>
                  <LI> <a class="dropdown-item" href="#">애니멀스토리</a></LI>
               </div>
          </div>
          
          <div class="dropdown"> 
              <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; "  data-toggle="dropdown" aria-expanded=”true” href="#">카테고리</a> 
               <div class="dropdown-menu" >
                   <LI><a class="dropdown-item" href="${pageContext.request.contextPath}/categrp/list.do">카테고리그룹</a></LI>
                   <LI><a class="dropdown-item" href="${pageContext.request.contextPath}/category/list.do">카테고리목록</a></LI>
               </div>
          </div>
          
              <!-- <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#about">Join</a> -->  <!-- 아래처럼 링크 넣으면 됌!  -->
              <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; "  href='#'>관리자페이지</a>

            
              <!-- <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#about">Join</a> -->  <!-- 아래처럼 링크 넣으면 됌!  -->
              <a style="color: #FFFFFF; font-size: 20px; font-weight:bold; margin-top: 3%; "  href='#'>로그아웃</a>
          </c:when>
       </c:choose>       
          <!-- 관리자 로그인 끝 -->
           
      <br>
      <br>
      </div>
   </nav>
    
   </body>
</html>









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

<script type="text/javascript">
/* 드롭다운 메뉴 나오게하기 */
    $(document).ready(function() {
    $(".dropdown-toggle").dropdown();
}); 
    
</script>
 </head>
 
<!-- 여기 첫번째 탑 부분에는 세션에 따라 로그인,회원가입,로그아웃 뜨게 해주고 
로그인했을경우에는 로그인한 아이디도 뜨게해줘
그리고 회원 로그인이면 마이페이지 뜨게 해주고
직원 로그인이면 직원페이지 뜨게 해주랑 색상은 여기는  color: gray; 회색으로 -->

<body id="page-top">
<div  style="text-align: right; width:100%; height: 30px; background-color: #FFFFFF; color: gray; width:90%; margin-top: 1.1%; margin-bottom: 1.1%;">
      <c:choose>
          <c:when test="${sessionScope.id == null}">
            <li class="top-li">
               <a style="color: gray !important;"href=' ${pageContext.request.contextPath}/member/login.do'>Login</a>
               <a style="color: gray !important;"href=' ${pageContext.request.contextPath}/member/create.do'>Join</a>
           </li>
          </c:when>
          <c:when test="${sessionScope.memberno != null}">
            <li class="top-li">
               <a style="color: gray !important;">[ID: ${sessionScope.id }]</a>
               <a style="color: gray !important;" href=' ${pageContext.request.contextPath}/pet/mypage_select.jsp'>MYPAGE</a>
               <a  style="color: gray !important; " href=' ${pageContext.request.contextPath}/pet/create.do'>반려동물 등록</a>
               <a style="color: gray !important;" href=' ${pageContext.request.contextPath}/member/logout.do'>Logout</a>
            </li>
          </c:when>
          <c:when test="${sessionScope.managerno != null}">
            <li class="top-li">
               <a style="color: gray !important;">[ID: ${sessionScope.id }]</a>
               <a style="color: gray !important;" href=' ${pageContext.request.contextPath}/manager/read.do?managerno=${managerno}'>관리자페이지</a>
               <a style="color: gray !important;" href=' ${pageContext.request.contextPath}/manager/logout.do'>Logout</a>
            </li>
          </c:when>
      </c:choose>
</div>
<!-- 나머지 카테고리나 메뉴들은 이 밑으로 정리해줘 색상은 #626262; 으로 -->
<div style='text-align:center; margin-top: 2%;  margin-bottom: 2%;'>
<a style="color: #8FD3E8; font-size: 60px;font-weight:bold; margin:0px auto; float: center; " href= '${pageContext.request.contextPath}/index.jsp'>Pet 24</a> <!-- 상단 왼쪽에 작게 나오는 이름 --><br>
 
 </div>
<!--  <div class='menu_line' style="width: 90%; margin: 0px auto; border-style: 1px solid; "></div> -->
   <nav style="background-color: #F6F3EE; width: 100%; margin: 0px auto; height: 50px; text-align: center;margin-bottom:-25px;">
   <div style='width: 70%; margin: 0px auto; '>

<!-- 로그인 전 -->
    <c:choose>
       <c:when test="${sessionScope.id == null}">
          <nav class="navbar">
            <div class="container-fluid center-block" style="width:350px; float:center; margin:0 auto; text-align:center;">
                <ul class="nav navbar-nav" style="display: table; margin: auto; padding:0; text-align:center; ">
                   <li class="active"><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/index.jsp">HOME</a></li>
                   
                   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="color: #626262; font-size: 17px; font-weight:bold;" type="button"  href="#" aria-haspopup="true" aria-expanded="false">커뮤니티 <span class="caret"></span></a>
                       <ul class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/question/list.do?categoryno=2">진료QnA</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/question/list.do?categoryno=4">미용QnA</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/review/list.do?categoryno=1">진료후기</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/review/list.do?categoryno=3">미용후기</a></li>
                       </ul>
                   </li>
                   
                   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="color: #626262; font-size: 17px; font-weight:bold;  href="#">소식공간 <span class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/beauty/list_all_beauty.do">미용스타일</a></li>
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/animalstory/list.do?content=&nowPage=">애니멀스토리</a></li>
                       </ul>
                   </li>
                   
                  </ul>                             
                </div>
             </nav>
 
        </c:when>
      </c:choose>
 <!-- 회원 로그인 후  --> 
    <c:choose>
       <c:when test="${sessionScope.memberno != null}">
          <nav class="navbar">
            <div class="container-fluid center-block" style="width:500px; float:center; margin:0 auto; text-align:center;">
                <ul class="nav navbar-nav" style="display: table; margin: auto; padding:0; text-align:center; ">
                   <li class="active"><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/index.jsp">HOME</a></li>
                   <li>
                   <c:choose>
                   <c:when test="${sessionScope.memberno == 1}">
                    <a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/calendar/calendar_t.jsp?memberno=${memberno}">예약&캘린더</a>
                   </c:when>
                   <c:when test="${sessionScope.memberno != 1}">
                    <a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/calendar/calendar.jsp?memberno=${memberno}">예약&캘린더</a>
                   </c:when>
                   </c:choose>
                   </li>
                   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="color: #626262; font-size: 17px; font-weight:bold;" type="button"  href="#" aria-haspopup="true" aria-expanded="false">커뮤니티 <span class="caret"></span></a>
                       <ul class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/question/list.do?categoryno=2">진료QnA</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/question/list.do?categoryno=4">미용QnA</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/review/list.do?categoryno=1">진료후기</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/review/list.do?categoryno=3">미용후기</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/survey/list_m.do?memberno=${memberno}">설문조사 참여</a>
                       </ul>
                   </li>
                  
                   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="color: #626262; font-size: 17px; font-weight:bold;"  href="#">소식공간 <span class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/beauty/search_paging.do?categoryno=1">미용스타일</a></li>
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/event/search_paging_member.do?memberno=${memberno}">이벤트응모</a></li>
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/animalstory/list.do?content=&nowPage=">애니멀스토리</a></li>
                       </ul>
                   </li>
                   
                 
                </ul>
           </div>
         
         </nav>
      </c:when>
    </c:choose>
    
<!-- 관리자 로그인 후 -->
    <c:choose>
       <c:when test="${sessionScope.managerno != null}">
          <nav class="navbar">
            <div class="container-fluid center-block" style="width:850px; float:center; margin:0 auto; text-align:center;">   
                <ul class="nav navbar-nav" style="display: table; margin: auto; padding:0; text-align:center; ">
                   <li class="active"><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/index.jsp">HOME</a></li>
                   
                   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="color: #626262; font-size: 17px; font-weight:bold;" type="button"  href="#" >진료 관리<span class="caret"></span></a>
                       <ul class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
                            
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/chart/list.do?managerno=${managerno}">의료차트</a></li>
                       </ul>                                    
                   </li>
                   
                   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="color: #626262; font-size: 17px; font-weight:bold;" type="button"  href="#">목록 관리 <span class="caret"></span></a>
                       <ul class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/member/list_search.do">회원목록</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/manager/list_search.do">직원목록</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/pet/list_search.do">동물목록</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/member_login/list.do">회원로그인기록</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/manager_login/list.do">관리자로그인기록</a>
                       </ul>
                   </li>
                   
                   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="color: #626262; font-size: 17px; font-weight:bold;" type="button"  href="#">커뮤니티 관리 <span class="caret"></span></a>
                       <ul class="dropdown-menu"  aria-labelledby="dropdownMenuButton">
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/question/list.do?categoryno=2">진료QnA</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/question/list.do?categoryno=4">미용QnA</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/review/list.do?categoryno=1">진료후기</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/review/list.do?categoryno=3">미용후기</a></li>
                            <li><a style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/survey/list_by_manager_search_paging.do?managerno=${managerno}">설문조사 항목</a>
                       </ul>
                   </li>                   
                  
                   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="color: #626262; font-size: 17px; font-weight:bold;"  href="#">소식공간 관리 <span class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/beauty/search_paging.do?categoryno=1">미용스타일</a></li>
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/event/search_paging_member.do?memberno=${memberno}">이벤트응모</a></li>

                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/event/search_paging.do?managerno=${managerno}">이벤트관리</a></li>   
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/present/list_all_present.do">이벤트상품</a></li>             
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/user/list_all_user.do">이벤트참여자보기</a></li>                    
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/animalstory/list.do?content=">애니멀스토리</a></li>                 
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/animalstory/list.do?content=&nowPage=">애니멀스토리</a></li>

                       </ul>
                   </li>
                   <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="color: #626262; font-size: 17px; font-weight:bold;"  href="#">카테고리 관리 <span class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/categrp/list.do">카테고리 그룹</a></li>
                           <li><a  style="color: #626262; font-size: 17px; font-weight:bold; " href="${pageContext.request.contextPath}/category/list.do">카테고리</a></li>
                       </ul>
                   </li>                   
                   
                </ul>
           </div>
         
         </nav>
      </c:when>
    </c:choose>    
    
    
  
      <br>
      <br>
      </div>
   </nav>
   <br>
    
   </body>
</html>

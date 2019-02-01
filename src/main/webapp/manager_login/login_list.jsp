<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<script type="text/javascript">
  $(function(){

  });
</script>
</head> 

<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' style='width: 100%;'>
<DIV class='content' style='padding-top:5%; width: 80%; padding-bottom: 10%;'>
  <DIV class='title_line' style='width: 20%;'>관리자 로그인 기록</DIV>
  
   <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='../index.jsp'>HOME</A>
    
    <A href='./list.do?manager_loginno=${manager_loginVO.manager_loginno }'>${manager_loginVO.manager_loginno }</A>
    
  </ASIDE> 
<br>
  <div class='menu_line'></div>
<DIV style='width: 100%; margin: 0px auto;'>
  
  <table class="table" style='width: 100%;'>
  <%-- <caption>회원 목록</caption> --%>
  <colgroup>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    
  </colgroup>
  <TR>
    <TH class='th'style='text-align: center;'>&nbsp로그인번호</TH>
    <TH class='th'style='text-align: center;'>회원번호</TH>
    <TH class='th'style='text-align: center;'>IP 주소</TH>
    <TH class='th'style='text-align: center;'>날짜</TH>
    <TH class='th'style='text-align: center;'>기타</TH>
  </TR>
  
  <c:forEach var="manager_loginVO" items="${list }">
    <c:set var="manager_loginno" value ="${manager_loginVO.manager_loginno }" /> 
  <TR>
    <TD class='td' style='text-align: center;'>${manager_loginno}</TD>
    <%-- <TD class='td'style='text-align: center;'>${manager_loginVO.managerno}</TD> --%>
    <TD class='td' style='text-align: center;'><A href="../manager/read.do?managerno= ${manager_loginVO.managerno}">${manager_loginVO.managerno}</A></TD>
    <TD class='td'style='text-align: center;'>${manager_loginVO.ip}</TD>
    <TD class='td'style='text-align: center;'>${manager_loginVO.rdate.substring(0, 10)}</TD> <!-- 년월일 -->
    <TD class='td'style='text-align: center;'>
    <A href="./delete.do?manager_loginno=${manager_loginno}"><IMG src='./images/delete.png'  width="20" height="20"  title='삭제'></A>
    </TD>
    
  </TR>
  </c:forEach>
  
</TABLE>
</DIV>

<DIV class='bottom_menu'>
  <!-- <button type='button' onclick="location.href='./create.do'">등록</button> -->
  <!-- <button type='button' onclick="location.reload();" class="btn btn-secondary">새로 고침</button> -->
</DIV>
<br>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
  
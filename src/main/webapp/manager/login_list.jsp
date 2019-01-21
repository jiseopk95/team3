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
<DIV class='content' style='padding-top:5%; padding-bottom: 10%;'>
  <DIV class='title_line' style='width: 20%;'>로그인 기록</DIV>
  
   <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='../index.jsp'>HOME</A>
  </ASIDE> 
<br>
  <div class='menu_line'></div>
<DIV style='width: 100%; margin: 0px auto;'>
  
  <table class="table" style='width: 100%;'>
  <%-- <caption>회원 목록</caption> --%>
  <colgroup>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 30%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    
  </colgroup>
  <TR>
    <TH class='th'>로그인번호</TH>
    <TH class='th'>회원번호</TH>
    <TH class='th'>IP 주소</TH>
    <TH class='th'>날짜</TH>
    <TH class='th'>기타</TH>
  </TR>
  
  <c:forEach var="managerVO" items="${list }">
    <c:set var="managerno" value ="${managerVO.managerno }" /> 
  <TR>
    <TD class='td'>${managerno}</TD>
    <TD class='td'>${sessionScope.id}"</TD>
    <TD class='td'>${managerVO.name}</TD>
    <TD class='td'>${managerVO.rdate.substring(0, 10)}</TD> <!-- 년월일 -->
    <TD class='td'>
      <IMG src='./images/update.png'  width="20" height="20"  title='수정'></A>
      <IMG src='./images/delete.png'  width="20" height="20"  title='삭제'></A>
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
  
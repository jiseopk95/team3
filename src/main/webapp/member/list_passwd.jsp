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
  <form name='frm' id='frm' method="get" action="./passwdsearch.do">
 
   <!-- <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='../index.jsp'>HOME</A>
  </ASIDE>  -->
  
  <br>
   <DIV style='font-weight:bold;'>비밀번호 찾기</DIV>
   <DIV class='title_line' style='width: 30%;'></DIV><br>
  <A href='./list_passwd.do?memberno=${memberVO.memberno }'>${memberVO.email }</A>

    <c:if test="${param.word.length() > 0}"> 
      >
      [${param.email}] 검색 목록(${search_count2 } 건) 
    </c:if>
    <input type='hidden' name='memberno' id='memberno' value='${memberVO.memberno }'>
    
    <c:choose>
      <c:when test="${param.email != '' }">
        <label class=fontss >Name</label> <span class='menu_divide' >│</span>  <input type='text' name='name' id='name' value='${param.name }' style='width: 20%;'> <br><br>
        <label class=fontss >&nbsp&nbsp&nbsp&nbsp&nbspID</label> <span class='menu_divide' >│</span>  <input type='text' name='id' id='id' value='${param.id }' style='width: 20%;'> <br><br>
        <label class=fontss >EMAIL </label><span class='menu_divide' >│</span>  <input type='text' name='email' id='email' value='${param.email }' style='width: 20%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='name' id='name' value='' style='width: 35%;'>
        <input type='text' name='id' id='id' value='' style='width: 35%;'>
        <input type='text' name='email' id='email' value='' style='width: 35%;'>
      </c:otherwise>
    </c:choose>
    <br><br>
    <button class="btn btn-secondary" type='submit'>확인</button>
    <DIV class='title_line' style='width: 30%;'></DIV>
  </form>

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
  
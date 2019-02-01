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
  
<br>
<DIV style='width: 100%; margin: 0px auto;'>
<DIV style='font-weight:bold;'>비밀번호 찾기</DIV>  

<DIV class='title_line' style='width: 30%;'></DIV>
<br>
     <c:choose>
      <c:when test="${search_count3 eq 1}">
        ${managerVO.id} 의 비밀번호는 ${managerVO.passwd} 입니다. <br><br>
        로그인 후 마이페이지에서 비밀번호를 변경해주세요. <br>
      </c:when>  
      <c:otherwise>  
        정보가 일치하지 않습니다.<br>
        다시시도해주세요.
        <br>
        </c:otherwise>  
      </c:choose>
<br>
<DIV class='title_line' style='width: 30%;'></DIV>
</DIV>

<DIV class='bottom_menu'>
  <button class="btn btn-primary"  type='button' onclick="location.href='./login.do'">LOGIN</button>
<!--   <button class="btn btn-secondary" type='button' onclick="location.href='./idsearch.jsp'">아이디 찾기</button> -->
  <!-- <button type='button' onclick="location.reload();" class="btn btn-secondary">비밀번호 찾기</button> -->
</DIV>
<br>
</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
  
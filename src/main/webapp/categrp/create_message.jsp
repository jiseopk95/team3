<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

</head> 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content'>

<DIV class='title_line' style="text-align:center">알림</DIV>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.count == 1}">
          <LI class='li_none'  style="text-align:center">새로운 카테고리 그룹을 등록 했습니다.</LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none' style="text-align:center">새로운 카테고리 그룹 등록 실패 했습니다.</LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none' style="text-align:center">
        <br>
        <button type='button' class="btn btn-primary" onclick="location.href='./list.do'">목록</button>
      </LI>
     </UL>  
  </fieldset>

</DIV>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

   
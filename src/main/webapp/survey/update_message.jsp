<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

function exit(){
  opener.location.reload();
  window.close();
}
</script>
</head> 
<body>
<DIV class='container' style='width:100%;text-align:center;'>
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>

<DIV class='title_line'>알림</DIV>

<DIV class='content'>
  <UL>
      <c:choose>
        <c:when test="${param.count == 1 }">
          <LI class='li_none'>새로운 카테고리 그룹을 수정을 성공했습니다.</LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>새로운 카테고리 그룹 수정을 실패했습니다.</LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
         
        <button type='button' onclick='exit();'>닫기</button> 

      </LI>
     </UL>
  
</DIV>

</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</DIV> <!-- container END -->
</body>

</html> 

   
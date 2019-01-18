<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
<DIV class='container'>
<DIV class='content'>

  <c:choose>
  <c:when test="${param.count == 1 }">
    <LI class='li_none'>새로운 컨텐츠를 등록했습니다.</LI>
  </c:when>
  <c:otherwise>
    <LI class='li_none'>새로운 컨텐츠 등록에 실패했습니다.</LI>
  </c:otherwise>
</c:choose>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html>




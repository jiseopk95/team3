<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function exit() {
  opener.location.reload();
  window.close();
}
</script>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
<DIV class='container'>
<DIV class='content' style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'>
  <DIV class='message'>
    <fieldset class='fieldset_basic'>
    <legend>알림</legend>
    <c:choose>
      <c:when test="${param.count  == 1}">
        수정됐습니다.
      </c:when>
      <c:otherwise>
        수정에 실패했습니다.
      </c:otherwise>
    </c:choose>
    <br>
    <button type="button" class="btn btn-primary" onclick="exit();">닫기</button>
    </fieldset>
  </DIV>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html>




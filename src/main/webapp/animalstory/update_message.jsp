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

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
function exit() {
  opener.location.reload();
  window.close();
}
</script>   
    
</head> 
<body>
<DIV class='container'>
<DIV class='content' style='width: 100%; margin:0px auto; margin-top: 10%; margin-bottom: 10%'>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
  <legend>알림</legend>
    <UL>
      <c:choose>
        <c:when test="${param.count == 1 }">
          <LI class='li_none'>컨텐츠를 수정했습니다.</LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>컨텐츠 수정에 실패했습니다.</LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
        <button type="button" class="btn btn-primary" onclick="exit();">닫기</button>
      </LI>
     </UL>
  </fieldset>

</DIV>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>

</html> 

   
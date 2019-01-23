<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<DIV class='title_line'>목록 삭제</DIV>

<DIV class='content'>
  <FORM name='frm' method='POST' action='./delete_form.do'>
  <input type='hidden' name='surveyno' id='surveyno' value='${surveyVO.surveyno }'>                                                
    <UL>
      <c:choose>
        <c:when test="${cnt == 0 }">
            삭제된 글은 복구 할 수 없습니다.<br>
            삭제를 진행 하시겠습니까?<br>
            정말로 삭제하시겠습니까?<br><br>
       
        </c:when>
        <c:otherwise>
         <span style="color:blue;">[${surveyVO.survey_title}]</span>그룹에 관련된 카테고리가  <br>
          <span style="color:red;"> ${cnt}개</span> 발견되었습니다.<br>
           정말로 삭제하시겠습니까?<br><br>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
        <button type="submit">삭제</button>
        <button type='button' onclick='exit();'>닫기</button>

      </LI>
     </UL>


  </FORM>
</DIV>

</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</DIV> <!-- container END -->
</body>

</html> 



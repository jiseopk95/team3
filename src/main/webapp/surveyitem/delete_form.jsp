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

</head> 
<body>
<DIV class='container' style='width:100%;text-align:center;'>
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>

<DIV class='title_line'>목록 삭제</DIV>

<DIV class='content'>
  <FORM name='frm' method='POST' action='./delete_form.do'>
   <input type='hidden' name='surveyitemno' id='surveyitemno' value='${survey_itemVO.surveyitemno }'> 
    <div style='color:red;'>[${survey_itemVO.question}]</div> 
    삭제하면 복구 할 수 없습니다.<br><br>
    정말로 삭제하시겠습니까?<br><br>         

        
    <button type="submit">삭제</button>
    <button type="button" onclick="window.close()">취소</button>

  </FORM>
</DIV>

</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</DIV> <!-- container END -->
</body>

</html> 



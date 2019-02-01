<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>블로그 삭제</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>

</head> 

<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' style='width: 100%;'>
<DIV class='content'>   
     

    
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?categoryno=1'>등록</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do?categoryno=1'>목록</A>
 </aside>


  <div class='menu_line'></div>

  <FORM name='frm' method='POST' action='./delete.do'>
      <input type='hidden' name='questionno' value='${questionVO.questionno}'>
      <input type="hidden" name="categoryno" value="${reviewVO.categoryno }">

            
      <div class="form-group">   
        <div class="col-md-12" style='text-align: center; margin: 30px;'>
          삭제 되는글: ${questionVO.title }<br><br>
          삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.<br>
          <button type = "submit">삭제 진행</button>
          <button type = "button" onclick = "history.back()">취소</button>
        </div>
      </div>   
  </FORM>

</DIV> <!-- content END -->


</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

  
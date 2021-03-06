<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

<!--  <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
 <script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script> -->
 
</head> 


<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' >
<DIV class='content'>     

 <%--  <ASIDE style='float: left;'>
      <A href='../category/list.do'>게시판 목록</A>  
      >
      <A href='./list.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>등록</A>
  </ASIDE>  --%>

 <!--  <div class='menu_line'></div> -->
  <DIV class='content' style='width: 100%; margin:0px auto; margin-top: 10%; margin-bottom: 10%'>
    <FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
   
        <div class="form-group">   
        <label for="info" class="col-md-1 control-label">내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='info' id='info'  rows='10'></textarea>
        </div>
      </div>
          <div class="form-group">   
        <label for="end_date" class="col-md-1 control-label">만료일</label>
        <div class="col-md-11">
        <input type='text' class="form-control input-lg" name='end_date' id='end_date' value='' required="required" style='width: 30%;'>
        </div>
      </div>

      <DIV style='text-align: right;'>
        <button type="submit" class="btn btn-primary btn-sm">등록</button>
        <button type="button" class="btn btn-primary btn-sm" onclick="location.href='./list_all_present.do'">취소[목록]</button>
      </DIV>
    </FORM>
  </DIV>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 


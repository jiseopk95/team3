<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>글 수정</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- <script type="text/javascript" src="../ckeditor/ckeditor.js"></script> -->

<!-- <script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script> -->

</head> 

<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' >
<DIV class='content' style='width: 100%; pagging-top:5%; padding-bottom:10%'>     

<%--   <ASIDE style='float: left;'>
    <A href='../category/list.do'>게시판 목록</A>
    <span style='font-size: 1.2em;'>></span>   
    <A href='./list_by_categoryno.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>

  </ASIDE>  --%>

<!--   <div class='menu_line'></div> -->
  <FORM name='frm' method='POST' action='./update.do'
             enctype="multipart/form-data" class="form-horizontal">
             <input type='hidden' name='presentno' id='presentno' value='${presentVO.presentno }'>
      <div class="form-group">   
        <label for="info" class="col-md-1 control-label">선물내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='info' id='info' rows='10'>${presentVO.info}</textarea>
        </div> 
      </div>
          <div class="form-group">   
        <label for="end_date" class="col-md-1 control-label">만료일</label>
        <div class="col-md-11">
        <input type='text' class="form-control input-lg" name='end_date' id='end_date' value=' ${presentVO.end_date}' required="required" style='width: 30%;'>
        </div>
      </div>

      <DIV style='text-align: right;'>
        <button type="submit" class="btn btn-primary btn-sm">변경된 내용 저장</button>
<button type="button" class="btn btn-primary btn-sm" onclick="location.href='./list_all_present.do'">취소[목록]</button>
      </DIV>
  </FORM>


</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>  
 
 

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>블로그 수정</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('contents');  // <TEXTAREA>태그 id 값
  };
  
  function chageLangSelect(){
    var langSelect = document.getElementById("score");
     
    // select element에서 선택된 option의 value가 저장된다.
    var selectValue = langSelect.options[langSelect.selectedIndex].value;
 
    // select element에서 선택된 option의 text가 저장된다.
    var selectText = langSelect.options[langSelect.selectedIndex].text;
}
</script>

</head> 

<body id="page-top">
<jsp:include page="/menu/top.jsp" flush='false' /> 
<DIV class='container' style='width: 100%;'>
<DIV class='content'>     

  <ASIDE style='float: left;'>
      <A href='../category/list.do'>게시판 목록</A>  
  </ASIDE>
 
  
    <DIV class='title_line'>질문 등록</DIV>

  <div class='menu_line'></div>
  <DIV class='content' style='width: 100%;'>
    <FORM name='frm' method='POST' action='./update.do'
               enctype="multipart/form-data" class="form-horizontal">
               
      <input type='hidden' name='categoryno' id='categoryno' value='${param.categoryno }'>
      <input type='hidden' name='memberno' id='memberno' value='1'>
      <input type='hidden' name='questionno' id='questionno' value='${questionVO.questionno }'>
      <br>
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">글 제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='${questionVO.title }' required="required" style='width: 70%;'>
        </div>
      </div>   
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">글쓴이</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='name' id='name' value='${questionVO.name }' required="required" style='width: 30%;'>
        </div>
      </div>   
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='content' id='content'  rows='10'>${questionVO.content }</textarea>
        </div>
      </div>
       <div class="form-group">
      <label for="passwd" class="col-md-1 control-label">패스워드</label>    
      <div class="col-md-10">
        <input type='password' class="form-control input-md" name='passwd' id='passwd' value='${questionVO.passwd }' required="required" style='width: 30%;' placeholder="패스워드">
      </div>
    </div> 
    <label for='visible'>형식</label>
      <select name='visible' id='visible'>
        <option value='Y' selected="selected">Y</option>
        <option value='N'>N</option>
      </select> 

      <DIV style='text-align: center;'>
        <button type="submit" class='btn btn-primary'>변경된 내용 저장</button>
        <button type="button"  class='btn btn-primary' onclick="location.href='./list.do?categoryno=1'">취소[목록]</button>
      </DIV>
  </FORM>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 
 
 

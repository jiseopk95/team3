<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>답변 등록</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<!-- <script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script> -->

</head> 

<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' style='width: 90%;'>
<DIV class='content'>     

  <ASIDE style='float: left;'>
    <A href='../category/list.do'>게시판 목록</A>
    >  
    <A href='./list?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A>
    >
    답변쓰기
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>
    <span class='menu_divide' >│</span> 
    <A href='javascript: history.back()'>답변 등록 취소</A>
  </ASIDE> 

 <DIV class='title_line'>답변 등록</DIV>
  <div class='menu_line'></div>
  <DIV style='width: 100%;'>
    <FORM name='frm' method='POST' action='./reply.do'
               enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='categoryno' id='categoryno' value='${categoryVO.categoryno }'>
      <input type="hidden" name="questionno" value="${questionVO.questionno}">
 <%--      <input type="hidden" name="nowPage" value="${param.nowPage}"> --%>
 
    <div class="form-group">   
        <label for="title" class="col-md-1 control-label">글 제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='바다건너' required="required" style='width: 70%;'>
        </div>
      </div>   
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">글쓴이</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='name' id='name' value='관리자' required="required" style='width: 30%;'></div>
        </div>
      </div>    
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='content' id='content'  rows='10'>크리스마스에 갔던곳</textarea>
        </div>
      </div>
       <div class="form-group">
      <label for="passwd" class="col-md-1 control-label">패스워드</label>    
      <div class="col-md-11">
        <input type='password' class="form-control input-md" name='passwd' id='passwd' value='${questionVO.passwd }' required="required" style='width: 30%;' placeholder="패스워드">
      </div>
    </div> 

     
      <DIV style='text-align: right;'>
        <button type="submit">답변 등록</button>
        <button type="button" onclick="location.href='./list.do?categoryno=${categoryVO.categoryno}'">취소[목록]</button>
      </DIV>
    </FORM>
  </DIV>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 


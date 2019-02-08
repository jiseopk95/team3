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
<%-- <DIV class='content'> 

  <ASIDE style='float: left;'>
      <A href='../category/list.do'>게시판 목록</A>  
      >
      <A href='./list.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>등록</A>
  </ASIDE>   --%>
    
<!--   <div class='menu_line'></div> -->
  <DIV class='content'  style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'> 
    <FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
            <%--    <input type='hidden' name='styleno' id='styleno' value=' ${beautyVO.styleno}'>  --%>
            <!--  create.cont 가보면 폼에 beautyVO가 없는데 내가 여기서 beautyVO.styleno를 쓰려 하니까 에러가 나는거임. -->
      <input type='hidden' name='managerno' id='managerno' value='${sessionScope.managerno}'>
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='최신미용트렌드 소개합니다' required="required" style='width: 80%;'>
        </div>
      </div>   
       <div class="form-group">   
        <label for="content" class="col-md-1 control-label">스타일이름</label>
        <div class="col-md-11">
           <input type='text' class="form-control input-lg" name='name' id='name' value='곰돌이컷' required="required" style='width: 30%;'>
        </div>
      </div>
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">작성자</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='rname' id='rname' value='${sessionScope.name}' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="content" class="col-md-1 control-label">이메일</label>
        <div class="col-md-11">
           <input type='text' class="form-control input-lg" name='email' id='email' value='aaa@aa' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="content" class="col-md-1 control-label">내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='content' id='content'  rows='10'>오늘 소개할 스타일</textarea>
        </div>
      </div>
        <div class="form-group">   
        <label for="content" class="col-md-1 control-label">가격</label>
        <div class="col-md-11">
           <input type='number' class="form-control input-lg" name='pay' id='pay' value='20000' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="content" class="col-md-1 control-label">소요시간</label>
        <div class="col-md-11">
           <input type='number' class="form-control input-lg" name='times' id='times' value='3' required="required" style='width: 30%;'>
        </div>
      </div>
      <div class="form-group">   
        <label for="filesMF" class="col-md-1 control-label">업로드 파일</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40' multiple="multiple">
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>   

      <DIV style='text-align: right;'>
        <button type="submit" class="btn btn-primary btn-sm">등록</button>
        <button type="button" class="btn btn-primary btn-sm" onclick="location.href='./search_paging.do?managerno=${sessionScope.managerno}'">취소[목록]</button>
      </DIV> 
    </FORM>
<!-- </DIV> -->

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 


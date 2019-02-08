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
<DIV class='content' style='width: 100%; margin:0px auto; margin-top: 10%; margin-bottom: 10%'>     

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
      <input type='hidden' name='styleno' id='styleno' value='${beautyVO.styleno }'>
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='${beautyVO.title }' required="required" style='width: 80%;'>
        </div>
      </div>   
       <div class="form-group">   
        <label for="content" class="col-md-1 control-label">스타일이름</label>
        <div class="col-md-11">
           <input type='text' class="form-control input-lg" name='name' id='name' value='${beautyVO.name }' required="required" style='width: 30%;'>
        </div>
      </div>
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">작성자</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='rname' id='rname' value='${beautyVO.rname }' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="content" class="col-md-1 control-label">이메일</label>
        <div class="col-md-11">
           <input type='text' class="form-control input-lg" name='email' id='email' value='${beautyVO.email }' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="content" class="col-md-1 control-label">내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='content' id='content'  rows='10'>${beautyVO.content}</textarea>
        </div>
      </div>
        <div class="form-group">   
        <label for="content" class="col-md-1 control-label">가격</label>
        <div class="col-md-11">
           <input type='number' class="form-control input-lg" name='pay' id='pay' value='${beautyVO.pay }' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="content" class="col-md-1 control-label">소요시간</label>
        <div class="col-md-11">
           <input type='number' class="form-control input-lg" name='times' id='times' value='${beautyVO.times }' required="required" style='width: 30%;'>
        </div>
      </div>
      <div id='file1Panel' class="form-group">
        <label class="col-md-2 control-label"></label>
        <div class="col-md-10">
          <c:if test="${file_list.size() > 0 }">
              <DIV>
                <c:forEach var ="fileVO"  items="${file_list }">
                  <A href="javascript: panel_img('${fileVO.file }')"><IMG src='./storage/${fileVO.thumb }' style='margin-top: 2px;'></A>
                </c:forEach>
              </DIV>
            </c:if>
        </div>
      </div>
      <div class="form-group">   
        <label for="filesMF" class="col-md-1 control-label">파일</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-md" name='filesMF' id='filesMF' size='40' multiple="multiple">
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>   

      <DIV style='text-align: right;'>
        <button type="submit" class="btn btn-primary btn-sm">변경된 내용 저장</button>
<button type="button" class="btn btn-primary btn-sm" onclick="location.href='./search_paging.do'">취소[목록]</button>
      </DIV>
  </FORM>


</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>

</html> 
 
 

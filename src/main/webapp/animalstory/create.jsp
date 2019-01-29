<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>애니멀스토리</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">
window.onload=function(){
  CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
};
</script>

</head> 

<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content'  style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'>

  <%-- <ASIDE style='float: left;'>
      <A href='../category/list.do'>게시판 목록</A>  
      >
      <A href='./list.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>등록</A>
  </ASIDE>  --%>

  <div class="title_line">${title }</div>
    <FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
               
      <input type="hidden" name="managerno" id="managerno" value="${managerno }">
      
      <div class="form-group">   
        <div class="col-md-12">
          <label class="radio-inline">
            <input type='radio' class='anitype' name='anitype' id='dog' value='1' checked>강아지
          </label>
          <label class="radio-inline">
            <input type='radio' class='anitype' name='anitype' id='cat' value='2'>고양이
          </label>
        </div>
      </div>
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">글 제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='강아지 이야기' required="required" style='width: 80%;'>
        </div>
      </div>   
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='content' id='content'  rows='10'>비글비글</textarea>
        </div>
      </div>
      <div class="form-group">   
        <label for="filesMF" class="col-md-1 control-label">대표사진</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40' multiple="multiple">
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>   

      <DIV style='text-align: right;'>
        <button type="submit"class="btn btn-primary"  >등록</button>
        <button type="button" class="btn btn-primary"  onclick="location.href='./list.do?managerno=${managerno}'">취소[목록]</button>
      </DIV>
    </FORM>



</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 


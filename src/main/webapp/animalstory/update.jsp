<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	$(function() {
	  if(${aniVO.anitype == 1}) {
	    $('#dog').attr("checked", "true");
	  } else {
	    $('#cat').attr("checked", "true");
	  }
	})
  function update() {
    //var check = confirm("기존의 사진은 삭제되고 새로운 사진이 등록됩니다.\n정말 수정하시겠습니까?");
  }
  function exit() {
	  opener.location.reload();
	  window.close();
	}
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script>
<style type="text/css">
label {
    font-size: 15px;
    float: left;
  }
</style>
</head> 

<body>

<DIV class='container'>
<DIV class='content'  style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'>

  <div class="title_line" style="width: 30%;">${title }</div>
    <FORM name='frm' method='POST' action='./update.do'
               enctype="multipart/form-data" class="form-horizontal">
               
      <input type='hidden' class='anino' name='anino' id='anino' value='${aniVO.anino}'>
      <input type='hidden' class='managerno' name='managerno' id='managerno' value='${aniVO.managerno}'>
                     
      <div class="form-group" style="text-align: left;">   
        <label for="anitype" class="col-md-1 control-label">글 분류</label>
        <div class="col-md-11">
          <label class="radio-inline">
            <input type='radio' class='anitype' name='anitype' id='dog' value='1' >강아지
          </label>
          <label class="radio-inline">
            <input type='radio' class='anitype' name='anitype' id='cat' value='2'>고양이
          </label> 
        </div>
      </div>
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">글 제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='${aniVO.title}' required="required" style='width: 80%;'>
        </div>
      </div>   
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='content' id='content'  rows='10'>${aniVO.content }</textarea>
        </div>
      </div>
      <div id='file1Panel' class="form-group">
        <label class="col-md-2 control-label">현재 사진</label>
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
        <label for="filesMF" class="col-md-1 control-label">수정 사진</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40' multiple="multiple">
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>

      <DIV style='text-align: right;'>
        <button type="submit" class="btn btn-primary" >등록</button>
        <button type="button" class="btn btn-primary" onclick="exit();" style="float: right;">취소</button>
      </DIV>
    </FORM>



</DIV> <!-- content END -->

</DIV> <!-- container END -->

</body>

</html> 


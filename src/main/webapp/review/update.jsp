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

<body>

<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' >
<DIV class='content'>     

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

  </ASIDE> --%> 

  <div class='menu_line'></div>
  <FORM name='frm' method='POST' action='./update.do'
             enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='categoryno' id='categoryno' value='1'>
      <input type='hidden' name='reviewno' id='reviewno' value='${reviewVO.reviewno }'>
      
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">글 제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-md" name='title' id='title' value='${reviewVO.title}' required="required" style='width: 80%;'>
        </div>
      </div>  
         <div class="form-group">   
        <label for="title" class="col-md-1 control-label">글쓴이</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='name' id='name' value='${reviewVO.name} ' required="required" style='width: 30%;'>
        </div>
      </div>    
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-md" name='contents' id='contents'  rows='10'>${reviewVO.contents}</textarea>
        </div>
      </div>
         <div class="form-group">   
        <label for="content" class="col-md-1 control-label">평점</label>
        <div class="col-md-11">
        
     <select name='score' id='score'>
          <option value='1' selected="selected"<c:if test="${reviewVO.score == '1'}">selected='selected'</c:if>>1점</option>
          <option value='2' <c:if test="${reviewVO.score == '2'}">selected='selected'</c:if>>2점</option>
          <option value='3' <c:if test="${reviewVO.score == '3'}">selected='selected'</c:if>>3점</option>
          <option value='4' <c:if test="${reviewVO.score == '4'}">selected='selected'</c:if>>4점</option>
          <option value='5' <c:if test="${reviewVO.score == '5'}">selected='selected'</c:if>>5점</option>
        </select>
        

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
 
 

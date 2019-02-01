<%@ page contentType="text/html; charset=UTF-8"%>
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

<script type="text/javascript">
  $(document).ready(function(){ // window.onload = function() { ... }
    imgResize()
  });

  //<div id='file1Panel'> 태그의 width에 맞추어 자동 축소
  function imgResize() {
    var file1 = $('#file1');
    var width = file1.width();
    // alert(width);
    // console.log("loading - width:" + loading + " - " + width);
    // console.log("screen.width-(screen.width * 0.3): " + screen.width-(screen.width * 0.3));
        
    if (file1 != null) {
      // 이미지 width가 화면의 70%보다 크다면
      if (width > screen.width-(screen.width * 0.4)) {
        // console.log("loading - width:" + loading + " - " + width);
        // file1.width(600); // 이미지 축소
        $('#file1Panel').attr('width', '100%');  // 할당된 영역을 100%로 설정함.
        file1.css('width', '900'); // <div id='file1Panel'> 태그의 width에 맞추어 자동 축소
      } else {
        // 작은 이미지는 그대로 출력
      }
    }

  }
  
  function panel_img(file){
    var panel = '';
    panel += "<DIV id='panel' class='popup_img' style='width: 80%;'>";
    panel += "  <A href=\"javascript: $('#main_panel').hide();\"><IMG src='./storage/"+file+"' style='width: 100%;'></A>";
    panel += "</DIV>";
    
    $('#main_panel').html(panel);
    $('#main_panel').show();
    
  }
</script>
</head>

<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' style='width: 100%;'>

<DIV class='content'>   

 <DIV class='title_line'>질문</DIV>
 
 <div class='menu_line'>
 <ASIDE style='float: center; text-align:right;'>
    <A href='./update.do?questionno=${questionVO.questionno }&categoryno=${questionVO.categoryno}'>수정</A>
    <span class='menu_divide' >│</span> 
    <A href='./delete.do?questionno=${questionVO.questionno }&categoryno=${questionVO.categoryno}'>삭제</A> <br>
  </ASIDE>
  </div>

  <DIV id='main_panel'></DIV>
  
  <FORM name='frm' method="get" action='./read.do'>
      <input type="hidden" name="questionno" value="${questionVO.questionno}">
      <input type="hidden" name="passwd" value="${questionVO.passwd}">
      <fieldset class="fieldset">
        <ul>
          <li class="li_none">
           <div class="subject-part">
             <div class="subject">           
             <span>${questionVO.title}</span>
             </div> 
            </div>
            <div class="info-part">
            <span>작성자: ${questionVO.name }</span>&nbsp&nbsp&nbsp&nbsp   <span class="sep">| </span>&nbsp&nbsp
            <span>작성일: ${questionVO.rdate.substring(0, 16)}</span>&nbsp&nbsp&nbsp&nbsp  <span class="sep">| </span>&nbsp&nbsp
            </div>
            <div class = 'content-part'>
            <DIV>${questionVO.content }</DIV>
          </li>
          </div>
        </ul>
      </fieldset>
      <DIV style='text-align: right;'>
        <button type="button" class = "btn btn-primary" onclick="location.href='./reply.do?questionno=${questionVO.questionno}&categoryno=${questionVO.categoryno}&passwd=${questionVO.passwd}'">답변하기</button>
          </DIV>
            <%-- <a href="./reply.do?questionno=${questionVO.questionno}&categoryno=${questionVO.categoryno}&passwd=${questionVO.passwd}"><img src="./images/q&a.png" title="답변" border='0'/></a> --%>
       <%--  <%@ include file="../question/comment.jsp" %> --%>
  </FORM>
  <br><br>


</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>


   
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
$(function(){

});
</script>

<script type="text/javascript">
</script>
</head>

<body id="page-top">
<jsp:include page="/menu/top.jsp" flush='false' />  
<DIV class='container' >
<DIV class='content'>     
<form name='frm' id='frm' method="get" action="./list.do">
 <%--  <ASIDE style='float: left;'>
    <A href='../category/list.do'>게시판 목록</A>
    <span style='font-size: 1.4em;'>></span> 
    <A href='./list_by_category.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A> 
  </ASIDE> --%>
  
  <DIV class='title_line'>${categoryVO.name} 후기 목록</DIV>
  
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <c:if test="${sessionScope.memberno != null}">
      <span class='menu_divide' >│</span> 
      <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>
    </c:if>
    <input type='hidden' name='categoryno' id='categoryno' value='${categoryVO.categoryno }'>
     <span class='menu_divide' >│</span> 
    <c:choose>
      <c:when test="${param.title != '' }">
        <input type='text' name='title' id='title' value='${param.title }' style='width: 35%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='title' id='title' value='' style='width: 35%;'>
      </c:otherwise>
    </c:choose>
    <button type='submit' class='btn btn-primary'>검색</button>
    <button type='button' class='btn btn-primary'
                 onclick="location.href='./list.do?categoryno=${categoryVO.categoryno }'">전체 보기</button>
  </ASIDE> 
  </form>
  <DIV class='menu_line' style='clear: both;'></DIV>  
         
  <div style='width: 100%;'>
    <table class="table" style='width: 100%;'>
      <colgroup>
        <col style="width: 5%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 20%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr >
          <th style='text-align: center;' >NO</th>
          <th style='text-align: center;' >파일</th>
          <th style='text-align: center;' >카테고리</th>
          <th style='text-align: center;'>제목</th>
          <th style='text-align: center;'>글쓴이</th>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>평점</th>
          <th style='text-align: center;'>기타</th>
        </tr>
      
      </thead>
        <c:forEach var="reviewVO" items="${list }">
          <tr> 
          <td style='vertical-align: middle;'> <p class="text-center">${reviewVO.reviewno} </p> </td>
          <td style='vertical-align: middle;'>
            <c:choose>
              <c:when test="${reviewVO.thumbs != NULL}">
               <p class="text-center">
                <IMG id='thumbs' src='./storage/${reviewVO.thumbs}' > <!-- 이미지 파일명 출력 -->
                </p>
              </c:when>
              <c:otherwise>
               <p class="text-center">
                <IMG id='thumbs' src='./images/none1.jpg' style='width: 120px; height: 80px;'> <!-- 파일이 존재하지 않는 경우 -->
                </p>
              </c:otherwise>
            </c:choose>
            </td>          
            <td style='vertical-align: middle;'> <p class="text-center">${categoryVO.name} </p> </td>
           <td style='vertical-align: middle;'>
              <p class="text-center">
              <a href="./read.do?reviewno=${reviewVO.reviewno}&categoryno=${reviewVO.categoryno}">${reviewVO.title}</a> 
              </p>
            </td> 
            
            <td style='vertical-align: middle;'> <p class="text-center">${reviewVO.name} </p> </td>
            <td style='vertical-align: middle;'><p class="text-center">${reviewVO.rdate.substring(0, 10)} </p></td>
            <td style='vertical-align: middle;'> <p class="text-center">${reviewVO.score} </p></td>
            <td style='vertical-align: middle;'>
            <p class="text-center">
              <a href="./update.do?reviewno=${reviewVO.reviewno}&categoryno=1"><img src="./images/update.png" title="수정" style='width: 20px; border='0' /></a>
              <a href="./delete.do?reviewno=${reviewVO.reviewno}&categoryno=1"><img src="./images/delete.png" title="삭제" style='width: 20px; border='0'/></a>
            <%--   ${reviewVO.categoryno} / ${reviewVO.memberno } --%>
            </p>
            </td>
          </tr>
        </c:forEach>
        
    </table>
    <br><br>
  </div>


</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>

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
<c:import url="/menu/top.jsp" /> 
<DIV class='container' style='width: 100%;'>
<DIV class='content'>     
<!-- <form name='frm' id='frm' method="get" action="./list.do"> -->

 <%--  <ASIDE style='float: left;'>
    <A href='../category/list.do'>게시판 목록</A>
    <span style='font-size: 1.4em;'>></span> 
    <A href='./list_by_category.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A> 
  </ASIDE> --%>
  
  <DIV class='title_line'>질문 목록</DIV>
  
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>
    <input type='hidden' name='categoryno' id='categoryno' value='${param.categoryno }'> 
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>  
         
  <div style='width: 100%;'>
    <table class="table" style='width: 100%;'>
      <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 40%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr >
          <th style='text-align: center;' >NO</th>
          <th style='text-align: center;' >카테고리</th>
          <th style='text-align: center;'>제목</th>
          <th style='text-align: center;'>글쓴이</th>
          <th style='text-align: center;'>등록일</th>
        </tr>
      
      </thead>
        <c:forEach var="questionVO" items="${list }">
         
          <tr> 
                
           <td style='vertical-align: middle;'> <p class="text-center">${questionVO.questionno} </p> </td>
            <td style='vertical-align: middle;'> <p class="text-center">${categoryVO.name}</p> </td>
            
       
            <td style='vertical-align: middle;'>
              <c:choose>
                <c:when test="${questionVO.ansnum == 0 }"> <!-- 부모글인 경우 -->
                <a href="./passwd_message.jsp?questionno=${questionVO.questionno}&categoryno=${questionVO.categoryno}">${questionVO.title}</a> <img src="./images/lock.png"  style='width: 15px; border='0' />
                </c:when>
                <c:when test="${questionVO.ansnum > 0}">    <!-- 답변글인 경우 -->
                  <img src='./images/white.png' style='width: ${questionVO.indent * 20}px; opacity: 0.0;'>
                  <img src='./images/reply4.png'>RE:
                  <a href="./reply_passwd_message.jsp?questionno=${questionVO.questionno}&categoryno=${questionVO.categoryno}">${questionVO.title}</a> <img src="./images/lock.png"  style='width: 15px; border='0' />
                </c:when>
              </c:choose>
              
              <%-- <a href="./passwd_message.jsp?questionno=${questionVO.questionno}&categoryno=${questionVO.categoryno}">${questionVO.title}</a> <img src="./images/lock.png"  style='width: 15px; border='0' /> --%>
            </td> 
            
           <%-- <td style='vertical-align: middle;'>
              <p class="text-center">
              <a href="./passwd_message.jsp?questionno=${questionVO.questionno}&categoryno=${questionVO.categoryno}">${questionVO.title}</a> <img src="./images/lock.png"  style='width: 15px; border='0' />
              </p>
            </td>  --%>
            
            <td style='vertical-align: middle;'> <p class="text-center">${questionVO.name} </p> </td>
            <td style='vertical-align: middle;'><p class="text-center">${questionVO.rdate.substring(0, 10)} </p></td>

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

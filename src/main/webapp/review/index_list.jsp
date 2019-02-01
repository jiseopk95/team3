<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">






  <legend>진료 후기</legend>
  <DIV class="view">
    ▷
    <A href="${pageContext.request.contextPath}/review/list.do">전체 보기</A>
  </DIV>
    <c:forEach var="reviewVO" items="${index_list }">
       <a href="./read.do?reviewno=${reviewVO.reviewno}&categoryno=${reviewVO.categoryno}">${reviewVO.title}</a>
        ( ${reviewVO.name }) &nbsp;  ${reviewVO.rdate.substring(0, 10)} 
        <br>
    </c:forEach>



  

</html>

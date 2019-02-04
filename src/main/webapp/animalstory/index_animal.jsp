<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style type="text/css">
/* fieldset 전체 적용 */
.index_animal{
  width: 100%;
  margin: 0px auto; 
  border-left: none; 
  border-right: none;
  padding-top:10px;
  padding-bottom:5px;
  text-align: left;
}
/* <UL> 태그에 적용 */
.categrp_category_list_left{
  padding-left: 10%; 
  line-height: 20px;
  
}
ul {
  list-style-image: url("${pageContext.request.contextPath}/animalstory/images/bu6.gif");
  
}
a {
  text-decoration: none;
}
/* 출력되는 A 태그 기본 모양 */
a:link{ 
  text-decoration: none !important; /* 밑줄 삭제 */
  color: #000000;
}
/* A 태그에 마우스가 위치했을 때 */
a:hover{
  text-decoration: none !important;
  color: #0080ff;
  color: #000000;
  font-weight: bold;
}
/* A 태그가 클릭된적이 있는 경우의 모양 */
a:visited {
  text-decoration: none !important;
  color: #000000;
}
legend {
  font-weight: bold;
  text-align: left;
}
.view {
/*   float: right; */
  color: #999999;
  font-size: 15px;
}

</style>
 <legend style="border-bottom: none; margin-bottom: 0%; color: black !important;">애니멀 스토리</legend>
   <DIV class="view" style="color: #999999; text-align: right;">
    <A href="${pageContext.request.contextPath}/animalstory/list.do?content=">+ more</A>
  </DIV>
  
<fieldset class='index_animal' >
  <!-- <UL class='categrp_category_list_left' > -->
    <c:forEach var="title" items="${index_list}">
      ${title}
    </c:forEach>
  <!-- </UL> -->
</fieldset>
<br>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<script type="text/javascript">
  $(function(){

  });
</script>
</head> 

<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' style='width: 100%;'>
<DIV class='content' style='padding-top:5%; width: 80%; padding-bottom: 10%;'>
  <form name='frm' id='frm' method="get" action="./list_search.do">
  <DIV class='title_line' style='width: 20%;'>등록 동물 목록</DIV>
  
   <ASIDE style='float: right;'>
    <A href='../pet/list.do'>전체 동물보기</A>
    
    <A href='./list_search.do?petno=${petVO.petno }'>${petVO.name }</A>

    <c:if test="${param.word.length() > 0}"> 
      >
      [${param.name}] 검색 목록(${search_count } 건) 
    </c:if>
    
    <span class='menu_divide' >│</span> 
    <c:choose>
      <c:when test="${param.name != '' }">
        <input type='text' name='name' id='name' value='${param.name }' style='width: 35%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='name' id='name' value='' style='width: 35%;'>
      </c:otherwise>
    </c:choose>
    <button type='submit'>검색</button>
  </ASIDE> 
    </form>
    
<br>
  <div class='menu_line'></div>
<DIV style='width: 100%; margin: 0px auto;'>
  
  <table class="table" style='width: 100%;'>
  <%-- <caption>회원 목록</caption> --%>
  <colgroup>
    <col style='width: 8%;'/>
    <col style='width: 11%;'/>
    <col style='width: 8%;'/>
    <col style='width: 8%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 13%;'/>
    <col style='width: 12%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
  </colgroup>
  <TR>
    <TH class='th'>&nbsp&nbsp번호</TH>
    <TH class='th'>&nbsp&nbsp회원번호</TH>
    <TH class='th'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp사진</TH>
    <TH class='th'>&nbsp&nbsp이름</TH>
    <TH class='th'>&nbsp&nbsp&nbsp&nbsp나이</TH>
    <TH class='th'>&nbsp&nbsp&nbsp&nbsp성별</TH>
    <TH class='th'>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp종류</TH>
    <TH class='th'>&nbsp중성화여부</TH>
    <TH class='th'>&nbsp&nbsp&nbsp몸무게</TH>
    <TH class='th'>&nbsp&nbsp&nbsp기타</TH>
  </TR>
  
  <c:forEach var="petVO" items="${list }">
  <c:set var="petno" value ="${petVO.petno }" />
  <TR>
    <TD class='td' style='padding-top: 3%'>${petno}</TD>
    <TD class='td' style='padding-top: 3%'><A href="../member/read.do?memberno= ${petVO.memberno}">${petVO.memberno}</A></TD>
    <td style='vertical-align: middle;'>
           <c:choose>
              <c:when test="${petVO.thumbs != ''}">
                <IMG style='border-radius: 50%' id='thumbs' src='./storage/${petVO.thumbs }'> <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                 <!-- 파일이 존재하지 않는 경우 -->
                 <IMG src='./images/none1.jpg' style='width: 120px; height: 80px;'>
               </c:otherwise>
            </c:choose> 
    </td>
    <TD class='td' style='padding-top: 3%'><A href="../pet/read.do?petno= ${petVO.petno}">${petVO.name}</A></TD>
    <TD class='td' style='padding-top: 3%'>${petVO.age}</TD>
    <TD class='td' style='padding-top: 3%'>${petVO.gender}</TD>
    <TD class='td' style='padding-top: 3%'>${petVO.pet_type}</TD>
    <TD class='td' style='padding-top: 3%'>${petVO.neutralization}</TD>
    <TD class='td' style='padding-top: 3%'>${petVO.weight}</TD>
    
    <TD class='td' style='padding-top: 3%'>
      <A href="../chart/create.do?petno=${petno}"><IMG src='./images/upload.png'  width="20" height="20"  title='차트만들기'></A>
      <A href="./update.do?petno=${petno}"><IMG src='./images/update.png'  width="20" height="20"  title='수정'></A>
      <A href="./delete.do?petno=${petno}"><IMG src='./images/delete.png'  width="20" height="20"  title='삭제'></A>
    </TD>
    
  </TR>
  </c:forEach>
  
</TABLE>
</DIV>

<DIV class='bottom_menu'>
  <!-- <button type='button' onclick="location.href='./create.do'">등록</button> -->
  <!-- <button type='button' onclick="location.reload();" class="btn btn-secondary">새로 고침</button> -->
</DIV>

<br>
<DIV class='bottom_menu'>${paging }</DIV>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
  
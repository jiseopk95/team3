<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>이벤트참여자</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/JavaScript"
  src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">

</script>
</head>

<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->

<DIV class='container'>
<DIV class='title_line'>이벤트참여자
 </DIV>
<DIV class='content' style='width: 100%; pagging-top:5%; padding-bottom:10%'>
    <!-- Modal -->    
  <div class="modal fade" id="modal_panel" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title" id='modal_title'></h4>
        </div>
        <div class="modal-body">
          <p id='modal_content'></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div> <!-- Modal END -->
<!--   <DIV class='menu_line' style='clear: both;'></DIV>  -->
  <div style='width: 100%;'>

    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 15%;"></col> 
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
       
          <th style='text-align: center;'>번호</th>
          <th style='text-align: center;'>회원번호</th>  
          <th style='text-align: center;'>회원이름</th>  
          <th style='text-align: center;'>이벤트 목록 번호</th>  
          <th style='text-align: center;'>참여날짜</th>  <!-- 아마 여기에 선물번호로 연결해줘야 할것같음.. -->
          <th style='text-align: center;'>당첨여부</th>  
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="member_userVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle; text-align: center;'>${member_userVO.eventuserno}</td>
            <td style='vertical-align: middle; text-align: center;'>${member_userVO.memberno}</td>
            <td style='vertical-align: middle; text-align: center;'>${member_userVO.name}</td>
            <td style='vertical-align: middle; text-align: center;'>${member_userVO.eventno}</td> <!-- 이거 번호 잘 나오면 여기에 이벤트 이름이 들어가게 하기 -->
            <td style='vertical-align: middle; text-align: center;'>${member_userVO.joindate.substring(0, 10)}</td>
<td style='vertical-align: middle; text-align: center;'> ${member_userVO.win} :
    <c:choose>   
         <c:when test="${member_userVO.win==1}"> 
            당첨
         </c:when>
         <c:otherwise>
            미당첨
         </c:otherwise>
    </c:choose>
    </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
      
    <br><br>
 </div>
<br><br>
  
</DIV> <!-- content END -->
</DIV> <!-- container END -->


<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
    


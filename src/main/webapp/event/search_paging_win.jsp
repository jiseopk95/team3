<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>당첨자 확인</title>
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


<DIV class='container' style='width: 100%;'>
<DIV class='content' style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%' ><!-- style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%' -->   
<DIV class='title_line' style="margin-bottom:50px">당첨확인
 </DIV>
  
<!--     Modal
  <div class="modal fade" id="modal_panel" role="dialog">
    <div class="modal-dialog">
    
      Modal content
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
  </div> Modal END -->
   <form name='frm' id='frm' method="get" action="./search_paging_win.do">
 <ASIDE style='float: left;'>
    <%-- <A href='../category/list.do'>게시판 목록</A> 
    >
    <A href='./search_paging.do?categoryno=${beautyVO.categoryno }'>${beautyVO.title }</A>
 --%>
    <c:if test="${param.id.length() > 0}"> 
      >
      [${param.id}] 검색 목록(${search_count_win } 건) 
    </c:if>
    
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    
    <!-- id가 어케 되는거지 일단 보류 -->
   <%--  <c:if test="${sessionScope.id ne null }">
      <span class='menu_divide' >│</span> 
      <A href='./create.do?'>등록</A>
    </c:if> --%>

     <span class='menu_divide' >│</span>
     <c:choose>
     <c:when test = " ${param.id != '' }">
     <input type = 'text' name='id' id='id' value='${param.id}' style='width:42%;'>
      <input type = 'text' name='eventno' id='eventno' value='${param.eventno}'> 
     </c:when>
     <c:otherwise>
     <input type ='text'  name = 'id' id='id' value='' placeholder='id를 입력하세요' style='width:42%;'>
     <input type = 'hidden' name='eventno' id='eventno' value='${param.eventno}'>
     </c:otherwise>
     </c:choose>
     <button type='submit' class="btn btn-primary btn-sm">검색</button>

     
  </ASIDE> 
  </form>

  
  <DIV class='menu_line' style='clear: both;'></DIV> 
<!--  <div style='width: 100%;'> -->
<ASIDE style='float: right;'>
    <!-- <button type="button" class="btn btn-primary btn-sm" 
    onclick="location.href='./create.do?managerno=1'">등록 --><!-- 여기 param으로 받거나 카테고리나 이벤트  브이오에서 받아오는걸로 바꿔랑 -->
    <!-- </button> -->
   </ASIDE>
    <table class="table table-striped" style='width: 100%;'>
        <colgroup>
        <col style="width: 13%;"></col>
        <col style="width: 13%;"></col>
        <col style="width: 14%;"></col> 
        <col style="width: 25%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 15%;"></col>
 <%--        <col style="width: 15%;"></col> --%>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>참여자번호</th>
          <th style='text-align: center;'>회원번호</th>
          <th style='text-align: center;'>이벤트번호</th>
          <th style='text-align: center;'>아이디</th>  
          <th style='text-align: center;'>회원이름</th>
          <th style='text-align: center;'>당첨여부</th>
          <!-- <th style='text-align: center;'>기타</th> -->
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="event_eventuserVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle; text-align: center;'>${event_eventuserVO.eventuserno}</td>
             <td style='vertical-align: middle; text-align: center;'>${event_eventuserVO.memberno}</td>
                <td style='vertical-align: middle; text-align: center;'>${event_eventuserVO.eventno}</td>          
             <td style='vertical-align: middle; text-align: center;'>${event_eventuserVO.id}</td>          
            <td style='vertical-align: middle; text-align: center;'>${event_eventuserVO.name}</td>
          <td style='vertical-align: middle; text-align: center;'>
           <c:choose>   
         <c:when test="${event_eventuserVO.win==1}"> 
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
    <div class='bottom_menu'>${paging_win}</div>
    <br><br>
  <!-- </div> -->

  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>

</html>
    


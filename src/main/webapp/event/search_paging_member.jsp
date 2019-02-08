<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>이벤트</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/JavaScript"
  src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">

// 삭제폼
function delete_event(eventno) {
  $.ajax({
  url: "./delete.do",
  type: "get",
  cache: false,
  async: true,
  dataType: "json",
  data: "eventno=" + eventno,
  success: function(rdata) {
/*   var title=document.getElementById("title"); */
  var delete_message = confirm(
      "삭제 되는 글 : "+ rdata.title + "\n글을 삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.\n"
  +"");
  if(delete_message) {
  delete_proc(rdata.eventno);
  } else {
  alert("글 삭제를 취소했습니다.");
  location.reload();
  }
  }
  });
  }
  // 삭제처리폼
  function delete_proc(eventno) {
  $.ajax({
  url: "./delete.do",
  type: "post",
  cache: false,
  async: true,
  dataType: "json",
  data: "eventno=" + eventno,
  success: function(rdata) {
  location.reload();
  alert(rdata.msgs);
  }
  });
  }
  function no_enter(){
    alert("응모기간이 아닙니다~!");
  }
  /* 당첨자확인 */
  function check_win(eventno){
   /*  if($('#win').val()==null){
    alert("관리자 확인중입니다.\n 잠시만 기다려주세요\n");
    }
    else{  */
      // 문자열: ', ""
      var url = './search_paging_win.do?eventno=' + eventno;
      var width = 900;
      var height = 600;
      var win = window.open(url, '당첨자확인', 'width='+width+'px, height='+height+'px');
      var x = (screen.width - width) / 2; 
      var y = (screen.height - height) / 2;
      
      win.moveTo(x, y);    
    //}
  }
</script>
</head>

<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container'>
<DIV class='content'   style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'><!-- style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%' -->   
<DIV class='title_line' style="margin-bottom:50px">이벤트응모
 </DIV>
<input type='hidden' name='managerno' id='managerno' value=''>
<c:forEach var="event_eventuserVO" items="${list }">
<%-- <input type='text' name='win' id='win' value='${event_eventuserVO.win}'> --%>
</c:forEach>
<!-- 좋아요수 증가 폼 -->
  <FORM name='frm_like1' id='frm_like1' method='post' action=''>
 <input type='hidden' name='eventno' id='eventno' value=''>
   
  </FORM>
  
    <!-- Modal -->
  <!-- <div class="modal fade" id="modal_panel" role="dialog">
    <div class="modal-dialog"> -->
    
      <!-- Modal content-->
   <!--    <div class="modal-content">
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
  </div> --> <!-- Modal END -->
   <form name='frm' id='frm' method="get" action="./search_paging_member.do">
 <ASIDE style='float: left;'>
    <%-- <A href='../category/list.do'>게시판 목록</A> 
    >
    <A href='./search_paging.do?categoryno=${beautyVO.categoryno }'>${beautyVO.title }</A>
 --%>
    <c:if test="${param.content.length() > 0}"> 
      >
      [${param.content}] 검색 목록(${search_count } 건) 
    </c:if>
    
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    
    <!-- id가 어케 되는거지 일단 보류 -->
   <%--  <c:if test="${sessionScope.id ne null }">
      <span class='menu_divide' >│</span> 
      <A href='./create.do?'>등록</A>
    </c:if> --%>
<%-- <input type='hidden' name='memberno' id='memberno' value='${param.memberno }'> --%>
     <span class='menu_divide' >│</span>
     <c:choose>
     <c:when test = " ${param.content != '' }">
     <input type = 'text' name='content' id='content' value='${param.content}' style='width:35%;'>
       <input type = 'text' name='memberno' id='memberno' value='${param.memberno}'> 
     </c:when>
     <c:otherwise>
     <input type ='text'  name = 'content' id='content' value='' style='width:35%;'>
       <input type = 'hidden' name='memberno' id='memberno' value='${param.memberno}'> 
     </c:otherwise>
     </c:choose>
     <button type='submit' class="btn btn-primary btn-sm">검색</button>
     <button type='button' class="btn btn-primary btn-sm" onclick="location.href='./search_paging_member.do?memberno=${param.memberno}'">전체보기</button>
     
  </ASIDE> 
  </form>

  
  <DIV class='menu_line' style='clear: both;'></DIV> 
<!--  <div style='width: 100%;'> -->
<input type='hidden' name='managerno' id='managerno' value='1'>
<ASIDE style='float: right;'>
    <!-- <button type="button" class="btn btn-primary btn-sm" 
    onclick="location.href='./create.do?managerno=1'">등록 --><!-- 여기 param으로 받거나 카테고리나 이벤트  브이오에서 받아오는걸로 바꿔랑 -->
    <!-- </button> -->
   </ASIDE>
    <table class="table table-striped" style='width: 100%;'>
        <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 5%;"></col>
        <col style="width: 10%;"></col> 
        <col style="width: 25%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 20%;"></col>
 <%--        <col style="width: 15%;"></col> --%>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>번호</th>
          <th style='text-align: center;'>이미지</th>
          <th style='text-align: center;'>제목</th>  
          <th style='text-align: center;'>시작일</th>
          <th style='text-align: center;'>종료일</th>
          <th style='text-align: center;'>당첨발표일</th>
          <th style='text-align: center;'>확인</th>
          <!-- <th style='text-align: center;'>기타</th> -->
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="eventVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle; text-align: center;'>${eventVO.rdate.substring(0, 10)}</td>
             <td style='vertical-align: middle; text-align: center;'>${eventVO.eventno}</td>
                 <td style='vertical-align: middle; text-align: center;'>
              <c:choose>
                <c:when test="${eventVO.thumb != ''}">
                  <IMG id='thumb' src='./storage/${eventVO.thumb}' > <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <IMG id='thumb' src='./images/none2.png' style='width: 120px; height: 80px;'> <!-- 파일이 존재하지 않는 경우 -->
                  </c:otherwise>
              </c:choose>
              </td>          
            <td style='vertical-align: middle; text-align: center;'>
            <!-- 날짜가 지났을 때 클릭시 응모기간이 아닙니다! 뜨도록 -->
          <jsp:useBean id="toDay" class="java.util.Date" />
         <fmt:formatDate value='${toDay}' pattern='yyyy-MM-dd' var="nowDate"/>
         <%--  <input type = 'text' name='nowDate' id='nowDate' value='${nowDate}' style='width:35%;'> --%>
            <c:choose>   
         <c:when test="${eventVO.period_end<nowDate}"> 
            <A href="javascript:no_enter();">${eventVO.title}</A>
         </c:when>
         <c:otherwise>
            <a href="./read_member.do?eventno=${eventVO.eventno}&memberno=${param.memberno}">${eventVO.title}</a> 
         </c:otherwise>
    </c:choose>
            <!--  -->
        
            </td> 
            <td style='vertical-align: middle; text-align: center;'>${eventVO.period_start}</td>
           <td style='vertical-align: middle; text-align: center;'>${eventVO.period_end}</td>
          <td style='vertical-align: middle; text-align: center;'>${eventVO.windate}</td>
            <%-- <td style='vertical-align: middle; text-align: center;'><!-- 뷰티카테고리를 1번이라 임시지정, 마지막에 조인할 때 managerno도 들어가서 값 받을 수 있게끔 수정필요. -->
             <a href="./create.do?managerno=${eventVO.managerno}"><img src="./images/create.png" title="등록" border='0' style='width: 20px; height: 20px;'/></a>
              <a href="./update.do?eventno=${eventVO.eventno}&managerno=${eventVO.managerno}"><img src="./images/update.png" title="수정" border='0' style='width: 20px; height: 20px;'/></a>
               <A href="javascript:delete_event(${eventVO.eventno});"><img src="./images/delete.png" title="삭제"  border='0' style='width: 20px; height: 20px;'/></a>
            </td> --%>
            <td style='vertical-align: middle; text-align: center;'>
            <jsp:useBean id="toDay2" class="java.util.Date" />
         <fmt:formatDate value='${toDay2}' pattern='yyyy-MM-dd' var="nowDate2"/>
                <%--  <input type = 'text' name='nowDate2' id='nowDate2' value='${nowDate2}' style='width:35%;'> --%>
            <c:choose>   
         <c:when test="${eventVO.period_end<nowDate2}"> <!-- 나중에 수정. 멋들어지는 이미지로 -->
           <button type="button" class="btn btn-danger btn-sm" style='width:65px;'>종료</button>
            
         </c:when>
         <c:otherwise>
           <button class="btn btn-primary btn-sm" style='width:65px;'>진행중</button>
         </c:otherwise>
    </c:choose>
       <c:choose>   
         <c:when test="${eventVO.windate<=nowDate2}"> <!-- 나중에 수정. 멋들어지는 이미지로 -->
         <!-- 이벤트 번호별로 당첨자 확인 -->
          <button type="button" class="btn btn-warning btn-sm" onclick="location.href='javascript:check_win(${eventVO.eventno});'">당첨자 확인</button>
         </c:when>
         <c:otherwise>
           <button type="button" class="btn btn-outline-warning btn-sm" ><!-- onclick="location.href='javascript:check_win();'" -->당첨자 확인불가</button>
         </c:otherwise>
    </c:choose>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class='bottom_menu'>${paging_member}</div>
    <br><br>
  <!-- </div> -->

  
</DIV> <!-- content END -->
</DIV> <!-- container END -->
    
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
    


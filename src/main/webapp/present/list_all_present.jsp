<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>이벤트선물</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/JavaScript"
  src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">

// 삭제폼
function delete_present(presentno) {
  $.ajax({
  url: "./delete.do",
  type: "get",
  cache: false,
  async: true,
  dataType: "json",
  data: "presentno=" + presentno,
  success: function(rdata) {
/*   var title=document.getElementById("title"); */
  var delete_message = confirm(
      "선물명 : "+ rdata.info + "\n 삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.\n"
  +"");
  if(delete_message) {
  delete_proc(rdata.presentno);
  } else {
  alert("선물 삭제를 취소했습니다.");
  location.reload();
  }
  }
  });
  }
  // 삭제처리폼
  function delete_proc(presentno) {
  $.ajax({
  url: "./delete.do",
  type: "post",
  cache: false,
  async: true,
  dataType: "json",
  data: "presentno=" + presentno,
  success: function(rdata) {
  location.reload();
  alert(rdata.msgs);
  }
  });
  }

/* //수정폼
   function update_present(presentno) {
    // 문자열: ', ""
    var url = './update.do?presentno=' + presentno;
    var width = 900;
    var height = 500;
    var win = window.open(url, '선물 정보 변경', 'width='+width+'px, height='+height+'px');
    var x = (screen.width - width) / 2; 
    var y = (screen.height - height) / 2;
    
    win.moveTo(x, y);
  }   */
  
</script>
</head>

<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->

<DIV class='container'>
<DIV class='title_line'>이벤트선물
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
<%-- <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
     <span class='menu_divide' >│</span>
     <A href='./search_paging.do?categoryno=${param.categoryno}'>검색 창 생성</A>
  </ASIDE> --%>
    <button type="button" class="btn btn-primary btn-sm" style='margin-left: 95%;'
    onclick="location.href='./create.do'">등록</button>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 5%;"></col>
        <col style="width: 60%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>번호</th>
          <th style='text-align: center;'>선물내용</th>  
          <th style='text-align: center;'>만료일</th>  
          <th style='text-align: center;'>이벤트이동</th>  <!-- 아마 여기에 선물번호로 연결해줘야 할것같음.. -->
          <th style='text-align: center;'>기타</th>  
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="presentVO" items="${list }">
          <tr> 
             <td style='vertical-align: middle; text-align: center;'>${presentVO.presentno}</td>
            <td style='vertical-align: middle;'>${presentVO.info}</td>
            <td style='vertical-align: middle;'>${presentVO.end_date}</td>
            <td style='vertical-align: middle;'>
            <a href="../event/search_paging.do?presentno=${presentVO.presentno}"><img src="./images/move.png" title="이동" border='0' style='width: 20px; height: 20px;'/></a></td>
            <td style='vertical-align: middle;'><!-- 뷰티카테고리를 1번이라 임시지정, 마지막에 조인할 때 managerno도 들어가서 값 받을 수 있게끔 수정필요. -->
             <a href="./create.do"><img src="./images/create.png" title="등록" border='0' style='width: 20px; height: 20px;'/></a>
              <a href="./update.do?presentno=${presentVO.presentno}"><img src="./images/update.png" title="수정" border='0' style='width: 20px; height: 20px;'/></a>
               <A href="javascript:delete_present(${presentVO.presentno});"><img src="./images/delete.png" title="삭제"  border='0' style='width: 20px; height: 20px;'/></a>
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
    


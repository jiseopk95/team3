<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>미용스타일</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/JavaScript"
  src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">

// 삭제폼
function delete_style(styleno,categoryno) {
  $.ajax({
  url: "./delete.do",
  type: "get",
  cache: false,
  async: true,
  dataType: "json",
  data: "styleno=" + styleno + "&categoryno=" + categoryno,
  success: function(rdata) {
/*   var title=document.getElementById("title"); */
  var delete_message = confirm(
      "삭제 되는 글 : "+ rdata.title + "\n글을 삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.\n"
  +"");
  if(delete_message) {
  delete_proc(rdata.styleno,rdata.categoryno);
  } else {
  alert("글 삭제를 취소했습니다.");
  location.reload();
  }
  }
  });
  }
  // 삭제처리폼
  function delete_proc(styleno,categoryno) {
  $.ajax({
  url: "./delete.do",
  type: "post",
  cache: false,
  async: true,
  dataType: "json",
  data: "styleno=" + styleno +"&categoryno=" + categoryno,
  success: function(rdata) {
  location.reload();
  alert(rdata.msgs);
  }
  });
  }

</script>
</head>

<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->

<DIV class='container'>
<DIV class='title_line'>이벤트
 </DIV>
<DIV class='content'  style='width: 100%; pagging-top:5%; padding-bottom:10%'>
<!-- 좋아요수 증가 폼 -->
  <FORM name='frm_like1' id='frm_like1' method='post' action=''>
 <input type='hidden' name='styleno' id='styleno' value=''>
    
  </FORM>
  
  <!--   <!-- Modal -->
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
  </div>  --><!-- Modal END -->
  
<!--   <DIV class='menu_line' style='clear: both;'></DIV>  -->
  <div style='width: 100%;'>
<input type='hidden' name='managerno' id='managerno' value='1'>
<ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
     <%-- <span class='menu_divide' >│</span>
     <A href='./search_paging.do?categoryno=${param.categoryno}'>검색 창 생성</A> --%>
  </ASIDE>
    <button type="button" class="btn btn-primary btn-sm" style='margin-left: 95%;'
    onclick="location.href='./create.do?managerno=${param.managerno}'">등록</button>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 5%;"></col>
        <col style="width: 10%;"></col> 
        <col style="width: 30%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th>번호</th>
          <th>이미지</th>
          <th style='text-align: center;'>제목</th>  
          <th>시작일</th>
          <th>종료일</th>
          <th>당첨발표일</th>
          <th>기타</th>
        </tr>
      
      </thead>
      
      
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="beautyVO" items="${list }">
          <tr> 
            <td style='vertical-align: middle; text-align: center;'>${beautyVO.rdate.substring(0, 10)}</td>
             <td style='vertical-align: middle; text-align: center;'>${beautyVO.styleno}</td>
                 <td style='vertical-align: middle;'>
              <c:choose>
                <c:when test="${beautyVO.thumb != ''}">
                  <IMG id='thumb' src='./storage/${beautyVO.thumb}' > <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <IMG id='thumb' src='./images/none2.png' style='width: 120px; height: 80px;'> <!-- 파일이 존재하지 않는 경우 -->
                  </c:otherwise>
              </c:choose>
              </td>          
            <td style='vertical-align: middle;'>
              <a href="./read.do?styleno=${beautyVO.styleno}&categoryno=${beautyVO.categoryno}">${beautyVO.title}</a> 
            </td> 
            <td style='vertical-align: middle;'>${beautyVO.rname}</td>
           <td style='vertical-align: middle;'>${beautyVO.cnt}</td>
           <td style='vertical-align: middle;'>
           <A href="javascript:increaseLike1(${beautyVO.styleno});">
           <img src="./images/like.png" title="좋아요" border='0' style='width: 20px; height: 20px;'
           /> </A>${beautyVO.like1}</td>
            <td style='vertical-align: middle;'><!-- 뷰티카테고리를 1번이라 임시지정, 마지막에 조인할 때 managerno도 들어가서 값 받을 수 있게끔 수정필요. -->
             <a href="./create.do?categoryno=${beautyVO.categoryno}&managerno=${beautyVO.managerno}"><img src="./images/create.png" title="등록" border='0' style='width: 20px; height: 20px;'/></a>
              <a href="./update.do?styleno=${beautyVO.styleno}&categoryno=${beautyVO.categoryno}&managerno=${beautyVO.managerno}"><img src="./images/update.png" title="수정" border='0' style='width: 20px; height: 20px;'/></a>
               <A href="javascript:delete_style(${beautyVO.styleno},${beautyVO.categoryno});"><img src="./images/delete.png" title="삭제"  border='0' style='width: 20px; height: 20px;'/></a>
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>

  
</DIV> <!-- content END -->
</DIV> <!-- container END -->


<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
    


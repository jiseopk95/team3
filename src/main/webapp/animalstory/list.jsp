<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>애니멀스토리</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
function deleteOne(anino) {
  $.ajax({
    url: "./delete.do",
    type: "get",
    cache: false,
    async: true,
    dataType: "json",
    data: "anino=" + anino,
    success: function(rdata) {
      var delete_check = confirm("아래 글을 삭제하시겠습니까?\n"
          																	+"-----------------------------------------------------"
                                  					+ "\n제목 : " + rdata.title
                                  					+ "\n작성자 : " + rdata.manager
                                  					+ "\n작성일 : " + rdata.rdate);
      
      if(delete_check) {
        delete_proc(rdata.anino);
      } else {
        alert("사용자가 삭제를 취소했습니다.");
        location.reload();
      }
  	},
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      console.log("request : " + request.status
										+"\n error : " + error);
    }
  });
}

function delete_proc(anino) {
  $.ajax({
    url: "./delete.do",
    type: "post",
    cache: false,
    async: true,
    dataType: "json",
    data: "anino=" + anino,
    success: function(rdata) {
      location.reload();
      alert(rdata.msgs);
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      console.log("request : " + request.status
										+"\n error : " + error);
    }
  });
}

function update(anino) {
  
  var url = './update.do?anino=' + anino; 
  var win = window.open(url, '애니멀스토리 글수정', 'width=1000px, height=1000px');
  
  var x = (screen.width - 500) / 2; // 1000 - 500 = 500 / 2 = 250
  var y = (screen.height - 350) / 2; // 800 - 350 = 450 / 2 = 225
  
  win.moveTo(x, y); //  화면을 가운데로 이동
}
</script>
<style type="text/css">
/* 출력되는 A 태그 기본 모양 */
.content a:link{ 
  text-decoration: none !important; /* 밑줄 삭제 */
  color: #000000;
}
/* A 태그에 마우스가 위치했을 때 */
.content a:hover{
  text-decoration: none !important;
  color: #0080ff;
  color: #000000;
  font-weight: bold;
}
/* A 태그가 클릭된적이 있는 경우의 모양 */
.content a:visited {
  text-decoration: none !important;
  color: #000000;
}
#month_table{
  margin-top: 30px;
  margin-bottom: 70px;
  width: 50%; 
  
}
#tool-tip {
  font-weight: bold;
  
  margin-bottom: 5px;
}
</style>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' >
<DIV class='content'  style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'>
<div class="title_line">${title }</div>
<div class='buttons'>
  <button class='btn btn-primary btn-sm' onclick='location.href="./create.do?managerno=${managerno}"'>글 등록하기</button>
  <button class='btn btn-primary btn-sm' onclick='location.href="./list_anitype.do?nowPage=&anitype=1&content="'>강아지</button>
  <button class='btn btn-primary btn-sm' onclick='location.href="./list_anitype.do?nowPage=&anitype=2&content="'>고양이</button>
</div>
<form name='frm' id='frm' method="get" action="./list.do">
  <input type="hidden" name="managerno" value="${managerno}">
  <div style='float: right;'>
  
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <c:choose>
      <c:when test="${param.content != '' }">
        <input type='text' name='content' id='content' value='${param.petname }' placeholder="내용검색" style='width: 25%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='content' id='content' value='${param.petname }' placeholder="내용검색" style='width: 25%;'>
      </c:otherwise>
    </c:choose>
    <button type='submit' class='btn btn-primary btn-sm'>검색</button>
    <button type='button' class='btn btn-primary btn-sm'
                 onclick="location.href='./list.do?content='">전체 보기</button>
  </div>
</form>
  <TABLE class='table'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 25%;'/>
      <col style='width: 15%;'/>
      <col style='width: 10%;'/>
      <col style='width: 15%;'/>
      <col style='width: 10%;'/>
      <col style='width: 15%;'/>
    </colgroup>
    <thead>  
      <tr>
        <th style='text-align: center ;'>글번호</th>
        <th style='text-align: center ;'></th>
        <th style='text-align: center ;'>제목</th>
        <th style='text-align: center ;'>작성자</th>
        <th style='text-align: center ;'>작성일</th>
        <th style='text-align: center ;'>조회수</th>
        <th style='text-align: center ;'>기타</th>
      </tr>
    </thead>
    <tbody id='tbody_panel' data-nowPage='0' data-endPage='0'>
      <c:forEach var="aniVO" items="${list }">
        <tr>
          <td style='vertical-align: middle; text-align: center ;'>${aniVO.anino }</td><!-- 글번호 -->
          <td style='vertical-align: middle; text-align: center ;'>
            <c:choose>
                <c:when test="${aniVO.thumb != ''}">
                  <IMG id='thumb' src='./storage/${aniVO.thumb}' > <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <IMG id='thumb' src='./images/none1.jpg' style='width: 120px; height: 80px;'> <!-- 파일이 존재하지 않는 경우 -->
                  </c:otherwise>
              </c:choose>
          </td><!-- 썸네일 -->
          <td style='vertical-align: middle; text-align: center ;'><a href='./read.do?anino=${aniVO.anino }'>${aniVO.title }</a></td><!-- 제목 -->
          <td style='vertical-align: middle; text-align: center ;'>${aniVO.manager }</td><!-- 작성자 (관리자번호로 select해오기) -->
          <td style='vertical-align: middle; text-align: center ;'>${aniVO.rdate }</td><!-- 작성일 -->
          <td style='vertical-align: middle; text-align: center ;'>${aniVO.cnt }</td><!-- 조회수 -->
          <td style='vertical-align: middle; text-align: center ;'>
            <a href="javascript:update(${aniVO.anino });"><img alt="수정이미지" src="./images/update.png" title="수정" style="width:20px; height:20px;"></a>
            <a href="javascript:deleteOne(${aniVO.anino })"><img alt="삭제이미지" src="./images/delete.png" title="삭제" style="width:20px; height:20px;"></a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </TABLE>
  <DIV class='bottom_menu'>${paging }</DIV>
  <br><br>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>




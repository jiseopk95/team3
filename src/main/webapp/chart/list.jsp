<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>차트 리스트</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(function() {
  $('#tool-tip').hide();
  $('#petname').on('click', function() {
    $('#tool-tip').show();
    $('#search').css('border', 'solid 2px orange')
    							.css('border-radius', '10px');
  });
	$('#name').on('click', function() {
	  $('#tool-tip').show();
    $('#search').css('border', 'solid 2px orange')
    							.css('border-radius', '10px');
  });
})

function deleteOne(chartno) {

  $.ajax({
    url: "./delete.do",
    type: "get",
    cache: false,
    async: true,
    dataType: "json",
    data: "chartno=" + chartno,
    success: function(rdata) {
      var delete_check = confirm(rdata.petname + "의 차트를 삭제하시겠습니까?\n"
          																	+"-----------------------------------------------------"
                                  					+ "\n차트명 : " + rdata.title
                                  					+ "\n주인명 : " + rdata.name
                                  					+ "\n병명 : " + rdata.sick
                                  					+ "\n등록날짜 : " + rdata.rdate);
      
      if(delete_check) {
        delete_proc(rdata.chartno);
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

function delete_proc(chartno) {
  $.ajax({
    url: "./delete.do",
    type: "post",
    cache: false,
    async: true,
    dataType: "json",
    data: "chartno=" + chartno,
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

function update(chartno, petno) {
  
  var url = './update.do?chartno=' + chartno + '&petno=' + petno; 
  var win = window.open(url, '차트 수정', 'width=1000px, height=1000px');
  
  var x = (screen.width - 500) / 2; // 1000 - 500 = 500 / 2 = 250
  var y = (screen.height - 350) / 2; // 800 - 350 = 450 / 2 = 225
  
  win.moveTo(x, y); //  화면을 가운데로 이동
}

function check() {
  if($('#petname').val() == "" || $('#name').val() == "") {
    alert("보호자명과 동물이름을 모두 검색해주십시오.");
  } else {
    $('#frm').submit();
  }
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
<form name='frm' id='frm' method="get" action="./list.do">
  <input type="hidden" name="managerno" value="${managerno}">
  
  <div style='float: right;'>
  
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <c:choose>
      <c:when test="${param.petname != '' && param.name != '' }">
        <input type='text' name='petname' id='petname' value='${param.petname }' placeholder="동물 이름" style='width: 25%;'>
        <input type='text' name='name' id='name' value='${param.name }' placeholder="보호자 성명" style='width: 25%;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='petname' id='petname' value='${param.petname }' placeholder="동물 이름" style='width: 25%;'>
        <input type='text' name='name' id='name' value='${param.name }' placeholder="보호자 성명" style='width: 25%;'>
      </c:otherwise>
    </c:choose>
    <button type='button' class='btn btn-primary btn-sm' onclick="check();">검색</button>
    <button type='button' class='btn btn-primary btn-sm'
                 onclick="location.href='./list.do?managerno=${managerno }'">전체 보기</button>
  </div>
</form>
  <TABLE class='table'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 15%;'/>
      <col style='width: 15%;'/>
      <col style='width: 25%;'/>
      <col style='width: 15%;'/>
      <col style='width: 10%;'/>
      <col style='width: 15%;'/>
    </colgroup>
    <thead>  
      <TR>
        <TH style='text-align: center ;'>번호</TH>
        <TH style='text-align: center ;'>동물명</TH>
        <TH style='text-align: center ;'>보호자명</TH>
        <TH style='text-align: center ;'>제목</TH>
        <TH style='text-align: center ;'>증상</TH>
        <TH style='text-align: center ;'>등록일</TH>
        <TH style='text-align: center ;'>기타</TH>
      </TR>
    </thead>
    <tbody id='tbody_panel' data-nowPage='0' data-endPage='0'>
      <c:forEach var="chartVO" items="${list }">
        <tr>
          <td style='vertical-align: middle; text-align: center ;'>${chartVO.chartno }</td>
          <td style='vertical-align: middle; text-align: center ;'>${chartVO.petname }</td>
          <td style='vertical-align: middle; text-align: center ;'>${chartVO.name }</td>
          <td style='vertical-align: middle; text-align: center ;'><a href="./read.do?chartno=${chartVO.chartno }&petno=${chartVO.petno}&managerno=${managerno }">${chartVO.title }</a></td>
          <td style='vertical-align: middle; text-align: center ;'>${chartVO.sick }</td>
          <td style='vertical-align: middle; text-align: center ;'>${chartVO.rdate }</td>
          <td style='vertical-align: middle; text-align: center ;'>
            <a href="./create.do?petno=${chartVO.petno }&managerno=${managerno }"><img alt="등록이미지" src="./images/create.png" title="새로운 차트 등록" style="width:20px; height:20px;"></a>
            <a href="javascript:update(${chartVO.chartno }, ${chartVO.petno });"><img alt="수정이미지" src="./images/update.png" title="수정" style="width:20px; height:20px;"></a>
            <a href="javascript:deleteOne(${chartVO.chartno })"><img alt="삭제이미지" src="./images/delete.png" title="삭제" style="width:20px; height:20px;"></a>
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




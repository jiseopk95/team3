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
function deleteOne(chartno) {
  var chartno1 = chartno
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
</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' >
<DIV class='content'  style='width: 80%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'>
<div class="title_line">${title }</div>
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
          <td style='vertical-align: middle; text-align: center ;'><%-- <a href="./read.do?reservationno=${chartVO.reservationno }"> --%>${chartVO.title }<!-- </a> --></td>
          <td style='vertical-align: middle; text-align: center ;'>${chartVO.sick }</td>
          <td style='vertical-align: middle; text-align: center ;'>${chartVO.rdate }</td>
          <td style='vertical-align: middle; text-align: center ;'>
            <a href="javascript:update();"><img alt="수정이미지" src="./images/update.png" title="수정" style="width:20px; height:20px;"></a>
            <a href="javascript:deleteOne(${chartVO.chartno })"><img alt="삭제이미지" src="./images/delete.png" title="삭제" style="width:20px; height:20px;"></a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </TABLE>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>




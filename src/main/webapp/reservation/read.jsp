<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>예약내용</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
/* 모든 TABLE 태그에 적용됨. */
table{
  width: 100%;
  margin: 0px auto;
  border: none; /* 선 스타일 실선 지정*/
  border-collapse: collapse; /* TH, TD의 선을 단일선으로 병합 */ 
  margin-left: auto;
  margin-right:auto;
}
td {
  border: 0px;
  margin-top: 0px;
  padding-top: 0px; 
  border-width: 1.5px;    /* 태그의 위쪽선 두께*/ 
  border-style:solid;    /* 실선*/
  border-color:#d9d9d9;  /* 선의 색깔 */
  border-left: none;
  border-right: none;
  color: #3e3e3e;        /* 제목*/ 
  height: 70px;
  font-size: 15px;
}

.table {
  width: 100%;
}
.title {
  font-weight: bold;
}
.top {
  border-top: none !important;
}
.table tr td{
  border-top:none !important;
}
</style>
<script type="text/javascript">
  function exit() {
    window.close();
  }
</script>
</head>
<body>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' >
<DIV class='content' style='width: 80%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'>
<div class="title_line">예약내용</div>
  <TABLE class='table'>
    <colgroup>
    <col style='width: 35%;'/>
    <col style='width: 65%;'/>
  </colgroup>
    <tbody id='tbody_panel' data-nowPage='0' data-endPage='0'>
        <tr>
          <td class='title'  class='top'  style='vertical-align: middle; text-align: center ;'>예약</td>
          <td class='top'  style='vertical-align: middle; text-align: center ;'>${reservationVO.restype }</td>
        </tr>
        <tr>
          <td class='title' style='vertical-align: middle; text-align: center ;'>예약명</td>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.title }</td>
        </tr>
        <tr>
          <td class='title' style='vertical-align: middle; text-align: center ;'>동물이름</td>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.name }</td>
        </tr>
        <tr>
          <td class='title' style='vertical-align: middle; text-align: center ;'>예약일</td>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.resdate }</td>
        </tr>
        <tr>
          <td class='title' style='vertical-align: middle; text-align: center ;'>예약시간</td>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.restime }</td>
        </tr>
        <tr>
          <td class='title' style='vertical-align: middle; text-align: center ;'>예약내용</td>
          <td style='vertical-align: middle; text-align: center ;'>${reservationVO.content }</td>
        </tr>
    </tbody>
  </TABLE>
  <br>
  <DIV style='text-align: right;'>
    <button type="button" class="btn btn-primary" onclick="exit();">닫기</button>
  </DIV>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
</body>
</html>




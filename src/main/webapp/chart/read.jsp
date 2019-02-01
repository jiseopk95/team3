<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>${title }</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function() {
  var gender = "${petVO.gender}";
  var neutralization = "${petVO.neutralization}";
  // 불러온 pet의 데이터에서 성별에 따라 radio버튼에 체크돼라 
  if(gender == "수컷") {
    $('#gender_M').attr("checked", true);
  } else if(gender == "암컷") {
    $('#gender_F').attr("checked", true);
  }
//불러온 pet의 데이터에서 중성화 여부에 따라 radio버튼에 체크돼라 
  if(neutralization == "Y") {
    $('#neutralization_Y').attr("checked", true);
  } else if(neutralization == "N") {
    $('#neutralization_N').attr("checked", true);
  }
});
function check() {
  if($('radio[name=stay]').val() == "" ) {
   	alert("예약유형을 선택해주세요");
   	$('radio[name=stay]').focus();
  } else if($('#sick').val() == "") {
    alert("병명을 기입해주세요");
    $('#sick').focus();
  } else if($('#medicine').val() == "") {
    alert("처방약을 작성해주세요");
    $('#medicine').focus();
  } else {
    $('#frm').submit();
  }
};
</script>
</head>
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container'>
<DIV class='content' style='width: 100%; margin:0px auto; margin-top: 10%; margin-bottom: 10%'>
<div class="title_line">${title }</div>
<div style="float: right;">작성일 : ${chartVO.rdate}</div>
  <form name='frm' id='frm' method='POST' action='./create.do'>
    <input type="hidden" name="petno" value="${petVO.petno }">
    <input type="hidden" name="memberno" value="${petVO.memberno }">
    <input type="hidden" name="managerno" value="${managerno}">
    
    <fieldset id="info">
      <legend>기본사항</legend>
      
      <table class="chart_table" style='width: 100%; margin:0px auto; '>
        <tr>
          <th>제목 :</th>
          <td>${chartVO.title}</td>
          <th>보호자명 :</th>
          <td>${chartVO.name }</td>
          <th>보호자 전화번호 :</th>
          <td>${chartVO.phone }</td>
        </tr>
        <tr>
          <th>동물이름 :</th>
          <td>${chartVO.petname }</td>
          <th>성별 :</th>
          <td>${petVO.gender }</td>
          <th>나이 :</th>
          <td>${petVO.age }</td>
        </tr>
        <tr>
          <th>품종 :</th>
          <td>${petVO.pet_type }</td>
          <th>몸무게 :</th>
          <td>${petVO.weight }kg</td>
          <th>중성화 :</th>
          <td>${petVO.neutralization }</td>
        </tr>
      </table>
    </fieldset>
  
    <fieldset id="contents">
      <legend>진료내용</legend>
      <table class="chart_table" >  
        <tr>
          <th>병명 :</th>
          <td>${chartVO.sick }</td>
        </tr>
        <tr>
          <th>입원 :</th>
          <td>${chartVO.stay }</td>
        </tr>
        <tr>
          <th>약처방 :</th>
          <td>${chartVO.medicine }</td>
        </tr>
      </table>
    </fieldset>
    
    <fieldset id="etc">
      <legend>진료사항 기록</legend>
      <DIV>
      ${chartVO.etc }
      </DIV>
    </fieldset>
    <button type="button" class='btn btn-primary btn-sm' onclick="location.href='./list.do?managerno=${managerno}'" style="float: right;">뒤로가기</button>
  </form>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>




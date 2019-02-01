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
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/javascript">
window.onload=function(){
  CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
};

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
   	alert("입원 여부를 선택해주세요");
   	$('radio[name=stay]').focus();
  } else if($('#title').val() == "") {
    alert("제목을 작성해주세요");
    $('#title').focus(); 
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

  <form name='frm' id='frm' method='POST' action='./create.do'>
    <input type="hidden" name="petno" value="${petVO.petno }">
    <input type="hidden" name="memberno" value="${petVO.memberno }">
    <input type="hidden" name="managerno" value="${managerno}">
    
    <fieldset id="info">
      <legend>기본사항</legend>
      <table class="chart_table">
        <tr>
          <th>제목</th>
          <td><input type='text' class='title' name='title' id="title" value=''></td>
          <th>보호자명</th>
          <td><input type='text' class='name' name='name' value='${memberVO.name }' ></td>
          <th>보호자 전화번호</th>
          <td><input type='text' class='phone' name='phone' value='${memberVO.phone }' ></td>
        </tr>
        <tr>
          <th>동물이름</th>
          <td><input type='text' class='petname' name='petname' value='${petVO.name }' ></td>
          <th>성별</th>
          <td>
            <input type='radio' class='gender' name='gender' id='gender_M' value='M' >남
            <input type='radio' class='gender' name='gender' id='gender_F' value='F' >여  
          </td>
          <th>나이</th>
          <td><input type='text' class='age' name='age' id='age' value='${petVO.age }' ></td>
        </tr>
        <tr>
          <th>품종</th>
          <td><input type='text' class='pet_type' name='pet_type' value='${petVO.pet_type }' ></td>
          <th>몸무게</th>
          <td><input type='text' class='weight' name='weight' id='weight' value='${petVO.weight }'>kg</td>
          <th>중성화</th>
          <td>
            <input type='radio' class='neutralization' id='neutralization_Y' name='neutralization' value='Y'>Y
            <input type='radio' class='neutralization' id='neutralization_N' name='neutralization' value='N' >N
          </td>
        </tr>
      </table>
    </fieldset>
  
    <fieldset id="contents">
      <legend>진료내용</legend>
      <table class="chart_table">
        <tr>
          <th>병명</th>
          <td><input type='text' class='sick' id='sick' name='sick' value='감기' ></td>
        </tr>
        <tr>
          <th>입원</th>
          <td>
            <input type='radio' class='stay' name='stay' value='Y'>Y
            <input type='radio' class='stay' name='stay' value='N' checked>N
          </td>
        </tr>
        <tr>
          <th>약처방</th>
          <td><input type='text' class='medicine' id='medicine' name='medicine' value='감기약'></td>
        </tr>
      </table>
    </fieldset>
    
    <fieldset id="etc">
      <legend>진료사항 기록</legend>
      <textarea rows="20" cols="100" class="content" id='content' name="etc" >
      특별한 내용 없음
      </textarea>
    </fieldset>
    <button type="button" class="btn btn-primary" onclick="location.href='./list.do?managerno=${managerno}'"  style="float: right;">취소</button>
    <button type="button" class="btn btn-primary" onclick="check();" style="float: right; margin-right: 1%;">등록</button>
  </form>
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>



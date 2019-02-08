<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Pet Doctor</title> <!-- 주소창 타이틀 -->

<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<title></title> 


<style>
/*   table, th, td {
    border: 3px solid #bcbcbc;
  } */
  table {
    width: 700px;
    height: 200px;
    margin-left: auto;
    margin-right: auto;
  }

</style>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<script type="text/javascript">
$(function(){
  $('#btn_send').on('click', submit_proc); // click 이벤트 등록
  });

function submit_proc(surveyitemno) {
  var frm_submit = $('#frm_submit');
  frm_submit.attr('action', './submit_proc.do');
  var radioVal = $('input[name="sradio"]:checked').val();
/*   alert(radioVal); */
  $('#surveyitemno', frm_submit).val(parseInt(radioVal));
  console.log(typeof(surveyitemno));
  console.log(typeof(surveyno));

  frm_submit.submit();
  alert("제출에 성공하였습니다.")
}

</script>

</head> 

<body>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' style='width:80%; margin:0px auto; text-align: center; margin-top: 5%; margin-bottom:5%' >

<DIV class='content'>

  <FORM name='frm_submit' id='frm_submit' method='post' action=''>
    <input type='hidden' name='surveyitemno' id='surveyitemno' value=''>
    <input type='hidden' name='surveyno' id='surveyno' value='${surveyVO.surveyno}'>
    <input type='hidden' name='memberno' id='memberno' value='${memberno}'>

    
  </FORM>
  


  <DIV id='main_panel'></DIV>

  <DIV class='title_line' style="width:50%;">${surveyVO.survey_title}</DIV>


<TABLE class='table table-hover' >
  <colgroup>
    <col style='width:10%;'/> 
    <col style='width: 10%;'/> 
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
  </colgroup>

  <thead>  
  <TR>
    <TH style='text-align: center ;'>번호</TH>
    <TH style='text-align: left ;'>사진</TH>
    <TH style='text-align: left ;'>Question</TH>
    <TH style='text-align: center ;'>기타</TH>    
  </TR>
  </thead>
  <c:forEach var="survey_itemVO" items="${list }">
  <TR>
    <TD style='text-align: center ;'>${survey_itemVO.surveyitemno}</TD>  
    
    <td style='vertical-align: middle;'>
              <c:choose>
            
                <c:when test="${survey_itemVO.thumb != ''}">
                  <IMG id='thumb' src='./storage/${survey_itemVO.thumb}' style='width:100px; height: 100px; float:middle;'> <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <IMG id='thumb' src='./images/none1.jpg' style='width: 150px; height: 100px;'> <!-- 파일이 존재하지 않는 경우 -->
                  </c:otherwise>
              </c:choose>
      </td>      
    
      <TD style='text-align: center;'>${survey_itemVO. question}</TD>
    
    <TD style='text-align: center ;'>
       <input type="radio"  name='sradio' id="sradio" value='${survey_itemVO.surveyitemno}'>
      
    </TD>
  </TR>
  </c:forEach> 

</TABLE>
  <button type="button" class='btn btn-primary'  id="btn_send" name="btn_send()" style="display:block;margin-left:auto;margin-right:auto;width:50%;">제출하기</button>
</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
</body>
        

</html> 
 
 
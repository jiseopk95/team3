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

  <DIV id='main_panel'></DIV>

  <DIV id='main_panel'></DIV>
  <aside style="float: center; font-weight: bold; font-size:35px; ">참여자 목록</aside>

  <DIV class='title_line' style="width:30%;">
 <span style="font-size:18px;font-weight:bold;">${surveyVO.survey_title}<br></span>
  <br>
  </DIV>

     <ASIDE style='float: left;'>
    <A href="../survey/list_by_manager_search_paging.do?managerno=${surveyVO.managerno}"><IMG src='./images/back.png' style="width:40px;height:40px;"></A>
    <!-- <A href='./create.jsp'>등록</A> -->
    </ASIDE> 

<TABLE class='table table-hover' >
  <colgroup>
    <col style='width:5%;'/> 
    <col style='width: 10%;'/> 
    <col style='width: 10%;'/> 
    <col style='width: 15%;'/>
  </colgroup>

  <thead>  
  <TR>
    <TH style='text-align: center ;'>번호</TH>
    <TH style='text-align: center ;'>회원 id</TH>
    <TH style='text-align: center;'>회원 이름</TH>
    <TH style='text-align: center ;'>응답한 답</TH> 
  </TR>
  </thead>
  <c:forEach var="survey_partyVO" items="${list_survey}">
  <TR>
    <TD style='text-align: center ;'>${survey_partyVO.surveypartyno}</TD>  
    <TD style='text-align: center ;'>${survey_partyVO.id}</TD>  
    <TD style='text-align: center ;'>${survey_partyVO.name}</TD>  
    <TD style='text-align: center ;'>${survey_partyVO.question}</TD>  
    
    
  
    

  </TR>
  </c:forEach> 

</TABLE>
  
</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
</body>
        

</html> 
 

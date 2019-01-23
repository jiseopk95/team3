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
    <!-- <link href="./css/style.css" rel='Stylesheet' type='text/css'> -->
    <!-- Bootstrap core CSS -->
    <link href=" ${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href=" ${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- Plugin CSS -->
    <link href=" ${pageContext.request.contextPath}/resources/vendor/magnific-popup/magnific-popup.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href=" ${pageContext.request.contextPath}/resources/css/freelancer.min.css" rel="stylesheet">
   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

   <!-- Bootstrap -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<script type="text/javascript">

$(function() {

 $('#panel_create').hide();
 $('#btn_create').on('click', create_form);
 $('#main_panel').hide();
 $('#panel_update').hide();
 
});

function create_form(){
  $('#panel_create').show();
  $('#btn_create').hide();
  $('#frm_find').hide();
}

//등록 처리
function create_proc() {
  $('#panel_create').hide();
  $('#btn_create').hide();
  $.ajax({
    url: "./create_json.do", // 요청을 보낼주소
    type: "post",  // or get
    cache: false,
    dataType: "json", // 응답 데이터 형식, or json
    data: $('#frm_create').serialize(), 
    // Ajax 통신 성공, JSP 정상 처리
    success: function(rdata) { // callback 함수
      var panel = '';
      panel += "<DIV>";
      for(index=0; index < rdata.msgs.length; index++) {
        panel += rdata.msgs[index]+'<br>';
      }
      panel += "  <button type='btn btn-primary' onclick=\"$('#main_panel').hide();location.reload();\">닫기</button>";
      panel += "</DIV>";
   		
      
      $('#main_panel').html(panel);
      $('#main_panel').show();
      
      
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var panel = '';
      panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
      panel += '  ERROR<br><br>';
      panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
      panel += '  <strong>error</strong><br>'+error + '<hr>';
      panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
      panel += "</DIV>";
      
      $('#main_panel').html(panel);
      $('#main_panel').show();

    }
  });
}

function update(surveyno) {
  $('#btn_create').hide();
  $('#panel_update').show();
  $('#panel_update').show();
  $('#frm_find').hide();
  $.ajax({
    url: "./update.do", 
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    // data: $('#frm').serialize(),  // 보내는 데이터
    data: 'surveyno='+surveyno,
    success: function(rdata) {
      // alert(rdata);

      var frm_update = $('#frm_update');
      $('#survey_title', frm_update).val(rdata.survey_title);
      $('#seqno', frm_update).val(rdata.seqno);
      $('#startdate', frm_update).val(rdata.startdate);
      $('#enddate', frm_update).val(rdata.enddate);

      $('#surveyno', frm_update).val(rdata.surveyno);
      
    },
    error: function(request, status, error) { // 응답 결과, 상태, 에러 내용
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
      msg += '<strong>request.responseText</strong><br>'+request.responseText + '<hr>';
      msg += '<strong>status</strong><br>'+status + '<hr>';
      msg += '<strong>error</strong><br>'+error + '<hr>';

        var msg = '알림<br><br>';
      msg += '<strong>현재 시스템 정비중입니다.</strong><br>조속히 처리하겠습니다.<hr>';
      msg += '예상 종료 시간: 16:00'; 
        
      $('#main_panel').html(msg);
      $('#main_panel').show();
    }
   });

} 

//수정 처리
function update_proc(){
  $('#panel_update').hide();
  $.ajax({
    url: "./update_json.do", // 요청을 보낼주소
    type: "post",  // or get
    cache: false,
    dataType: "json", // 응답 데이터 형식, or json
    data: $('#frm_update').serialize(), 
    // Ajax 통신 성공, JSP 정상 처리
    success: function(rdata) { // callback 함수
      var panel = '';
      panel += "<DIV>";
      for(index=0; index < rdata.msgs.length; index++) {
        panel += rdata.msgs[index]+'<br>';
      }
      panel += "<br>";
      panel += "  <button type='btn btn-primary' onclick=\"$('#main_panel').hide();location.reload();\">닫기</button>";
      panel += "</DIV>";
   		
      
      $('#main_panel').html(panel);
      $('#main_panel').show();
      
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      var panel = '';
      panel += "<DIV id='panel' class='popup1' style='heigth: 450px;'>";
      panel += '  ERROR<br><br>';
      panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
      panel += '  <strong>error</strong><br>'+error + '<hr>';
      panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
      panel += "</DIV>";
      
      $('#main_panel').html(panel);
      $('#main_panel').show();

    }
  });
}




function delete_form(surveyno) {
  // 문자열: ', ""
  var url = './delete_form.do?surveyno=' + surveyno;
  var width = 500;
  var height = 300;
  var win = window.open(url, '회원 정보 변경', 'width='+width+'px, height='+height+'px');
  var x = (screen.width - width) / 2; 
  var y = (screen.height - height) / 2;
  
  win.moveTo(x, y);
}

function seqnoUp(surveyno,managerno) {
  var frm_seqno = $('#frm_seqno');
  frm_seqno.attr('action', './update_seqno_up.do');
  $('#surveyno', frm_seqno).val(surveyno);
  $('#managerno', frm_seqno).val(managerno);
  frm_seqno.submit();
}

function seqnoDown(surveyno,managerno) {
  var frm_seqno = $('#frm_seqno');
  frm_seqno.attr('action', './update_seqno_down.do');
  $('#surveyno', frm_seqno).val(surveyno);
  $('#managerno', frm_seqno).val(managerno);
  frm_seqno.submit();
}

function exit(){
  alert("관리자의 권한이 필요합니다.");
}


function create_update_cancel() {
  $('#panel_update').hide();
  $('#panel_create').hide();
  $('#panel_delete').hide();
  $('#btn_create').show();
  $('#frm_find').show();

}


</script>



</head> 

<body>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' style='width:80%; margin:0px auto; text-align: center; margin-top: 5%; margin-bottom:5%' >
<DIV class='content' >

    
  <DIV class='title_line'>설문조사 항목</DIV>




<%--     <input type='hidden' name='managerno' id='managerno' value='${managerno}'> --%>


  
<TABLE class='table '>
  <colgroup>

    <col style='width: 10%;'/> 
    <col style='width: 20%;'/>
     <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup>

  <thead>  
  <TR>
    <TH style='text-align: center ;'>순서</TH>
    <TH style='text-align: center ;'>설문조사 타이틀</TH>
     <TH style='text-align: center ;'>선택지 수</TH>
    <TH style='text-align: center ;'>시작일</TH>
    <TH style='text-align: center ;'>종료일</TH>        
    <TH style='text-align: center ;'>등록일</TH>
    <TH style='text-align: center ;'>기타</TH>    
  </TR>
  </thead>
  <c:forEach var="surveyVO" items="${list }">
  <TR>
    <TD style='text-align: center ;'>${surveyVO.surveyno}</TD>  

    <TD style='text-align: left;'>${surveyVO.survey_title }</TD>
      <TD style='text-align: left;'>${surveyVO.q_cnt }</TD>

    <TD style='text-align: center ;'>${surveyVO.startdate }</TD>
    <TD style='text-align: center ;'>${surveyVO.enddate }</TD>
    <TD style='text-align: center ;'>${surveyVO.rdate.substring(0,10)}</TD>
    <TD style='text-align: center ;'>

    <c:choose>   
         <c:when test="${surveyVO.q_cnt!=0}">
          <button type="button" class="btn btn-primary btn-xs"onclick="location.href='../surveyitem/party_list.do?surveyno=${surveyVO.surveyno}&memberno=1'" >응시 가능</button>
         </c:when>
         <c:otherwise>
        
         <button type="button" class="btn btn-danger btn-xs "onclick="exit();" >응시 불가</button>
         </c:otherwise>
    </c:choose>
    
    </TD>
  </TR>
  </c:forEach> 

</TABLE>
 <DIV class='bottom_menu'>${paging }</DIV>
 <br><br>

</DIV> <!-- content END -->
</DIV> <!-- container END -->

<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
</body>

</html> 
 
 
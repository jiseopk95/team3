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
<title></title> 


<link href="../css/style.css" rel="Stylesheet" type="text/css">
<style>

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

function create(surveyno) {
  // 문자열: ', ""
  var url = './create.do?surveyno=' + surveyno;
  var width = 500;
  var height = 600;
  var win = window.open(url, '회원 정보 변경', 'width='+width+'px, height='+height+'px');
  var x = (screen.width - width) / 2; 
  var y = (screen.height - height) / 2;
  
  win.moveTo(x, y);
}

function update(surveyno,surveyitemno) {
  // 문자열: ', ""
  var url = './update.do?surveyno=' + surveyno+"&surveyitemno="+surveyitemno;
  var width = 500;
  var height = 600;
  var win = window.open(url, '회원 정보 변경', 'width='+width+'px, height='+height+'px');
  var x = (screen.width - width) / 2; 
  var y = (screen.height - height) / 2;
  
  win.moveTo(x, y);
}

function delete_form(surveyno,surveyitemno) {
  // 문자열: ', ""
  var url = './delete_form.do?surveyno=' + surveyno+"&surveyitemno="+surveyitemno;
  var width = 400;
  var height = 460;
  var win = window.open(url, '회원 정보 변경', 'width='+width+'px, height='+height+'px');
  var x = (screen.width - width) / 2; 
  var y = (screen.height - height) / 2;
  
  win.moveTo(x, y);
}

function seqnoUp(surveyitemno,surveyno) {
  var frm_seqno = $('#frm_seqno');
  frm_seqno.attr('action', './update_seqno_up.do');
  $('#surveyno', frm_seqno).val(surveyno);
  $('#surveyitemno', frm_seqno).val(surveyitemno);
  
  frm_seqno.submit();
}

function seqnoDown(surveyitemno,surveyno) {
  var frm_seqno = $('#frm_seqno');
  frm_seqno.attr('action', './update_seqno_down.do');
  $('#surveyno', frm_seqno).val(surveyno);
  $('#surveyitemno', frm_seqno).val(surveyitemno);
  frm_seqno.submit();
}


</script>

</head> 

<body>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' style="width:80%;" >
<DIV class='content' style=' margin:0px auto; text-align: center; margin-top: 5%; margin-bottom:5%'>
  
  <!-- 우선 순위 증가 감소 폼 -->
  <FORM name='frm_seqno' id='frm_seqno' method='post' action=''>
    <input type='hidden' name='surveyno' id='surveyno' value=''>
    <input type='hidden' name='surveyitemno' id='surveyitemno' value=''>
  </FORM>

  <DIV id='main_panel'></DIV>


  
    <DIV class='title_line' style="width:30%;">
  <span style="font-size:18px;font-weight:bold;"><IMG src='./images/q.png'>  ${surveyVO.survey_title}<br></span>
  <br>
  </DIV>

<div>
     <ASIDE style='float: left;'>
    <A href="../survey/list_by_manager_search_paging.do?managerno=${surveyVO.managerno}"><IMG src='./images/back.png' style="width:40px;height:40px;"></A>
    <!-- <A href='./create.jsp'>등록</A> -->
    </ASIDE> 
    
    <ASIDE style='float: right;'>
    <button class="btn btn-primary btn" type="button" onclick="create(${surveyVO.surveyno});">등록</button>
<%--     <A href="javascript:create(${surveyVO.surveyno})">등록</A> --%>
    </ASIDE> 
</div>
<TABLE class='table ' >
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
                  <IMG id='thumb' src='./storage/${survey_itemVO.thumb}' style='width: 100px; height: 100px;'> <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <IMG id='thumb' src='./images/box2.png' style='width: 100px; height: 100px;'> <!-- 파일이 존재하지 않는 경우 -->
                  </c:otherwise>
              </c:choose>
      </td>      
    
      <TD style='text-align: left;'><%-- <A href="../choice/list.do?surveyitemno=${survey_itemVO.surveyitemno}"> --%>${survey_itemVO. question}</TD>
    
 <%--    
    <TD style='text-align: center ;'>${manager_surveyVO.name}</TD>  
    <TD style='text-align: left;'>${manager_surveyVO.survey_title }</TD>
    <TD style='text-align: center ;'>${manager_surveyVO.q_cnt}</TD>
    <TD style='text-align: center ;'>${manager_surveyVO.startdate }</TD>
    <TD style='text-align: center ;'>${manager_surveyVO.enddate }</TD>
    <TD style='text-align: center ;'>${manager_surveyVO.rdate.substring(0,10)}</TD> --%>
    <TD style='text-align: center ;'>
      <A href="javascript:update(${surveyVO.surveyno},${survey_itemVO.surveyitemno})"><IMG src='./images/update.png' title='수정' style='width:20px;'></A>
      <A href="javascript:delete_form(${survey_itemVO.surveyno},${survey_itemVO.surveyitemno})"><IMG src='./images/delete.png' title='삭제' style='width:20px;'></A>
       <A href="javascript:seqnoUp(${survey_itemVO.surveyitemno},${survey_itemVO.surveyno})"><IMG src='./images/up.png' title='우선순위 높임' style='width:20px;'></A>
      <A href="javascript:seqnoDown(${survey_itemVO.surveyitemno},${survey_itemVO.surveyno})"><IMG src='./images/down.png' title='우선순위 낮춤' style='width:20px;'></A>
     <span style ='color:blue'>[${survey_itemVO.seqno }]</span>
     
    </TD>
  </TR>
  </c:forEach> 

</TABLE>

</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
</body>

</html> 
 
 
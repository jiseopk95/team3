<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 

    <title>Pet Doctor</title> <!-- 주소창 타이틀 -->
   

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<script type="text/javascript">
$(function() {
 
});

function test(memberno,surveyno){
    $.ajax({
      url: "./list_mnoCnt.do", // 요청을 보낼주소
      type: "post",  // or get
      cache: false,
      async:true,
      dataType: "json", // 응답 데이터 형식, or json
      data: "memberno=" + memberno + "&surveyno=" + surveyno,
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
      
        if(rdata.mnoCnt ==0){
         location.href='../surveyitem/party_list.do?memberno='+memberno+'&surveyno='+surveyno;
       }else if(rdata.mnoCnt != 0){
         alert("이미 응시하신 설문조사입니다.");
       }
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

function exit(){
  alert("관리자의 권한이 필요합니다.");
}

//수정 처리


</script>



</head> 

<body>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container'  style="width:80%;">
<DIV class='content'  style='margin:0px auto; text-align: center; margin-top: 5%; margin-bottom:5%' >


 <DIV class='title_line' style="width:30%;">
 <span style="font-size:18px;font-weight:bold;">설문조사 항목<br></span>
  <br>
  </DIV>





<%--     <input type='hidden' name='managerno' id='managerno' value='${managerno}'> --%>


  
<TABLE class='table '>
  <colgroup>

    <col style='width: 10%;'/> 
    <col style='width: 25%;'/>
     <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 5%;'/>
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
  <TR style="height:50px;margin:0px auto;">
    <TD style='text-align: center ;'>${surveyVO.surveyno}</TD>  

    <TD style='text-align: left;'>${surveyVO.survey_title }</TD>
      <TD style='text-align: center;'>${surveyVO.q_cnt }</TD>

    <TD style='text-align: center ;'>${surveyVO.startdate }</TD>
    <TD style='text-align: center ;'>${surveyVO.enddate }</TD>
    <TD style='text-align: center ;'>${surveyVO.rdate.substring(0,10)}</TD>
    <TD style='text-align: center ;'>
    <!-- 오늘날짜 구하는 구간 -->
    <jsp:useBean id="toDay" class="java.util.Date" />
    <fmt:formatDate value='${toDay}' pattern='yyyy-MM-dd' var="nowDate"/>
    <!-- end -->
    <c:choose>   
         <c:when test="${surveyVO.q_cnt!=0&&nowDate <=surveyVO.enddate}">
          <button type="button" class="btn btn-primary btn-xs"onclick="test(6,${surveyVO.surveyno});">응시 가능</button>
         </c:when>
         <c:when test="${nowDate >surveyVO.enddate}">
         <IMG src='./images/end.png' style="width:35px; height:35px;">
         </c:when>
         <c:when test="${surveyVO.q_cnt==0}">
          <button type="button" class="btn btn-danger btn-xs"onclick="exit()">응시 불가</button>
         </c:when>
 

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
 
 
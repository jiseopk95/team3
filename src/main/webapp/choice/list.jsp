<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style>
  table, th, td {
    border: 3px solid #bcbcbc;
  }
  table {
    width: 700px;
    height: 200px;
    margin-left: auto;
    margin-right: auto;
  }

</style>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<script type="text/javascript">
 $(function() {
   $('#main_panel').hide();
  $('#panel_update').hide();
});

//등록 처리
 function create_proc() {
   $('#panel_create').hide();
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
 
function update(surveyitemno) {

  $('#panel_update').show();
  
  $.ajax({
    url: "./update.do", 
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    // data: $('#frm').serialize(),  // 보내는 데이터
    data: 'surveyitemno='+surveyitemno,
    success: function(rdata) {
      // alert(rdata);

      var frm_update = $('#frm_update');
      $('#a1', frm_update).val(rdata.a1);
      $('#a2', frm_update).val(rdata.a2);
      $('#a3', frm_update).val(rdata.a3);
      $('#a4', frm_update).val(rdata.a4);

      $('#choiceno', frm_update).val(rdata.choiceno);
      
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

// 수정 처리
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


function create_update_cancel() {
  $('#panel_update').hide();
  $('#panel_create').hide();
  $('#panel_delete').hide();

}

// 삭제 폼
function delete_form(surveyitemno) {
  $('#panel_create').hide();
  $('#panel_update').hide();
  $('#panel_delete').show();
  
  $.ajax({
    url: "./delete.do", 
    type: "get", // or get
    cache: false,
    async: true, // true: 비동기
    dataType: "json", // 응답 형식, html, xml...
    // data: $('#frm').serialize(),  // 보내는 데이터
    data: 'surveyitemno='+surveyitemno,
    success: function(rdata) {
      // alert(rdata);
      var frm_delete = $('#frm_delete');
      $('#surveyitemno', frm_delete).val(surveyitemno);
      
      var str = '';        
      // 소속된 카테고리 갯수를 출력 예정

        panel = '문제 <span style=color:red>[' + rdata.question + "]</span> 의 사지선다를  삭제 하시겠습니까??<br>";
        panel += "삭제하면 복구 할 수 없습니다.<br>"
        panel += '<button type="button" id=submit onclick="delete_proc()">삭제</button>';
        panel += '&nbsp;<button type="button" onclick="create_update_cancel();">취소</button>'; 

        $('#msg_delete').html(panel);
        $('#msg_delete').show();
    },
    error: function(request, status, error) { // 응답 결과, 상태, 에러 내용
      var msg = 'ERROR<br><br>';
      panel += '<strong>request.status</strong><br>'+request.status + '<hr>';
      panel += '<strong>request.responseText</strong><br>'+request.responseText + '<hr>';
      panel += '<strong>status</strong><br>'+status + '<hr>';
      panel += '<strong>error</strong><br>'+error + '<hr>';

/*         var msg = '알림<br><br>';
      msg += '<strong>현재 시스템 정비중입니다.</strong><br>조속히 처리하겠습니다.<hr>';
      msg += '예상 종료 시간: 16:00'; */
        
      $('#main_panel').html(msg);
      $('#main_panel').show();
    }
   });

}

//삭제 처리
function delete_proc(){
  $('#panel_delete').hide();
  $.ajax({
    url: "./delete_json.do", // 요청을 보낼주소
    type: "post",  // or get
    cache: false,
    dataType: "json", // 응답 데이터 형식, or json
    data: $('#frm_delete').serialize(), 
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



</script>

</head> 

<body>
<DIV class='container' style='width: 100%;'>
 <c:import url="/menu/top.jsp"  /> 
<DIV class='content'>
  
  <!-- 우선 순위 증가 감소 폼 -->
  <FORM name='frm_seqno' id='frm_seqno' method='post' action=''>
    <input type='hidden' name='surveyno' id='surveyno' value=''>
    <input type='hidden' name='surveyitemno' id='surveyitemno' value=''>
  </FORM>



  <DIV class='title_line'><span style='color:red; font-size:18px;'>[${survey_itemVO.question}]</span><br> Answer 등록</DIV>

   <c:choose>
   <c:when test="${cnt == 0 }">
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
      <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'
             enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='surveyitemno' id='surveyitemno' value='${param.surveyitemno}'> 

      <div class="row">
          <div class="col-md-3 mb-2">
            <label for="a1">Answer 1</label>
            <input type="text" class="form-control" name="a1" id="a1" placeholder="" value="answer1" required >
          </div>
          
          <div class="col-md-3 mb-2">
            <label for="a2">Answer 2</label>
            <input type="text" class="form-control" name="a2" id="a2" placeholder="" value="" required >
          </div>
          
         <div class="col-md-3 mb-2">
            <label for="a3">Answer 3</label>
            <input type="text" class="form-control" name="a3" id="a3" placeholder="" value="answer3" required >
          </div>
          
          <div class="col-md-3 mb-2">
            <label for="a4">Answer 4</label>
            <input type="text" class="form-control" name="a4" id="a4" placeholder="" value="answer4" required >
          </div>
          
        <label></label>
       <div style="text-align : right; margin-right:15px;">
             <button type="button"  class="btn btn-primary "  id='submit' onclick="create_proc()">등록</button>
            <button class="btn btn-primary " type="button" onclick="create_update_cancel();">취소</button>
       </div>  
     </div> 

    </FORM>
   
    </DIV>
   </c:when>   
   </c:choose>
   
  <DIV style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%;height:80px;text-align: center; ' id='main_panel' ></DIV>
  
   <DIV id='panel_update' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
      <FORM name='frm_update' id='frm_update' method='POST' action='./update.do'
             enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='choiceno' id='choiceno' value=''> 


      <div class="row">
          <div class="col-md-3 mb-2">
            <label for="a1">Answer 1</label>
            <input type="text" class="form-control" name="a1" id="a1" placeholder="" value="" required >
          </div>
          
          <div class="col-md-3 mb-2">
            <label for="a2">Answer 2</label>
            <input type="text" class="form-control" name="a2" id="a2" placeholder="" value="" required >
          </div>
          
         <div class="col-md-3 mb-2">
            <label for="a3">Answer 3</label>
            <input type="text" class="form-control" name="a3" id="a3" placeholder="" value="" required >
          </div>
          
          <div class="col-md-3 mb-2">
            <label for="a4">Answer 4</label>
            <input type="text" class="form-control" name="a4" id="a4" placeholder="" value="" required >
          </div>
          
        <label></label>
       <div style="text-align : right; margin-right:15px;">
         <button type="button"  class="btn btn-primary "  id='submit' onclick="update_proc()">수정</button>
         <button class="btn btn-primary " type="button" onclick="create_update_cancel();">취소</button>
       </div>  
     </div> 

    </FORM>
      
    </DIV> 
    
    
      <DIV id='panel_delete' style='display: none; padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_delete' id='frm_delete' method='POST' 
                action='./delete.do'> 
      <input type='hidden' name='surveyitemno' id='surveyitemno' value=''>

      <DIV id='msg_delete' style='margin: 20px auto;'></DIV>
    </FORM>
  </DIV>


      <div>
       <ASIDE style='float: left;'>
      <A href="../surveyitem/list.do?surveyno=${survey_itemVO.surveyno}">전체 categrp</A>
      <!-- <A href='./create.jsp'>등록</A> -->
      </ASIDE> 
    
  
          <ASIDE style='float: right;'>
          <c:choose>
            <c:when test="${cnt == 0 }">
              <A href="javascript:create(${survey_itemVO.surveyitemno})"><IMG src='./images/create.png' title='등록' style='width:20px;'></A>
            </c:when>
          </c:choose> 

            <A href="javascript:update(${surveyitem_choiceVO.surveyitemno})"><IMG src='./images/update.png' title='수정' style='width:20px;'></A>
  	        <A href="javascript:delete_form(${surveyitem_choiceVO.surveyitemno})"><IMG src='./images/delete.png' title='삭제' style='width:20px;'></A>

          </ASIDE> 
        <br><br>
      </div>
    
<div>
<TABLE>
  <colgroup>

    <col style='width: 10%;'/> 
    <col style='width: 40%;'/>

  </colgroup>

  <thead>  
  
  </thead>
   

  <TR >
    <TD colspan ='2' style='text-align: center ;'>${surveyitem_choiceVO.question}</TD>  
  </TR>
  <TR>
    <TH style='text-align: center ;'>A1</TH>
    <TD style='text-align: left ;'>${surveyitem_choiceVO.a1}</TD>  
  </TR>
    <TR>
    <TH style='text-align: center ;'>A2</TH>
    <TD style='text-align:  left ;'>${surveyitem_choiceVO.a2}</TD>  
  </TR>
    <TR>
    <TH style='text-align: center ;'>A3</TH>
    <TD style='text-align:  left ;'>${surveyitem_choiceVO.a3}</TD>  
  </TR>
    <TR>
    <TH style='text-align: center ;'>A4</TH>
    <TD style='text-align:  left ;'>${surveyitem_choiceVO.a4}</TD>  
  </TR>


</TABLE>
</div>

</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" /> --%>
</DIV> <!-- container END -->
</body>

</html> 
 
 
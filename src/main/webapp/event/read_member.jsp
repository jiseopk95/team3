<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
  $(document).ready(function(){ // window.onload = function() { ... }
    imgResize()
  });

  //<div id='file1Panel'> 태그의 width에 맞추어 자동 축소
  function imgResize() {
    var file1 = $('#file1');
    var width = file1.width();
    // alert(width);
    // console.log("loading - width:" + loading + " - " + width);
    // console.log("screen.width-(screen.width * 0.3): " + screen.width-(screen.width * 0.3));
        
    if (file1 != null) {
      // 이미지 width가 화면의 70%보다 크다면
      if (width > screen.width-(screen.width * 0.4)) {
        // console.log("loading - width:" + loading + " - " + width);
        // file1.width(600); // 이미지 축소
        $('#file1Panel').attr('width', '100%');  // 할당된 영역을 100%로 설정함.
        file1.css('width', '900'); // <div id='file1Panel'> 태그의 width에 맞추어 자동 축소
      } else {
        // 작은 이미지는 그대로 출력
      }
    }

  }
  
  function panel_img(file){
    var panel = '';
    panel += "<DIV id='panel' class='popup_img' style='width: 80%;'>";
    panel += "  <A href=\"javascript: $('#main_panel').hide();\"><IMG src='./storage/"+file+"' style='width: 100%;'></A>";
    panel += "</DIV>";
    
    $('#main_panel').html(panel);
    $('#main_panel').show();
    
  }
  // 이벤트처리폼- ajax로
  /* function check_form(eventno,memberno) {
    alert("응모하시겠습니까?")
    $.ajax({
      url: "./eventCheck.do",
      type: "post",
      cache: false,
      async: true,
      dataType: "json",
      data: "eventno=" + eventno +"&memberno="+memberno,
      success: function(rdata) {
      location.reload();
      alert(rdata.msgs);
  }
  });
  }  */
  /* 이벤트응모 */
   /* function check_form(eventno){
    console.log("eventno : " + $('#eventno').val() + "memberno : " + $('#memberno').val());
    alert("이벤트응모하시겠습니까?")
     if(($('#memberno').val()==$('#userno').val())&&($('#eventno').val()==$('#userevent').val())){
      alert("이미 응모한 이벤트 입니다!");
    }else{ 
    var frm_event = $('#frm');
    //frm_event.attr('action', './eventCheck.do');
    $('#eventno', frm_event).val();
    $('#memberno', frm_event).val();
    frm_event.submit();
    alert("응모완료!") 
    }  
  }*/
    // 처리폼
    function check(eventno,memberno) {
    alert("응모하시겠습니까?")
    $.ajax({
    url: "./check.do",
    type: "post",
    cache: false,
    async: true,
    dataType: "json",
    data: "eventno=" + eventno+"&memberno="+memberno,
    success: function(rdata) {
    location.reload();
    var msgs = rdata.msgs; // 출력메세지를 담은 JSON
    var dismsgs = ''; // 출력메세지를 담을곳
    for (var i = 0; i < msgs.length; i++) {
      // JSON에 있는 출력메세지를 이어져라
      dismsgs += msgs[i];
    }
    alert(dismsgs);
    },

    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error : function(request, status, error) {
      var msg = 'ERROR<br><br>';
      msg += '<strong>request.status</strong><br>' + request.status + '<hr>';
      msg += '<strong>error</strong><br>' + error + '<hr>';
      console.log(msg);
    }
  });
}
    
</script>
</head>

<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' >
<DIV class='content' style='width: 100%; pagging-top:5%; padding-bottom:10%'> 
  <FORM name='frm_event' id='frm_event' method='post' action='./eventCheck.do'>
    <%-- <input type='hidden' name='eventno' id='eventno' value=''>
    <input type='hidden' name='memberno' id='memberno' value='${memberno}'> --%>
  </FORM>
            
 <%-- <ASIDE style='float: left;'>
    <A href='../category/list.do?categrpno=${categoryVO.categrpno }'>카테고리</A>
    <span style='font-size: 1.2em;'>></span>  
    <A href='./list_by_category.do?categoryno=${categoryVO.categoryno }&word=${param.word}'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./search_paging_member.do?memberno=${param.memberno}'>목록</A>
    <c:if test="${sessionScope.id ne null }">
      <span class='menu_divide' >│</span> 
      <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>
      <span class='menu_divide' >│</span> 
      <A href='./reply.do?categoryno=${categoryVO.categoryno }&styleno=${beautyVO.styleno }&word=${param.word}&nowPage=${param.nowPage}'>답변</A>
      <span class='menu_divide' >│</span> 
      <A href='./update.do?styleno=${beautyVO.styleno }&word=${param.word}&nowPage=${param.nowPage}'>수정</A>
      <span class='menu_divide' >│</span> 
      <A href='./delete.do?styleno=${beautyVO.styleno }&categoryno=${categoryVO.categoryno }&word=${param.word}&nowPage=${param.nowPage}'>삭제</A>
    </c:if>
  </ASIDE>   --%>
  
  <div class='menu_line'></div>

  <DIV id='main_panel'></DIV>
  
  <FORM name='frm' id='frm' method="POST" action='./update.do'>
      <input type="hidden" name="eventno" value="${eventVO.eventno}">
        <input type="hidden" name="memberno" value="${memberno}">
      <fieldset class="fieldset">
            <table style="margin-bottom: 10px;width:100%;">
        <colgroup>  <!-- tr과 연관, 한행에서 왼쪽기준부터 85퍼는 타이틀준다는 뜻 15퍼에 날짜주면 자동으로 오른쪽부분에 날짜가 찍히게됨. -->
          <col width="85%">
          <col width="15%">
        </colgroup>
        <tr>
    <td style="font-weight: bold; font-size:20px; text-align: left; padding-left:10px;">${eventVO.title}</td>
          <td> (${eventVO.rdate.substring(0, 16)})</td>

        </tr>
      </table>
     <div class='menu_line'></div>
            <DIV style='float:left; margin: 10px 10px 0px 10px; width: 40%;'>
              <c:forEach var ="fileVO"  items="${file_list }">
                <A href="javascript: panel_img('${fileVO.file }')"><IMG src='./storage/${fileVO.file }' style='width:100%;'></A>
              </c:forEach>
            </DIV>
            <table style="margin-left:0px; margin-top:20px">
        <tr>
          <td>${eventVO.content }</td>
        </tr>
           </table>
        <table style="margin-left:0px; margin-top:50px">
        
         <tr><td>▶이벤트 시작일 : ${eventVO.period_start}</tr>
         <tr><td>▶이벤트 종료일 : ${eventVO.period_end}</td></tr><!-- tr은 행, 일종의 한줄띄기 느낌 -->
        <tr><td style="text-align: left;">▶당첨 발표일 : ${eventVO.windate}</td></tr>
      <tr> <td style="text-align: left;">작성자 : ${eventVO.writer}</td></tr>
         </table>
       <table style="margin-left:0px; margin-top:50px">
      <tr><td style="text-align: left;">
         <A href="javascript: check_form(${eventVO.eventno})">
            <img src="./images/join.png" title="응모" border='0' style='width: 180px; height: 60px;'/></A></td></tr>
            </table>
        <button type="button" class="btn btn-primary btn-sm" style='margin-left: 95%;'
    onclick="location.href='./search_paging.do?managerno=${sessionScope.managerno}'">목록</button>
      </fieldset>
  </FORM>


</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>


   
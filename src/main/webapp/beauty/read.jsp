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
</script>
</head>

<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' >
<DIV class='content' style='width: 100%; margin:0px auto; margin-top: 10%; margin-bottom: 10%'> 
   <input type='hidden' name='categoryno' id='categoryno' value='${param.categoryno}'> <!-- 원래 value 가 param.categoryno이런거였음. -->
      <input type='hidden' name='managerno' id='managerno' value='${sessionScope.managerno}'>
            
<%--  <ASIDE style='float: left;'>
    <A href='../category/list.do?categrpno=${categoryVO.categrpno }'>카테고리</A>
    <span style='font-size: 1.2em;'>></span>  
    <A href='./list_by_category.do?categoryno=${categoryVO.categoryno }&word=${param.word}'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./search_paging.do?nowPage=${param.nowPage}'>목록</A>
    <c:if test="${sessionScope.managerno ne null }">
      <span class='menu_divide' >│</span> 
      <A href='./create.do?managerno=${sessionScope.managerno}'>등록</A>
      <span class='menu_divide' >│</span> 
    </c:if>
  </ASIDE>   --%>

  <DIV id='main_panel'></DIV>
  
  <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="styleno" value="${beautyVO.styleno}">
      <fieldset class="fieldset">
      <table style="margin-bottom: 10px;width:100%;">
        <colgroup>  <!-- tr과 연관, 한행에서 왼쪽기준부터 85퍼는 타이틀준다는 뜻 15퍼에 날짜주면 자동으로 오른쪽부분에 날짜가 찍히게됨. -->
          <col width="85%">
          <col width="15%">
        </colgroup>
        <tr>
    <td style="font-weight: bold; font-size:20px; text-align: left; padding-left:10px;">${beautyVO.title}</td>
          <td> (${beautyVO.rdate.substring(0, 16)})</td>

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
          <td>${beautyVO.content }</td>
        </tr>
           </table>
               <table style="margin-left:0px; margin-top:50px">
        
         <tr><td> ▶총 비용 : ${beautyVO.pay}원 / ▶소요시간 : ${beautyVO.times} 시간</td></tr>  <!-- tr은 행, 일종의 한줄띄기 느낌 -->
        <tr><td style="text-align: left;">작성자 : ${beautyVO.rname}( ${beautyVO.email} )</td></tr>
      <tr> <td style="text-align: left;">조회 수 : ${beautyVO.cnt}</td></tr>
    </table>
         <button type="button" class="btn btn-primary btn-sm" style='margin-left: 95%;'
    onclick="location.href='./search_paging.do?nowPage=${param.nowPage}'">목록</button>
      </fieldset>
  </FORM>


</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>


   
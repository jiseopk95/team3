<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/jquery.cookie.js"></script>

<script type="text/javascript">
$(function() {
var kind='${managerVO.kind}';
if(kind=='M') {
  $("input:radio[name='kind']:radio[value='M']").prop('checked', true); // 선택하기
} else if(kind=='B') {
  $("input:radio[name='kind']:radio[value='B']").prop('checked', true); // 선택하기
} else if(kind=='D') {
  $("input:radio[name='kind']:radio[value='D']").prop('checked', true); // 선택하기
} else if(kind=='A') {
  $("input:radio[name='kind']:radio[value='A']").prop('checked', true); // 선택하기
} else if(kind=='N') {
  $("input:radio[name='kind']:radio[value='N']").prop('checked', true); // 선택하기
}
});
</script>


</head> 


<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' style='width: 100%;'>

<DIV class='content' style='padding-top:5%; width: 80%; padding-bottom: 10%;'>
<DIV class='title_line' style='width: 20%;'>권한 변경</DIV>
  
   <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='../index.jsp'>HOME</A>
  </ASIDE> 
<br>
  <div class='menu_line'></div>
  <DIV id='main_panel'></DIV>

  <!-- Modal -->
  <div class="modal fade" id="modal_panel" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title" id='modal_title'></h4>
        </div>
        <div class="modal-body">
          <p id='modal_content'></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div> <!-- Modal END -->
    
  
  <!-- <ASIDE style='float: left;'>
      <A href='./member/list.do'>회원 목록</A>  
  </ASIDE> -->
  <!-- <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>목록</A>
  </ASIDE> 

  <div class='menu_line'></div> -->
  

  <FORM name='frm' method='POST' action='./kind_update2.do' 
            onsubmit="return send();" class="form-horizontal">
    <br>
    <input type='hidden' name='managerno' id='managerno' value='${managerVO.managerno }'> 
<%--     <input type='hidden' name='name' id='name' value='${managerVO.name }'> 
    <input type='hidden' name='position' id='position' value='${managerVO.position }'>  --%>
    
    <div class="list_search_form">
    <br>
      <label class=kind_font><input type="radio" name="kind" value='N'  />&nbsp&nbsp N 인증 되지 않은 회원</label> <br> <br>
      <label class=kind_font ><input type="radio" name="kind" value='B' />&nbsp&nbsp B 미용관계자</label> <br> <br>
      <label class=kind_font ><input type="radio" name="kind" value='D' />&nbsp&nbsp D 병원관계자</label> <br> <br>
      <label class=kind_font ><input type="radio" name="kind" value='A' />&nbsp&nbsp A 질의응답사원</label> <br> <br>
      <label class=kind_font ><input type="radio" name="kind" value='M' />&nbsp&nbsp M 최상위 관계자</label> <br> <br>
    </div>
    
    <%-- <input type='hidden' name='phone' id='phone' value='${managerVO.phone }'> 
    <input type='hidden' name='email' id='email' value='${managerVO.email }'> 
    <input type='hidden' name='zipcode' id='zipcode' value='${managerVO.zipcode }'> 
    <input type='hidden' name='address1' id='address1' value='${managerVO.address1 }'> 
    <input type='hidden' name='address2' id='address12' value='${managerVO.address2 }'>  --%>
    

          <%-- <c:if test="${file_list.size() > 0 }">
                <c:forEach var ="fileVO"  items="${file_list }">
                  <A href="javascript: panel_img('${fileVO.files }')"><IMG src='./storage/${fileVO.thumbs }' style='margin-top: 2px;'></A>
                </c:forEach>
            </c:if> --%>
          <!-- <input type='file' name='filesMF' id='filesMF' size='40' multiple="multiple" > -->
          <br>
     <button type="submit" class="btn btn-primary">수정</button>
     <button type="button" onclick="history.go(-1);" class="btn btn-secondary">취소</button> <br>


</FORM>

</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 



<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 

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
    $.cookie('checkID', 'FALSE'); // Cookie 생성
    $('#popup').hide();
  });
  
  function checkId(){
    var frm = $('#frm');
    var params = 'id='+$('#id', frm).val(); // #: id
    // alert('params: ' + params);
    
    $.ajax({
      url: "./checkId.do",
      type: "GET",
      cache: false,
      dataType: "json", // or html
      data: params,
      success: function(data){
        var msg = "";

        if (data.cnt > 0) {
          $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap
          msg = "이미 사용중인 ID 입니다.";
          /* alert('이미 사용중인 ID 입니다.'); */
        } else {
          $('#modal_content').attr('class', 'alert alert-success'); // Bootstrap
          msg = "사용 가능한 ID 입니다.";
          /* alert('사용 가능한 ID 입니다.'); */
          $.cookie('checkID', 'TRUE'); // Cookie 값 변경
        }

        $('#modal_title').html('ID 중복 확인');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error: function (request, status, error){  
        var msg = "에러가 발생했습니다. <br><br>";
        msg += "다시 시도해주세요.<br><br>";
        msg += "request.status: " + request.status + "<br>";
        msg += "request.responseText: " + request.responseText + "<br>";
        msg += "status: " + status + "<br>";
        msg += "error: " + error;

        // var panel = "";
        // panel += "<DIV id='panel' class='popup1' style='height: 350px;'>";
        // panel += msg;
        // panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\">CLOSE</A>]";
        // panel += "</DIV>";
        
        // $('#main_panel').html(panel);      
        // $('#main_panel').show();
            // id_span.html(msg);
        $('#modal_title').html('ID 중복 확인');
        $('#modal_content').attr('class', 'alert alert-danger');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      }
    });

  }
  
  function checkemail(){
    var frm = $('#frm');
    var params = 'email='+$('#email', frm).val(); // #: id
    // alert('params: ' + params);
    
    $.ajax({
      url: "./checkemail.do",
      type: "GET",
      cache: false,
      dataType: "json", // or html
      data: params,
      success: function(data){
        var msg = "";

        if (data.cnt > 0) {
          $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap
          msg = "이미 사용중인 email 입니다.";
          /* alert('이미 사용중인 email 입니다.'); */
        } else {
          $('#modal_content').attr('class', 'alert alert-success'); // Bootstrap
          msg = "사용 가능한 email 입니다.";
          /* alert('사용 가능한 email 입니다.'); */
          $.cookie('checkemail', 'TRUE'); // Cookie 값 변경
        }

        $('#modal_title').html('email 중복 확인');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      },
      // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
      error: function (request, status, error){  
        var msg = "에러가 발생했습니다. <br><br>";
        msg += "다시 시도해주세요.<br><br>";
        msg += "request.status: " + request.status + "<br>";
        msg += "request.responseText: " + request.responseText + "<br>";
        msg += "status: " + status + "<br>";
        msg += "error: " + error;

        // var panel = "";
        // panel += "<DIV id='panel' class='popup1' style='height: 350px;'>";
        // panel += msg;
        // panel += "<br>[<A href=\"javascript: $('#main_panel').hide()\">CLOSE</A>]";
        // panel += "</DIV>";
        
        // $('#main_panel').html(panel);      
        // $('#main_panel').show();
            // id_span.html(msg);
        $('#modal_title').html('email 중복 확인');
        $('#modal_content').attr('class', 'alert alert-danger');
        $('#modal_content').html(msg);
        $('#modal_panel').modal(); // 다이얼로그 출력   
      }
    });

  }
  
  function send() {
    var check = $.cookie('checkID'); // 쿠키값
   
    if (check != 'TRUE') {
      var msg = "ID 중복확인이 되지 않았습니다.<br>";
      msg += "ID 중복확인을 해주세요.<br>";
      /* alert('중복x.'); */

      $('#modal_title').html('ID 체크 확인');
      $('#modal_content').attr('class', 'alert alert-danger');
      $('#modal_content').html(msg);
      $('#modal_panel').modal(); // 다이얼로그 출력   
      
      return false; // submit 중지
    }
    
    if ($('#passwd').val() != $('#passwd2').val()) {
      var msg = "입력된 패스워드가 일치하지 않습니다.<br>";
      msg += "패스워드를 다시 입력해주세요.<br>";

      $('#modal_title').html('패스워드 일치여부 확인');
      $('#modal_content').attr('class', 'alert alert-danger');
      $('#modal_content').html(msg);
      $('#modal_panel').modal(); // 다이얼로그 출력   
      
      return false; // submit 중지
    }
    
    return true; // submit 진행
  }
  
</script>


</head> 

<body id="page-top">
<jsp:include page="/menu/top.jsp" flush='false' />
</body>

<DIV class='container' style='width: 100%;'>
<DIV class='content' style='padding-top:5%; width: 80%; padding-bottom: 10%;'>

<DIV class='title_line' >회원가입</DIV>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./login.do'>로그인 &nbsp</A>
  </ASIDE>
  <br>

  <br>
 
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
  
<DIV style='width: 80%; margin: 0px auto;'>
  <FORM name='frm' id='frm' method='POST' action='./create.do' 
              onsubmit="return send();" class="form-horizontal">
              
              
<br>
      <IMG src='./images/member.jpg' style='width: 200px; height: 200px; border-radius: 50%'> <br><br><br><br>
 가입유형&nbsp&nbsp    

       <input type="radio" name="login1" OnClick="window.location.href='../member/create.do'" checked >&nbsp일반회원&nbsp
       <input type="radio" name="login2" OnClick="window.location.href='../manager/create.do'">&nbsp직원&nbsp&nbsp&nbsp
   <br><br><br><br>
       <!-- <label class=fonts >아이디</label> -->
       
      &nbsp&nbsp&nbsp&nbsp&nbsp아이디
       <input type='text' name='id' id='id' value='' required="required" style='width: 20%;' placeholder="" autofocus="autofocus">
       &nbsp
        <button type='button' onclick="checkId()" class="btn btn-info"  >중복확인</button>
        <SPAN id='id_span'></SPAN> <!-- ID 중복 관련 메시지 -->        
    <br>
   <br>             
    
      <!-- <label for="passwd" >비밀번호</label> -->
      비밀번호
        <input type='password' name='passwd' id='passwd' value='' required="required" style='width: 20%;' placeholder=""> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
     
    <!-- <input type='hidden' name='kind' id='kind' value='G'> -->
    <br>
   <br>
      <!-- <label for="passwd2" >비밀번호 확인</label> -->
      비밀번호 확인    
        <input type='password'  name='passwd2' id='passwd2' value='' required="required" style='width: 20%;' placeholder=""> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
     <br><br>
    
    
      <!-- <label for="name" >성명</label>   -->  
     성명
        <input type='text' name='name' id='name' 
                   value='' required="required" style='width: 20%;' placeholder=""> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
     <br><br>
    
      <!-- <label for="phone" >전화번호</label> -->
      전화번호    
        <input type='text'  name='phone' id='phone'
                   value='' required="required" style='width: 20%;' placeholder="">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
      <br><br>
      &nbsp&nbsp
      
      <!-- <label for="email" >이메일</label> -->
      &nbsp&nbsp이메일    
        <input type='text'  name='email' id='email'
                   value='' required="required" style='width: 20%;' placeholder=""> &nbsp
      <button type='button' onclick="checkemail()" class="btn btn-info"  >중복확인</button>
      <br><br>
      

      <!-- <label for="zipcode" >우편번호</label> --> 
      &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 
       우편번호
        <input type='text' name='zipcode' id='zipcode' 
                   value='' required="required" style='width: 20%;' placeholder="">
&nbsp
        <input type="button" onclick="DaumPostcode()" value="우편번호 찾기" class="btn btn-secondary">
    <br><br>
    
    <!-- ----- DAUM 우편번호 API 시작 ----- -->
<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:0px auto;position:relative">
  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }

    function DaumPostcode() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address1').value = fullAddr;

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
                
                $('#address2').focus();
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
</script>
<!-- ----- DAUM 우편번호 API 종료----- -->
<br><br>
    &nbsp&nbsp
      <!-- <label for="address1" >주소</label>  -->   
      주소
        <input type='text'  name='address1' id='address1' 
                   value='' required="required" style='width: 35%;' placeholder=""> &nbsp&nbsp&nbsp&nbsp&nbsp
    <br><br>

      <!-- <label for="address2" >상세 주소</label>  -->
      상세 주소   
        <input type='text' name='address2' id='address2' 
                   value='' required="required" style='width: 35%;' placeholder=""> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<br><br>
<br>
    
        <button type="submit" class="btn btn-primary">가입</button>
        <button type="button" onclick="location.href='../index.jsp'" class="btn btn-secondary">취소</button>

  </FORM>
  </DIV>

</DIV><!--  content END  -->
</DIV> <!-- container END -->

    
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

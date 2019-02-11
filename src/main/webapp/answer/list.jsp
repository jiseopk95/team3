<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
<script type="text/javascript">
  $(function() {
    action_cancel(); // panel 출력 초기화
    
    list();  // 모든 카테고리 목록
    
  });
  
  // 모든 카테고리 목록
  function list() {
    $.ajax({
      url: "./list_json.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: "reviewno=" +${param.reviewno}, 
      // data: "categrpno=" + categrpno, 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var panel = '';
 
        for(index=0; index < rdata.length; index++) {
          panel += "<TR>";
          panel += "<TD style='text-align: right ;'>"+rdata[index].name+"</TD>";
          panel += "<TD style='text-align: left ;'>"+rdata[index].rdate.substring(0,10);
          panel += "<br>"+rdata[index].content+"</TD>";
          panel += "<TD ></TD>";
          panel += "<TD ></TD>";
          panel += "<TD ></TD>";
/*       panel += "<TD style='text-align: center;'>"; 
          panel += "  <A href=\"javascript:update("+rdata[index].categoryno+")\"><IMG src='./images/update.png' title='수정' style='width: 20px;'></A>";  
          panel += "  <A href=\"javascript:deleteForm("+rdata[index].categoryno+")\"><IMG src='./images/delete.png' title='삭제' style='width: 20px;'></A>";
          panel += "</TD>";  */
          panel += "</TR>";
        }
        // alert(panel);
        // return;
        $('#tbody_panel').empty();
        $('#tbody_panel').append(panel);
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

  // 등록 처리
  function create() { 
    $.ajax({
      url: "./create_json.do", // 요청을 보낼주소
      type: "post",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: $('#frm_create').serialize(),  
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
       alert("댓글 등록");
        
        action_cancel();
        
        list();  // 전체 카테고리 목록
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
        
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        alert("댓글 등록 실패");
        
        $('#main_panel').html(panel);
        $('#main_panel').show();
 
      }
    });
  }

 
  
  function action_cancel() {
    $('#panel_update').hide();
    $('#panel_delete').hide();
    $('#panel_create').show();

    $('#frm_create')[0].reset(); // id가 frm_create인 첫번째폼을 reset
  }
  
</script>
 
</head> 
 
<body>
<DIV class='container' style='width: 100%;'>
<DIV class='content' style='width: 100%;'>
  
  <DIV id='main_panel'></DIV>
  
      
      <div class="container">
       <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <!-- 개발시 임시 값 사용 -->
      <!-- 
      <input type='hidden' name='categrpno' id='categrpno' value='1'>
       -->
        <input type='hidden' name='memberno' id='memberno' value='${sessionScope.memberno }'>
        <input type='hidden' name='reviewno' id='reviewno' value='${param.reviewno }'>
       <br><br>
        <div>
            <div class="text-left">
                <span><strong>Comments</strong></span> 
                <input name='name' id='name' value='${sessionScope.name }'>
            </div>
            <div>
                <table class="table">                    
                    <tr>

                        <td>
                           
                            <textarea style="width: 1100px" rows="3" cols="30" id="content" name="content" value=''  placeholder="댓글을 입력하세요"></textarea>
                            <br>
                            <button type="button" id='submit'  class='btn btn-primary pull-right'  onclick="create()">등록</button>
                        </td>
                    </tr>
                </table>
            </div>
        </div>        
    </form>
</div>
 
             
<!--       <label for='title'>카테고리 이름</label>
      <input type='text' name='content' id='content' size='10' value='' required="required" style='width: 10%;'>
  
 
      <button type="button" id='submit' class='btn btn-primary'  onclick="create()">등록</button>
      <button type="button" class='btn btn-primary'  onclick="action_cancel()">취소</button>
    </FORM> -->
  </DIV>
  
<TABLE class='table'>
  <colgroup>
<%--     <col style='width: 10%;'/>
    <col style='width: 10%;'/> --%>
    <col style='width: 10%;'/>
    <col style='width: 30%;'/>
    <col style='width: 20%;'/>
    <col style='width: 30%;'/>
    <col style='width: 5%;'/>


 
  </colgroup>

 
  <tbody id='tbody_panel' data-nowPage='0' data-endPage='0'>
  </tbody>
  
</TABLE>
 
 
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
 
</html> 
 
<%@ page contentType="text/html; charset=UTF-8" %>
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

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

<script type="text/JavaScript">
function send(){
  var frm = $('#frm');
  if($('#survey_title',frm).val()==''){
    alert('설문조사 타이틀을 입력해주세요.')
    $('#survey_title', frm).focus();
    return;
  }
   if($('#startdate',frm).val()==''){
    alert('시작날짜를 입력해주세요.')
    $('#startdate', frm).focus();
    return;
  }
  if($('#enddate',frm).val()==''){
    alert('끝난 날짜를 입력해주세요.')
    $('#enddate', frm).focus();
    return;
  }
  $('#frm').submit();  // 서브밋

  }
</script>

</head> 
<body>
<DIV class='container' style='width:100%;'>
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<DIV class='content'>

<DIV class='title_line' style='width:100%'>설문조사 그룹 등록</DIV>

<FORM name='frm' id='frm'method='POST' action='./update.do'
             enctype="multipart/form-data" class="form-horizontal">
       <input type='hidden' name='surveyno' id='surveyno' value='${param.surveyno}'> 
       <input type='hidden' name='surveyitemno' id='surveyitemno' value='${param.surveyitemno}'> 
      <div class="form-group">   
        <label for="question" class="col-md-2 control-label">질문 타이틀</label>
        <div class="col-md-4">
          <input type='text' class="form-control input-lg" name="question" id="question" placeholder="" value="${survey_itemVO.question}" required ></input>
        </div>
      </div>   
      
      <div class="form-group">   
        <label for="seqno" class="col-md-2 control-label">출력순서</label>
        <div class="col-md-1">
          <input type='number' class="form-control input-lg" name='seqno' id='seqno' value="${survey_itemVO.seqno}"></input>
        </div>
      </div>
      
          <div id='file1Panel' class="form-group">
        <label class="col-md-2 control-label"></label>
        <div class="col-md-10">
          <c:if test="${file_list.size() > 0 }">
              <DIV>
                <c:forEach var ="fileVO"  items="${file_list }">
                  <IMG src='./storage/${fileVO.thumb}' style='margin-top: 2px;'></A>
                </c:forEach>
              </DIV>
            </c:if>
        </div>
      </div>
      
      <div class="form-group">   
        <label for="filesMF"class="col-md-2 control-label">이미지</label>
        <div class="col-md-5">
          <input  type="file" name='filesMF' id='filesMF' size='40' multiple="multiple"> (10 MB 이하만 전송 가능)
        </div>
      </div>
      
  
     <DIV style='text-align: right;'>
       <button class="btn btn-primary " type="submit" style='width:20%;'>수정</button>
        <button class="btn btn-primary " onclick='window.close();'>취소</button>
       
      </DIV> 
    </FORM>
  </DIV>


</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</DIV> <!-- container END -->
</body>

</html> 
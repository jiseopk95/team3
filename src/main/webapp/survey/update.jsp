<%@ page contentType="text/html; charset=UTF-8" %>

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


</script>

</head> 
<body>
<DIV class='container' style='width:100%;'>
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<DIV class='content'>

<DIV class='title_line' style='width:100%'>설문조사 그룹 등록</DIV>

<FORM name='frm' method='POST' action='./update.do'
             enctype="multipart/form-data" class="form-horizontal">
             <input type='hidden' name='surveyno' id='surveyno' value='${surveyVO.surveyno }'> 
             
      <div class="form-group">   
        <label for="survey_title" class="col-md-2 control-label">설문조사 타이틀</label>
        <div class="col-md-5">
          <input type='text' class="form-control input-lg" name='survey_title' id='survey_title' value='${surveyVO.survey_title}'required="required" style='width: 100%;'></input>
        </div>
      </div>   
      
      <div class="form-group">   
        <label for="seqno" class="col-md-2 control-label">출력순서</label>
        <div class="col-md-2">
          <input type='number' class="form-control input-lg" name='seqno' id='seqno' value='${surveyVO.seqno}'style='width: 30%;'></input>
        </div>
      </div>
      
      <div class="form-group">   
        <label for="startdate" class="col-md-2 control-label">시작 날짜</label>
        <div class="col-md-5">
          <input type="date" name='startdate'  value='${surveyVO.startdate}'>
        </div>
      </div>
      
            <div class="form-group">   
        <label for="enddate" class="col-md-2 control-label">종료 날짜</label>
        <div class="col-md-5">
          <input type="date" name='enddate'  value='${surveyVO.enddate }' >
        </div>
      </div>
   
      <DIV style='text-align: center;'>
         <button type="submit">수정</button>
        <button type="button"onclick='window.close()'>취소</button>
      </DIV>
    </FORM>
  </DIV>


</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</DIV> <!-- container END -->
</body>

</html> 


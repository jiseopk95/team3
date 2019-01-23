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

<DIV class='title_line' style='width:100%'>Answer 등록</DIV>


<FORM name='frm' id='frm' method='POST' action='./create.do'
             enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='managerno' id='managerno' value='1'> 
 

      
      <div class="row">
          <div class="col-md-5 mb-2">
            <label for="survey_title">설문조사 타이틀</label>
            <input type="text" class="form-control" name="survey_title" id="survey_title" placeholder="" value="" required >
          </div>
          
          <div class="col-md-1 mb-2">
            <label for="seqno">Seqno</label>
            <input type="number" class="form-control" name="seqno" id="seqno" placeholder="" value="1" required >
          </div>
          
         <div class="col-md-3 mb-2">
            <label for="startdate">Startdate</label>
            <input type="date" class="form-control" name="startdate" id="startdate" placeholder="" value="2019-01-15" required >
          </div>
          
          <div class="col-md-3 mb-2">
            <label for="enddate">Enddate</label>
            <input type="date" class="form-control" name="enddate" id="enddate" placeholder="" value="2019-01-15"  required >
          </div>
          
        <label></label>
       <div style="text-align : right; margin-right:15px;">
            <button class="btn btn-primary " type="submit" style='width:10%;'>등록</button>
            <button class="btn btn-primary " onclick="location.href='./list.do'" style='width:10%;'>취소</button>
       </div>  
     </div> 

         
     
    </FORM>



</DIV> <!-- content END -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</DIV> <!-- container END -->
</body>

</html> 


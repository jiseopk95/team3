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
  
</script>


</head> 


<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' style='width: 100%;'>

<DIV class='content' style='padding-top:5%; padding-bottom: 10%;'>
<DIV class='title_line' style='width: 20%;'>등록 동물 수정</DIV>
  
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
  

  <FORM name='frm' id='frm' method='POST' action='./update.do'  enctype="multipart/form-data" 
              onsubmit="return send();" class="form-horizontal">
    <input type='hidden' name='petno' id='petno' value='${petVO.petno }'>          

    <input type='hidden' name='memberno' id='memberno' value='${petVO.memberno }'>

    <br><br>
       <label for="name" >이름</label> &nbsp
        <input type='text'  name='name' id='name' 
                   value='${petVO.name }' required="required" style='width: 30%;' placeholder="반려동물 이름"> <br><br>

    
   
      <label for="age" >나이</label> &nbsp
      <input type='text' name='age' id='age' value='${petVO.age }' required="required" style='width: 30%;' placeholder="나이" autofocus="autofocus"> 
    <br><br>

    <label for="gender" >성별</label> &nbsp 
      <label><input type="radio" name="gender" id='gender1' value='수컷'  />수컷&nbsp</label>
      <label><input type="radio" name="gender" id='gender2' value='암컷' />암컷</label>
    <br><br>
    
    <!-- <div class="form-group">
      <label for="gender" class="col-md-2 control-label">성별</label>    
      <div class="col-md-10">
        <input type='text' class="form-control input-md" name='gender' id='gender' value='1' required="required" style='width: 30%;' placeholder="성별" autofocus="autofocus">
      </div>
    </div>  -->
                
   
      <label for="pet_type" >종류</label> &nbsp   
        <input type='text' name='pet_type' id='pet_type' value='${petVO.pet_type }' required="required" style='width: 30%;' placeholder="종류">
<br><br>

    <label for="neutralization" >중성화 여부</label> &nbsp
      <input type="radio" name="neutralization" id='neutralization1' value='N'  > N 
      <input type="radio" name="neutralization" id='neutralization2' value='Y' > Y
      <br><br>
    <!-- <div class="form-group">
      <label for="neutralization" class="col-md-2 control-label">중성화 여부</label>    
      <div class="col-md-10">
        <input type='text' class="form-control input-md" name='neutralization' id='neutralization' value='Y' required="required" style='width: 30%;' placeholder="중성화 여부">
      </div>
    </div>  -->
    <label for="weight" >체중</label> &nbsp    
        <input type='text' name='weight' id='weight' value='${petVO.weight }' required="required" style='width: 30%;' placeholder="몸무게">
        
     <br><br> 
     
     <div id='file1Panel' class="form-group">
        <label class="col-md-2 control-label"></label>
        <div class="col-md-10">
          <c:if test="${file_list.size() > 0 }">
              <DIV>
                <c:forEach var ="fileVO"  items="${file_list }">
                  <A href="javascript: panel_img('${fileVO.files }')"><IMG src='./storage/${fileVO.thumbs }' style='margin-top: 2px;'></A>
                </c:forEach>
              </DIV>
            </c:if>
        </div>
      </div>     
       <div class="form-group">   
        <label for="files" class="col-md-1 control-label">업로드 파일</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40' multiple="multiple">
          <br>
        
        </div>
      </div>
   
    <br><br>
    
        <button type="submit" class="btn btn-primary">수정</button>
        <button type="button" onclick="history.go(-1);" class="btn btn-secondary">취소</button>

  </FORM>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 



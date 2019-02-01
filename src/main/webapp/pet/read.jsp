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
<DIV class='content' style='padding-top:5%; width: 80%; padding-bottom: 10%;'>
<DIV class='title_line' style='width: 20%;'>등록 동물 조회</DIV>
  
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
  

  <FORM name='frm' id='frm' method='POST' action='./list.do' 
              onsubmit="return send();" class="form-horizontal">
    <input type='hidden' name='petno' id='petno' value='${petVO.petno }'>          

    <input type='hidden' name='memberno' id='memberno' value='${memberno }'>

    <br><br>  
      
       <IMG style='border-radius: 50%;width: 30%;height: 30%; ' id='files' src='./storage/${petVO.files }'> <br> <br><br>
       
       <label for="name" >이름</label> ${petVO.name } <br><br>
       
      <label for="age" >나이</label> ${petVO.age } <br><br>

      <label for="gender" >성별</label> ${petVO.gender } <br><br>
                
      <label for="pet_type" >종류</label> ${petVO.pet_type } <br><br>

      <label for="neutralization" >중성화 여부</label> ${petVO.neutralization } <br><br>

      <label for="weight" >체중</label> ${petVO.weight }   <br><br>
        
      
    <br><br>
    
        <!-- <button type="submit" class="btn btn-primary">수정</button> -->
        <button type="button" onclick="history.go(-1);" class="btn btn-secondary">목록</button>

  </FORM>

</DIV> <!-- content END -->

</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 



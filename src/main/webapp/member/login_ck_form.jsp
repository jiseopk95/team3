<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>로그인</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/jquery.cookie.js"></script>

</head> 

<body>
<jsp:include page="/menu/top.jsp" flush='false'/>
<DIV class='container' style='width: 100%;'>
<DIV class='content' style='padding-top:5%; padding-bottom: 10%;'>

<DIV class='title_fontss' style='width: 20%;'>&nbsp&nbsp&nbspLOGIN</DIV>

<!--    <ASIDE style='float: right;'>
    <A href='./create.do'>JOIN &nbsp</A>
  </ASIDE>

<div class='menu_line'></div> -->
 
  <FORM name='frm' method='POST' action='./login.do'>
  &nbsp&nbsp&nbsp&nbsp
       <label class=fontss ><input type="radio" name="myname" OnClick="window.location.href='../member/login.do'" checked>&nbsp&nbsp일반회원&nbsp</label>
       <label class=fontss ><input type="radio" name="myname" OnClick="window.location.href='../manager/login.do'">&nbsp직원&nbsp&nbsp&nbsp</label>
   <br><br>
      <label class=fontss >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspID</label><span class='menu_divide' >│</span>    
        <input type='text' class='loginfont' name='id' id='id' value='${ck_id }' required="required" style='width: 18%;' placeholder="" autofocus="autofocus">
         &nbsp
        <Label >   
          <input type='checkbox' name='id_save' value='Y' 
                    ${ck_id_save == 'Y' ? "checked='checked'" : "" }> 
        </Label>
          <label class=fontss >저장</label>    
  <br>
        <label class=fontss >PASSWORD</label><span class='menu_divide' >│</span>    
        <input type='password' class='loginfont' name='passwd' id='passwd' value='${ck_passwd }' required="required" style='width: 18%;' placeholder="">
        &nbsp
        <Label>
          <input type='checkbox' name='passwd_save' value='Y' 
                    ${ck_passwd_save == 'Y' ? "checked='checked'" : "" }>
        </Label>
        
       
        <label class=fontss >저장</label>    <br><br>
      &nbsp&nbsp&nbsp <A style='font-size: 13px' href='./list_id.do'>아이디 찾기</A><span class='menu_divide' >│</span><A style='font-size: 13px' href='./list_passwd.do'>비밀번호 찾기</A>
      <br><br>&nbsp&nbsp
        <button type="submit" class="btn btn-primary">로그인</button>
        <button type="button" onclick="location.href='./create.do' " class="btn btn-secondary">회원가입</button>

  </FORM>

</DIV> <!-- content END -->
</DIV>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 
 
 
 
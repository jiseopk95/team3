<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    
</head> 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='container' style='width: 100%;'>
<DIV class='content'>

<DIV class='title_line'>비밀글 보기</DIV>

<DIV class='message'>
<FORM name='frm' method='POST' action='./passwd_check.do'>
  <fieldset class='fieldset_basic'>
  
    <input type='hidden' name='questionno' id='questionno' value='${param.questionno }'>
    
    <UL> 
     <LI class='li_none' style='text-align:center';>이 글은 비밀글입니다. 비밀번호를 입력하여 주세요.<br>
                                         관리자는 확인버튼만 누르시면 됩니다.</LI> <br>

        
    <LI class='li_none' style='text-align:center';>  
        <label for="passwd">비밀번호 |</label>
        <input type='password' name='passwd' id='passwd' value=''${ck_passwd } required="required" style='width: 20%;' placeholder="패스워드">
           </LI>  

    
      <LI class='li_none' style='text-align:center';>
        <br><br>
        <button type='button' class ='btn btn-primary' onclick="location.href='./list.do?categoryno=1'">목록</button>
     <!--    <button type="submit" class = "btn btn-primary">확인</button> -->
     <button type='button' class ='btn btn-primary'  onclick="location.href='./reply_read.do?questionno=${param.questionno}'">확인</button> 
      </LI>
     </UL>
  </fieldset>
</FORM>
</DIV>

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 

   
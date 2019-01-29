<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>MY PAGE</title> 

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

<DIV class='title_fontss' style='width: 20%;'>MY PAGE</DIV>

 
  <FORM name='frm' method='POST' action='./login.do'>
         <img class="img-fluid mb-5 d-block mx-auto" style='margin-top:5%; width: 20%; height: 20%;' src="../resources/img/profile.png" >  <!-- 관련 이미지 넣거나 넣지 않음 --> <br>
        <button type="button" onclick="location.href='../member/read.do?memberno=${memberno }' " class="btn btn-primary" style="width: 20%;height: 20%;">나의 정보 조회</button>
        <button type="button" onclick="location.href='./pet_list.do?memberno=${memberno}'" class="btn btn-secondary" style="width: 20%;height: 20%;">반려동물 정보 조회</button>

  </FORM>

</DIV> <!-- content END -->
</DIV>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 
 
 
 
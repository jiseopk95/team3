<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP --> Django</title>
<script type="text/JavaScript"
        src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
  function send(f) {
    var species = f.species.value;
    var food = f.food.value;
    var walk = f.walk.value;
    var play = f.play.value
    var bath = f.bath.value
    var hospital=f.hospital.value
    var medicine=f.medicine.value
    
    var data = species+","+food+","+walk+","+play+","+bath+","+hospital+","+medicine;
    f.data.value = data;
    /* alert(data); */
    f.submit();
     
  }
</script>
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    


<style type="text/css">
  *{
    font-size: 26px;
  
  }
</style>

</head>
<body>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' style="width:80%;" >
<DIV class='content' style='margin-top: 5%; margin-bottom:5%'>

 <aside style="text-align:left; font-weight: bold; font-size:35px; ">반려견 수명 예측 시스템</aside><br>
 <br>

 <form name='frm_send' action='http://127.0.0.1:8000/ai/puppy_django' method='GET'>
   <input type='hidden' name='data' id='data' value=''>
 
<!--   <input type='text' name='data' value='5,2,2,1,2,3,1' style='width: 60%;'><br> -->

  <label>
    <img src='./images/puppy1.jpg' style='width: 150px; height: 100px;'>
    <input type='radio' value='1' checked="checked" name='species' id='species'>시츄
  </label>
  <label> 
    <img src='./images/puppy2.jpg' style='width: 150px; height: 100px;'>
    <input type='radio' value='2' name='species' id='species'>푸들
  </label>
  <label>
    <img src='./images/puppy3.jpg' style='width: 150px; height: 100px;'> 
    <input type='radio' value='3' name='species' id='species'>닥스훈트
  </label>
  <label>
    <img src='./images/puppy4.jpg' style='width: 150px; height: 100px;'>
    <input type='radio' value='4' name='species' id='species'>포메라니안
  </label>
  <label> 
    <img src='./images/puppy5.jpg' style='width: 150px; height: 100px;'>
    <input type='radio' value='5' name='species' id='species'>시베리안 허스키
  </label><br>
  <br><br>
  <div style="text-align:left;">
 하루 밥을 주는 횟수:<input type='number' name='food' value='1' style='width: 15%;'><br>
 1주 단위 산책횟수:<input type='number' name='walk' value='2' style='width: 15%;'><br>
 1주 단위 반려견과 놀아주는 시간: <input type='number' name='play' value='5' style='width: 15%;'><br>
월별 반려견 목욕 횟수: <input type='number' name='bath' value='2' style='width: 15%;'><br>
년간 동물 병원 치료 횟수: <input type='number' name='hospital' value='3' style='width: 15%;'><br>
년간 예방 주사 투약 횟수:<input type='number' name='medicine' value='5' style='width: 15%;'><br><br>
</div>
  <input style="float:left;"type='button' value='수명 확인' onclick="send(this.form);">
</form>
</DIV> <!-- content END -->
</DIV> <!-- container END -->

</body>
</html>


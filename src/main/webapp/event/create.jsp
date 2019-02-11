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
<script type="text/javascript">

/* var present_array = ["가","나"]; // 선물 배열 */ 


</script>
<!--  <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
 <script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script> -->
 
</head> 

<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' >
<%-- <DIV class='content'> 

  <ASIDE style='float: left;'>
      <A href='../category/list.do'>게시판 목록</A>  
      >
      <A href='./list.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>등록</A>
  </ASIDE>   --%>
    
<!--   <div class='menu_line'></div> -->
  <DIV class='content' style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'> 
    <FORM name='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
            <%--    <input type='hidden' name='styleno' id='styleno' value=' ${beautyVO.styleno}'>  --%>
            <!--  create.cont 가보면 폼에 beautyVO가 없는데 내가 여기서 beautyVO.styleno를 쓰려 하니까 에러가 나는거임. -->
     <input type = 'hidden' name='managerno' id='managerno' value='${param.managerno}'> 
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='이달의 이벤트' required="required" style='width: 80%;'>
        </div>
      </div>   
       <div class="form-group">   
        <label for="content" class="col-md-1 control-label">이벤트<br>상세내용</label>
        <div class="col-md-11">
           <textarea class="form-control input-lg" name='content' id='content'  rows='10'>이벤트입니다.</textarea>
        </div>
      </div>
          <div class="form-group">   
        <label for="presentno" class="col-md-1 control-label">선물</label>
        <div class="col-md-11">
     <select class="form-control" id="present" name="presentno">
            <option value="">--선택해주세요--</option>
            <c:forEach var="event_presentVO" items="${list_present }">
            <option value="${event_presentVO.presentno }">${event_presentVO.info }</option>
            </c:forEach><!-- 내가 안나온 이유 - controller에 등록get에서 이거 나오게 값을 안설정해줌 추가를 안해줌. -->
          </select>
        </div>
      </div>
      <div class="form-group">   
        <label for="writer" class="col-md-1 control-label">작성자</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='writer' id='writer' value='${sessionScope.name}' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="period_start" class="col-md-1 control-label">시작일</label>
        <div class="col-md-11">
           <input type='date' class="form-control input-lg" name='period_start' id='period_start' value="2019-01-27" required="required" style='width: 30%;'>
        </div>
      </div>
       <div class="form-group">   
        <label for="period_end" class="col-md-1 control-label">종료일</label>
        <div class="col-md-11">
           <input type='date' class="form-control input-lg" name='period_end' id='period_end' value="2019-03-01" required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="usercnt" class="col-md-1 control-label">최대응모 인원수</label>
        <div class="col-md-11">
           <input type='number' class="form-control input-lg" name='usercnt' id='usercnt' value='30' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="windate" class="col-md-1 control-label">당첨발표</label>
        <div class="col-md-11">
           <input type='date' class="form-control input-lg" name='windate' id='windate' value="2019-03-08" required="required" style='width: 30%;'>
        </div>
      </div>
      <div class="form-group">   
        <label for="filesMF" class="col-md-1 control-label">업로드<br>파일</label>
        <div class="col-md-11">
          <input type="file" class="form-control input-lg" name='filesMF' id='filesMF' size='40' multiple="multiple">
          <br>
          Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
      </div>   

      <DIV style='text-align: right;'>
        <button type="submit" class="btn btn-primary btn-sm">등록</button>
        <button type="button" class="btn btn-primary btn-sm" onclick="location.href='./search_paging.do?managerno=${param.managerno}'">취소[목록]</button>
      </DIV> 
    </FORM>
<!-- </DIV> -->

</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 


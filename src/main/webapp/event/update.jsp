<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>글 수정</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

/* <script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  }; */
/*  var info = $("#presentno").find(":selected").val();
  $('#info').val(info).prop("selected", true);
  var info = $("select[name=presentno]").val();
  $('#info').val(info).prop("selected", true); */
  $(function(){//화면로딩즉시 실행되는놈 
<%-- <%=keyword.equals("민사")?" selected":""%> --%>
  var presentno = ${eventVO.presentno};
$("#presentno").val(presentno);
  });
	  </script>

</head> 

<body id="page-top">
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' >
<DIV class='content' style='width: 100%; margin:0px auto; text-align: center; margin-top: 10%; margin-bottom: 10%'>     

<%--   <ASIDE style='float: left;'>
    <A href='../category/list.do'>게시판 목록</A>
    <span style='font-size: 1.2em;'>></span>   
    <A href='./list_by_categoryno.do?categoryno=${categoryVO.categoryno }'>${categoryVO.title }</A>
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do?categoryno=${categoryVO.categoryno }'>등록</A>

  </ASIDE>  --%>

<!--   <div class='menu_line'></div> -->
  <FORM name='frm' method='POST' action='./update.do'
             enctype="multipart/form-data" class="form-horizontal">
      <input type='hidden' name='eventno' id='eventno' value='${eventVO.eventno }'>
      <input type='hidden' name='managerno' id='managerno' value='${eventVO.managerno }'>
      
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='${eventVO.title}' required="required" style='width: 80%;'>
        </div>
      </div>   
       <div class="form-group">   
        <label for="content" class="col-md-1 control-label">이벤트<br>내용</label>
        <div class="col-md-11">
           <textarea class="form-control input-lg" name='content' id='content'  rows='10'>${eventVO.content}</textarea>
        </div>
      </div>
           <div class="form-group">   
        <label for="presentno" class="col-md-1 control-label">선물</label>
        <div class="col-md-11">
     <select class="form-control" id="presentno" name="presentno">
                 <option value="default">--선물 리스트--</option>
                <c:forEach var="event_presentVO" items="${list_present }">
            <option value="${event_presentVO.presentno }" id="info">${event_presentVO.info }</option>
            </c:forEach><!-- 내가 안나온 이유 - controller에 등록get에서 이거 나오게 값을 안설정해줌 추가를 안해줌. -->
          </select>
          
        </div>
      </div>
      <div class="form-group">   
        <label for="writer" class="col-md-1 control-label">작성자</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='writer' id='writer' value='${eventVO.writer}' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="period_start" class="col-md-1 control-label">시작일</label>
        <div class="col-md-11">
           <input type='date' class="form-control input-lg" name='period_start' id='period_start' value='${eventVO.period_start}' required="required" style='width: 30%;'>
        </div>
      </div>
       <div class="form-group">   
        <label for="period_end" class="col-md-1 control-label">종료일</label>
        <div class="col-md-11">
           <input type='date' class="form-control input-lg" name='period_end' id='period_end' value='${eventVO.period_end}' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="usercnt" class="col-md-1 control-label">최대응모 인원수</label>
        <div class="col-md-11">
           <input type='number' class="form-control input-lg" name='usercnt' id='usercnt' value='${eventVO.usercnt}' required="required" style='width: 30%;'>
        </div>
      </div>
        <div class="form-group">   
        <label for="windate" class="col-md-1 control-label">당첨발표</label>
        <div class="col-md-11">
           <input type='date' class="form-control input-lg" name='windate' id='windate' value='${eventVO.windate}' required="required" style='width: 30%;'>
        </div>
      </div>
         <div id='file1Panel' class="form-group">
        <label class="col-md-2 control-label"></label>
        <div class="col-md-10">
          <c:if test="${file_list.size() > 0 }">
              <DIV>
                <c:forEach var ="fileVO"  items="${file_list }">
                  <A href="javascript: panel_img('${fileVO.file }')"><IMG src='./storage/${fileVO.thumb }' style='margin-top: 2px;'></A>
                </c:forEach>
              </DIV>
            </c:if>
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
        <button type="submit" class="btn btn-primary btn-sm">변경된 내용 저장</button>
<button type="button" class="btn btn-primary btn-sm" onclick="location.href='./search_paging.do?managerno=${param.managerno}'">취소[목록]</button>
      </DIV>
  </FORM>


</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html> 
 
 

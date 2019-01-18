<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>예약페이지</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
// 의료 총 예약 가능 시간
var time_h_array = ["오전 10:00", "오전 10:30", "오전 11:00", "오전 11:30", "오후 12:00", "오후 12:30","오후 1:00", 
	    										"오후 1:30", "오후 2:00", "오후 2:30", "오후 3:00","오후 3:30", "오후 4:00", "오후 4:30", "오후 5:00", 
	    										"오후 5:30","오후 6:00", "오후 6:30", "오후 7:00"];
// 미용 총 예약 가능 시간
var time_b_array = ["오전 10:00", "오전 10:30", "오전 11:00", "오전 11:30", "오후 12:00", "오후 12:30","오후 1:00", 
                    			"오후 1:30", "오후 2:00", "오후 2:30", "오후 3:00","오후 3:30", "오후 4:00", "오후 4:30", "오후 5:00", 
                    			"오후 5:30","오후 6:00", "오후 6:30", "오후 7:00"];
var time_h_array_copy = new Array(); // 의료 예약된 시간을 담을 배열
var time_b_array_copy = new Array();

var list_h = new Array(); // 의료 예약된 시간을 담을 배열
var list_b = new Array(); // 미용 예약된 시간을 담을 배열

var index = 0;

var time_schedule = "<div id='time_schedule'>"  
	+"<label for='title' class='col-md-1 control-label'>약속시간</label>"
	+"<div class='col-md-11'>"
	+"<input type='text' class='form-control input-lg' name='restime' required='required'>"
	+"</div>"
	+"</div>";

var time_h = '<div class="class="form-group" id="time_h">'
+'<label for="restime" class="col-md-1 control-label">예약시간</label>'
+ '<div class="col-md-11">'
+'<select class="form-control" id="restime_h" name="restime">'
+'<option value="default">-- 병원 예약 가능 시간 --</option>'
+'</select>'
+'</div>'
+'</div>';

var time_b = '<div class="class="form-group" id="time_b">'
+'<label for="restime" class="col-md-1 control-label">예약시간</label>'
+ '<div class="col-md-11">'
+'<select class="form-control" id="restime_b" name="restime">'
+'<option value="default">-- 미용 예약 가능 시간 --</option>'
+'</select>'
+'</div>'
+'</div>';

	$(function() {
	  
	  $('#time_div').append(time_h);
	 	$('#time_schedule').remove();
	 	
	 	for(var i = 0; i < time_h_array.length; i++) {
      time_h_array_copy[i] = time_h_array[i];
      time_b_array_copy[i] = time_b_array[i];
    }
		// cont에서 받아온 예약된 리스트를 list_h에 담아라
		<c:forEach var="Time_hVO" items="${list_h}">
			list_h.push("${Time_hVO.restime}");
		</c:forEach>
		
		// cont에서 받아온 예약된 리스트를 list_b에 담아라
		<c:forEach var="Time_bVO" items="${list_b}">
			list_b.push("${Time_bVO.restime}");
		</c:forEach>
		
	// time_array와 list_h의 겹치는 부분이 있다면 제거해라
		for(var i = 0; i < list_h.length; i++) {
		  console.log("list_h내용 : " + list_h[i]);
		  index = time_h_array_copy.indexOf(list_h[i]);
		  if(index > -1) {
		    time_h_array_copy.splice(index, 1);
		  }
		}
	// time_array와 list_b의 겹치는 부분이 있다면 제거해라
		for(var i = 0; i < list_b.length; i++) {
		  console.log("list_b내용 : " + list_b[i]);
		  index = time_b_array_copy.indexOf(list_b[i]);
		  if(index > -1) {
		    time_b_array_copy.splice(index, 1);
		  }
		}
		
	// time_h_array(예약된 시간이 제거된 리스트의 상태)를 option으로 추가해라
		for(var i = 0; i < time_h_array_copy.length; i++) {
		  $('#restime_h').append('<option value="' + time_h_array_copy[i] + '">' + time_h_array_copy[i] + '</option>');
		}
		// time_b_array(예약된 시간이 제거된 리스트의 상태)를 option으로 추가해라
		for(var i = 0; i < time_b_array_copy.length; i++) {
		  $('#restime_b').append('<option value="' + time_b_array_copy[i] + '">' + time_b_array_copy[i] + '</option>');
		}
		
		

		// 라디오버튼의 클릭이벤트 - select box disabled 시키기
		$('.restype').click(function() {
		  var restype = $(':radio[name="restype"]:checked').val();
		  
		  if(restype == 1) {
		    $('#time_h').remove();
		    $('#time_b').remove();
		    $('#time_schedule').remove();
		    
		    $('#time_div').append(time_h);
		    
		    $('#name').val("default").prop("selected", true);
		    
		    for(var i = 0; i < time_h_array_copy.length; i++) {
				  $('#restime_h').append('<option value="' + time_h_array_copy[i] + '">' + time_h_array_copy[i] + '</option>');
				}
		    
		  } else if(restype == 2) {
		    $('#time_h').remove();
		    $('#time_b').remove();
		    $('#time_schedule').remove();
		    
		    $('#time_div').append(time_b);
		    
		    $('#name').val("default").prop("selected", true);
		    
		 // time_b_array(예약된 시간이 제거된 리스트의 상태)를 option으로 추가해라
				for(var i = 0; i < time_b_array_copy.length; i++) {
				  $('#restime_b').append('<option value="' + time_b_array_copy[i] + '">' + time_b_array_copy[i] + '</option>');
				}
		    
		  } else if(restype == 3) {
		    $('#time_h').remove();
		    $('#time_b').remove();
		    $('#time_schedule').remove();	// 여러번 time_schedule이 생성되는거를 막기 위해 지우고 다시 생성함 
		    
		    $('#time_div').append(time_schedule);
		    $('#name').val("개인").prop("selected", true);
		  }
		    
		});
	});
	
	function check() {
	  if($('radio[name=restype]').val() == "" ) {
	   	alert("예약유형을 선택해주세요");
	  } else if($('#name').val() == "default") {
	    alert("예약동물의 이름을 선택해주세요");
	    $('#name').focus();
	  } else if($('#title').val() == "") {
	    alert("제목을 작성해주세요");
	    $('#title').focus();
	  } else if($('#restdate').val() == "") {
	    alert("예약날짜를 선택해주세요");
	    $('#restdate').focus();
	  } else if($('select[name=restime]').val() == "default" | $('input[name=restime]').val() == "") {
	    alert("예약시간을 선택해주세요");
	    $('select[name=restime]').focus();
	    $('input[name=restime]').focus();
	  } else if($('#content') == "") {
	    alert("내용을 작성해주세요");
	    $('#content').focus();
	  } else {
	    $('#frm').submit();
	  }
	};
	
	function handler(e){
    $.ajax({
      url: "./resdate.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: "resdate=" + e.target.value, 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        time_h_array_copy = [];
        time_b_array_copy = [];
        list_h = [];
        list_b = [];
        $('#restime_h').empty();
        $('#restime_b').empty();
        
        for(var i = 0; i < time_h_array.length; i++) {
          time_h_array_copy[i] = time_h_array[i];
          time_b_array_copy[i] = time_b_array[i];
        }
        
        for(var j=0; j < rdata.length; j++) {
          if(rdata[j].restype == 1) {
         // cont에서 받아온 예약된 리스트를 list_h에 담아라
        			list_h.push(rdata[j].restime);
        			//console.log("의료시간 : " + rdata[j].restype + ", " + rdata[j].restime);  
          } else if(rdata[j].restype == 2) {
         // cont에서 받아온 예약된 리스트를 list_b에 담아라
            list_b.push(rdata[j].restime);
            //console.log("미용시간 : " + rdata[j].restype + ", " + rdata[j].restime);  
          }
        }
        
     // time_array와 list_h의 겹치는 부분이 있다면 제거해라
    		for(var i = 0; i < list_h.length; i++) {
    		  console.log("list_h내용 : " + list_h[i]);
    		  index = time_h_array_copy.indexOf(list_h[i]);
    		  if(index > -1) {
    		    time_h_array_copy.splice(index, 1);
    		  }
    		}
     
    	// time_array와 list_b의 겹치는 부분이 있다면 제거해라
    		for(var i = 0; i < list_b.length; i++) {
    		  console.log("list_b내용 : " + list_b[i]);
    		  index = time_b_array_copy.indexOf(list_b[i]);
    		  if(index > -1) {
    		    time_b_array_copy.splice(index, 1);
    		  }
    		}
    	
    	// time_h_array(예약된 시간이 제거된 리스트의 상태)를 option으로 추가해라
    		for(var i = 0; i < time_h_array_copy.length; i++) {
    		  //console.log("time_h_array_copy[i]내용 : " + time_h_array_copy[i]);
    		  $('#restime_h').append('<option value="' + time_h_array_copy[i] + '">' + time_h_array_copy[i] + '</option>');
    		}
    	
    	// time_b_array(예약된 시간이 제거된 리스트의 상태)를 option으로 추가해라
    		for(var i = 0; i < time_b_array_copy.length; i++) {
    		  //console.log("time_b_array의 요소 : " + time_b_array_copy[i]);
    		  $('#restime_b').append('<option value="' + time_b_array_copy[i] + '">' + time_b_array_copy[i] + '</option>');
    		}
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        
      }
    });
  }

</script>
</head>
<body>
<%
/* 예약날짜에 오늘날짜가 기본으로 떠라 */
Calendar cal = Calendar.getInstance();
String year = Integer.toString(cal.get(Calendar.YEAR));
String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
if (month.length() < 2) {
  month = "0" + month;
}
if (day.length() < 2) {
  day = "0" + day;
}
String today = year + "-" + month + "-" + day;
String date = year + "-" + month;
%>
<c:import url="/menu/top.jsp" /> <!--  top 부분 소스분리 -->
<DIV class='container' >
<DIV class='content'>
<h1>예약창</h1>
  <FORM name='frm' id='frm' method='POST' action='./create.do'
               enctype="multipart/form-data" class="form-horizontal">
               
      <input type='hidden' name='memberno' id='memberno' value='${param.memberno }'>
      <input type='hidden' name='petno' id='petno' value='1'>
      
      <div class="form-group">   
        <div class="col-md-11">
          <label class="radio-inline">
            <input type='radio' class='restype' name='restype' value='1' checked>의료
          </label>
          <label class="radio-inline">
            <input type='radio' class='restype' name='restype' value='2'>미용
          </label>
          <label class="radio-inline">
            <input type='radio' class='restype' name='restype' value='3'>개인
          </label>
        </div>
      </div>
      
      <div class="form-group">
          <label for="name" class="col-md-1 control-label">예약동물</label>
          <div class="col-md-11">
          <select class="form-control" id="name" name="name">
            <option value="default">-- 예약동물이름 --</option>
            <c:forEach var="reservationVO" items="${list }">
            <option value="${reservationVO.name }">${reservationVO.name }</option>
            </c:forEach>
            <option value="개인">개인 스케줄</option>
          </select>
        </div>
      </div>
      <div class="form-group">   
        <label for="title" class="col-md-1 control-label">제목</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='title' id='title' value='예약 타이틀' placeholder="예약내용 또는 스케줄의 제목을 입력해주세요" required="required" style='width: 80%;'>
        </div>
      </div>   
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">레이블</label>
        <div class="col-md-11">
          <input type='text' class="form-control input-lg" name='label' id='label' value='예약 레이블'  placeholder="달력에 표시될 제목을 입력해주세요" required="required" style='width: 80%;'>
        </div>
      </div>
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">예약날짜</label>
        <div class="col-md-11">
          <input type='date' class="form-control input-lg" name='resdate' id='resdate' value="${param.resdate}" 
                    min="<%=today %>" required="required" onchange="handler(event);">
        </div>
      </div>
      <div class="form-group" id="time_div">
      </div>
      <div class="form-group">   
        <label for="content" class="col-md-1 control-label">예약내용</label>
        <div class="col-md-11">
          <textarea class="form-control input-lg" name='content' id='content'  placeholder="참고사항 / 희망사항 / 스케줄을 입력해주세요" rows='10'>검진 / 미용 / 스케줄 내용</textarea>
        </div>
      </div>
      <DIV style='text-align: right;'>
        <button type="button" onclick="check();">등록</button>
        <button type="button" onclick="location.href='./my_list.do?memberno=${param.memberno}&date=<%=date%>'">취소</button>
      </DIV>
    </FORM>
    
</DIV> <!-- content END -->
</DIV> <!-- container END -->
<jsp:include page="/menu/bottom.jsp" flush='false' /> <!--  bottom 부분 소스분리 -->
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>YCAR</title>
<script src="http://54.180.123.46:3000/socket.io/socket.io.js"></script> 

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- datepicker -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- tmap -->
<script src="https://apis.openapi.sk.com/tmap/js?version=1&format=javascript&appKey=5beda631-7db0-4be9-b0bd-b6b5a7f41945"></script>
<!-- bootstrap -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- font -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<link rel="stylesheet" href="static/style.css">

<link rel="stylesheet" href="static/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" href="static/css/animate.css">
<link rel="stylesheet" href="static/css/owl.carousel.min.css">
<link rel="stylesheet" href="static/css/owl.theme.default.min.css">
<link rel="stylesheet" href="static/css/magnific-popup.css">
<link rel="stylesheet" href="static/css/aos.css">
<link rel="stylesheet" href="static/css/ionicons.min.css">
<link rel="stylesheet" href="static/css/flaticon.css">
<link rel="stylesheet" href="static/css/icomoon.css">

<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->

<style>
body {
   background-color: #FFFEF4;
   font-family: 'Noto Sans KR', sans-serif;
   text-align: center;
}
.container {
   margin-top: 100px;
   margin-bottom: 100px;
   min-width: 100px;
   width: 500px;
}
.ftco-navbar-light.scrolled {
   background-color: #FFFEF4 !important;
}
.boxes {
   text-align: center;
   margin: 0 auto;
   width: 70%
}
.modal-body {
   text-align: left;
}
.inputborder {
   border: 0px;
}
.rsvsbtn {
   display: inline-block !important;
   width: 100px !important;
   height: 40px;
}
.rsv {
   text-align: left;
   color: black;
}
</style>
</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300"> 
    
    <!--navbar 시작-->
	<%@ include file="frame/passengerNavbar.jsp"%>
	<!--navbar 끝 -->
	
<div class="container">
   <div id="title">
      <h2 class="mb-4">나의 카풀</h2>
   </div>
   <div class="row">
      <div class="col-md-12">
         <div class="search-wrap-1 ftco-animate p-4 boxes">
            <button id="confirmRsv" onclick="confirmRsv();" class="btn btn-primary rsvsbtn">확정된예약</button>
            <button id="waitingRsv" onclick="waitingRsv();" class="btn btn-primary rsvsbtn">대기예약</button>
            <button id="waitingRsv" onclick="pastRsv();" class="btn btn-primary rsvsbtn">지난예약</button>
         </div>
         <div class="boxes">
            <div id="confirmList"></div>
            <div id="waitingList"></div>
            <div id="pastList"></div>
         </div>
      </div>
   </div>
</div>
      <script>
      var p_idx = ${sessionScope.login.idx};
      var socket = io('http://54.180.123.46:3000/');

      function drivingStart (r_idx) {
    		//소켓 연결 
          console.log('소켓 연결을 위한 r_idx '+r_idx);
          socket.emit('join start room', r_idx); 
          socket.on('startroom join result', function(msg){
             console.log(msg);
          });
          
          alert('운전자가 운행 시작 버튼을 누르면 자동으로 운행이 시작됩니다. 조금만 기다려주세요!');
      }
      
      //운행중 page 로 redirect 
      socket.on('go driving page', function(r_idx){
         console.log('탑승자님, 운행 중 페이지로 이동하실게요 '+r_idx);
         setTimeout(function(){
            window.location.href="http://13.125.252.85:8080/passenger/passenger-driving.jsp?r_idx="+r_idx;
         }, 3000);
      });  
      
      $(document).ready(function() {
         confirmList();
         waitingList();
      });
      
      function confirmRsv(p_idx){
    	  var p_idx = ${sessionScope.login.idx};
         $('#waitingList').css('display', 'none');
         $('#pastList').css('display', 'none');
         $('#confirmList').css('display', 'block');
         $.ajax({
            url : 'http://13.125.252.85:8080/server/rsv/mycarpool/' + p_idx,
            type : 'GET',
            success : function(data){
               var html = '';
               for (var i = 0; i < data.length; i++) {
                  
                  html += '<div class="rsv">\n';
                  html += '예약일자 '+ data[i].r_date + '<br>\n';
                  html += '운전자 닉네임 ' + data[i].nickname + '<br>\n';
                  html += '카풀일시 '+ data[i].d_date + '\t' + data[i].d_starttime + '\t -\t ' + data[i].d_endtime + '<br>\n';
                  html += '출발지 '+ data[i].d_startpoint + '<br>\n';
                  html += '도착지 ' + data[i].d_endpoint + '<br>\n';
                  html += '요금 '+ data[i].d_fee +'원 <br>\n';
                  html += '<button id="delete" onclick="deleteRsv('+p_idx+',' + data[i].r_idx + ')" class="btn btn-primary">카풀취소</button>\t';
                  html += '\t<button class="btn btn-primary" onclick="drivingStart('+data[i].r_idx+')" >탑승대기</button>';
                  /* html += '<input id="hiddenR_idx" type="hidden" value="'+data[i].r_idx+'">'; */
                  html += '</div>' 
               }
               $('#confirmList').html(html);
            }
         });
         
      }
      
      function deleteRsv(p_idx, r_idx){
    	  if (confirm('예약을 취소하시겠습니까?')) {
    		  $.ajax({
    		  url : 'http://13.125.252.85:8080/server/rsv/mycarpool/'+p_idx+'/'+r_idx,
    		  type : 'DELETE',
    		  success : function(data) {
    			  if (data == 'success') {
    			 /*  $('#deleteModal').modal('hide'); */
    			  alert('취소되었습니다!');
    			  }
    			}
    	  });
			}
    	  }
      
      function waitingRsv(p_idx){
    	  var p_idx = ${sessionScope.login.idx};
         $('#confirmList').css('display', 'none');
         $('#pastList').css('display', 'none');
         $('#waitingList').css('display', 'block');
         $.ajax({
            url : 'http://13.125.252.85:8080/server/rsv/waitcarpool/' + p_idx,
            type : 'GET',
            success : function(data){
               var html = '';
               for (var i = 0; i < data.length; i++) {
                  
                  html += '<div class="rsv">\n';
                  html += '예약요청일자 '+ data[i].r_date + '<br>\n';
                  html += '카풀일시 '+ data[i].d_date + '\t' + data[i].d_starttime + '\t -\t ' + data[i].d_endtime + '<br>\n';
                  html += '출발지 '+ data[i].d_startpoint + '\t 도착지 \t ' + data[i].d_endpoint + '<br>\n';
                  html += '요금 '+ data[i].d_fee +'원 <br>\n';
                  html += '<button id="delete" onclick="deleteReq('+p_idx+',' + data[i].r_idx + ')" class="btn btn-primary" data-toggle="modal" data-target="#deleteModal">요청취소</button>';
                  html += '</div>' 
               }
               $('#waitingList').html(html);
            }
         });  
      }
      
     function deleteReq(p_idx, r_idx){
    	 if (confirm('예약을 취소하시겠습니까?')) {
    		 $.ajax({
    		  url : 'http://13.125.252.85:8080/server/rsv/waitcarpool/'+p_idx+'/'+r_idx,
    		  type : 'DELETE',
    		  success : function(data) {
    			  if (data == 'success') {
    	    			 /*  $('#deleteModal').modal('hide'); */
    	    			  alert('취소되었습니다!');
    	    			  }
    			  	}
    		 	});
    		 }
    	 }
      
      function pastRsv(){
    	  var p_idx = ${sessionScope.login.idx};
         $('#waitingList').css('display', 'none');
         $('#confirmList').css('display', 'none');
         $('#pastList').css('display', 'block');
         $.ajax({
            url : 'http://13.125.252.85:8080/server/rsv/pastcarpool/'+ p_idx,
            type : 'GET',
            success : function(data){
               var html = '';
               for (var i = 0; i < data.length; i++) {
                  
                  html += '<div class="rsv">\n';
                  html += '카풀일시 '+ data[i].d_date + '\t' + data[i].d_starttime + '\t -\t ' + data[i].d_endtime + '<br>\n';
                  html += '출발지 '+ data[i].d_startpoint + '<br>\n';
                  html += '도착지 '+ data[i].d_endpoint + '<br>\n';
                  html += '요금 '+ data[i].d_fee +'원 <br>\n';
                  html += '</div>' 
               }
               $('#pastList').html(html);
            }
         });
      }
      </script>
   <script src="static/js/jquery.min.js"></script>
   <script src="static/js/jquery-migrate-3.0.1.min.js"></script>
   <script src="static/js/popper.min.js"></script>
   <script src="static/js/bootstrap.min.js"></script>
   <script src="static/js/jquery.easing.1.3.js"></script>
   <script src="static/js/jquery.waypoints.min.js"></script>
   <script src="static/js/jquery.stellar.min.js"></script>
   <script src="static/js/owl.carousel.min.js"></script>
   <script src="static/js/jquery.magnific-popup.min.js"></script>
   <script src="static/js/aos.js"></script>
   <script src="static/js/jquery.animateNumber.min.js"></script>
   <script src="static/js/scrollax.min.js"></script>
   <script src="static/js/main.js"></script>
</body>
</html>
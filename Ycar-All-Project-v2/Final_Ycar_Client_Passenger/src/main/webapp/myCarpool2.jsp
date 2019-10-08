<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>YCAR</title>
<!-- <script src="http://localhost:3000/socket.io/socket.io.js"></script> --> 

<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
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


	<!-- chat socket -->
   <script src="http://localhost:5000/socket.io/socket.io.js"></script> 
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


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



    
    /* --------------------------------- */
	/*             채 팅 c s s           */    
    #container {
        width: 400px;
        height: 600px;
        border: 1px solid black;
        background: #FFFEF4;
        margin: 50px auto;
        font-family: 'Noto Sans KR', sans-serif;
    }
    #chatView {
        height: 90%;
        overflow-y: scroll;
    }
    #chatForm {
        height: 10%;
        border-top: 1px solid black;
        text-align: center;
    }
    #msg {
        width: 70%;
        height: 63%;
        line-height: 32px;
        border-radius: 8px;
        margin: 10px 0;
        padding: 5px;
        font-family: 'Noto Sans KR', sans-serif;
    }
    #send {
        width: 16%;
        height: 34px;
        border-radius: 50px;
        background: #413A6D;
        color: white;
        font-family: 'Noto Sans KR', sans-serif;
        margin-left: 5px;
    }
    .msgLine {
        margin: 15px;
        clear: both;
    }
    .msgBox {
        border: 1px solid black;
        background: skyblue;
        padding: 2px 5px;
        border-radius: 10px;
        font-size: 14px;
    }
    .me {
        color: #fff;
    }
    
    .btn_chat {
        background-color:  #413A6D;
        display: inline-block;
        color: #fff;
        padding: 2px 10px;
        margin-top: 10px;
    }
	#roomTitle {
	    background-color: #ccc;
	    text-align: center;
	    padding: 10px 0;
	    color: #fff;
	}
	
	.roomactive {
	    background-color: #6258A4 !important;
	}
	
	.infoTxt {
		font-size: 10px;
		text-align: left;
		clear: both;
	}
	
	.bold {
		font-weight: bold;
		color: #000;
	}
        
	/*             채 팅 c s s           */  
    /* --------------------------------- */


    


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
   
   
            <!-- chat room -->
            <div class="row justify-content-center pb-0 pb-mb-5 pt-5 pt-md-0">
	            <input type="hidden"  value="${sessionScope.login.nickname}" id="chatNickName">
            </div>
            <h1 class="mb-4">채팅</h1>

            <div id="container">
        		<div id="chatView">
        			<div id="roomTitle">채팅방준비중</div>
        		</div>
		        <form id="chatForm" onsubmit="return false">
		            <input type="text" id="msg">
		            <input type="submit" id="send" value="전송">
		        </form>
		    </div>
   
   
</div>



      <script>
      $(document).ready(function() {
         confirmList();
         waitingList();
         
         var p_idx = ${sessionScope.login.idx};
        /* 
        //소켓 연결 
        var socket = io('http://localhost:3000');
        var hiddenridx = $('#hiddenR_idx').val();
        console.log('소켓 연결을 위한 hidden r_idx '+hiddenridx);
        socket.emit('join start room', r_idx); 
        socket.on('startroom join result', function(msg){
           console.log(msg);
        });
      //운행중 page 로 redirect 
      socket.on('go driving page', function(r_idx){
         console.log('탑승자님, 운행 중 페이지로 이동하실게요 '+r_idx);
         setTimeout(function(){
            window.location.href="http://localhost:8080/server/rsv/passenger-driving.jsp?r_idx="+r_idx;
         }, 3000);
      });     
          */
        
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
                  html += '<button id="delete" onclick="deleteRsv('+p_idx+',' + data[i].r_idx + ')" class="btn btn-primary">카풀취소</button>';
                 html += '<input id="hiddenR_idx" type="hidden" value="'+data[i].r_idx+'">';
                 html += '<span class = "btn_chat" onclick="fn_chat('+data[i].r_idx+')">채팅하기</span><br>\n' ;
                 html += '</div>' 
               }
               $('#confirmList').html(html);
            }
         });
         
      }
      
      function deleteRsv(p_idx, r_idx){
    	  if (confirm('삭제하시겠습니까?')) {
    		  $.ajax({
    		  url : 'http://13.125.252.85:8080/server/rsv/mycarpool/'+p_idx+'/'+r_idx,
    		  type : 'DELETE',
    		  success : function(data) {
    			  if (data == 'success') {
    			 /*  $('#deleteModal').modal('hide'); */
    			  alert('삭제되었습니다!');
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
                  html += '<span class = "btn_chat" onclick="fn_chat('+data[i].r_idx+')">채팅하기</span><br>\n' ;
                  html += '</div>' 
               }
               $('#waitingList').html(html);
            }
         });  
      }
      
     function deleteReq(p_idx, r_idx){
    	 if (confirm('삭제하시겠습니까?')) {
    		 $.ajax({
    		  url : 'http://13.125.252.85:8080/server/rsv/waitcarpool/'+p_idx+'/'+r_idx,
    		  type : 'DELETE',
    		  success : function(data) {
    			  if (data == 'success') {
    	    			 /*  $('#deleteModal').modal('hide'); */
    	    			  alert('삭제되었습니다!');
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
   
   
   <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   
   
<script>

/**********************
    	  채팅용
**********************/
	var chat_nickname = $('#chatNickName').val();
	console.log('nickname type? \n' + typeof(chat_nickname));
	
function fn_chat(r_idx){
 	
	socketChat.emit('set room', {room: r_idx, nickname: chat_nickname});
      //title 재정의
      document.getElementById('roomTitle').innerHTML = r_idx + '번 방 개설!' + '<input type="hidden" id="chatIdx" value = "'+r_idx+'">';
      
      $('#roomTitle').addClass('roomactive');
  }
  const socketChat = io('http://localhost:5000');
  
  let chatView = document.getElementById('chatView');
  let chatForm = document.getElementById('chatForm');

  chatForm.addEventListener('submit', function() {
      let msg = $('#msg');

      if (msg.val() == '') {
          return;

      } else {
      	socketChat.emit('SEND', 
                      {room: $('#chatIdx').val(),
                      nickname: chat_nickname,
                      msg: msg.val()
                      }
         );

          let msgLine = $('<div class="msgLine">');
          let msgBox = $('<div class="me msgBox">');

          msgBox.append(msg.val());
          msgBox.css('display', 'inline-block');
          msgLine.css('text-align', 'right');
          msgLine.append(msgBox);

          $('#chatView').append(msgLine);
          
          msg.val('');
          chatView.scrollTop = chatView.scrollHeight;
      }
  });
  
  socketChat.on('SEND', function(nickname, msg, date) {
      //msgData: room, nickname, parsedate, msg
      let msgLine =  $('<div class="msgLine">');
      let msgBox = $('<div class="msgBox">');
      
      msgLine.append('<p class="infoTxt bold">'+nickname+'</p>');
      
      msgBox.append(msg);
      msgBox.css('display', 'inline-block');
      msgBox.css('float', 'left');
      msgLine.append(msgBox);
      msgLine.append('<p class="infoTxt">'+date+'</p>');
      
      $('#chatView').append(msgLine);
      chatView.scrollTop = chatView.scrollHeight;
      
  });




</script>
   
   
</body>
</html>
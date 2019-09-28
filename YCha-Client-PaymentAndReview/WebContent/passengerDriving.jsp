<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="http://localhost:3000/socket.io/socket.io.js"></script> 
</head>
<body>
	<h1>탑승자 화면 : 카풀 운행 중 </h1>
	<!-- param : 1) ${loginInfo.p_idx}"  2) 현재 사용자의 r_idx -->
	<button id="arrBtn">목적지 도착</button>
	<script>
		var socket = io('http://localhost:3000');
		$(document).ready(function(){
			var r_idx = document.location.search.substring('1').split('=')[1];
			console.log('r_idx 확인 : ', r_idx);  

			socket.emit('join room', r_idx); 

			$('#arrBtn').on('click', function(){
				socket.emit('arrive', r_idx);
				console.log('arrive 이벤트 발생');
			})
	
			socket.on('redirect', function(r_idx){
				console.log('redirect 리슨 ', r_idx);
				setTimeout(function(){
					window.location.href="http://localhost:8080/parclient/index.jsp?r_idx="+r_idx;
				}, 2000);
			});

		});
		
		// function arrive() {
		// 	socket.emit('arrive', r_idx);
		// 	console.log('arrive 이벤트 발생');
		// };
	</script>
</body>
</html>
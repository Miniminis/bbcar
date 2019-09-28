<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>탑승자 화면 : 카풀 운행 중 </h1>
	<!-- param : 1) ${loginInfo.p_idx}"  2) 현재 사용자의 r_idx -->
	<button onclick="arrive(3)">목적지 도착</button>
	<script>
		//소켓 서버에 arrive 이벤트 리스너 만들어주기 
		function arrive(r_idx) {
			location.replace="http://localhost:8080/parclient/index.html?r_idx="+r_idx;
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>운전자 화면 : 카풀 운행 중</h1>
	<!-- param : 1) ${loginInfo.d_idx}"  2) 현재  r_idx -->
	<button onclick="arrive(3)">목적지 도착</button>
	<script>
		//소켓 서버에 arrive 이벤트 리스너 만들어주기 
		function arrive(r_idx) {
			location.replace="http://localhost:8080/parclient/payment/driverPaychk.jsp?r_idx="+r_idx;
		}
	</script>
</body>
</html>
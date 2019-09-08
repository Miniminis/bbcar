<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
	<script>
		$(document).ready(function(){
			
			var params = document.location.href.split('?'); 
			console.log('kakao 결제 성공 후 token 값 : '+params[1]);
			
			//var paramArray = params[1].split('=');
			//console.log('params.split() 결과 : '+paramArray[0]+' / '+paramArray[1]);
			
			$.ajax({
				url : "http://localhost:8080/par/payment/kakao/success?"+params[1],
				success : function(data) {
					console.log('결제 승인까지 성공! '+data.payment_method_type);
					paymentDetail();
				}, 
				error : function(e) {
					console.log('결제 승인 실패함 ');
				}
			})
		})
		
		function paymentDetail(){
			alert('결제 내역 뽑아야댐;;');
		}
	</script>
</body>
</html>
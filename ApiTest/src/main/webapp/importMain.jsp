<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<h1>아임포트 - 페이코 결제 페이지</h1>
	
	<button onclick="requestPay()">결제하기</button>
	
	<script>
		alert('페이코 결제 시작');
		var IMP = window.IMP; // 생략해도 괜찮습니다.
		IMP.init("imp61079881"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.
		
		// IMP.request_pay(param, callback) 호출
		  IMP.request_pay({ // param
		    pg: "payco",
		    pay_method: "card",
		    merchant_uid: "ORD20180131-0000011",
		    name: "노르웨이 회전 의자",
		    amount: 64900,
		    buyer_email: "gildong@gmail.com",
		    buyer_name: "홍길동",
		    buyer_tel: "010-4242-4242",
		    buyer_addr: "서울특별시 강남구 신사동",
		    buyer_postcode: "01181"
		  }, function (rsp) { // callback
		    if (rsp.success) {
		    	console.log('성공!'+rsp);
		        // 결제 성공 시 로직,
		        
		    } else {
		        // 결제 실패 시 로직,
		    	alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
		    }
		  });
	</script>
</body>
</html>
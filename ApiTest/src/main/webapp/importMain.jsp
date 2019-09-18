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
		
		function requestPay() {
			// IMP.request_pay(param, callback) 호출
			IMP.request_pay({ // param
				pg : 'payco', // version 1.1.0부터 지원.
				pay_method : 'card',
				merchant_uid : 'merchant_' + new Date().getTime(),
				name : '주문명:결제테스트',
				amount : 14000,
				buyer_email : 'iamport@siot.do',
				buyer_name : '홍길동',
				buyer_tel : '010-1234-5678',
				buyer_addr : '서울특별시 강남구 삼성동',
				buyer_postcode : '123-456',
			}, function(rsp) { // callback
				if (rsp.success) {
					console.log('성공!' + rsp);
					// 결제 성공 시 로직,
					var msg = '결제가 완료되었습니다.';
					msg += '고유ID : ' + rsp.imp_uid;
					msg += '상점 거래ID : ' + rsp.merchant_uid;
					msg += '결제 금액 : ' + rsp.paid_amount;
					msg += '카드 승인번호 : ' + rsp.apply_num;
				} else {
					// 결제 실패 시 로직,
					alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
				}
				console.log(msg);

			});
		}
	</script>
</body>
</html>
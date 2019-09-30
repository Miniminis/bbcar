<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- <script src="http://localhost:3000/socket.io/socket.io.js"></script> -->
<!-- <script src="http://cdn.socket.io/socket.io-1.0.0.js"></script>  -->
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<h1>toss pay test 입니다.</h1>
	<button onclick="tossStart()">토스결제 시작 </button>

	<h1>naver pay 입니다.</h1>
	<button onclick="naverPayStart()">네이버 페이 결제 시작 </button>

	<h1>아임포트 - 페이코 결제 페이지</h1>
	<button onclick="requestPay()">아임포트 - 페이코로 결제하기</button>

	<h1>import - KG 이니시스 </h1>
	<button onclick="kgInisisStart()">아임포트 - KG 이니시스 결제 시작 </button>

	<button onclick="urlTest()">url test </button>
	<script>
	
		function urlTest() {
			alert('페이지 이동합니다.');
			var url = 'http://localhost:8080/parclient/import/success.jsp?paid_amount='+3+'&apply_num='+3210;	
			location.replace(url);
		}

		//아임포트 
		var IMP = window.IMP; // 생략해도 괜찮습니다.
		IMP.init("imp61079881"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.

		//아임포트 - KG 이니시스 
		function kgInisisStart() {
			
			alert('kg inisis로 결제 요청');
			
			// IMP.request_pay(param, callback) 호출
			IMP.request_pay({ // param
				pg : 'html5_inicis', // version 1.1.0부터 지원.
				pay_method : 'card',
				merchant_uid : 'merchant_' + new Date().getTime(),
				name : '주문명:결제테스트',
				amount : 1001,
				buyer_email : 'iamport@siot.do',
				buyer_name : '홍길동',
				buyer_tel : '010-1234-5678',
				buyer_addr : '서울특별시 강남구 삼성동',
				buyer_postcode : '123-456',
			}, function(rsp) { // callback
				if (rsp.success) {
					console.log('성공!', rsp);
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

		//아임포트 - 페이코
		function requestPay() {
			alert('payco로 결제 요청');
			
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


		function tossStart() {

			var num = 5025;
			console.log('toss pay 요청 01  ' + num);

			$
					.ajax({
						//url : 'http://localhost:8090/boottest/'+num,
						url : 'http://localhost:8090/parboot/payment/toss/r_idx/'
								+ num,
						type : 'POST',
						success : function(data) {
							console.log('mission success toss01  ' + data);
							//console.log('mission success toss02  '+data.username);
							//console.log('mission success toss03  '+data.password);
						},
						error : function(e) {
							console.log('error 발생!!!! ㅠㅠㅠㅠ ' + e)
						}

					})
		}

		/* function tossStart() {
			$.ajax({
				url : 'http://localhost:8080/test/tosstest',
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					console.log('toss pay success' + data);
					console.log('toss pay success  ' + data.checkoutPage);
					//window.location.href='\''+data.checkoutPage+'\''; 
					console.log('\'' + data.checkoutPage + '\'');
					location.replace(data.checkoutPage);
					//href를 쓰니까 context path 가 붙는 상황 발생 
					//따라서 replace 로 고쳐서 url 아예 바꿔줌 
				},
				error : function(e) {
					console.log('error 발생 ' + e);
				}
			})
		} */

		
		function naverPayStart() {
			console.log('naver pay start');
		}

		//소켓 테스트 
		/* $(document).ready(function() {
			var socket = io('http://localhost:3000');
			socket.on('alert', function(msg) {
				console.log(msg);
			});
		}); */
	</script>
	

	<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js"
		data-client-id="u86j4ripEt8LRfPGzQ8" data-mode="development"
		data-merchant-user-key="5555" data-merchant-pay-key="1234"
		data-product-name="연차 카풀 서비스" data-total-pay-amount="1000"
		data-tax-scope-amount="1000" data-tax-ex-scope-amount="0"
		data-return-url="http://localhost:8080/test/ordercheck.jsp">
		
	</script>

	<!-- <input type="button" id="naverPayBtn" value="네이버페이 결제 버튼">
	<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js"></script>
	<script>

	    var oPay = Naver.Pay.create({ //SDK Parameters를 참고 바랍니다.
	          "mode" : "development",
	          "payType" : "normal", // normal or recurrent
	          "clientId": "b96dMxe4JJY4bdC5m9Xf"
	          //"chainId" : "{그룹형일 경우 chainId를 넣어주세요}"
	    });
	
	    //직접 만드신 네이버페이 결제버튼에 click Event를 할당하세요
	    var elNaverPayBtn = document.getElementById("naverPayBtn");
	
	    elNaverPayBtn.addEventListener("click", function() {
	        oPay.open({ // Pay Reserve Parameters를 참고 바랍니다.
	          /* "merchantUserKey": "{#_merchantUserKey}",
	          "merchantPayKey": "{#_merchantPayKey}",
	          "productName": "연차서비스",
	          "totalPayAmount": 5000,
	          "taxScopeAmount": 5000,
	          "taxExScopeAmount": 0,
	          "returnUrl": "http://localhost:8080/test/ordercheck.jsp" */
	          
	        });
	    }); 
	 </script>
	 -->	
</body>
</html>
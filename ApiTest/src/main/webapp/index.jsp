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
	<h1>toss pay test 입니다.</h1>
	<a onclick="tossStart()">토스결제 시작 </a>

	<h1>naver pay 입니다.</h1>
	<a onclick="naverPayStart()">네이버 페이 결제 시작 </a>
	
	<h1>PAYCO 로 결제</h1>
	<a onclick="paycoStart()">네이버 페이 결제 시작 </a>
	
	<script>
		function paycoStart() {
		}
	
	
		function tossStart() {
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
		}

		function naverPayStart() {
			console.log('naver pay start');
		}
	</script>

	<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js"
		    data-client-id="u86j4ripEt8LRfPGzQ8"
		    data-mode="development"
		    data-merchant-user-key="5555"
		    data-merchant-pay-key="1234"
		    data-product-name="연차 카풀 서비스"
		    data-total-pay-amount="1000"
		    data-tax-scope-amount="1000"
		    data-tax-ex-scope-amount="0"
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
	    }); -->
	
	</script>
</body>
</html>
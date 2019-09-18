$(document).ready(function(){
    
    //navbar load 
    $("#navbar").load("../framePassenger/navbar.html");
    
	//url 로부터 token 값 가져오기  
	var params = document.location.href.split('?'); 
	console.log('kakao 결제 성공 후 token 값 01: '+params[1]);
	
	//var paramArray = params[1].split('=');
	//console.log('params.split() 결과 : '+paramArray[0]+' / '+paramArray[1]);
	
	$.ajax({
		url : "http://localhost:8080/par/payment/kakao/success?"+params[1],
		success : function(data) {
			console.log('결제 승인08 까지 성공! '+data.paymethod);
			
			//1. 결제 DB에 저장 : 결제 내역이 성공적으로 나온 뒤에 처리 
			//2. 결제 내역 보여주기 위해 따로 매서드로 페이지 구성 처리 
			paymentDetail(data);
			
		}, 
		error : function(e) {
			console.log('결제 승인 실패함 ');
			window.location.href = "http://localhost:8080/parclient/kakao/fail.html";
		}
	})
})

//1. 결제 DB에 저장 처리 및 
//2. 결제 내역 보여주기 위해 따로 매서드로 페이지 구성 처리 
function paymentDetail(data){	
	/* 결제 완료 페이지에 필요한 내용  
	 * 1. 날짜 
	 * 2. 총 운행거리
	 * 3. 총 운행시간
	 * 4. 총 금액
	 * 5. 결제 수단 //data
	 * 6. 출발시간
	 * 7. 출발지
	 * 8. 도착시간
	 * 9. 도착지
	 * */
	console.log('결제 내역 시이작 01 '+data.paymethod);
	
	$.ajax({
		url : "http://localhost:8080/par/payment/passenger",
		type: 'post',
		data : JSON.stringify({
			r_idx : data.r_idx,
			paymethod : data.paymethod
		}),
		//다수의 데이터들을 jquery{} 객체로 보내면 서버단에 도착할때 미세한 시간차 발생. 
		//동시에 도착하지 않기 때문에 command 객체로 한꺼번에 받는데 400에러가 계속 발생함. 
		//json.stringify 이용하여 json 타입으로 한꺼번에 보내니 문제 없이 커맨드 객체에 binding 됨 
		contentType:'application/json;charset=UTF-8',
		success : function(data) {
			if(data>0) {
				console.log('결제내역05 '+data);
				
				/* 결제 완료 페이지에 보여줄 정보 내역 
				 * 1. 총 운행시간 
				 * 2. 총 운행거리 
				 * 3. 총 금액 amount - total,tax_free, vat, point, discount
				 * 4. 출발시간 
				 * 5. 출발지 
				 * 6. 도착시간 
				 * 7. 도착지 
				 * 8. 결제 일시 approved_at
				 * 9. 결제 수단 payment_method_type
				 * */
				
				//탑승자 - 결제 내역 표시 
				$('#date').html(servicedate);
				$('#distance').html(servicedistance+'km / '+servicetime+'시간');
				$('#amount').html(amount01+'원');
				$('#method').html(paytype);
				$('#stime').html(departtime +'시 / '+departplace);
				$('#etime').html(arrvietime+'시 / '+arriveplace);
				
				//탑승자 페이지 갱신 
				setTimeout(function(){
					window.location.href='http://localhost:8080/parclient/reviewPassenger/writePassenger.html?r_idx='+reservationIdx; //탑승자 후기 작성 페이지로 이동 
				}, 5000);
				
				//운전자 화면 - 입금내역 표시 
				//시간차 주의 : 탑승자 결제내용이 DB에 입력 되고 난 후에야 조회 가능함!
				//depositDetail(reservationIdx);
				
			} else {
				console.log('결제내역06 '+data);
			}
		}, 
		error : function(e) {
			console.log('결제내역07 '+e);
		}
	})	
}

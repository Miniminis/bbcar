$(document).ready(function(){
    $("#navbar").load("framePassenger/navbar.html");
});

/*카카오페이 결제를 위한 탑승자, 예약정보  받아오기*/
function getRDVInfo(pidx) {
	
	console.log("RDV 받아오기01 : "+pidx);
	
	$.ajax({
		url : "http://localhost:8080/par/payment/rdvinfo/"+pidx,
		type: 'get',
		success : function(data) {
			//console.log('일단성공01 '+data.r_idx);
			//console.log('일단성공02 '+data.r_fee);
			
			var r_idx = data.r_idx;
			var p_idx = pidx;
			var r_fee = data.r_fee;
			var tax_free_amount = r_fee*0.05;		
			
			//뽑은 예약 정보 가지고 카카오 결제 요청 
			kakaoPayProcess(r_idx, p_idx, r_fee, tax_free_amount);
		},
		error : function(e) {
			console.log('getRDVInfo() 에러발생 '+e);
		}
		
	})
}

//뽑은 예약 정보 가지고 카카오 결제 요청 
function kakaoPayProcess(r_idx, p_idx, r_fee, tax_free_amount) {
	
	console.log('kakaopay 요청 01  '+r_fee);
	
	$.ajax({
		url : "http://localhost:8080/par/payment/kakao",
        type: 'POST',
		data : {
			r_idx : r_idx,
			p_idx : p_idx,
			r_fee : r_fee,
			tax_free_amount : tax_free_amount
		},
		dataType : "text",
		success : function(data) {
			console.log('kakao 결제 성공  - 성공페이지로 이동'+data);
			window.location.href = data; //성공할 경우 클라이언트 성공 페이지로 이동 
			//ajax 로 요청했는데 controller 단에서 redirect 할 경우, cors origin 에러가 발생하게 된다. 
			//요청하는 위치가 달라지기 때문 : client  vs  server 
			//따라서 client 단으로 다시 돌아와서 원하는 페이지로 redirect 하도록 처리함 
		},
		error : function(data) {
			console.log('kakao 결제 실패 - 실패 페이지 이동 ');
			window.location.href = "http://localhost:8080/parclient/kakao/fail.html";
		}
		
	})
}

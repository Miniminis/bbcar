$(document).ready(function(){
    
    //navbar load 
    $("#navbar").load("../framePassenger/navbar.html");
    
	//url 처음부터 ?까지 삭제 
	var paramUrl = document.location.search.substring('1'); 
	var params = paramUrl.split('&');  //&을 기준으로 분리 
	
	//r_idx
	var paramArray0 = params[0].split('=');
	console.log('toss 결제 성공 후 token 값 02: '+paramArray0[0]+' / '+paramArray0[1]);

	//status
	var paramArray1 = params[1].split('=');
	console.log('toss 결제 성공 후 token 값 03: '+paramArray1[0]+' / '+paramArray1[1]);

	//orderNo
	var paramArray2 = params[2].split('=');
	console.log('toss 결제 성공 후 token 값 04: '+paramArray2[0]+' / '+paramArray2[1]);
	
	//payMethod
	var paramArray3 = params[3].split('=');
	console.log('toss 결제 성공 후 token 값 04: '+paramArray3[0]+' / '+paramArray3[1]);
	
	//만약 status가 pay_complete이라면, 결제 완료 처리 진행
	if(paramArray1[1]=='PAY_COMPLETE') {
		 console.log('toss pay success 처리 시작');
		 
		//1. 결제 DB에 저장 
		//2. 결제 내역 보여주기 위해 따로 매서드로 페이지 구성 처리 
		 $.ajax({
			url : "http://localhost:8090/parboot/payment/toss",
			type: 'post',
			data : JSON.stringify({
				r_idx : paramArray0[1],
				paymethod : paramArray3[1]
			}),
			contentType:'application/json;charset=UTF-8;',
			success : function(data) {
					console.log('결제내역08 '+data);
					//탑승자 - 결제 내역 화면 표시 
					$('#date').html(data.paydate);
					$('#commuteType').html(data.d_commute);
					$('#distance').html(data.d_distance+'km / '+(data.d_endtime-data.d_starttime)+'분');
					$('#amount').html(data.d_fee+'원');
					$('#method').html(data.paymethod);
					$('#stime').html(data.d_starttime +'/ '+data.d_startpoint);
					$('#etime').html(data.d_endtime+'/ '+data.d_endpoint);
					
					//탑승자 페이지 갱신 
					setTimeout(function(){
						//일정 시간 후 : 탑승자 후기 작성 페이지로 이동 
						window.location.href='http://localhost:8080/parclient/reviewPassenger/writePassenger.html?payidx='+data.payidx; 
					}, 5000);
					
					//운전자 페이지 갱신 - 입금내역 표시 
					//시간차 주의 : 탑승자 결제내용이 DB에 입력 되고 난 후에야 조회 가능함!
					//depositDetail(reservationIdx);
					
			}, 
			error : function(e) {
				console.log('결제내역07 '+e);
			}
		}) //ajax end
	} //if end
}) //ready end
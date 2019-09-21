$(document).ready(function(){
    $("#navbar").load("framePassenger/navbar.html");
});

//뽑은 예약 정보 가지고 카카오 결제 요청 
function kakaoPayProcess() {
	
	//운행 중 화면으로부터 넘어올 때 r_idx 받아오기
	var r_idx = 4;
	
	console.log('kakaopay 요청 01  '+r_idx);
	
	$.ajax({
		url : "http://localhost:8080/par/payment/kakao/r_idx/"+r_idx,
        type: 'POST',
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

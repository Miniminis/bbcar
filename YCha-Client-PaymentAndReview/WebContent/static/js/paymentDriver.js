$(document).ready(function(){
	//navbar load 
    $("#navbar").load("../frameDriver/navbar.html");
})

//입금 내역 check : socket 이용해서 페이지 처리 
/*순서
 * 1. 사용자 페이지와 통신 - 입금 내역 데이터 받아오기 (json)
 * 2. 해당 내역 출력 
 * */

/*function depositDetail(r_idx) {
	console.log('입금내역 01 '+r_idx);
	
	$.ajax({
		url : "http://localhost:8080/par/payment/driver/"+r_idx,
		type: 'GET',
		success : function(data) {
			console.log(data);
			
			$('#d_date').html(data.serdate);
			$('#d_distance').html(data.serdistance+'km / '+data.sertime+'시간');
			$('#d_amount').html(data.serprice+'원');
			$('#d_stime').html(data.dtime +'시 / '+data.dplace);
			$('#d_etime').html(data.atime+'시 / '+data.aplace);
		},
		error : function(e) {
			console.log('error on driver deposit page '+e);
		}
	})
}*/
$(document).ready(function(){
	//navbar load 
    $("#navbar").load("../frameDriver/navbar.html");
})

function depositDetail(r_idx) {
	console.log('입금내역 01 '+r_idx);
	
	$.ajax({
		url : "http://localhost:8080/par/payment/result/driver/"+r_idx,
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
}
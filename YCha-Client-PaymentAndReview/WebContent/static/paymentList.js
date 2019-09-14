$(document).ready(function(){
    $("#navbar").load("../framePassenger/navbar.html");
    
    //세션에서 현재 사용자의 p_idx 값 가져오기 
    var p_idx = 1;
    
    console.log('결제리스트 시작 01 '+p_idx);
    
    //결제 리스트 출력 
    $.ajax({
    	url : 'http://localhost:8080/par/payment/passenger/'+p_idx,
    	type: 'GET',
    	success : function(data) {
    		console.log('결제리스트 시작 05 '+data);
    		
    		var output = '';
    		
    		for(var i=0; i<data.length; i++) {
    			output += '<div class="col-sm-6">';
    			output += '<div class="card">';
    			output += '<div class="card-body">';
    			output += '<h5 class="card-title">'+data[i].serprice+'원</h5>';
    			output += '<div class="container">';
    			output += '<table class="table">';
    			output += '<thead>';
    			output += '<tr>';
    			output += '<th scope="col">항목</th>';
    			output += '<th scope="col">내용</th>';
    			output += '</tr>';
    			output += '</thead>';
    			output += '<tbody>';
    			output += '<tr><th scope="row">날짜</th><td>'+data[i].serdate+'</td></tr>';
    			output += '<tr><th scope="row">총 운행거리/총 운행시간</th><td>'+data[i].serdistance+' / '+data[i].sertime+'</td></tr>';
    			output += '<tr><th scope="row">출발시간/출발지</th><td id="stime">'+data[i].dtime+' / '+data[i].dplace+'</td></tr>';
    			output += '<tr><th scope="row">도착시간/도착지</th><td id="etime">'+data[i].atime+' / '+data[i].aplace+'</td></tr>';
    			output += '</tbody></table></div><a href="#" class="btn btn-primary">Go somewhere</a></div></div></div>';
    		}
    		
    		$('#passengerPayList').html(output);
    	},
    	error : function(e) {
    		console.log(e);
    	}
    })
    
});
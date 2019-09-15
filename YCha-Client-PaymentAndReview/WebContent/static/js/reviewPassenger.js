$(document).ready(function(){
    
    //navbar load 
    $("#navbar").load("../framePassenger/navbar.html");
    
    //현재 세션에서 p_idx, nickname 값 구해서 input 에 넣어주기 
    var p_idx = 1;
    var nickname='익명의 탑승자';
	$('#p_idx').val(p_idx);
	$('#nickname').val(nickname);
	
    //리뷰 리스트 로딩 
    reviewListByDrivers(p_idx);

    
    
	/* 후기 등록 
	 * 1. 사용자 세션 값에서 p_idx, 닉네임 가져오기 --> input 에 넣어주기 
	 * 2. form 제출 --> DB에 insert 
	 *  rv_idx int(7) AI PK 
		payidx int(7) 
		p_idx int(10) 
		d_idx int(10) 
		pr_content varchar(255) 
		pr_star int(10) 
		dr_content varchar(255) 
		dr_star int(10)
	 * 3. 완료되면 띄워줄 alert + 페이지 이동   
	 * */
	
	//결제 완료 페이지에서 넘겨준 r_idx 이용해서 서버단에서 payidx, p_idx, d_idx 구하기 
	//--> 세션의 p_idx 와 결제 테이블의 p_idx 가 일치하는지 체크 필요 
	var params = document.location.href.split('?'); 
	console.log('리뷰 등록 01  r_idx 01 : '+params[1]);
	
	var paramArray = params[1].split('=');
	console.log('리뷰 등록 02 r_idx 02 '+paramArray[0]+' / '+paramArray[1]);
	
	$('#r_idx').val(paramArray[1]);
	
	//수정 폼 모달
	$('#editReviewModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget); 
	  var rv_idx = button.data('id');
	  var nickname = button.data('nickname'); 
	  var star = button.data('star');
	  var content = button.data('content'); 

	  var modal = $(this);
	  modal.find('#nickname').val(nickname);
	  modal.find('#rv_idx').val(rv_idx);
	  modal.find('#star').val(star); //별점 표시부터 다시
	  modal.find('#content').val(content);
	  
	})
	
})

//나의 후기 리스트 출력 + //내가 작성한 후기 리스트 출력 
function reviewListByDrivers(p_idx) {
	$.ajax({
		url: 'http://localhost:8080/par/review/passenger/'+p_idx,
		type: 'GET',
		success : function(data) {
			console.log('첫번쩨 리스트 성공'+data);
			
			var output ='';
			
			for(var i=0; i<data.length; i++) {
				if(data[i].dr_content==null) {
					break;
				}
				output += '<div class="col-sm-6">';
				output += '<div class="card">';
				output += '<div class="card-header">★';
				output += data[i].dr_star;
				output += '</div>';
				output += '<div class="card-body">';
				output += '<blockquote class="blockquote mb-0">';
				output += '<p>'+data[i].dr_content+'</p>';
				output += '<footer class="blockquote-footer"><cite title="Source Title">'+data[i].d_nickname+'</cite></footer>';
				output += '</blockquote>';
				output += '</div>';
				output += '</div>';
				output += '</div>';				  
			}
			
			$('#reviewListByDrivers').html(output);
			
			output = '';
			
			for(var j=0; j<data.length; j++) {
				if(data[j].pr_content==null) {
					break;
				}
				output += '<div class="col-sm-6">';
				output += '<div class="card">';
				output += '<div class="card-header">★';
				output += data[j].pr_star;
				output += '</div>';
				output += '<div class="card-body">';
				output += '<blockquote class="blockquote mb-0">';
				output += '<p>'+data[j].pr_content+'</p>';
				output += '<footer class="blockquote-footer"><cite title="Source Title">'+data[j].p_nickname+'</cite></footer>';
				output += '<button data-toggle="modal" data-target="#editReviewModal" data-id="'+data[j].rv_idx+'" data-nickname="'+data[j].p_nickname+'" data-star="'+data[j].pr_star+'" data-content="'+data[j].pr_content+'" class="btn btn-primary">리뷰수정</button>';
				output += '<button onclick="deleteReview('+data[j].rv_idx+', '+p_idx+')" class="btn btn-primary">리뷰삭제</button>';
				output += '</blockquote>';
				output += '</div>';
				output += '</div>';
				output += '</div>';	
			}
			
			$('#reviewWrittenByMe').html(output);
			
		}, 
		error : function(e) {
			console.log('첫번쩨 리스트 실패'+e);
		}
	})
}

//내가 작성한 후기 수정
function editReview() {
	
}

//내가 작성한 후기 삭제
function deleteReview(rv_idx, p_idx) {
	
	if(confirm('삭제한 후기는 복구가 불가능합니다. 정말 삭제하시겠습니까?')) {
		$.ajax({
			url : 'http://localhost:8080/par/review/passenger/'+rv_idx+'/p_idx/'+p_idx,
			type: 'DELETE',
			/*contentType : 'applicaion/json;charset=utf-8;',
			data : JSON.stringify({
				p_idx : p_idx
			}),*/
			success : function(data) {
				console.log('탑승자의 후기 삭제 성공'+data);
				if(data>0) {
					alert(data+'개의 후기가 성공적으로 삭제되었습니다.');
					reviewListByDrivers(p_idx);
				}
			},
			error : function(e) {
				console.log('탑승자의 후기 삭제 실패 ㅠㅠ'+e);
				alert('후기 삭제에 실패하였습니다!');
				reviewListByDrivers(p_idx);
			}
		})
	}
	
}

//후기 form 제출 + DB 저장 + 성공시 redirect page 지정 
function reviewSubmit(){
	
	//session 에서 p_idx 값 가져오기 : 현재는 임의의 값 1 
	$.ajax({
		url : 'http://localhost:8080/par/review/passenger',
		type: 'post',
		contentType:'application/json;charset=UTF-8',
		data : JSON.stringify({
			r_idx : $('#r_idx').val(),
			p_idx : $('#p_idx').val(),
			pr_content : $('#comment').val(),
			pr_star : $('#starRate').val()
		}),
		success : function(data) {
			//alert(data);
			//alert('탑승자님의 소중한 리뷰가 등록되었습니다!');
			if(data>0) {
				alert(data+'개의 소중한 리뷰가 등록되었습니다!');
				window.location.href='http://localhost:8080/parclient/passengerMain.html';
			}
		}, 
		error : function(e) {
			console.log('리뷰 입력 실패 '+e);
		}
	})
	
}

$(document).ready(function(){
	
    //navbar load 
    $("#navbar").load("../framePassenger/navbar.html");
    
    //현재 세션에서 p_idx 구하기
    var p_idx = 1;
	
    //리뷰 리스트 로딩 
    reviewListByDrivers(p_idx);

	//수정 폼 모달 보이기 
	$('#editReviewModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget); 
	  
	  var rv_idx = button.data('id');
	  var nickname = button.data('nickname'); 
	  var star = button.data('star');
	  var content = button.data('content'); 
	  
	  var modal = $(this);
	  modal.find('#eRv_idx').val(rv_idx);
	  modal.find('#eNickname').val(nickname);
	  modal.find('#eStarRate').val(star); 
	  modal.find('#eContent').val(content);
	  modal.find('#eP_idx').val(p_idx);
	  
	})
	
	//수정 폼 모달 숨기기
	$('#editReviewModal').on('hide.bs.modal', function (e) {
		$(this).find('.modal-body form')[0].reset(); 
			//폼 초기화 : 이후 다시 열어도 폼이 비워져 있도록!
	});
	
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
				
				//리스트에 내용 없을때
				if(data[i].dr_content==null) {
					output += '등록된 후기가 없습니다!';
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
				
				//리스트에 내용 없을때
				if(data[j].pr_content==null) {
					output += '등록된 후기가 없습니다!';
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

//후기 수정
function editReview() {
	var rv_idx = $('#eRv_idx').val();
	var p_idx = $('#eP_idx').val();
	console.log('리뷰 수정 시작 01'+rv_idx);
	
	$.ajax({
		url : 'http://localhost:8080/par/review/passenger/'+rv_idx,
		type: 'PUT',
		data : JSON.stringify({
			rv_idx : rv_idx,
			p_idx : p_idx,
			pr_content : $('#eContent').val(),
			pr_star : $('#eStarRate').val()
		}),
		contentType : 'application/json;charset=utf-8;',
		success : function(data) {
			alert(data+'개의 후기가 수정 성공인디?');
			$('#editReviewModal').modal('hide');
			reviewListByDrivers(p_idx);
		},
		error : function(e) {
			console.log('수정 실패얌 ㅠㅠ');
		}
	})
	
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


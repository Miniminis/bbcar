$(document).ready(function(){
	
	$(function() {
		$('#starRate').barrating({
			theme : 'fontawesome-stars'
		});
	});
	$('#starRate').barrating({
		theme : 'fontawesome-stars',
		initialRating : 10
	});
	
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
	
	//세션값 대신 임의로 닉네임 가져오기
	$('#nickname').val('익명의 고라니');
	
	//결제 완료 페이지에서 넘겨준 r_idx 이용해서 서버단에서 payidx, p_idx, d_idx 구하기 
	//--> 세션의 p_idx 와 결제 테이블의 p_idx 가 일치하는지 체크 필요 
	var params = document.location.href.split('?'); 
	console.log('리뷰 등록 01  r_idx 01 : '+params[1]);
	
	var paramArray = params[1].split('=');
	console.log('리뷰 등록 02 r_idx 02 '+paramArray[0]+' / '+paramArray[1]);

	
	//후기 form 제출 + DB 저장 + 성공시 redirect page 지정 
	function reviewSubmit(){
		
		//session 에서 p_idx 값 가져오기 : 현재는 임의의 값 1 
		$.ajax({
			url : 'http://localhost:8080/par/review/passenger',
			type: 'post',
			data : JSON.stringify({
				r_idx : paramArray[1],
				p_idx : 1,
				p_nickname : $('#nickname'),
				pr_content : $('#comment'),
				pr_star : $('#starRate')
			}),
			contentType : 'application/json;charset=UTF-8',
			success : function(data) {
				console.log('리뷰 입력 성공');
			}, 
			error : function(e) {
				console.log('리뷰 입력 실패 '+e);
			}
		})
		
		
	}
	
	
})

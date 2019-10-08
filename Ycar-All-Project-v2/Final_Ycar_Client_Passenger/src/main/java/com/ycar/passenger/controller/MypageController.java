package com.ycar.passenger.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ycar.passenger.domain.ChangeInfo;

@CrossOrigin
@Controller
@RequestMapping("/mypage")
public class MypageController {

	// 마이페이지 로딩
	@GetMapping
	public String page() {
		return "mypage";
	}

	// 내 정보 업로드
	@PutMapping
	@ResponseBody
	public int changeInfo(@RequestBody ChangeInfo changeInfo) {
		
		RestTemplate rt = new RestTemplate();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", changeInfo.getId());
		map.put("pw1", changeInfo.getPw1());
		map.put("pw2", changeInfo.getPw2());
		
		System.out.println(map);
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Map<String,String>> entity = new HttpEntity<Map<String,String>>(map,headers);
		
		int result = rt.exchange("http://13.125.252.85:8080/server/members/mypage", HttpMethod.PUT, entity, Integer.class).getBody();
		System.out.println(result);
		// [수정 추가예정 내용: email수정] result = 1일때, session에 업데이트된 정보로 다시 저장
		
		return result;
	}
}

package com.ycha.par.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ycha.par.domain.KakaoPayPreReady;
import com.ycha.par.service.KakaoPayService;


@RestController
@RequestMapping("/payment/kakao")
public class PaymentKakaoController {
	
	@Autowired
	private KakaoPayService kakaoPayService;
	
	//kakao pay 결제 요청을 위한 post  
	@PostMapping() 
	@CrossOrigin
	public String kakaoPay(KakaoPayPreReady kakaoPayPreReady) {
		
		System.out.println("kakao pay 요청 02 "+kakaoPayPreReady.toString());
		
		return kakaoPayService.kakaoPayReady(kakaoPayPreReady);
	}
	
	//카카오페이 성공시 호출 매서드 
	@GetMapping("/success")
	@CrossOrigin
	public Map<String, Object> kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		
		System.out.println("kakao pay 성공 02 " +pg_token);
		
        //model.addAttribute("info", kakaoPayService.getkakaoPayResult(pg_token));
		//return "http://localhost:8080/parclient/kakao/success.jsp";
		return kakaoPayService.getkakaoPayResult(pg_token);
	}

}

package com.ycha.par.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycha.par.domain.KakaoPayPreReady;
import com.ycha.par.domain.KakaoPayResult;
import com.ycha.par.domain.Payment;
import com.ycha.par.domain.RDV;
import com.ycha.par.service.KakaoPayService;
import com.ycha.par.service.PayInsertService;
import com.ycha.par.service.RDVService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private RDVService rDVService;
	
	@Autowired
	private KakaoPayService kakaoPayService;
	
	@Autowired
	private PayInsertService payInsertService;
	
	
	//예약 정보 받아오기 위한 get  
	@GetMapping("/rdvinfo/{idx}") 
	@CrossOrigin
	@ResponseBody
	public RDV getRDV(@PathVariable("idx") int p_idx) {
		
		System.out.println("RDV 받아오기02"+p_idx);
		
		return rDVService.getRDVInfo(p_idx);
		
	};
	
	//kakao pay 결제 요청을 위한 post  
	@PostMapping("/kakao") 
	@CrossOrigin
	@ResponseBody
	public String kakaoPay(KakaoPayPreReady kakaoPayPreReady) {
		
		System.out.println("kakao pay 요청 02 "+kakaoPayPreReady.toString());
		
		return kakaoPayService.kakaoPayReady(kakaoPayPreReady);
	}
	
	@GetMapping("/kakao/success")
	@CrossOrigin
	@ResponseBody
	public Map<String, Object> kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		
		System.out.println("kakao pay 성공 02 " +pg_token);
		
        //model.addAttribute("info", kakaoPayService.getkakaoPayResult(pg_token));
		//return "http://localhost:8080/parclient/kakao/success.jsp";
		return kakaoPayService.getkakaoPayResult(pg_token);
	}
	
	/*@GetMapping("/kakao/cancle")
	public void kakaoPayCancle() {
		System.out.println("kakao pay 요청 10 ");
	}

	@GetMapping("/kakao/fail")
	public void kakaoPayFail() {
		System.out.println("kakao pay 요청 11 ");
	}*/
	
	
	//결제 완료 후 DB에 저장처리 
	@PostMapping()
	@CrossOrigin
	@ResponseBody
	public int savePayment(@RequestBody Payment payment) {
		
		System.out.println("결제내역02 "+ payment);
		
		return payInsertService.insert(payment);

	}
}

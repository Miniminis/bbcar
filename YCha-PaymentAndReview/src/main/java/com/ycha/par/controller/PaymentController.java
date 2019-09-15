package com.ycha.par.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.ycha.par.domain.KakaoPayPreReady;
import com.ycha.par.domain.Payment;
import com.ycha.par.domain.RDV;
import com.ycha.par.service.GetDepositListService;
import com.ycha.par.service.GetOnePayService;
import com.ycha.par.service.GetPayListService;
import com.ycha.par.service.KakaoPayService;
import com.ycha.par.service.PayInsertService;
import com.ycha.par.service.RDVService;

@RestController
@RequestMapping("/payment")
/* url
	/rdvinfo : 예약내역 
	/kakao : kakao pay api  
	/passenger : 탑승객 
 	/driver : 운전자 
*/
public class PaymentController {
	
	@Autowired
	private RDVService rDVService;
	
	@Autowired
	private KakaoPayService kakaoPayService;
	
	@Autowired
	private PayInsertService payInsertService;
	
	@Autowired
	private GetOnePayService getOnePayService;
	
	@Autowired
	private GetPayListService getPayListService;
	
	@Autowired
	private GetDepositListService getDepositListService;
	
	//예약 정보 받아오기 위한 get  
	@GetMapping("/rdvinfo/{idx}") 
	@CrossOrigin
	public RDV getRDV(@PathVariable("idx") int p_idx) {
		
		System.out.println("RDV 받아오기02"+p_idx);
		
		return rDVService.getRDVInfo(p_idx);
		
	};
	
	//kakao pay 결제 요청을 위한 post  
	@PostMapping("/kakao") 
	@CrossOrigin
	public String kakaoPay(KakaoPayPreReady kakaoPayPreReady) {
		
		System.out.println("kakao pay 요청 02 "+kakaoPayPreReady.toString());
		
		return kakaoPayService.kakaoPayReady(kakaoPayPreReady);
	}
	
	//카카오페이 성공시 호출 매서드 
	@GetMapping("/kakao/success")
	@CrossOrigin
	public Map<String, Object> kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		
		System.out.println("kakao pay 성공 02 " +pg_token);
		
        //model.addAttribute("info", kakaoPayService.getkakaoPayResult(pg_token));
		//return "http://localhost:8080/parclient/kakao/success.jsp";
		return kakaoPayService.getkakaoPayResult(pg_token);
	}
		
	
	//탑승자 - 결제 완료 후 DB에 저장처리 
	@PostMapping("/passenger")
	@CrossOrigin
	public int savePayment(@RequestBody Payment payment) {
		
		System.out.println("결제내역02 "+ payment);
		
		return payInsertService.insert(payment);

	}
	
	//운전자 - 결제 완료 후 DB에서 결제 내역 가져오기 
	@GetMapping("/result/driver/{idx}")
	@CrossOrigin
	public Payment getPaymentDetail(@PathVariable("idx") int r_idx) {
		
		System.out.println("입금내역 02  "+r_idx);
		
		return getOnePayService.getPayDetail(r_idx);
		
	}
	
	//탑승자 - 결제 내역 출력
	@GetMapping("/passenger/{idx}")
	@CrossOrigin
	public List<Payment> getPayList(@PathVariable("idx") int p_idx) {
		
		System.out.println("결제 리스트 02  "+p_idx);
		
		return getPayListService.getListPassenger(p_idx);
		
	}
	
	//운전자 - 입금내역 출력
	@GetMapping("/driver/{idx}")
	@CrossOrigin
	public List<Payment> getDepList(@PathVariable("idx") int d_idx) {
		
		System.out.println("입금리스트  02  "+d_idx);
		
		return getDepositListService.getDepListDriver(d_idx);
		
	}
	
}

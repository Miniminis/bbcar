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
@RequestMapping("/payment/passenger")
/* url
	/kakao : kakao pay api  
	/passenger : 탑승객 
 	/driver : 운전자 
*/
public class PaymentPassengerController {
	
	@Autowired
	private RDVService rDVService;
	
	@Autowired
	private PayInsertService payInsertService;
	
	
	@Autowired
	private GetPayListService getPayListService;
	
	
	//예약 정보 받아오기 위한 get  
	@GetMapping("/rdvinfo/{idx}") 
	@CrossOrigin
	public RDV getRDV(@PathVariable("idx") int p_idx) {
		
		System.out.println("RDV 받아오기02"+p_idx);
		
		return rDVService.getRDVInfo(p_idx);
		
	};
	
	//탑승자 - 결제 완료 후 DB에 저장처리 
	@PostMapping()
	@CrossOrigin
	public int savePayment(@RequestBody Payment payment) {
		
		System.out.println("결제내역02 "+ payment);
		
		return payInsertService.insert(payment);

	}

	//탑승자 - 결제 내역 출력
	@GetMapping("/{idx}")
	@CrossOrigin
	public List<Payment> getPayList(@PathVariable("idx") int p_idx) {
		
		System.out.println("결제 리스트 02  "+p_idx);
		
		return getPayListService.getListPassenger(p_idx);
		
	}
	
}

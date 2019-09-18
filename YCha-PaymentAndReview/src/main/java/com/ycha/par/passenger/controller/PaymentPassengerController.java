package com.ycha.par.passenger.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ycha.par.domain.Payment;
import com.ycha.par.passenger.service.GetPayListService;
import com.ycha.par.passenger.service.PayInsertService;

@RestController
@RequestMapping("/payment/passenger")
public class PaymentPassengerController {
	/* url 
	 * POST : / - 탑승자 결제 DB insert  
	 * GET : /idx	- 탑승자 결제 내역 출력 
	*/
	
	@Autowired
	private PayInsertService payInsertService;
	
	@Autowired
	private GetPayListService getPayListService;
	
	//탑승자 - 결제 완료 후 DB에 저장처리 
	@PostMapping()
	@CrossOrigin
	public int savePayment(@RequestParam("r_idx") int r_idx, 
							@RequestParam("paymethod") String paymethod) {
		
		System.out.println("결제내역02 "+ paymethod);
		
		return payInsertService.insert(r_idx, paymethod);

	}

	//탑승자 - 결제 내역 출력
	@GetMapping("/{idx}")
	@CrossOrigin
	public List<Payment> getPayList(@PathVariable("idx") int p_idx) {
		
		System.out.println("결제 리스트 02  "+p_idx);
		
		return getPayListService.getListPassenger(p_idx);
		
	}
	
}

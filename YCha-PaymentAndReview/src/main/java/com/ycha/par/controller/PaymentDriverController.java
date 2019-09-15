package com.ycha.par.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycha.par.domain.Payment;
import com.ycha.par.service.GetDepositListService;
import com.ycha.par.service.GetOnePayService;


@RestController
@RequestMapping("/payment/driver")
public class PaymentDriverController {
	
	@Autowired
	private GetOnePayService getOnePayService;
	
	@Autowired
	private GetDepositListService getDepositListService;
	
	
	//운전자 - 결제 완료 후 DB에서 결제 내역 가져오기 
	@GetMapping("/result/{idx}")
	@CrossOrigin
	public Payment getPaymentDetail(@PathVariable("idx") int r_idx) {
		
		System.out.println("입금내역 02  "+r_idx);
		
		return getOnePayService.getPayDetail(r_idx);
		
	}
	
	//운전자 - 입금내역 출력
	@GetMapping("/{idx}")
	@CrossOrigin
	public List<Payment> getDepList(@PathVariable("idx") int d_idx) {
		
		System.out.println("입금리스트  02  "+d_idx);
		
		return getDepositListService.getDepListDriver(d_idx);
		
	}

}

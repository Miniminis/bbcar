package com.parboot.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parboot.domain.TossRequestVO;
import com.parboot.service.TossService;

@RestController
@RequestMapping("/parboot/payment/toss")
public class TossController {
	
	@PostMapping("/r_idx/{r_idx}")
	@CrossOrigin
	public ResponseEntity<String> tossTest(HttpServletResponse rep, 
											@PathVariable("r_idx") int r_idx) {
		
		@Autowired
		private TossService tossService;
		
		System.out.println("toss pay 요청 02  "+r_idx);

		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();
		
		try {

			//연결할 url connection
			url = new URL("https://pay.toss.im/api/v1/payments");
			connection = url.openConnection();
			connection.addRequestProperty("Content-Type", "application/json;charset=utf-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			//ResponseEntity<TossRequestVO> tossEntityVo;			
			//System.out.println("toss pay 요청 03  "+tossEntityVo);
			
			//TossRequestVO 객체에 Toss server 로 전달할 데이터 삽입
			TossRequestVO tossVO = new TossRequestVO();
			
			tossVO.setOrderNo("1545");
			tossVO.setAmount(4567);
			tossVO.setAmountTaxFree(0);
			tossVO.setProductDesc("연차 카풀 서비스");
			tossVO.setApiKey("sk_test_apikey1234567890");
			tossVO.setAutoExecute(true);
			tossVO.setResultCallback("https://localhost:8080/parclient/toss/success.html");
			tossVO.setRetUrl("http://localhost:8080/test/ordercheck.jsp?r_idx=");
			tossVO.setRetCancelUrl("http://localhost:8080/test/close.jsp");
			
			System.out.println("toss pay 요청 04  "+tossVO.toString());
			
			//tossEntityVo = new ResponseEntity<TossRequestVO>(tossVO, HttpStatus.OK);
			//System.out.println("toss pay 요청 05  "+tossEntityVo);
			
			//자바 객체인 tossVO를 json 객체로 변경
			String json = new ObjectMapper().writeValueAsString(tossVO);			
			System.out.println("toss pay 요청 05  "+json.toString());

			//output stream 이용하여 연결된 url connection에 json 객체 넘겨주기
			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			bos.write(json.toString().getBytes(StandardCharsets.UTF_8));
			bos.flush();
			bos.close();

			//input stream 통해서 결제 결과 받아오기 
			BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				//tossRequestVo = line;
				responseBody.append(line);
				System.out.println("toss pay 요청 06  "+responseBody.toString());
			}
			br.close();
		} catch (Exception e) {
			responseBody.append(e);
		}
		
		System.out.println("toss pay 요청 07  " + responseBody.toString());
		
		String str = responseBody.toString();
		System.out.println("toss pay 요청 08  " + str);
		
		return new ResponseEntity<String>(str, HttpStatus.OK);
		
	}

}

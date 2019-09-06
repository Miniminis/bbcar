package com.mytest.test.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mytest.test.service.TossPayService;

@Controller("/test/tosstest/")
public class TossController {

	// @Autowired
	// private TossPayService tossPayService;

	/*
	 * @PostMapping("/callback") public String tossPay() {
	 * 
	 * tossPayService.tossTest(); }
	 */

//	@GetMapping
//	public String tossRet() {
//		
//	}

	@GetMapping()
	@CrossOrigin
	public void tossTest() {

		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();
		
		try {
			
		url = new URL("https://pay.toss.im/api/v1/payments");
		connection = url.openConnection();
		connection.addRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);
		connection.setDoInput(true);

		org.json.simple.JSONObject jsonBody = new JSONObject();
		jsonBody.put("orderNo", "1");
		jsonBody.put("amount", 35000);
		jsonBody.put("amountTaxFree", 0);
		jsonBody.put("productDesc", "토스티셔츠");
		jsonBody.put("apiKey", "sk_test_apikey1234567890");
		jsonBody.put("autoExecute", true);
		jsonBody.put("resultCallback", "https://YOUR-SITE.COM/callback");
		jsonBody.put("retUrl", "http://YOUR-SITE.COM/ORDER-CHECK");
		jsonBody.put("retCancelUrl", "http://YOUR-SITE.COM/close");

		BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
		bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
		bos.flush();
		bos.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
		String line = null;
		while ((line = br.readLine()) != null) {
		responseBody.append(line);
		}
		br.close();
		} catch (Exception e) {
		responseBody.append(e);
		}
		System.out.println(responseBody.toString());

	}
	
}

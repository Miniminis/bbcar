package com.mytest.test.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tosstest")
public class TossController {

	@PostMapping()
	@CrossOrigin
	//@ResponseBody
	public ResponseEntity<String> tossTest(HttpServletResponse rep) {

		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();
		
		try {

			url = new URL("https://pay.toss.im/api/v1/payments");
			connection = url.openConnection();
			connection.addRequestProperty("Content-Type", "application/json;charset=utf-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);

			JSONObject jsonBody = new JSONObject();
			jsonBody.put("orderNo", "100");
			jsonBody.put("amount", 35000);
			jsonBody.put("amountTaxFree", 0);
			jsonBody.put("productDesc", "토스티셔츠");
			jsonBody.put("apiKey", "sk_test_apikey1234567890");
			jsonBody.put("autoExecute", true);
			jsonBody.put("resultCallback", "https://localhost:8080/test/callback.jsp");
			jsonBody.put("retUrl", "http://localhost:8080/test/ordercheck.jsp");
			jsonBody.put("retCancelUrl", "http://localhost:8080/test/close.jsp");

			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
			bos.flush();
			bos.close();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				//tossRequestVo = line;
				responseBody.append(line);
			}
			br.close();
		} catch (Exception e) {
			responseBody.append(e);
		}
		System.out.println("결제 결과 01" + responseBody.toString());
		
		String str = responseBody.toString();
		System.out.println("결제 결과 02" + str);
		/*PrintWriter out;
		
		try {
			out = rep.getWriter();
			out.println("결제 결과 03 " + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		json.put("data", str);
		
		System.out.println("결제 결과 04" + json.get("data"));*/
		
		return new ResponseEntity<String>(str, HttpStatus.OK);
		
	}

}

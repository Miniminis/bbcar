package com.ycar.server.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.server.reservation.domain.Carpool;
import com.ycar.server.reservation.domain.MyCarpool;
import com.ycar.server.reservation.domain.Reservation;
import com.ycar.server.reservation.domain.lonlat;
import com.ycar.server.reservation.service.CarpoolService;
import com.ycar.server.reservation.service.MyCarpoolListService;
import com.ycar.server.reservation.service.ReservationService;
import com.ycar.server.reservation.service.LonLatService;

@RestController
public class ReservationController {
	
	@Autowired
	private CarpoolService carpoolService;
	
	@Autowired
	private ReservationService rsvService;
	
	@Autowired
	private MyCarpoolListService myCarpoolService;
	
	@Autowired
	private LonLatService lonlatService;
	
	//전체 카풀 리스트
	@CrossOrigin
	@GetMapping("/rsv/carpool")
	public List<Carpool> getAllList(){
		
		List<Carpool> list = carpoolService.getAllList();
		
		return list;
	}
	
	//카풀 검색
	@CrossOrigin
	@GetMapping("/rsv/searchcarpool")
	public ResponseEntity<List<Carpool>> getSearchList(
			@RequestParam(value = "date", required = false) String date, 
			@RequestParam(value = "time", required = false) String time, 
			@RequestParam(value = "startPoint", required = false) String startPoint, 
			@RequestParam(value = "endPoint", required = false) String endPoint){
		
		List<Carpool> list = carpoolService.getSearchList(date, time, startPoint, endPoint);
		
		ResponseEntity<List<Carpool>> entity = new ResponseEntity<List<Carpool>>(list, HttpStatus.OK);
		
		System.out.println("검색 컨트롤러"+ date + time + startPoint + endPoint);
		
		return entity;
	}

	//예약할 카풀 선택해서 정보 부르자 
	@CrossOrigin
	@GetMapping("/rsv/carpool/{dr_idx}")
	public Carpool selectCarpool(@PathVariable("dr_idx") int idx) {
		
		return carpoolService.selectCarpool(idx);
	}
	
	//불러와서 rsv테이블에 insert
	@CrossOrigin
	@PostMapping("/rsv/reserve/{p_idx}")
	public String insert(Reservation rsv, @PathVariable("p_idx") int idx) {
		
		String Demail = rsvService.getDemail(rsv.getDr_idx());
		
		rsvService.send(Demail);
		
		int result = rsvService.reserve(rsv);
		
		return result > 0 ? "success" : "fail";		
	}
	
//	//회원별 예약 리스트 대기예약과 확정/거절예약으로 리스트 분리
//	@CrossOrigin
//	@GetMapping("/reserve/{p_idx}")
//	public List<Reservation> selectByPidx(@PathVariable("p_idx") int idx) {
//
//		List<Reservation> list = rsvService.getRsvList(idx);
//
//		return list;
//	}
	
	//나의카풀 페이지에 출력될 예약 확정 리스트
	@CrossOrigin
	@GetMapping("/rsv/mycarpool/{p_idx}")
	public List<MyCarpool> getConfirmList(@PathVariable("p_idx") int idx){
		
		List<MyCarpool> list = myCarpoolService.getConfirmList(idx);
		
		return list;
	}
	
	// 나의카풀 페이지에 출력될 예약 대기 리스트
	@CrossOrigin
	@GetMapping("/rsv/waitcarpool/{p_idx}")
	public List<MyCarpool> getWaitingList(@PathVariable("p_idx") int idx) {

		List<MyCarpool> list = myCarpoolService.getWaitingList(idx);

		return list;
	}
	
	//나의카풀 페이지에 과거 예약(완료된)리스트
	@CrossOrigin
	@GetMapping("/rsv/pastcarpool/{p_idx}")
	public List<MyCarpool> getPastList(@PathVariable("p_idx") int idx){
		
		List<MyCarpool> list = myCarpoolService.getPastList(idx);
		
		return list;
	}
	
	//완료예약삭제
	@CrossOrigin
	@DeleteMapping("/rsv/mycarpool/{p_idx}/{r_idx}")
	public String delete(@PathVariable("p_idx") int p_idx, @PathVariable("r_idx") int r_idx) {
		
		int result = rsvService.delete(p_idx, r_idx);
		
		return result > 0 ?"success":"fail";
	}
	
	//대기예약삭제
	@CrossOrigin
	@DeleteMapping("/rsv/waitcarpool/{p_idx}/{r_idx}")
	public String wdelete(@PathVariable("p_idx") int p_idx, @PathVariable("r_idx") int r_idx) {
		
//		String Demail = rsvService.getDemail(dr_idx);
//		
//		rsvService.send(Demail);
		
		int result = rsvService.delete(p_idx, r_idx);
		
		return result > 0 ?"success":"fail";
	}
	
	@CrossOrigin
	@GetMapping("/rsv/lonlat/{r_idx}")
	public lonlat selectLonlat(
			@PathVariable("r_idx") int r_idx
			) {
		System.out.println("r_idx 값은 :  " + r_idx);
		lonlat DR = lonlatService.selectlonlat(r_idx);
		
		System.out.println("lonlat을 가져온 결과를 출력해보쟛  : " + DR);
		return DR;
	}
	
}
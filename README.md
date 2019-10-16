# Spring, Spring-Boot, NodeJS를 이용해서 구현한 카풀 웹앱 프로젝트 - 결제 및 후기 페이지
> 직장인을 위한 안전한 카풀앱, 연차 - 결제 및 후기 페이지 <br>
> [탑승자 데모 페이지 Link](http://13.125.252.85:8080/passenger/) <br>
> [운전자 데모 페이지 Link](http://13.125.252.85:8080/driver/) <br>
> 구현화면 : 하단 참조

## 기술/구조
* 웹 표준 
    * `HTML5`
    * `CSS3`
    * `JavaScript`
    * `jQuery`
    * `Bootstrap4`
* TOMCAT 컨테이너 사용
* DBMS - `MySQL`
* Spring Framework, Spring-Boot, NodeJs(Socket.io)
* `MyBatis`, `JPA`
* 결제 Api 사용 
    * `jQuery`, `aJax`, `JSON` 파싱
    * 카카오페이 api
    * Toss Api 
    * 아임포트 api
* `RESTful Api 구조`  
    * 서버 <--> 클라이언트 통신을 위한 REST 인터페이스 구현
* `Spring Boot` & `JPA`: 결제 기능 중 `Toss Api` 와 `아임포트 Api` 부분은 Spring Boot 와 JPA 를 이용하여 구현함 
* `NodeJS` : 탑승자와 운전자 소켓 통신을 통한 데이터 공유 부분을 NodeJs의 Socket.io 를 이용하여 구현함 
* `AWS` 배포 : Spring, Spring-Boot, Node 프로젝트 서버 부분
    * `EC2`
    * `RDS`  
 
## 결제/후기 페이지 구조
<img src="https://github.com/Miniminis/BBCar/blob/master/ycar_screenshots/YCAR.png">

## 흐름도
* 전체 : 
<img src="https://github.com/Miniminis/BBCar/blob/master/ycar_screenshots/driver_flow.png">
<img src="https://github.com/Miniminis/BBCar/blob/master/ycar_screenshots/passenger_flow.png">

* 결제 페이지 : 
<img src="https://github.com/Miniminis/BBCar/blob/master/payment.png">

* 후기 페이지 : 
<img src="https://github.com/Miniminis/BBCar/blob/master/review.png">

## 소스코드 
* [전체 소스코드 GROUP GITHUB LINK - 초기버전](https://github.com/mand2/y-car-project)
* [개인 소스코드 PERSONAL GITHUB LINK - 개선 진행중](https://github.com/Miniminis/BBCar/tree/master/Ycar-All-Project-v2)  

## 프로젝트 PPT 
* 각 기능 설명 
* 구현 중 어려웠던 점 
* 협업 시 어려웠던 점
* [Link](https://docs.google.com/presentation/d/1_1OVTik6grzxKbo3_WMnwzl9_Jl6KYHD/edit)

## 결제 및 후기 파트를 구현하면서 겪었던 어려움 
* **유사 코드에 대한 구조적 고민** <br>
혼자서 운전자와 탑승자 부분을 동시에 구현하다보니 겹치는 코드들을 이용하여 두 부분을 같은 프로젝트 폴더 내에서 구현을 해야할지, 아니면 확장성을 고려하여 처음부터 다른 패키지에 구현을 해야할지 고민이 많았음 <br>
--> **향후 확장성 및 코드 관리의 용이함**을 위해 프로젝트를 분리하여 관리하기로함. 그에 맞춰 대대적인 프로젝트 구조 변경 작업이 진행되었음.  <br>

* **두 사용자의 실시간 데이터 공유** <br>
특정 탑승자와 특정 운전자가 다른 어플리케이션에서 실시간으로 데이터를 공유하고 통신을 해야 했음 <br>
--> **`NodeJS`** 및 **`socket.io`** 를 이용한 통신으로 해결함 <br>
--> Unique 값인 예약번호(r_idx)를 이용하여 **특정 탑승자와 특정 운전자 단 둘만 join 할 수 있는 room 을 생성하고 배열에 담음** <br>
--> 통신으로 두 사용자 간 데이터 공유가 필요한 페이지마다 room join event 처리 <br>
--> node 서버에서는 event listener 에 맞는 callback 함수가 처리되도록 함 <br>
--> 운행 종료시 발생하는 이벤트 서버단 코드 <br>
```
//운행 종료 시 room join 처리
    socket.on('join room', function(r_idx){
        console.log('r_idx 로 룸 조인 ', r_idx);
        
        roomIdx = room.indexOf('room'+r_idx);
        if(r_idx != null  && roomIdx == -1) { //만약 r_idx 번 방이 존재하지 않다면,
            room.push('room'+r_idx); //신규 방 생성 
            console.log('신규 방 생성 : '+r_idx);
            console.log('현재 방 배열 : ', room);
        }

        roomIdx = room.indexOf('room'+r_idx);
        socket.join(room[roomIdx], function(){
            console.log(roomIdx+'번 룸에 조인됨');
        });
    });

    socket.on('arrive', function(r_idx){
        console.log('arrive 이벤트 리슨');
        io.to(room[roomIdx]).emit('redirect', r_idx);
    });
```

* **조인이 많은 SQL 문의 JPA 적용**  <br>
Spring Boot 및 JPA 를 프로젝트에 적용하는 과정에서 **조인이 많은 SQL 조건문** 의 경우 구현하는데 어려움이 있었음 <br>
--> **@JoinColumn** 을 이용, @OneToMany, @ManyToOne, @OneToOne 등 **컬럼간 관계설정** 을 통해 다소 까다로웠던 조인문을 구현할 수 있었음 <br>
--> ReservationEntity 에서 CarpoolEntity와 조인컬럼 처리한 코드 <br>
```
@ManyToOne	//다 대 일 관계 : 예약 - 카풀경로
	@JoinColumn(name = "dr_idx", insertable = false, updatable = false) //조인 컬럼 정의 
	private CarpoolEntity carpoolEntity; //카풀

	public CarpoolEntity getCarpoolEntity() {
		return carpoolEntity;
	}

	public void setCarpoolEntity(CarpoolEntity carpoolEntity) {
		this.carpoolEntity = carpoolEntity;
	}
```

* **협업** 시, 코드를 결합하는 과정에서 시간이 많이 소요됨 <br>
--> **git** 의 효율적인 사용을 통해 보완해가야할 점 

* **Node 서버**, **Boot 서버 AWS 배포** <br>
익숙했던 Spring Project 가 아닌, 새롭게 배워 적용한 nodeJS 및 Spring-boot 프로젝트를 배포하는 과정에서 시행착오를 겪음. <br>
--> 그러나 유투브 및 구글 검색을 통해 우리 프로젝트에 맞는 환경에서 aws ec2로 배포하는 과정을 무사히 완료함 

## 테스트 안내 
**연차는 탑승자와 운전자간의 소통이 필수적인 카풀 웹 어플리케이션입니다. 두 화면을 같이 띄워서 테스트 해보시는 것을 권장합니다.** 
1. 탑승자 계정 : 
* ID : passengerggg
* PW : gggg1!
<br>

2. 운전자 계정 : 
* ID : test2
* PW : 1234

## 구현화면 
![메인화면](https://github.com/Miniminis/BBCar/blob/master/ycar_screenshots/ycar2.png)
![카풀등록 및 선택](https://github.com/Miniminis/BBCar/blob/master/ycar_screenshots/ycar3.png)
![운행중](https://github.com/Miniminis/BBCar/blob/master/ycar_screenshots/ycar15.png)
![결제수단선택](https://github.com/Miniminis/BBCar/blob/master/ycar_screenshots/ycar17.png)
![결제내역](https://github.com/Miniminis/BBCar/blob/master/ycar_screenshots/ycar18.png)
![후기작성](https://github.com/Miniminis/BBCar/blob/master/ycar_screenshots/ycar19.png)


## 버전설명
* 자세한 사항은 본 repository의 커밋 내역 중 [YCha-Payment]를 참고
* v0
   * 프로젝트 기획, 업무분담
   * DB erd로 테이블 구조 설정 
   * 카카오 페이 api 구현 
   * 일부 페이지들 간단한 마크업 완료 
   * 결제 내역 페이지 완성 
* v1
   * 별점 플러그인 이용하여 후기 별점 기능 추가 
   * 탑승자 후기 작성 기능 완료 
   * navbar - body 분리하여 관리 : javascript 로 동적으로 구성 
   * 운전자 입금내역 페이지 구성 - 탑승자와 socket 통신 필요 : 우선 보류 
   * 탑승자 결제 리스트 페이지 완료 
   * 운전자 결제 리스트 페이지 완료 
   * 운전자 후기 등록 페이지 완료 
   * 탑승자 후기 리스트, 후기 삭제 기능 추가 
   * 탑승자 후기 수정 및 운전자 후기 CRUD 추가  
* v2
    * `Toss pay Api` 테스트 완료 
    * `Payco`, `import` 이용하여 구현 진행 중 
    * 운전자 경로등록, 예약, 결제, 후기 DB 구조 변경 --> 전체 프로젝트 구조 운전자-탑승자 분리하여 변경 및 바뀐 DB에 맞게 코드 변경 
    * 전체 프로젝트 스타일 적용 
* v3 
   * `NodeJS`, `socket.io` 이용하여 탑승자와 운전자 실시간 통신 및 정보공유 기능 구현 
   * `아임포트 KG 이니시스` 결제 api 구현 완료
   * 프로젝트의 운전자 및 탑승자 로그인/회원가입 부분과 연결을 위해 로그인 세션 값 설정 완료 
   * 전체 프로젝트 결합 완료 
* v4 
   * NodeJS로 탑승자 후기작성부분 서버 재구성 진행중
   * Spring-Boot 및 JPA 이용, 운전자 후기작성부분 서버 재구성 진행중 
   

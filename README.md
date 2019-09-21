# BBCar - payment and review
> 직장인을 위한 안전한 카풀앱, 우리회사 붕붕카 - 결제 및 후기 페이지 
[Link]()

#### 소스코드 
* [전체 소스코드](https://github.com/mand2/y-car-project)
* [개인 소스코드 - 서버](https://github.com/Miniminis/BBCar/tree/master/YCha-PaymentAndReview)
* [개인 소스코드 - 클라이언트](https://github.com/Miniminis/BBCar/tree/master/YCha-Client-PaymentAndReview/WebContent)

#### 흐름도
* 전체 : 
<img src="">

* 관리자 페이지 : 
<img src="">

#### 기술/구조
* 웹 표준 
    * `HTML5`
    * `CSS3`
    * `JavaScript`
    * `jQuery`
    * `Bootstrap4`
* TOMCAT 컨테이너 사용
* DBMS - `MySQL`
* Spring Framework
* `MyBatis`
* 결제 Api 사용 
    * `jQuery`, `aJax`, `JSON` 파싱
    * 카카오페이 api
    * Toss Api 
    * 아임포트 api
* `RESTful Api 구조`  
    * 서버 <--> 클라이언트 통신을 위한 REST 인터페이스 구현
* `AWS` 배포
    * `EC2`
    * `RDS`

#### 버전설명
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
    * Toss pay Api 테스트 완료 
    * Payco, import 이용하여 구현 진행 중 
    * 운전자 경로등록, 예약, 결제, 후기 DB 구조 변경 --> 전체 프로젝트 구조 운전자-탑승자 분리하여 변경 및 바뀐 DB에 맞게 코드 변경 
    * 전체 프로젝트 스타일 적용 
   
#### 구현 중 겪었던 어려움 
* 
* 
* 

#### 프로젝트 설명 PPT 
* 각 기능 설명 
* 구현 중 어려웠던 점 
* 개선사항 
* [Link]()

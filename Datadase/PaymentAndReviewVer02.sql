
-- 결제
ALTER TABLE PAYMENT
	DROP FOREIGN KEY FK_RESERVATION_TO_PAYMENT; -- 예약 -> 결제

-- 결제
ALTER TABLE PAYMENT
	DROP PRIMARY KEY; -- 결제 기본키

-- 결제
DROP TABLE IF EXISTS PAYMENT RESTRICT;
select * from PAYMENT;

-- 결제
CREATE TABLE PAYMENT (
	payidx    INT(7)      NOT NULL, -- 결제번호
	r_idx     INT(10)     NOT NULL, -- 예약번호
	paydate   datetime default CURRENT_TIMESTAMP NOT NULL, -- 결제날짜
	paymethod VARCHAR(20) NOT NULL  -- 결제수단
);


-- 결제
ALTER TABLE PAYMENT
	ADD CONSTRAINT PK_PAYMENT -- 결제 기본키
		PRIMARY KEY (
			payidx -- 결제번호
		);

-- 결제
ALTER TABLE PAYMENT
	ADD CONSTRAINT FK_RESERVATION_TO_PAYMENT -- 예약 -> 결제
		FOREIGN KEY (
			r_idx -- 예약번호
		)
		REFERENCES RESERVATION ( -- 예약
			r_idx -- 예약번호
		);

-- ------------------------------------------------------------------------------------------------------
-- 리뷰
ALTER TABLE REVIEW
	DROP FOREIGN KEY FK_PASSENGER_TO_REVIEW; -- 탑승자 -> 리뷰

-- 리뷰
ALTER TABLE REVIEW
	DROP FOREIGN KEY FK_DRIVER_TO_REVIEW; -- 운전자 -> 리뷰

-- 리뷰
ALTER TABLE REVIEW
	DROP FOREIGN KEY FK_PAYMENT_TO_REVIEW; -- 결제 -> 리뷰

-- 리뷰
ALTER TABLE REVIEW
	DROP PRIMARY KEY; -- 리뷰 기본키

-- 리뷰
DROP TABLE IF EXISTS REVIEW RESTRICT;
select * from REVIEW;

-- 리뷰
CREATE TABLE REVIEW (
	rv_idx  INT(7)       NOT NULL, -- 리뷰번호
	p_idx   INT(10)      NOT NULL, -- 탑승자 회원번호
	d_idx   INT(10)      NOT NULL, -- 운전자 회원번호
	payidx  INT(7)       NOT NULL, -- 결제번호
	writer  VARCHAR(10)  NULL,     -- 작성자
	content VARCHAR(255) NULL,     -- 리뷰내용
	star    INT(10)      NULL      -- 별점
);

-- 리뷰
ALTER TABLE REVIEW
	ADD CONSTRAINT PK_REVIEW -- 리뷰 기본키
		PRIMARY KEY (
			rv_idx -- 리뷰번호
		);

-- 리뷰
ALTER TABLE REVIEW
	ADD CONSTRAINT FK_PASSENGER_TO_REVIEW -- 탑승자 -> 리뷰
		FOREIGN KEY (
			p_idx -- 탑승자 회원번호
		)
		REFERENCES PASSENGER ( -- 탑승자
			p_idx -- 회원번호
		);

-- 리뷰
ALTER TABLE REVIEW
	ADD CONSTRAINT FK_DRIVER_TO_REVIEW -- 운전자 -> 리뷰
		FOREIGN KEY (
			d_idx -- 운전자 회원번호
		)
		REFERENCES DRIVER ( -- 운전자
			d_idx -- 회원번호
		);

-- 리뷰
ALTER TABLE REVIEW
	ADD CONSTRAINT FK_PAYMENT_TO_REVIEW -- 결제 -> 리뷰
		FOREIGN KEY (
			payidx -- 결제번호
		)
		REFERENCES PAYMENT ( -- 결제
			payidx -- 결제번호
		);
        
-- -------------------------------------------------------------------------------------------------------------------------------
select * from D_CARPOOL;
select * from RESERVATION;
select * from PAYMENT;
select * from REVIEW;        

-- dummies 
insert into PAYMENT values (null, 2, default, 'card');
delete from PAYMENT where payidx=2;

-- 결제 전 예약 정보 조회 
select r_idx, p_idx, d_fee from RESERVATION join D_CARPOOL using(dr_idx) where p_idx=1;

-- 결제 내역 
select payidx, paydate, d_distance, d_commute, d_starttime, d_endtime, d_fee, paymethod, d_startpoint, d_endpoint 
from PAYMENT join RESERVATION using(r_idx) join D_CARPOOL using(dr_idx) where r_idx=2;

-- 결제 리스트 탑승자
select  payidx, paydate, d_distance, d_commute, d_starttime, d_endtime, d_fee, paymethod, d_startpoint, d_endpoint 
from PAYMENT join RESERVATION using(r_idx) join D_CARPOOL using(dr_idx) where p_idx=6;

-- 결제 리스트 운전자 
select payidx, paydate, d_distance, d_commute, d_starttime, d_endtime, 
d_fee, paymethod, d_startpoint, d_endpoint 
from PAYMENT 
join RESERVATION using(r_idx) 
join D_CARPOOL using(dr_idx) 
where d_idx=2;

-- 후기 등록 
select d_idx from PAYMENT join RESERVATION using (r_idx) join D_CARPOOL using (dr_idx) where payidx=1;
insert into REVIEW values (null, 1, 2, 1, "운전자", "별로;;", 2);
select * from REVIEW where payidx=1;
select * from REVIEW;

-- passenger list 
select * from REVIEW where p_idx=6;


-- 후기 리스트 
select rv_idx, p_idx, d_idx, payidx, writer, content, star, PASSENGER.nickname as p_nickname, DRIVER.nickname as d_nickname 
from REVIEW join PASSENGER using(p_idx) join DRIVER using(d_idx) where p_idx=1;

-- 수정할 후기 확인
select * from REVIEW where rv_idx=1;

-- 수정처리
update REVIEW set content = "굳굳 낫베드", star=10 where rv_idx=1;
select * from REVIEW;

-- 삭제 처리
delete from REVIEW where rv_idx=1;

-- 후기 리스트 운전자 
select rv_idx, p_idx, d_idx, payidx, writer, content, star, 
PASSENGER.nickname as p_nickname, DRIVER.nickname as d_nickname 
from REVIEW 
join PASSENGER using(p_idx) 
join DRIVER using(d_idx) 
where d_idx=2;


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
select * from RESERVATION 
join D_CARPOOL using(dr_idx) 
join PAYMENT using (r_idx);

select paydate, d_distance, d_starttime, d_endtime, d_fee,  from 
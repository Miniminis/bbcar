-- 결제
ALTER TABLE PAYMENT
	DROP FOREIGN KEY FK_RDV_TO_PAYMENT; -- 확정된예약 -> 결제

-- 결제
ALTER TABLE PAYMENT
	DROP PRIMARY KEY; -- 결제 기본키

-- 결제
DROP TABLE IF EXISTS PAYMENT RESTRICT;

-- 결제
CREATE TABLE PAYMENT (
	payidx      INT(7)       NOT NULL, -- 결제번호
	r_idx       INT(10)      NOT NULL, -- 확정예약번호
	serdate     DATE         NOT NULL, -- 운행날짜
	sertime     INT(10)      NOT NULL, -- 총운행시간
	serdistance INT(20)      NOT NULL, -- 총운행거리
	serprice    INT(10)      NOT NULL, -- 총금액
	dtime       DATE         NOT NULL, -- 출발시간
	dplace      VARCHAR(255) NOT NULL, -- 출발지
	atime       DATE         NOT NULL, -- 도착시간
	aplace      VARCHAR(255) NOT NULL  -- 도착지
);

-- 결제
ALTER TABLE PAYMENT
	ADD CONSTRAINT PK_PAYMENT -- 결제 기본키
		PRIMARY KEY (
			payidx -- 결제번호
		);

-- 결제
ALTER TABLE PAYMENT
	ADD CONSTRAINT FK_RDV_TO_PAYMENT -- 확정된예약 -> 결제
		FOREIGN KEY (
			r_idx -- 확정예약번호
		)
		REFERENCES RDV ( -- 확정된예약
			r_idx -- 확정예약번호
		);
        
-- ---------------------------------------------------------------------------------------------------------------------------------
-- 리뷰
ALTER TABLE REVIEW
	DROP FOREIGN KEY FK_PAYMENT_TO_REVIEW; -- 결제 -> 리뷰

-- 리뷰
ALTER TABLE REVIEW
	DROP FOREIGN KEY FK_PASSENGER_TO_REVIEW; -- 탑승자 -> 리뷰

-- 리뷰
ALTER TABLE REVIEW
	DROP FOREIGN KEY FK_DRIVER_TO_REVIEW; -- 운전자 -> 리뷰

-- 리뷰
ALTER TABLE REVIEW
	DROP PRIMARY KEY; -- 리뷰 기본키

-- 리뷰
DROP TABLE IF EXISTS REVIEW RESTRICT;

-- 리뷰
CREATE TABLE REVIEW (
	rv_idx     INT(7)       NOT NULL, -- 리뷰번호
	payidx     INT(7)       NOT NULL, -- 결제번호
	p_idx      INT(10)      NOT NULL, -- 탑승자번호
	d_idx      INT(10)      NOT NULL, -- 운전자번호
	pr_content VARCHAR(255) NOT NULL, -- 탑승자리뷰내용
	pr_star    INT(10)      NOT NULL default 0, -- 탑승자별점
	dr_content VARCHAR(255) NOT NULL, -- 운전자리뷰내용
	dr_star    INT(10)      NOT NULL default 0  -- 운전자별점
);

-- 리뷰
ALTER TABLE REVIEW
	ADD CONSTRAINT PK_REVIEW -- 리뷰 기본키
		PRIMARY KEY (
			rv_idx -- 리뷰번호
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

-- 리뷰
ALTER TABLE REVIEW
	ADD CONSTRAINT FK_PASSENGER_TO_REVIEW -- 탑승자 -> 리뷰
		FOREIGN KEY (
			p_idx -- 탑승자번호
		)
		REFERENCES PASSENGER ( -- 탑승자
			p_idx -- 회원번호
		);

-- 리뷰
ALTER TABLE REVIEW
	ADD CONSTRAINT FK_DRIVER_TO_REVIEW -- 운전자 -> 리뷰
		FOREIGN KEY (
			d_idx -- 운전자번호
		)
		REFERENCES DRIVER ( -- 운전자
			d_idx -- 회원번호
		);
        
        
        
SELECT * FROM REVIEW;
DESC REVIEW;
SELECT * FROM PAYMENT;
DESC REVIEW;

SELECT * FROM PASSENGER;
SELECT * FROM DRIVER;
SELECT * FROM RDV;

INSERT INTO PAYMENT ('', 1, '2019-09-06', );
INSERT INTO REVIEW ();
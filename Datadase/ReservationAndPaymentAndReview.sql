-- 운전자카풀등록
ALTER TABLE D_CARPOOL
	DROP FOREIGN KEY FK_DRIVER_TO_D_CARPOOL; -- 운전자 -> 운전자카풀등록

-- 운전자카풀등록
ALTER TABLE D_CARPOOL
	DROP PRIMARY KEY; -- 운전자카풀등록 기본키

-- 운전자카풀등록
DROP TABLE IF EXISTS D_CARPOOL RESTRICT;

-- 운전자카풀등록
CREATE TABLE D_CARPOOL (
	dr_idx       INT(10)      NOT NULL, -- 카풀등록번호
	d_idx        INT(10)      NOT NULL, -- 운전자 회원번호
	d_date       DATE         NOT NULL, -- 운행날짜
	d_starttime  INT(10)      NOT NULL, -- 출발시간
	d_endtime    INT(10)      NOT NULL, -- 도착시간
	d_startpoint VARCHAR(255) NOT NULL, -- 출발지
	d_endpoint   VARCHAR(255) NOT NULL, -- 도착지
	d_commute    VARCHAR(4)   NOT NULL, -- 출퇴근
	d_fee        INT(10)      NOT NULL, -- 요금
	d_distance   INT(20)      NULL,     -- 운행거리
	d_lonlat     VARCHAR(500) NULL      -- 위도경도
);

-- 운전자카풀등록
ALTER TABLE D_CARPOOL
	ADD CONSTRAINT PK_D_CARPOOL -- 운전자카풀등록 기본키
		PRIMARY KEY (
			dr_idx -- 카풀등록번호
		);

-- 운전자카풀등록
ALTER TABLE D_CARPOOL
	ADD CONSTRAINT FK_DRIVER_TO_D_CARPOOL -- 운전자 -> 운전자카풀등록
		FOREIGN KEY (
			d_idx -- 운전자 회원번호
		)
		REFERENCES DRIVER ( -- 운전자
			d_idx -- 회원번호
		);
        
        
-- ----------------------------------------------------------------------------------------------------------------
-- 예약
ALTER TABLE RESERVATION
	DROP FOREIGN KEY FK_PASSENGER_TO_RESERVATION; -- 탑승자 -> 예약

-- 예약
ALTER TABLE RESERVATION
	DROP FOREIGN KEY FK_D_CARPOOL_TO_RESERVATION; -- 운전자카풀등록 -> 예약

-- 예약
ALTER TABLE RESERVATION
	DROP PRIMARY KEY; -- 예약 기본키

-- 예약
DROP TABLE IF EXISTS RESERVATION RESTRICT;

-- 예약
CREATE TABLE RESERVATION (
	r_idx     INT(10) NOT NULL, -- 예약번호
	p_idx     INT(10) NOT NULL, -- 탑승자 회원번호
	dr_idx    INT(10) NOT NULL, -- 카풀등록번호
	r_date    DATE    NOT NULL, -- 예약날짜
	r_confirm CHAR(1) NULL      -- 운전자 승낙여부
);

-- 예약
ALTER TABLE RESERVATION
	ADD CONSTRAINT PK_RESERVATION -- 예약 기본키
		PRIMARY KEY (
			r_idx -- 예약번호
		);

-- 예약
ALTER TABLE RESERVATION
	ADD CONSTRAINT FK_PASSENGER_TO_RESERVATION -- 탑승자 -> 예약
		FOREIGN KEY (
			p_idx -- 탑승자 회원번호
		)
		REFERENCES PASSENGER ( -- 탑승자
			p_idx -- 회원번호
		);

-- 예약
ALTER TABLE RESERVATION
	ADD CONSTRAINT FK_D_CARPOOL_TO_RESERVATION -- 운전자카풀등록 -> 예약
		FOREIGN KEY (
			dr_idx -- 카풀등록번호
		)
		REFERENCES D_CARPOOL ( -- 운전자카풀등록
			dr_idx -- 카풀등록번호
		);

SELECT * FROM ycar.RESERVATION;

select r.r_idx, r.p_idx, d.d_fee    from RESERVATION r join D_CARPOOL d using(dr_idx)    where r.r_idx=3;

select p.payidx, p.paydate, d.d_distance, d.d_commute, d.d_starttime, 
d.d_endtime, d.d_fee, p.paymethod, d.d_startpoint, d.d_endpoint 
from PAYMENT p
join RESERVATION r using(r_idx) 
join D_CARPOOL d using(dr_idx) 
where r.r_idx=3;

select  p.payidx, p.paydate, d.d_distance, d.d_commute, d.d_starttime, d.d_endtime, 
d.d_fee, p.paymethod, d.d_startpoint, d.d_endpoint 
from PAYMENT p 
join RESERVATION r using(r_idx) 
join D_CARPOOL d using(dr_idx) 
where r.p_idx=11;

select p.payidx, p.paydate, d.d_distance, d.d_commute, d.d_starttime, d.d_endtime, 
d.d_fee, p.paymethod, d.d_startpoint, d.d_endpoint 
from PAYMENT p
join RESERVATION r using(r_idx) 
join D_CARPOOL d using(dr_idx) 
where d.d_idx=2;

select p_idx 
from PAYMENT p
join RESERVATION r using (r_idx) 
join D_CARPOOL d using (dr_idx) 
where p.payidx=29;

select r.rv_idx, p.p_idx, d.d_idx, r.payidx, r.writer, r.content, r.star, 
p.nickname as p_nickname, d.nickname as d_nickname 
from REVIEW r
join PASSENGER p using(p_idx) 
join DRIVER d using(d_idx) 
where d.d_idx=2;

select d.d_idx 
from PAYMENT p
join RESERVATION r using (r_idx) 
join D_CARPOOL d using (dr_idx) 
where p.payidx=29;

select r.rv_idx, p.p_idx, d.d_idx, 
r.payidx, r.writer, r.content, r.star, 
p.nickname as p_nickname, d.nickname as d_nickname 
from REVIEW r
join PASSENGER p using(p_idx) 
join DRIVER d using(d_idx) 
where p.p_idx=11;
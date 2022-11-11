--drop table deliver_address;
--drop table memberTBL;
--drop table product_category;
--drop table productTbl;
--drop table managertbl;
--drop table user_board;
--drop table cartTBL;
--drop table orderTBL;
--drop table order_detail;
--drop table noticeTBL;

show tables;

select * from deliver_address;
select * from memberTBL;
select * from product_category;
select * from productTbl;
select * from managerTbl;
select * from user_board;
select * from cartTBL;
select * from orderTBL;
select * from order_detail;
select * from noticeTBL;


insert into deliver_address values(0, '123','123','123');
--insert into deliver_address values(1, '','','');
--delete from deliver_address where member_code = 1;
--update deliver_address set address1='대구광역시', address2='달서구',address3='상인동' where member_code=0;

--delete from memberTBL where member_code =1;
insert into memberTBL values ('000000', 'user123' ,'user123' , 'user','1993-05-03','010-1111-2222','metalwing53@gmail.com','M');

insert into product_category values('나이키','나이키');
insert into product_category values('아디다스','아디다스');
insert into product_category values('기타','기타');

insert into productTBL values 
('1001260','나이키','나이키신발1','260','50000','5','나이키슈즈1은, 조깅할때 발의 편안함과 가볍고 시원한 감을 주는 러닝화 입니다.','2022-10-26','11','nikeshoes1.jpg');
insert into productTBL values 
('1001270','나이키','나이키신발1','270','50000','5','나이키슈즈1은, 조깅할때 발의 편안함과 가볍고 시원한 감을 주는 러닝화 입니다.','2022-10-26','11','nikeshoes1.jpg');
insert into productTBL values 
('1001280','나이키','나이키신발1','280','50000','5','나이키슈즈1은, 조깅할때 발의 편안함과 가볍고 시원한 감을 주는 러닝화 입니다.','2022-10-26','11','nikeshoes1.jpg');

insert into productTBL values 
('1002260','나이키','나이키신발2','260','60000','6','나이키슈즈2은, 평소에 데일리룩과 매칭이 잘어울리는 나이키 단화 입니다.','2022-10-26','38','nikeshoes2.jpg');
insert into productTBL values 
('1002270','나이키','나이키신발2','270','60000','6','나이키슈즈2은, 평소에 데일리룩과 매칭이 잘어울리는 나이키 단화 입니다.','2022-10-26','38','nikeshoes2.jpg');
insert into productTBL values 
('1002280','나이키','나이키신발2','280','60000','6','나이키슈즈2은, 평소에 데일리룩과 매칭이 잘어울리는 나이키 단화 입니다.','2022-10-26','38','nikeshoes2.jpg');

insert into productTBL values 
('1003260','나이키','나이키신발3','260','70000','7','나이키슈즈3은, 특유의 물결라인과 잘 전체의 에어쿠셔닝으로 발의 편안함을 주는 운동화 입니다.','2022-10-26','45','nikeshoes3.jpg');
insert into productTBL values 
('1003270','나이키','나이키신발3','270','70000','7','나이키슈즈3은, 특유의 물결라인과 잘 전체의 에어쿠셔닝으로 발의 편안함을 주는 운동화 입니다.','2022-10-26','45','nikeshoes3.jpg');
insert into productTBL values 
('1003280','나이키','나이키신발3','280','70000','7','나이키슈즈3은, 특유의 물결라인과 잘 전체의 에어쿠셔닝으로 발의 편안함을 주는 운동화 입니다.','2022-10-26','45','nikeshoes3.jpg');

insert into productTBL values 
('1004260','나이키','나이키신발4','260','80000','8','나이키슈즈4은, 세가지 독특한 라인과 푹신한 에어쿠셔닝으로 스타일과 편안함을 한꺼번에 가질수 있는 신발 입니다.','2022-10-26','74','nikeshoes4.jpg');
insert into productTBL values 
('1004270','나이키','나이키신발4','270','80000','8','나이키슈즈4은, 세가지 독특한 라인과 푹신한 에어쿠셔닝으로 스타일과 편안함을 한꺼번에 가질수 있는 신발 입니다.','2022-10-26','74','nikeshoes4.jpg');
insert into productTBL values 
('1004280','나이키','나이키신발4','280','80000','8','나이키슈즈4은, 세가지 독특한 라인과 푹신한 에어쿠셔닝으로 스타일과 편안함을 한꺼번에 가질수 있는 신발 입니다.','2022-10-26','74','nikeshoes4.jpg');




insert into managerTBL values ('admin123', 'admin123' ,'metalwing53@gmail.com');

--insert into user_board;

--insert into cartTBL;

insert into orderTBL values(0,0, 100000 ,'2022-10-29',0);

insert into order_detail values(0,0,1001280,1,50000);
insert into order_detail values(1,0,1001270,1,50000);

insert into noticeTBL values(1, '123','123','2022-10-30');

/*******************************************************************************************************************************/
select  * from Order_detail natural join ordertbl natural join membertbl natural join deliver_address where order_id = 0;
select order_id, member_id,concat(address1, " ", address2, " ", address3) as address, member_phone, member_email, product_no, order_amount, order_price from Order_detail natural join ordertbl natural join membertbl natural join deliver_address  where order_id = 0;

select order_id, member_id,concat(address1, " ", address2, " ", address3) as address,  member_phone, member_email, product_no, order_amount, order_price from Order_detail natural join ordertbl natural join membertbl natural join deliver_address  where order_id = 0;


select member_code, member_id, member_name, member_phone from memberTBL order by 1 desc;

select member_code, member_id, member_pwd, member_name, member_birth, member_phone, member_email, member_gender, address1, address2, address3 from memberTBL natural join deliver_address;

select ifnull(max(notice_no),0)+1 as notice_no from NoticeTBL;


select * from producttbl;

select * from ordertbl natural join order_detail;
select order_id, product_no, order_amount, order_price, order_date from ordertbl natural join order_detail;

--ProductDAO / insertOrderProduct()
select * from orderTBL;
select * from order_detail;

--select ifnull(max(order_id),0)+1 from orderTBL;
--insert into orderTBL(order_id, member_code, order_date,order_status,totalmoney) values(1, 0,now(),'0',100000);
--select max(order_detail_id)+1 from order_detail;
--insert into order_detail(order_detail_id, order_id, product_no, order_amount, order_price) values (2,1,1001270,1,50000);
--insert into order_detail(order_detail_id, order_id, product_no, order_amount, order_price) values (3,1,1001280,1,50000);

--delete from orderTBL where order_id = 1;
--delete from order_detail where order_id = 1;


select count(*) from orderTBL;

select CEIL(count(*)/10),count(*)/10 , count(*)%10 from orderTBL;

select CEIL(1) from dual;

select CEIL(count(*)/10) from productTBL;

select post_no, post_subject ,member_id, post_date from memberTBL natural join user_board order by post_no desc limit  0 ,10;

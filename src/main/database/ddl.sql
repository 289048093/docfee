create table tb_product(id int identity,name char,price char,default_rate char);
create table tb_doctor(id int identity ,name char) ;
create table tb_doctor_bid_product(id int identity ,product_id int,doctor_id int,rate char);
create table tb_product(id int identity,name char,price double,default_rate double);
create table tb_doctor(id int identity ,name char,hospital char);
create table tb_doctor_bid_product(id int identity ,product_id int,doctor_id int,rate double,add_date date,price double,num int);
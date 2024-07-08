create database IphoneStore
use IphoneStore
create table AccountRole(
roleid int primary key not null ,
rolename nvarchar(100) not null
)
insert AccountRole values(1, 'Admin')
insert AccountRole values(2, 'User')
select * from AccountRole
-------------------------
create table Account(
id int primary key identity(1,1) not null,
username NVARCHAR(100) NOT NULL,
password NVARCHAR(100) NOT NULL, 
fullname nvarchar(max) not null,
[address] nvarchar(max) not null, 
dob date not null,
gender nvarchar(20) not null,
email nvarchar(100) not null,
phone nvarchar(20) not null,
roleid int,
foreign key (roleid) references AccountRole(roleid)
)
insert into Account values 
('minhs','minh2003',N'Bùi Văn Minh', N'Vĩnh Bảo,Hải Phòng', '2003-10-06', 'Male','bminhvan123@gmail.com','+84 348190828', 1)
select * from Account
------------------
CREATE TABLE Product (
    id int primary key IDENTITY(1,1) NOT NULL,
	name nvarchar(max) not null,
	image nvarchar(max) not null,
	price money not null,
	title nvarchar(max) not null,
	description nvarchar(max) not null,
	cateID int not null,
	foreign key (cateID) references Category(cateID)
)
select * from Product
insert Product values ('IPhone 15', 'img\iphone-15-pro-max_256gb.png', 1253, 'Pro Max VN/A', '256GB', 1)
insert Product values ('IPhone 15', 'img\iphone-15-plus_128gb.png', 941, 'Plus VN/A', '128GB', 1)
insert Product values ('IPhone 15', 'img\iphone-15-basic_128gb.png', 820, 'Standard VN/A', '128GB', 1)
insert Product values ('IPhone 15', 'img\iphone-15-pro_128gb.png', 1083, 'Pro VN/A', '128GB', 1)
insert Product values ('IPhone 15', 'img\iphone-15-pro-256gb.png', 1217, 'Pro VN/A', '256GB', 1)
insert Product values ('IPhone 15', 'img\iphone-15-pro-max_512gb.png', 1583, 'Pro Max VN/A', '512GB', 1)

insert Product values ('IPhone 14', 'img\iphone-14-pro-max-128gb.png', 1112, 'Pro Max VN/A', '128GB', 1)
insert Product values ('IPhone 14', 'img\iphone-14-pro-128gb.png', 1018, 'Pro VN/A', '128GB', 1)
insert Product values ('IPhone 14', 'img\iphone-14-basic-128gb.png', 702, 'VN/A', '128GB', 1)
insert Product values ('IPhone 14', 'img\iphone-14-plus-128gb.png', 807, 'Plus VN/A', '128GB', 1)
insert Product values ('IPhone 14', 'img\iphone-14-pro-max-256gb.png', 1177, 'Pro Max VN/A', '256GB', 1)
insert Product values ('IPhone 14', 'img\iphone-14-pro-256gb.png', 1096, 'Pro VN/A', '256GB', 1)

insert Product values ('IPhone 13', 'img\iphone-13-basic-128gb.png', 600, 'Standard VN/A', '128GB', 1)
insert Product values ('IPhone 13', 'img\iphone-13-basic-256gb.png', 811, 'Standard VN/A', '256GB', 1)
insert Product values ('IPhone 13', 'img\iphone-13-pro-max-128gb.png', 933, 'Pro Max VN/A', '128GB', 1)
insert Product values ('IPhone 13', 'img\iphone-13-pro-128gb.png', 892, 'Pro VN/A', '128GB', 1)

insert Product values ('IPhone 12', 'img\iphone-12-basic-64gb.png', 499, 'Standard VN/A', '64GB', 1)
insert Product values ('IPhone 12', 'img\iphone-12-basic-128gb.png', 555, 'Standard VN/A', '128GB', 1)
insert Product values ('IPhone 12', 'img\iphone-12-pro-max-128gb.png', 953, 'Pro Max VN/A', '128GB', 1)
insert Product values ('IPhone 12', 'img\iphone-12-mini-64gb.png', 527, 'Mini VN/A', '64GB', 1)
insert Product values ('IPhone 12', 'img\iphone-12-pro-128gb.png', 1014, 'Pro VN/A', '128GB', 1)

-----------------------
create table Category(
cateID int primary key identity not null,
cname nvarchar(max) not null
)
insert Category values('IPhone 15')
insert Category values('IPhone 14')
insert Category values('IPhone 13')
insert Category values('IPhone 12')
select * from Category
------------------------
create table Seller(
sell_ID int primary key not null,
id int not null,
foreign key (id) references Account(id),
foreign key (sell_ID) references Product(id) on delete cascade
)
insert Seller values(1,1)
insert Seller values(2,1)
insert Seller values(3,1)
insert Seller values(4,1)
insert Seller values(5,1)
insert Seller values(6,1)
insert Seller values(7,1)
insert Seller values(8,1)
insert Seller values(9,1)
insert Seller values(10,1)
insert Seller values(11,1)
insert Seller values(12,1)
insert Seller values(13,1)
insert Seller values(14,1)
insert Seller values(15,1)
insert Seller values(16,1)
insert Seller values(17,1)
insert Seller values(18,1)
insert Seller values(19,1)
insert Seller values(20,1)
insert Seller values(21,1)
select * from Seller
--------------
create table UserInfo(
id int primary key identity not null,
[name] nvarchar(max) not null,
[address] nvarchar(max) not null,
phone nvarchar(max) not null
)
drop table UserInfo
drop table OrderDetail
select * from UserInfo
select * from OrderDetail
create table OrderDetail(
id_order int primary key identity(1,1) not null,
id_user int not null,
id_product int not null, 
total money not null,
[status] bit not null,
foreign key (id_user) references UserInfo(id) on delete cascade,
foreign key (id_product) references Product(id) on delete cascade,
)
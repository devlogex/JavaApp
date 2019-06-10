create schema db;
use db;

CREATE TABLE GIANGVIEN
(
	MaGV varchar(100) PRIMARY KEY,
	TenGV NVARCHAR(100) NOT NULL,
    TongLop int not null default 0,
    TongTiet int not null default 0
);

CREATE TABLE LOPHOC
(
	MaLop INT auto_increment  PRIMARY KEY,
    TenMon NVARCHAR(100),
    Siso int not null,
	BatDau date,
	KetThuc date,
	SoTiet INT NOT NULL,
	MaGV varchar(100) NOT NULL
);

alter table LOPHOC 
add constraint LOPHOC_GIANGVIEN_FK 
foreign key(MaGV) references GIANGVIEN(MaGV);

DELIMITER $$
CREATE PROCEDURE USP_AddGV(id VARCHAR(100),name nvarchar(100))
BEGIN
	insert GIANGVIEN(MaGV,TenGV) values(id,name);
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetGV()
BEGIN
	select * from GIANGVIEN;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetGVByID(id varchar(100))
BEGIN
	select * from GIANGVIEN where MaGV=id;
END; $$
DELIMITER ;
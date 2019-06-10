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
	MaLop varchar(100)  PRIMARY KEY,
    TenMon NVARCHAR(100),
    Siso int ,
	BatDau date,
	KetThuc date,
	SoTiet int,
	MaGV varchar(100)
);


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

DELIMITER $$
CREATE PROCEDURE USP_AddLop(id VARCHAR(100),name nvarchar(100),siso int,start date,end date,sotiet int,gvID varchar(100))
BEGIN
	if(gvID!="none")
    then
		insert LOPHOC values(
			id,name,siso,start,end,sotiet,gvID
		);
	else
        insert LOPHOC values(
			id,name,siso,start,end,sotiet,null
		);
	end if;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_GetLop()
BEGIN
	select * from LOPHOC;
END; $$
DELIMITER ;
CALL USP_GetLop();
select * from GIANGVIEN
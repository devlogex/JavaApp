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
CREATE PROCEDURE USP_GetGVByID(old_MaGV varchar(100))
BEGIN
	update GIANGVIEN
		set TongLop=(select count(*) from LOPHOC h where h.MaGV=old_MaGV),
			TongTiet=(select sum(SoTiet) from LOPHOC h where h.MaGV=old_MaGV)
		where MaGV=old_MaGV;
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
        update GIANGVIEN set TongLop=TongLop+1, TongTiet=TongTiet+sotiet where MaGV=gvID;
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

DELIMITER $$
CREATE PROCEDURE USP_GetLopByID(id varchar(100))
BEGIN
	select * from LOPHOC where MaLop=id;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_UpdateGVLop(gvID varchar(100))
BEGIN
	update GIANGVIEN
		set TongLop=(select count(*) from LOPHOC where MaGV=gvID),
			TongTiet=(select sum(SoTiet) from LOPHOC where MaGV=gvID)
        where MaGV=gvID;
END; $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE USP_UpdateLop(id VARCHAR(100),name nvarchar(100),siso int,start date,end date,sotiet int,gvID varchar(100))
BEGIN    
	if(gvID="none")
    then
	update LOPHOC 
		set TenMon=name, Siso=siso, BatDau=start, KetThuc=end, SoTiet=sotiet, MaGV=null
        where MaLop=id;
	else
    update LOPHOC 
		set TenMon=name, Siso=siso, BatDau=start, KetThuc=end, SoTiet=sotiet, MaGV=gvID
        where MaLop=id;
	end if;
        
	update GIANGVIEN
		set TongLop=(select count(*) from LOPHOC where MaGV=gvID),
			TongTiet=(select sum(SoTiet) from LOPHOC where MaGV=gvID)
        where MaGV=gvID;
END; $$
DELIMITER ;

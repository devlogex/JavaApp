/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tnd4698
 */
public class LopHoc {
    public String id;
    public String name;
    public int siso;
    public Date start;
    public Date end;
    public int sotiet;
    public String gvID;
    
    public LopHoc(){}
    public LopHoc(String id, String name,int siso,Date start,Date end,int sotiet,String gvID){
        this.id=id;
        this.name=name;
        this.siso=siso;
        this.start=start;
        this.end=end;
        this.sotiet=sotiet;
        this.gvID=gvID;
    }

    public boolean AddLop(String id, String name, int siso, Date start, Date end, int sotiet, String gvID) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd");
        String SQL="call USP_AddLop(\""+id+"\",\""+name+"\",\""+siso+"\",\""+df.format(start)+"\",\""+df.format(end)+"\",\""+sotiet+"\",\""+gvID+"\")";
        try{
            DataAccessHelper.getInstance().getConnect();
            Statement statement =DataAccessHelper.getInstance().conn.createStatement();
            int rs=statement.executeUpdate(SQL);
            if(rs>0)
            {
                DataAccessHelper.getInstance().getClose();
                return true;
            }
            else
            {
                DataAccessHelper.getInstance().getClose();
                return false;
            }
        } catch (Exception e) {return false;}
    }

    public ArrayList<LopHoc> getLop() {
        String SQL="call USP_GetLop()";
        ArrayList<LopHoc> list=new ArrayList<LopHoc>();
        try{
            DataAccessHelper.getInstance().getConnect();
            Statement statement =DataAccessHelper.getInstance().conn.createStatement();
            ResultSet rs=statement.executeQuery(SQL);
            while(rs.next())
            {System.out.println(rs.getString("MaGV"));
                list.add(new LopHoc(
                        rs.getString("MaLop"),
                        rs.getString("TenMon"),
                        Integer.parseInt(rs.getString("Siso")),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse(rs.getString("BatDau")),
                        (new SimpleDateFormat("yyyy-MM-dd")).parse(rs.getString("KetThuc")),
                        Integer.parseInt(rs.getString("SoTiet")),
                        rs.getString("MaGV")
                ));
            }
            DataAccessHelper.getInstance().getClose();
        } catch (Exception e) {}
        return list;
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author tnd4698
 */
public class GiangVien {
    public String id;
    public String name;
    public int sumLop;
    public int sumTiet;
    
    public GiangVien(){}
    public GiangVien(String id,String name,int sumLop,int sumTiet){
        this.id=id;
        this.name=name;
        this.sumLop=sumLop;
        this.sumTiet=sumTiet;
    }

    public boolean addGV(String id, String name) {
        String SQL="call USP_AddGV(\""+id+"\",\""+name+"\")";
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

    public ArrayList<GiangVien> getGV() {
        String SQL="call USP_GetGV()";
        ArrayList<GiangVien> list=new ArrayList<GiangVien>();
        try{
            DataAccessHelper.getInstance().getConnect();
            Statement statement =DataAccessHelper.getInstance().conn.createStatement();
            ResultSet rs=statement.executeQuery(SQL);
            while(rs.next())
            {
                list.add(new GiangVien(
                        rs.getString("MaGV"),
                        rs.getString("TenGV"),
                        Integer.parseInt(rs.getString("TongLop")),
                        Integer.parseInt(rs.getString("TongTiet"))
                ));
            }
            DataAccessHelper.getInstance().getClose();
        } catch (Exception e) {}
        return list;
    }
    
    public GiangVien getGVByID(String id){
        String SQL="call USP_GetGVByID(\""+id+"\")";
        GiangVien list=null;
        try{
            DataAccessHelper.getInstance().getConnect();
            Statement statement =DataAccessHelper.getInstance().conn.createStatement();
            ResultSet rs=statement.executeQuery(SQL);
            while(rs.next())
            {
                list=new GiangVien(
                        rs.getString("MaGV"),
                        rs.getString("TenGV"),
                        Integer.parseInt(rs.getString("TongLop")),
                        Integer.parseInt(rs.getString("TongTiet"))
                );
            }
            DataAccessHelper.getInstance().getClose();
        } catch (Exception e) {}
        return list;
    }
}

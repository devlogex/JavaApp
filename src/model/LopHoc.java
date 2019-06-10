/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    
    
}

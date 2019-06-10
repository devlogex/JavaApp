/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    
}

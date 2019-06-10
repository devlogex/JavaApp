/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.GiangVien;

/**
 *
 * @author tnd4698
 */
public class GiangVienController {
    GiangVien Model=new GiangVien();

    public boolean addGV(String id, String name) {
        return Model.addGV(id,name);
    }

    public void loadGV(JTable table) {
        String[] head=new String[]{"STT","Mã giảng viên","Tên giảng viên","Tổng số lớp","Tổng số tiết","Đạt chuẩn"};
        ArrayList<GiangVien> list= Model.getGV();
        Object[][] body=new Object[list.size()][6];
        for(int i=0;i<list.size();i++)
        {
            body[i][0]=i+1;
            body[i][1]=list.get(i).id;
            body[i][2]=list.get(i).name;
            body[i][3]=list.get(i).sumLop;
            body[i][4]=list.get(i).sumTiet;
            body[i][5]=list.get(i).sumTiet>=135 ? "Có":"Không";
        }
        DefaultTableModel dtm = new DefaultTableModel(body,head){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setModel(dtm);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
    }
}

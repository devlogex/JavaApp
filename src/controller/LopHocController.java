/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.GiangVien;
import model.LopHoc;

/**
 *
 * @author tnd4698
 */
public class LopHocController {
    LopHoc Model =new LopHoc();

    public void loadGV(JComboBox<String> cbGV) {
        cbGV.removeAllItems();
        
        cbGV.addItem("none");
        ArrayList<GiangVien>list= (new GiangVien()).getGV();
        for(int i=0;i<list.size();i++)
        {
            cbGV.addItem(list.get(i).name+"-"+list.get(i).id);
        }
        cbGV.addItem("...");
    }

    public boolean AddLop(String id, String name, String siso, String start, String end, String sotiet, String gvID) throws ParseException {
        return Model.AddLop(
                id,
                name,
                Integer.parseInt(siso),
                (new SimpleDateFormat("dd/MM/yyyy")).parse(start),
                (new SimpleDateFormat("dd/MM/yyyy")).parse(end),
                Integer.parseInt(sotiet),
                gvID
        );
    }

    public void loadLop(JTable table) {
        String[] head=new String[]{"STT","Tên giảng viên","Mã lớp","Tên môn","Sĩ số","Bắt đầu","Kết thúc","Số tiết"};
        ArrayList<LopHoc> list= Model.getLop();
        Object[][] body=new Object[list.size()][8];
        for(int i=0;i<list.size();i++)
        {
            body[i][0]=i+1;
            body[i][1]=(new GiangVien()).getGVByID(list.get(i).gvID)!=null?(new GiangVien()).getGVByID(list.get(i).gvID).name:"";
            body[i][2]=list.get(i).id;
            body[i][3]=list.get(i).name;
            body[i][4]=list.get(i).siso;
            body[i][5]=(new SimpleDateFormat("dd/MM/yyyy")).format(list.get(i).start);
            body[i][6]=(new SimpleDateFormat("dd/MM/yyyy")).format(list.get(i).end);
            body[i][7]=list.get(i).sotiet;
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
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
        table.getColumnModel().getColumn(7).setPreferredWidth(200);
    }

}

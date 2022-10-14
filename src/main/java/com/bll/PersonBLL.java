package com.bll;

import com.dto.DTO;
import com.dto.PersonDTO;
import com.dal.CoreDAL;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Vector;

public class PersonBLL extends BLL{
    public PersonBLL() {
        super(new CoreDAL("Person",new PersonDTO()));
    }
    public DefaultTableModel toTable(HashMap<String, DTO> list) {
        Vector<String> header=new Vector<>();
        header.add("ID");
        header.add("First name");
        header.add("Last name");
        header.add("Hire date");
        return toTable(list, header);
    }

    public DefaultTableModel toTable(HashMap<String, DTO> list, Vector<String> header) {
        DefaultTableModel rs=new DefaultTableModel(new Vector<>(), header);
        for (var id : list.keySet()) {
            Vector<String> row = getList().get(id).toTable();
            row.add(0, id + "");
            rs.addRow(row);
            rs.setValueAt(Integer.parseInt(id),rs.getRowCount()-1,0);
        }
        return rs;
    }
}

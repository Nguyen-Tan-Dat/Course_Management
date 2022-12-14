package com.gui;

import com.gui.components.MyColor;
import com.gui.components.MyTable;
import com.gui.components.SearchBar;
import com.bll.BLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class SeePanel extends UIPanel {
    protected final JTable table;
    protected final BLL control;
    protected final SearchBar searchBar;
    protected JPanel top;

    public JTable getTable() {
        return table;
    }
    public SeePanel(BLL control) {
        this.control=control;
        var actionBackground = MyColor.primary;
        setLayout(new BorderLayout(0, 0));
        top = new JPanel(new BorderLayout());
        top.setBackground(actionBackground);
        JScrollPane spTable = new JScrollPane();
        table = new MyTable(this.control.toTable());
        spTable.setViewportView(table);
        add(spTable, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);
        Vector<String> field=new Vector<>();
        field.add("All");
        for (int i=0;i<table.getModel().getColumnCount();i++){
            field.add(table.getColumnName(i));
        }
        searchBar = new SearchBar(top.getBackground());
        searchBar.getComboBox().setModel(new DefaultComboBoxModel<>(field));
        searchBar.getButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                search();
            }
        });
        searchBar.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    search();
                }
            }
        });
        top.add(searchBar, BorderLayout.EAST);
    }
    protected void search(){
        int sl = searchBar.getComboBox().getSelectedIndex();
        table.setModel(control.toTable());
        String input=searchBar.getTextField().getText().toLowerCase();
        if (sl > 0) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int modelLengh = model.getRowCount();
            int count = 0;
            for (int i = 0; i < modelLengh; i++) {
                String info = model.getValueAt(i-count, sl -1 ).toString().toLowerCase();
                if (!info.contains(input)){
                    model.removeRow(i-count);
                    count++;
                }
                else System.out.println(i);
            }
        } else {
            table.setModel(control.toTable(control.searchAll(searchBar.getTextField().getText())));
        }
    }
    @Override
    public UIPanel newView() {
        return new SeePanel(control);
    }
}

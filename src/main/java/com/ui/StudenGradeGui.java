/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ui;

import com.bll.CourseBL;
import com.bll.PersonBL;
import com.bll.StudentGradeBL;
import com.dto.Course;
import com.dto.Person;
import com.dto.StudentGrade;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NguyenVuong
 */
public class StudenGradeGui extends UIPanel {
    CourseBL course;
    PersonBL person;
    StudentGradeBL stdGrade;
    DefaultTableModel tablemodelCrouse;
    DefaultTableModel tablemodelPerson;

    public StudenGradeGui() {
        initComponents();
        course = new CourseBL();
        person = new PersonBL();
        stdGrade = new StudentGradeBL();
        tablemodelCrouse = (DefaultTableModel) jTable_Course.getModel();
        tablemodelPerson = (DefaultTableModel) jTable_Preson.getModel();
        try {
            load();
        } catch (SQLException ex) {
            Logger.getLogger(StudenGradeGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadCourse() throws SQLException {
        ArrayList<Course> listCourse = new ArrayList<Course>();
        listCourse = course.getCourse();
        tablemodelCrouse.setRowCount(0);
        for (Course sp : listCourse) {
            Vector vctData = new Vector();
            vctData.add(sp.getCourseID());
            vctData.add(sp.getTitle());
            vctData.add(course.checkCourse(sp.getCourseID()));

            tablemodelCrouse.addRow(vctData);
        }
    }

    public void loadCourse(int id) throws SQLException {
        ArrayList<Course> listCourse = new ArrayList<Course>();
        listCourse = course.getCourse(id);
        tablemodelCrouse.setRowCount(0);
        for (Course sp : listCourse) {
            Vector vctData = new Vector();
            vctData.add(sp.getCourseID());
            vctData.add(sp.getTitle());
            vctData.add(course.checkCourse(sp.getCourseID()));
            tablemodelCrouse.addRow(vctData);
        }
    }

    public void loadCourse(String s) throws SQLException {
        ArrayList<Course> listCourse = new ArrayList<Course>();
        listCourse = course.getCourse(s);
        tablemodelCrouse.setRowCount(0);
        for (Course sp : listCourse) {
            Vector vctData = new Vector();
            vctData.add(sp.getCourseID());
            vctData.add(sp.getTitle());
            vctData.add(course.checkCourse(sp.getCourseID()));
            tablemodelCrouse.addRow(vctData);
        }
    }

    public void loadPerson(String s) throws SQLException {
        ArrayList<Person> listper = new ArrayList<Person>();
        listper = person.getlistPersonByName(s);
        ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
        tablemodelPerson.setRowCount(0);
        for (Person rs : listper) {

            list = stdGrade.getlistStudentGradeByID(rs.getPersonID());
            if (list.size() >= 0) {
                for (StudentGrade rs1 : list) {
                    Vector vctData1 = new Vector();
                    vctData1.add(rs1.getEnrollmentID());
                    vctData1.add(rs.getPersonID());
                    vctData1.add(rs.getFirstname() + " " + rs.getLastname());
                    vctData1.add(rs1.getCourseID());
                    vctData1.add(rs1.getGrade());
                    tablemodelPerson.addRow(vctData1);

                }
            } else {
                Vector vctData = new Vector();
                vctData.add("null");
                vctData.add(rs.getPersonID());
                vctData.add(rs.getFirstname() + " " + rs.getLastname());
                vctData.add("null");
                vctData.add("null");
                tablemodelPerson.addRow(vctData);

            }

        }

    }

    public void loadPerson() throws SQLException {
        ArrayList<Person> list = new ArrayList<Person>();
        list = person.getlistPerson();
        tablemodelPerson.setRowCount(0);
        for (Person rs : list) {
            Vector vctData = new Vector();
            vctData.add(rs.getPersonID());
            vctData.add(rs.getFirstname() + " " + rs.getLastname());
            vctData.add("Online");
            vctData.add("Pyshical");
            vctData.add("10.0");

            tablemodelPerson.addRow(vctData);
        }
    }

    public void loadPerson(int id) throws SQLException {
        ArrayList<Person> list = new ArrayList<Person>();
        list = person.getlistPerson(id);
        tablemodelPerson.setRowCount(0);
        for (Person rs : list) {
            Vector vctData = new Vector();
            vctData.add(rs.getPersonID());
            vctData.add(rs.getFirstname() + " " + rs.getLastname());
            vctData.add("Online");
            vctData.add("Pyshical");
            vctData.add("10.0");

            tablemodelPerson.addRow(vctData);
        }
    }

    public void loadStudentGrade(int id) throws SQLException {
        ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
        list = stdGrade.getlistStudentGrade(id);
        ArrayList<Person> listper = new ArrayList<Person>();
        listper = person.getlistPerson();
        tablemodelPerson.setRowCount(0);
        for (StudentGrade rs : list) {
            Vector vctData = new Vector();
            for (Person rs1 : listper) {
                if (rs.getStudentID() == rs1.getPersonID()) {
                    vctData.add(rs.getEnrollmentID());
                    vctData.add(rs1.getPersonID());
                    vctData.add(rs1.getFirstname() + " " + rs1.getLastname());
                    vctData.add(rs.getCourseID());
                    vctData.add(rs.getGrade());
                }
            }
            tablemodelPerson.addRow(vctData);

        }
    }

    public void loadStudentGradeByID(int id) throws SQLException {
        ArrayList<StudentGrade> list = new ArrayList<StudentGrade>();
        list = stdGrade.getlistStudentGradeByID(id);
        ArrayList<Person> listper = new ArrayList<Person>();
        listper = person.getlistPerson();
        tablemodelPerson.setRowCount(0);
        if (list.size() > 0) {
            for (StudentGrade rs : list) {
                Vector vctData = new Vector();
                for (Person rs1 : listper) {
                    if (rs.getStudentID() == rs1.getPersonID()) {
                        vctData.add(rs.getEnrollmentID());
                        vctData.add(rs1.getPersonID());
                        vctData.add(rs1.getFirstname() + " " + rs1.getLastname());
                        vctData.add(rs.getCourseID());
                        vctData.add(rs.getGrade());
                    }
                }
                tablemodelPerson.addRow(vctData);

            }
        } else {
            for (Person rs1 : listper) {
                Vector vctData = new Vector();

                if (id == rs1.getPersonID()) {
                    vctData.add(rs1.getPersonID());
                    vctData.add(rs1.getFirstname() + " " + rs1.getLastname());
                    vctData.add("");
                    vctData.add("");
                    tablemodelPerson.addRow(vctData);

                }
            }
        }

    }

    public void loadPersonbyID(int id) throws SQLException {
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(person.getPersonbyID(id));

        tablemodelPerson.setRowCount(0);
        for (Person rs : list) {
            Vector vctData = new Vector();
            vctData.add(rs.getPersonID());
            vctData.add(rs.getFirstname() + " " + rs.getLastname());
            vctData.add("Online");
            vctData.add("Pyshical");
            vctData.add("10.0");

            tablemodelPerson.addRow(vctData);
        }
    }

    public void load() throws SQLException {
        loadCourse();
        //loadPerson();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()  {
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_idPreson = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_Grade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_idCourse = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_NameCourse = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_delete = new javax.swing.JButton();
        btn_add1 = new javax.swing.JButton();
        btn_search = new javax.swing.JButton();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        btn_reset = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Preson = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Course = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setText("M? sinh vi�n");

        txt_idPreson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        jLabel2.setText("T�n sinh vi�n");

        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        jLabel3.setText("�i?m");

        txt_Grade.setEnabled(false);
        txt_Grade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        jLabel4.setText("M? kh�a h?c");

        txt_idCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        jLabel5.setText("T�n kh�a h?c");

        txt_NameCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        jLabel6.setText("Tr?ng th�i");

        btn_delete.setText("X�a");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        btn_add1.setText("Th�m");
        btn_add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        btn_search.setText("T?m ki?m");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Online", "OnSite" }));
        jComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        btn_reset.setText("reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        btn_edit.setText("S?a");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txt_idPreson, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txt_Grade, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txt_idCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txt_NameCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(btn_add1)
                                                .addGap(20, 20, 20)
                                                .addComponent(btn_edit)
                                                .addGap(20, 20, 20)
                                                .addComponent(btn_delete)
                                                .addGap(20, 20, 20)
                                                .addComponent(btn_search)
                                                .addGap(20, 20, 20)
                                                .addComponent(btn_reset)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txt_idPreson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(txt_idCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_NameCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel2)
                                                .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txt_Grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)
                                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_add1)
                                        .addComponent(btn_delete)
                                        .addComponent(btn_search)
                                        .addComponent(btn_reset)
                                        .addComponent(btn_edit))
                                .addGap(15, 15, 15))
        );

        jTable_Preson.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "ID", "M? sinh vi�n", "H? t�n ", "M? kh�a h?c", "�i?m"
                }
        ));
        jTable_Preson.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

            }
        });
        jScrollPane1.setViewportView(jTable_Preson);

        jTable_Course.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "M? kh�a h?c", "T�n kh�a h?c", "Tr?ng th�i"
                }
        ));
        jTable_Course.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

            }
        });
        setLayout(new BorderLayout(0, 0));
        jScrollPane2.setViewportView(jTable_Course);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Danh s�ch sinh vi�n");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Danh s�ch kh�a h?c");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Th�ng tin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(9)
                                                                .addComponent(jLabel9)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel8)
                                                                .addGap(97))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(24))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(381))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(550, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(20)
                                                .addComponent(jLabel9)
                                                .addGap(0)
                                                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel7))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
                                .addGap(11))
        );
        jPanel1.setLayout(jPanel1Layout);
        add(jPanel1,BorderLayout.NORTH);
        add(jScrollPane1, BorderLayout.CENTER);
    }
// </editor-fold>//GEN-END:initComponents

    private void txt_idPresonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idPresonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idPresonActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void txt_GradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GradeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GradeActionPerformed

    private void txt_idCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idCourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idCourseActionPerformed

    private void txt_NameCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NameCourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameCourseActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed

        String idCourse = txt_idCourse.getText();
        String idPreson = txt_idPreson.getText();
        String grade = txt_Grade.getText();
        if (idCourse.trim().equals("") || idPreson.trim().equals("") || grade.trim().equals("")) {
            JOptionPane.showMessageDialog(getRootPane(), "C�c tr�?ng kh�ng ��?c b? tr?ng");
        } else {
            StudentGrade std = new StudentGrade(Integer.parseInt(idCourse), Integer.parseInt(idPreson), Float.parseFloat(grade));
            try {
                boolean check = stdGrade.delete(std);
                if (check) {
                    JOptionPane.showMessageDialog(getRootPane(), "X�a th�nh c�ng");
                    loadStudentGrade(Integer.parseInt(idCourse));
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudenGradeGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add1ActionPerformed

        String idCourse = txt_idCourse.getText();
        String idPreson = txt_idPreson.getText();
        String grade = txt_Grade.getText();
        if (idCourse.trim().equals("") || idPreson.trim().equals("") || grade.trim().equals("")) {
            JOptionPane.showMessageDialog(getRootPane(), "C�c tr�?ng kh�ng ��?c b? tr?ng");
        } else {
            StudentGrade std = new StudentGrade(Integer.parseInt(idCourse), Integer.parseInt(idPreson), Float.parseFloat(grade));
            try {
                boolean check = stdGrade.insert(std);
                if (check) {
                    JOptionPane.showMessageDialog(getRootPane(), "Th�m th�nh c�ng");
                    loadStudentGrade(Integer.parseInt(idCourse));
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudenGradeGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btn_add1ActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed

        if (!txt_idCourse.getText().trim().equals("")) {
            try {
                loadCourse(Integer.parseInt(txt_idCourse.getText()));
                loadStudentGrade(Integer.parseInt(txt_idCourse.getText()));

            } catch (SQLException ex) {
                Logger.getLogger(StudenGradeGui.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!txt_NameCourse.getText().trim().equals("")) {
                try {
                    loadCourse(txt_NameCourse.getText());

                } catch (SQLException ex) {
                    Logger.getLogger(StudenGradeGui.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (!txt_idPreson.getText().trim().equals("")) {
                try {
                    loadStudentGradeByID(Integer.parseInt(txt_idPreson.getText()));

                } catch (SQLException ex) {
                    Logger.getLogger(StudenGradeGui.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!txt_name.getText().trim().equals("")) {
                try {
                    loadPerson(txt_name.getText());

                } catch (SQLException ex) {
                    Logger.getLogger(StudenGradeGui.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


    }//GEN-LAST:event_btn_searchActionPerformed

    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxStatusActionPerformed

    private void jTable_CourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CourseMouseClicked
        int i = jTable_Course.getSelectedRow();
        txt_idCourse.setText(String.valueOf(jTable_Course.getModel().getValueAt(i, 0)));
        txt_NameCourse.setText(String.valueOf(jTable_Course.getModel().getValueAt(i, 1)));
        jComboBoxStatus.setSelectedItem(String.valueOf(jTable_Course.getModel().getValueAt(i, 2)));
        txt_idCourse.setEnabled(false);
        txt_NameCourse.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_CourseMouseClicked

    private void jTable_PresonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PresonMouseClicked
        int i = jTable_Preson.getSelectedRow();
        txt_idPreson.setText(String.valueOf(jTable_Preson.getModel().getValueAt(i, 1)));
        txt_name.setText(String.valueOf(jTable_Preson.getModel().getValueAt(i, 2)));
        if (stdGrade.getListStudentGrade().size() > 0) {
            txt_Grade.setText(String.valueOf(jTable_Preson.getModel().getValueAt(i, 4)));
        } else {
            txt_Grade.setText("");
        }
        txt_name.setEnabled(false);
        txt_idPreson.setEnabled(false);
        txt_Grade.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_PresonMouseClicked

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        jComboBoxStatus.setSelectedItem("All");
        txt_idCourse.setText("");
        txt_NameCourse.setText("");
        txt_idCourse.setText("");
        txt_idPreson.setText("");
        txt_name.setText("");
        txt_Grade.setText("");
        txt_idPreson.setEnabled(true);
        txt_Grade.setEnabled(false);
        txt_idCourse.setEnabled(true);
        txt_NameCourse.setEnabled(true);
        txt_name.setEnabled(true);
        try {
            loadCourse();
            // TODO add your handling code here:

        } catch (SQLException ex) {
            Logger.getLogger(StudenGradeGui.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed

        String idCourse = txt_idCourse.getText();
        String idPreson = txt_idPreson.getText();
        String grade = txt_Grade.getText();
        if (idCourse.trim().equals("") || idPreson.trim().equals("") || grade.trim().equals("")) {
            JOptionPane.showMessageDialog(getRootPane(), "C�c tr�?ng kh�ng ��?c b? tr?ng");
        } else {
            StudentGrade std = new StudentGrade(Integer.parseInt(idCourse), Integer.parseInt(idPreson), Float.parseFloat(grade));
            try {
                boolean check = stdGrade.update(std);
                if (check) {
                    JOptionPane.showMessageDialog(getRootPane(), "S?a th�nh c�ng");
                    loadStudentGrade(Integer.parseInt(idCourse));
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudenGradeGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btn_editActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudenGradeGui.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudenGradeGui.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudenGradeGui.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudenGradeGui.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudenGradeGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add1;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_search;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Course;
    private javax.swing.JTable jTable_Preson;
    private javax.swing.JTextField txt_Grade;
    private javax.swing.JTextField txt_NameCourse;
    private javax.swing.JTextField txt_idCourse;
    private javax.swing.JTextField txt_idPreson;
    private javax.swing.JTextField txt_name;
    // End of variables declaration//GEN-END:variables
}

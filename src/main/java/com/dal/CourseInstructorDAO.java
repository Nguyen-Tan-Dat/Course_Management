
package com.dal;

import com.dto.CourseInstructorDTO;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CourseInstructorDAO {
    MySQLConnect conn = new MySQLConnect("localhost", "root", "", "school");
    public CourseInstructorDAO(){
        try{
            conn.testDriver();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database");
        }
    }
    
    public ArrayList<CourseInstructorDTO> docDSCI(){
        ArrayList<CourseInstructorDTO> dsci = new ArrayList<>();
        try{
            String sql = "select * from courseinstructor";
            ResultSet rs = conn.executeQuery(sql);
            
            while(rs.next()){
                CourseInstructorDTO ci = new CourseInstructorDTO();
                ci.setCourseID(rs.getInt(1));
                ci.setPersonID(rs.getInt(2));
                dsci.add(ci);
            }
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Không thể đọc dữ liệu từ database");
        }
        return dsci;
    }
    public void them(CourseInstructorDTO ci) {
        String sql = String.format("insert into courseinstructor values (%d, %d)", 
                ci.getCourseID(), ci.getPersonID());
        try{
            conn.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không thể thêm dữ liệu");
        }
    }
    
    public void sua(CourseInstructorDTO ci){
        String sql = String.format("update courseinstructor set PersonID = %d where CourseID = %d", 
                ci.getPersonID(), ci.getCourseID());
        try{
            conn.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Không thể sửa dữ liệu");
        }
    }
    public void xoa(CourseInstructorDTO ci) {
       String sql = String.format("delete from courseinstructor where CourseID = %d ", ci.getCourseID());
       try{
            conn.executeUpdate(sql);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Không thể xóa dữ liệu");
        }
    }
}

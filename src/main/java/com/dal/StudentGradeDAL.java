package com.dal;

import com.dto.StudentGrade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentGradeDAL extends DAL {
	public StudentGradeDAL() {
		super("StudentGrade");
	}
            ResultSet rs = null;
    // ===========================
    ArrayList<StudentGrade> listStudentGrade = new ArrayList<StudentGrade>();
    public ArrayList<StudentGrade> getlistStudent() throws SQLException {
        listStudentGrade.clear();
        rs = sql.select("studentgrade");
        while (rs.next()) {
            listStudentGrade.add(new StudentGrade(rs.getInt("EnrollmentID"),rs.getInt("CourseID"),rs.getInt("StudentID"),rs.getFloat("Grade")));
        }
        return listStudentGrade;
    }
    
    public ArrayList<StudentGrade> getStudentByCourse(int id) throws SQLException {
        listStudentGrade.clear();
        String s = "`CourseID` = '" + id +"'";
        rs = sql.select("studentgrade",s);
        while (rs.next()) {
            listStudentGrade.add(new StudentGrade(rs.getInt("EnrollmentID"),rs.getInt("CourseID"),rs.getInt("StudentID"),rs.getFloat("Grade")));
        }
        return listStudentGrade;
    }
    public ArrayList<StudentGrade> getStudentByID(int id) throws SQLException {
        listStudentGrade.clear();
        String s = "`StudentID` = '" + id +"'";
        rs = sql.select("studentgrade",s);
        while (rs.next()) {
            listStudentGrade.add(new StudentGrade(rs.getInt("EnrollmentID"),rs.getInt("CourseID"),rs.getInt("StudentID"),rs.getFloat("Grade")));
        }
        return listStudentGrade;
    }
    public boolean insert(StudentGrade std) throws SQLException{
        HashMap<String,Object> insertData = new HashMap<String,Object>();
        insertData.put("CourseID", std.getCourseID());
        insertData.put("StudentID", std.getStudentID());
        insertData.put("Grade", std.getGrade());
        sql.insert("studentgrade", insertData); 
        return true;
    }
    public boolean delete(StudentGrade std) throws SQLException{
        return sql.delete("studentgrade","CourseID = " + std.getCourseID() + " AND StudentID = " + std.getStudentID());
    }
    public boolean update(StudentGrade std) throws SQLException{
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("Grade", std.getGrade());
        return sql.update("studentgrade", data,"CourseID = " + std.getCourseID() + " AND StudentID = " + std.getStudentID());
    }
}

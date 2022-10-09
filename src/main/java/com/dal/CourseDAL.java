package com.dal;

import com.dal.ConnectSQL;
import com.dal.ConnectUnit;
import com.dto.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAL extends DAL {

    public CourseDAL() {
        super("Course");
    }
    ResultSet rs = null;
    // ===========================
    ArrayList<Course> listCourse = new ArrayList<Course>();

    public ArrayList<Course> getCourse() throws SQLException {
        listCourse.clear();
        rs = sql.select("course");
        while (rs.next()) {
            listCourse.add(new Course(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), rs.getInt("DepartmentID")));
        }
        return listCourse;
    }
    public ArrayList<Course> getCourse(int id) throws SQLException {
        listCourse.clear();
        String s = "`CourseID` = '" + id +"'";
        rs = sql.select("course",s);
        while (rs.next()) {
            listCourse.add(new Course(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), rs.getInt("DepartmentID")));
        }
        return listCourse;
    }
    public String checkCourse(int id) throws SQLException{
        Boolean checkONl = false;
        Boolean checkOnSite = false;
        if(checkCourseOnl(id) != ""){
            checkONl = true;
        }
        if(checkCourseOnSite(id) != ""){
            checkOnSite = true;
        }
        if(checkONl && checkOnSite){
            return "All";
        }
        if(checkONl){
            return "Online";
        }
        return "OnSite";

    }
    public String checkCourseOnl(int id) throws SQLException{
        String s = "`CourseID` = '" + id +"'";
             rs = sql.select("onlinecourse",s);
             if(rs.next()){
                 return "Online";
             }
             return "";
    }
    public String checkCourseOnSite(int id) throws SQLException{
        String s = "`CourseID` = '" + id +"'";
             rs = sql.select("onsitecourse",s);
             if(rs.next()){
                 return "OnSite";
             }
             return "";
    }
}

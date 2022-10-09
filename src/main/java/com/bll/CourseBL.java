package com.bll;

import com.dal.CourseDAL;
import com.dto.Course;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseBL extends BLL {

    public CourseBL() {
        super(new CourseDAL());
    }
    //====================================
    ArrayList<Course> listCourse = new ArrayList<Course>();
    CourseDAL courseDal = new CourseDAL();

    public ArrayList<Course> getCourse() throws SQLException {
        listCourse = courseDal.getCourse();
        return listCourse;
    }

    public ArrayList<Course> getCourse(int id) throws SQLException {
        listCourse = courseDal.getCourse(id);
        return listCourse;
    }

    public ArrayList<Course> getCourse(String s) throws SQLException {
        
        ArrayList<Course> ds = new ArrayList<Course>();
        for (Course rs : listCourse) {
            if (rs.getTitle().indexOf(String.valueOf(s)) >= 0) {
                ds.add(rs);
            }
        }
        return ds;
    }

    public String checkCourse(int id) throws SQLException {
        return courseDal.checkCourse(id);
    }

    public ArrayList<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(ArrayList<Course> listCourse) {
        this.listCourse = listCourse;
    }
}

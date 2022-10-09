/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dto;

/**
 *
 * @author NguyenVuong    
 */
public class StudentGrade {
    protected int EnrollmentID;
    protected int CourseID;
    protected int StudentID;
    protected float Grade;

    public int getEnrollmentID() {
        return EnrollmentID;
    }

    public void setEnrollmentID(int EnrollmentID) {
        this.EnrollmentID = EnrollmentID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public float getGrade() {
        return Grade;
    }

    public void setGrade(float Grade) {
        this.Grade = Grade;
    }

    public StudentGrade(int EnrollmentID, int CourseID, int StudentID, float Grade) {
        this.EnrollmentID = EnrollmentID;
        this.CourseID = CourseID;
        this.StudentID = StudentID;
        this.Grade = Grade;
    }

    public StudentGrade(int CourseID, int StudentID, float Grade) {
        this.CourseID = CourseID;
        this.StudentID = StudentID;
        this.Grade = Grade;
    }
    public StudentGrade() {
    }
    
    
}

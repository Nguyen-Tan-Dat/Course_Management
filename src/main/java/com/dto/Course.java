/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dto;

/**
 *
 * @author NguyenVuong
 */
public class Course {
    protected int CourseID;
    protected int Credits;
    protected int DepartmentID;
    protected String Title;

    public Course(int CourseID, String Title, int Credits, int DepartmentID) {
        this.CourseID = CourseID;
        this.Credits = Credits;
        this.DepartmentID = DepartmentID;
        this.Title = Title;
    }

    public Course() {
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int Credits) {
        this.Credits = Credits;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    
}

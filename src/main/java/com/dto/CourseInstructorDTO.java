
package com.dto;


public class CourseInstructorDTO {
    private int CourseID;
    private int PersonID;

    public CourseInstructorDTO() {
    }
    
    public CourseInstructorDTO(int CourseID, int PersonID) {
        this.CourseID = CourseID;
        this.PersonID = PersonID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

   
}

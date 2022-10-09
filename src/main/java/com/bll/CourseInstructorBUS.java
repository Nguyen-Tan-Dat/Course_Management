/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bll;

import com.dal.CourseInstructorDAO;
import com.dto.CourseInstructorDTO;

import java.util.ArrayList;
public class CourseInstructorBUS {
    public static ArrayList<CourseInstructorDTO> dsci;
    public CourseInstructorBUS(){
        
    }
    public void docSDCI() {
        CourseInstructorDAO data = new CourseInstructorDAO();
        if(dsci == null)
            dsci = new ArrayList<CourseInstructorDTO>();
            dsci = data.docDSCI();        
    }
    public void them(CourseInstructorDTO ci) {
        CourseInstructorDAO data = new CourseInstructorDAO();
        data.them(ci);
        dsci.add(ci);        
    }
    public void sua(CourseInstructorDTO ci) {
        CourseInstructorDAO data = new CourseInstructorDAO();
        data.sua(ci);
        dsci = data.docDSCI();
    }
    public void xoa(CourseInstructorDTO ci) {
        CourseInstructorDAO data = new CourseInstructorDAO();
        data.xoa(ci);
        for(CourseInstructorDTO cifor : dsci){
            if(cifor.getCourseID() == ci.getCourseID()){
                dsci.remove(cifor);
                break;
            }
        }
    }
     public ArrayList<CourseInstructorDTO> timTheoCourseID(int courseId){
         ArrayList<CourseInstructorDTO> kq = new ArrayList<CourseInstructorDTO>();
         for(CourseInstructorDTO ci : dsci){
             if(String.valueOf(ci.getCourseID()).indexOf(String.valueOf(courseId)) >= 0){
                 kq.add(ci);
             }
         }
         return kq;
     }
     public ArrayList<CourseInstructorDTO> timTheoPersonID(int personId){
         ArrayList<CourseInstructorDTO> kq = new ArrayList<CourseInstructorDTO>();
         for(CourseInstructorDTO ci : dsci){
             if(String.valueOf(ci.getPersonID()).indexOf(String.valueOf(personId)) >= 0){
                 kq.add(ci);
             }
         }
         return kq;
     }
     public ArrayList<CourseInstructorDTO> timTheoTatCa(int allID){
          ArrayList<CourseInstructorDTO> kq = new ArrayList<CourseInstructorDTO>();
          for(CourseInstructorDTO ci : dsci){
              if(String.valueOf(ci.getCourseID()).indexOf(String.valueOf(allID)) >= 0 
                    || String.valueOf(ci.getPersonID()).indexOf(String.valueOf(allID)) >= 0){
                  kq.add(ci);
              }
          }
          return kq;
     }
}

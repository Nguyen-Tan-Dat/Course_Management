package com.bll;

import com.dal.PersonDAL;
import com.dal.StudentGradeDAL;
import com.dto.Person;
import com.dto.StudentGrade;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentGradeBL extends BLL {
	public StudentGradeBL(){
		super(new StudentGradeDAL());
	}
        //==========
        ArrayList<StudentGrade> listStudentGrade = new ArrayList<StudentGrade>();
        StudentGradeDAL studentDal = new StudentGradeDAL();

    public ArrayList<StudentGrade> getListStudentGrade() {
        return listStudentGrade;
    }

    public void setListStudentGrade(ArrayList<StudentGrade> listStudentGrade) {
        this.listStudentGrade = listStudentGrade;
    }
       public ArrayList<StudentGrade> getlistStudentGrade() throws SQLException {
            listStudentGrade = studentDal.getlistStudent();
            return listStudentGrade;
        }
        public ArrayList<StudentGrade> getlistStudentGrade(int id) throws SQLException {
            listStudentGrade = studentDal.getStudentByCourse(id);
            return listStudentGrade;
        }
        public ArrayList<StudentGrade> getlistStudentGradeByID(int id) throws SQLException {
            listStudentGrade = studentDal.getStudentByID(id);
            return listStudentGrade;
        }
        public boolean insert(StudentGrade std) throws SQLException{
            return studentDal.insert(std);
        }
        public boolean update(StudentGrade std) throws SQLException{
            return studentDal.update(std);
        }
        public boolean delete(StudentGrade std) throws SQLException{
            return studentDal.delete(std);
        }
}

package com.bll;

import com.dal.StudentGradeDAL;

public class StudentGradeBL extends BLL {
	public StudentGradeBL(){
		super(new StudentGradeDAL());
	}
}

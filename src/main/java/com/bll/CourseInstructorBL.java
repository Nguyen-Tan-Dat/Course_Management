package com.bll;

import com.dal.CourseInstructorDAL;

public class CourseInstructorBL extends BLL {
	public CourseInstructorBL() {
		super(new CourseInstructorDAL());
	}
}

package com.bll;

import com.dal.CourseDAL;

public class CourseBL extends BLL {
	public CourseBL(){
		super(new CourseDAL());
	}
}

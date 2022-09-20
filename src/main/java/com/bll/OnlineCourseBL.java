package com.bll;

import com.dal.OnlineCourseDAL;

public class OnlineCourseBL extends BLL {
	public OnlineCourseBL(){
		super(new OnlineCourseDAL());
	}
}

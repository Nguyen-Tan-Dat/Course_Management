package com.bll;

import com.dal.OnsiteCourseDAL;

public class OnsiteCourseBL extends BLL {
	public OnsiteCourseBL(){
		super(new OnsiteCourseDAL());
	}
}

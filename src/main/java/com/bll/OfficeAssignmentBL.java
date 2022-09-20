package com.bll;

import com.dal.OfficeAssignmentDAL;

public class OfficeAssignmentBL extends BLL {

	public OfficeAssignmentBL() {
		super(new OfficeAssignmentDAL());
	}
}

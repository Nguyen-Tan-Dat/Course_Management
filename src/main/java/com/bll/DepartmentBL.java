package com.bll;

import com.dal.DepartmentDAL;

public class DepartmentBL extends BLL {
	public DepartmentBL() {
		super(new DepartmentDAL());
	}
}

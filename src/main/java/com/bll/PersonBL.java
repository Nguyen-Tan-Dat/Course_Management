package com.bll;

import com.dal.PersonDAL;

public class PersonBL extends BLL {
	public PersonBL(){
		super(new PersonDAL());
	}
}

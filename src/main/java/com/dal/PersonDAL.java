package com.dal;

import com.dto.Person;

import java.util.Vector;

public class PersonDAL extends DAL {
	public PersonDAL() {
		super("Person", new Person());
	}

	@Override
	public Vector<String> columnNames() {
		Vector<String> r = new Vector<>();
		r.add("ID");
		r.add("Tên");
		r.add("Họ");
		r.add("Ngày đăng nhập");
		r.add("Ngày sai mật khẩu");
		return r;
	}
}

package com.dal;

import com.dto.Course;
import com.dto.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Vector;

public class PersonDAL extends DAL {
	public PersonDAL() {
		super("Person", new Person());
	}

	@Override
	public Vector<String> columnNames() {
		Vector<String> r = new Vector<>();
		r.add("Mã");
		r.add("Tên");
		r.add("H�?");
		r.add("�?ăng nhập");
		r.add("�?ăng nhập sai");
		return r;
	}
        ResultSet rs = null;
    // ===========================
    ArrayList<Person> listPerson = new ArrayList<Person>();

    public ArrayList<Person> getPerson() throws SQLException {
        listPerson.clear();
        rs = sql.select("person");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String time = dateFormat.format(jTextField_NgayLap.getDate());
        while (rs.next()) {
            listPerson.add(new Person(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Firstname"),rs.getDate("HireDate"),rs.getDate("EnrollmentDate")));
        }
        return listPerson;
    }
    public ArrayList<Person> getPerson(int id) throws SQLException {
        listPerson.clear();
        String s = "`CourseID` = '" + id +"'";
        rs = sql.select("studentgrade",s);
        while (rs.next()) {
            listPerson.add(new Person(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Firstname"),rs.getDate("HireDate"),rs.getDate("EnrollmentDate")));
        }
        return listPerson;
    }
    public Person getPersonbyID(int id) throws SQLException{
        String s = "`PersonID` = '" + id +"'";
        rs = sql.select("person",s);
        Person person = null;
        while (rs.next()) {
            person = new Person(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Firstname"),rs.getDate("HireDate"),rs.getDate("EnrollmentDate"));
           break; 
        }
        return person;
    }
    

}

package com.bll;

import com.dal.CourseDAL;
import com.dal.PersonDAL;
import com.dto.Course;
import com.dto.Person;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonBL extends BLL {
	public PersonBL(){
		super(new PersonDAL());
	}
        
        //==========
        ArrayList<Person> listPerson = new ArrayList<Person>();
        PersonDAL personDal = new PersonDAL();
        public ArrayList<Person> getlistPerson() throws SQLException {
            listPerson = personDal.getPerson();
            return listPerson;
        }
        public ArrayList<Person> getlistPerson(int id) throws SQLException {
            listPerson = personDal.getPerson(id);
            return listPerson;
        }
        public Person getPersonbyID(int id) throws SQLException {
            return personDal.getPersonbyID(id);
        }
    public ArrayList<Person> getListPerson() {
        return listPerson;
    }

    public void setListPerson(ArrayList<Person> listPerson) {
        this.listPerson = listPerson;
    }
    public ArrayList<Person> getlistPersonByName(String s) throws SQLException {
            ArrayList<Person> ds = new ArrayList<Person>();
            listPerson = getlistPerson();
        for (Person rs : listPerson) {
            if (rs.getFirstname().indexOf(String.valueOf(s)) >= 0 || rs.getLastname().indexOf(String.valueOf(s)) >= 0) {
                ds.add(rs);
            }
        }
        return ds;
        }
}

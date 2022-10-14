package com.dto;

import java.util.Vector;

public class PersonDTO implements DTO {
    protected String lastname;
    protected String firstname;
    protected String hireDate;

    public PersonDTO(String lastname, String firstname, String hireDate) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.hireDate = hireDate;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public PersonDTO() {

    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public DTO getDTO(String[] values) {
        return new PersonDTO(values[0], values[1], values[2]);
    }

    @Override
    public String[] getData() {
        return new String[]{getFirstname(), getLastname(), getHireDate(), "0000-00-00 00:00:00"};
    }

    @Override
    public Vector<String> toTable() {
        Vector<String> rs=new Vector<>();
        rs.add(getFirstname());
        rs.add(getLastname());
        rs.add(getHireDate());
        return rs;
    }

    @Override
    public String toString() {
        return "\t|\t" + lastname + "\t|\t" + firstname + "\t|\t" + hireDate + "\t|\t";
    }
}

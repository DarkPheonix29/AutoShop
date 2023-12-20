package com.example.autoshop;


import java.util.Date;

public class Customers {

    // string variables for our name and job
    private String email;
    private String password;

    private String adress;

    private String country;

    private String postalcode;

    private String gender;

    private String first_name;

    private String last_name;

    private String birthday;

    private Date register_date;

    public Customers(String email, String password, String adress, String country, String postalcode, String gender, String first_name, String last_name, String birthday, Date register_date) {
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.country = country;
        this.postalcode = postalcode;
        this.gender = gender;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.register_date = register_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date registerDate) {
        this.register_date = registerDate;
    }
}




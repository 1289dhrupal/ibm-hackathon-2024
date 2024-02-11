package com.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    private String userName;
    private String firstname;
    private String lastname;
    private String location;

    private String bio;


    private Integer ethnicity;

    private Integer course;

    private Integer year;

    private String email;
    private String phoneNum;

    public User(String userName,
                String firstname,
                String lastname,
                String location,
                Integer ethnicity,
                Integer course,
                Integer year,
                String bio,
                String email,
                String phoneNum) {
        this.userName = userName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.ethnicity = ethnicity;
        this.course = course;
        this.year = year;
        this.bio = bio;
        this.email = email;
        this.phoneNum = phoneNum;
    }


    public User() {
        // this.userName = null;
        // this.firstname = null;
        // this.lastname = null;
        // this.location = null;
        // this.ethnicity = null;
        // this.course = null;
        // this.year = null;
        // this.bio = null;

    }

    public String getLocation() {
        return location;
    }

    public Integer getEthnicity() {
        return ethnicity;
    }

    public Integer getCourse() {
        return course;
    }

    public Integer getYear() {
        return year;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}

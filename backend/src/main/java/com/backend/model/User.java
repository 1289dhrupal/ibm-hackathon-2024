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

    public User(String userName,
                String firstname,
                String lastname,
                String location,
                Integer ethnicity,
                Integer course,
                Integer year) {
        this.userName = userName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.ethnicity = ethnicity;
        this.course = course;
        this.year = year;
    }


    public User() {
        this.userName = null;
        this.firstname = null;
        this.lastname = null;
        this.location = null;
        this.ethnicity = null;
        this.course = null;
        this.year = null;
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
}

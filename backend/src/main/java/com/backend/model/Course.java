package com.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_table")
public class Course {
    @Id
    private Integer key;

    private String course;


    public Course(Integer key, String course) {
        this.key = key;
        this.course = course;
    }

    public Course() {
        this.key = null;
        this.course = null;
    }

    public String getCourse() {
        return course;
    }
}
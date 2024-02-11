package com.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_table")
public class Course {
    @Id
    private Integer id;

    private String course;


    public Course(Integer key, String course) {
        this.id = key;
        this.course = course;
    }

    public Course() {
        this.id = null;
        this.course = null;
    }

    public String getCourse() {
        return course;
    }
}
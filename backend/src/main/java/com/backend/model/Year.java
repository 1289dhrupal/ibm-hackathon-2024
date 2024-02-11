package com.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "year_table")

public class Year {
    @Id
    private Integer id;

    private String year;


    public Year(Integer key, String year) {
        this.id = key;
        this.year = year;
    }

    public Year() {
        this.id = null;
        this.year = null;
    }

    public String getYear() {
        return year;
    }
}
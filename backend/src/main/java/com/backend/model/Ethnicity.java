package com.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ethnicity_table")

public class Ethnicity {
    @Id
    private Integer id;

    private String ethnicity;


    public Ethnicity(Integer key, String ethnicity) {
        this.id = key;
        this.ethnicity = ethnicity;
    }

    public Ethnicity() {
        this.id = null;
        this.ethnicity = null;
    }

    public String getEthnicity() {
        return ethnicity;
    }
}

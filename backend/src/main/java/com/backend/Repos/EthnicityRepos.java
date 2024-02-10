package com.backend.Repos;

import com.backend.Model.Ethnicity;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EthnicityRepos extends CrudRepository<Ethnicity, Integer> {
    @Override
    ArrayList<Ethnicity> findAll();
}

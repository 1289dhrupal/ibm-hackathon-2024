package com.backend.Repos;

import com.backend.Model.Ethnicity;
import com.backend.Model.User;
import com.backend.Model.Year;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface YearRepos extends CrudRepository<Year, Integer> {
    @Override
    ArrayList<Year> findAll();
}
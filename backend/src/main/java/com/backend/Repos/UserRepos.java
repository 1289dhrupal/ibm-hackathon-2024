package com.backend.Repos;

import com.backend.Model.User;


import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepos extends CrudRepository<User, String> {
    ArrayList<User> findAll();

    ArrayList<User> findAllByLocationContaining(String code);

    // @Query(value = "FROM user_table WHERE location like ?1% AND (ethnicity IN (?2) OR course IN (?3) OR year IN (?4))")
    // ArrayList<User> findAllWithArgs(String code, String ethnicities, String courses, String years);

}
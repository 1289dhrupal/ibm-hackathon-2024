package com.backend.Repos;

import com.backend.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepos extends CrudRepository<User, String> {
    ArrayList<User> findAll();
}
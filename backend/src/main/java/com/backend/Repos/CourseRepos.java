package com.backend.Repos;

import com.backend.Model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface
CourseRepos extends CrudRepository<Course, Integer> {

    ArrayList<Course> findAll();
}

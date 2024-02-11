package com.backend.Controller;

import com.backend.Model.Course;
import com.backend.Model.Ethnicity;
import com.backend.Model.User;
import com.backend.Model.Year;
import com.backend.Repos.CourseRepos;
import com.backend.Repos.EthnicityRepos;
import com.backend.Repos.UserRepos;
import com.backend.Repos.YearRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    private UserRepos userRepos;

    @Autowired
    private CourseRepos courseRepos;

    @Autowired
    private EthnicityRepos ethnicityRepos;

    @Autowired
    private YearRepos yearRepos;

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    String alive() {
        // create dummy data
        ethnicityRepos.save(new Ethnicity(0, "asian"));
        ethnicityRepos.save(new Ethnicity(1, "white"));
        ethnicityRepos.save(new Ethnicity(2, "african"));

        courseRepos.save(new Course(0, "Comp Sci"));
        courseRepos.save(new Course(1, "Business"));
        courseRepos.save(new Course(2, "Maths"));

        yearRepos.save(new Year(0, "PG1"));
        yearRepos.save(new Year(1, "UG1"));
        yearRepos.save(new Year(2, "PUG3"));



        return "I am Alive";
    }

    @PostMapping(value = "/login", produces = "application/json")
    @ResponseBody
    Boolean validateLogin(@RequestParam String username, @RequestParam String pass) {
        // mock login set as cannot connect to university user database
        HashMap<String, String> logins = new HashMap<>();
        logins.put("user", "password");

        return logins.containsKey(username) && logins.get(username).equals(pass);
    }

    @PostMapping(value = "/newUser", produces = "application/json")
    @ResponseBody
    User setUser(@RequestBody User newUser) {
        return userRepos.save(newUser);
    }

    @GetMapping(value = "/community", produces = "application/json")
    @ResponseBody
    HashMap<String, ArrayList<String>> getCommunity() {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        // map.put("ethnicity", ethnicityRepos.findAll());
        // map.put("course", courseRepos.findAll());
        // map.put("year", yearRepos.findAll());
        
        ArrayList<String> temp = new ArrayList<>();
        for (Ethnicity eth : ethnicityRepos.findAll()) {
            temp.add(eth.getEthnicity());
        }
        map.put("ethnicity", temp);
        temp = new ArrayList<>();

        for (Course course : courseRepos.findAll()) {
            temp.add(course.getCourse());
        }
        map.put("course", temp);
        temp = new ArrayList<>();

        for (Year year : yearRepos.findAll()) {
            temp.add(year.getYear());
        }
        map.put("year", temp);

        return map;
    }

    @GetMapping(value = "/nearby", produces = "application/json")
    @ResponseBody
    ArrayList<User> getNearby(@RequestParam @NonNull String code,
                              @RequestParam List<String> ethnicity,
                              @RequestParam List<String> course,
                              @RequestParam List<String> year) {
        ArrayList<User> users = new ArrayList<>();
        for (User i : userRepos.findAll()) {


            if (i.getLocation().substring(0, 2).equals(code) ||
                    (ethnicity != null && ethnicity.contains(ethnicityRepos.findById(i.getEthnicity()).get().getEthnicity())) ||
                    (course != null && course.contains(courseRepos.findById(i.getCourse()).get().getCourse())) ||
                    (year != null && year.contains(yearRepos.findById(i.getYear()).get().getYear()))) {

                users.add(i);
            }
        }
        return users;
    }
}

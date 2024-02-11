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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

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
        yearRepos.save(new Year(2, "UG3"));

        userRepos.save(new User("ab123", "John", "Doe", "LE1 1AB", 0, 0, 0, "this is my bio", "john@doe.com", "441111111111"));
        userRepos.save(new User("bc456", "Mary", "Sue", "LE1 2JS", 1, 2, 1, "I have just moved to leicester", "mary@sue.com", "03333333333"));
        

        return "{\"test\":\"I am Alive\"}";
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
    ArrayList<HashMap<String, String>> getNearby(@RequestParam String code,
                              @RequestParam(required = false) ArrayList<String> ethnicity,
                              @RequestParam(required = false) ArrayList<String> course,
                              @RequestParam(required = false) ArrayList<String> year) {
                                System.out.println(code);
        ArrayList<HashMap<String, String>> users = new ArrayList<>();
        if (ethnicity == null) {
            ethnicity = new ArrayList<>();
        }
        if (course == null) {
            course = new ArrayList<>();
        }
        if (year == null) {
            year = new ArrayList<>();
        }



        for (User usr : userRepos.findAllByLocationContaining(code)) {
            if(ethnicity == null && course == null && year == null) {
                users.add(parseUser(usr));
            } else if (ethnicity.contains(ethnicityRepos.findById(usr.getEthnicity()).get().getEthnicity())) {
                users.add(parseUser(usr));
            } else if (course.contains(courseRepos.findById(usr.getCourse()).get().getCourse())) {
                users.add(parseUser(usr));
            } else if (year.contains(yearRepos.findById(usr.getYear()).get().getYear())) {
                users.add(parseUser(usr));
            }
        }
        return users;
        
    }

    @GetMapping(value = "/users", produces = "application/json")
    @ResponseBody
    ArrayList<HashMap<String, String>> users() {
        // System.out.println(userRepos.findAll());
        ArrayList<HashMap<String, String>> out = new ArrayList<>();
        for (User usr : userRepos.findAll()) {
            out.add(parseUser(usr));
        }
        return out;
    } 

    HashMap<String, String> parseUser(User usr) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", usr.getUserName());
        map.put("firstName", usr.getFirstName());
        map.put("lastName", usr.getLastName());
        map.put("bio", usr.getBio());
        map.put("email", usr.getEmail());
        map.put("location", usr.getLocation());
        map.put("phoneNum", usr.getPhoneNum());
        map.put("course", courseRepos.findById(usr.getCourse()).get().getCourse());
        map.put("ethnicity", ethnicityRepos.findById(usr.getEthnicity()).get().getEthnicity());
        map.put("year", yearRepos.findById(usr.getYear()).get().getYear());
        return map;
    }
    
}

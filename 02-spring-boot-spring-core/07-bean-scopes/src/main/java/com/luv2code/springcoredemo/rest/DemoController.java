package com.luv2code.springcoredemo.rest;


import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
 public Coach myCoach;
 public Coach anAnotherCoach;

@Autowired
public  DemoController(@Qualifier ("cricketCoach") Coach coach,
                       @Qualifier ("cricketCoach") Coach theAnotherCoach){
    System.out.println("In Constructor: " + getClass().getSimpleName());
    myCoach = coach;
    anAnotherCoach = theAnotherCoach;
}

@GetMapping("/dailyworkout")
public String getDailyWorkout(){
    return myCoach.getDailyWorkout();
}

@GetMapping("/check")
    public String check(){
        return "Comparing beans myCoach==anAnotherCoach : " + (myCoach==anAnotherCoach);
    }
}

package com.luv2code.springcoredemo.rest;


import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

public Coach myCoach;


@Autowired
public void setMyCoach(Coach coach){
    myCoach = coach;
}


  /*  @Autowired
    public void doSomeStuff(Coach coach){
        myCoach = coach;
    }

   */

@GetMapping("/dailyworkout")
public String getDailyWorkout(){
    return myCoach.getDailyWorkout();
}
}

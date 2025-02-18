package com.luv2code.springcoredemo.common;

public class SwimCoach implements Coach {

    public  SwimCoach(){
        System.out.println("Inside SwimCoach: "+ getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim for 1000m as warm up practice!!!.";
    }
}

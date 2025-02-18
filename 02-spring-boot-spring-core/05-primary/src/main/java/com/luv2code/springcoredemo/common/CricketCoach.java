package com.luv2code.springcoredemo.common;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {

        return "Practice fast daily for 15 mins. for fast bowling great Tanishq - setter!!!!";
    }
}

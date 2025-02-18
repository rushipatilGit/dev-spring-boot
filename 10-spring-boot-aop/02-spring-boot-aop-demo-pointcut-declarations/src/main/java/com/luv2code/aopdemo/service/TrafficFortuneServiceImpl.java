package com.luv2code.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


// @Service so that Spring can find that during Component Scanning
@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService{


    @Override
    public String getFortune() {

        // simulate a delay
        try {
            TimeUnit.SECONDS.sleep(5); // this will sleep or delay the application for 5 seconds.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // return a fortune
        return "Expect heavy traffic this morning";
    }

    @Override
    public String getFortune(boolean tripWire) {
        if(tripWire){
            throw new RuntimeException("Major Accident! Highway is closed !");
        }
       // return "Expect heavy traffic this morning";
        return getFortune();
    }
}

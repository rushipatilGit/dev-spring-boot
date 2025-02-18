package com.luv2code.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // need a controller method to show initial HTML form
    // @RequestMapping  // Handles all of the Http Request.
    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // need a controller method to process the HTML form
    // @RequestMapping  // Handles all of the Http Request.
    @GetMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    // need controller method to read form data and
    // add data to the model
    //@RequestMapping  // Handles all of the Http Request.
    @GetMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){

        // read request parameter from HTML form
        String theName = request.getParameter("studentName");

        // covert that data to all caps
        theName = theName.toUpperCase();

        //create the message
        String result = "Yo!" + theName;

        //add that message to the model
        model.addAttribute("message" , result);
        return "helloworld";
    }

    //@RequestMapping  // Handles all of the Http Request.
    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName, Model model){

        // read request parameter from HTML form
       // String theName = request.getParameter("studentName");

        // covert that data to all caps
        theName = theName.toUpperCase();

        //create the message
        String result = "Hey My Friend from V3!" + theName;

        //add that message to the model
        model.addAttribute("message" , result);
        return "helloworld";
    }


}

package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> listOfStudents;

    // define @PostConstruct to load the student data ... only once!
    @PostConstruct
    public void loadData(){

        listOfStudents = new ArrayList<>();
        listOfStudents.add(new Student("Rushikesh","Patil"));
        listOfStudents.add(new Student("Tanishq","Patil"));
        listOfStudents.add(new Student("Ashwini","Pimpale"));

    }

    // define End point for ("/students") - return a list of Students.
    @GetMapping("/students")
    public List<Student> getStudents(){
        return listOfStudents;
    }

    // Each request will load the new student data .. not good for performance.
//    @GetMapping("/students")
//    public List<Student> getStudents(){
//
//        List<Student> listOfStudents = new ArrayList<>();
//        listOfStudents.add(new Student("Rushikesh" , "Patil"));
//        listOfStudents.add(new Student("Tanishq" , "Patil"));
//        listOfStudents.add(new Student("Ashiwini" , "Pimpale"));
//
//        return listOfStudents;
//    }

    // define the endpoint or ("/students/studentId") - return the student at index.
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        // just index into the list ... keep it simple for now.
       // System.out.println("inside getStudent ");
        // check the studentId against the list Size.

        if ((studentId >= listOfStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return listOfStudents.get(studentId);

    }

    // Add an Exception Handler using @ExceptionHandler
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
//
//        // Create StudentErrorResponse
//            StudentErrorResponse error = new StudentErrorResponse();
//            error.setStatus(HttpStatus.NOT_FOUND.value());
//            error.setMessage(exc.getMessage());
//            error.setTimeStamp(System.currentTimeMillis());
//
//        // Return ResponseEntity
//        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
//
//    }

    // Add another Exception handler - to catch any exceptions (catch all)

//    @ExceptionHandler ResponseEntity<StudentErrorResponse>  handleException(Exception exc) {
//
//        // Create StudentErrorResponse
//
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // Return ResponseEntity
//         return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//    }

}

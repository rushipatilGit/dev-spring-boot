package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int theId);

     List<Course> findCoursesByInstructorId(int theId);

     Instructor findInstructorByIdJoinFetch(int theId);

     void update(Instructor tempInstructor);

     void update(Course tempCourse);

     Course findCourseById(int theId);

     void deleteCourseById(int theId);

}

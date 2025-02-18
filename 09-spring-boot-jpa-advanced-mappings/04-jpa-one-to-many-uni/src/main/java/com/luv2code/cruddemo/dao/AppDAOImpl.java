package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // define the field for EntityManager

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl (EntityManager entityManager){
    this.entityManager = entityManager;
    }

    // inject the entity manager using the constructor injection
    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

   /* @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
        //delete the instructor
        entityManager.remove(tempInstructor);
    }*/

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
        // get the courses
        List<Course>  courses = tempInstructor.getCourses();
       // Break the association of all courses for the instructor
        for(Course tempCourse: courses){
            tempCourse.setInstructor(null);
        }
        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // retrieve the instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directionl link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);
        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id=:data", Course.class);
        query.setParameter("data", theId);

        // execute the query
        List<Course> courses =  query.getResultList();
        return courses;
    }


    // Even with Lazy Loading , both the instructor and courses will be fetched as if its Eager loading.
    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        /*TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        +"JOIN FETCH i.courses "
                    +"where i.id=:data ", Instructor.class);*/
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        +"JOIN FETCH i.courses "
                        +"JOIN FETCH i.instructorDetail "
                        +"where i.id=:data ", Instructor.class);
        query.setParameter("data", theId);
        // execute the query
        Instructor instructor = query.getSingleResult();
        return instructor;
     }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {

        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
         return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        // retrieve the course
        Course tempCourse =  entityManager.find(Course.class, theId);

        // delete the course
        entityManager.remove(tempCourse);

    }

    @Override
    @Transactional
    public void save(Course theCourse) {

        entityManager.persist(theCourse);

    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
              + "JOIN FETCH c.reviews "
                + " where c.id=:data", Course.class);
        query.setParameter("data", theId);
        //execute query
        Course theCourse = query.getSingleResult();

        return theCourse;
    }
}

package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, id);
        //delete the instructor
        entityManager.remove(tempInstructor);
    }
}

package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement the save method
    @Transactional
    @Override
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        // create query

       // TypedQuery<Student> theQuery =  entityManager.createQuery("from Student order by lastName", Student.class);
        TypedQuery<Student> theQuery =  entityManager.createQuery("from Student", Student.class);


        //return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {

        // create Query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where lastName=:theData", Student.class);

        // set query parameter
        theQuery.setParameter("theData", lastName);
        // return query results

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(int id) {

        // Retrive the Student
        Student theStudent = entityManager.find(Student.class,id);

        // Delete the Student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {

       int numRowsDeleted =  entityManager.createQuery("delete from Student").executeUpdate();
        return numRowsDeleted;
    }
}

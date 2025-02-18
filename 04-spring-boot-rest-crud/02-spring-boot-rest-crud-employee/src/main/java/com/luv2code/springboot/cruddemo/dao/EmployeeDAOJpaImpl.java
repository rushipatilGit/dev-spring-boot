package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    // define field for entitymanager

    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl (EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // Execute a query & get Result List
        List<Employee> employees = theQuery.getResultList();

        // Return the list
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // get the Employee
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // Return the Employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        // Save Employee
        // if id==0 the it will insert new Employee or else Update the existing Employee.
       Employee dbEmployee =  entityManager.merge(theEmployee);

       // Return Employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        // Find the employee by id
       Employee theEmployee =  entityManager.find(Employee.class,theId);
        // Remove employee
        entityManager.remove(theEmployee);
    }
}

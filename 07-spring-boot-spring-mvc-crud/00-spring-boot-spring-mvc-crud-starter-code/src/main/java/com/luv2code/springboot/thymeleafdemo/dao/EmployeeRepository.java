package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // add a method to sort by last name ascending
   // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    public List<Employee> findAllByOrderByLastNameAsc(); // Due to Spring Data JPA entry in Pom.xml

}

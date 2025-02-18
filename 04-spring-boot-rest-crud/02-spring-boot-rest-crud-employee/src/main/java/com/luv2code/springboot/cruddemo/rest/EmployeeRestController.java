package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.dao.EmployeeDAOJpaImpl;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // Quick and dirty : inject employee Dao (Use Constructor Injection here)
//    private EmployeeDAO employeeDAO;
//
//    @Autowired
//    public EmployeeRestController(EmployeeDAO theEmployeeDAO){
//        employeeDAO=theEmployeeDAO;
//    }

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController (EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    // expose  "/employees" end point and  return a list of employees.
//    @GetMapping("/employees")
//    public List<Employee> findAll(){
////        List<Employee> employees = employeeDAO.findAll();
////        return  employees;
//        return  employeeDAO.findAll();
//    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return  employeeService.findAll();
    }

    // Add the get mapping for "/api/employees/{employeeId}"

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null) {
            throw new RuntimeException("Employee Id not found - " + employeeId);
        }
        return theEmployee;
    }

    // add mapping for post employees - add new employee
    @PostMapping("/employees")
   public Employee addEmployee(@RequestBody Employee theEmployee){

        // also just in case they pass an id in the Json... set Id to 0
        // this is to force save of new item i.e. to insert new item.... instead of update.

        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee; // return the updated employee object.
    }

    // add mapping for put ("/employees") ---updating existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for delete ("/employee/{employeeId}) --- deleting exiting employee based on id
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee tempEmployee = employeeService.findById(employeeId);

        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found -" + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted Employee id - " + employeeId;
    }
}

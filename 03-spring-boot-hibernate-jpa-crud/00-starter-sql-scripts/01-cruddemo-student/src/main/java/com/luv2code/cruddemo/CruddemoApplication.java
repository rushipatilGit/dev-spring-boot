package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner -> {
			//createStudent(studentDAO);
			 createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			 // queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			// deleteAll(studentDAO);
		};
	}

	private void deleteAll(StudentDAO studentDAO) {
		System.out.println("Deleting all Students");

		int numofRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted Row Count : " + numofRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting Student id :" + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {

		//Retrive the student based on the id: primary key
		int studentId=1;
		System.out.println("Getting the student id with :" + studentId);
		Student myStudent = studentDAO.findById(studentId);
		// change the first name to "John"
		System.out.println("Updating the Student..");
		myStudent.setFirstName("John");

		//update the Student
		studentDAO.update(myStudent);

		//display the updated the student
		System.out.println("Updated Student : "+ myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get List of students
		List<Student> theStudents = studentDAO.findByLastName("Doe");
		//Display the Students
		for(Student tempStudent:theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		//get the List of students
		List<Student> theStudents  = studentDAO.findAll();

		//display the list of students

		for (Student tempStudent:theStudents){
		System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		// create a student object
		System.out.println("Creating the new student object......");
		Student tempStudent = new Student("Daffy" , "Duck","daffy@luv2code.com");

		// save the student
		studentDAO.save(tempStudent);
		// display the id of the saved student
		int id = tempStudent.getId();
		System.out.println("Saved Student. Generated id: " + id);

		// retrieve the student based on the id: primary key
		System.out.println("Retriving Stduent with id : " + id);
		Student myStudent = studentDAO.findById(id);
		//display student
		System.out.println("Found the Student : " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// Creating the student objects;
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("John","Doe","john@luv2code.com");
		Student tempStudent2 = new Student("Mary","Public","mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita","Applebum","bonita@luv2code.com");

		// Saving the student objects
		System.out.println("Saving student objects...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {

		// create the Student object
		System.out.println("Creating the student....");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		//save the student object
		System.out.println("Saving the student....");
		studentDAO.save(tempStudent);

		//display the id of the save student
		System.out.println("Saved Student. Generated id : " + tempStudent.getId());
	}

}
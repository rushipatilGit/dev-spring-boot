package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
			// System.out.println("Hello World !!");
			// createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			// findInstructorWithCourses(appDAO);

			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			// updateCourse(appDAO);
			// deleteInstructor(appDAO);
			deleteCourse(appDAO);
		};

	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 10;
		System.out.println("Deleting the Course Id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done !!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId=10;

		// find the course
		System.out.println("Finding Course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating course id : " + theId);
		tempCourse.setTitle("Enjoy the Simple Things");

		appDAO.update(tempCourse);
		System.out.println("Done !!");

	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;
		// find the instructor
		System.out.println("Finding the Instructor :" + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update the instructor
		System.out.println("Updating instructor id:" + theId);
		tempInstructor.setLastName("TESTER");
		appDAO.update(tempInstructor);
		System.out.println("Done !!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding Instructor id:" + theId);
		// will retrieve instructor and courses as you are using the Join fetch query.
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor :" + tempInstructor);
		System.out.println("the associated courses :" + tempInstructor.getCourses());

		System.out.println("Done !!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		// find instructor
		System.out.println("Finding instructor ID : " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor : " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id :" + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		//associate the object
		tempInstructor.setCourses(courses);
		System.out.println("the associated courses : " + tempInstructor.getCourses());

		System.out.println("Done !!");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding instructor ID : " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor : " + tempInstructor);
		System.out.println("the associated Courses: " + tempInstructor.getCourses());
		System.out.println("Done !!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		// Create the instructor
		Instructor tempInstructor = new Instructor("Susan", "Public", "Susan.public@gmail.com");

		// Create the instructor details
		InstructorDetail tempInstructorDetails = new InstructorDetail("http://www.youtube.com",
				"Video Games");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetails);

		//Create some courses
		Course tempCourse1 = new Course("Air Guitar the Ultimate Guide");
		Course tempCourse2 = new Course("The PinCall Master class");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		// NOTE: This will also save the courses because of CascadeType.PERSIST
		System.out.println("Saving instructor : " + tempInstructor);
		System.out.println("The Courses :" + tempInstructor.getCourses());

		appDAO.save(tempInstructor);
		System.out.println("Done !!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId=6;
		System.out.println("Deleting instructor detail id : " + theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done !!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor detail object
		int theId=2;
		InstructorDetail tempIinstructorDetail = appDAO.findInstructorDetailById(theId);
		//print the instructor detail

		System.out.println("tempIinstructorDetail : " + tempIinstructorDetail);

		//print the associated instructor
		System.out.println( "the associated instructor : " + tempIinstructorDetail.getInstructor());

		System.out.println("Done!!");
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId=1;
		System.out.println("Deleting Instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding Instructor id : " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		// InstructorDetail tempInstructorDetails = tempInstructor.getInstructorDetail();
		System.out.println("the associateed instructorDetail only : " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		/*
		// Create the instructor

		Instructor tempInstructor = new Instructor("Rush", "Patil", "rp150982@gmail.com");

		// Create the instructor details

		InstructorDetail tempInstructorDetails = new InstructorDetail("http://www.luv2code.com/youtube",
				"Luv 2 Code!!");

		 */

		// Create the instructor

		Instructor tempInstructor = new Instructor("Ashwini", "Patil", "ashwini@gmail.com");

		// Create the instructor details

		InstructorDetail tempInstructorDetails = new InstructorDetail("http://www.luv2code.com/youtube",
				"Music!");


		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetails);

		// save the instructor
		// Note : This will also save details object because of CascadeType.ALL
		//
		System.out.println("Saving instructor :" +  tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done !!");

	}
}

package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			 deleteInstructorDetail(appDAO);
		};

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

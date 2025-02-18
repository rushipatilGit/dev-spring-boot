package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(AopdemoApplication.class, args);
	}

	// Spring will automatic inject the dependency for AccountDAO because of @Bean annotation
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO){
		return  runner -> {
			//System.out.println("Hello World !!");
				demoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

		// call the business method
		//theAccountDAO.addAccount();
		Account theAccount = new Account();
		theAccountDAO.addAccount(theAccount, true);
		theAccountDAO.doWork();

		// call the membership business method
		theMembershipDAO.addSillyAccount();
		theMembershipDAO.goToSleep();
//		// do it again!
//		System.out.println("\n let's do it again!");
//
//		// call the business method again
//		theAccountDAO.addAccount();
//
//		// call the membership business method again
//		theMembershipDAO.addAccount();
	}

}

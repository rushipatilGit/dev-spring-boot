package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(AopdemoApplication.class, args);
	}

	// Spring will automatic inject the dependency for AccountDAO because of @Bean annotation
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO,
											   TrafficFortuneService theTrafficFortuneService){
		return  runner -> {
			//System.out.println("Hello World !!");
				//demoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
			//demoTheAfterReturningAdvice(theAccountDAO);
			// demoTheAfterThrowingAdvice(theAccountDAO);
			//demoTheAfterAdvice(theAccountDAO);
			// demoTheAroundAdvice(theTrafficFortuneService);
			//	demoTheAroundAdviceHandleException(theTrafficFortuneService);
			demoTheAroundAdviceRethrowException(theTrafficFortuneService);
		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceRethrowException");
		System.out.println("Calling the getFortune()");
		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);
		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");
		System.out.println("Calling the getFortune()");
		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);
		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");

	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdvice");
		System.out.println("Calling the getFortune()");
		String data = theTrafficFortuneService.getFortune();
		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		//call the method to find the accounts
		List<Account> theAccounts = null;
		try{
			// add a boolean flag to simulate the exceptions
			boolean tripWire = false;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		}catch (Exception exc){
			System.out.println("\n\n Main Program: ...caught exception: " + exc);
		}
		// display the accounts
		System.out.println("\n\n Main Program: demoTheAfterAdvice");
		System.out.println("--------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		//call the method to find the accounts
		List<Account> theAccounts = null;
		try{
			// add a boolean flag to simulate the exceptions
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		}catch (Exception exc){
			System.out.println("\n\n Main Program: ...caught exception: " + exc);
		}
		// display the accounts
		System.out.println("\n\n Main Program: demoTheAfterThrowingAdvice");
		System.out.println("--------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {

		//call the method to find the accounts
		List<Account> theAccounts = theAccountDAO.findAccounts();
		// display the accounts
		System.out.println("\n\n Main Program: demoTheAfterReturningAdvice");
		System.out.println("--------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

		// call the business method
		//theAccountDAO.addAccount();
		Account theAccount = new Account();
		theAccount.setName("Rushikesh");
		theAccount.setLevel("Platinum");
		theAccountDAO.addAccount(theAccount, true);
		theAccountDAO.doWork();

		// call accountDAO getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");

		String name = theAccountDAO.getName();
		String serviceCode = theAccountDAO.getServiceCode();

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

package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// Makes class available for component scanning - its sub annotation of @Component.
// so that we inject this implementation later on.
@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;

    private String serviceCode;

//    @Override
//    public void addAccount() {
//
//        System.out.println(getClass()+" DOING MY DB WORK : ADDING AN ACCOUNT");
//
//    }


    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {

        System.out.println(getClass()+": DOING MY DB WORK : ADDING AN ACCOUNT");

    }

    @Override
    public boolean doWork() {
        System.out.println(getClass()+": doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass()+": in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass()+": in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass()+": in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass()+": in setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        List<Account> myAccounts = new ArrayList<>();
        // create sample accounts
        Account tempAccount1 = new Account("John", "Silver");
        Account tempAccount2 = new Account("Rushikesh", "Paltinum");
        Account tempAccount3 = new Account("Madhu", "Gold");
        //add them to our accounts list
        myAccounts.add(tempAccount1);
        myAccounts.add(tempAccount2);
        myAccounts.add(tempAccount3);
        return  myAccounts;
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        // for academic purpose ... simulate an exception
        if(tripWire){
            throw new RuntimeException("No soup for you !!!");
        }
        List<Account> myAccounts = new ArrayList<>();
        // create sample accounts
        Account tempAccount1 = new Account("John", "Silver");
        Account tempAccount2 = new Account("Rushikesh", "Paltinum");
        Account tempAccount3 = new Account("Madhu", "Gold");
        //add them to our accounts list
        myAccounts.add(tempAccount1);
        myAccounts.add(tempAccount2);
        myAccounts.add(tempAccount3);
        return  myAccounts;
    }
}

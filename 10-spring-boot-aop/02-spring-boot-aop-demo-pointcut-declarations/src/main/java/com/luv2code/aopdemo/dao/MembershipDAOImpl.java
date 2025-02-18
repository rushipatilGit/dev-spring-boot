package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

// Makes class available for component scanning - its sub annotation of @Component.
// so that we inject this implementation later on.
@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public boolean addSillyAccount() {

        System.out.println(getClass()+": DOING MY DB WORK : ADDING A MEMBERSHIP ACCOUNT");

        return true;

    }

    @Override
    public void goToSleep() {

        System.out.println(getClass()+": I am going to sleep now..");

    }
}

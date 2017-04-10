package edu.gatech.cs2340.waterfall;

import org.junit.Test;


import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;
import edu.gatech.cs2340.waterfall.model.Administrator;
import edu.gatech.cs2340.waterfall.model.Manager;
import edu.gatech.cs2340.waterfall.model.Model;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.Worker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Mohit Chauhan on 4/10/2017.
 */

public class MohitChauhanUnitTest {
    @Test
    public void determineUserTestNotNull() throws Exception {
        WelcomeActivity wel = new WelcomeActivity();
        String type = "user";
        String uid = "34344";
        String name = "Foo Bar";
        String email = "foo@bar.com";
        int zip = 30332;
        String phoneNumber = "909900900";
        User newUser = new User(uid, name, email, zip, phoneNumber);
        assertNotNull("UserIntentCreation", wel.determineUser(type, uid, name, email, zip, phoneNumber));
        type = "worker";
        assertNotNull("WorkerIntentCreation", wel.determineUser(type, uid, name, email, zip, phoneNumber));
        type = "manager";
        assertNotNull("MangerIntentCreation", wel.determineUser(type, uid, name, email, zip, phoneNumber));
        type = "admin";
        assertNotNull("AdminIntentCreation", wel.determineUser(type, uid, name, email, zip, phoneNumber));
    }

    @Test
    public void determineUserTestModelSet() throws Exception {
        WelcomeActivity wel = new WelcomeActivity();
        String type = "user";
        String uid = "34344";
        String name = "Foo Bar";
        String email = "foo@bar.com";
        int zip = 30332;
        String phoneNumber = "909900900";
        User newUser = new User(uid, name, email, zip, phoneNumber);
        wel.determineUser(type, uid, name, email, zip, phoneNumber);
        assertEquals(Model.getInstance().getCurrentUser(), newUser);
        Worker newWorker = new Worker((uid + "1"), name, email, zip, phoneNumber);
        type = "worker";
        wel.determineUser(type, (uid + "1"), name, email, zip, phoneNumber);
        assertEquals(Model.getInstance().getCurrentUser(), newWorker);
        Manager newManager = new Manager((uid + "2"), name, email, zip, phoneNumber);
        type = "manager";
        wel.determineUser(type, (uid + "2"), name, email, zip, phoneNumber);
        assertEquals(Model.getInstance().getCurrentUser(), newManager);
        Administrator newAdmin = new Administrator((uid + "3"), name, email, zip, phoneNumber);
        type = "admin";
        wel.determineUser(type, (uid + "3"), name, email, zip, phoneNumber);
        assertEquals(Model.getInstance().getCurrentUser(), newAdmin);
    }

}

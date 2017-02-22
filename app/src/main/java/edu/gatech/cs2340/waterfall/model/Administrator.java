package edu.gatech.cs2340.waterfall.model;

import com.google.firebase.database.DatabaseReference;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class Administrator extends User{



    //constructor
    public Administrator(String uid, String email, String name, int zipcode, String phoneNumber) {
        super(uid, email, name, zipcode, phoneNumber);
    }

    public Administrator(String uid, String email, String name) {
        this(uid, email, name, 0, null);
    }


    //action methods
    public void deleteAccount() {
        //TODO
    }

    public void banUser() {
        //TODO
    }

    public void unblockAccount() {
        //TODO
    }

    public void viewSecurityLog() {
        //TODO
    }
}

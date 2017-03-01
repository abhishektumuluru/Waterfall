package edu.gatech.cs2340.waterfall.model;

import android.os.Parcel;

import com.google.firebase.database.DatabaseReference;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;

/**
 * Created by vivekraja07 on 2/21/17.
 */
//The Administrator class
public class Administrator extends User{
    //Constructor
    public Administrator(String uid, String name, String email, int zipcode, String phoneNumber) {
        super(uid, name, email, zipcode, phoneNumber);
    }

    //creating a parcelable constructor
    public Administrator(Parcel parcel) {
        super(parcel);
    }
    //constructor

    /**
     * Delete an account from the database
     */
    public void deleteAccount() {
        //TODO
    }

    /**
     * Ban a user from the database
     */
    public void banUser() {
        //TODO
    }

    /**
     * Unblock a blocked account from the database
     */
    public void unblockAccount() {
        //TODO
    }

    /**
     * View the security log
     */
    public void viewSecurityLog() {
        //TODO
    }
}

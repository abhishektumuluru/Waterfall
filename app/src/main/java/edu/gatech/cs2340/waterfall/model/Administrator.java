package edu.gatech.cs2340.waterfall.model;

import android.os.Parcel;

import com.google.firebase.database.DatabaseReference;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class Administrator extends User{

    public Administrator(String uid, String email, String name, int zipcode, String phoneNumber) {
        super(uid, email, name, zipcode, phoneNumber);
    }
    public Administrator(Parcel parcel) {
        super(parcel);
    }
    //constructor

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

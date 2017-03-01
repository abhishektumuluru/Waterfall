package edu.gatech.cs2340.waterfall.model;

import android.os.Parcel;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class Worker extends User {

//    public String getType() {
//        return type;
//    }

    //private String type = "worker";

    public Worker(String uid, String name, String email, int zipcode, String phoneNumber) {
        super(uid, name, email, zipcode, phoneNumber);
    }
    //constructor
    public Worker(Parcel parcel) {
        super(parcel);
    }

    //action methods
    public void reportPurity() {
        //TODO
    }
}

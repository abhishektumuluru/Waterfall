package edu.gatech.cs2340.waterfall.model;

import android.os.Parcel;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class Worker extends User {

    public String getType() {
        return type;
    }

    private final String type = "worker";

    public Worker(String uid, String email, String name, int zipcode, String phoneNumber) {
        super(uid, email, name, zipcode, phoneNumber);
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

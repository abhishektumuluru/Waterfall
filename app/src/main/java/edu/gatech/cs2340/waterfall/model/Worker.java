package edu.gatech.cs2340.waterfall.model;

import android.os.Parcel;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class Worker extends User {
    //constructor
    public Worker(String uid, String name, String email, int zipcode, String phoneNumber) {
        super(uid, name, email, zipcode, phoneNumber);
    }
    //Parcelable constructor
    public Worker(Parcel parcel) {
        super(parcel);
    }

    //action methods

    /**
     * Report the water purity of a particular report
     */
    public void reportPurity() {
        //TODO
    }
}

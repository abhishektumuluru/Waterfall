package edu.gatech.cs2340.waterfall.model;

import android.os.Parcel;


@SuppressWarnings("unused")
public class Worker extends User {
    //constructor
    public Worker(String uid, String name, String email, int zipcode, String phoneNumber) {
        super(uid, name, email, zipcode, phoneNumber);
    }
    //Parcelable constructor
    public Worker(Parcel parcel) {
        super(parcel);
    }

}

package edu.gatech.cs2340.waterfall.model;

import android.os.Parcel;

import com.google.firebase.database.DatabaseReference;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;


//The Administrator class
public class Administrator extends User{
    //Constructor
    public Administrator(String uid, String name, String email, int zipcode, String phoneNumber) {
        super(uid, name, email, zipcode, phoneNumber);
    }

    //creating a parcelable constructor
    @SuppressWarnings("unused")
    public Administrator(Parcel parcel) {
        super(parcel);
    }
    //constructor

    /**
     * Delete an account from the database
     */
    @SuppressWarnings("unused")
    public void deleteAccount() {
        DatabaseReference mUserDatabase = WelcomeActivity.getUserDatabase();
        mUserDatabase.child(uid).removeValue();
    }

}

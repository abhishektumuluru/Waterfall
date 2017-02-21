package edu.gatech.cs2340.waterfall.model;

import com.google.firebase.database.DatabaseReference;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class Administrator {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //instance variables
    private String uid;
    private String name;
    private String email;
    private final String type = "admin";
    private int zipcode;
    private String phoneNumber;

    //constructor
    public Administrator(String uid, String email, String name, int zipcode, String phoneNumber) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
    }

    public Administrator(String uid, String email, String name) {
        this(uid, email, name, 0, null);
    }

    //write to database
    public void writeToDatabase() {
        DatabaseReference mUserDatabase = WelcomeActivity.getmUserDatabase();
        mUserDatabase.child(uid).child("email").setValue(email);
        mUserDatabase.child(uid).child("name").setValue(name);
        mUserDatabase.child(uid).child("zipcode").setValue(zipcode);
        mUserDatabase.child(uid).child("phone number").setValue(phoneNumber);
        mUserDatabase.child(uid).child("type").setValue(type);
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

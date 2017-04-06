package edu.gatech.cs2340.waterfall.model;


public class Manager extends Worker {
    //constructor
    public Manager(String uid, String name, String email, int zipcode, String phoneNumber) {
        super(uid, name, email, zipcode, phoneNumber);
    }
    @SuppressWarnings("unused")
    public Manager(String uid, String name, String email) {
        this(uid, name, email, 0, null);
    }

}

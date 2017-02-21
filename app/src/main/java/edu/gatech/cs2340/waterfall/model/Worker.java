package edu.gatech.cs2340.waterfall.model;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class Worker extends User {

    public String getType() {
        return type;
    }

    private final String type = "worker";

    //constructor
    public Worker(String uid, String email, String name, int zipcode, String phoneNumber) {
        super(uid, email, name, zipcode, phoneNumber);
    }

    public Worker(String uid, String email, String name) {
        this(uid, email, name, 0, null);
    }

    //action methods
    public void reportPurity() {
        //TODO
    }
}

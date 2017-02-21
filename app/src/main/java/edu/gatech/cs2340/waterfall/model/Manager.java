package edu.gatech.cs2340.waterfall.model;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class Manager extends Worker {

    public String getType() {
        return type;
    }

    private final String type = "manager";

    //constructor
    public Manager(String uid, String email, String name, int zipcode, String phoneNumber) {
        super(uid, email, name, zipcode, phoneNumber);
    }

    public Manager(String uid, String email, String name) {
        this(uid, email, name, 0, null);
    }

    //action Methods
    public void viewHistoricalReports() {
        //TODO
    }

    public void viewHistoricalTrends() {
        //TODO
    }

    public void deleteReport() {
        //TODO
    }

}

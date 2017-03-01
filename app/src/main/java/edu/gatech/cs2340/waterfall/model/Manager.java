package edu.gatech.cs2340.waterfall.model;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class Manager extends Worker {

//    public String getType() {
//        return type;
//    }
//
//    private String type = "manager";

    //constructor
    public Manager(String uid, String name, String email, int zipcode, String phoneNumber) {
        super(uid, name, email, zipcode, phoneNumber);
    }

    public Manager(String uid, String name, String email) {
        this(uid, name, email, 0, null);
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

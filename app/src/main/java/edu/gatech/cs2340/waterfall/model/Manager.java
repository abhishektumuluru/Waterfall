package edu.gatech.cs2340.waterfall.model;


public class Manager extends Worker {
    //constructor
    public Manager(String uid, String name, String email, int zipcode, String phoneNumber) {
        super(uid, name, email, zipcode, phoneNumber);
    }
    public Manager(String uid, String name, String email) {
        this(uid, name, email, 0, null);
    }

    /**
     * View the historical reports of water reports
     */
    //action Methods
    public void viewHistoricalReports() {
        //TODO
    }

    /**
     * View historical trends of water reports
     */
    public void viewHistoricalTrends() {
        //TODO
    }

    /**
     * Delete a report from the database
     */
    public void deleteReport() {
        //TODO
    }

}

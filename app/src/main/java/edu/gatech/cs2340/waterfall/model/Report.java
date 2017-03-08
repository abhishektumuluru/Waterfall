package edu.gatech.cs2340.waterfall.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Random;

/**
 * Created by seanrsain on 3/5/17.
 */

public abstract class Report implements Parcelable{

    // getter and setter

    /**
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return the report No
     */
    public String getReportNo() {
        return reportNo;
    }

    /**
     *
     * @param reportNo to change it
     */
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    /**
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date to change it
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     *
     * @param uid the uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    //instance variables
    private Location location;
    private User user;
    private String uid;
    private String userName;
    private String reportNo;
    private static int reportCounts;
    private Date date;


    /**
     *
     * @param user that wants to submit a report
     * @param location that he is submitting report from
     */
    public Report(User user, Location location){
        this.date = new Date();
        Random random = new Random();
        reportCounts = random.nextInt(30000);
        this.user = user;
        this.location = location;
        this.userName = user.getName();
        this.uid = user.getUid();
        this.reportNo = uid + reportCounts;
    }

    /**
     * write the information of a water report to eh database
     */
    public abstract void writeToDatabase();

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

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
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

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

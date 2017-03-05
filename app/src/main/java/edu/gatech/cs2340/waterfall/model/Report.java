package edu.gatech.cs2340.waterfall.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by seanrsain on 3/5/17.
 */

public abstract class Report implements Parcelable {

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

    public int getReportNo() {
        return reportNo;
    }

    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //instance variables
    private Location location;
    private User user;
    private String userName;
    private int reportNo;
    Date date;


    public Report(User user, Location location){
        this.user = user;
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}

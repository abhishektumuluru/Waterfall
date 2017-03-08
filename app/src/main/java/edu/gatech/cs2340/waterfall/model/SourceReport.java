package edu.gatech.cs2340.waterfall.model;

import android.location.Location;
import android.os.Parcel;

import com.google.firebase.database.DatabaseReference;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;

/**
 * Created by seanrsain on 3/6/17.
 */

public class SourceReport extends Report{

    public waterType getWaterType() {
        return WaterType;
    }

    public void setWaterType(waterType waterType) {
        WaterType = waterType;
    }

    public waterCondition getWaterCondition() {
        return WaterCondition;
    }

    public void setWaterCondition(waterCondition waterCondition) {
        WaterCondition = waterCondition;
    }

    private waterType WaterType;
    private waterCondition WaterCondition;

    public SourceReport(User user, Location location, waterType WT, waterCondition WC) {
        super(user, location);
        WaterType = WT;
        WaterCondition = WC;
    }

    /**
     *
     * @return String of Source Report
     */
    public String toString() {
        return "Source Report: " + "Type: " + WaterType.toString() + " Condition: " + WaterCondition.toString();
    }

    @Override
    public void writeToDatabase() {
        DatabaseReference mReportDatabase = WelcomeActivity.getmReportDatabase();
        mReportDatabase.child(getReportNo()).child("dateAndTime").setValue(getDate());
        mReportDatabase.child(getReportNo()).child("reporterName").setValue(getUserName());
        mReportDatabase.child(getReportNo()).child("location").setValue(getLocation());
        mReportDatabase.child(getReportNo()).child("type").setValue(WaterType);
        mReportDatabase.child(getReportNo()).child("condition").setValue(WaterCondition);
        mReportDatabase.child(getReportNo()).child("user").setValue(getUser());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.getDate().toString());
        parcel.writeString(this.getUserName());
        parcel.writeString(this.getLocation().toString());
        parcel.writeString(this.WaterType.toString());
        parcel.writeString(this.WaterCondition.toString());
    }

}

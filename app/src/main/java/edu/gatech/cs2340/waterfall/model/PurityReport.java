package edu.gatech.cs2340.waterfall.model;

import android.location.Location;
import android.os.Parcel;

import com.google.firebase.database.DatabaseReference;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;

/**
 * Created by seanrsain on 3/6/17.
 */

public class PurityReport extends Report {

    private overallCondition OverallCondition;
    private int virusPPM;
    private int containmentPPM;

    public PurityReport(User user, Location location, overallCondition OC, int virus, int containment) {
        super(user, location);
        OverallCondition = OC;
        virusPPM = virus;
        containmentPPM = containment;
    }

    public void writeToDatabase() {
        DatabaseReference mReportDatabase = WelcomeActivity.getmReportDatabase();
        mReportDatabase.child(getReportNo()).child("dateAndTime").setValue(getDate());
        mReportDatabase.child(getReportNo()).child("reporterName").setValue(getUserName());
        mReportDatabase.child(getReportNo()).child("location").setValue(getLocation());
        mReportDatabase.child(getReportNo()).child("overallCondition").setValue(OverallCondition);
        mReportDatabase.child(getReportNo()).child("virus").setValue(virusPPM);
        mReportDatabase.child(getReportNo()).child("containment").setValue(containmentPPM);
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
        parcel.writeString(this.OverallCondition.toString());
        parcel.writeInt(this.virusPPM);
        parcel.writeInt(this.containmentPPM);
    }
}

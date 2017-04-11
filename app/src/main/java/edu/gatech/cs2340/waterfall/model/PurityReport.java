package edu.gatech.cs2340.waterfall.model;

import android.location.Location;
import android.os.Parcel;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;

public class PurityReport extends Report {

    private final overallCondition OverallCondition;
    private final int virusPPM;
    private final int containmentPPM;

    public PurityReport(User user, Location location, overallCondition OC, int virus, int containment) {
        super(user, location);
        OverallCondition = OC;
        virusPPM = virus;
        containmentPPM = containment;
    }

    @Override
    public void writeToDatabase() {
        DatabaseReference mReportDatabase = WelcomeActivity.getPurityReportDatabase();
        mReportDatabase.child(getReportNo()).child("dateAndTime").setValue(getDate());
        mReportDatabase.child(getReportNo()).child("reporterName").setValue(getUserName());
        mReportDatabase.child(getReportNo()).child("location").setValue(getLocation());
        mReportDatabase.child(getReportNo()).child("overallCondition").setValue(OverallCondition);
        mReportDatabase.child(getReportNo()).child("virus").setValue(virusPPM);
        mReportDatabase.child(getReportNo()).child("containment").setValue(containmentPPM);
    }

    /**
     *
     * @return String representation of Purity Report
     */
    public String toString() {
        return "Purity Report: " + "virusPPM: " + virusPPM + " containmentPPM " + containmentPPM;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        PurityReport check = (PurityReport) o;
        return ((check.getUid().equals(getUid())) && (check.getDate().equals(getDate())));
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

    /**
     *
     * @return the uid
     */
    @Override
    public String getUid() {
        return super.getUid();
    }

    /**
     *
     * @return the date
     */
    @Override
    Date getDate() {
        return super.getDate();
    }
}

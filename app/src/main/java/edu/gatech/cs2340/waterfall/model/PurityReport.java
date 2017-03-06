package edu.gatech.cs2340.waterfall.model;

import android.location.Location;
import android.os.Parcel;

/**
 * Created by seanrsain on 3/6/17.
 */

public class PurityReport extends Report {
    public PurityReport(User user, Location location) {
        super(user, location);
    }

    public void writeToDatabase() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}

package edu.gatech.cs2340.waterfall.controller;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Mohit Chauhan on 3/6/2017.
 */

public interface OnGetDataListener {
    void onSuccess(DataSnapshot dataSnapshot);
    void onStart();
    void onFailure();
}

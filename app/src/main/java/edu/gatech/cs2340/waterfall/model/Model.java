package edu.gatech.cs2340.waterfall.model;

/**
 * Created by seanrsain on 2/21/17.
 */

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

import java.util.ArrayList;
import java.util.List;


public class Model {
    /** Singleton instance */
    private static Model _instance = new Model();
    public static Model getInstance() {
        if(_instance == null)
        {
            _instance = new Model();
        }
        return _instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private User currentUser;
}

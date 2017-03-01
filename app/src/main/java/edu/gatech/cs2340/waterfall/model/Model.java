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

//Singleton Model class to save the current user locally

public class Model {
    /** Singleton instance */
    private static Model _instance = new Model();

    /**
     * @return the singleton instance of the model.
     * If it is null, initialize it.
     */
    public static Model getInstance() {
        if(_instance == null)
        {
            _instance = new Model();
        }
        return _instance;
    }

    //getters and setters
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private User currentUser;
}

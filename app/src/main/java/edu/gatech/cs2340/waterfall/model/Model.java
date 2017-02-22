package edu.gatech.cs2340.waterfall.model;

/**
 * Created by seanrsain on 2/21/17.
 */

import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;

import java.util.ArrayList;
import java.util.List;


public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private User currentUser;
}

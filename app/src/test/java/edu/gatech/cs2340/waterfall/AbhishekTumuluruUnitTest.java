package edu.gatech.cs2340.waterfall;

import android.content.Intent;

import com.google.firebase.database.DatabaseReference;

import org.junit.Test;

import edu.gatech.cs2340.waterfall.controller.CreateProfile;
import edu.gatech.cs2340.waterfall.controller.GPSTracker;
import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;
import edu.gatech.cs2340.waterfall.model.Administrator;
import edu.gatech.cs2340.waterfall.model.Manager;
import edu.gatech.cs2340.waterfall.model.Model;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.Worker;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class AbhishekTumuluruUnitTest {
    @Test
    public void testModelDetails() throws Exception {
        // checking if model user is not null
        Model m = new Model();
        assertNotNull(Model.getInstance());
        User user = m.getCurrentUser();
        assertNotNull(user);
        //checking if user details are valid
        String name = user.getName();
        String email = user.getEmail();
        int zipcode = user.getZipcode();
        assertFalse(name.matches(".*\\d+.*"));
        assertTrue(zipcode > 0);
        assertTrue(email.contains("@"));

    }


}

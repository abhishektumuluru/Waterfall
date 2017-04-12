package edu.gatech.cs2340.waterfall;

import android.location.Location;

import org.junit.Test;

import edu.gatech.cs2340.waterfall.model.PurityReport;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.overallCondition;

import static org.junit.Assert.*;

/**
 * Created by mehulmohagaonkar on 4/10/17.
 */

public class MehulMohagaonkarUnitTest {

    //Testing getName method in the user class
    @Test
    public void getName_test() throws Exception {
        User aUser = new User("123abc", "Test User", "testuser@email.com", 23334, "9999999999");

        assertEquals(aUser.getName(), "Test User");
    }

    //Testing equals method in the PurityReport Class
    @Test
    public void ReportEquals_test() throws Exception {
        User auser = new User("123abc", "Test User", "testuser@email.com", 23334, "9999999999");

        User buser = new User("456def", "Another User", "anotheruser@email.com", 23234, "1234567890");

        PurityReport report1 = new PurityReport(auser, null, overallCondition.Treatable, 10, 10);
        PurityReport report3 = new PurityReport(auser, null, overallCondition.Treatable, 10, 10);

        PurityReport report2 = new PurityReport(buser, null, overallCondition.Safe, 12, 12);

        assertEquals(report1.equals(report1), true);
        assertEquals(report1.equals(null), false);
        assertEquals(report1.equals(auser), false);
        assertEquals(report3.equals(report3), true);
        assertEquals(report1.equals(report2), false);

    }
}



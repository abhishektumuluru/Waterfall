package edu.gatech.cs2340.waterfall;

import android.location.Location;

import org.junit.Test;

import edu.gatech.cs2340.waterfall.model.PurityReport;
import edu.gatech.cs2340.waterfall.model.SourceReport;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.waterType;
import edu.gatech.cs2340.waterfall.model.waterCondition;

import static org.junit.Assert.*;

/**
 * Created by vivekanandRajasekar on 4/11/17.
 */

public class VivekRajaUnitTest {
    //testing getUid() method in the user class
    @Test
    public void getUid_test() throws Exception {
        User userA = new User("303jjj", "User A", "fakeuserman123@fakemail.com", 56565, "0000000000");
        assertEquals(userA.getUid(), "303jjj");
    }

    //testing equals method in the sourceReport class
    @Test
    public void sourceReportEquals_test() throws Exception {
        //sets up different users
        User userA = new User("303jjj", "User A", "fakeuserman123@fakemail.com", 56565, "0000000000");
        User userB = new User("565lll", "User B", "fakeuserwoman123@fakemail.com", 65656, "111111111");

        Location nA = new Location("North America");
        Location sA = new Location("South America");

        waterType bottled = waterType.Bottled;
        waterType spring = waterType.Spring;

        waterCondition waste = waterCondition.Waste;
        waterCondition potable = waterCondition.Potable;

        //first check what happens for equals
        SourceReport report1 = new SourceReport(userA, nA, bottled, waste);
        SourceReport report2 = null;
        assertFalse(report1.equals(report2));

        //check for self
        SourceReport report3 = new SourceReport(userA, nA, bottled, waste);
        SourceReport report4 = reporr3;
        assertTrue(report3.equals(report4));

        //type check
        SourceReport report5 = new SourceReport(userA, nA, bottled, waste);
        PurityReport report6 = new PurityReport(auser, null, overallCondition.Treatable, 10, 10);
        assertFalse(report5.equals(report6));

        //fields check
        SourceReport uno = new SourceReport(userA, nA, bottled, waste);
        SourceReport dos = new SourceReport(userA, nA, bottled, waste);
        assertTrue(uno.equals(dos));

        uno = new SourceReport(userA, nA, bottled, waste);
        dos = new SourceReport(userB, nA, bottled, waste);
        assertFalse(uno.equals(dos));

        uno = new SourceReport(userA, nA, bottled, waste);
        dos = new SourceReport(userA, sA, bottled, waste);
        assertFalse(uno.equals(dos));

        uno = new SourceReport(userA, nA, bottled, waste);
        dos = new SourceReport(userB, nA, spring, waste);
        assertFalse(uno.equals(dos));

        uno = new SourceReport(userA, nA, bottled, waste);
        dos = new SourceReport(userB, nA, bottled, potable);
        assertFalse(uno.equals(dos));
    }
}

package edu.gatech.cs2340.waterfall;

import org.junit.Test;

import edu.gatech.cs2340.waterfall.model.Model;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.Worker;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ReshavSainUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    // Reshav Sain
    // model instance too easy
    @Test
    public void ModelTest() throws Exception {
        assertNotNull(Model.getInstance());
    }


    // added User equals method
    @Test
    public void UserEqualsTest() throws Exception {
        String type = "user";
        String uid = "34344";
        String uid1 = "34335";
        String name = "Foo Bar";
        String email = "foo@bar.com";
        int zip = 30332;
        String phoneNumber = "909900900";
        User newUser = new User(uid, name, email, zip, phoneNumber);
        User newUser1 = new User(uid, name, email, zip, phoneNumber);
        User newUser2 = new User(uid1, name, email, zip, phoneNumber);
        Worker worker = new Worker(uid1, name, email, zip, phoneNumber);
        assertTrue(newUser.equals(newUser1));
        assertFalse(newUser1.equals(newUser2));
        assertFalse(newUser2.equals(worker));
    }
}
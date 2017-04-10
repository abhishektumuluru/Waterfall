package edu.gatech.cs2340.waterfall;

import org.junit.Test;

import edu.gatech.cs2340.waterfall.model.User;

import static org.junit.Assert.*;

/**
 * Created by mehulmohagaonkar on 4/10/17.
 */

public class MehulMohagaonkarUnitTest {
    @Test
    public void getName_test() throws Exception {
        User aUser = new User("123abc", "Test User", "testuser@email.com", 23334, "9999999999");

        assertEquals(aUser.getName(), "Test User");
    }
}



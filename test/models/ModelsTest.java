package models;

import org.junit.Before;
import org.junit.Test;
import play.api.test.WithApplication;

import static junit.framework.Assert.*;
import static play.test.Helpers.*;

/**
 * @Author: Mukund Bhudia
 */
public class ModelsTest {

    @Before
    public void setUp(){
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test
    public void createAndRetrieveUser(){
        new User("bob@gmail.com", "Bob", "Secret").save();
        User bob = User.find.where().eq("email", "bob@gmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("Bob", bob.name);
    }

    @Test
    public void tryAuthenticateUser(){
        new User("bob@gmail.com", "Bob", "secret").save();

        assertNotNull(User.authenticate("bob@gmail.com", "secret"));
        assertNull(User.authenticate("bob@gmail.com", "badpassword"));
        assertNull(User.authenticate("tom@gmail.com", "secret"));
    }
}

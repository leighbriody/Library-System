/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoTests;

import business.User;
import daos.UserDao;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Osama Kheireddine
 */
public class UserDaoTest {

    private static UserDao uDao;

    public UserDaoTest() {
    }

    // Put in code to, for example, create object that will be tested (instead of
    // recreating it over and over)
    @BeforeClass
    public static void setUpClass() {
        uDao = new UserDao("librarydb_test_2");
    }

    /**
     * To test adding a user to the db with multiple params passed
     */
    @Test
    public void testSignUpUser() {
        int expResult = 1;
//        int result = uDao.signUpUser("test", "test@email", "password", "testing", "guy", 1000);
//        assertEquals(expResult, result);
    }

    /**
     * Get a user id from the username passed as a param
     */
    @Test
    public void testgetUserId() {
        String username = "test";
        int expResult = 44;
        int result = uDao.getUserId(username);
        assertEquals(expResult, result);
    }

    /**
     * To get the user based off username and password passed through
     */
    @Test
    public void testGetUserByUsernamePassword() {
        String username = "test";
        String password = "password";
        User expResult = new User(44, "testing", "guy", "test@email", "test", "password", "00");
        User result = uDao.getUserByUsernamePassword(username, password);
        assertEquals(expResult, result);
    }

    /**
     * To get the user object based off username passed through
     */
    @Test
    public void testGetUserByUsername() {
        String username = "test";
        User expResult = new User(44, "testing", "guy", "test@email", "test", "password", "00");
        User result = uDao.getUserByUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * To test editing user details based off params entered
     */
    @Test
    public void testEditUserDetails() {
        boolean expResult = true;
        boolean result = uDao.editUserDetails(44, "testing", "gue", "testing@emaiol.com", "010101");
        assertEquals(expResult, result);
    }
}

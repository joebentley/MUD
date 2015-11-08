/*
 * Copyright (c) 2015
 *
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 *
 */

package tests;

import main.com.joebentley.mud.GameDatabaseConnection;
import main.com.joebentley.mud.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

public class GameDatabaseConnectionTest {
    private static GameDatabaseConnection connection;
    private static User user;

    @BeforeClass
    public static void setUp() {
        connection = new GameDatabaseConnection();
        user = new User("test");
        user.getNewID(connection);
    }

    @Test
    public void savingAndGettingNewUser() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        connection.newUser(user, "password");

        assertTrue(connection.isUserSaved(user));
        assertTrue(connection.getUsers().containsUsername(user.getUsername()));
    }

    @Test
    public void savingUserMultipleTimes() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        connection.newUser(user, "password");
        user.setUsername("joe");
        connection.updateUserGivenByID(user.getID(), user);

        assertTrue(connection.isUserSaved(user));
        assertTrue(connection.getUsers().containsUsername(user.getUsername()));

        user.setUsername("test");
    }

    @Test
    public void verifyingPassword() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        connection.newUser(user, "password");

        assertTrue(connection.verifyPassword(user, "password"));
    }
}

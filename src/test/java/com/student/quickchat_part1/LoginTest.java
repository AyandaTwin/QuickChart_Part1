package com.student.quickchat_part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // --- USERNAME TESTS ---

    @Test
    public void testCheckUserNameCorrectlyFormatted() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNamePoorlyFormatted() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle")); 
    }

    // --- PASSWORD TESTS ---

    @Test
    public void testCheckPasswordComplexityMeetsRequirements() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityDoesNotMeetRequirements() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // --- CELL PHONE TESTS ---

    @Test
    public void testCheckCellPhoneNumberValid() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberInvalid() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("0838968976"));
    }

    // --- REGISTRATION STATUS TEST ---

    @Test
    public void testUserRegistrationSuccessful() {
        Login login = new Login();
        String expectedMessage = "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
        String actualMessage = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        assertEquals(expectedMessage, actualMessage);
    }
}
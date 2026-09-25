package com.student.quickchat_part1;

import java.util.regex.Pattern;

/**
 * The Login class handles user registration rules and authentication verifications.
 */
public class Login {

    // Instance variables to store registered user credentials
    private String registeredUsername;
    private String registeredPassword;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    // Getters and Setters
    public String getRegisteredUsername() { return registeredUsername; }
    public String getRegisteredPassword() { return registeredPassword; }
    public String getCellPhoneNumber() { return cellPhoneNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Checks if the username contains an underscore (_) and is no more than 5 characters.
     */
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    /**
     * Ensures password meets complexity requirements:
     * - At least 8 characters long
     * - Contains a capital letter
     * - Contains a number
     * - Contains a special character
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasDigit && hasSpecial;
    }

    /**
     * Checks if the SA cell phone number starts with international code (+27)
     * and contains 10 digits following the country code (+27 followed by 9 digits = 12 total chars).
     * 
     * Reference Attribution:
     * Regular Expression structure adapted from standard E.164 South African format checks.
     * Source: OWASP Validation Regex Repository (https://owasp.org/www-community/OWASP_Validation_Regex_Index)
     */
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) {
            return false;
        }
        // Regex: starts with +27 followed by exactly 9 digits (total string length 12)
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, cellNumber);
    }

    /**
     * Evaluates all registration criteria and returns the resulting status message.
     */
    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // If all conditions pass, save the details
        this.registeredUsername = username;
        this.registeredPassword = password;
        this.cellPhoneNumber = cellNumber;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    /**
     * Verifies provided login credentials against stored registration credentials.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (this.registeredUsername == null || this.registeredPassword == null) {
            return false;
        }
        return this.registeredUsername.equals(enteredUsername) && 
               this.registeredPassword.equals(enteredPassword);
    }

    /**
     * Returns appropriate login response message based on authentication status.
     */
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

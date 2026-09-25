package com.student.quickchat_part1;

/**
 * Class representing user authentication and validation logic for QuickChat.
 * Handles username formatting, password complexity, SA phone number validation,
 * and user registration/login operations.
 * 
 * @author ST10482388
 * @version 1.0
 */
public class Login {

    private String firstName;
    private String lastName;
    private String registeredUsername;
    private String registeredPassword;
    private String cellPhoneNumber;

    public Login() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRegisteredUsername() {
        return registeredUsername;
    }

    public void setRegisteredUsername(String registeredUsername) {
        this.registeredUsername = registeredUsername;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }

    public void setRegisteredPassword(String registeredPassword) {
        this.registeredPassword = registeredPassword;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    /**
     * Validates that the username contains an underscore (_) and is no more than 5 characters long.
     * 
     * @param username The username input string to evaluate
     * @return true if formatting criteria are met, false otherwise
     */
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    /**
     * Checks whether a password satisfies complexity rules:
     * - Minimum 8 characters long
     * - Contains at least one uppercase letter
     * - Contains at least one digit
     * - Contains at least one special character
     * 
     * @param password The password string to evaluate
     * @return true if all criteria are satisfied, false otherwise
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
     * Validates South African cell phone numbers in international E.164 format (+27 followed by 9 digits).
     * 
     * REFERENCE ATTRIBUTION:
     * International phone number validation structure adapted from standard E.164 specifications.
     * Source: OWASP Validation Regex Repository
     * URL: https://owasp.org/www-community/OWASP_Validation_Regex_Repository
     * 
     * @param cellNumber The phone number string to validate
     * @return true if the format matches international SA standards, false otherwise
     */
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) {
            return false;
        }
        return cellNumber.matches("^\\+27[0-9]{9}$");
    }

    /**
     * Registers a new user if username, password, and cell phone number meet validation requirements.
     * 
     * @param username Desired username
     * @param password Desired password
     * @param cellNumber User's cell phone number
     * @return A status message describing the outcome of registration
     */
    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number is incorrectly formatted or invalid. Must start with +27 followed by 9 digits.";
        }

        this.registeredUsername = username;
        this.registeredPassword = password;
        this.cellPhoneNumber = cellNumber;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    /**
     * Verifies if entered credentials match stored user details.
     * 
     * @param username Input username
     * @param password Input password
     * @return true if credentials match, false otherwise
     */
    public boolean loginUser(String username, String password) {
        if (this.registeredUsername == null || this.registeredPassword == null) {
            return false;
        }
        return this.registeredUsername.equals(username) && this.registeredPassword.equals(password);
    }

    /**
     * Returns welcome or error message based on login success.
     * 
     * @param loginSuccess Status of login attempt
     * @return Formatted status string
     */
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "") + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again";
        }
    }
}
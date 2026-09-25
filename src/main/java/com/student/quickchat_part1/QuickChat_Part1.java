package com.student.quickchat_part1;

import java.util.Scanner;

/**
 * Main console application runner for QuickChat Part 1.
 * Provides the interactive terminal loop for user registration and login verification.
 * 
 * @author ST10482388
 * @version 1.0
 */
public class QuickChat_Part1 {

    /**
     * Main entry point for the QuickChat registration and login application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginApp = new Login();

        System.out.println("=== WELCOME TO QUICKCHAT REGISTRATION ===");

        // Collect Personal Details
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        loginApp.setFirstName(firstName);

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        loginApp.setLastName(lastName);

        // Interactive Registration Loop
        boolean registrationSuccessful = false;
        while (!registrationSuccessful) {
            System.out.println("\n--- REGISTRATION ---");
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            System.out.print("Enter Cell Phone Number (+27...): ");
            String cellNumber = scanner.nextLine();

            String registrationMessage = loginApp.registerUser(username, password, cellNumber);
            System.out.println(registrationMessage);

            if (registrationMessage.contains("successfully")) {
                registrationSuccessful = true;
            }
        }

        // Interactive Login Loop
        System.out.println("\n=== WELCOME TO QUICKCHAT LOGIN ===");
        boolean loginSuccess = false;

        while (!loginSuccess) {
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

            loginSuccess = loginApp.loginUser(loginUsername, loginPassword);
            String statusMessage = loginApp.returnLoginStatus(loginSuccess);
            System.out.println(statusMessage);
        }

        scanner.close();
    }
}
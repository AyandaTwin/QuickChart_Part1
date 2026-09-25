package com.student.quickchat_part1;

import java.util.Scanner;

public class QuickChat_Part1 {

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

        // Registration Loop
        boolean registrationSuccessful = false;
        while (!registrationSuccessful) {
            System.out.println("\n--- REGISTRATION ---");
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            System.out.print("Enter Cell Phone Number (+27...): ");
            String cellNumber = scanner.nextLine();

            String statusMessage = loginApp.registerUser(username, password, cellNumber);
            System.out.println("\n" + statusMessage);

            if (statusMessage.contains("successfully added")) {
                registrationSuccessful = true;
            } else {
                System.out.println("\nPlease re-enter details according to constraints.");
            }
        }

        // Login Loop
        System.out.println("\n=== WELCOME TO QUICKCHAT LOGIN ===");
        boolean loginSuccessful = false;

        while (!loginSuccessful) {
            System.out.print("\nEnter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

            boolean authenticated = loginApp.loginUser(loginUsername, loginPassword);
            String loginMessage = loginApp.returnLoginStatus(authenticated);

            System.out.println(loginMessage);

            if (authenticated) {
                loginSuccessful = true;
            }
        }

        scanner.close();
    }
}
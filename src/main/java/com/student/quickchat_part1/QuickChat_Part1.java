package com.student.quickchat_part1;

import java.util.Random;
import java.util.Scanner;

/**
 * Main console application runner for QuickChat Part 2.
 * Handles registration, authentication, main menu navigation, and message sending.
 * 
 * @author Ayanda Sosibo (ST10482388)
 * @version 2.0
 */
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

        // --- PART 2: MAIN MENU & MESSAGE SENDING ---
        if (loginSuccess) {
            System.out.println("\nWelcome to QuickChat.");

            boolean keepRunning = true;
            int totalSentMessagesCount = 0;
            Random random = new Random();

            while (keepRunning) {
                System.out.println("\n--- MAIN MENU ---");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Please enter option (1-3): ");

                String choiceInput = scanner.nextLine();

                switch (choiceInput) {
                    case "1":
                        System.out.print("\nHow many messages would you like to enter? ");
                        int numMessages = 0;
                        try {
                            numMessages = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number. Returning to menu.");
                            break;
                        }

                        for (int i = 1; i <= numMessages; i++) {
                            System.out.println("\n--- Entering Message " + i + " of " + numMessages + " ---");

                            // Auto-generate 10-digit Message ID
                            long idNum = 1000000000L + (long) (random.nextDouble() * 9000000000L);
                            String messageID = String.valueOf(idNum);

                            // Recipient Cell Input
                            String recipient = "";
                            boolean validRecipient = false;
                            while (!validRecipient) {
                                System.out.print("Enter Recipient Cell Number (+27...): ");
                                recipient = scanner.nextLine();
                                Message tempMsg = new Message();
                                tempMsg.setRecipientCell(recipient);
                                if (tempMsg.checkRecipientCell()) {
                                    validRecipient = true;
                                    System.out.println("Cell phone number successfully captured.");
                                } else {
                                    System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                                }
                            }

                            // Message Text Input
                            String messageText = "";
                            boolean validLength = false;
                            while (!validLength) {
                                System.out.print("Enter Message text: ");
                                messageText = scanner.nextLine();
                                Message tempMsg = new Message();
                                String validationResult = tempMsg.validateMessageLength(messageText);
                                System.out.println(validationResult);

                                if (validationResult.equals("Message ready to send.")) {
                                    validLength = true;
                                }
                            }

                            // Create Message Object
                            Message msg = new Message(messageID, i, recipient, messageText);

                            // Print Generated Details
                            System.out.println("\nGenerated Message Details:");
                            System.out.println(msg.printMessages());

                            // Select Action
                            System.out.println("\nSelect Message Action:");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message to send later");
                            System.out.print("Option: ");

                            int actionChoice = 1;
                            try {
                                actionChoice = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                actionChoice = 1; // Default to send
                            }

                            String actionResponse = msg.SentMessage(actionChoice);
                            System.out.println(actionResponse);

                            if (actionChoice == 1) {
                                totalSentMessagesCount++;
                            }
                        }

                        System.out.println("\nTotal Messages Sent during this session: " + totalSentMessagesCount);
                        break;

                    case "2":
                        System.out.println("Coming Soon.");
                        break;

                    case "3":
                        System.out.println("Thank you for using QuickChat. Goodbye!");
                        keepRunning = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please enter 1, 2, or 3.");
                        break;
                }
            }
        }

        scanner.close();
    }
}
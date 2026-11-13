package com.student.quickchat_part1;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class representing a single message payload, formatting, validation,
 * and JSON storage handling for QuickChat Part 2.
 * 
 * @author Ayanda Sosibo (ST10482388)
 * @version 2.0
 */
public class Message {

    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private String actionStatus; // "Sent", "Disregarded", or "Stored"

    // Constructor
    public Message(String messageID, int messageNumber, String recipientCell, String messageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // Default Constructor
    public Message() {
    }

    // --- GETTERS & SETTERS ---
    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }

    public int getMessageNumber() { return messageNumber; }
    public void setMessageNumber(int messageNumber) { this.messageNumber = messageNumber; }

    public String getRecipientCell() { return recipientCell; }
    public void setRecipientCell(String recipientCell) { this.recipientCell = recipientCell; }

    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }

    public String getMessageHash() { return messageHash; }

    public String getActionStatus() { return actionStatus; }
    public void setActionStatus(String actionStatus) { this.actionStatus = actionStatus; }

    // --- RUBRIC REQUIRED METHODS ---

    /**
     * Checks if the Message ID is not more than 10 characters long.
     */
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    /**
     * Validates that the recipient cell number contains an international code (+27)
     * and is no more than 12 characters total (code + 9 digits).
     */
    public boolean checkRecipientCell() {
        if (recipientCell == null) {
            return false;
        }
        return recipientCell.matches("^\\+27[0-9]{9}$");
    }

    /**
     * Creates and returns the Message Hash in all uppercase.
     * Format: 00:0:FIRSTLAST (First 2 digits of ID : Message Number : First and Last word of message)
     */
    public String createMessageHash() {
        if (messageID == null || messageText == null || messageID.length() < 2) {
            return "";
        }

        // Get first two digits of Message ID
        String idPrefix = messageID.substring(0, 2);

        // Extract first and last words from message
        String trimmed = messageText.trim();
        String[] words = trimmed.split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        // Combine into all-uppercase hash
        String rawHash = idPrefix + ":" + messageNumber + ":" + firstWord + lastWord;
        return rawHash.toUpperCase();
    }

    /**
     * Validates message text length (must not exceed 250 characters).
     */
    public String validateMessageLength(String text) {
        if (text == null) {
            return "Please enter a message of less than 250 characters.";
        }
        if (text.length() > 250) {
            int overLimit = text.length() - 250;
            return "Message exceeds 250 characters by " + overLimit + " ; please reduce the size.";
        }
        return "Message ready to send.";
    }

    /**
     * Handles message action selection (Send, Disregard, or Store).
     * Option 1: Send
     * Option 2: Disregard
     * Option 3: Store
     */
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                this.actionStatus = "Sent";
                return "Message successfully sent.";
            case 2:
                this.actionStatus = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                this.actionStatus = "Stored";
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    /**
     * Formats and returns all details of the message in rubric order:
     * Message ID, Message Hash, Recipient, Message.
     */
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageText;
    }

    /**
     * Stores message details in a local JSON file (messages.json).
     * REFERENCE ATTRIBUTION:
     * Basic JSON file writing implemented using Standard Java File I/O.
     */
    public void storeMessage() {
        String jsonPayload = String.format(
            "{\n  \"messageID\": \"%s\",\n  \"messageNumber\": %d,\n  \"recipient\": \"%s\",\n  \"messageHash\": \"%s\",\n  \"message\": \"%s\"\n}\n",
            messageID, messageNumber, recipientCell, messageHash, messageText.replace("\"", "\\\"")
        );

        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write(jsonPayload);
        } catch (IOException e) {
            System.err.println("Error saving message to JSON: " + e.getMessage());
        }
    }
}
package com.student.quickchat_part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a single message payload, formatting, validation,
 * and JSON storage handling for QuickChat Part 2 and Part 3.
 * 
 * @author Ayanda Sosibo (ST10482388)
 * @version 3.0
 */
public class Message {

    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private String actionStatus; // "Sent", "Disregarded", or "Stored"

    // --- PART 3: Parallel Lists & Collections ---
    private static final List<String> sentMessages = new ArrayList<>();
    private static final List<String> disregardedMessages = new ArrayList<>();
    private static final List<String> storedMessages = new ArrayList<>();
    private static final List<String> messageHashes = new ArrayList<>();
    private static final List<String> messageIDs = new ArrayList<>();
    private static final List<String> sentRecipients = new ArrayList<>();
    private static final List<String> storedRecipients = new ArrayList<>();
    private static final List<String> storedSenders = new ArrayList<>();

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

    public static List<String> getSentMessages() { return sentMessages; }
    public static List<String> getStoredMessages() { return storedMessages; }
    public static List<String> getMessageIDs() { return messageIDs; }
    public static List<String> getMessageHashes() { return messageHashes; }

    // --- RUBRIC REQUIRED METHODS (PART 2) ---

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
     */
    public String createMessageHash() {
        if (messageID == null || messageText == null || messageID.length() < 2) {
            return "";
        }

        String idPrefix = messageID.substring(0, 2);
        String trimmed = messageText.trim();
        String[] words = trimmed.split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

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
     * Formats and returns all details of the message in rubric order.
     */
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageText;
    }

    /**
     * Stores message details in a local JSON file (messages.json).
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

    // --- PART 3 METHODS & ARRAY POPULATION ---

    /**
     * Reads stored messages from 'messages.json' into arrays/lists[cite: 28].
     * Attribution: File reading logic adapted from Oracle Java Documentation (2024).
     */
    public static void loadStoredMessagesFromJson(String filePath) {
        storedMessages.clear();
        storedRecipients.clear();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("message") || line.contains("text")) {
                    storedMessages.add(line.trim());
                } else if (line.contains("recipient") || line.contains("cell")) {
                    storedRecipients.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }

    public static void addSentMessageRecord(String id, String recipient, String message, String hash) {
        messageIDs.add(id);
        sentRecipients.add(recipient);
        sentMessages.add(message);
        messageHashes.add(hash);
    }

    public static void addStoredMessageRecord(String sender, String recipient, String message, String hash, String id) {
        storedSenders.add(sender);
        storedRecipients.add(recipient);
        storedMessages.add(message);
        messageHashes.add(hash);
        messageIDs.add(id);
    }

    /**
     * 2a. Display the sender and recipient of all stored messages[cite: 28].
     */
    public static String displayStoredSendersAndRecipients() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < storedMessages.size(); i++) {
            String sender = (i < storedSenders.size()) ? storedSenders.get(i) : "Unknown";
            String recipient = (i < storedRecipients.size()) ? storedRecipients.get(i) : "Unknown";
            sb.append("Sender: ").append(sender)
              .append(" | Recipient: ").append(recipient).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * 2b. Display the longest stored (or sent) message[cite: 28].
     */
    public static String displayLongestMessage() {
        String longest = "";
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        for (String msg : sentMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest.isEmpty() ? "No messages available." : longest;
    }

    /**
     * 2c. Search for a message ID and display the corresponding recipient and message[cite: 28].
     */
    public static String searchByMessageID(String targetID) {
        for (int i = 0; i < sentMessages.size(); i++) {
            if (i < messageIDs.size() && messageIDs.get(i).equals(targetID)) {
                return "Recipient: " + sentRecipients.get(i) + " | Message: " + sentMessages.get(i);
            }
        }
        return "Message ID not found.";
    }

    /**
     * 2d. Search for all messages sent or stored regarding a particular recipient[cite: 28].
     */
    public static String searchByRecipient(String targetRecipient) {
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < sentRecipients.size(); i++) {
            if (sentRecipients.get(i).equals(targetRecipient)) {
                results.append(sentMessages.get(i)).append("\n");
            }
        }
        for (int i = 0; i < storedRecipients.size(); i++) {
            if (storedRecipients.get(i).equals(targetRecipient)) {
                results.append(storedMessages.get(i)).append("\n");
            }
        }
        String output = results.toString().trim();
        return output.isEmpty() ? "No messages found for recipient: " + targetRecipient : output;
    }

    /**
     * 2e. Delete a message using the message hash[cite: 28].
     */
    public static String deleteMessageByHash(String targetHash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equalsIgnoreCase(targetHash)) {
                String deletedMsg = "";
                if (i < storedMessages.size()) {
                    deletedMsg = storedMessages.remove(i);
                    if (i < storedRecipients.size()) storedRecipients.remove(i);
                    if (i < storedSenders.size()) storedSenders.remove(i);
                } else if (i < sentMessages.size()) {
                    deletedMsg = sentMessages.remove(i);
                    if (i < sentRecipients.size()) sentRecipients.remove(i);
                }
                messageHashes.remove(i);
                if (i < messageIDs.size()) messageIDs.remove(i);
                return "Message \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    /**
     * 2f. Display a report that lists the full details of all sent/stored messages[cite: 28].
     */
    public static String displayReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < sentMessages.size(); i++) {
            String hash = (i < messageHashes.size()) ? messageHashes.get(i) : "N/A";
            String recipient = (i < sentRecipients.size()) ? sentRecipients.get(i) : "N/A";
            report.append("Message Hash: ").append(hash)
                  .append(" | Recipient: ").append(recipient)
                  .append(" | Message: ").append(sentMessages.get(i)).append("\n");
        }
        return report.toString().trim();
    }
}
package com.student.quickchat_part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Message class managing message creation, validation, hashing, 
 * JSON persistence, array population, and reporting for PROG5121 Part 3.
 * 
 * @author Ayanda Sosibo (ST10482388)
 */
public class Message {

    // Parallel Arrays / Lists for Part 3 Requirements[cite: 28]
    private static List<String> sentMessages = new ArrayList<>();
    private static List<String> disregardedMessages = new ArrayList<>();
    private static List<String> storedMessages = new ArrayList<>();
    private static List<String> messageHashes = new ArrayList<>();
    private static List<String> messageIDs = new ArrayList<>();
    private static List<String> storedRecipients = new ArrayList<>();

    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status;

    public Message(String messageID, String recipient, String messageText, String messageHash, String status) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = messageHash;
        this.status = status;
    }

    public Message() {}

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

    public static List<String> getStoredMessages() { return storedMessages; }
    public static List<String> getSentMessages() { return sentMessages; }
    public static List<String> getMessageIDs() { return messageIDs; }
    public static List<String> getMessageHashes() { return messageHashes; }
}
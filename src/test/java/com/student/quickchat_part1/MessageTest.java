package com.student.quickchat_part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for Message class functionality in QuickChat Part 2.
 * Tests message length validation, recipient cell formatting, hash generation,
 * and action selection returns according to assessment rubric specifications.
 * 
 * @author Ayanda Sosibo (ST10482388)
 * @version 2.0
 */
public class MessageTest {

    // --- MESSAGE LENGTH TESTS ---

    @Test
    public void testValidateMessageLengthSuccess() {
        Message msg = new Message();
        String validText = "Hi Mike, can you join us for dinner tonight?";
        String expected = "Message ready to send.";
        String actual = msg.validateMessageLength(validText);
        assertEquals(expected, actual);
    }

    @Test
    public void testValidateMessageLengthFailure() {
        Message msg = new Message();
        // Create string exceeding 250 characters
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            longText.append("0123456789");
        } // 260 characters total (exceeds by 10)

        String expected = "Message exceeds 250 characters by 10 ; please reduce the size.";
        String actual = msg.validateMessageLength(longText.toString());
        assertEquals(expected, actual);
    }

    // --- RECIPIENT CELL PHONE TESTS ---

    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message();
        msg.setRecipientCell("+27718693002");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message();
        msg.setRecipientCell("08575975889"); // Missing +27 country code
        assertFalse(msg.checkRecipientCell());
    }

    // --- MESSAGE HASH TEST ---

    @Test
    public void testCreateMessageHash() {
        // Test Case 1: ID starting with 00 (or any 2 digits), message number 0 or 1
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String expectedHash = "00:0:HITONIGHT?";
        String actualHash = msg.createMessageHash();
        assertEquals(expectedHash, actualHash);
    }

    // --- MESSAGE ACTION TESTS ---

    @Test
    public void testSentMessageSend() {
        Message msg = new Message();
        String expected = "Message successfully sent.";
        String actual = msg.SentMessage(1);
        assertEquals(expected, actual);
        assertEquals("Sent", msg.getActionStatus());
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message();
        String expected = "Press 0 to delete the message.";
        String actual = msg.SentMessage(2);
        assertEquals(expected, actual);
        assertEquals("Disregarded", msg.getActionStatus());
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message("1234567890", 1, "+27718693002", "Test Store Message");
        String expected = "Message successfully stored.";
        String actual = msg.SentMessage(3);
        assertEquals(expected, actual);
        assertEquals("Stored", msg.getActionStatus());
    }
}
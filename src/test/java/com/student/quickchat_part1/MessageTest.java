package com.student.quickchat_part1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test suite for Message class functionality in QuickChat Part 2 and Part 3.
 *
 * @author Ayanda Sosibo (ST10482388)
 * @version 3.0
 */
public class MessageTest {

    // --- PART 3 TEST DATA ---
    private static final String MSG_1_RECIPIENT = "+27834557896";
    private static final String MSG_1_TEXT = "Did you get the cake?";

    private static final String MSG_2_RECIPIENT = "+27838884567";
    private static final String MSG_2_TEXT = "Where are you? You are late! I have asked you to be on time.";
    private static final String MSG_2_HASH = "00:2:WHERETIME";

    private static final String MSG_4_DEV = "0838884567";
    private static final String MSG_4_TEXT = "It is dinner time!";
    private static final String MSG_4_ID = "0838884567";
    private static final String MSG_4_HASH = "00:4:ITTIME";

    private static final String MSG_5_RECIPIENT = "+27838884567";
    private static final String MSG_5_TEXT = "Ok, I am leaving without you.";

    @BeforeEach
    void setUp() throws Exception {
        clearStaticLists();

        Message.addSentMessageRecord(MSG_4_ID, MSG_1_RECIPIENT, MSG_1_TEXT, "00:1:DIDCAKE?");
        Message.addSentMessageRecord(MSG_4_ID, MSG_4_DEV, MSG_4_TEXT, MSG_4_HASH);

        Message.addStoredMessageRecord(MSG_4_DEV, MSG_2_RECIPIENT, MSG_2_TEXT, MSG_2_HASH, "1000000002");
        Message.addStoredMessageRecord(MSG_4_DEV, MSG_5_RECIPIENT, MSG_5_TEXT, "00:5:OKYOU.", "1000000005");
    }

    private void clearStaticLists() throws Exception {
        String[] listFields = {
            "sentMessages", "disregardedMessages", "storedMessages",
            "messageHashes", "messageIDs", "sentRecipients",
            "storedRecipients", "storedSenders"
        };
        for (String name : listFields) {
            Field field = Message.class.getDeclaredField(name);
            field.setAccessible(true);
            ((List<?>) field.get(null)).clear();
        }
    }

    // --- PART 2: MESSAGE LENGTH TESTS ---

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
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            longText.append("0123456789");
        } // 260 characters total (exceeds by 10)

        String expected = "Message exceeds 250 characters by 10 ; please reduce the size.";
        String actual = msg.validateMessageLength(longText.toString());
        assertEquals(expected, actual);
    }

    // --- PART 2: RECIPIENT CELL PHONE TESTS ---

    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message();
        msg.setRecipientCell("+27718693002");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message();
        msg.setRecipientCell("08575975889");
        assertFalse(msg.checkRecipientCell());
    }

    // --- PART 2: MESSAGE HASH TEST ---

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String expectedHash = "00:0:HITONIGHT?";
        String actualHash = msg.createMessageHash();
        assertEquals(expectedHash, actualHash);
    }

    // --- PART 2: MESSAGE ACTION TESTS ---

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

    // --- PART 3: ARRAY POPULATION & JSON FILE READING ---

    @Test
    void testSentMessagesArrayPopulated() {
        List<String> sent = Message.getSentMessages();
        assertTrue(sent.contains(MSG_1_TEXT));
        assertTrue(sent.contains(MSG_4_TEXT));
    }

    @Test
    void testLoadStoredMessagesFromJson() throws IOException {
        Path tempJson = Files.createTempFile("messages_test", ".json");
        String sampleJson = "{\n"
                + "  \"recipient\": \"" + MSG_2_RECIPIENT + "\",\n"
                + "  \"message\": \"" + MSG_2_TEXT + "\"\n"
                + "}\n";
        Files.writeString(tempJson, sampleJson);

        Message.loadStoredMessagesFromJson(tempJson.toString());
        assertTrue(Message.getStoredMessages().size() >= 1);

        Files.deleteIfExists(tempJson);
    }

    // --- PART 3: LONGEST MESSAGE, SEARCH, DELETE, AND REPORT ---

    @Test
    void testDisplayLongestMessage() {
        assertEquals(MSG_2_TEXT, Message.displayLongestMessage());
    }

    @Test
    void testSearchByMessageID() {
        String actual = Message.searchByMessageID(MSG_4_ID);
        assertTrue(actual.contains(MSG_1_TEXT) || actual.contains(MSG_4_TEXT));
    }

    @Test
    void testSearchByRecipient() {
        String actual = Message.searchByRecipient(MSG_2_RECIPIENT);
        assertTrue(actual.contains(MSG_2_TEXT));
        assertTrue(actual.contains(MSG_5_TEXT));
    }

    @Test
    void testDeleteMessageByHash() {
        String actual = Message.deleteMessageByHash(MSG_4_HASH);
        assertEquals("Message \"" + MSG_4_TEXT + "\" successfully deleted.", actual);
    }

    @Test
    void testDisplayReport() {
        String report = Message.displayReport();
        assertTrue(report.contains(MSG_4_HASH));
        assertTrue(report.contains(MSG_4_DEV));
        assertTrue(report.contains(MSG_4_TEXT));
    }
}
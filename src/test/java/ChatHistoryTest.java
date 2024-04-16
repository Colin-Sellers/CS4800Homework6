import ChatApp.ChatHistory;
import ChatApp.Message;
import ChatApp.User;
import ChatApp.ChatServer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChatHistoryTest
{
    User test = new User("Test");

    User recieveUser = new User("Test2");
    ChatServer testServer = new ChatServer("TestServer", test);
    Message testMessageSent = new Message("Test", test, testServer);
    Message testMessageReceive = new Message("TestReceive", recieveUser, testServer);

    ChatHistory historyTest = new ChatHistory();

    @Test
    public void addAndGetLastMessageSentTest()
    {
        // Stored in hashmap, unique key based on timestamp and server it was sent to
        historyTest.addMessageSent(testMessageSent);
        Message expected = testMessageSent;
        Message actual = historyTest.getLastMessageSent();
        assertEquals(expected, actual);
    }

    @Test
    public void changeMessageTest()
    {
        historyTest.addMessageSent(testMessageSent);
        historyTest.changeLastMessageSent("ChangedTest");
        Message changedMessage = historyTest.getLastMessageSent();
        String expected = "ChangedTest\n(Edit)";
        String actual = changedMessage.getMessageContent();
        assertEquals(expected, actual);
    }

    @Test
    public void undoMessageTest()
    {
        historyTest.addMessageSent(testMessageSent);
        historyTest.changeLastMessageSent("ChangedTest");
        historyTest.undoMessage();
        Message message = historyTest.getLastMessageSent();
        String expected = "Test";
        String actual = message.getMessageContent();
        assertEquals(expected, actual);
    }

}

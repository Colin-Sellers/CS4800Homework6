import ChatApp.ChatHistory;
import ChatApp.Message;
import ChatApp.User;
import ChatApp.ChatServer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChatHistoryTest
{
    User test = new User("Test");
    ChatServer testServer = new ChatServer("TestServer", test);
    Message testMessageSent = new Message("Test", test, testServer);

    ChatHistory historyTest = new ChatHistory();

    @Test
    public void addAndGetLastMessageSentTest()
    {
        historyTest.addMessageSent(testMessageSent);
        Message expected = testMessageSent;
        Message actual = historyTest.getLastMessageSent();
        assertEquals(expected, actual);
    }


}

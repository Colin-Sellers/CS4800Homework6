import ChatApp.ChatHistory;
import ChatApp.ChatServer;
import ChatApp.Message;
import ChatApp.User;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchByUserTest
{
    User test = new User("Test");

    User recieveUser = new User("Test2");
    ChatServer testServer = new ChatServer("TestServer", test);
    Message testMessageReceive = new Message("TestReceive", recieveUser, testServer);

    ChatHistory historyTest = new ChatHistory();
    @Test
    public void iteratorHasNextTest()
    {
        historyTest.addMessageReceived(testMessageReceive);
        Iterator<Message> iterator = historyTest.displayUsersMessages(recieveUser);
        Boolean expected = true;
        Boolean actual = iterator.hasNext();
        assertEquals(expected, actual);
    }

    @Test
    public void iteratorNextTest()
    {
        historyTest.addMessageReceived(testMessageReceive);
        Iterator<Message> iterator = historyTest.displayUsersMessages(recieveUser);
        Message thisMessage = iterator.next();
        String expected = "TestReceive";
        String actual = thisMessage.getMessageContent();
        assertEquals(expected, actual);
    }
}

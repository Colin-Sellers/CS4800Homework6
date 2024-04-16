import ChatApp.ChatHistory;
import ChatApp.Message;
import ChatApp.User;
import ChatApp.ChatServer;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest
{
    User test = new User("TestUser");
    User test2 = new User("TestUser2");
    ChatServer testServer = new ChatServer("TestServer", test);
    @Test
    public void getUserNameTest()
    {
        String expected = "TestUser";
        String actual = test.getUserName();
        assertEquals(expected, actual);
    }

    @Test
    public void sendMessageTest()
    {
        testServer.registerUser(test2);
        test.sendMessage("TestMessage", testServer);
        Iterator<Message> iterator = test2.iterator(test);
        Message thisMessage = iterator.next();
        String expected = "TestMessage";
        String actual = thisMessage.getMessageContent();
        assertEquals(expected, actual);
    }

    @Test
    public void undoMessageTest()
    {
        test.sendMessage("TestMessage2", testServer);
        test.changeMessage("ChangeMessage2");
        String expected = "TestUser undid their message.";
        String actual = test.undoMessage();
        assertEquals(expected, actual);
    }
}

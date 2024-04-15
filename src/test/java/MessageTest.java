import ChatApp.Message;
import ChatApp.User;
import ChatApp.ChatServer;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class MessageTest
{
    User testUser = new User("Test");
    ChatServer testServer = new ChatServer("TestServer", testUser);
    // Message must be connected to a user and a server
    Message testMessage = new Message("Test Message", testUser, testServer);

    LocalDateTime time = LocalDateTime.now();
    DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
    String timeStamp = timeStampFormat.format(time);

    @Test
    public void getMessageTest()
    {
        String expected = "From: Test in TestServer\nAt: " + timeStamp + "\nTest Message";
        String actual = testMessage.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void getServerTest()
    {
        ChatServer expected = testServer;
        ChatServer actual = testMessage.getServer();
        assertEquals(expected, actual);
    }

    @Test
    public void getTimeStampTest()
    {
        String expected = timeStamp;
        String actual = testMessage.getTimeStamp();
        assertEquals(expected, actual);
    }

    @Test
    public void addToMessageTest()
    {
        testMessage.addToMessage("Test");
        String expected = "From: Test in TestServer\nAt: " + timeStamp + "\nTest MessageTest";
        String actual = testMessage.getMessage();
        assertEquals(expected, actual);
    }
}

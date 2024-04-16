import ChatApp.ChatHistory;
import ChatApp.Message;
import ChatApp.User;
import ChatApp.ChatServer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ChatServerTest
{
    User testUser = new User("Test1");
    User testUser2 = new User("Test2");
    ChatServer testServer = new ChatServer("TestServer", testUser);

    @Test
    public void getServerNameTest()
    {
        String expected = "TestServer";
        String actual = testServer.getServerName();
        assertEquals(expected, actual);
    }

    @Test
    public void sendMessageTest()
    {
        testServer.registerUser(testUser2);
        String expected = "Test1 to TestServer Message Sent!\n";
        String actual = testUser.sendMessage("Test", testServer);
        assertEquals(expected, actual);
    }

    @Test
    public void sendMessageBlockedTest()
    {
        testServer.blockUser("Test2");
        String expected = "Test2 to TestServer -Message Blocked-\n";
        String actual = testUser2.sendMessage("Test", testServer);
        assertEquals(expected, actual);
    }

}

import ChatApp.MessageMemento;
import ChatApp.ChatHistory;
import ChatApp.Message;
import ChatApp.User;
import ChatApp.ChatServer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class MessageMementoTest
{
    User test = new User("Test");
    ChatServer testServer = new ChatServer("TestServer", test);
    Message testMessage1 = new Message("Test", test, testServer);
    Message testMessage2 = new Message("Test2", test, testServer);
    ChatHistory history = new ChatHistory();


    @Test
    public void getAndSetStateTest()
    {

    }
}

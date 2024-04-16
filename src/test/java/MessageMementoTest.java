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

    ChatHistory historyTest = new ChatHistory();



    @Test
    public void setAndGetStateTest()
    {
        historyTest.addMessageSent(testMessage1); // Memento set to this message
        historyTest.changeLastMessageSent("ChangeTest"); // Memento Updated to last message, Change Message
        historyTest.undoMessage(); // Undo message, back to "Test"
        Message changedMessage = historyTest.getLastMessageSent();
        String expected = "Test";
        String actual = changedMessage.getMessageContent();
        assertEquals(expected, actual);
    }
}

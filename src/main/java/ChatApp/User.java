package ChatApp;

import java.util.Iterator;

public class User implements IterableByUser
{
    private final String userName;
    private ChatHistory history = new ChatHistory();


    public User(String userName)
    {
        this.userName = userName;
    }

    public String sendMessage(String message, ChatServer server)
    {
        // Send to Mediator + Update Memento
        // Create message
        Message newMessage = new Message(message, this, server);
        // Send to Mediator
        String outcome = server.sendMessage(userName, newMessage);
        if (!outcome.equals("Message Sent!"))
        {
            newMessage.addToMessage("\n" + outcome);
        }
        // Update Memento & history
        history.addMessageSent(newMessage);

        // Return OutCome
        return userName + " to " + server.getServerName() + " " + outcome + "\n";
    }

    public void receiveMessage(Message message)
    {
        // Add to History
        history.addMessageReceived(message);
    }

    public void changeMessage(String changedMessage)
    {
        history.changeLastMessageSent(changedMessage); // Change Message
        history.getLastMessageSent().getServer().updateThisMessage(history.getLastMessageSent()); // Update all other users of edit
    }

    public String undoMessage()
    {
        // Retrieve from Memento, Request Undo of Other's message to memento Through Mediator
        if (history.getLastMessageSent() == null)
        {
            return "Error, no message to undo!";
        }
        // Undo Message for message Owner
        history.undoMessage();
        // Send Change to other Users in Server
        Message thisMessage = history.getLastMessageSent();
        ChatServer server = thisMessage.getServer();
        server.updateThisMessage(thisMessage);
        return userName + " undid their message.";
    }


    public void receiveUndo(String timestamp, Message message)
    {
        history.changeMessageReceived(timestamp, message);
    }

    public void displayAllMessages()
    {
        System.out.println(userName + "'s Messages:\n");
        history.displayAllMessages();
        System.out.println("\nEnd of Messages\n");
    }

    public void displayMessagesByUser(User userToSearchFor)
    {
        Iterator<Message> iterator = iterator(userToSearchFor);
        System.out.println("Finding messages from " + userToSearchFor.getUserName() + "\n");
        while(iterator.hasNext())
        {
            System.out.println(iterator.next().getMessage() + "\n");
        }
        System.out.println("Found all messages from " + userToSearchFor.getUserName());
    }

    public Iterator iterator(User userToSearchFor)
    {
        return this.history.displayUsersMessages(userToSearchFor);
    }

    public String getUserName()
    {
        return userName;
    }
}

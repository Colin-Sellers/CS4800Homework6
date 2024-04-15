package ChatApp;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class ChatHistory
{
    // Linked Hash Map Since Order Matters (Time Messages Were Sent)
    private LinkedHashMap<String, Message> history = new LinkedHashMap<String, Message>();
    private String lastMessageSent = null;
    private MessageMemento mementoOfLastMessage;

    public void addMessageSent(Message message)
    {
        // If two messages are sent at exactly the same time, may overwrite
        // Make timeStamp unique to ensure this does not happen
        String timeStamp = makeTimeStampUnique(message);
        history.put(timeStamp, message);
        lastMessageSent = timeStamp;
        mementoOfLastMessage = new MessageMemento(message);
    }
    public void addMessageReceived(Message message)
    {
        String timeStamp = makeTimeStampUnique(message);
        history.put(timeStamp, message);
    }

    private String makeTimeStampUnique(Message message)
    {
        String timeStamp = message.getTimeStamp();
        timeStamp += message.getServer().getServerName();
        if(history.get(timeStamp) != null)
        {
            // Message at time stamp already exists, loop until unique timestamp is created
            while (history.get(timeStamp) != null)
            {
                int uniqueNum = 1;
                timeStamp += uniqueNum;
            }
        }
        return timeStamp;
    }

    public void changeLastMessageSent(String newMessage)
    {
        mementoOfLastMessage.setState(history.get(lastMessageSent));
        Message lastMessage = getLastMessageSent();
        lastMessage.changeMessage(newMessage + "\n(Edit)");
    }
    public void changeMessageReceived(String timestamp, Message message)
    {
        // To show message was changed for other users
        Message thisMessage = history.get(timestamp);
        thisMessage.changeMessage(message.getMessageContent());
    }
    public void undoMessage()
    {
        Message thisMessage = history.get(lastMessageSent);
        mementoOfLastMessage.getState(thisMessage);
    }

    public Message getLastMessageSent()
    {
        if(lastMessageSent == null)
        {
            return null; // In case user attempts to undo immediately before sending anything
        }
        return history.get(lastMessageSent);
    }

    public void displayAllMessages()
    {
        if (history.isEmpty())
        {
            System.out.println("Error, no messages to display!");
        }
        else
        {
            for(Map.Entry<String, Message> message : history.entrySet())
            {
                System.out.println(message.getValue().getMessage() + "\n");
            }
        }
    }

    public Iterator displayUsersMessages(User userToSearchFor)
    {
        return new SearchMessagesByUser(userToSearchFor);
    }

    private class SearchMessagesByUser implements Iterator
    {
        private ArrayList<Message> messages;
        private int index = 0;

        private User userToSearchFor;

        public SearchMessagesByUser(User userToSearchFor)
        {
            this.userToSearchFor = userToSearchFor;
            messages = new ArrayList<>();
            for (Map.Entry<String, Message> message : history.entrySet())
            {
                messages.add(message.getValue()); // This user's list of messages
            }

        }

        @Override
        public boolean hasNext()
        {
            while (index < messages.size())
            {
                Message message = messages.get(index);
                if (message.getSenderName().equals(userToSearchFor.getUserName()))
                {
                    return true;
                }
                index++;
            }
            return false;
        }

        @Override
        public Message next()
        {
            if (hasNext())
            {
                Message message = messages.get(index++);
                return message;
            }

            return null;
        }
    }

}

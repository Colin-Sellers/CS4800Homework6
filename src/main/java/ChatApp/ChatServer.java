package ChatApp;
import java.util.HashMap;
import java.util.Map;

// Mediator
public class ChatServer
{
    private String serverName;
    // Key = Username, User = User Object
    private HashMap<String, User> users = new HashMap<String, User>();
    private HashMap<String, User> blockedUsers = new HashMap<String, User>();

    public ChatServer(String serverName, User creator)
    {
        this.serverName = serverName;
        users.put(creator.getUserName(), creator);
    }

    // Obtain user from list of users
    public void registerUser(User newUser)
    {
        users.put(newUser.getUserName(), newUser);
        System.out.println("User " + newUser.getUserName() +
                " was successfully added to " + serverName + ".");
    }

    public void unregisterUser(String userName)
    {
        try {
            users.remove(userName);
            System.out.println("User " + userName +
                    " was successfully removed from " + serverName + ".");
        }
        catch (Exception e)
        {
            System.out.println("Error, user does not exist in server!");
        }
    }

    // Blocked users can receive messages, but cannot send messages
    public void blockUser(String userName)
    {
        if (users.get(userName) != null)
        {
            // User exists, add to blocked list
            blockedUsers.put(userName, users.get(userName));
        }
        else
        {
            System.out.println("Error, cannot block user that is not in server!");
        }
    }

    public String sendMessage(String fromUser, Message message)
    {
        // Request from User, Send to All Users
        // Blocked Users CANNOT send messages
        if (blockedUsers.get(fromUser) != null)
        {
            System.out.println("Error, " + fromUser + " is blocked from sending messages in " + serverName);
            return "-Message Blocked-";
        }
        else if (users.get(fromUser) == null)
        {
            System.out.println("Error, " + fromUser + " is not apart of " + serverName);
            return "-Message Blocked-";
        }
        else
        {
            // Not blocked, iterate through users to receive message
            for(Map.Entry<String, User> user : users.entrySet())
            {
                if (user.getKey().equals(fromUser))
                {
                    // Came from this user, skip
                    continue;
                }
                User thisUser = user.getValue();
                thisUser.receiveMessage(message);
            }
            return "Message Sent!";
        }
    }

    public void updateThisMessage(Message message)
    {
        for(Map.Entry<String, User> user : users.entrySet())
        {
            User thisuser = user.getValue();
            thisuser.receiveUndo(message.getInitialTimeStamp() + serverName, message);
        }

    }

    public void changeServerName(String serverName)
    {
        this.serverName = serverName;
    }

    public String getServerName()
    {
        return serverName;
    }
}

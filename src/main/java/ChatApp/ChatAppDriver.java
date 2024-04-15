package ChatApp;
import java.util.concurrent.TimeUnit;

public class ChatAppDriver
{
    public static void main(String[] args) throws InterruptedException
    {
        // Create some Users
        // Chat Server has Users, Does Not Own Users
        User user1 = new User("Frank");
        User user2 = new User("Bob");
        User user3 = new User("Alice");
        User user4 = new User("Eva");

        // Suppose Alice, Bob and Frank are part of a Server
        ChatServer server1 = new ChatServer("Cool Server", user3);
        server1.registerUser(user2);
        server1.registerUser(user1);

        // Frank is a blocked member of this server
        server1.blockUser("Frank");

        // Each tries to send a message
        System.out.println(user1.sendMessage("Hi Guys!", server1)); // Blocked Member, reject
        TimeUnit.SECONDS.sleep(1);
        System.out.println(user2.sendMessage("Hello!", server1));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(user3.sendMessage("Hiya!", server1));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(user4.sendMessage("Hello?", server1)); // Not part of the server, reject
        TimeUnit.SECONDS.sleep(1);

        // Display messages
        user1.displayAllMessages(); // Shows Message they attempted to send
        System.out.println("==================================");
        user2.displayAllMessages();
        System.out.println("==================================");
        user3.displayAllMessages();
        System.out.println("==================================");
        user4.displayAllMessages(); // Did not receive any messages
        System.out.println("==================================");

        // Alice sends and edits a message but decides to undo
        System.out.println("\n~~Undo Demo~~\n");
        System.out.println(user3.sendMessage("Where's Frank?", server1));
        user2.displayAllMessages();
        System.out.println("==================================");
        user3.displayAllMessages();
        System.out.println("==================================");

        user3.changeMessage("Where's Eva?");
        user2.displayAllMessages();
        System.out.println("==================================");
        user3.displayAllMessages();
        System.out.println("==================================");

        System.out.println(user3.undoMessage());

        user2.displayAllMessages();
        System.out.println("==================================");
        user3.displayAllMessages();
        System.out.println("==================================\n~~End Undo Demo~~");

        // Unregister Frank, register Eva
        // Create new server for Frank and Eva
        server1.unregisterUser("Frank");
        server1.registerUser(user4);
        ChatServer server2 = new ChatServer("Even Cooler Server", user1);
        server2.registerUser(user4); // Eva part of two servers

        // Multi-Server Test

        System.out.println(user1.sendMessage("I made a cooler server", server1));
        TimeUnit.SECONDS.sleep(1);

        // Two users part of the same server send a message at the same time, however system will not overwrite
        // All users part of server1 message key will be synced
        System.out.println(user3.sendMessage("I like This Server", server1));
        System.out.println(user4.sendMessage("Hi guys!", server1));

        TimeUnit.SECONDS.sleep(2);

        // Two users send a message at the same time
        // Eva will receive both messages, however system will not overwrite message as they are on different servers
        System.out.println(user1.sendMessage("Best Server!", server2));
        System.out.println(user2.sendMessage("This is the cool server!", server1));

        System.out.println(user2.sendMessage("Is this Cooler Server?", server2));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(user4.sendMessage("This Server is Cooler!", server2));
        TimeUnit.SECONDS.sleep(1);

        user1.displayAllMessages();
        System.out.println("==================================");
        user2.displayAllMessages();
        System.out.println("==================================");
        user3.displayAllMessages();
        System.out.println("==================================");
        user4.displayAllMessages();
        System.out.println("==================================");

        // Alice Attempts to Undo a message that did not change
        System.out.println(user3.undoMessage());
        user3.displayAllMessages();
        System.out.println("==================================");
        user4.displayAllMessages();

        // Iterator Demo

        System.out.println("~~Iterator Demo~~");
        user2.displayMessagesByUser(user3);
        // End of Demo

    }
}


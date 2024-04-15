package ChatApp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message
{
    private String message;
    private final User sender;
    private final ChatServer server;
    private final String initialTimeStamp;
    private String timeStamp;


    public Message(String message, User sender, ChatServer server)
    {
        this.message = message;
        this.sender = sender;
        this.server = server;
        // Create Timestamp based on user's time
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        this.initialTimeStamp = timeStampFormat.format(time);
        timeStamp = initialTimeStamp;

    }

    public void addToMessage(String adding)
    {
        message = message + adding;
    }

    public void changeMessage(String newMessage)
    {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        timeStamp = timeStampFormat.format(time);
        message = newMessage;
    }
    public void setTimeStamp(String timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp()
    {
        return timeStamp;
    }
    public String getInitialTimeStamp()
    {
        return initialTimeStamp;
    }

    public String getMessageContent()
    {
        return message;
    }

    public ChatServer getServer()
    {
        return server;
    }
    public String getSenderName()
    {
        return sender.getUserName();
    }

    public String getMessage()
    {
        return "From: " + sender.getUserName() + " in "+ server.getServerName() + "\nAt: "
                + timeStamp + "\n" + message;
    }
}

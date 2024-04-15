package ChatApp;

public class MessageMemento
{
    private String timeStamp;
    private String message;
    public MessageMemento(Message message)
    {
        this.timeStamp = message.getTimeStamp();
        this.message = message.getMessageContent();
    }

    public void getState(Message message)
    {
        message.changeMessage(this.message);
        message.setTimeStamp(this.timeStamp);
    }

    public void setState(Message message)
    {
        this.timeStamp = message.getTimeStamp();
        this.message = message.getMessageContent();
    }
}

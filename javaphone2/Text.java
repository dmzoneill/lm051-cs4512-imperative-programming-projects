/**
 * A class to model a simple text message.
 * The message has sender and recipient numbers and a message string.
 * @author David J. Barnes
 * @version 2007.11.07
 */
public class Text
{
    private long timeStamp ;
    // The sender of the message.
    private String from;
    // The intended recipient.
    private String to;
    // The text of the message.
    private String message;

    /**
     * Create a text from sender to the given recipient,
     * containing the given message.
     * @param from The sender of this item.
     * @param to The intended recipient of this item.
     * @param message The text of the message to be sent.
     */
    public Text(long timeStamp, String from, String to, String message)
    {
        this.timeStamp = timeStamp ;
        this.from = from ;
        this.to = to;
        this.message = message;
    }

    /**
     * @return The timeStamp of this message.
     */
    public long getTimeStamp()
    {
        return timeStamp ;
    }
    
    /**
     * @return The sender of this message.
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * @return The intended recipient of this message.
     */
    public String getTo()
    {
        return to;
    }

    /**
     * @return The text of the message.
     */
    public String getMessage()
    {
        return message;
    }
}

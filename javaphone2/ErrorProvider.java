
/**
 * Write a description of class ErrorProvider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ErrorProvider extends Provider
{
    /**
     * Constructor for objects of class ErrorProvider
     */
    public ErrorProvider()
    {
        super("Error - unknown provider");
    }
    
    /**
     * Send the given text.
     * @param txt The text to be sent.
     */
    public void sendText(Text txt)
    {
        System.err.println("No network for number " + txt.getTo());
    }
}


/**
 * Indicate that there is no valid provider matching
 * a phone number.
 * 
 * @author David J. Barnes 
 * @version 2007.11.13
 */
public class NoProviderException extends RuntimeException
{
    /**
     * Constructor for objects of class NoProviderException
     */
    public NoProviderException(String message)
    {
        super(message);
    }
}

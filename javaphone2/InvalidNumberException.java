
/**
 * An attempt has been made to use an illegal phone number.
 * 
 * @author David J. Barnes
 * @version 2007.11.13
 */
public class InvalidNumberException extends RuntimeException
{
    /**
     * Constructor for objects of class InvalidNumberException
     */
    public InvalidNumberException(String number)
    {
        super(number + " is an invalid number");
    }
}

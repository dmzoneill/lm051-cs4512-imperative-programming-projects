import java.util.HashMap;
import java.util.Map;

/**
 * A simple model of a mail server. The server is able to receive
 * mail items for storage, and deliver them to clients on demand.
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public class Provider
{
    // The name of this provider.
    private String name;
    // Storage for the arbitrary number of phones connected
    // to this provider.
    private Map<String, AbstractMobile> phones;

    /**
     * Construct a mobile service provider.
     * @param name The name of the service provider.
     */
    public Provider(String name)
    {
        this.name = name;
        phones = new HashMap<String, AbstractMobile>();
    }
    
    /**
     * @return The name of this provider.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Add the given phone to this network.
     */
    public void addToNetwork(AbstractMobile phone)
    {
        phones.put(phone.getNumber(), phone);
    }
    
    /**
     * Send the given text.
     * @param txt The text to be sent.
     * @throws NoProviderException If the recipient's phone number does not
     *                             have a valid prefix.
     * @throws InvalidNumberException If the number has a valid prefix but
     *                             is not recognised.
     */
    public void sendText(Text txt)
      throws NoProviderException, InvalidNumberException
      {
        String To = txt.getTo() ;
        Provider recipientProvider = NetworkAuthority.findProvider(To);
        if(recipientProvider == this) {
            // This provider.
            AbstractMobile recipientPhone = phones.get(To);
            if(recipientPhone != null) {
                recipientPhone.receiveText(txt);
            }
            else {
                // Number not found.
                throw new InvalidNumberException(To);
            }
        }
        else {
            // Other provider.
            recipientProvider.sendText(txt);
        }
    }
}

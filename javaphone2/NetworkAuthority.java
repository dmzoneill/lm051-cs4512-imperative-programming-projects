import java.util.*;

/**
 * A class acting as the global network of phone
 * providers
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NetworkAuthority
{
    // A mapping of provider prefixes to providers.
    private static Map<String, Provider> providers;
    static {
        providers = new HashMap<String, Provider>();
        Provider p;
        p = new Provider("three");
        providers.put("083", p);
        p = new Provider("Meteor");
        providers.put("085", p);
        p = new Provider("O2");
        providers.put("086", p);
        p = new Provider("Vodafone");
        providers.put("087", p);
        
    }
    
    // An error provider, in case of problems.
    private static final Provider ERROR_PROVIDER =
            new ErrorProvider();
    
    /**
     * Allow a phone to connect to the appropriate provider.
     * @return The provider if one is found, null otherwise.
     * @throws NoProviderException If the phone number does not have a valid prefix.
     */
    public static Provider connect(AbstractMobile phone)
      throws NoProviderException
    {
        Provider provider = findProvider(phone.getNumber());
        provider.addToNetwork(phone);
        return provider;
    }
    
    /**
     * Identify the provider for the given number.
     * @paran number The number to be looked up.
     * @return The provider associated with the number.
     * @throws NoProviderException If the number does not have a valid prefix.
     */
    public static Provider findProvider(String number)
      throws NoProviderException
    {
        Set<String> prefixes = providers.keySet();
        Iterator<String> it = prefixes.iterator();
        String prefix = null;
        while(it.hasNext()) {
            prefix = it.next();
            if(number.startsWith(prefix)) {
                return providers.get(prefix);
            }
        }
        throw new NoProviderException(
                "No provider found for number " + number);
    }
    
    /**
     * Constructor for objects of class NetworkAuthority.
     * Prevent external instantiation.
     */
    private NetworkAuthority()
    {
    }
}

import java.util.* ;
/**
 * A class to model a simple mobile phone (supposedly a Nokia6110n).
 * @author Dermot Shinners-Kennedy (UL) and David J. Barnes (University of Kent)
 * @version 2008.09.23
 * 
 */

public class Nokia6110nV2 extends AbstractMobile
{
    private static final Random rand = new Random(Calendar.getInstance().getTimeInMillis()); 
    // The number of this phone.
    private String number;
    // This phone's network provider.
    private Provider provider;
    // This phone's window interface on screen
    //private PhoneInterface screen = getGUI();
    // On(true)/Off(false) state of the phone
    private boolean switchedOn ;
    // Time (in milliseconds) the phone was switched on
    private long timeSwitchedOn;
    // Current battery level (measured in milliseconds or
    // the time it will take for it to go 'flat')
    private long batteryLevel ;
    // Current signal strength (random number)
    private int signalLevel;
    
    private StringList dictionary ;

    /**
     * Create a mobile phone (client) and attach it to a provider (server).
     */
    public Nokia6110nV2(String numb)
    {
        /**
         * Set the initial state of each of the state variables
         * stored by this phone object
         */
        // The number the phone has been assigned.
        number = numb ;
        // Initially (we assume) it is turned off
        switchedOn = false ; 
        // Initially (we assume) the battery is not charged fully
        batteryLevel = MAXIMUM_BATTERY_LEVEL ; 
        signalLevel = MAXIMUM_SIGNAL_LEVEL ;
        // Connect to the mobile network and remember who
        // the provider is.
        provider = connect();
        
        dictionary = new StringList("bncwords.txt") ;
    }
    
    public void switchOn()
    {   
        if(batteryLevel < USABLE_BATTERY_LEVEL) {
            // Can't use the phone so BRIEFLY...
            // Show the phone window (i.e. make the window visible)
            screenOn() ;
            // Display a message to identify the problem
            setDisplay("Battery Low.....Charge the battery") ;
            // Wait for 1.5 seconds so the user can read the message
            // NOTE: the number in brackets is the number of milliseconds to wait
            pause(1500) ; 
            // Remove the message from the window
            clearDisplay() ;
            // Hide the phone window
            screenOff() ;
            // Note, the overall effect of these operations does 
            // NOT CHANGE THE STATE OF THE PHONE - IT REMAINS TURNED OFF.
        } else {
            // Determine the current signal level
            signalLevel = checkSignal() ;
            // Show the phone window (i.e. make the window visible)
            screenOn() ;
            // Remove any items currently on the screen so that
            // the user starts with a clear screen
            clearDisplay() ;
            // Show the latest text message received (if there is one)
            showLatestText() ;
            // Alter the state of the phone to ON
            switchedOn = true ;
            // Make a note of the time it was switched on
            timeSwitchedOn = now()  ; 
        }
    }
    
    public void switchOff()
    {   
        // Note the change in the battery level OR (put another way)
        // alter the state of the phone to note the current battery level.
        batteryLevel = batteryLevel - batteryUsage(now() - timeSwitchedOn) ;
        // Hide the phone window
        screenOff() ;
        // Alter the state of the phone to OFF
        switchedOn = false ;
    }
    
    /**
     * Simulate a mobile phone battery charger. Please note, this is a
     * crude attempt at charging a battery but the simplicity is useful
     * at the moment.
     */
    public void chargeBattery()
    {   
        // While the battery level is below the required state
        while(batteryLevel < MAXIMUM_BATTERY_LEVEL) {
            // Apply some charge to increase the level
            batteryLevel = batteryLevel + charge() ;
        }
        showBatteryLevel() ;
    }
    
    /**
     * Show the most recent message received, if any.
     */
    public void showLatestText()
    {
        /**
         * Check with the provider if there is a text message pending. The
         * provider will either send back a text OR a signal (i.e. the
         * special value 'null') to indicate that there are no messages. 
         */
        Text txt = null ;// getLatestText() ;
        // If the provider sent back a message (i.e. the value 
        // returned is NOT null) then show the message on the screen.
        if(txt != null) {
            setDisplay(format(txt));
        }
    }
    
    /**
     * Clear the screen. This operation is provided for convenience.
     */
    public void clearScreen()
    {
        clearDisplay() ;
    }

    /**
     * This operation is provided for convenience.
     */
    public void tryForBetterSignal()
    {
        signalLevel = checkSignal() ;
        showSignalLevel() ;
    }
        
    /**
     * Send the given message to the given recipient via
     * the provider.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendText(String toNumber, String message)
    {
        if(signalLevel < USABLE_SIGNAL_LEVEL) {
            setDisplay("Poor signal. Messages cannot be sent.") ;
        } else {
            Text txt = new Text(now(), number, toNumber, message);
            provider.sendText(txt);
        }
    }

    /**
     * Collection of ACCESSOR methods
     */
    public String getNumber()
    {
        return number ;
    }
    
    public long getBatteryLevel()
    {
        return batteryLevel ;
    }
    
    public int getSignalLevel()
    {
        return signalLevel ;
    }
    public Provider getProvider()
    {
        return provider ;
    }
    
    public long getTimeSwitchedOn()
    {
        return timeSwitchedOn ;
    }

    /**
     * javaPhone V3 additions
     * 
     * A series of user text enter methods to highlight
     * and explain the utility and simplicity of if and while
     * and also introduce some of String class facilities
     */  
    public String keypadInput(String delimiter)
    {
        boolean Caps = false;
        int spacePos, opencurleyPos ;
        String key, text, word ;
        StringList predictiveList  = null ;
        int predictiveListChoice = 0 ;
        text = "";
        key = waitForKeyPress();
        while(key.compareToIgnoreCase(delimiter) != 0) {
            if(key.equalsIgnoreCase("#")) {
                if(Caps == true) {
                    Caps = false ;
                } else {
                    Caps = true ;
                }
            } else {
                if(key.equalsIgnoreCase(" ")) {
                    spacePos = text.lastIndexOf(" ") ;
                    word = text.substring(spacePos+1) ;
                    if(word.length() > 0 && dictionary.find(word) == -1) {
                        text = text.substring(0,spacePos+1) + "{" + word + "}" ;
                    }
                    text = text + key ;
                    clearList() ;
                    predictiveList = null ;
                } else if(key.equalsIgnoreCase("Backspace")) {
                    if(text.length() > 0) {
                        if(text.charAt(text.length()-1) == '}') {
                            opencurleyPos = text.lastIndexOf("{") ;
                            text = text.substring(0,opencurleyPos) + text.substring(opencurleyPos+1,text.length()-1) ;
                        } else if(text.charAt(text.length()-1) == ' ') {
                            text = text.substring(0,text.length() - 1) ;
                            if(text.charAt(text.length()-1) == '}') {
                                opencurleyPos = text.lastIndexOf("{") ;
                                text = text.substring(0,opencurleyPos) + text.substring(opencurleyPos+1,text.length()-1) ;
                            }
                        } else {
                            text = text.substring(0,text.length() - 1) ;
                        }
                    }
                    spacePos = text.lastIndexOf(" ") ;
                    word = text.substring(spacePos+1) ;
                    if(word.length() == 0) {
                        clearList() ;
                        predictiveList = null ;
                    } else {
                        predictiveList = dictionary.filteredBy(word) ;
                        displayList(predictiveList) ;
                        if(predictiveList != null) {
                            predictiveListChoice = 0 ;
                            highlightListItem(0) ;
                        }
                    }
                } else if(key.equalsIgnoreCase("insert") && predictiveList != null) {
                    spacePos = text.lastIndexOf(" ") ;
                    text = text.substring(0,spacePos+1) + highlightedListItem(predictiveListChoice) + " " ;
                    clearList() ;
                    predictiveList = null ;
                } else if(key.equalsIgnoreCase("up") && predictiveList != null) {
                    if(predictiveListChoice > 0) {
                        predictiveListChoice-- ;
                    }
                    highlightListItem(predictiveListChoice) ;
                } else if(key.equalsIgnoreCase("down") && predictiveList != null) {
                    if(predictiveListChoice < predictiveList.length() -1) {
                        predictiveListChoice++ ;
                    }
                    highlightListItem(predictiveListChoice) ;              
                } else {
                    if(Caps == true) {
                        text = text + key.toUpperCase();  
                    } else {
                        text = text + key.toLowerCase(); 
                    }
                    spacePos = text.lastIndexOf(" ") ;
                    word = text.substring(spacePos+1) ;
                    predictiveList = dictionary.filteredBy(word) ;
                    displayList(predictiveList) ;
                    if(predictiveList != null) {
                        predictiveListChoice = 0 ;
                        highlightListItem(0) ;
                    }
                }
                setDisplay(text);
            }           
            key = waitForKeyPress();
        }
        return text ;
    }
    
    public int levenshteinDistance( String s, String t) 
    {
        int n = s.length();
        int m = t.length();
        
        if (n == 0) return m;
        if (m == 0) return n;
 
        int[][] d = new int[n + 1][m + 1];
 
        for ( int i = 0; i <= n; d[i][0] = i++ );
        for ( int j = 1; j <= m; d[0][j] = j++ );
 
        for ( int i = 1; i <= n; i++ ) 
        {
            char sc = s.charAt( i-1 );
            for (int j = 1; j <= m;j++) 
            {
                int v = d[i-1][j-1];
                if ( t.charAt( j-1 ) !=  sc ) v++;
                    d[i][j] = Math.min( Math.min( d[i-1][ j] + 1, d[i][j-1] + 1 ), v );
            }
        }
        
        return d[n][m];
    }
        
    
}
/**
 * A class to model a simple mobile phone (supposedly a SonyW890i).
 * @author Dermot Shinners-Kennedy (UL) and David J. Barnes (University of Kent)
 * @version 2008.09.04
 * 
 */

public class SonyW890i extends AbstractMobile
{
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

    /**
     * Create a mobile phone (client) and attach it to a provider (server).
     */
    public SonyW890i(String numb)
    {
        /**
         * Set the initial state of each of the state variables
         * stored by this phone object
         */
        // The number the phone has been assigned.
        number = numb;
        // Initially (we assume) it is turned off
        switchedOn = false ; 
        // Initially (we assume) the battery is not charged fully
        batteryLevel = USABLE_BATTERY_LEVEL ; 
        signalLevel = checkSignal() ;
        // Connect to the mobile network and remember who
        // the provider is.
        provider = connect();
    }
    
    /**
     * If battery is sufficiently charged then switch on the phone
     * an make the window associated with the phone visible.
     */
    
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
    
    /**
     * 
     */
    public void switchOff()
    {   
        /** 
         * The following three operations are not necessary. We are just
         * using them for the moment so that we can verify (test?) that
         * the code is correctly altering the batteryLevel.
         */
        // Calculate the length of time the phone was switched on
        long timeOn = now() - timeSwitchedOn ;
        // Show the result on the screen
        setDisplay("Phone has been on " + timeOn + " milliseconds") ;
        // Wait 1.5 seconds so the user can read it
        pause(1500) ;
        // Note the change in the battery level OR (put another way)
        // alter the state of the phone to note the current battery level.
        batteryLevel = batteryLevel - batteryUsage(timeOn) ;
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
     * Wait for the user to press a key and show it on the phone screen.
     */
    public void keyPress()
    {
        // Wait for the user to press a key/button and store the key (e.g. A or a)
        // or the name of the key (e.g. Page Down or Backspace) in 'key'
        String key = waitForKeyPress() ;
        // Add the key value to the end of the text currently on the display
        addToDisplay(key) ;
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
        Text txt = getLatestText() ;
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
            /**
             * Create a text message object using the current time,
             * the number of this phone (i.e. the sender), the number
             * of the receipient's phone and the actual text of the message.
             */
            Text txt = new Text(now(), number, toNumber, message);
            /**
             * Send the text message object to the provider and let them 
             * look after delivering it. The text object contains all the
             * information the provider needs for billing (i.e. the sender's
             * number and receipient's number - in case roaming charges apply).
             * It also has all the information necessary for delivery.
             */
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
}

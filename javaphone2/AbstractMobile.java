import java.util.Calendar ;
import java.util.* ;
import java.text.SimpleDateFormat;

/**
 * Write a description of class phoneCalendar here.
 * 
 * @author Dermot Shinners-Kennedy 
 * @version 2008.09.03
 */

/**
 * Abstract class AbstractMobile - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class AbstractMobile 
{
    private static final Random rand = new Random(Calendar.getInstance().getTimeInMillis()); 
    
    protected static final long MINIMUM_BATTERY_LEVEL = 0 ;
    protected static final long MAXIMUM_BATTERY_LEVEL = 18000000 ; // in milliseconds = 5hrs
    protected static final long USABLE_BATTERY_LEVEL = 600000 ; // in milliseconds = 10mins
    protected static final long BATTERY_CHARGE_INCREMENT = 600000 ; // in milliseconds = 10mins
    protected static final int MINIMUM_SIGNAL_LEVEL = 0 ; // 
    protected static final int MAXIMUM_SIGNAL_LEVEL = 10 ; // 10 is an arbitrary choice
    protected static final int USABLE_SIGNAL_LEVEL = 4 ; // 

    protected static final String PREFERRED_DATE_FORMAT = "EEE d MMM yyyy HH:mm:ss";

    // The most recent txt.
    private Text[] messages ;
    private int msgCount ;
    // Default width and height of the GUI.
    private static final int DEF_WIDTH = 350, DEF_HEIGHT = 150;
    // The GUI for this mobile.
    private phoneGUI gui;

    /**
     * Set up the GUI associated with this phone.
     */
    public AbstractMobile()
    {
        gui = new phoneGUI();
        gui.configureBatteryDisplay(MINIMUM_BATTERY_LEVEL, MAXIMUM_BATTERY_LEVEL) ;
        gui.configureSignalDisplay(MINIMUM_SIGNAL_LEVEL, MAXIMUM_SIGNAL_LEVEL) ;    
        //displayBatteryLevel() ;
        messages = new Text[500] ;
        msgCount = 0 ;
    }

    /**
     * @return The number of this phone.
     */
    public abstract String getNumber();
    public abstract long getBatteryLevel() ;
    public abstract int getSignalLevel() ;
    //public abstract void setBatteryLevel(long level) ; 
    //public abstract void setSignalLevel(int level) ;    
    /**
     * Send the given message to the given recipient via
     * the provider.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public abstract void sendText(String to, String message);
    
    /**
     * @return The latest text message a string.
     */
/*    public Text getLatestText()
    {
        if(msgCount > 0) {
            Text temp = null ; //latestText ;
            //latestText = null ;
        }
        return temp ;
    }
 */   
    public String format(Text txt)
    {
        return "Message: " + txt.getMessage()  + "  From: " + txt.getFrom() +  "  Received: " + timeStampAsString(txt.getTimeStamp()) ;
    }
    
    public long charge()
    {   
        gui.updateBatteryDisplay(getBatteryLevel()) ;
        long m = MAXIMUM_BATTERY_LEVEL - getBatteryLevel() ;
        pause(1000) ;
        return BATTERY_CHARGE_INCREMENT < m ? BATTERY_CHARGE_INCREMENT : m ; 
    }
    
    public void showBatteryLevel()
    {
        gui.updateBatteryDisplay(getBatteryLevel()) ;
    }
    
    public void showSignalLevel()
    {
        gui.updateSignalDisplay(getSignalLevel()) ;
    }

    public void setPrompt(String s)
    {
        gui.setPrompt(s) ;
    }
    public void setCapsStatus(String s)
    {
        gui.setCapsStatus(s) ;
    }
    public void setMsgLength(String s)
    {
        gui.setMsgLength(s) ;
    }    
    public void pause(long milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        }
            catch(InterruptedException e) {
        }
    }
    
    public long batteryUsage(long milliseconds)
    {
        // We DON'T want to run the battery down in real time so
        // we have accelerated the battery usage by a factor of 10
        // Thus, using the phone for 30mins should run the battery down completely
        long n = milliseconds * 10 ;
        long m = getBatteryLevel() ;
        return n < m ? n : m ;
    }
    

    public long now()
    {
        return Calendar.getInstance().getTimeInMillis() ;
    }
    
    public String timeStampAsString(long timeStamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(PREFERRED_DATE_FORMAT);
        return sdf.format(new Date(timeStamp)) ;
    }
    
    public int checkSignal()
    {
        return MAXIMUM_SIGNAL_LEVEL ;
    }

   /**
     * Read a key with echo.
     * This call blocks until a key is available.
     */
    public String waitForKeyPressAndEcho()
    {
        return gui.waitForKeyPressAndEcho();
    }
    
    /**
     * Read a key without echo.
     * This call blocks until a key is available.
     */
    public String waitForKeyPress()
    {
        return gui.waitForKeyPress();
    }
        
    /**
     * Return an interface for the phone.
     * @return The GUI.
     */
    public PhoneInterface getGUI()
    {
        return gui;
    }

    /**
     * Receive a text message.
     * @param text The message.
     */
    protected void receiveText(Text txt)
    {
        Text temp = txt;
    }
    
    /**
     * Connect to a network. This assumes that the mobile number
     * has already been set.
     */
    protected Provider connect()
    {
        Provider provider = NetworkAuthority.connect(this);
        // Put the phone's number in the title of the GUI.
        // NOTE, the phone window is created but NOT MADE VISIBLE
        gui.setTitle(getNumber());
        gui.setSize(DEF_WIDTH, DEF_HEIGHT);
        gui.setVisible(false) ;
        return provider;
    }
    
    public void displayBatteryLevel()
    {
        gui.updateBatteryDisplay(getBatteryLevel()) ;
    }
    
    /**
     * Make the phone window visible.
     * @author Dermot Shinners-Kennedy
     */
    public void screenOn()
    {
        gui.updateBatteryDisplay(getBatteryLevel()) ;
        gui.updateSignalDisplay(getSignalLevel()) ;
        gui.setVisible(true) ;
    }
    /**
     * Hide the phone window.
     * @author Dermot Shinners-Kennedy
     */
     public void screenOff()
    {
        gui.updateBatteryDisplay(getBatteryLevel()) ;
        gui.updateSignalDisplay(getSignalLevel()) ;
        gui.setVisible(false) ;
    } 
    
    public void displayList(StringList list)
    {
        gui.displayList(list) ;
    }
    
    public void clearList()
    {
        gui.clearItemList() ;
    }
    public void addListItem(String s)
    {
        gui.addListItem(s) ;
    }
    public void highlightListItem(int index)
    {
        gui.highlightListItem(index) ;
    }
    
    public String highlightedListItem(int index)
    {
        return gui.highlightedListItem(index) ;
    }
    /**
     * Add text to the phone's display.
     * @param s The text to add.
     */
    public void addToDisplay(String s)
    {
        gui.addToDisplay(s);
    }

    /**
     * Remove the rightmost character, if there is one, from the phone's display.
     * @author Dermot Shinners-Kennedy
     */
    public void removeFromDisplay()
    {
            gui.removeFromDisplay();
    }
    
    /**
     * Set the display to show the given text.
     * @param s The text to display.
     */
    public void setDisplay(String s)
    {
        gui.setDisplay(s);
    }
    
    public void setSpellingDisplay(String s)
    {
        gui.setSpellingDisplay(s);
    }
    /**
     * Retrun the text, if any, currently displayed on the phone's display.
     */
    public String getDisplay()
    {   
        return gui.getDisplay() ;
    }
    
    /**
     * Clear the phone's display.
     */
    public void clearDisplay()
    {
        gui.setDisplay("");
    }
    
    public PhoneInterface getInterface()
    {
        return gui;
    }
}

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Functionality of the mobile's GUI.
 * 
 * @author Dermot Shinners-Kennedy (UL) and David J. Barnes (University of Kent)
 * @version 2008.09.04   // 2007.11.26
 * 
 * Modifications:
 * @author Dermot Shinners-Kennedy
 * @version 2008.09.01
 */
public class phoneGUI extends JFrame implements PhoneInterface
{
    private static final Random rand = new Random(123);
    
    // The following variables provide handles for the
    // phone scrren objects that display phone status
    // and text information
    // A label to display the key input to the phone.
    private JLabel keyDisplay;
    // A list of things.
    private JPanel itemList;
    // A progress bar for the battery level  //DSK
    private JProgressBar batteryBar ;  //DSK
    // A progress bar for the battery level  //DSK
    private JProgressBar signalBar ;  //DSK
    private JLabel capsStatus ;
    private JLabel msgLength ;
    private JLabel prompt ;
    // Any pending keypress. A value of -1 indicates there is no pending press.
    private KeyEvent pendingKeypress = null;
    // The delay for keypresses.
    private static final int KEYPRESS_TIMEOUT = 1000;
    // The action to be taken when the keypress timeout is activated.
    private ActionListener keypressTimeoutHandler;
    // The timer used to fire the keypress timeout handler.
    private Timer keypressTimer;
    // A list for managing blocking key handling.
    private List<KeyEvent> keyList;

    /**
     * Constructor for objects of class MobileGUI
     */
    public phoneGUI()
    {
        super("");
        // Create a phone window
        // Choose a random location near the centre of the screen.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int middle_x = screen.width / 2;
        int middle_y = screen.height / 2;
        setLocation(middle_x + rand.nextInt(20) - 10,
                    middle_y + rand.nextInt(20)- 10);
        
        // Create a typing interface for handling key presses
        // NB: The following code is setup to handle keypresses with
        // delays managed by a timer. It is an alternative to buffered
        // blocking key handling.
        // A handler for keypresses.
        keypressTimeoutHandler = new KeyPressTimeoutHandler();
        // A timer for keypress timeouts.
        keypressTimer = new Timer(KEYPRESS_TIMEOUT, keypressTimeoutHandler);
        keypressTimer.setRepeats(false);
        
        // NB: The following code is to manage buffered blocking key handling.
        // It is an alternative to handling keypresses with a timer.
        keyList = Collections.synchronizedList(new LinkedList<KeyEvent>());
        // Listen for key presses.
        KeyListener keyHandler = new KeyAdapter() {
            public void keyPressed(KeyEvent e) 
            {
                // Choose whether to handle keypresses with or without
                // a timer.
                receiveKeypressWithoutTimer(e);
            }
        };
        

        // Add and configure interface components
        getContentPane().setBackground(Color.WHITE);
        // A component for text message and user interface
        // display - i.e. where to display key presses.
        keyDisplay = new JLabel(" ");
        // Enable the label to receive key events.
        keyDisplay.setFocusable(true);
        
        // Add 
        keyDisplay.addKeyListener(keyHandler);

        
        // Where to display a list of things.
        itemList = new JPanel();
        itemList.setLayout(new GridLayout(0, 1));

        batteryBar = new JProgressBar() ; //MINIMUM_BATTERY_LEVEL, 18000000);
        batteryBar.setOrientation(JProgressBar.VERTICAL) ;
        batteryBar.setBackground( Color.WHITE);
        batteryBar.setForeground( Color.BLACK);
        batteryBar.setToolTipText( "Battery Status");
        batteryBar.setStringPainted(true);
        add(batteryBar, BorderLayout.EAST);
        
        signalBar  = new JProgressBar() ; //MINIMUM_SIGNAL_LEVEL, MAXIMUM_SIGNAL_LEVEL);
        signalBar.setOrientation(JProgressBar.VERTICAL) ;
        signalBar.setBackground( Color.WHITE);
        signalBar.setForeground( Color.BLACK);
        signalBar.setToolTipText( "Signal Status");
        signalBar.setStringPainted(true);
        add(signalBar, BorderLayout.WEST);
        
        
        // Manage the item list.
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout());
        itemPanel.add(itemList);
        itemPanel.addKeyListener(keyHandler);

        JPanel innerScreen = new JPanel();
        innerScreen.setLayout(new BorderLayout());
        innerScreen.add(keyDisplay, BorderLayout.CENTER);
        //setVisible(true) ;
        innerScreen.add(itemPanel, BorderLayout.EAST);

        //statusItems.setAlignment(FlowLayout.TRAILING);
        //statusItems.setLayout(new BoxLayout(statusItems, BoxLayout.LINE_AXIS));
        JPanel statusbar = new JPanel() ;
        statusbar.setLayout(new FlowLayout(FlowLayout.LEFT)) ;
        prompt= new JLabel("", JLabel.LEFT);
        capsStatus = new JLabel("", JLabel.LEFT);
        //capsStatus.setToolTipText( "Capitalisation Style");
        //capStatus.setBorder(new LineBorder(Color.BLACK, 3));
        //capsStatus.setPreferredSize(new Dimension(50, 20));
        msgLength = new JLabel("", JLabel.LEFT) ;
        //msgLength.setBorder(new LineBorder(Color.BLACK, 3));
        //msgLength.setPreferredSize(new Dimension(50, 20));
        statusbar.add(prompt) ;
        statusbar.add(capsStatus) ;
        statusbar.add(msgLength) ;
        innerScreen.add(statusbar,BorderLayout.SOUTH);
        add(innerScreen,BorderLayout.CENTER) ;
    }

     /**
     * Add text to the phone's display.
     * @param s The text to add.
     */
    public void addToDisplay(String s)
    {
        String txt = keyDisplay.getText();
        setDisplay(txt.substring(6,txt.length()-7)+s) ; // keyDisplay.getText().replace("<html>","").replace("</html>","") + s);
    }

    /**
     * Remove the rightmost character, if there is one, from the phone's display.
     * @author Dermot Shinners-Kennedy
     */
    public void removeFromDisplay()
    {
        String s = keyDisplay.getText() ;
        if(s.length() > 0) {
            setDisplay(s.substring(0,s.length()-1));
        }
    }
    
    /**
     * Set the display to show the given text.
     * @param s The text to display.
     */
    public void setDisplay(String s)
    {
        //keyDisplay.setText("<html>"+s.replace("\n","<br>")+"</html>");
        //keyDisplay.setText("<html>"+s.replace(SI,"<u>").replace(SO,"</u>").replace("\n","<br>")+"</html>");
        keyDisplay.setText("<html>"+s.replace("{","<u>").replace("}","</u>").replace("\n","<br>")+"</html>");
     }
    
    public void setSpellingDisplay(String s)
    {
       //String x = s.replace("{","<u>");
       // x = x.replace("}","</u>");
       // x = "<html>"+x+"</html>";
       // keyDisplay.setText(x); //"<html>"+s.replace("<","<u>").replace(">","</u>")+"</html>");
       char si = (char) 17, so = (char) 16 ;
       String SI = "" + si , SO = "" + so ;
       keyDisplay.setText("<html>"+s.replace(SI,"<u>").replace(SO,"</u>")+"</html>");
    }
    
    public void setPrompt(String s)
    {
        prompt.setText(s) ;
    }
    
    /**
     * Retrun the text, if any, currently displayed on the phone's display.
     */
    public String getDisplay()
    {   
        return keyDisplay.getText() ;
    }
    
    /**
     * Clear the phone's display.
     */
    public void clearDisplay()
    {
        setDisplay("");
    }
    
    public void setCapsStatus(String stat)
    {
        capsStatus.setText(stat) ;
    }
    
    public void setMsgLength(String len)
    {
        msgLength.setText(len) ;
    }

    public void configureBatteryDisplay(long minimum, long maximum)
    {
        batteryBar.setMinimum((int) minimum) ;
        batteryBar.setMaximum((int) maximum) ;
    }
    
    public void updateBatteryDisplay(long value)
    {
        //int val = value / 1000 ;
        batteryBar.setValue((int) value) ;
    }

    public void configureSignalDisplay(int minimum, int maximum)
    {
        signalBar.setMinimum(minimum) ;
        signalBar.setMaximum(maximum) ;
    }
    
    public void updateSignalDisplay(int value)
    {
        signalBar.setValue(value) ;
    }    
   
   
    /**
     * Read a key without echo.
     * This call blocks until a key is available.
     */
    public String waitForKeyPress()
    {
        toFront();
        while(keyList.size() == 0) {
            try {
                Thread.sleep(100);
            }
            catch(InterruptedException e) {
            }
        }
        KeyEvent event = keyList.remove(0);
        return convertKeyEventToString(event);
    }
    

    
    /**
     * Read a key with echo.
     * This call blocks until a key is available.
     */
    public String waitForKeyPressAndEcho()
    {
        String s = waitForKeyPress();
        addToDisplay(s);
        return s;
    }
 
    
    public void displayList(StringList list)
    {
        int i ;
        clearItemList() ;
        if(list != null) {
            i = 0 ;
            while(i < list.capacity()) {
                addListItem(list.item(i)) ;
                i++ ;
            }
            highlightListItem(0) ;
        } else {
            addListItem("<no words>") ;
        }
    }   
    
    /**
     * Clear the list of items.
     */
    public void clearItemList()
    {
        itemList.removeAll();
        getContentPane().validate();
    }
    
    /**
     * Add a new item to the displayed list.
     * The added item is not selected by default.
     * @param s The text to be added to the list.
     */
    public void addListItem(String s)
    {
        JLabel nextItem = new JLabel(s);
        nextItem.setForeground(Color.BLACK);
        nextItem.setBackground(Color.GRAY);
        nextItem.setOpaque(true);
        itemList.add(nextItem);
        getContentPane().validate();
    }
    
    /**
     * Select a particular list item.
     * @param index The item to be selected. Zero is the first index.
     */
    public void highlightListItem(int index)
    {
        Component[] items = itemList.getComponents();
        int count = items.length;
        if(index >= 0 && index < count) {
            // Ok.
            for(Component item : items) {
                item.setBackground(Color.GRAY);
            }
            items[index].setBackground(Color.WHITE);
        }
    }
    
   public String highlightedListItem(int index)
    {
        Component[] items = itemList.getComponents();
        JLabel chosen = (JLabel) items[index] ;
        return chosen.getText() ;
    }
   
    /**
     * Map a key event to a String representation.
     * 
     * Modifications @author Dermot Shinners-Kennedy
     * @param event The key event.
     * @return A String equivalent of event.
     */
    private String convertKeyEventToString(KeyEvent event)
    {
       String keyName = KeyEvent.getKeyText(event.getKeyCode()).toLowerCase() ;
       if(keyName.equalsIgnoreCase("number sign")) {
           keyName = "#" ;
       } else if(keyName.equalsIgnoreCase("semicolon")) {
           keyName = ";" ;
       } else if(keyName.equalsIgnoreCase("period")) {
           keyName = "." ;
       } else if(keyName.equalsIgnoreCase("comma")) {
           keyName = "," ;
       } else if(keyName.equalsIgnoreCase("numpad *")) {
           keyName = "*" ;
       } else if(keyName.equalsIgnoreCase("slash")) {
           keyName = "/" ;
       } else if(keyName.equalsIgnoreCase("numpad +")) {
           keyName = "+" ;
       } else if(keyName.equalsIgnoreCase("minus")) {
           keyName = "-" ;
       } else if(keyName.equalsIgnoreCase("quote")) {
           keyName = "'" ;
       } else if(keyName.equalsIgnoreCase("open bracket")) {
           keyName = "[" ;
       } else if(keyName.equalsIgnoreCase("close bracket")) {
           keyName = "]" ;
       } else if(keyName.equalsIgnoreCase("back slash")) {
           keyName = "\\" ;
       } else if(keyName.equalsIgnoreCase("equals")) {
           keyName = "=" ;
       } else if(keyName.equalsIgnoreCase("space")) {
           keyName = " " ;
       }
       return keyName ;
    }
        
    /**
     * A key has been pressed.
     * Place it in the blocking buffer and notify any buffer
     * readers.
     * @param c The key.
     */
    private void receiveKeypressWithoutTimer(KeyEvent event)
    {
        keyList.add(event);
    }
    
    /**
     * A key has been pressed.
     * @param c The key.
     */
    private void receiveKeypressWithTimer(KeyEvent event)
    {
        // Stop the current pending character from being shown.
        keypressTimer.stop();
        if(pendingKeypress == null) {
            // Nothing pending.
            pendingKeypress = event;
        }
        else if(KeyEvent.getKeyText(event.getKeyCode()).equals(
                    KeyEvent.getKeyText(pendingKeypress.getKeyCode()))) {
            //pendingKeypress++;
        }
        else {
            showPendingKeypress();
            pendingKeypress = event;
        }
        keypressTimer.restart();
    }
    
    /**
     * Show a keypress that is pending. Clear the pending
     * field afterwards.
     */
    private void showPendingKeypress()
    {
        synchronized(this) {
            if(pendingKeypress != null) {
                addToDisplay(KeyEvent.getKeyText(
                                pendingKeypress.getKeyCode()));
                pendingKeypress = null;
            }
        }
    }
    
    /**
     * Required when the timeout on a keypress has 
     */
    private class KeyPressTimeoutHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(pendingKeypress != null) {
                showPendingKeypress();
            }
        }
    }
}

/**
 * A visual interface for a mobile phone.
 * 
 * @author David J. Barnes
 * @version 2007.11.29
 */
public interface PhoneInterface
{
    //public void on() ; //dsk 
    //public void off() ; //dsk 
    //public void showBatteryLevel(double percent) ;
    /**
     * Add text to the phone's display.
     * @param s The string to show.
     */
    public void addToDisplay(String s);
    
    /**
     * Remove the rightmost character (NOTE one character) from the phone's display.
     * 
     */
    public void removeFromDisplay() ; //dsk
    
    /**
     * Set the display to show the given text.
     * @param s The text to display.
     */
    public void setDisplay(String s);
    
    public String getDisplay() ;
    
    /**
     * Clear the phone's display.
     */
    public void clearDisplay();

    /**
     * Clear the list of items.
     */
    public void clearItemList();
    
    /**
     * Add a new item to the displayed list.
     * The added item is not selected by default.
     * @param s The text to be added to the list.
     */
    public void addListItem(String s);

    /**
     * Select a particular list item.
     * @param index The item to be selected. Zero is the first index.
     */
    //public void highlightListItem(int index);
    //public String highlightedListItem(int index);
    
}

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
     * javaPhone V2 additions
     * 
     * A series of user text enter methods to highlight
     * and explain the utility and simplicity of if and while
     * and also introduce some of String class facilities
     */  
    public String typeText(String delimiter)
    {
        String key, text ;
        clearDisplay() ;
        text = "";
        key = waitForKeyPress();
        while(key.compareToIgnoreCase(delimiter) != 0) {
            text = text + key ;
            setDisplay(text) ;
            key = waitForKeyPress();
        }
        return text ;
    }
    
    public String typeTextWithCorrection(String delimiter)
    {
        String key, text ;
        clearDisplay() ;
        text = "";
        key = waitForKeyPress();
        while(key.compareToIgnoreCase(delimiter) != 0) {
            if(key.equalsIgnoreCase("Backspace")) {
                if(text.length() > 0) {
                    text = text.substring(0,text.length() - 1) ;
                }
            } else {
                text = text + key;
            }
            setDisplay(text);
            key = waitForKeyPress();
        }
        return text ;
    }    

    public String secureTextWithCorrection(String delimiter)
    {
        String key, secureText ;
        int i ;
        clearDisplay() ;
        secureText = "";
        key = waitForKeyPress();
        while(key.compareToIgnoreCase(delimiter) != 0) {
            if(key.equalsIgnoreCase("Backspace")) {
                if(secureText.length() > 0) {
                    secureText = secureText.substring(0,secureText.length() - 1) ;
                }
            } else {
                secureText = secureText + key;
            }
            clearDisplay() ;
            i = 0;
            while(i < secureText.length()){
                addToDisplay("*");
                i = i + 1 ;
            }
            key = waitForKeyPress();
        }
        return secureText ;
    }    
    
    public String typeTextWithCaps(String delimiter)
    {
        boolean Caps = false ;
        String key, text ;
        clearDisplay() ;
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
                if(Caps) {
                    text = text + key.toUpperCase();  
                } else {
                    text = text + key.toLowerCase(); 
                }
                setDisplay(text) ;
            }
            key = waitForKeyPress();
        }
        return text ;
    }
    
    public String typeTextWithCapsAndCorrection(String delimiter)
    {
        boolean Caps = false;
        String key, text ;
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
                if(key.equalsIgnoreCase("Backspace")) {
                    if(text.length() > 0) {
                        text = text.substring(0,text.length() - 1) ;
                    }
                } else {
                    if(Caps == true) {
                        text = text + key.toUpperCase();  
                    } else {
                        text = text + key.toLowerCase(); 
                    }
                }
                setDisplay(text);
            }           
            key = waitForKeyPress();
        }
        return text ;
    }
 
    public void usingTypeTextV0()
    {
        clearDisplay() ;
        String text = typeTextWithCapsAndCorrection("enter") ;
    }
    
    public void usingTypeTextV1()
    {
        clearDisplay() ;
        String text = typeTextWithCapsAndCorrection("enter") ;
        addToDisplay("\n\n") ; // add two blank lines to the output/display
        split(text) ;
    }
    
    public void split(String phrase)
    {
        String word ;
        int startPos, spacePos ;
        phrase = phrase + " " ;
        startPos = 0;
        spacePos = phrase.indexOf(" ",startPos) ;
        while(spacePos != -1) {
            word = phrase.substring(startPos,spacePos) ;
            addToDisplay(word + "\n") ; // display word and move to next line
            startPos = spacePos + 1 ;
            spacePos = phrase.indexOf(" ",startPos) ;
        }
    }

    public void usingTypeTextV2()
    {
        String msgText, toNumber ;
        clearDisplay() ;
        setPrompt("Message") ;
        msgText = typeTextWithCapsAndCorrection("enter") ;
        clearDisplay() ;
        setPrompt("Number") ;
        toNumber = typeTextWithCapsAndCorrection("enter") ;
        setPrompt("") ;
        clearDisplay() ;
        sendText(toNumber, msgText) ;
    }

    public void translateEnglishToPirateV0()
    {
        String englishText, pirateText ;
        clearDisplay() ;
        englishText = typeTextWithCapsAndCorrection("enter") ;
        pirateText = talkLikeAPirate(englishText) ;
        addToDisplay(" -- TRANSLATION -- " + pirateText) ;
    }
        
    public void translateEnglishToPirateV1()
    {
        String englishText, pirateText ;
        clearDisplay() ;
        englishText = typeTextWithCapsAndCorrection("enter") ;
        while(englishText.length() > 0) {
            pirateText = talkLikeAPirate(englishText) ;
            addToDisplay("\n\n" + pirateText) ;
            addToDisplay("\n\n\nPress any key to continue...") ;
            waitForKeyPress() ;
            //System.out.println(englishText) ;
            //System.out.println(pirateText) ;
            //System.out.print("\n\n") ;
            clearDisplay() ;
            englishText = typeTextWithCapsAndCorrection("enter") ;
        }
    }    
    
    public String talkLikeAPirate(String phrase)
    {
        String word, pirateTalk = "" ;
        int startPos, spacePos ;
        phrase = phrase + " " ;
        startPos = 0;
        spacePos = phrase.indexOf(" ",startPos) ;
        while(spacePos != -1) {
            word = phrase.substring(startPos,spacePos) ;
            if(word.equalsIgnoreCase("attention")) {
                pirateTalk = pirateTalk + "avast" ;
            } else if(word.equalsIgnoreCase("sir")) {
                pirateTalk = pirateTalk + "matey" ;
            } else if(word.equalsIgnoreCase("keyword")) {
                pirateTalk = pirateTalk + "riggin'" ;
            } else if(word.equalsIgnoreCase("keywords")) {
                pirateTalk = pirateTalk + "riggin'" ;
            } else if(word.equalsIgnoreCase("madam")) {
                pirateTalk = pirateTalk + "proud beauty" ;
            } else if(word.equalsIgnoreCase("never")) {
                pirateTalk = pirateTalk + "ne'er" ;
            } else if(word.equalsIgnoreCase("ever")) {
                pirateTalk = pirateTalk + "e'er" ;
            } else if(word.equalsIgnoreCase("over")) {
                pirateTalk = pirateTalk + "o'er" ;
            } else if(word.equalsIgnoreCase("officer")) {
                pirateTalk = pirateTalk + "foul blaggart" ;
            } else if(word.equalsIgnoreCase("is")) {
                pirateTalk = pirateTalk + "be" ;
            } else if(word.equalsIgnoreCase("was")) {
                pirateTalk = pirateTalk + "be" ;
            } else if(word.equalsIgnoreCase("welcome")) {
                pirateTalk = pirateTalk + "come aboard" ;
            } else if(word.equalsIgnoreCase("error")) {
                pirateTalk = pirateTalk + "scupper" ;
            } else if(word.equalsIgnoreCase("errors")) {
                pirateTalk = pirateTalk + "scuppers" ;
            } else if(word.equalsIgnoreCase("am")) {
                pirateTalk = pirateTalk + "be" ;
            } else if(word.equalsIgnoreCase("where")) {
                pirateTalk = pirateTalk + "whar" ;
            } else if(word.equalsIgnoreCase("pull")) {
                pirateTalk = pirateTalk + "heave to" ;
            } else if(word.equalsIgnoreCase("lecturer")) {
                pirateTalk = pirateTalk + "yellow-bellied sapsucker" ;
            } else if(word.equalsIgnoreCase("lecturers")) {
                pirateTalk = pirateTalk + "yellow-bellied sapsuckers" ;
            } else if(word.equalsIgnoreCase("tutor")) {
                pirateTalk = pirateTalk + "lilly-livered landlubber" ;
            } else if(word.equalsIgnoreCase("tutors")) {
                pirateTalk = pirateTalk + "lilly-livered landlubbers" ;
            } else if(word.equalsIgnoreCase("programmer")) {
                pirateTalk = pirateTalk + "milksop" ;
            } else if(word.equalsIgnoreCase("exam")) {
                pirateTalk = pirateTalk + "hempen halter" ;
            } else if(word.equalsIgnoreCase("exams")) {
                pirateTalk = pirateTalk + "hempen halters" ;
            } else if(word.equalsIgnoreCase("examination")) {
                pirateTalk = pirateTalk + "hempen halter" ;
            } else if(word.equalsIgnoreCase("examinations")) {
                pirateTalk = pirateTalk + "hempen halters" ;
            } else if(word.equalsIgnoreCase("ul")) {
                pirateTalk = pirateTalk + "the poop deck" ;
            } else if(word.equalsIgnoreCase("programmers")) {
                pirateTalk = pirateTalk + "milksops" ;
            } else if(word.equalsIgnoreCase("the")) {
                pirateTalk = pirateTalk + "th'" ;
            } else if(word.equalsIgnoreCase("my")) {
                pirateTalk = pirateTalk + "me" ;
            } else if(word.equalsIgnoreCase("your")) {
                pirateTalk = pirateTalk + "ye" ;
            } else if(word.equalsIgnoreCase("you")) {
                pirateTalk = pirateTalk + "ye" ;
            } else if(word.equalsIgnoreCase("hello")) {
                pirateTalk = pirateTalk + "ahoy" ;
            } else if(word.equalsIgnoreCase("friend")) {
                pirateTalk = pirateTalk + "matey" ;
            } else if(word.equalsIgnoreCase("friends")) {
                pirateTalk = pirateTalk + "hearties" ;
            } else if(word.equalsIgnoreCase("yes")) {
                pirateTalk = pirateTalk + "aye" ;
            } else if(word.equalsIgnoreCase("turn")) {
                pirateTalk = pirateTalk + "go about" ;
            } else if(word.equalsIgnoreCase("left")) {
                pirateTalk = pirateTalk + "port" ;
            } else if(word.equalsIgnoreCase("right")) {
                pirateTalk = pirateTalk + "starboard" ;
            } else if(word.equalsIgnoreCase("back")) {
                pirateTalk = pirateTalk + "aft" ;
            } else if(word.equalsIgnoreCase("front")) {
                pirateTalk = pirateTalk + "bow" ;
            } else if(word.equalsIgnoreCase("walk")) {
                pirateTalk = pirateTalk + "sail" ;
            } else if(word.equalsIgnoreCase("walking")) {
                pirateTalk = pirateTalk + "sailing" ;
            } else if(word.equalsIgnoreCase("break")) {
                pirateTalk = pirateTalk + "scuttle" ;
            } else if(word.equalsIgnoreCase("student")) {
                pirateTalk = pirateTalk + "scallywag" ;
            } else if(word.equalsIgnoreCase("know-it-all")) {
                pirateTalk = pirateTalk + "messdeck lawyer" ;
            } else if(word.equalsIgnoreCase("students")) {
                pirateTalk = pirateTalk + "scallywags" ;
            } else if(word.equalsIgnoreCase("repeat")) {
                pirateTalk = pirateTalk + "walk the blank" ;
            } else if(word.equalsIgnoreCase("sequence")) {
                pirateTalk = pirateTalk + "the Jack o' Coins" ;
            } else if(word.equalsIgnoreCase("selection")) {
                pirateTalk = pirateTalk + "the Jack o' Cups" ;
            } else if(word.equalsIgnoreCase("iteration")) {
                pirateTalk = pirateTalk + "the Jack o' Staves" ;
            } else if(word.equalsIgnoreCase("treasure")) {
                pirateTalk = pirateTalk + "booty" ;
            } else if(word.equalsIgnoreCase("insulting")) {
                pirateTalk = pirateTalk + "bilge-sucking" ;
            } else if(word.equalsIgnoreCase("class")) {
                pirateTalk = pirateTalk + "nipper" ;
            } else if(word.equalsIgnoreCase("object")) {
                pirateTalk = pirateTalk + "nipperkin" ;
            } else if(word.equalsIgnoreCase("classes")) {
                pirateTalk = pirateTalk + "nippers" ;
            } else if(word.equalsIgnoreCase("objects")) {
                pirateTalk = pirateTalk + "nipperkins" ;
            } else if(word.equalsIgnoreCase("insult")) {
                pirateTalk = pirateTalk + "bilge-suck" ;
            } else if(word.equalsIgnoreCase("oops")) {
                pirateTalk = pirateTalk + "blimey" ;
            } else if(word.equalsIgnoreCase("great")) {
                pirateTalk = pirateTalk + "great grand" ;
            } else if(word.equalsIgnoreCase("never")) {
                pirateTalk = pirateTalk + "no nay ne'er" ;
            } else if(word.equalsIgnoreCase("surprised")) {
                pirateTalk = pirateTalk + "taken aback" ;
            } else if(word.equalsIgnoreCase("novice")) {
                pirateTalk = pirateTalk + "buccaneer" ;
            } else if(word.equalsIgnoreCase("food")) {
                pirateTalk = pirateTalk + "salmagundi" ;
            } else if(word.equalsIgnoreCase("drunk")) {
                pirateTalk = pirateTalk + "loaded to the gunwhales" ;
//            } else if(word.equalsIgnoreCase("drunk")) {
//                pirateTalk = pirateTalk + "three sheets to the wind" ;
            } else if(word.equalsIgnoreCase("programming")) {
                pirateTalk = pirateTalk + "seafarin'" ;
            } else if(word.equalsIgnoreCase("language")) {
                pirateTalk = pirateTalk + "clipper" ;
            } else if(word.equalsIgnoreCase("languages")) {
                pirateTalk = pirateTalk + "clippers" ;
            } else if(word.equalsIgnoreCase("experienced")) {
                pirateTalk = pirateTalk + "old salt" ;
            } else if(word.equalsIgnoreCase("boy")) {
                pirateTalk = pirateTalk + "laddy" ;
            } else if(word.equalsIgnoreCase("girl")) {
                pirateTalk = pirateTalk + "lassie" ;
            } else if(word.equalsIgnoreCase("Dermot")) {
                pirateTalk = pirateTalk + "Captain Kidd" ;
            } else if(word.equalsIgnoreCase("classmate")) {
                pirateTalk = pirateTalk + "shipmate" ;
            } else if(word.equalsIgnoreCase("classmates")) {
                pirateTalk = pirateTalk + "shipmates" ;
            } else if(word.equalsIgnoreCase("java")) {
                pirateTalk = pirateTalk + "Tall Ship" ;
            } else if(word.equalsIgnoreCase("god")) {
                pirateTalk = pirateTalk + "by thunder" ;
            } else if(word.equalsIgnoreCase("clever")) {
                pirateTalk = pirateTalk + "smart as paint" ;
            } else if(word.equalsIgnoreCase("theatre")) {
                pirateTalk = pirateTalk + "black spot" ;
            } else if(word.equalsIgnoreCase("lecture")) {
                pirateTalk = pirateTalk + "parley" ;
            } else if(word.equalsIgnoreCase("lectures")) {
                pirateTalk = pirateTalk + "parleys" ;
            } else if(word.equalsIgnoreCase("lecturing")) {
                pirateTalk = pirateTalk + "saucin'" ;
            } else if(word.equalsIgnoreCase("examhall")) {
                pirateTalk = pirateTalk + "execution dock" ;
            } else if(word.equalsIgnoreCase("examhalls")) {
                pirateTalk = pirateTalk + "execution docks" ;
            } else if(word.equalsIgnoreCase("cemetery")) {
                pirateTalk = pirateTalk + "Davy Jones' Locker" ;
            } else if(word.equalsIgnoreCase("die")) {
                pirateTalk = pirateTalk + "feed the fish" ;
            } else if(word.equalsIgnoreCase("died")) {
                pirateTalk = pirateTalk + "fed the fish" ;
            } else if(word.equalsIgnoreCase("leave")) {
                pirateTalk = pirateTalk + "weigh anchor and hoist the mizzen" ;
            } else if(word.equalsIgnoreCase("program")) {
                pirateTalk = pirateTalk + "vessel" ;
            } else if(word.equalsIgnoreCase("bug")) {
                pirateTalk = pirateTalk + "mutiny" ;
            } else if(word.equalsIgnoreCase("crashed")) {
                pirateTalk = pirateTalk + "is marooned" ;
            } else if(word.equalsIgnoreCase("leaving")) {
                pirateTalk = pirateTalk + "weighing anchor and hoisting the mizzen" ;
            } else if(word.equalsIgnoreCase("clean")) {
                pirateTalk = pirateTalk + "shipshape" ;
            } else if(word.equalsIgnoreCase("C++")) {
                pirateTalk = pirateTalk + "son of a biscuit eater" ;
            } else if(word.equalsIgnoreCase("rob")) {
                pirateTalk = pirateTalk + "pillage" ;
            } else if(word.equalsIgnoreCase("computer")) {
                pirateTalk = pirateTalk + "crow's nest" ;
            } else if(word.equalsIgnoreCase("dead")) {
                pirateTalk = pirateTalk + "fed to the fish" ;
            } else if(word.equalsIgnoreCase("cheat")) {
                pirateTalk = pirateTalk + "hornswaggle" ;
            } else if(word.equalsIgnoreCase("drink")) {
                pirateTalk = pirateTalk + "noggin of rum" ;
            } else if(word.equalsIgnoreCase("cheated")) {
                pirateTalk = pirateTalk + "hornswaggled" ;               
            } else if(word.equalsIgnoreCase("restroom")) {
                pirateTalk = pirateTalk + "head" ;
            } else if(word.equalsIgnoreCase("restaurant")) {
                pirateTalk = pirateTalk + "galley" ;
            } else if(word.equalsIgnoreCase("hotel")) {
                pirateTalk = pirateTalk + "fleabag inn" ;
            } else if(word.equalsIgnoreCase("wow")) {
                pirateTalk = pirateTalk + "shiver me timbers" ;
            } else if(word.equalsIgnoreCase("fail")) {
                pirateTalk = pirateTalk + "hang from the yardarm " ;
            } else if(word.equalsIgnoreCase("failed")) {
                pirateTalk = pirateTalk + "hung from the yardarm " ;
            } else {
                pirateTalk = pirateTalk + word ;
            }
            pirateTalk = pirateTalk + " " ;
            startPos = spacePos + 1 ;
            spacePos = phrase.indexOf(" ",startPos) ;
        }
        return "arrr " + pirateTalk + "arrr" ;
    }
    
    public void dateAndTime()
    {
        long time, millisec, second, minute, hour, day, month, year, dayOfWeek, leapYears ;
        time = now() ;
        // determine the millisec part - its whatever is left over after 
        // the full seconds have been accounted for
        millisec = time % 1000 ;
        // now convert time from millsecs to (full) seconds
        time = time / 1000 ;
        // determine the second part - its whatever is left over after
        // the full minutes have been accounted for
        second = time % 60 ;
        // now convert time from seconds to (full) minutes
        time = time / 60 ;
        // determine the minute part - its whatever is left over after
        // the full houts have been accounted for
        minute = time % 60 ;
        // now convert time from minutes to (full) hours
        time = time / 60 ;
        // determine the hour part - its whatever is left over after
        // the full days have been accounted for
        hour = time % 24 ;
        // now convert time from hours to (full) days
        time = time / 24 ;
        // calc the day of the week
        // 1 Jan 1970 was Thursday. It is day 0
        // if we mod (%) the days and get 0 its Thursday, 1 Fri, 2 Sat etc.
        dayOfWeek = time % 7 ;
        // estimate (no leap year days considered) of day - its whatever 
        // is left over after the full years have been accounted for
        day = time % 365 ;
        // determine the year part - its whatever is left over after
        // the full days have been accounted for        
        year = time / 365 ;
        leapYears = (year+1) / 4 ;
        day = day - leapYears ;
        month = 1 ;
        if(day > 31) { //Jan
            day -= 31 ;
            month++ ;
        }
        if((1970+year)%4 == 0) { //Feb leap year
            if(day > 29) {
                day -= 29 ;
                month++ ;
            }
        } else {                 // Feb normal
            if(day > 28) {
                day -= 28 ;
                month++ ;
            }
        }
        if(day > 31) { // Mar
            day -= 31 ;
            month++ ;
        }
        if(day > 30) { // Apr
            day -= 30 ;
            month++ ;
        }
        if(day > 31) { // May
            day -= 31 ;
            month++ ;
        }
        if(day > 30) { // Jun
            day -= 30 ;
            month++ ;
        }
        if(day > 31) { // Jul
            day -= 31 ;
            month++ ;
        }
        if(day > 31) { // Aug
            day -= 30 ;
            month++ ;
        }
        if(day > 30) { // Sep
            day -= 30 ;
            month++ ;
        }
        if(day > 31) { // Oct
            day -= 31 ;
            month++ ;
        }
        if(day > 30) { // Nov
            day -= 30 ;
            month++ ;
        }
        //if(day > 31) { // Dec
        //    day -= 31 ;
        //    month++ ;
        //}

        // what day number is left has to be the day of the month
        // that's why Dec can be left out above
        if(dayOfWeek == 0) {
            System.out.print("Thu ") ;
        } else if(dayOfWeek == 1) {
            System.out.print("Fri ") ;
        } else if(dayOfWeek == 2) {
            System.out.print("Sat ") ;
        } else if(dayOfWeek == 3) {
            System.out.print("Sun ") ;
        } else if(dayOfWeek == 4) {
            System.out.print("Mon ") ;
        } else if(dayOfWeek == 5) {
            System.out.print("Tue ") ;
        } else if(dayOfWeek == 6) {
            System.out.print("Wed ") ;
        }
        System.out.print(day) ;
        if(month == 1) {
            System.out.print(" Jan ") ;
        } else if(month == 2) {
            System.out.print(" Feb ") ;
        } else if(month == 3) {
            System.out.print(" Mar ") ;
        } else if(month == 4) {
            System.out.print(" Apr ") ;
        } else if(month == 5) {
            System.out.print(" May ") ;
        } else if(month == 6) {
            System.out.print(" Jun ") ;
        } else if(month == 7) {
            System.out.print(" Jul ") ;
        } else if(month == 8) {
            System.out.print(" Aug ") ;
        } else if(month == 9) {
            System.out.print(" Sep ") ;
        } else if(month == 10) {
            System.out.print(" Oct ") ;
        } else if(month == 11) {
            System.out.print(" Nov ") ;
        } else if(month == 12) {
            System.out.print(" Dec ") ;
        }
        System.out.print(1970+year + " ") ;
        System.out.print(hour + ":") ;
        System.out.print(minute + ":") ;
        System.out.print(second + ":") ;
        System.out.println(millisec) ;
    }
}
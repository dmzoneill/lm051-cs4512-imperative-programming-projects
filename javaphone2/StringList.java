import java.util.*;
import java.io.* ;
//****************************
//StringList.java
//****************************

public class StringList
{
    private String[] list ; 
    private int stringCount ;
    
    // Default Constructor
    public StringList()
    {
        list = new String[50] ;
        stringCount = 0 ;
    }

    // Constructor
    public StringList(int capacity)
    {
        list = new String[capacity] ;
        stringCount = 0 ;
    }

    // Constructor
    public StringList(String filename)
    {
        initialiseFromFile(filename) ;
        if(list != null) {
            stringCount = list.length ;
        } else {
            list = new String[50] ;
            stringCount = 0  ;
        }
    }

    // Constructor
    public StringList(int additionalCapacity, String supplementaryStrings)
    {
        /*
         * FROM http://ogden.basic-english.org/
         * 
         * 
         * If one were to take the 25,000 word Oxford Pocket English Dictionary and take away the redundancies
         * of our rich language and eliminate the words that can be made by putting together simpler words, 
         * we find that 90% of the concepts in that dictionary can be achieved with 850 words. The shortened 
         * list makes simpler the effort to learn spelling and pronunciation irregularities. The rules of usage 
         * are identical to full English so that the practitioner communicates in perfectly good, but simple, 
         * English.
         * We call this simplified language Basic English, the developer is Charles K. Ogden, and was released 
         * in 1930 with the book: Basic English: A General Introduction with Rules and Grammar. He founded the 
         * Orthological Institute to develop the tools for teaching Basic English. His most famous associate, 
         * I.A. Richards, led the effort in the Orient, which uses the techniques to this day.
         * 
         * 
         * The list of words as Ogden categorised them is at  http://ogden.basic-english.org/words.html 
         * and , in alphabetical order at                     http://ogden.basic-english.org/wordalph.html
         */
        
        
        String basicEnglish = "a able about account acid across act addition adjustment " ;
        basicEnglish = basicEnglish + "advertisement after again against agreement air all almost among " ;
        basicEnglish = basicEnglish + "amount amusement and angle angry animal answer ant any apparatus " ;
        basicEnglish = basicEnglish + "apple approval arch argument arm army art as at attack attempt " ;
        basicEnglish = basicEnglish + "attention attraction authority automatic awake baby back bad bag " ;
        basicEnglish = basicEnglish + "balance ball band base basin basket bath be beautiful because bed bee " ;
        basicEnglish = basicEnglish + "before behaviour belief bell bent berry between bird birth bit bite " ;
        basicEnglish = basicEnglish + "bitter black blade blood blow blue board boat body boiling bone book " ;
        basicEnglish = basicEnglish + "boot bottle box boy brain brake branch brass bread breath brick " ;
        basicEnglish = basicEnglish + "bridge bright broken brother brown brush bucket building bulb burn " ;
        basicEnglish = basicEnglish + "burst business but butter button by cake camera canvas card care " ;
        basicEnglish = basicEnglish + "carriage cart cat cause certain chain chalk chance change cheap " ;
        basicEnglish = basicEnglish + "cheese chemical chest chief chin church circle clean clear clock " ;
        basicEnglish = basicEnglish + "cloth cloud coal coat cold collar colour comb come comfort committee " ;
        basicEnglish = basicEnglish + "common company comparison competition complete complex condition " ;
        basicEnglish = basicEnglish + "connection conscious control cook copper copy cord cork cotton cough " ;
        basicEnglish = basicEnglish + "country cover cow crack credit crime cruel crush cry cup cup current " ;
        basicEnglish = basicEnglish + "curtain curve cushion damage danger dark daughter day dead dear death " ;
        basicEnglish = basicEnglish + "debt decision deep degree delicate dependent design desire " ;
        basicEnglish = basicEnglish + "destruction detail development different digestion direction dirty " ;
        basicEnglish = basicEnglish + "discovery discussion disease disgust distance distribution division " ;
        basicEnglish = basicEnglish + "do dog door doubt down drain drawer dress drink driving drop dry dust " ;
        basicEnglish = basicEnglish + "ear early earth east edge education effect egg elastic electric end " ;
        basicEnglish = basicEnglish + "engine enough equal error even event ever every example exchange " ;
        basicEnglish = basicEnglish + "existence expansion experience expert eye face fact fall false  " ;
        basicEnglish = basicEnglish + "family far farm fat father fear feather feeble feeling female fertile " ;
        basicEnglish = basicEnglish + "fiction field fight finger fire first fish fixed flag flame flat " ;
        basicEnglish = basicEnglish + "flight floor flower fly fold food foolish foot for force fork form " ;
        basicEnglish = basicEnglish + "forward fowl frame free frequent friend from front fruit full future " ;
        basicEnglish = basicEnglish + "garden general get girl give glass glove go goat gold good government " ;
        basicEnglish = basicEnglish + "grain grass great green grey grip group growth guide gun hair hammer " ;
        basicEnglish = basicEnglish + "hand hanging happy harbour hard harmony hat hate have he head healthy " ;
        basicEnglish = basicEnglish + "hear hearing heart heat help high history hole hollow hook hope horn " ;
        basicEnglish = basicEnglish + "horse hospital hour house how humour I ice idea if ill important " ;
        basicEnglish = basicEnglish + "impulse in increase industry ink insect instrument insurance interest " ;
        basicEnglish = basicEnglish + "invention iron island jelly jewel join journey judge jump keep kettle " ;
        basicEnglish = basicEnglish + "key kick kind kiss knee knife knot knowledge land language last late " ;
        basicEnglish = basicEnglish + "laugh law lead leaf learning leather left leg let letter level " ;
        basicEnglish = basicEnglish + "library lift light like limit line linen lip liquid list little " ;
        basicEnglish = basicEnglish + "living lock long look loose loss loud love low machine make male man " ;
        basicEnglish = basicEnglish + "manager map mark market married mass match material may meal measure " ;
        basicEnglish = basicEnglish + "meat medical meeting memory metal middle military milk mind mine " ;
        basicEnglish = basicEnglish + "minute mist mixed money monkey month moon morning mother motion " ;
        basicEnglish = basicEnglish + "mountain mouth move much muscle music nail name narrow nation natural " ;
        basicEnglish = basicEnglish + "near necessary neck need needle nerve net new news night no noise " ;
        basicEnglish = basicEnglish + "normal north nose not note now number nut observation of off offer " ;
        basicEnglish = basicEnglish + "office oil old on only open operation opinion opposite or orange " ;
        basicEnglish = basicEnglish + "order organization ornament other out oven over owner page pain paint " ;
        basicEnglish = basicEnglish + "paper parallel parcel part past paste payment peace pen pencil person " ;
        basicEnglish = basicEnglish + "physical picture pig pin pipe place plane plant plate play please " ;
        basicEnglish = basicEnglish + "pleasure plough pocket point poison polish political poor porter " ;
        basicEnglish = basicEnglish + "position possible pot potato powder power present price print prison " ;
        basicEnglish = basicEnglish + "private probable process produce profit property prose protest public " ;
        basicEnglish = basicEnglish + "pull pump punishment purpose push put quality question quick quiet " ;
        basicEnglish = basicEnglish + "quite rail rain range rat rate ray reaction reading ready reason " ;
        basicEnglish = basicEnglish + "receipt record red regret regular relation religion representative " ;
        basicEnglish = basicEnglish + "request respect responsible rest reward rhythm rice right ring river " ;
        basicEnglish = basicEnglish + "road rod roll roof room root rough round rub rule run sad safe sail " ;
        basicEnglish = basicEnglish + "salt same sand say scale school science scissors screw sea seat " ;
        basicEnglish = basicEnglish + "second secret secretary see seed seem selection self send sense " ;
        basicEnglish = basicEnglish + "separate serious servant sex shade shake shame sharp sheep shelf ship " ;
        basicEnglish = basicEnglish + "shirt shock shoe short shut side sign silk silver simple sister size " ;
        basicEnglish = basicEnglish + "skin skirt sky sleep slip slope slow small smash smell smile smoke " ;
        basicEnglish = basicEnglish + "smooth snake sneeze snow so soap society sock soft solid some son " ;
        basicEnglish = basicEnglish + "song sort sound soup south space spade special sponge spoon spring " ;
        basicEnglish = basicEnglish + "square stage stamp star start statement station steam steel stem step " ;
        basicEnglish = basicEnglish + "stick sticky stiff still stitch stocking stomach stone stop store " ;
        basicEnglish = basicEnglish + "story straight strange street stretch strong structure substance such " ;
        basicEnglish = basicEnglish + "sudden sugar suggestion summer sun support surprise sweet swim system " ;
        basicEnglish = basicEnglish + "table tail take talk tall taste tax teaching tendency test than that " ;
        basicEnglish = basicEnglish + "the then theory there thick thin thing this thought thread throat " ;
        basicEnglish = basicEnglish + "through through thumb thunder ticket tight till time tin tired to toe " ;
        basicEnglish = basicEnglish + "together tomorrow tongue tooth top touch town trade train transport " ;
        basicEnglish = basicEnglish + "tray tree trick trouble trousers true  turn twist umbrella under unit " ;
        basicEnglish = basicEnglish + "up use value verse very vessel view violent voice waiting walk wall " ;
        basicEnglish = basicEnglish + "war warm wash waste watch water wave wax way weather week weight well " ;
        basicEnglish = basicEnglish + "west wet wheel when where while whip whistle white who why wide will " ;
        basicEnglish = basicEnglish + "wind window wine wing winter wire wise with woman wood wool word work " ;

        String[] basicEnglishWords = basicEnglish.split(" ") ;
        String[] supplementary = supplementaryStrings.split(" ") ;
        list = new String[basicEnglishWords.length + supplementary.length + additionalCapacity] ;
        stringCount = 0 ;
        int i = 0 ;
        while(i < basicEnglishWords.length) {
            list[stringCount] = basicEnglishWords[i] ;
            stringCount++ ;
            i++ ;
        }
        i = 0 ;
        while(i < supplementary.length) {
            list[stringCount] = supplementary[i] ;
            stringCount++ ;
            i++ ;
        }
    }


    public void insertArrived(String str)
    {
        // Check if there is space for (at least) one more item
        if(stringCount < list.length) {
            // There is space so do the insertion
            //
            // Store the new item in the next available position
            list[stringCount] = new String(str) ;
            // Count the item just inserted            
            stringCount++ ;   
        }
    }

    public void insertOrdered(String str)
    {
        int i, j ;
        // Check if there is space for (at least) one more item
        if(stringCount < list.length) {
            // There is space so do the insertion
            //
            // Determine the correct position for the new item
            i = 0 ;
            while(i < stringCount && str.compareToIgnoreCase(list[i]) > 0 ) {
                i++ ;
            }
            if(i < stringCount) {
                // The new item needs to be inserted at a position already
                // occupied by an entry in the list
                // Move the existing items down one position to create a
                // gap for the new item
                j = stringCount ;
                while(j > i) {
                    list[j] = list[j-1] ;
                    j-- ;
                }
                // Now that the gap has been created insert the new item
                // in its correct position
                list[i] = new String(str) ;
            } else {
                // The new item is bigger (i.e. comes after) the biggest
                // item currently in the list so insert the new item at the 
                // end of the list (i.e. the new item becomes the last entry
                // in the list)
                list[stringCount] = new String(str) ;
            }
            // Count the item just inserted
            stringCount++ ;
        }
    } 
    
    public void insertArrivedNoDuplicates(String str)
    {
        // Check if there is space for (at least) one more item
        if(stringCount < list.length) {
            // There is space so do the insertion
            //
            // FIRST - make sure the new item is NOT already in the list
            if(find(str) == -1) {
                // Its not in the list
                //
                // Store the new item in the next available position
                list[stringCount] = new String(str) ;
                // Count the item just inserted            
                stringCount++ ; 
            }
        }
    }

    public void insertOrderedNoDuplicates(String str)
    {
        int i, j ;
        // Check if there is space for (at least) one more item
        if(stringCount < list.length) {
            // There is space so do the insertion
            //
            // Determine the correct position for the new item
            i = 0 ;
            while(i < stringCount && str.compareToIgnoreCase(list[i]) > 0 ) {
                i++ ;
            }
            if(i < stringCount) {
                // The new item needs to be inserted at a position already
                // occupied by an entry in the list
                //
                // Make sure this position DOESN'T ALREADY store a value
                // that is the same as the new item
                if(str.compareToIgnoreCase(list[i]) != 0) {
                    // We have confirmed that the new item
                    // is NOT a duplicate value
                    //
                    // SO...do the insertion
                    //
                    // Move the existing items down one position to create a
                    // gap for the new item
                    j = stringCount ;
                    while(j > i) {
                        list[j] = list[j-1] ;
                        j-- ;
                    }
                    // Now that the gap has been created insert the new item
                    // in its correct position
                    list[i] = new String(str) ;
                    // Count the item just inserted            
                    stringCount++ ; 
                }
                // NOTE
                // ----
                // If we reach this point in the code we know that there is
                // space for a new item but that the new item is a duplicate
                // so we do nothing (i.e. we ignore the new item)
            } else {
                // The new item is bigger (i.e. comes after) the biggest
                // item currently in the list so insert the new item at the 
                // end of the list (i.e. the new item becomes the last entry
                // in the list)
                list[stringCount] = new String(str) ;
                // Count the item just inserted            
                stringCount++ ;
            }
        }
    } 
    
    public int find(String str)
    {
        int i ;
        // Start searching from the beginning of the list
        i = 0 ;
        // Keep searching while
        //     there are still some entries to be checked 
        //        |          AND  the current item is not the one we are looking for
        //        |           |      |
        //        |           |      |
        //        V           V      V
        while(i < stringCount && str.compareToIgnoreCase(list[i]) != 0 ) {
            // move on to the next item
            i++ ;
        }
        // When we reach tis point in the code we know the 'while' has stopped. The
        // question is - WHAT CAUSED IT TO STOP?
        //
        // If 'i < stringCount' is STILL TRUE it means that the 'while' stopped because
        // 'str.compareToIgnoreCase(list[i])' was equal to 0 (i.e. the item at position 'i'
        // was equal to 'str' - WE FOUND IT)
        if(i < stringCount) {
            // WE MUST HAVE FOUND IT - return the position we found it at.
            return i ;
        } else {
            // WE DIDN'T FIND - return a -1 to signal/indicate that we didn't find it
            return -1 ;
        }
    }

    public void remove(String str)
    {
        int i, strIndex ;
        // First establish whether the item is in the list
        //
        // Use the find method to locate where the item is in the list
        strIndex = find(str) ;
        if(strIndex != -1) {
            // If the item was found in the list then we should remove it
            //
            // We can remove it by moving all the items below it up ONE position
            // starting from the one that comes immediately after the item to be removed
            i = strIndex + 1 ;
            // Now move them all up one at a time
            while(i < stringCount) {
                list[i-1] = list[i] ;
                i++ ;
            }
            // Now decrement the count because we have removed one item
            stringCount-- ;
        }
    }
    
    public void displayFirstToLast()
    {
        int i ;
        i = 0 ;
        while(i < stringCount) {
            System.out.println(list[i]) ;
            i++ ;
        }
    }
       
    public void displayLastToFirst()
    {
        int i ;
        i = stringCount - 1 ;
        while(i >= 0) {
            System.out.println(list[i]) ;
            i-- ;
        }
    }
    
    
    public int capacity()
    {   return list.length ;
    }

    public int length()
    {   return list.length ;
    }
    
    public int used()
    {   return stringCount ;
    }  
    
    public String item(int i)
    {
        if(i >= 0 && i < stringCount) {
            return list[i] ;
        } else {
            return "" ;
        }
    }
    
    public StringList filteredBy(String filter)
    {
        int first = 0, last = 0, i ;
        StringList filterList ;
        filter = filter.toLowerCase() ;
        i = 0 ;
        while(i < stringCount && list[i].toLowerCase().startsWith(filter) == false) {
            i++ ;
        }
        if(i < stringCount) {
            first = i ;
            i++ ;
            while(i < stringCount && list[i].toLowerCase().startsWith(filter) == true) {
                i++ ;
            }
            last = i - 1 ;
            filterList = new StringList(last-first+1) ;
            i = first ;
            while(i <= last) {
                filterList.insertArrived(list[i]) ;
                i++ ;
            }
        }else {
            filterList = null ;
        }
        return filterList ;
    }
    
    private void initialiseFromFile(String filename)
    {
        // BasicEnglish.txt - the 850 words of Basic English
        // BNCwords.txt - "the 6,318 words with more than 800 occurrences in the whole 100M-word BNC"
        try {
            FileReader aFileReader = new FileReader(filename);
            BufferedReader aBufferReader = new BufferedReader(aFileReader);
            String lineFromFile;
            ArrayList<String> words = new ArrayList<String>();
            while ((lineFromFile = aBufferReader.readLine()) != null)
            {  
                words.add(lineFromFile);
            }
            aBufferReader.close();
            aFileReader.close();
            list = words.toArray(new String[words.size()]) ;
        }
        catch(IOException x)
        {
            list =  null ;
        }
    } 
}
//****************************
//WordList.java
//****************************
public class WordList
{
    private String[] dictionary ; 
    private int wordCount ;
    
    // Default Constructor
    public WordList()
    {
        dictionary = new String[50] ;

    }

    // Constructor
    public WordList(int capacity)
    {
        dictionary = new String[capacity] ;

    }

    // Constructor
    public WordList(int additionalCapacity, String supplementaryWords)
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
        String[] supplementary = supplementaryWords.split(" ") ;
        dictionary = new String[basicEnglishWords.length + supplementary.length + additionalCapacity] ;
        wordCount = 0 ;
        int i = 0 ;
        while(i < basicEnglishWords.length) {
            dictionary[wordCount] = basicEnglishWords[i] ;
            wordCount++ ;
            i++ ;
        }
        i = 0 ;
        while(i < supplementary.length) {
            dictionary[wordCount] = supplementary[i] ;
            wordCount++ ;
            i++ ;
        }
    }


    public void insert(String word)
    {
        if(wordCount < dictionary.length) {
            dictionary[wordCount] = new String(word) ;
            wordCount++ ;   
        }
    }

    public int find(String word)
    {
        int i ;
        i = 0 ;
        while(i < wordCount && word.compareToIgnoreCase(dictionary[i]) != 0 ) {
            i++ ;
        }
        if(i < wordCount) {
            return i ;
        } else {
            return -1 ;
        }
    }

    public void display()
    {
        int i ;
        i = 0 ;
        while(i < wordCount) {
            System.out.println(dictionary[i]) ;
            i++ ;
        }
    }
       
    public int capacity()
    {   return dictionary.length ;
    }

    public int used()
    {   return wordCount ;
    }
}
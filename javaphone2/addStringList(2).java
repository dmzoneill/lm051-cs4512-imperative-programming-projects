import java.util.*;
import java.io.* ;

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
package player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



/**
 * @author David O Neill 0813001
 */

public class Mp3
{
	String spacer = new String("   ");
	
	public Mp3()
	{

	}
	
	
	/**
	 * Takes a file and ses up the data structure population
	 * @param filename
	 */
	
	public Mp3(String filename)
	{
		this.initialiseFromFile(filename);
	}
	
	
	/**
	 * Inserts an enetry into the data structure
	 * @param filename
	 */

    public void insert(String filename)
    {    	
    	if(Node.insert(filename)==false)
    		System.out.println(spacer + spacer + "Sucessfully inserted " + filename + " into the library");
    	else
    		System.out.println(spacer + spacer + "Duplicate " + filename + " detected");
    }

    
    /**
     * Removes Unwanted tracks given an artist and track name from the data structure
     * @param track the name of the track to remove
     * @param artist the name of the artist to remove
     */
    
    public void remove(String track, String artist)
    {     	
    	Node[] nodes = Node.toArray();
    	
    	String item;
    	String[] info;
    	String tempTrack, tempArtist;
    	
    	boolean success = false;
    	
    	String inputArtist = artist.trim();
    	String inputTrack = track.trim();
    	
    	System.out.println("");
   	
    	for(int y=0; y<nodes.length; y++)
    	{
    		item = nodes[y].getNodeValue();
    		info = item.split("\\[");
    		tempTrack = info[0];
    		info = info[1].split("]");
    		tempArtist = info[0];
    		
    		if(inputArtist.equalsIgnoreCase(tempArtist) && inputTrack.equalsIgnoreCase(tempTrack))
    		{
    			Node.remove(nodes[y]);
    			success = true;
    		}
    	}
    	
    	if(success==true)
    		System.out.println(spacer + spacer + "Sucessfully removed " + inputTrack + " by "  + inputArtist);
    	else
    		System.out.println(spacer + spacer + "Unable to find " + inputTrack + " by "  + inputArtist);
    	
    	System.out.println("");
    }
    
    
    /**
     * Play all track in the list either in sequence or randomly. Passing the value true as a parameter plays the tracks randomly.
     * If random is true, the linked list is fetched as an array, the array is then randomized, the array items still point to the nodes
     * Otherwise we iterate directly with the linked list
     * @param random
     */    

    public void play(boolean random)
    {   	
    	
    	Node[] nodes = Node.toArray();
    	
    	if(random==true)
    	{    		
    		nodes = Node.randomizeNodeArray(nodes);
    	}

        System.out.println("");
        	
       	for(int h=0;h<nodes.length;h++)
        {
            System.out.println(spacer + spacer + nodes[h].getNodeValue());        	
        }    	
       		
       	System.out.println("");
    	
    }


    /**
     * PlayTrack finds files in the list using the track name or a portion of the track name (i.e. some of the letters at the start of the name) and plays those files randomly or in sequence.
     * @param trackNamePortion
     * @param random
     */
    
    public void playTrack(String trackNamePortion, boolean random)
    {
    	Node[] nodes = Node.toArray(0, trackNamePortion);
    	    	
    	if(random==true)
    	{
    		nodes = Node.randomizeNodeArray(nodes);
    	}
    	
    	System.out.println("");
    	
   		for(int h=0;h<nodes.length;h++)
        {
        	System.out.println(spacer + spacer + nodes[h].getNodeValue());        	
    	}    	
   		
   		System.out.println("");
    }


    /**
     * PlayArtist finds ALL the files in the playlist for the artist specified and plays them either in sequence or randomly.
     * @param artistName
     * @param random
     */

    public void playArtist(String artistName, boolean random)
    {
    	Node[] nodes = Node.toArray(1, artistName);
    	
    	if(random==true)
    	{
    		nodes = Node.randomizeNodeArray(nodes);
    	}
    	
    	System.out.println("");
    	
   		for(int h=0;h<nodes.length;h++)
        {
        	System.out.println(spacer + spacer + nodes[h].getNodeValue());        	
    	}    	
   		
   		System.out.println("");
    }

    
    /**
     * PlayGenre finds ALL the files in the playlist for the genre specified and plays them either in sequence or randomly.
     * @param genreName
     * @param random
     */

    public void playGenre(String genreName, boolean random)
    {   	
    	Node[] nodes = Node.toArray(2, genreName);
    	
    	if(random==true)
    	{
    		nodes = Node.randomizeNodeArray(nodes);
    	}
    	
    	System.out.println("");
    	
   		for(int h=0;h<nodes.length;h++)
        {
        	System.out.println(spacer + spacer + nodes[h].getNodeValue());        	
    	}   
   		
   		System.out.println("");
    }
    

    /**
     * Shows the mp3 Library
     */

    public void showList()
    {
    	System.out.println("");    	
    	
    	Node iter = Node.getHeadNode();
		
		while(iter != null)
		{
			System.out.println(spacer + spacer + iter.getNodeValue()); 
			iter = iter.getNextNode();
		}
    	    	
   		System.out.println("");
    }

    
    /**
     * Shows found tracks given a track name portion
     * @param trackName String
     */

    public void showTrack(String trackName)
    {    	
    	System.out.println("");    	
    	
    	Node iter = Node.getHeadNode();
		
		while(iter != null)
		{
			if(Node.splitNodeValue(iter)[0].toLowerCase().startsWith(trackName.toLowerCase()))
			{
				System.out.println(spacer + spacer + iter.getNodeValue()); 
			}
			iter = iter.getNextNode();
		}
    	    	
   		System.out.println("");
    }

    
    /**
     * Shows found tracks given an artist
     * @param artistName String
     */

    public void showArtist(String artistName)
    {
    	System.out.println("");    	
    	
    	Node iter = Node.getHeadNode();
		
		while(iter != null)
		{
			if(Node.splitNodeValue(iter)[1].toLowerCase().startsWith(artistName.toLowerCase()))
			{
				System.out.println(spacer + spacer + iter.getNodeValue()); 
			}
			iter = iter.getNextNode();
		}
    	    	
   		System.out.println("");
    }

    
    /**
     * Shows found tracks given a type of genre
     * @param genreName String
     */

    public void showGenre(String genreName)
    {
    	System.out.println("");    	
    	
    	Node iter = Node.getHeadNode();
		
		while(iter != null)
		{
			if(Node.splitNodeValue(iter)[2].toLowerCase().startsWith(genreName.toLowerCase()))
			{
				System.out.println(spacer + spacer + iter.getNodeValue()); 
			}
			iter = iter.getNextNode();
		}
    	    	
   		System.out.println("");
    }    
    
    
    /**
     * Loads the database from a file
     * @param filename
     */
    
    private void initialiseFromFile(String filename)
    {
        try 
        {
            FileReader aFileReader = new FileReader(filename);
            BufferedReader aBufferReader = new BufferedReader(aFileReader);
            String lineFromFile;
            while ((lineFromFile = aBufferReader.readLine()) != null)
            {  
                this.insert(lineFromFile);
            }
            aBufferReader.close();
            aFileReader.close();
        }
        catch(IOException x)
        {
            System.out.println("Unable to read file");
        }
    } 
    
    
    /**
     * Dumps the database to the file out.txt
     *
     */
    
    public void dump()
    {
    	String newline = System.getProperty("line.separator");
    	try
    	{
    	   	FileWriter fstream = new FileWriter("out.txt");
    	   	BufferedWriter out = new BufferedWriter(fstream);
    	   	Node iter = Node.getHeadNode();
    	   	while(iter!=null)
    	   	{
    	   		out.write(iter.getNodeValue() + newline);
    	   		iter = iter.getNextNode();
    	   	}
    	   	out.close();
    	}
    	catch (Exception e)
    	{
    		System.out.println("Error: " + e.getMessage());
    	}    		    
    }
    
}

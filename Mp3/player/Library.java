package player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class Library
{
	private ArrayList mp3Library, playList;
	private int currentlyPlayingItem;
	
	
	/**
	 * Constructor, 
	 * Sets up the main mp3 library
	 * Sets up a playlist library for filtereed songs
	 * Sets currently playing item to 0
	 * Note* Play all tracks uses the mp3library Directly
	 * Mp3Database has its own song count and item index for when working with (filtered / searched ) tracks
	 */

	public Library()
	{
		this.mp3Library = new ArrayList();
		this.playList = new ArrayList();
		this.currentlyPlayingItem = 0;
	}
	
   
	/**
     * Adds tracks to the library (No duplicates)
     * @param filename 
     * @return boolean 
     */

    public boolean insert(String filename)
    {
    	if(this.mp3Library.contains(filename.toLowerCase()))
    	{
    		return false;
    	}
    	else
    	{
    		this.mp3Library.add(filename.toLowerCase());
    		return true;
    	}    	
    }

    
    /**
     * Removes Unwanted tracks given an artist and track name
     * @param track the name of the track to remove
     * @param artist the name of the artist to remove
     * @return boolean success
     */
    
    public boolean remove(String track, String artist)
    {
    	int librarySize = this.mp3Library.size();
    	
    	String item;
    	String[] info;
    	String tempTrack, tempArtist;
    	
    	boolean success = false;
    	
    	String inputArtist = artist.trim();
    	String inputTrack = track.trim();
   	
    	for(int y=0; y< librarySize; y++)
    	{
    		item = this.mp3Library.get(y).toString();
    		info = item.split("\\[");
    		tempTrack = info[0];
    		info = info[1].split("]");
    		tempArtist = info[0];
    		
    		if(inputArtist.equalsIgnoreCase(tempArtist) && inputTrack.equalsIgnoreCase(tempTrack))
    		{
    			this.mp3Library.remove(y);
    			librarySize--;
    			success = true;
    		}
    	}
    	return success;
    }


    /**
     * Play returns ALL the tracks in the list either in sequence or randomly. Passing the value true as a parameter plays the tracks randomly.
     * Note* Play all tracks uses the mp3library Directly
	 * Mp3Database has its own song count and item index for when working with (filtered / searched ) tracks
     * @param random
     * @return String next item to play
     */

    public String play(boolean random)
    {
    	int librarySize = this.mp3Library.size();
    	int currentItem = this.currentlyPlayingItem;
    	Random item = new Random();    	
    	
    	if(random==true)
    	{
    		// return a random item to play
    		int randomItem = item.nextInt(librarySize);
    		return this.mp3Library.get(randomItem).toString();
    	}
    	else 
    	{    		
    		this.currentlyPlayingItem++;
    		if(this.currentlyPlayingItem==this.mp3Library.size())
    		{
    			// back to the start of the playlist
    			this.currentlyPlayingItem=0;
    		}
    		return this.mp3Library.get(currentItem).toString();    		
    	}
    }


    /**
     * PlayTrack finds files in the list using the track name or a portion of the track name (i.e. some of the letters at the start of the name) and returns those files randomly or in sequence.
     * @param trackNamePortion
     * @param random
     * @return playlist ArrayList
     */
    
    public ArrayList playTrack(String trackNamePortion, boolean random)
    {
    	this.sortLibrary();
    	int librarySize = this.mp3Library.size();
    	String[] temp;
    	String item;
    	
    	this.playList.clear();
    	
    	for(int h=0;h<librarySize;h++)
    	{
    		item = this.mp3Library.get(h).toString();
    		temp = item.split("\\[");
    		item = temp[0].toLowerCase();
    		if(item.indexOf(trackNamePortion.toLowerCase())>=0)
    		{
    			this.playList.add(this.mp3Library.get(h));
    		}
    	}   
    	
    	if(random==true)
    	{
    		Collections.shuffle(this.playList);
    		return this.playList;
    	}
    	else 
    	{
    		return this.playList;
    	}    	
    }


    /**
     * PlayArtist finds ALL the files in the playlist for the artist specified and returns them either in sequence or randomly.
     * @param artistName
     * @param random
     * @return playlist ArrayList
     */

    public ArrayList playArtist(String artistName, boolean random)
    {
    	this.sortLibrary();
    	int librarySize = this.mp3Library.size();
    	String[] temp;
    	String item;
    	
    	this.playList.clear();
    	
    	for(int h=0;h<librarySize;h++)
    	{
    		item = this.mp3Library.get(h).toString();
    		temp = item.split("\\[");
    		item = temp[1].toLowerCase();
    		temp = item.split("]");
    		item = temp[0];
    		if(item.indexOf(artistName.toLowerCase())>=0)
    		{
    			this.playList.add(this.mp3Library.get(h));
    		}
    	}
    	
    	
    	if(random==true)
    	{
    		Collections.shuffle(this.playList);
    		return this.playList;
    	}
    	else 
    	{
    		return this.playList;
    	}    	
    }

    
    /**
     * PlayGenre finds ALL the files in the playlist for the genre specified and returns them either in sequence or randomly.
     * @param genreName
     * @param random
     * @return playlist ArrayList
     */

    public ArrayList playGenre(String genreName, boolean random)
    {
    	this.sortLibrary();
    	int librarySize = this.mp3Library.size();
    	String[] temp;
    	String item;
    	
    	this.playList.clear();
    	
    	for(int h=0;h<librarySize;h++)
    	{
    		item = this.mp3Library.get(h).toString();
    		temp = item.split("]");
    		item = temp[1].toLowerCase();
    		if(item.indexOf(genreName.toLowerCase())>=0)
    		{
    			this.playList.add(this.mp3Library.get(h));
    		}
    	}
    	
    	
    	if(random==true)
    	{
    		Collections.shuffle(this.playList);
    		return this.playList;
    	}
    	else 
    	{
    		return this.playList;
    	}    
    }
    

    /**
     * Gets the mp3 Library
     * @return mp3Library ArrayList
     */

    public ArrayList showList()
    {
    	return this.mp3Library;
    }

    

    /**
     * Returns found tracks given a track name
     * @param trackName String
     * @return Playlist ArrayList  
     */

    public ArrayList showTrack(String trackName)
    {
    	this.sortLibrary();
    	int librarySize = this.mp3Library.size();
    	
    	this.playList.clear();
    	
    	String item;
    	String[] temp;
    	
    	for(int h=0;h<librarySize;h++)
    	{
    		item = this.mp3Library.get(h).toString();
    		temp = item.split("\\[");
    		item = temp[0].toLowerCase();

    		if(item.indexOf(trackName.toLowerCase())>=0)
    		{
    			this.playList.add(this.mp3Library.get(h));
    		}
    	}
    	
    	return this.playList;
    }

    
    /**
     * Returns found tracks given an artist
     * @param artistName String
     * @return Playlist ArrayList 
     */

    public ArrayList showArtist(String artistName)
    {
    	this.sortLibrary();
    	int librarySize = this.mp3Library.size();
    	
    	this.playList.clear();
    	
    	String item;
    	String[] temp;
    	
    	for(int h=0;h<librarySize;h++)
    	{
    		item = this.mp3Library.get(h).toString();
    		temp = item.split("\\[");
    		item = temp[1].toLowerCase();
    		temp = item.split("]");
    		item = temp[0].toLowerCase();

    		if(item.indexOf(artistName.toLowerCase())>=0)
    		{
    			this.playList.add(this.mp3Library.get(h));
    		}
    	}
    	
    	return this.playList;
    }

    
    /**
     * Returns found tracks given a type of genre
     * @param genreName String
     * @return Playlist ArrayList 
     */

    public ArrayList showGenre(String genreName)
    {
    	this.sortLibrary();
    	int librarySize = this.mp3Library.size();
    	
    	this.playList.clear();
    	
    	String item;
    	String[] temp;
    	
    	for(int h=0;h<librarySize;h++)
    	{
    		item = this.mp3Library.get(h).toString();
    		temp = item.split("]");
    		item = temp[1].toLowerCase();

    		if(item.indexOf(genreName.toLowerCase())>=0)
    		{
    			this.playList.add(this.mp3Library.get(h));
    		}
    	}
    	
    	return this.playList;
    }
    
    
    /**
     * Sorts the mp3 Library
     */
    
    public void sortLibrary()
    {
    	Collections.sort(this.mp3Library);
    }

    
    /**
     * Clears the Mp3 Library
     */
    
    public void clearLibrary()
    {
    	this.mp3Library.clear();
    }
    
}

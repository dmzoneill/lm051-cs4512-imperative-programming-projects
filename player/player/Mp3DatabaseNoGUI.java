package player;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;



/**
 * @author David O Neill 0813001
 */

public class Mp3DatabaseNoGUI
{
	PrintStream out = System.out;
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	String spacer = new String("   ");
	private Mp3 musicDatabaseLibrary;
	

	public Mp3DatabaseNoGUI()
	{
		this.musicDatabaseLibrary = new Mp3();
		this.mainMenu();		
	}
	
	public Mp3DatabaseNoGUI(String[] args)
	{		
		this.musicDatabaseLibrary = new Mp3(args[0]);
		this.mainMenu();
	}
	
	
	/**
	 * Main menu for this consoel application
	 */
	
	public void mainMenu()
	{				
		this.out.println("");
		this.out.println(this.spacer + "Type \"Help\" at the prompt for options");
		this.out.println(this.spacer + "Type \"Quit\" to exit");
		this.out.println("");

		try 
		{
			this.out.print(this.spacer + "Database > ");
			String inputString = this.in.readLine();
			String test = inputString.toLowerCase();
			
			
			while(inputString.equalsIgnoreCase("quit")==false)
			{
				if(test.startsWith("help"))
					this.menuOptions();				
				
				else if(test.startsWith("showlist"))
					this.musicDatabaseLibrary.showList();
				
				else if(test.startsWith("showtrack"))
					this.showTrackMenu(inputString);
				
				else if(test.startsWith("showartist"))
					this.showArtistMenu(inputString);
				
				else if(test.startsWith("showgenre"))
					this.showGenreMenu(inputString);
				
				else if(test.startsWith("playall"))
					this.playAllMenu(inputString);
				
				else if(test.startsWith("playtrack"))
					this.playTrackMenu(inputString);
				
				else if(test.startsWith("playartist"))
					this.playArtistMenu(inputString);
				
				else if(test.startsWith("playgenre"))
					this.playGenreMenu(inputString);
				
				else if(test.startsWith("remove"))
					this.removeMenu(inputString);
				
				else if(test.startsWith("insert"))
					this.insertMenu(inputString);	
				
				else if(test.startsWith("dump"))
					this.dumpDatabaseMenu(inputString);	
				
				else if(test.startsWith("testbed"))
					this.testbed(inputString);
				
				else
					this.menuOptions(true);

				this.out.print(this.spacer + "Database > ");
				inputString = this.in.readLine();
				test = inputString.toLowerCase();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * Prints out the input options avaiable to the user
	 */

	public void menuOptions()
	{
		this.out.println(this.spacer + "   ");
		this.out.println(this.spacer + "   Playall");
		this.out.println(this.spacer + "   Playtrack ***");
		this.out.println(this.spacer + "   Playartist ***");
		this.out.println(this.spacer + "   Playgenre ***");
		this.out.println(this.spacer + "   ");
		this.out.println(this.spacer + "   Showlist");
		this.out.println(this.spacer + "   Showtrack [track] ***");
		this.out.println(this.spacer + "   Showartist [artist] ***");
		this.out.println(this.spacer + "   Showgenre [genre] ***");
		this.out.println(this.spacer + "   ");
		this.out.println(this.spacer + "   Insert");
		this.out.println(this.spacer + "   Remove");		
		this.out.println(this.spacer + "   ");
		this.out.println(this.spacer + "   Dump");
		this.out.println(this.spacer + "      -> Dumps the database into out.txt");
		this.out.println(this.spacer + "   ");
		this.out.println(this.spacer + "   *** Starts with");
		this.out.println(this.spacer + "   ");		
	}
	
	
	/**
	 * Prints out the input options avaiable to the user
	 */

	public void menuOptions(boolean error)
	{
		this.out.println("");
		this.menuOptions();
		this.out.println("");
		this.out.println(this.spacer + "Invalid command");
		this.out.println("");
	}
		
	
	public void insertMenu(String option)
	{
		try
		{
			this.out.println(spacer + "   ");
			String track,artist,genre,temp;
			this.out.print(this.spacer + this.spacer + "Track > ");
			track = this.in.readLine();
			this.out.print(this.spacer + this.spacer + "Artist > ");
			artist = this.in.readLine();
			this.out.print(this.spacer + this.spacer + "Genre > ");
			genre = this.in.readLine();
		
			temp = track + "[" + artist + "]" + genre;
			
			this.musicDatabaseLibrary.insert(temp);
			
			System.out.println("");
		}
		catch(Exception e)
		{
			this.out.println(this.spacer + this.spacer + "Unable to read input");
		}
	}
	
	
	public void dumpDatabaseMenu(String option)
	{
		this.musicDatabaseLibrary.dump();
		System.out.println("");
		System.out.println("Database dumped");
		System.out.println("");
	}
	
	
	public void removeMenu(String option)
	{		
		try
		{
			this.out.println(spacer + "   ");
			
			String temp, temp2;
			this.out.print(this.spacer + this.spacer + "Track name > ");
			temp = in.readLine();
			this.out.print(this.spacer + this.spacer + "Artist > ");
			temp2 = in.readLine();
		
			this.musicDatabaseLibrary.remove(temp,temp2);	
			
			this.out.println(spacer + "   ");
		}
		catch(Exception e)
		{
			this.out.println(this.spacer + this.spacer + "Unable to read input");
		}
	}
	
	
	public void playGenreMenu(String option)
	{		
		try
		{
			this.out.println(spacer + "   ");
			
			boolean random = false;			
			String temp,temp2;
			
			this.out.print(this.spacer + this.spacer + "Genre > ");
			temp = in.readLine();			
			this.out.print(this.spacer + this.spacer + "Random order [y/(n)] > ");
			temp2 = in.readLine();
			
			if(temp2.equalsIgnoreCase("y"))
				random = true;

			this.musicDatabaseLibrary.playGenre(temp, random);	
			
			this.out.println(spacer + "   ");	

		}
		catch(Exception e)
		{
			this.out.println(this.spacer + this.spacer + "Unable to read input");
		}		
	}
	
	
	public void playArtistMenu(String option)
	{		
		try
		{
			this.out.println(spacer + "   ");
			
			boolean random = false;			
			String temp,temp2;
			
			this.out.print(this.spacer + this.spacer + "Artist > ");
			temp = in.readLine();			
			this.out.print(this.spacer + this.spacer + "Random order [y/(n)] > ");
			temp2 = in.readLine();
			
			if(temp2.equalsIgnoreCase("y"))
				random = true;

			this.musicDatabaseLibrary.playArtist(temp, random);	
			
			this.out.println(spacer + "   ");				

		}
		catch(Exception e)
		{
			this.out.println(this.spacer + this.spacer + "Unable to read input");
		}
	}
	
	
	
	public void playTrackMenu(String option)
	{		
		try
		{
			this.out.println(spacer + "   ");
			
			boolean random = false;			
			String temp,temp2;
			
			this.out.print(this.spacer + this.spacer + "Track > ");
			temp = in.readLine();			
			this.out.print(this.spacer + this.spacer + "Random order [y/(n)] > ");
			temp2 = in.readLine();
			
			if(temp2.equalsIgnoreCase("y"))
				random = true;

			this.musicDatabaseLibrary.playTrack(temp, random);	
			
			this.out.println(spacer + "   ");	
			
		}
		catch(Exception e)
		{
			this.out.println(this.spacer + this.spacer + "Unable to read input");
		}
	}
	
	
	public void playAllMenu(String option)
	{		
		try
		{
			this.out.println(spacer + "   ");
			
			boolean random = false;			
			String temp2;
			
			this.out.print(this.spacer + this.spacer + "Random order [y/(n)] > ");
			temp2 = in.readLine();
			
			if(temp2.equalsIgnoreCase("y"))
				random = true;

			this.musicDatabaseLibrary.play(random);	
			
			this.out.println(spacer + "   ");	
		}
		catch(Exception e)
		{
			this.out.println(this.spacer + this.spacer + "Unable to read input");
		}
	}
	
	
	public void showGenreMenu(String option)
	{		
		try
		{
			this.out.println(spacer + "   ");
			
			String temp;
			
			this.out.print(this.spacer + this.spacer + "Genre > ");
			temp = in.readLine();			

			this.musicDatabaseLibrary.showGenre(temp);	
			
			this.out.println(spacer + "   ");	
		}
		catch(Exception e)
		{
			this.out.println(this.spacer + this.spacer + "Unable to read input");
		}
	}
	
	
	public void showArtistMenu(String option)
	{		
		try
		{
			this.out.println(spacer + "   ");
			
			String temp;
			
			this.out.print(this.spacer + this.spacer + "Artist > ");
			temp = in.readLine();			

			this.musicDatabaseLibrary.showArtist(temp);	
			
			this.out.println(spacer + "   ");	
		}
		catch(Exception e)
		{
			this.out.println(this.spacer + this.spacer + "Unable to read input");
		}
	}
	
	
	public void showTrackMenu(String option)
	{		
		try
		{
			this.out.println(spacer + "   ");
			
			String temp;
			
			this.out.print(this.spacer + this.spacer + "Track > ");
			temp = in.readLine();			

			this.musicDatabaseLibrary.showTrack(temp);	
			
			this.out.println(spacer + "   ");	
		}
		catch(Exception e)
		{
			this.out.println(this.spacer + this.spacer + "Unable to read input");
		}
	}	
	
	
	public void testbed(String input)
	{
		long start = System.currentTimeMillis();		
		out.println(Node.binarySearch("think twice[Groove Armada](35)"));
		long end = System.currentTimeMillis();		
		System.out.println("\n" + (end-start) + " ms");
		System.out.println("");
		
	   		
   		System.out.println("Linked list binary search");   		
		start = System.currentTimeMillis();	
		int pos = Node.linkedListBinarySearch("Superstylin[Groove Armada]Electronic");
		out.println(pos);
		out.println(Node.get(pos).getNodeValue());
		end = System.currentTimeMillis();		
		System.out.println("\n" + (end-start) + " ms");
		System.out.println("");
		
		
		System.out.println("");
		System.out.println("binary search linked list to array");  		
		start = System.currentTimeMillis();	
		pos = Node.binarySearch("Superstylin[Groove Armada]Electronic");
		out.println(pos);
		out.println(Node.get(pos).getNodeValue());		
		end = System.currentTimeMillis();		
		System.out.println("\n" + (end-start) + " ms");
		System.out.println("");

	}	
		
	
	/**
	 * lets go!
	 * @param args
	 */
	
	public static void main(String[] args)
	{
		if(args.length>0 && args[0].contains(".txt"))
			new Mp3DatabaseNoGUI(args);
		else
			new Mp3DatabaseNoGUI();		
	}
}

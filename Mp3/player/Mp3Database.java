package player;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import player.id3.Reader;
import player.id3.Tag;


public class Mp3Database extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private File musicDirectory;
	private File inputDirectory;
	private DirectoryReader musicDirectoryReader;
	private Library musicDatabaseLibrary;
	private int duplicates = 0;
	private String[] folder; 
	

	private JButton loadLibrary, addToLibrary, addFileToLibrary, exitButton, removeButton, play;
	private JTextField search, removeField;
	private JTextArea viewer;
	private JScrollPane scroll1;
	private JPanel panel1;
	private JLabel searchTitle, notify, removeTitle;
	private JRadioButton artistRadio, trackRadio, genreRadio, random, ordered;
	private Box sizeboxSearch, sizeboxBool;
	private ButtonGroup sizeGroupSearch, sizeGroupBool;
	private GridBagConstraints gc;
	
	private int playBytypeLenght = 0;
	private int playBytypePosition = 0;
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Constructor For Jframe etc
	 * @param directory the optional arguments folder to read
	 */
	
	public Mp3Database(String[] directory)
	{		
		this.folder = directory;
		this.setSize(500, 400);
		this.setTitle("Simple Mp3 Database");
		this.setVisible(true);
		this.setLocation(50,50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Color blue = new Color(0,0,255);
		Font font;
		
		
		// Container
		this.panel1 = new JPanel();
		this.panel1.setLayout(new GridBagLayout());
		
		
		
		
		// Option Group box (track artist genre)
		this.sizeboxSearch = Box.createHorizontalBox();
		this.artistRadio = new JRadioButton("Artist");
		this.artistRadio.addActionListener(this);
		this.trackRadio = new JRadioButton("Track");
		this.trackRadio.addActionListener(this);
		this.genreRadio = new JRadioButton("Genre");
		this.genreRadio.addActionListener(this);
		this.artistRadio.setSelected(true);		
		this.sizeboxSearch.add(this.artistRadio);
		this.sizeboxSearch.add(this.trackRadio);
		this.sizeboxSearch.add(this.genreRadio);		
		this.sizeGroupSearch = new ButtonGroup();
		this.sizeGroupSearch.add(this.artistRadio);
		this.sizeGroupSearch.add(this.trackRadio);
		this.sizeGroupSearch.add(this.genreRadio);		
		this.sizeboxSearch.setBorder(BorderFactory.createTitledBorder(" [ Play / Search ] By "));
		
		
		// Option group box ( random ordered)
		this.sizeboxBool = Box.createHorizontalBox();
		this.random = new JRadioButton("Random");
		this.random.addActionListener(this);
		this.ordered = new JRadioButton("Ordered");
		this.ordered.addActionListener(this);
		this.ordered.setSelected(true);		
		this.sizeboxBool.add(this.ordered);
		this.sizeboxBool.add(this.random);		
		this.sizeGroupBool = new ButtonGroup();
		this.sizeGroupBool.add(this.ordered);
		this.sizeGroupBool.add(this.random);		
		this.sizeboxBool.setBorder(BorderFactory.createTitledBorder(" [ Play / Order ] By "));
				
		// First Row		
		// col 0 
		this.addItemToPanel(this.panel1, this.sizeboxSearch , 0, 1, 1, 1, GridBagConstraints.WEST);	
		// col 1 (span 3)
		this.addItemToPanel(this.panel1, this.sizeboxBool , 1, 1, 3, 1, GridBagConstraints.EAST);
		
				
		// play Button
		this.play = new JButton();
		this.play.setText("Play All");		
		this.play.addActionListener(this);	
		
		// Second Row
		// col 0
		this.addItemToPanel(this.panel1, this.play, 0, 2, 1, 1, GridBagConstraints.WEST);
		
		// Search Label
		this.searchTitle = new JLabel("Search");		
		this.searchTitle.setForeground(blue);
		// col 1
		this.addItemToPanel(this.panel1, this.searchTitle , 1, 2, 1, 1, GridBagConstraints.EAST);	
		
		// Search Box
		this.search = new JTextField(20);
		this.search.setForeground(blue);
		font = this.search.getFont();
		this.search.setFont(font.deriveFont(font.getStyle() ^ Font.BOLD));
		this.search.addKeyListener(this);
		// col 2 (span 2)
		this.addItemToPanel(this.panel1, this.search, 2, 2, 2, 1, GridBagConstraints.EAST);	
		
		
		
		
		// text area
		this.viewer = new JTextArea(20,50);
		this.scroll1 = new JScrollPane(this.viewer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// Third Row
		// col 0 (span 4)
		this.addItemToPanel(this.panel1, this.scroll1, 0, 3, 4, 1, GridBagConstraints.WEST);
		
		
		
		// Notification Label
		this.notify = new JLabel("Notification Area");
		this.notify.setForeground(new Color(160,0,0));
		// Fourth Row
		// Col 0 span 4
		this.addItemToPanel(this.panel1, this.notify , 0, 4, 4, 1, GridBagConstraints.WEST);			
		
		
		// Remove Label
		this.removeTitle = new JLabel("Remove");		
		this.searchTitle.setForeground(blue);
		// Fifth Row
		// col 0 
		this.addItemToPanel(this.panel1, this.removeTitle , 0, 5, 1, 1, GridBagConstraints.WEST);
		
		// Remove input box
		this.removeField = new JTextField(20);
		this.removeField.setForeground(blue);
		font = this.removeField.getFont();
		this.removeField.setFont(font.deriveFont(font.getStyle() ^ Font.BOLD));
		// Col 1 span 2
		this.addItemToPanel(this.panel1, this.removeField, 1, 5, 2, 1, GridBagConstraints.EAST);
		
		// Remove button
		this.removeButton = new JButton();
		this.removeButton.setText("Remove");		
		this.removeButton.addActionListener(this);	
		// Col 3
		this.addItemToPanel(this.panel1, this.removeButton, 3, 5, 1, 1, GridBagConstraints.EAST);
		
		
		// Reload Default Library
		this.loadLibrary = new JButton();
		this.loadLibrary.setText("Reload Library");		
		this.loadLibrary.addActionListener(this);		
		// Sixth Row
		// Col 0
		this.addItemToPanel(this.panel1, this.loadLibrary, 0, 6, 1, 1, GridBagConstraints.WEST);
		
		// Add file button
		this.addFileToLibrary = new JButton();
		this.addFileToLibrary.setText("Add File");		
		this.addFileToLibrary.addActionListener(this);		
		// Col 1
		this.addItemToPanel(this.panel1, this.addFileToLibrary, 1, 6, 1, 1, GridBagConstraints.CENTER);
		
		// Add Folder button
		this.addToLibrary = new JButton();
		this.addToLibrary.setText("Add Folder");		
		this.addToLibrary.addActionListener(this);		
		// Col 2
		this.addItemToPanel(this.panel1, this.addToLibrary, 2, 6, 1, 1, GridBagConstraints.CENTER);
		
		// Exit button
		this.exitButton = new JButton();
		this.exitButton.setText("Exit");		
		this.exitButton.addActionListener(this);		
		// col 3
		this.addItemToPanel(this.panel1, this.exitButton, 3, 6, 1, 1, GridBagConstraints.EAST);
		
		this.add(panel1);
		this.pack();
		
		this.musicDatabaseLibrary = new Library();
		if(this.folder.length==0)
		{
			this.loadDefaultLibrary();
		}
		else 
		{
			String[] tempFolder = this.folder;
			this.loadMusicDirectory(tempFolder);
        	this.musicLibrarySetup(); 
        	this.musicDatabaseLibrary.sortLibrary();
        	this.viewerRefresh();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Adds components to the panel component
	 * @param p panael container
	 * @param c compoent to add
	 * @param x x position
	 * @param y y position
	 * @param width span columns
	 * @param height span rows
	 * @param align alignment
	 */
	
	private void addItemToPanel(JPanel p, JComponent c, int x, int y, int width, int height, int align)
	{
		this.gc = new GridBagConstraints();
		this.gc.gridx = x;
		this.gc.gridy = y;
		this.gc.gridwidth = width;
		this.gc.gridheight = height;
		this.gc.weightx = 100.0;
		this.gc.weighty = 100.0;
		this.gc.insets = new Insets(5,5,5,5);
		this.gc.anchor = align;
		this.gc.fill = GridBagConstraints.NONE;
		p.add(c,gc);
	}
	
	
	/**
	 * Updates the textarea withe current listing
	 *
	 */
	
	public void viewerRefresh()
	{		
		this.viewer.setText("");
		int sizeOfList = this.musicDatabaseLibrary.showList().size();
		
		for(int u=0;u<sizeOfList; u++)
		{
			this.viewer.append(this.musicDatabaseLibrary.showList().get(u).toString() + "\n");
		}	
	}		
	
	
	/**
	 * Updates the removeBox textbox with track or Artist string
	 *
	 */
	
	public void updateRemoveBox()
	{		
		if(this.artistRadio.isSelected())
    	{
    		this.removeField.setText("["+ this.search.getText() + "]");  
    	}
    	else if(this.trackRadio.isSelected())
    	{
    		this.removeField.setText(this.search.getText() +"[]"); 
    	}
	}
	
	
	/**
	 * Updates the text on the play button
	 */
	
	public void updatePlayButton()
	{				
    	String searchText = this.search.getText();
    	
    	String filter = "";
    	String extra = "";
    	
    	if(this.genreRadio.isSelected())
    		filter = "genre ";
    	
    	if(this.trackRadio.isSelected())
    		filter = "track ";
    	
    	if(this.artistRadio.isSelected())
    		filter = "artist ";
    	
    	if(this.random.isSelected())
    		extra = "randomly ";
    	
    	if(searchText.length()>0)
    		this.play.setText("Play " + extra +  "by " + filter);
    	else 
    		this.play.setText("Play all " + extra);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * addActionListener
	 * Implemented Event Handler Method from ActionEvent
	 * Invoked when an action occurs. 
	 * 
	 * actionPerformed
	 * @param e ActionEvent
	 */
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == loadLibrary)
		{
			this.loadDefaultLibrary();					

		}
		else if (e.getSource() == addToLibrary)
		{
			this.addFolder();
		}
		else if (e.getSource() == addFileToLibrary)
		{
			this.addFile();
		}
		else if (e.getSource() == removeButton)
		{
			this.removeFile();
		}
		else if (e.getSource() == play)
		{
			this.playFile();
		}
		else if (e.getSource() == exitButton)
		{
			System.exit(0);
		}
		else
		{
			this.updatePlayButton();
		}
	}	
	
	
	/**
	 * Implemented Event Handler Method from KeyListener
	 */
	
	public void keyPressed(KeyEvent keyEvent) 
	{
    }

	
	/**
	 * Implemented Event Handler Method from KeyListener
	 */
	
    public void keyReleased(KeyEvent keyEvent) 
    {
    	workWithKeyReleasedEvent(keyEvent);
    }

    
    /**
	 * Implemented Event Handler Method from KeyListener
	 */
    
    public void keyTyped(KeyEvent keyEvent) 
    {
    }

    
    /**
     * workWithKeyReleasedEvent
     * When keys are pressed in the search box, the search is  done and componets are updated
     * @param keyEvent
     */
    
    private void workWithKeyReleasedEvent(KeyEvent keyEvent) 
    {    		    	
    	JTextField textField = (JTextField) keyEvent.getSource();
    	String searchText = textField.getText();
    	
    	boolean random = this.isRandomSelected();
    	
    	this.updatePlayButton();
    	
    	this.setPlayByTypeLenght(0);
    	this.setPlayByTypePosition(0);
    	
    	if(this.isArtistSelected())
    	{
    		this.getSearchArtist(searchText,random);
    		this.updateRemoveBox();
    	}
    	else if(this.isTrackSelected())
    	{
    		this.getSearchTrack(searchText,random);
    		this.updateRemoveBox();
    	}
    	else 
    	{
    		this.getSearchGenre(searchText,random);
    	}	      	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Called from workWithKeyReleasedEvent
	 * getSearchArtist gets the search results from Artist search and displays them to the user
	 * @param match The artist name
	 * @param random Whether or not the results will be randomly ordered
	 */
	
	public void getSearchArtist(String match, boolean random)
	{
		this.viewer.setText("");
		
		ArrayList result = this.musicDatabaseLibrary.playArtist(match, random);
		
		int sizeOfList = result.size();

		for(int u=0;u<sizeOfList; u++)
		{
			this.viewer.append(result.get(u).toString() + "\n");
		}		
	}
	
	
	/**
	 * Called from workWithKeyReleasedEvent
	 * getSearchTrack gets the search results from Track search and displays them to the user
	 * @param match The track name
	 * @param random Whether or not the results will be randomly ordered
	 */
	
	public void getSearchTrack(String match, boolean random)
	{
		this.viewer.setText("");
		
		ArrayList result = this.musicDatabaseLibrary.playTrack(match, random);
		
		int sizeOfList = result.size();

		for(int u=0;u<sizeOfList; u++)
		{
			this.viewer.append(result.get(u).toString() + "\n");
		}	
	}
	
	
	/**
	 * Called from workWithKeyReleasedEvent
	 * getSearchGenre gets the search results from Genre search and displays them to the user
	 * @param match The genre type
	 * @param random Whether or not the results will be randomly ordered
	 */
	
	public void getSearchGenre(String match, boolean random)
	{
		this.viewer.setText("");
		
		ArrayList result = this.musicDatabaseLibrary.playGenre(match, random);
		
		int sizeOfList = result.size();

		for(int u=0;u<sizeOfList; u++)
		{
			this.viewer.append(result.get(u).toString() + "\n");
		}			
	}
	
	
	/**
	 * Called from actionPerformed
	 * PlayFile plays files in sequences or random if there is no search filter
	 * 
	 * If there is a search filter
	 * It gets the results for the filter search type, ie genre, track, artist
	 * It plays these files in order or randmly (if selected)
	 * It keeps track of the position in the playlist (filtered list)
	 * with a local variable "playBytypePosition"
	 * and also the size of the filtered list "playBytypeLenght"
	 *
	 */
	
	public void playFile()
	{
		Random random = new Random();
		boolean randomPlayOrder = this.isRandomSelected();
		
		if(this.search.getText().length()>0)
		{
			if(this.isArtistSelected())
			{
				ArrayList mp3s = this.musicDatabaseLibrary.playArtist(this.search.getText(),randomPlayOrder);
				this.playBytypeLenght = mp3s.size();
				if(this.playBytypeLenght == 0) return;
				
				// Randomly
				if(randomPlayOrder) this.playBytypePosition = random.nextInt(this.playBytypeLenght);
				this.notify.setText("Playing " + mp3s.get(this.playBytypePosition));
				
				// Sequencily
				this.playBytypePosition++;
				if(this.playBytypePosition==this.playBytypeLenght)
					this.playBytypePosition = 0;
			}
			else if(this.isTrackSelected())
			{
				ArrayList mp3s = this.musicDatabaseLibrary.playTrack(this.search.getText(),randomPlayOrder);
				this.playBytypeLenght = mp3s.size();
				if(this.playBytypeLenght == 0) return;
				
				// Randomly
				if(randomPlayOrder) this.playBytypePosition = random.nextInt(this.playBytypeLenght);
				this.notify.setText("Playing " + mp3s.get(this.playBytypePosition));
				
				// Sequencily
				this.playBytypePosition++;
				if(this.playBytypePosition==this.playBytypeLenght)
					this.playBytypePosition = 0;
			}
			else 
			{
				ArrayList mp3s = this.musicDatabaseLibrary.playGenre(this.search.getText(),randomPlayOrder);
				this.playBytypeLenght = mp3s.size();
				if(this.playBytypeLenght == 0) return;
				
				// Randomly
				if(randomPlayOrder) this.playBytypePosition = random.nextInt(this.playBytypeLenght);
				this.notify.setText("Playing " + mp3s.get(this.playBytypePosition));
				
				// Sequencily
				this.playBytypePosition++;
				if(this.playBytypePosition==this.playBytypeLenght)
					this.playBytypePosition = 0;
			}
			
		}
		else
		{
			// PLAY ALL (randomly or not)
			String playingFile = this.musicDatabaseLibrary.play(randomPlayOrder);
			this.notify.setText("Playing " + playingFile);
		}
	}
	
	
	/**
	 * Given a coorect input string trackName[Artist]
	 * This will call the remove item function
	 * And remove matching songs
	 */
	
	public void removeFile()
	{
		String input = this.removeField.getText().toString();
		String[] remove;
		String[] remove2;
		
		if(input.indexOf("]")>=0 && input.indexOf("[")>=0)
		{
    		remove = input.split("\\[");
    		remove2 = remove[1].split("]");
    		if(remove.length==0 || remove2.length==0)
    		{
    			this.notify.setText("To Remove use the format \"Trackname[Artist]\"");
    			return;
    		}

			if(this.musicDatabaseLibrary.remove(remove[0], remove2[0]))
			{
				this.notify.setText("Successfully removed \"" + remove[0] + "[" + remove[1] + "\"");
				this.viewerRefresh();
			}
			else
			{
				this.notify.setText("Unable to find \"" + remove[0] + "[" + remove[1] + "\"");
			}
			
		}
		else 
		{
			this.notify.setText("To Remove use the format \"Trackname[Artist]\"");
		}		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Loads the default library 
	 * Clears the old library
	 * Updates the viewer with the new list
	 */
	
	public void loadDefaultLibrary()
	{
		this.viewer.setText("");
		this.musicDatabaseLibrary.clearLibrary();
		this.loadMusicDirectory(this.folder);
		this.musicLibrarySetup();
		this.musicDatabaseLibrary.sortLibrary();
		
		int sizeOfList = this.musicDatabaseLibrary.showList().size();
		
		for(int u=0;u<sizeOfList; u++)
		{
			this.viewer.append(this.musicDatabaseLibrary.showList().get(u).toString() + "\n");
		}	
	}
	
	
	/**
	 * File chooser
	 * Adds a single mp3 to the current library
	 */
	
	public void addFile()
	{
		JFileChooser file = new JFileChooser();
		file.setDialogTitle("Choose a folder");
		this.getContentPane().add(file);
		file.setVisible(true);
		
		int returnValue = file.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        	File selectedFile = file.getSelectedFile();
        	String mp3 = getid3Tag(selectedFile);
        	this.musicDatabaseLibrary.insert(mp3);
        	this.musicDatabaseLibrary.sortLibrary();
        	this.viewerRefresh();
        }
		
	}

	
	/**
	 * Adds a folder and subfolders to the current library
	 */
	
	public void addFolder()
	{
		JFileChooser folder = new JFileChooser();
		folder.setDialogTitle("Choose a folder");
		folder.setCurrentDirectory(new java.io.File("."));
		folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.getContentPane().add(folder);
		folder.setVisible(true);
		
		int returnValue = folder.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) 
        {        	 
        	File selectedFile = folder.getSelectedFile();
        	String[] tempFolder = new String[1];
        	tempFolder[0] = selectedFile.toString();
        	this.loadMusicDirectory(tempFolder);
        	this.musicLibrarySetup(); 
        	this.musicDatabaseLibrary.sortLibrary();
        	this.viewerRefresh();
        }		
	}
	
	
	/**
	 * Gets the id3 tags for a mp3
	 * @param mp3 File
	 * @return String trackname[artist]genre
	 */
	
	public String getid3Tag(File mp3)
	{
		String mp3Details;
		try
		{
			Reader reader = new Reader();
			File songFile = mp3;
			FileInputStream in = new FileInputStream(songFile);
			Tag tag = reader.read(in, songFile.length());
			// specification layed out
			// title [Artist] Genre
			if(tag.getTitle().trim().length()<=0 || tag.getArtist().trim().length()<=0 || tag.getGenre().trim().length()<=0)
			{
				mp3Details = "error";
			}
			else
			{
				mp3Details = tag.getTitle().trim() + "[" + tag.getArtist().trim() + "]" + tag.getGenre().trim();
			}
			in.close();
		}
		catch (Exception e)
		{
			mp3Details = "error";
		}
	    
		return mp3Details;
	}
	
	
	/**
	 * Loads a folder a user defined directory
	 * @param directory
	 */
	
	public void loadMusicDirectory(String[] directory)
	{		
		if(directory.length>0)
		{
			this.inputDirectory = new File(directory[0]);
			
			if(this.inputDirectory.isDirectory()==true)
			{			
				this.musicDirectory = new File(this.inputDirectory.toString());
			}
			else 
			{				
				this.error("Please specify a valid directoy path .ie \n\"C:\\users\\music\"	\n\"/home/user/music\"");
				System.exit(0);
			}
		}
		else
		{
			this.musicDirectory = new File(System.getProperty("user.dir") + "/player/music");
		}	
		
		this.musicDirectoryReader = new DirectoryReader(this.musicDirectory);
	}
	
	
	/**
	 * Reads the result of the recurisve directory search
	 * and puts the files into the library
	 */
	
	public void musicLibrarySetup()
	{		
		String title;
		
		this.duplicates = 0;
		
		ArrayList files = this.musicDirectoryReader.getList();
		
		for(int j = 0; j < files.size(); j++)
		{
			title = this.getid3Tag(new File(files.get(j).toString()));
			if(title.compareTo("error")==0)
			{
				this.error("Unable to load file : " + files.get(j).toString());
			}
			else 
			{
				if(this.musicDatabaseLibrary.insert(title)==false)
				{
					this.error("Ignoring duplicate file : " + files.get(j).toString());
					this.duplicates++;
				}
			}
		}
		if(this.duplicates>0)
		{
			this.notify.setText(this.duplicates + " duplicates ignored");
		}
	}	
	
	
	/**
	 * Prints messages to the terminal
	 * @param message
	 */
		
	public void error(String message)
	{
		System.out.println(message);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Is the random box selected
	 * @return boolean
	 */
	
	public boolean isRandomSelected()
	{
		return this.random.isSelected();
	}
	
	
	/**
	 * Is the artist box selected
	 * @return boolean
	 */
	
	public boolean isArtistSelected()
	{
		return this.artistRadio.isSelected();
	}
	
	
	/**
	 * Is the track box selected
	 * @return boolean
	 */
	
	public boolean isTrackSelected()
	{
		return this.trackRadio.isSelected();
	}
	
	
	/**
	 * Is the genre box selected
	 * @return boolean
	 */
	
	public boolean isGenreSelected()
	{
		return this.genreRadio.isSelected();
	}
	
	
	/**
	 * Sets the position in the current playlist
	 */
	
	public void setPlayByTypePosition(int current)
	{
		this.playBytypePosition = current;
	}
	
	
	/**
	 * Sets the size of the current playlist
	 */
	
	public void setPlayByTypeLenght(int size)
	{
		this.playBytypeLenght = size;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Lets Go
	 * @param args
	 */
	
	public static void main(String[] args) 
	{		
		new Mp3Database(args);		
		
	}
}

package delete;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;


public class DirectoryReader
{	
	private File[] library;
	private FilenameFilter filter;
	
	public DirectoryReader(File directory)
	{
		this.filter = new FilenameFilter() 
		{ 
			public boolean accept(File dir, String name) 
			{ 
				return true; 
			} 
		}; 

		this.library = DirectoryReader.listFilesAsArray(directory, filter, true);
	}
	
	public static File[] listFilesAsArray(File directory, FilenameFilter filter, boolean recurse)
	{
		Collection files = listFiles(directory, filter, recurse);		    	
		File[] arr = new File[files.size()];
		
		return (File[]) files.toArray(arr);
	}
		   
	public static Collection listFiles(File directory, FilenameFilter filter, boolean recurse)
	{
		Vector files = new Vector();
	   	File[] entries = directory.listFiles();
		
	   	for (int f = 0; f < entries.length; f++) 
	   	{
	   		File entry = (File) entries[f];

	   		if (filter == null || filter.accept(directory, entry.getName()))
		  	{
			  	files.add(entry);
		  	}
		  
		  	if (recurse && entry.isDirectory())
		  	{
			  	files.addAll(listFiles(entry, filter, recurse));
		  	}
	   	}
	
	   	return files;		
	}
	
	
	public ArrayList getList()
	{
		ArrayList thelist = new ArrayList();
		
		for(int t=0;t<this.library.length; t++)
		{
			thelist.add(this.library[t].toString());
		}
		
		return thelist;
	}
}




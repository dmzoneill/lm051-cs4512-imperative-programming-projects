package player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BinarySearch 
{
	int stringCount;
	String[] list;

	public BinarySearch(String[] args)
	{
		 initialiseFromFile(args[0]) ;
		 if(list != null) 		 {
			 stringCount = list.length ;
		 } else 
		 {
			 list = new String[50] ;
			 stringCount = 0  ;
		 }
		 
		 int position = this.binarySearch(list, args[1]);
		 System.out.println(position);
	
	}
	
	public int binarySearch(String[] list, String str)
	{  
		int Left =0;
		int Right = list.length;
		int Mid = (Left + Right) /2 ;
 
		while(Left <= Right && str.compareToIgnoreCase(list[Mid]) != 0) 
		{
			System.out.println("left " + Left);
			System.out.println("Right " + Right);
			System.out.println("Mid " + Mid);
			System.out.println("");
			
			if(str.compareToIgnoreCase(list[Mid]) < 0) 
			{
				Right = Mid - 1 ;
			} 
			else 
			{
				Left = Mid + 1 ;
			}
			Mid = (Left + Right) / 2 ;
		}
		
		if(Left <= Right) 
			return Mid ;
		else 
			return -1 ;
	}
	
	
	private void initialiseFromFile(String filename)
    {
        try 
        {
            FileReader aFileReader = new FileReader(filename);
            BufferedReader aBufferReader = new BufferedReader(aFileReader);
            String lineFromFile;
            ArrayList listFromFile = new ArrayList();
            while ((lineFromFile = aBufferReader.readLine()) != null)
            {  
                listFromFile.add(lineFromFile);
            }
            aBufferReader.close();
            aFileReader.close();
            list = (String[]) listFromFile.toArray(new String[listFromFile.size()]) ;
        }
        catch(IOException x)
        {
            list =  null ;
        }
    } 
	
	public static void main(String[] args)
	{
		new BinarySearch(args);
	}
}

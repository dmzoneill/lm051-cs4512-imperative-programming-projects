package OurMethods;

public class OurMethods 
{
	
	public OurMethods()
	{
		
		
	}
	
	
	
	
	
	
	/**
	 * breakUpStringUsingSplit
	 * @param theString         -- the string to split up
	 * @param breakUsingChar    -- what to use the split up the string
	 * @return void             -- don't return anything
	 */
	
	public void break_Up_String_Using_Split(String theString, String breakUsingChar)
	{
		// theString = "hello john how are you";
		
		String temp[] = theString.split(breakUsingChar);	
		
		// temp[0] = "hello";
		// temp[1] = "john";
		// temp[2] = "how";
		// temp[3] = "are";
		// temp[4] = "you";		
		
		for(int p = 0; p < temp.length; p++)
		{
			System.out.println(temp[p]);			
		}		
	}
	
	
	
	
	
	
	
	
	/**
	 * break_Up_String_Using_Substring
	 * @param theString         -- the string to split up
	 * @param breakUsingChar    -- what to use the split up the string
	 * @return void             -- don't return anything
	 */
	
	public void break_Up_String_Using_Substring(String theString, String breakUsingChar)
	{
		int posOfChar = theString.indexOf(breakUsingChar,0); // look for character starting from position 0
		int startPos = 0; // start here
		
		while(posOfChar > -1) // while indexOf did not return -1 (not found)
		{
			String temp = theString.substring(startPos, posOfChar); // read from 0 to were we found a match
			System.out.println(temp); // print out the match
			
			startPos = posOfChar + 1; // start from here next time (*note +1)
			posOfChar = theString.indexOf(breakUsingChar,startPos); // look for character starting from position startPos			
		}
		
		// finally print the last one
		// use startpos and theString.length
		String temp = theString.substring(startPos, theString.length());
		System.out.println(temp);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * checks to see if the list is ascending
	 * descending
	 * otherwise its undulating
	 * @param theList   -- an array of ints
	 * @return void     -- return nothing
	 */
	
	public int check_Number_Sequence(int[] theList)
	{
		// keep it simple
		// we could probably do this all in one go		
		
		// check ascending first		
		int y = 0;
		boolean ascending = true; // assumption		
			
		while(y < theList.length -1 && ascending==true )
		{
			if(theList[y + 1] < theList[y]) // if the next one is smaller, then its not ascending
			{
				ascending = false; 
			}		
			y++;
		}
		
		if(ascending==true)
		{
			return 1; // its ascending		
			// note returning here will make the function stop here
		}
		
				
		// check descending 		
		int x = 0;
		boolean descending = true; // assumption
		
		while(x < theList.length -1 && descending==true )
		{
			if(theList[x + 1] > theList[x]) // if the next one is bigger then its not descending
			{
				descending = false; 
			}		
			x++;
		}
		
		if(descending==true)
		{
			return 2; // its descending		
			// note returning here will make the function stop here
		}
				
		
		// if we get here (function does not return)		
		return 3; // it must be undulating				
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Reverse String
	 * @param theString -- the sting to reverse
	 * @return void -- return nothing
	 */
	
	public void reverse_String(String theString)
	{
		String reverse = "";
		int p = theString.length() -1;
		
		// for exmaple
		// "dave"
	    // p = 3
		// loop until p = 0
				
		while(p > -1)		
		{
			char temp = theString.charAt(p);
			reverse = reverse + temp;
			p--;						
		}
		
		System.out.println(reverse);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Converts a text message into its alternative phone keys
	 * lower case only
	 * try make this handle both
	 * @param theString -- the string to convert
	 * @return void -- return nothing
	 */	
	
	public void string_To_Keypad_Numbers(String theString)
	{
		String keyPad = "02233334445556666777888999";
		String letters = " abcdefghijklmnopqrstuvwxyz"; // notice we also handle space
		
		for(int y =0; y < theString.length(); y++)		
		{
			char letterAtIndex = theString.charAt(y); // get the character in the string
			int alphabetPos = letters.indexOf(letterAtIndex); // check the alphabet for this character (get index)
			
			char keypadConvert = keyPad.charAt(alphabetPos);	 // get the corresponding number given the index	
			
			System.out.print(keypadConvert);			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Entry point for the program
	 * All programs have main 
	 * 
	 */
	public static void main(String[] args) 
	{
		OurMethods something = new OurMethods();
		
		something.break_Up_String_Using_Split("this is some string and we will split it up using space", " ");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		something.break_Up_String_Using_Substring("this is some string and we will split it up using space", " ");
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		int[] someNumberList1 = {3,5,21,4,6,3};	 // undulating	
		int und = something.check_Number_Sequence(someNumberList1);
		System.out.println(und);
		
		int[] someNumberList2 = {1,1,2,3,4,5,6};	 // ascending	
		int asc = something.check_Number_Sequence(someNumberList2);
		System.out.println(asc);
		
		int[] someNumberList3 = {32,31,4,3,2,1}; // descending
		int des = something.check_Number_Sequence(someNumberList3);
		System.out.println(des);
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		something.reverse_String("this is some string that we will reverse");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		something.string_To_Keypad_Numbers("this is some string that we will convert to phone keypad numbers");
		
		
		
	}

}

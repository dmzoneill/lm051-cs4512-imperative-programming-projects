package OurMethods;

public class PSLG 
{	
	
	/*
	 * 
	 * Write a method to take an array and a integer
	 * the method checks to see if the integer is in the array
	 * return true if it is, false if its not
	 * 
	 */	
	
	public static boolean contains(int[] set, int val)
	{
		boolean hasValue = false;
		int h = 0;
		
		while(h < set.length && !hasValue)
		{
			if(set[h] == val)
			{
				hasValue = true;
			}
			h++;
		}
		
		return hasValue;		
	}
		
	
	
	/*
	 * 
	 * Write a method to take two arrays
	 * find the union of the 2 sets (no duplicates!!!!)
	 * hopefully we've already written contains!
	 * return the union (array of integers)
	 * 
	 */
	
	public static int[] unionOfSets(int[] setA, int setB[])
	{
		int[] temp = new int[setA.length + setB.length];
		int[] union;
		int items = 0;
		
		for(int y = 0 ; y < setA.length; y++)
		{
			if(!contains(temp, setA[y]))
			{
				temp[items] = setA[y];
				items++;
			}
		}
		
		for(int y = 0 ; y < setB.length; y++)
		{
			if(!contains(temp, setB[y]))
			{
				temp[items] = setB[y];
				items++;
			}
		}
		
		union = new int[items];
		for(int u = 0; u < items; u++)
		{
			union[u] = temp[u];
		}
		
		return union;
	}
	
	
	
	/*
	 * 
	 * Write a method to take two arrays
	 * find the intersection of the 2 sets
	 * hopefully we've already written contains!
	 * return the union (array of integers)
	 * 
	 */
	
	public static int[] intersectionOfSets(int[] setA, int setB[])
	{
		int[] temp = new int[setA.length + setB.length];
		int[] intersection;
		int items = 0;
		
		for(int y = 0 ; y < setA.length; y++)
		{
			if(contains(setB, setA[y]))
			{
				temp[items] = setA[y];
				items++;
			}
		}
				
		
		intersection = new int[items];
		for(int u = 0; u < items; u++)
		{
			intersection[u] = temp[u];
		}
		
		return intersection;
	}
	
	
	
	/* 
	 * 
	 * I HATE MATHS
	 * In mathematics, the Fibonacci numbers are the numbers in the following sequence:
	 * 0	1	1	2
	 * The first 2 numbers are 0 1
	 * the sum of these is 1, which is the third number
	 * the 4th is (2nd) 1 + (3rd)1 = 2
	 * the 5th is (3nd) 1 + (4th)2 = 3
	 * 
	 * what's the 73rd? 
	 * 
	 * long 64 bits
	 * int 32 bits
	 * 
	 */
	
	public static long fibonacci( int valueAt )
	{
		long[] f = new long[ valueAt + 1 ];
        f[0] = 0;
        f[1] = 1;
        int x = 2; 
        
        while( x < f.length && x < valueAt )
        {
        	f[ x ] = f[ x - 2 ] + f[ x - 1 ];
        	x++;
        }
        
        return f[ x - 1 ];	
	}
	
	
	
	/*
	 * 
	 * Write a method that takes an array of numbers
	 * once of the numbers in the array will be repeated
	 * if you can't find it return -1, otherwise return the number repeated
	 * 
	 */
	
	public static int moreThanOne(int[] arr)
	{
		boolean found = false;
		int theOne = 0;
		int outer = 0;
		int inner = 0;
		
		while( outer < arr.length && !found )
		{
				inner = outer + 1;
				while( inner < arr.length && !found )
				{
					if( arr[outer] == arr[inner])
					{
						theOne = arr[outer];
						found = true;
					}
					inner++;
				}	
				outer++;				
		}
		
		if( !found )
		{
			return -1;
		}
		else
		{
			return theOne;		
		}
	}		
	
	
	public static void main(String[] args)
	{
		// union
		int[] ty = {6,7,3,4,2,1,9};
		int[] tg = {6,2,3,7,3,4,5,2,1,9};
		int[] result = unionOfSets(ty,tg);
		
		// intersection
		int[] result2 = intersectionOfSets(ty,tg);
				
		// fibionacci
		System.out.println(fibonacci(91));
		
		// more than one
		int[] arr = {1,2,3,4,5,6,7,5,8,9};
		System.out.println(moreThanOne(arr));
	}
}

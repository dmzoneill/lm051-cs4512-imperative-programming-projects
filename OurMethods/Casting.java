package OurMethods;

public class Casting 
{

	public Casting()
	{
		
		// TO string !!!!!!! SPECIAL !!!!!!!		
		int x = 5;		
		String temp1 = String.valueOf(x);
		
		// char to string
		char c = 'c';
		String temp5 = String.valueOf(c);
		
		// float to string
		float q = 3.0f;
		String temp3 = String.valueOf(q);
		
		// long to string
		long d = 232323213213L;
		String temp4 = String.valueOf(d);
		
		// char array to string
		char[] a = new char[5]; 
		a[0] = 'h';
		a[1] = 'e';
		a[2] = 'l';
		a[3] = 'l';
		a[4] = '0';		
		
		String array_to_string = new String(a);
			
		
		
		
		// general casting of numbers			
		
		
		// DOUBLE		
		double p = 5.6;			
		
		// double to int
		int p1 = (int) p; // see what happens to the number when you cast it!!!!!	
		
		// double to long
		long p2 = (long) p; // see what happens to the number when you cast it!!!!!
		
		// double to byte
		byte p3 = (byte) p; // see what happens to the number when you cast it!!!!!
		
		// double to float
		float p4 = (float) p;	// see what happens to the number when you cast it!!!!!
		

		
		
		
		
		// to INT FROM STRING SPECIAL
		String bump = "5";
		int newBump = Integer.parseInt(bump) + 1; 
		
		double dd = 5.6;				
		// double to int
		int b1 = (int) dd; // see what happens to the number when you cast it!!!!!
		// or  (float) or (int) or (double) or (byte) .....		
		
		
	}
	
	public static void main(String args[])
	{
		new Casting();
	}
	
}

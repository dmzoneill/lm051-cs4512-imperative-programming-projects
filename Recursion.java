
public class Recursion 
{
	
	public Recursion()	{}
	
	
	
	public int sumOfNumbersTo(int n)
	{
		if(n==0)
			return 0;
		else
			return n + sumOfNumbersTo(n-1);
	}
	
	
	
	public int sixTimes(int n)
	{
		if(n==1)
			return 1;
		else
			return 6 * sixTimes(n-1);
	}
	
	
	
	
	public int mutiply(int x, int y)
	{
		if(y==0)
			return 0;
		else
			return x + mutiply(x, y -1);		
	}
	
	
	public int div4(int p)
	{
		if(p<4)
			return 0;
		else if(p % 4 == 0)
			return p;
		else
			return div4(p -1);
	}
	
	
	
	
	public int HighestXDivByY(int x, int y)
	{
		if(y>x)
			return 0;
	    else if(x % y == 0)
			return x;
		else
			return HighestXDivByY(x-1, y);
	}
	
	
	
	
	public static void main(String args[])
	{
		Recursion r = new Recursion();
		
		
		// sum 1 to 6
		int x =  r.sumOfNumbersTo(6);		
		System.out.println(x);		
		
		// 6 * n
		int p =  r.sixTimes(3);		
		System.out.println(p);	
		
		// multiply x by y
		int g =  r.mutiply(3,6);		
		System.out.println(g);
		
		// highest number divisible by 4 in p
		int h =  r.div4(47);		
		System.out.println(h);
		
		// highest number in X divisible to Y
		int q =  r.HighestXDivByY(4,2);		
		System.out.println(q);
		
		String name = "john";
		
		if(name == "john")
		{
			System.out.println("yes");
		}
		
		
		
	}

}

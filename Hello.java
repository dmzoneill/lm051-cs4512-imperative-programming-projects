
class Hello {

	public static int x = 0;
	Hello2 dick;

	public Hello()
	{
		System.out.println("Constructor " + x);
	}
	
	public Hello(String message)
	{
		dick = new Hello2();
		System.out.println("Constructor " + message);
	}

	public static void main(String args[])
	{
		
		String input = "";
		if(args.length > 0)
			input = args[0] + " ";

		System.out.println("main " + input + x);
		x++;
		Hello fed1 = new Hello();
		x++;
		fed1.printX(x);
		x++;
		Hello fed2 = new Hello("with string " + x);
		x++;		
		fed2.printX(x);
		
	}

	public void printX(int x)
	{
		System.out.println(x);
	}
}

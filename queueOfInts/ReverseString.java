import javax.swing.JOptionPane ;

public class ReverseString 
{
	
	private char[] charStack;
	private int head, tail;
	
	public ReverseString()
	{
		charStack = new char[10];
		head = tail = 0;
	}
	
	public ReverseString(int lenght)
	{
		charStack = new char[lenght];
		head = tail = 0;
	}
	
	public void Join(char val)
	{	
		charStack[tail] = val ;
		tail = (tail + 1) % charStack.length;
	}

	public char Leave()
	{	
		char Removed ;
		Removed = charStack[head];
		head = (head + 1) % charStack.length;
		return Removed ;
	}
	
	public static void main(String[] args)
	{
		String word = JOptionPane.showInputDialog(null,"Input String : ");
		char[] letters = word.toCharArray();
		
		ReverseString stack = new ReverseString(letters.length);
		int o = 0;
			
		while(o < letters.length)
		{
			stack.Join(letters[o]);
			o++;
		}
		
		o = 0;
		while(o < letters.length)
		{
			char fail = stack.Leave();
			//System.out.println(fail);
			o++;
		}
	}
}

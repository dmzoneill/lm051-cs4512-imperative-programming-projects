package queueOfInts;

import javax.swing.JOptionPane ;
public class QueueOfInts01
{
	public static void main(String[] args)
	{	
		String Operation ;
		QueueOfInts Q = new QueueOfInts(3) ;
		int x, newval ;
		Operation = JOptionPane.showInputDialog(null,"Operation? ") ;
		
		
		while(Operation.compareToIgnoreCase("stop") != 0) 
		{
			if(Operation.compareToIgnoreCase("join") == 0) 
			{
				if(!Q.isfull()) 
				{
					newval = Integer.parseInt(JOptionPane.showInputDialog(null,"Join Value : ")) ;
					Q.Join(newval) ;
				} 
				else 
				{
					System.out.println("Queue is full") ;
				}
			} 
			else if(Operation.compareToIgnoreCase("leave") == 0) 
			{
				if(!Q.isempty()) 
				{
					x = Q.Leave() ;
					System.out.println(x) ;
				} 
				else 
				{
					System.out.println("Queue is empty") ;
				}
			} 
			else if(Operation.compareToIgnoreCase("display") == 0) 
			{
				if(!Q.isempty()) 
				{
					Q.display() ;
				} 
				else 
				{
					System.out.println("Queue is empty") ;
				}
			}
			else if(Operation.compareToIgnoreCase("extend") == 0) 
			{
				Q.EXTEND();
			}
			Operation = JOptionPane.showInputDialog(null,"Operation? ") ;
		}		
		if(!Q.isempty())
		{
			Q.display() ;
		}
		System.exit(0) ;
		
	}
}


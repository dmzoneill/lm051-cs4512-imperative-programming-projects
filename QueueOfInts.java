public class QueueOfInts
{	private int[] Queue ;
	private int Head, Tail ;
	
	public QueueOfInts()
	{	Queue = new int[10] ;
		Head = Tail = 0 ;
	}
	
	public QueueOfInts(int capacity)
	{	Queue = new int[capacity] ;
		Head = Tail = 0 ;
	}
	
	public boolean isempty()
	{	return Head == Tail ;
	}

	public boolean isfull()
	{
		if ((Tail + 1) % Queue.length == Head) {
    		return true;
  		} else {
    		return false;
		}
	}	
	
	public void Join(int val)
	{	Queue[Tail] = val ;
		Tail = (Tail + 1) % Queue.length;
	}

	public int Leave()
	{	int Removed ;
		Removed = Queue[Head];
		Head = (Head + 1) % Queue.length;
		return Removed ;
	}
	
	public int PEEK()
	{	return Queue[Head] ; 
	}
}	
package player;


/**
 * @author David O Neill 0813001
 * @version 0.11
 */

public class Node 
{
	private static Node headNode = null;
	private static Node tailNode = null;
	private static int nodeListLenght = 0;
	
	private String nodeValue = null;
	private Node nextNode = null;
	private Node prevNode = null;


	public Node()
	{
		
	}	
	

	/**
	 * Constructor for a new node
	 * @param nodeValue String
	 */
	
	public Node(String nodeValue)
	{
		this.nodeValue = nodeValue;			
	}
	
	
	/**
	 * Returns the head node
	 * @return Node
	 */

	public static Node getHeadNode()
	{
		return Node.headNode;
	}
	
	
	/**
	 * Returns the tail node
	 * @return Node
	 */
	
	public static Node getTailNode()
	{
		return Node.tailNode;
	}
	
	
	/**
	 * Gets the value stored in this node
	 * @return String
	 */
	
	public String getNodeValue()
	{
		return this.nodeValue;		
	}
	
	
	/**
	 * Sets the node that this node points to
	 * @param nextNode
	 */
	
	public void setNextNode(Node nextNode)
	{		
		this.nextNode = nextNode;
	}	
	
	
	/**
	 * Gets the next node that this node points to
	 * @return node
	 */
	
	public Node getNextNode()
	{		
		return this.nextNode;
	}	
	
	
	/**
	 * Sets the node that this node points to
	 * @param nextNode
	 */
	
	public void setPrevNode(Node prevNode)
	{		
		this.prevNode = prevNode;
	}	
	
	
	/**
	 * Gets the next node that this node points to
	 * @return node
	 */
	
	public Node getPrevNode()
	{		
		return this.prevNode;
	}		
	
	
	/**
	 * Creates a new instance of itself
	 * Correctly places itself in the linkedlist
	 * Does not accept duplicates
	 * @param newNodeValue Value of the Node 
	 */
	
	public static boolean insert(String newNodeValue)
	{
		Node newNode = new Node(newNodeValue);
		Node nextNode = null;
		
		boolean duplicate = false;
		
		if(Node.headNode==null)
		{
			newNode = new Node(newNodeValue);
			Node.headNode = newNode;
			Node.tailNode = newNode;
			Node.nodeListLenght++;
			return duplicate;
		}
		
		// ignore duplicates
		if(Node.linkedListBinarySearch(newNodeValue)>-1)
		{
			return true;
		}
		
		
		Node currentNode = Node.headNode;
		boolean insertion = false;
		
		while(insertion==false)
		{			
			nextNode = currentNode.getNextNode();
						
			if(newNodeValue.compareToIgnoreCase(Node.headNode.getNodeValue())<0)
			{
				newNode.setNextNode(currentNode);
				currentNode.setPrevNode(newNode);
				Node.headNode = newNode;
				if(nextNode==null)
				{
					Node.tailNode = currentNode;
				}
				insertion = true;
				Node.nodeListLenght++;
			}
			
			else if(nextNode==null)
			{
				currentNode.setNextNode(newNode);
				newNode.setPrevNode(currentNode);
				Node.tailNode = newNode;
				insertion = true;
				Node.nodeListLenght++;
			}
			
			else 
			{
				if(newNodeValue.compareToIgnoreCase(currentNode.getNodeValue())>0 && newNodeValue.compareToIgnoreCase(nextNode.getNodeValue())<0)
				{						
					newNode.setNextNode(nextNode);
					currentNode.setNextNode(newNode);
					newNode.setPrevNode(currentNode);
					nextNode.setPrevNode(newNode);
					insertion = true;
					Node.nodeListLenght++;
				}
			}
			
			currentNode = nextNode;			
		}	
		return duplicate;
	}
	
	/**
	 * Removes a node
	 * links the node before the index node to the index after the index node
	 * @param index
	 */
	
	public static void remove(Node node)
	{
     	Node nextNode = node.getNextNode();
		Node prevNode = node.getPrevNode();
		if(prevNode==null)
		{
			Node.headNode = nextNode;			
			if(nextNode!=null)
				nextNode.setPrevNode(null);				
		}
		else
		{
			prevNode.setNextNode(nextNode);
		}
		
		if(nextNode==null)
		{
			Node.tailNode = prevNode;			
			if(prevNode!=null)
				prevNode.setNextNode(null);				
		}
		else
		{
			nextNode.setPrevNode(prevNode);
		}		
		node = null;
		Node.nodeListLenght--;
	}
	
		
	/**
	 * Gets a node in the linklist at an index point
	 * Decides to start from the top or bottom depending on the index requested
	 * 
	 * @param index
	 * @return
	 */
	
	public static Node get(int index)
	{
		Node node = null;
		int middle = Node.size() / 2;			
		
		if(index>Node.size()-1)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			Node iter;
			int x;
			
			if(index>middle)
			{
				x = Node.size();	
				iter = Node.getTailNode();
				
				while(iter != null)
				{
					if(x==index)
					{
						node = iter;
						iter = null;
					}
					else
					{
						x--;
						iter = iter.getPrevNode();
					}
				}
				
			}
			else
			{
				x = 0;	
				iter = Node.getHeadNode();
				
				while(iter != null)
				{
					if(x==index)
					{
						node = iter;
						iter = null;
					}
					else
					{
						x++;
						iter = iter.getNextNode();
					}
				}
				
			}		
			
			return node;
		}
	}
		
	
	/**
	 * Splits up the nodes value into 3 separate parts to we can work with it easier
	 * @param node
	 * @return String[]
	 */
	
	public static String[] splitNodeValue(Node node)
	{
		String[] details = new String[3];
		String[] temp, temp2;
		temp = node.getNodeValue().split("\\[");
		details[0] = temp[0];
		temp2 = temp[1].split("]");
		details[1] = temp2[0];
		details[2] = temp2[1];
		return details;
	}
	
	
	/**
	 * Returns the linked List as an array
	 * @return Node[]
	 */
	
	public static Node[] toArray()
	{
		Node[] nodes = new Node[Node.size()];		
		Node iter = Node.headNode;
		int x =0;
		
		while(iter != null)
		{
			nodes[x] = iter;
			x++;
			iter = iter.getNextNode();
		}		

		return nodes;		
	}	
		
	
	/**
	 * Returns the linked List as an array where the option = track, artist or genre
	 * @param int track = 0, artist =1, genre = 2
	 * @param String what your looking for
	 * @return Node[]
	 */
	
	public static Node[] toArray(int option, String str)
	{
		Node iter = Node.headNode;
		int y =0;
		
		while(iter != null)
		{
			if(Node.splitNodeValue(iter)[option].toLowerCase().startsWith(str.toLowerCase()))
			{
				y++;
			}
			iter = iter.getNextNode();
		}
		
		
		Node[] nodes = new Node[y];		
		iter = Node.headNode;
		int x =0;
		
		while(iter != null)
		{
			if(Node.splitNodeValue(iter)[option].toLowerCase().startsWith(str.toLowerCase()))
			{
				nodes[x] = iter;
				x++;
			}			
			iter = iter.getNextNode();
		}
		
		return nodes;		
	}	
	
	
	/**
	 * Takes an array of node pointers and randomzies it
	 * @param nodes
	 * @return Node[]
	 */

    public static Node[] randomizeNodeArray(Node[] nodes)
    {    	
    	Node tempNode;
		for (int i=0; i<nodes.length; i++) 
		{
		    int randomPosition = (int) (Math.random() * nodes.length) ;
		    tempNode = nodes[i];
		    nodes[i] = nodes[randomPosition];
		    nodes[randomPosition] = tempNode;
		}    	
    	return nodes;
    }    
    
	
	/**
	 * Currenly puts all the nodes into a Node array and searches them
	 * @param str 
	 * @return int 
	 */
	
	public static int binarySearch(String str)
	{		
		Node[] nodes = Node.toArray();
		
		if(nodes.length<3)
			return -1;
		
		int Left =0;
		int Right = nodes.length -1;
		int Mid = (Left + Right) /2 ;
 
		while(Left <= Right && str.compareToIgnoreCase(nodes[Mid].getNodeValue()) != 0) 
		{
			if(str.compareToIgnoreCase(nodes[Mid].getNodeValue()) < 0) 
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
	
	
	/**
	 * Currenly puts all the nodes into a Node array and searches them
	 * 
	 * @param str startsWith
	 * @return int 
	 */
	
	public static int linkedListBinarySearch(String str)
	{				

		int Left = 0;
		int Right = Node.nodeListLenght -1;
		int Mid = (Left + Right) / 2 ;		
		
		Node midNode = Node.getHeadNode();
		
		if(Node.nodeListLenght<3)
		{
			Node iter = Node.getHeadNode();
			int y = -1;
			while(iter!=null)
			{
				if(iter.getNodeValue().compareToIgnoreCase(str)==0)
					y++;
				iter = iter.getNextNode();
			}
			
			return y;			
		}
		
		
		int nodePosition = 0;
		while(nodePosition<Mid+1)
		{
			midNode = midNode.getNextNode();
			nodePosition++;
		}
 
		while(Left <= Right && str.compareToIgnoreCase(midNode.getNodeValue()) != 0) 
		{
			if(str.compareToIgnoreCase(midNode.getNodeValue()) < 0) 
			{
				Right = Mid - 1 ;
			} 
			else 
			{
				Left = Mid + 1 ;
			}
			Mid = (Left + Right) / 2 ;

			// todo
			// update to start iterating from left node or right node, up or down
			// shave a few microseconds off the total search time
			
			nodePosition = 0;
			midNode = Node.getHeadNode();
			while(nodePosition<Mid)
			{
				midNode = midNode.getNextNode();
				nodePosition++;
			}
		}
		
		if(Left <= Right) 
			return Mid ;
		else 
			return -1 ;
	}		
	
	
	/**
	 * Sets the headNode to null
	 * Sets the tailNode to null
	 * Fine garbage collector
	 * Thereby orphaning the linkedlist
	 */
	
	public static void clear()
	{
		Node.headNode = null;
		Node.tailNode = null;
		Node.nodeListLenght = 0;
	}	
	
	
	/**
	 * Returns the current lenght of the linkedlist
	 * @return int
	 */
	
	public static int size()
	{
		return Node.nodeListLenght;
	}
}

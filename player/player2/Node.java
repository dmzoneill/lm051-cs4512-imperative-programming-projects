 

public class Node 
{
	private String nodeValue = null;
	private Node nextNode = null;
	private Node prevNode = null;
	private static Node headNode = null;
	private static Node tailNode = null;
	private static int nodeListLenght = 0;

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
		
		Node currentNode = Node.headNode;
		boolean insertion = false;
		
		while(insertion==false)
		{			
			nextNode = currentNode.getNextNode();
			
			if(newNodeValue.equalsIgnoreCase(currentNode.getNodeValue()))  
			{
				duplicate = true;
				insertion = true;
			}
			
			else if(newNodeValue.compareToIgnoreCase(Node.headNode.getNodeValue())<0)
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
	 * Returns the current lenght of the linkedlist
	 * @return int
	 */
	
	public static int size()
	{
		return Node.nodeListLenght;
	}
	
	/**
	 * Gets a node in the linklist at an index point
	 * @param index
	 * @return
	 */
	
	public static Node get(int index)
	{
		Node node = null;
		int x =0;
		
		if(index>Node.size()-1)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			Node iter = Node.headNode;
		
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
			if(Node.splitNodeValue(iter)[option].toLowerCase().indexOf(str.toLowerCase())>-1)
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
			if(Node.splitNodeValue(iter)[option].toLowerCase().indexOf(str.toLowerCase())>-1)
			{
				nodes[x] = iter;
				x++;
			}			
			iter = iter.getNextNode();
		}
		
		return nodes;		
	}	
	
	
	/**
	 * Too limited by the constraints of java and project specifications to implement the way would like it
	 * In java with the spec aside, i would have a hash map, pointing to each node, and using the hash map to implment the binary search
	 * Java aside, pointers....... stl?
	 * 
	 * Currenly puts all the nodes into a Node array and searches them
	 * 
	 * @param str startsWith
	 * @return int 
	 */
	
	public static int binarySearch(String str)
	{		
		Node[] nodes = Node.toArray();
		
		if(nodes.length==0)
			return -1;
		
		int Left =0;
		int Right = nodes.length;
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
}

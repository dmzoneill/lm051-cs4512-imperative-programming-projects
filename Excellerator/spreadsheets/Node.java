package spreadsheets;


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
	
	private static Node currentSheet = null;


	public Node()
	{
		
	}	
	

	/**
	 * Constructor for a new node
	 * @param nodeValue String
	 */
	
	public Node(String nodeValue)
	{
		if(nodeValue.length()>30)
		{
			nodeValue = nodeValue.substring(0, 30);
		}
		this.nodeValue = nodeValue;		
		
		// if there is currently no selected sheet
		// the first node will become the default sheet
		
		if(Node.currentSheet==null)
		{
			Node.currentSheet = this;
		}
		
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
	 * Sets the value stored in this node
	 * @return String
	 */
	
	public void setNodeValue(String Value)
	{
		if(Value.length()>30)
			Value = Value.substring(0,30);
		
		this.nodeValue = Value;		
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
	 * Sets the next sheet as the current sheet
	 * If there is no next sheet, the current sheet will remain the same
	 */
	
	public static void MoveForward()
	{
		if(Node.currentSheet.getNextNode()!=null)
		{
			Node.currentSheet = Node.currentSheet.getNextNode();
			System.out.println("Moving Forward to Sheet : " + Node.currentSheet.getNodeValue());
		}		
	}
	
	
	/**
	 * Sets the previous sheet as the current sheet
	 * If there is no previous sheet, the current sheet will remain the same
	 */
	
	public static void MoveBack()
	{
		if(Node.currentSheet.getPrevNode()!=null)
		{
			Node.currentSheet = Node.currentSheet.getPrevNode();
			System.out.println("Moving Back to Sheet : " + Node.currentSheet.getNodeValue());
		}		
	}
	
	
	/**
	 * Goes to the first sheet
	 */
	
	public static void Home()
	{
		Node.currentSheet = Node.getHeadNode();
		System.out.println("Moving to the 1st Sheet : " + Node.currentSheet.getNodeValue());
	}
	
	
	/**
	 * Goes to the last sheet
	 */
	
	public static void End()
	{
		Node.currentSheet = Node.getTailNode();
		System.out.println("Moving to the last Sheet : " + Node.currentSheet.getNodeValue());
	}
	
	
	/**
	 * Creates a new instance of itself with the correct sheet name
	 * Does Not allow Dupliplicates
	 * If the target sheet does not exists, the new node is inserted at the end
	 * If beforeOrAfter is false (insert before)
	 * If beforeOrAfter is true (insert after)
	 * @param newNodeValue
	 * @param beforeOrAfter
	 * @param targetNode
	 * @return
	 */
	
	public static boolean insert(String newNodeValue, boolean beforeOrAfter, String targetNode)
	{
		Node newNode = new Node(newNodeValue);
				
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
		if(Node.Search(newNodeValue)>-1)
		{
			return true;
		}
		
		// find position to insert
		
		int position = Node.Search(targetNode);
		
		if(position>-1)
		{
			// we found the target node to insert before or after
			
			if(position==0 && beforeOrAfter == false)
			{
				// new head		
				newNode.setNextNode(Node.getHeadNode());
				Node.getHeadNode().setPrevNode(newNode);
				Node.headNode = newNode;
				Node.nodeListLenght++;
				
			}
			else if(position==(Node.size()-1) && beforeOrAfter == true)
			{
				// new tail
				newNode.setPrevNode(Node.getTailNode());
				Node.getTailNode().setNextNode(newNode);			
				Node.tailNode = newNode;
				Node.nodeListLenght++;
			}
			else
			{
				// between
				Node nodeTarget = get(position);
				Node nodeBefore = get(position -1);
				Node nodeAfter = get(position +1);
				
				if(beforeOrAfter==false)
				{
					// before target node
					newNode.setPrevNode(nodeBefore);
					newNode.setNextNode(nodeTarget);
					nodeBefore.setNextNode(newNode);
					nodeTarget.setPrevNode(newNode);
					Node.nodeListLenght++;					
					
				}				
				else
				{					
					// after target node
					newNode.setPrevNode(nodeTarget);
					newNode.setNextNode(nodeAfter);
					nodeTarget.setNextNode(newNode);
					nodeAfter.setPrevNode(newNode);
					Node.nodeListLenght++;	
				}				
				
			}			
			
		}
		else
		{
			// cant find the target node, so slap the new one at the end
			newNode.setPrevNode(Node.getTailNode());
			Node.getTailNode().setNextNode(newNode);			
			Node.tailNode = newNode;
			Node.nodeListLenght++;
		}
		
			
		return duplicate;
	}
	
	
	
	public static boolean move(String Sheet, boolean beforeOrAfter, String targetSheet)
	{
		boolean success = false;
		
		int positionSheet = Node.Search(Sheet);
		int positiontargetSheet = Node.Search(targetSheet);
		
		// if both the sheet to move and the detination sheet exist
		// also were not trying to move the sheet to its current position
		
		if((positionSheet>-1 && positiontargetSheet>-1) && Sheet.equalsIgnoreCase(targetSheet)!=true)
		{
			Node nodeToMove = get(positionSheet);			
			Node nodeTarget = get(positiontargetSheet);
			
			
			if(nodeToMove != Node.headNode)
			{
				nodeToMove.getPrevNode().setNextNode(nodeToMove.getNextNode());
			}
			else
			{
				Node.headNode = nodeToMove.getNextNode();
				Node.headNode.setPrevNode(null);
			}
			
			if(nodeToMove != Node.tailNode)
			{
				nodeToMove.getNextNode().setPrevNode(nodeToMove.getPrevNode());
			}
			else
			{
				Node.tailNode = nodeToMove.getPrevNode();
				Node.tailNode.setNextNode(null);
			}		
			
			
			if(beforeOrAfter==false)
			{
								
				nodeToMove.setPrevNode(nodeTarget.getPrevNode());
				nodeToMove.setNextNode(nodeTarget);
				
				if(nodeTarget.getPrevNode()!=null)
					nodeTarget.getPrevNode().setNextNode(nodeToMove);
				else
				{
					Node.headNode = nodeToMove;
					Node.headNode.setPrevNode(null);
				}
				
				nodeTarget.setPrevNode(nodeToMove);
				
				success = true;
			}
			else
			{				
				nodeToMove.setPrevNode(nodeTarget);
				nodeToMove.setNextNode(nodeTarget.getNextNode());
									
				if(nodeTarget.getNextNode()!=null)
					nodeTarget.getNextNode().setPrevNode(nodeToMove);
				else
				{
					nodeToMove.setNextNode(null);
					Node.tailNode = nodeToMove;
					
				}
				
				nodeTarget.setNextNode(nodeToMove);
				
				success = true;
			}
		}		
		
		return success;
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
		
		// leave at least 1 sheet
		
		if(Node.size()>1)
		{
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
	}
	
	
	/**
	 * Renames a nodes value
	 * Searches for an exiting node with the same name as the newName supplied
	 * Does not rename is a sheet with the same value exists and returns false
	 * otherwise it returns true
	 * @param index
	 */
	
	public static boolean rename(String current, String newName)
	{
		boolean result = true;
		
		int targetNodePos = Node.Search(current); 
		Node targetNode = Node.get(targetNodePos);
		
		if(Node.Search(newName)>-1)
		{
			result = false;
		}
		else
		{
			targetNode.setNodeValue(newName);		
		}
		
		return result;
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
		Node iter;
		Node node = null;
		
		if(index>Node.size()-1)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			int x = 0;	
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
			
			return node;
		}
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
	 * @param str startsWith
	 * @return int 
	 */
	
	public static int Search(String str)
	{				

		Node iter = Node.getHeadNode();
		int y = 0;
		int index = -1;
		while(iter!=null)
		{
			if(iter.getNodeValue().compareToIgnoreCase(str)==0)
			{
				index = y;
			}
			y++;
			iter = iter.getNextNode();
		}
			
		return index;				
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
	
	/**
	 * Displays the linked List
	 *
	 */
	
	public static void displaySheets()
	{
		Node iter = Node.getHeadNode();
		
		while(iter!=null)
		{
			System.out.println(iter.getNodeValue());
			iter = iter.getNextNode();
		}
				
	}
}

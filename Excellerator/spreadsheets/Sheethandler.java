package spreadsheets;

import java.util.Random;

public class Sheethandler {
	
	public Sheethandler()
	{	
		
		for(int y=0; y < 10; y++)
		{
			if(Node.insert("Sheet"+(y+1),false,"")==false)
	    		System.out.println("Sucessfully added sheet " + "Sheet"+(y+1));
	    	else
	    		System.out.println("Duplicate " + "Sheet"+(y+1) + " detected");
		}
				
		System.out.println("");
		System.out.println("");
		
		Node.displaySheets();
		
		System.out.println("");
		System.out.println("");
		
		int sheets;
		
		sheets= Node.size();

		
		Random rand1 = new Random();
		
		String sheetPos; 		
		String sheetDes;
		
		sheetPos = Node.get(rand1.nextInt(sheets)).getNodeValue();
		sheetDes = Node.get(rand1.nextInt(sheets)).getNodeValue();	
		sheets= Node.size();
		System.out.println(Node.size());
		System.out.println(sheetPos + " " + sheetDes);
		Node.move(sheetPos, rand1.nextBoolean(), sheetDes);		
		Node.displaySheets();
		System.out.println("");
		System.out.println("");
		
		
		sheetPos = Node.get(rand1.nextInt(sheets)).getNodeValue();
		sheetDes = Node.get(rand1.nextInt(sheets)).getNodeValue();	
		sheets= Node.size();
		System.out.println(Node.size());
		System.out.println(sheetPos + " " + sheetDes);
		Node.move(sheetPos, rand1.nextBoolean(), sheetDes);		
		Node.displaySheets();
		System.out.println("");
		System.out.println("");
		
		
		Node.End();
		
		Node.Home();
		

		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveForward();
		Node.MoveForward();
		Node.MoveForward();
		Node.MoveForward();
		Node.MoveForward();
		Node.MoveForward();
		
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();
		Node.MoveBack();

		Node.displaySheets();
	}
	
	
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Sheethandler();

	}

}

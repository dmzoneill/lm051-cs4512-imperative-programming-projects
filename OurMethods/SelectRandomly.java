package OurMethods;
import java.util.Random;

public class SelectRandomly 
{	
	private String[] people; // holds the list of people
	private int count; // the current amount of people in the list
	Random random;

	public SelectRandomly() 
	{
		// initialise the data members	
		this.people = new String[10];	
		this.count = 0;
		this.random = new Random(); // use the java.util.Random class for pseudo random numbers
	}

	public void addPerson(String name) 
	{
		if(this.count == this.people.length)
		{
			System.out.println("Sorry the List is full!");
			return;
		}
		
		this.people[this.count] = name;
		this.count++;
    }
	
	public void randomizedPicker()
	{
		String[] temp = new String[this.people.length];		
		
		for(int k = 0; k < this.people.length; k++)
			temp[k] = this.people[k];
		
		int pickNext; // next index(person) to pick
		int peopleLeft = this.count; // the amount of people in the list
		
		for(int h=0; h < this.count; h++)
		{
			pickNext = this.random.nextInt(peopleLeft); // 0 to peopleLeft
			System.out.println(temp[pickNext]);
						
			// once we pick one, move the ones after the one we've picked up the list			
			for(int u = pickNext; u < peopleLeft -1; u++)
			{
				temp[u] = temp[u+1]; 
				// since we've taken out one, we take the one after and put it in its place 
				// and so on till the end of the list
			}				
			
			peopleLeft--; // we've picked from the list ( so now its -1 )			
		}	
	}
	
	public static void main(String[] args) 
	{
		// create a new instance of our class
		SelectRandomly p = new SelectRandomly();
		// add some people
		p.addPerson("john");
		p.addPerson("sarah");
		p.addPerson("mick");
		p.addPerson("vanessa");
		p.addPerson("peter");
		p.addPerson("alex");
		p.addPerson("michael");
		p.addPerson("clem");
		p.addPerson("dermot");
		// randomly pick all people
		p.randomizedPicker();
	}
}
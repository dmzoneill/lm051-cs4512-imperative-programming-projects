package OurMethods;

public class TimeReverse 
{
	public void reverse(String val)
	{
		String[] tims = val.split(":");
		int hours = Integer.parseInt(tims[0]); // note (int) "5" does not work
		int minutes = Integer.parseInt(tims[1]); // so we use the integer class and the method parseInt
		
		if(hours > 23 || minutes > 59)
		{
			System.out.println("invalid time : " + val);
			return;
		}
		
		int revHours = 0;
		int revMins = 0;
		
		if(hours >= 12)
		{
			if(minutes > 0)
				revHours = 23 - hours;					
			else
				revHours = 24 - hours;			
		}
		else
		{
			if(minutes > 0)
				revHours = 11 - hours;
			else
				revHours = 12 - hours;			
		}						
		
		if(minutes == 0)
			revMins = 0;
		else if(minutes == 30)
			revMins = 30;			
		else
			revMins = 60 - minutes;
		
		System.out.println("Old Time : " + val);
		System.out.println("Reverse on y axis : " + revHours + ":" + revMins);
		System.out.println("");
		
	}
	
	public static void main(String[] args)
	{
		TimeReverse t = new TimeReverse();
		t.reverse("1:22");
		t.reverse("2:21");
		t.reverse("3:21");
		t.reverse("4:45");
		t.reverse("5:59");
		t.reverse("6:00");		
		t.reverse("7:22");
		t.reverse("8:21");
		t.reverse("9:21");
		t.reverse("10:45");
		t.reverse("11:59");
		t.reverse("12:00");			
		t.reverse("13:21");
		t.reverse("14:45");
		t.reverse("15:59");
		t.reverse("16:00");		
		t.reverse("17:22");
		t.reverse("18:21");
		t.reverse("19:21");
		t.reverse("20:45");
		t.reverse("21:59");
		t.reverse("22:00");	
		t.reverse("23:22");
		t.reverse("0:0");
		t.reverse("24:61");
	}
}

public class ContactList 
{
    private String[] list ; 
    private int contactCount;
    
    public ContactList()
    {
        list = new String[50] ;
        contactCount = 0 ;
    }
    
    public void insert(String Str)
    {
        if(contactCount < list.length) {
            list[contactCount] = new String(Str) ;
            contactCount++ ;   
        }
    }

  

}
       	


// Modify the phone typeText method by removing the clearDisplay() operation.


// Create a list of 10 phones and assigne them random numbers
Nokia6110nV2[] phoneUsers = new Nokia6110nV2[10] ;
int i, j, k ;
i = 0 ;
while(i < phoneUsers.length) {
    j = (int) (Math.random() * 1000000) ;
    phoneUsers[i] = new Nokia6110nV2("086 " + j) ;
    phoneUsers[i].switchOn() ;
    i++ ;
}

// Randomly pick pairs of phones to send messages to each other
i = 0 ;
while(i < 20) {
    j = (int) (Math.random() * phoneUsers.length) ;
    k = (int) (Math.random() * phoneUsers.length) ;
    phoneUsers[j].sendText(phoneUsers[k].getNumber(),"This is an automated text message") ;
    phoneUsers[k].showLatestText() ;
    phoneUsers[k].sendText(phoneUsers[j].getNumber(),"Please remove my number from your subscriber list") ;
    phoneUsers[j].showLatestText() ;
    i++ ;
    phoneUsers[j].typeText("escape") ;
}    


// Randomly pick pairs of phones to send messages to each other
// ensuring that the two phones chosen are in fact different phones
i = 0 ;
while(i < 20) {
    j = (int) (Math.random() * phoneUsers.length) ;
    k = (int) (Math.random() * phoneUsers.length) ;
    while(k == j) {
    	k = (int) (Math.random() * phoneUsers.length) ;
	}    
    phoneUsers[j].sendText(phoneUsers[k].getNumber(),"This is an automated text message") ;
    phoneUsers[k].showLatestText() ;
    phoneUsers[k].sendText(phoneUsers[j].getNumber(),"Please remove my number from your subscriber list") ;
    phoneUsers[j].showLatestText() ;
    i++ ;
    phoneUsers[j].typeText("escape") ;
} 




// Add  a "messages list" to the phone to improve its functionality
//        -- as a private variable

    private WordList messages ;

//        -- initialise it in the constructor

    messages = new WordList(150) ;

//
//
// Add in the the checkMessages method as a slightly modified version of the getLatestText

    public void checkMessages()
    {
        /**
         * Check with the provider if there is a text message pending. The
         * provider will either send back a text OR a signal (i.e. the
         * special value 'null') to indicate that there are no messages. 
         */
        Text txt = getLatestText() ;
        // If the provider sent back a message (i.e. the value 
        // returned is NOT null) then show the message on the screen.
        while(txt != null) {
            messages.insert(format(txt));
            txt = getLatestText() ;
        }
    }    

// Add a new method called displayMessages to display the message list in the terminal window
    public void displayMessages()
    {
        messages.display() ;
    }



// To show that the messages can be stored in a list
i = 0 ;
while(i < 20) {
    j = (int) (Math.random() * phoneUsers.length) ;
    k = (int) (Math.random() * phoneUsers.length) ;
    phoneUsers[j].sendText(phoneUsers[k].getNumber(),"This is an aoutmated text message") ;
    phoneUsers[k].checkMessages() ;
    phoneUsers[k].sendText(phoneUsers[j].getNumber(),"Please remove my number from your subscriber list") ;
    phoneUsers[j].checkMessages() ;
    i++ ;
}
    

// To show that the messages are being stored in a list
i = 0 ;
while(i < 10) {
    System.out.println("MESSAGES FOR: " + phoneUsers[i].getNumber()) ;
    phoneUsers[i].displayMessages() ;
    System.out.println() ;
    i++ ;
}




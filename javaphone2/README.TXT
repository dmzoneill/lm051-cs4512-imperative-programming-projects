Project: Mobile Phone Week 1
Authors: Dermot Shinners-Kennedy and David J. Barnes 

Copyright 2008.

PROJECT NOTES
-------------
The project models the operation of a mobile phone. Phones can be "connected" to any of the following
providers by using the associated phone number prefix

Prefix      Provider
------      --------
 083        three
 085        Meteor
 086        O2
 087        Vodafone

You can decide which network you would like to subscribe to when you are 'creating' a phone object.
If you do not choose one of these providers the phone creation operation will fail and the project
will 'throw an exception' and fail. The phone numbers you use are not checked for correctness so you
are free to use any numbers you like.

Each phone has a battery life of approximately five hours - represented as 18,000 seconds (i.e. 5x60x60).
For each second the phone is charging the battery level increases by 600 seconds (i.e. 10 mins) so it
takes 30 seconds to charge the battery completely if it is flat. Once you start charging the battery
you cannot interrupt it - you must wait until it is fully charged.

Each second the phone is on uses ten seconds of battery life so after 30 minutes a fully charged battery
would go 'flat'. However, because of the way the we have modelled the battery life in this first project
the battery levels are not precise. We don't change the battery level until the phone is being switched
off so there may be times when the battery level shows a value greater than 18,000 or less than zero. The
only affect this will have is that you will be able to switch on and use a the phone for longer periods
than should be technically possible. A more sophisticated modelling of the battery life is something we 
can revisit in a later project.
      
If you attempt to switch on a phone when the battery is low the phone will briefly (1.5 seconds) display
a prompt encouraging you to charge it and then it will switch off.

When a phone is switched on a window representing the 'phone screen' is displayed. You can type a character
on the screen using the keyPress method. To type more characters you will have to use the keyPress method
each time. There is no delete feature. This is something we will want to improve upon in the near future.

For the time being, you can use the sendText method to send messages between phones. HOWEVER, each phone
can only store one message at a time. If a phone receives several messages only the most recent or 'latest
one' will be stored and available for display. Use the showLatest method to check for a message. We will
need to improve the message handling capabilities of the phones pretty quickly if they are to continue to
be of any use or interest.


 
LEARNING NOTES
--------------
This is the first project in a series of mobile phone projects. 

This first example provides basic methods including
    - turning the phone on 
    - turning the phone off
    - charging the battery
    - typing a single character on the screen
    - sending a text message 
    - receiving a text message

The key concepts introduced in this example are
    - difference between class and object instance
    - methods and variables (for state representation)
    - state - the state of an object can ONLY be inspected (get) or altered (set)
        - alteration uses assignment
        - inspection uses 
            - state tests (i.e. conditions) exemplified by use of iteration (i.e. while) and selection (i.e. if)
            - variable reference exemplified by use of return, parameters and variables on RHS of assignments
    - counting (i.e. assignments of the type X = X + n)
    
These concept expositions are the first in a 'spiralling' concept development model so they will be revisited
several times in later projects.

Another important goal of this project is to introduce the BlueJ environment and provide an opportunity
to practice with it. Getting comfortable and proficient with the environment will provide lasting benefits.
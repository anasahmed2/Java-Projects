# The Chip Counter

## As a user, I want to be able to add chipType to the chipType counter
## As a user, I want to be able to view the list of all different types of chipType
## As a user, I want to be able to look at the total sum of money from all the chipType
## As a user, I want to be able to remove chipType from my total chipType count
## As a user, I want to be able to save my list which contains the different chips
## As a user, I want to be able to load my list which contains the different chips
## As a user, I want to be able to only view the type of chip that is selected by the user in the list of Chips

The Chip Counter:
- The application will keep track of the count of poker chipType.
The application will first sort the poker chipType into their respective groups.
And then the application will count the total chip count and the total
money count.
- This application will mostly be used by casinos and online gambling websites.
Regular people are also welcome to use this application as this application
would allow users to easily keep track of their chipType and money.
- I am interested in doing this project because sometimes when I am in a Casino
gambling my money away I loose track of all the money I have because of all the chipType
that I have.

# Instructions for Grader
- You can generate the first required action related to the user story of adding
  chips to a list of chips by running the application and on the first open screen pressing
  the start app button. This will then lead you into a new window which will allow you to choose
  the type of chips you want to add to the list of chips and also allow you to enter the number
  of chips you want to add. You can then press the add chips button in the bottom panel which
  will then add that type of chips with the amount of chips to the list of chips.
- You can generate the second required action related to the user story of view the list of chips
  by going into the bottom panel and selecting the button view chip types. This will display
  all the chips that you have added to the list.
- You can locate my visual component by running the program and the first window that opens up
  has my visual component which is a picture of a man playing poker. 
- You can save the state of my application by simply clicking on the save button in the second
  window.
- You can load that state by clicking on the load button that shows up on the first window
  when you load up the program.
- The one other user story that I displayed when display total money which can be seen
  by clicking on the total money button which will display the total money.
- The other thing related chips and list of chips that you can use is select chip types button
  which will allow you to choose a chip type and only display that chip type in the list of chips.

# REFERENCES
- https://www.google.com/url?sa=i&url=https%3A%2F%2Fpngtree.com%2Fso%2Fcoin&psig=AOvVaw0keoBBLOhxTFbc4njYu2eO&ust=1711787413223000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCKj5qpeHmYUDFQAAAAAdAAAAABAE
- https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.britannica.com%2Fstory%2Fpoker-hands-ranked&psig=AOvVaw1blBkt6FWJfLoJH-fBLfYu&ust=1711787434719000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCKij_KGHmYUDFQAAAAAdAAAAABAE
- https://www.youtube.com/watch?v=Kmgo00avvEw&t=3673s
- https://www.youtube.com/watch?v=1vVJPzVzaK8&list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U
- https://www.javatpoint.com/java-windowlistener#:~:text=The%20Java%20WindowListener%20is%20notified,awt.
- https://docs.oracle.com/javase/tutorial/uiswing/
- https://www.geeksforgeeks.org/introduction-to-java-swing/
- https://www.edureka.co/blog/java-swing/

PHASE 4: TASK 3
- If I had been given more time to work on my project I would focus most on refactoring the
  classes in my ui package. Especially the two classes ChipCounterApp and Game. If I were to
  look closely at both of these classes there are a bunch of methods that are present in both
  of the classes which makes readability difficult. If I were to look at Game class first there
  are a bunch of changes I would make to the class. First off I would probably separate it into
  multiple classes, in which one classes is solely used for buttons just because of how many
  buttons I have. I would probably branch of Game into another class which would make it branching
  it off into three classes. This new class would contain all the JOptionPane and all the times
  I used JTextField, these changes would make the class much cleaner and much easier to read.
  The changes that I would make to ChipCounterApp are pretty much the same as it would be
  making a lot of methods improve in readability by making helpers and maybe refactoring the class
  into multiple to allow for a smaller and more readable class.
 
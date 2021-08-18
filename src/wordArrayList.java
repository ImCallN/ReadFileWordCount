import java.io.*;
import java.util.Scanner;


import java.util.ArrayList;

public class wordArrayList
{
    ArrayList<wordEntry> wordEntryArrayList = new ArrayList<wordEntry>();               //our arraylist to hold all of our objects
    
    //Method to compare the string from the scanner to the string in the arrayList
    //If there is no such string in the arrayList, it gets added, otherwise a counter
    //gets increased for the word
    public void compareStrings(String path)                                             
    {
        File inputFile = new File(path);        //file obj for our scanner
        try
        {
            Scanner scanFile = new Scanner(inputFile);      //scans through the file and gives us the input
            
            while(scanFile.hasNext())
            {
                wordEntry newWord = new wordEntry();
                newWord.word = scanFile.next().toLowerCase().replaceAll("\\p{Punct}", "");                 //sets the word of the wordEntry object to the scanFile word
                if(wordEntryArrayList.size() > 0)               //checks to see the size of the 
                {
                    for(wordEntry r : wordEntryArrayList)   
                    {
                        if(r.word.equals(newWord.word))             //compares the words to see if there is a duplicate
                        {
                            r.counter++;                            //if there is then increase that objects counter
                            newWord = null;                         //found a match so we set the object to null to act as a flag
                            break;                                  //break out of our for loop
                        }
                    }
                    if(newWord != null)                             //check to see if we flipped our newWord flag above, if it isn't null then we add it to the arrayList
                    {               
                    newWord.counter = 1;                            //sets the counter for the new object to 1
                    wordEntryArrayList.add(newWord);                //adds the object to the arrayList
                    }
                }
                else                                                //If size !> 0, then we just add the object to the arrayList
                {
                    newWord.counter = 1;
                    wordEntryArrayList.add(newWord);
                }
            }
            scanFile.close();                                       //Close the scanner object
        }
        catch(IOException e)                                        //try catch
        {
            e.printStackTrace();
        }
    }
    


    //Method to display our information
    public void displayArray()
    {
        int totalCount = 0;                                     //totalCount for all of our words

        for(wordEntry r : wordEntryArrayList)                   //loop through all of the objects in the array list
        {
            totalCount = totalCount + r.counter;                //increase the total count
            System.out.println(r.word);                         //print the word field
            System.out.println(r.counter);                      //print the counter field   
            System.out.println("****************************************************************************");
        }

        System.out.println("Total Words: " + totalCount);       //print out the total count at the bottom
    }


    //Method to bubble sort our array and then display it, in order from lowest to highest
    public void bubbleSortArray()                               
    {
        boolean valuesSwitched = false;                                        //boolean to keep track if we need to switch values or not        
        int len = wordEntryArrayList.size();
        for(int i = 1; i <= len; i++)
        {
            if(i == len)                                                       //check to see if we are at the end of the len
            {
                if(!valuesSwitched) break;                                      //if we are at the end and flag false, then we are done

                valuesSwitched = false;                                         //else flip the flag to false and go back to the beginning
                i = 1;
            }
            if(wordEntryArrayList.get(i - 1).counter > wordEntryArrayList.get(i).counter)           //comparing the counters of the elements to each other
            {
                wordEntry tempEntry = new wordEntry();                                              //our temp object
                tempEntry = wordEntryArrayList.get(i - 1);                                          //setting the temp obj to obj
                wordEntryArrayList.set(i - 1, wordEntryArrayList.get(i));                           //setting the objects of i-1 to i
                wordEntryArrayList.set(i, tempEntry);                                               //setting the object of i to tempEntry
                valuesSwitched = true;                                                              //flipping our boolean to true to let the for loop to go again
            }
        }
    }
}
    


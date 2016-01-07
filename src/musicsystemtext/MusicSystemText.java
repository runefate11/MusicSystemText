package musicsystemtext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author 348564469
 */
public class MusicSystemText {

    public void signOut (String studentNum, String barcode){
        String [] fileLine;
        File studentFile = new File ("studentFile.txt");
        
        boolean studentFound = false;
        
        String firstName;
        String lastName;
        
        Scanner scan = null;
        try {
            scan = new Scanner(studentFile);
            // continue running through file until end or until match is found
            while (scan.hasNext()) {
                fileLine = scan.nextLine().split(",");
                // if the username and passwords match, log into account
                if (studentNum.equals(fileLine[2])) {
                    studentFound = true;
                    firstName = fileLine[0];
                    lastName = fileLine[1];
                }
            }
            scan.close();
        } catch (IOException e) { // if file not found, output message
            System.out.println("Can't find file.");
        }
        
        if (studentFound == false){
            System.out.println("That student was not found.");
        }
        
        File instrumentFile = new File ("instrumentFile.txt");
        
        boolean instrumentFound = false;
        
        String instruNum;
        
        try {
            scan = new Scanner(instrumentFile);
            // continue running through file until end or until match is found
            while (scan.hasNext()) {
                fileLine = scan.nextLine().split(",");
                // if the username and passwords match, log into account
                if (barcode.equals(fileLine[2])) {
                    instrumentFound = true;
                    instruNum = fileLine[0];
                }
            }
            scan.close();
        } catch (IOException e) { // if file not found, output message
            System.out.println("Can't find file.");
        }
        
        if (instrumentFound == false){
            System.out.println("That instrument was not found.");
        }
        
        ////////////////////////////////////////////////////////////////////////
        
        File signOutFile = new File("signOutFile.txt"); // open file
        try {
            PrintWriter regWriter = new PrintWriter(new FileWriter(signOutFile, true));
            regWriter.println();
            regWriter.close();
        } catch (IOException e) {
            System.out.println("Can't find file.");
        }
    }
    
    public void signIn (int barcode){
        
    }
    
    public void addArchive(){
        
    }
    
    public void addInstrument(){
        
    }
    
   public void removeInstrument(){
        
    }
    
    public void addStudent(){
        
    }
    
    public void removeStudent(){
        
    }
    
    public void search (int searchField){
        
    }
    
    public void seeSignOuts (){
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

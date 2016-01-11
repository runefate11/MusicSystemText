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

    public void signOut(String studentNum, String barcode) {
        String[] fileLine;
        Scanner scan = null;

        boolean studentFound = false;
        String firstName = "";
        String lastName = "";

        File studentFile = new File("studentFile.txt");
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

        boolean instrumentFound = false;
        String instruNum = "";

        File instrumentFile = new File("instrumentFile.txt");
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

        if (studentFound == false) {
            System.out.println("That student was not found.");
        }
        
        if (instrumentFound == false) {
            System.out.println("That instrument was not found.");
        }

        if (studentFound == true && instrumentFound == true) {
            File signOutFile = new File("signOutFile.txt"); // open file
            try {
                PrintWriter archiveWriter = new PrintWriter(new FileWriter(signOutFile, true));
                String signOutDate = ""; /// add date code
                InstrumentArchive i = new InstrumentArchive(firstName, lastName, studentNum, instruNum, signOutDate, "-");
                archiveWriter.println(i);
                archiveWriter.close();
            } catch (IOException e) {
                System.out.println("Can't find file.");
            }
        }
    }

    public void signIn(String barcode) {
        String[] fileLine;
        Scanner scan = null;
        
        boolean instrumentFound = false;
        String instruNum = "";

        File instrumentFile = new File("instrumentFile.txt");
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

        
        ////// add more code
    }

    public void addArchive() {

    }

    public void addInstrument() {

    }

    public void removeInstrument() {

    }

    public void addStudent() {

    }

    public void removeStudent() {

    }

    public void search(int searchField) {

    }

    public void seeSignOuts() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}

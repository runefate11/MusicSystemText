package musicsystemtext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    /**
     * This method adds a new archive object to the archive Admin function
     * Gregory Wong
     *
     * @param firstName Student first name
     * @param lastName Student last name
     * @param studentNum Student number
     * @param instruNum Instrument number
     * @param dateIn Date that the instrument came in
     * @param dateOut Date the instrument was signed out
     */
    public void addArchive(String firstName, String lastName, String studentNum, String instruNum, String dateIn, String dateOut) {
        //create new instance of an instrument archive object
        InstrumentArchive i = new InstrumentArchive(firstName, lastName, studentNum, instruNum, dateIn, dateOut);
        //create a printwriter object for writing
        PrintWriter file = null;
        //write to an archive file
        try {
            file = new PrintWriter(new FileWriter("Archive.txt", true));
            file.println(i);
            file.close();
        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }

    /**
     * Add an instrument to the file of instruments Admin function Gregory Wong
     *
     * @param instruNum Instrument's number
     * @param make Make of the instrument
     * @param barcode Barcode on instrument
     * @param model Instrument model
     */
    public void addInstrument(String instruNum, String make, int barcode, String model) {
        //create a new instrument object
        Instrument i = new Instrument(instruNum, make, barcode, model);
        //create a printwriter object for writing
        PrintWriter file = null;
        //write to an archive file
        try {
            file = new PrintWriter(new FileWriter("instrumentFile.txt", true));
            file.println(i);
            file.close();
        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }
    /**
     * This is the removal method for an instrument Gregory Wong
     *
     * @param barcode The barcode of the instrument you wish to remove from the
     * list
     */
    public void removeInstrument(int barcode) {
        //create a list for the instruments in the file currently
        ArrayList<Instrument> list = new ArrayList<>();
        //create a scanner object to read file
        Scanner keyboard = null;
        try {
            keyboard = new Scanner(new File("instrumentFile.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Error occurred in removing the instrument");
        }
        keyboard.useDelimiter(",");
        //read the file
        while (keyboard.hasNextLine()) {
            //get each line of data and split into the various fields
            String[] line = keyboard.nextLine().split(",");
            //assign values based on parsing
            String instruNum = line[0];
            String make = line[1];
            int serial = Integer.valueOf(line[2]);
            String model = line[3];
            //create an instrument object
            Instrument i = new Instrument(instruNum, make, serial, model);
            //add to the list of instruments in the file
            list.add(i);
        }
        //tracks whether or not the barcode in question has been found
        boolean found = false;
        int count2 = 0;
        //search and delete from file
        while (found == false) {
            //if the corresponding barcode is found
            if (list.get(count2).barcode == barcode) {
                //get rid of that entry
                list.remove(count2);
                found = true;
            } else {
                count2++;
            }
        }
        //only do this if the barcode was found
        if (found == true) {
            //create a printwriter object for overwriting the file with the updated list
            PrintWriter overwrite = null;
            try {
                overwrite = new PrintWriter("instrumentFile.txt");
            } catch (FileNotFoundException ex) {
                System.out.println("Could not overwrite file");
            }
            //rewrite updated list to file
            for (int count3 = 0; count3 <= list.size() - 1; count3++) {
                overwrite.println(list.get(count3));
            }
            overwrite.close();
        }
    }

    /**
     * This method will add a student to the list of students Admin Function
     * Gregory Wong
     *
     * @param firstName Student's first name
     * @param lastName Student's last name
     * @param studentNum Student number
     */
    public void addStudent(String firstName, String lastName, String studentNum) {
        Student s = new Student(firstName, lastName, studentNum);
        PrintWriter file = null;
        //write to an archive file
        try {
            file = new PrintWriter(new FileWriter("studentFile.txt", true));
            file.println(s);
            file.close();
        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }
    /**
     * This is a removal method for students from the student list
     * Admin Function
     * Gregory Wong
     * @param studentNum Student number 
     */
    public void removeStudent(String studentNum) {
        //create a list for the instruments in the file currently
        ArrayList<Student> list = new ArrayList<>();
        //create a scanner object to read file
        Scanner keyboard = null;
        try {
            keyboard = new Scanner(new File("instrumentFile.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Error occurred in removing the instrument");
        }
        keyboard.useDelimiter(",");
        //read the file
        while (keyboard.hasNextLine()) {
            //get each line of data and split into the various fields
            String[] line = keyboard.nextLine().split(",");
            //assign values based on parsing
            String firstName = line[0];
            String lastName = line[1];
            String num = line [2];
            //create a student object
            Student s = new Student (firstName, lastName, num);
            //add to the list of students in the file
            list.add(s);
        }
        //tracks whether or not the barcode in question has been found
        boolean found = false;
        int count2 = 0;
        //search and delete from file
        while (found == false) {
            //if the corresponding student is found
            if (list.get(count2).studentNum.equals(studentNum)) {
                //get rid of that entry
                list.remove(count2);
                found = true;
            } else {
                count2++;
            }
        }
        //only do this if the student was found
        if (found == true) {
            //create a printwriter object for overwriting the file with the updated list
            PrintWriter overwrite = null;
            try {
                overwrite = new PrintWriter("instrumentFile.txt");
            } catch (FileNotFoundException ex) {
                System.out.println("Could not overwrite file");
            }
            //rewrite updated list to file
            for (int count3 = 0; count3 <= list.size() - 1; count3++) {
                overwrite.println(list.get(count3));
            }
            overwrite.close();
        }
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

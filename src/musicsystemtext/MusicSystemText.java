package musicsystemtext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 348564469
 */
public class MusicSystemText {

    /**
     * Takes in a student code and a instrument code, and basically signs out
     * that instrument under that student. It first checks if the student and
     * instrument exists. Then it checks if the instrument has been sign out
     * before. Only checks out the instrument if student exists; instrument
     * exists; an instrument is not signed out
     *
     * @param studentNum - the student number that is scanned
     * @param barcode - the instrument barcode that is scanned
     */
    public void signOut(String studentNum, String barcode) {
        String[] fileLine; // holds the split line
        Scanner scan = null;

        boolean studentFound = false; // tells if the student was found or not
        String firstName = ""; // stores the first name that corresponds to that student number
        String lastName = ""; // stores the last name that corresponds to that student number
        File studentFile = new File("studentFile.txt"); // open file of student records
        try {
            scan = new Scanner(studentFile);
            // continue running through file until end or until match is found
            while (scan.hasNext()) {
                fileLine = scan.nextLine().split(","); // read and split line
                if (studentNum.equals(fileLine[2])) { // if the student numeberrs match...
                    // state that the student exists and store their first and last name
                    studentFound = true;
                    firstName = fileLine[0];
                    lastName = fileLine[1];
                    break;
                }
            }
            scan.close();
        } catch (IOException e) { // if any error occurs, output message
            System.out.println("Can't find file.");
        }

        boolean instrumentFound = false; // tells if the instrument was found or not
        String instruNum = ""; // stores the instrument name that corresponds to that barcode
        File instrumentFile = new File("instrumentFile.txt"); // open file of instrument records
        try {
            scan = new Scanner(instrumentFile);
            // continue running through file until end or until match is found
            while (scan.hasNext()) {
                fileLine = scan.nextLine().split(","); // read and split line
                if (barcode.equals(fileLine[2])) { // if the barcodes match...
                    // state that the instrument exists and store its instrument name
                    instrumentFound = true;
                    instruNum = fileLine[0];
                    break;
                }
            }
            scan.close();
        } catch (IOException e) { // if any error occurs, output message
            System.out.println("Can't find file.");
        }

        // only run if the instrument and student exists
        if (studentFound == true && instrumentFound == true) {
            boolean instrumentSignOuted = false; // tells if the instrument has been signed out
            File signOutFile = new File("signOutFile.txt"); // open file of instruments signed out
            try {
                scan = new Scanner(signOutFile);
                // continue running through file until end or until match is found
                while (scan.hasNext()) {
                    fileLine = scan.nextLine().split(","); // read and split line
                    if (instruNum.equals(fileLine[3])) { // if the instrument names match...
                        // state that the instrument has been signed out
                        instrumentSignOuted = true;
                        break;
                    }
                }
                scan.close();
            } catch (IOException e) { // if any error occurs, output message
                System.out.println("Can't find file.");
            }

            // only run if the instrument has been not signed out
            if (instrumentSignOuted == false) {
                try {
                    PrintWriter archiveWriter = new PrintWriter(new FileWriter(signOutFile, true)); // add to end of file
                    // initialize date formating and get current date
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String signOutDate = dateFormat.format(new Date());
                    // create instrument
                    InstrumentArchive i = new InstrumentArchive(firstName, lastName, studentNum, instruNum, signOutDate, "-");
                    // print to archive file and close the writer
                    archiveWriter.println(i);
                    archiveWriter.close();
                } catch (IOException e) { // if any error occurs, output message
                    System.out.println("There is an error.");
                }
            } else {
                System.out.println("That instrument is already signed out.");
            }
        } else {
            System.out.println("The instrument barcode or student ID doesn't exist.");
        }
    }

    /**
     * Takes in a instrument barcode and signs in the instrument only if the
     * instrument exists and it has been signed out. Takes out that entry in the
     * sign out file and places it into the archive file
     *
     * @param barcode - the instrument barcode that is scanned
     */
    public void signIn(String barcode) {
        String[] fileLine; // holds the split line
        Scanner scan = null;

        boolean instrumentFound = false; // tells if the instrument was found or not
        String instruNum = ""; // stores the instrument name that corresponds to that barcode

        File instrumentFile = new File("instrumentFile.txt"); // open file of isntrument records
        try {
            scan = new Scanner(instrumentFile);
            // continue running through file until end or until match is found
            while (scan.hasNext()) {
                fileLine = scan.nextLine().split(","); // read and split line
                if (barcode.equals(fileLine[2])) { // barcode matches...
                    // state that the instrument exists and store its instrument name
                    instrumentFound = true;
                    instruNum = fileLine[0];
                    break;
                }
            }
            scan.close();
        } catch (IOException e) { // if any error occurs, output message
            System.out.println("Can't find file.");
        }

        ArrayList<InstrumentArchive> list = new ArrayList<>(); // array of 
        boolean instrumentSignedOut = false;  // tells if the instrument has been signed out of not

        /// only run if the instrument exists
        if (instrumentFound == true) {
            File signOutFile = new File("signOutFile.txt"); // open file of signed out instruments
            try {
                scan = new Scanner(signOutFile);
                // continue running through file until end
                while (scan.hasNext()) {
                    fileLine = scan.nextLine().split(","); // read and split line
                    if (instruNum.equals(fileLine[3])) { // if instrument names match...
                        // state that the instrument has been signed out
                        instrumentSignedOut = true;
                        // get the current date
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Date date = new Date();
                        String signInDate = dateFormat.format(date);
                        // add instrument to archive
                        addArchive(fileLine[0], fileLine[1], fileLine[2], fileLine[3], fileLine[4], signInDate);
                    } else {
                        // add instrument to list (it skips over the instrument that is signed in)
                        list.add(new InstrumentArchive(fileLine[0], fileLine[1], fileLine[2], fileLine[3], fileLine[4], fileLine[5]));
                    }
                }
                scan.close();
                
                try {
                    PrintWriter file = new PrintWriter(signOutFile); // rewrite over whole sign out file
                    // loop through the whole array list and write it to the file
                    for (int count = 0; count < list.size(); count++) {
                        file.println(list.get(count));
                    }
                    file.close();
                } catch (IOException e) { // if any error occurs, output message
                    System.out.println("Can't find file.");
                }

                // let user know if the instrument hasn't been signed out yet
                if (instrumentSignedOut == false) {
                    System.out.println("The instrument you are trying to sign in has not been signed out yet.");
                }
            } catch (IOException e) {// if any error occurs, output message
                System.out.println("Can't find file.");
            }
        } else {
            System.out.println("The instrument you are trying to sign in doesn't exist.");
        }
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
    public void addInstrument(String instruNum, String make, String barcode, String model) {
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
     * This is a removal method for students from the student list Admin
     * Function Gregory Wong
     *
     * @param studentNum Student number
     */
    public void removeStudent(String studentNum) {
        //create a list for the instruments in the file currently
        ArrayList<Student> list = new ArrayList<>();
        //tracks whether or not the barcode in question has been found
        boolean found = false;
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
            if (!studentNum.equals(line[2])) {
                //create a student object
                Student s = new Student(line[0], line[1], line[2]);
                //add to the list of students in the file
                list.add(s);
            } //if the student number was found
            else {
                found = true;
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

    /**
     * This is the removal method for an instrument Gregory Wong
     *
     * @param barcode The barcode of the instrument you wish to remove from the
     * list
     */
    public void removeInstrument(String barcode) {
        //create a list for the instruments in the file currently
        ArrayList<Instrument> list = new ArrayList<>();
        //tracks whether or not the barcode in question has been found
        boolean found = false;
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
            //only add to overwrite list if the barcode isn't the one you want to delete
            if (!line[2].equals(barcode)) {
                //create an instrument object
                Instrument i = new Instrument(line[0], line[1], line[2], line[3]);
                //add to the list of instruments in the file
                list.add(i);
            } //if the barcode was found
            else {
                found = true;
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
     * Gregory Wong and Devanjith Ganepola
     * @param searchField   The field that admin wants to search through
     *                      0 - first name
     *                      1 - last name
     *                      2 - student number
     *                      3 - instrument number
     *                      4 - date in
     *                      5 - date out
     * @param searchItem    The actual value you are searching for
     */
    public void searchArchive(int searchField, String searchItem) {
        //create scanner object for reading the file
        Scanner keyboard = null;
        try {
            keyboard = new Scanner(new File("Archive.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MusicSystemText.class.getName()).log(Level.SEVERE, null, ex);
        }
        keyboard.useDelimiter(",");
        //search through whole file
        while (keyboard.hasNextLine()) {
            String[] line = keyboard.nextLine().split(",");
            //if the search item is found, or at least part of the item
            if (line[searchField].contains(searchItem)) {
                InstrumentArchive i = new InstrumentArchive(line[0], line[1], line[2], line[3], line[4], line[5]);
                //print out that information
                System.out.println(i.printing());
            }
        }
    }

    /**
     * Gregory Wong and Devanjith Ganepola
     * @param searchField   The field that admin wants to search through
     *                      0 - instrument number
     *                      1 - instrument make
     *                      2 - barcode
     *                      3 - instrument model
     * @param searchItem    The actual value you are searching for
     */
    public void searchInstruments(int searchField, String searchItem) {
        //create scanner object for reading the file
        Scanner keyboard = null;
        try {
            keyboard = new Scanner(new File("instrumentFile.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MusicSystemText.class.getName()).log(Level.SEVERE, null, ex);
        }
        keyboard.useDelimiter(",");
        //search through whole file
        while (keyboard.hasNextLine()) {
            String[] line = keyboard.nextLine().split(",");
            //if the search item is found, or at least part of the item
            if (line[searchField].contains(searchItem)) {
                Instrument i = new Instrument(line[0], line[1], line[2], line[3]);
                //print out that information
                System.out.println(i.printing());
            }
        }
    }

    /**
     *
     */
    public void seeSignOuts() {
        Scanner scan = null;
        String[] fileLine;
        InstrumentArchive i;
        File signOutFile = new File("signOutFile.txt"); // open file
        try {
            scan = new Scanner(signOutFile);
            while (scan.hasNext()) {
                fileLine = scan.nextLine().split(",");
                i = new InstrumentArchive(fileLine[0], fileLine[1], fileLine[2], fileLine[3], fileLine[4], fileLine[5]);
                System.out.println(i.printing());
            }
            scan.close();
        } catch (IOException e) { // if file not found, output message
            System.out.println("Can't find file.");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}

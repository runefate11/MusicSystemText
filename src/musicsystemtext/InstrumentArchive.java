package musicsystemtext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 348564469
 */
public class InstrumentArchive {

    String firstName;
    String lastName;
    String studentNum;
    String instruNum;
    String dateIn;
    String dateOut;

    public InstrumentArchive(String firstName, String lastName, String studentNum, String instruNum, String dateIn, String dateOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNum = studentNum;
        this.instruNum = instruNum;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    @Override
    public String toString() {
        return this.firstName + "," + this.lastName + "," + this.studentNum + "," + this.instruNum + "," + this.dateIn + "," + this.dateOut;
    }
}

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
public class Student {

    String firstName;
    String lastName;
    String studentNum;

    public Student(String firstName, String lastName, String studentNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNum = studentNum;
    }
    
     public String printing() {
        String item = String.format("%-15s %-15s %-10s",firstName, lastName, studentNum );
        return item;
    }

    @Override
    public String toString() {
        return this.firstName + "," + this.lastName + "," + this.studentNum;
    }
}

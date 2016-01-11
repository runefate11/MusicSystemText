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
public class Instrument {

    String instruNum;
    String make;
    int barcode;
    String model;

    public Instrument(String instruNum, String make, int barcode, String model) {
        this.instruNum = instruNum;
        this.make = make;
        this.barcode = barcode;
        this.model = model;
    }

    @Override
    public String toString() {
        return this.instruNum + "," + this.make + "," + this.barcode + "," + this.model;
    }
}

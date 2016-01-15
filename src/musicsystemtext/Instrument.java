package musicsystemtext;

/**
 * Class for instrument object (keeps the instrument name, the make, the
 * barcode, and the model
 *
 * @author Devanjith Ganepola and Gregory Wong
 */
public class Instrument {

    String instruNum;
    String make;
    String barcode;
    String model;

    /**
     * Constructor for instrument
     *
     * @param instruNum - instrument name
     * @param make - instrument make
     * @param barcode - barcode on instrument
     * @param model - instrument model number
     */
    public Instrument(String instruNum, String make, String barcode, String model) {
        this.instruNum = instruNum;
        this.make = make;
        this.barcode = barcode;
        this.model = model;
    }

    /**
     * Print the instrument with the formating desired for the GUI
     *
     * @return - the formatted string of all instrument info
     */
    public String printing() {
        String item = String.format("%-15s %-10s %-20s %-15s", instruNum, make, barcode, model);
        return item;
    }

    /**
     * Prints how the instrument should be printed to the file (uses "," as the
     * delimiter)
     *
     * @return - a single string of all instrument info separated by a delimiter
     */
    @Override
    public String toString() {
        return this.instruNum + "," + this.make + "," + this.barcode + "," + this.model;
    }
}

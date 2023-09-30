package databaseapp;

/**
 * A class to define the creation parameters for the Database App
 * @author Bajan
 */
public class FormState {
    //metadata
    private boolean inputSuccessful;
    //dynamic data
    private String descriptionString;
    private String dateString;
    private String startString;
    private String endString;

    /**
     * Called when a date, start, and end are validated and pass checks
     * @param date the validated date inputted MM-dd-yyyy
     * @param start the validated start time hh:mm a (AM/PM)
     * @param end the validated end time hh:mm a (AM/PM)
     */
    public FormState(String description, String date, String start, String end) {
        this.inputSuccessful = true;
        this.descriptionString = description;
        this.dateString = date;
        this.startString = start;
        this.endString = end;
    }

    /**
     * Create an unsuccessful Form State 
     */
    public FormState() {
        this.inputSuccessful = false;
    }

    /**
     * Called to just switch the state of the UI
     * @param newState the new state of the app
     */

    public boolean isSuccessful() {
        return this.inputSuccessful;
    }

    public String getDescription() {
        return this.descriptionString;
    }

    public String getDate() {
        return this.dateString;
    }

    public String getStart() {
        return this.startString;
    }

    public String getEnd() {
        return this.endString;
    }
}

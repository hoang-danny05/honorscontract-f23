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

    /** 
     * gets the Description string
     * @return The description string that is never above 50 chars.
    */
    public String getDescription() {
        if (this.descriptionString.length() >= 50) {
            return this.descriptionString.substring(0, 50);
        }
        return this.descriptionString;
    }

    /**
     * gets the Date string
     * @return Date string in the format of MM-dd-yyyy 
     */
    public String getDate() {
        return this.dateString;
    }

    /**
     * gets the start time string
     * @return the start time in the format hh:mm AM/PM
     */
    public String getStart() {
        return this.startString;
    }

    /**
     * gets the end time string
     * @return the end time in the format hh:mm AM/PM
     */
    public String getEnd() {
        return this.endString;
    }
    //#### SQL FRIENDLY METHODS 
    /**
     * SQL friendly version of the date
     * @return DATE FORMAT: yyyy-MM-dd
     */
    public String getSQLDate() {
        if (! this.inputSuccessful) {
            return "";
        }
        //current format: MM-dd-yyyy
        //01234567
        //mm-dd-yyyy
        return String.format(
            "%s-%s-%s", 
            this.dateString.substring(6),
            this.dateString.substring(0, 2),
            this.dateString.substring(3, 5)
        );
    }

    /**
     * SQL friendly version of start time
     * @return start time in the format HH:MM:SS (hh:MM:ss)
     */
    public String getSQLStart() {
        if (! this.inputSuccessful) {
            return "";
        }
        //hh:mm AM/PM
        //01234567
        int hour = Integer.valueOf(this.startString.substring(0, 2));
        if (this.startString.substring(6, 8).equals("PM")) {
            hour += 12;
        }
        return String.format(
            "%d:%s:00",
            hour,
            this.startString.substring(3,5)
        );
    }

    /**
     * SQL friendly version of end time
     * @return end time in the format HH:MM:SS (hh:MM:ss)
     */
    public String getSQLEnd() {
        if (! this.inputSuccessful) {
            return "";
        }
        //hh:mm AM/PM
        //01234567
        int hour = Integer.valueOf(this.endString.substring(0, 2));
        if (this.endString.substring(6, 8).equals("PM")) {
            hour += 12;
        }
        return String.format(
            "%d:%s:00",
            hour,
            this.endString.substring(3,5)
        );
    }

}


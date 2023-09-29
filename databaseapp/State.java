package databaseapp;

/**
 * A class to define the creation parameters for the Database App
 * @author Bajan
 */
public class State {
    //fixed data
    private boolean inputSuccessful;
    private UI_STATE state;
    //dynamic data
    private String dateString;
    private String startString;
    private String endString;

    /**
     * Called when a date, start, and end are validated and pass checks
     * @param newState the state of the app
     * @param date the validated date inputted
     * @param start the validated start time
     * @param end the validated end time
     */
    public State(UI_STATE newState, String date, String start, String end) {
        this.inputSuccessful = true;
        this.state = newState;
        this.dateString = date;
        this.startString = start;
        this.endString = end;
    }

    /**
     * Called to just switch the state of the UI
     * @param newState the new state of the app
     */
    public State(UI_STATE newState) {
        this.inputSuccessful = true;
        this.state = newState;
    }

    public boolean isSuccessful() {
        return this.inputSuccessful;
    }

    public UI_STATE getState() {
        return this.state;
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

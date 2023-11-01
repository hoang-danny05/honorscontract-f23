/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
// import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Properties;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Bajan
 */
public class Database {
    private FileInputStream in;
    private Properties props;
    private Connection conn;
    private Statement stat;
    private static Database instance = null;

    public Database() throws InvalidClassException {
        if (Database.instance == null) {
            Database.instance = this;
        } else {
            throw new InvalidClassException("Instance already created");
        }
        this.props = new Properties();
    }

    /**
     * Starts the Apache Derby database.
     * @return if the database successfully launched. If not, exit the app
     */
    public boolean init() {
        String databaseName;
        String connectionUrl;

        //this part is error prone, the database might not be installed or may be misconfigured...
        try {
            //Read database.conf in the src folder, get the database name
            this.in = new FileInputStream("./database.conf");
            this.props.load(this.in);
            databaseName = props.getProperty("database.name");
            //create the url that will be used to connect to the database
            connectionUrl = "jdbc:derby:" + databaseName + ";create = true";
            //Connects to the database 
            this.conn = DriverManager.getConnection(connectionUrl);
            System.out.println("Successfully connected to " + databaseName);

            //added stat to execute SQL queries later on
            this.stat = this.conn.createStatement();
            //Create the table if it does not exist
            if (! Utils.checkForTable(conn)) {
                System.out.println("Table does not exist, creating a table");
                stat.execute("""
                    CREATE TABLE Appointments (
                        description VARCHAR(50),
                        day DATE,
                        startTime TIME,
                        endTime TIME
                    )
                """);
            } else {
                System.out.println("Table check passed.");
            }
        } 
        catch (FileNotFoundException e) //no configuration file
        {
            System.out.println("No configuration file found. Please input config in \"database.conf\"");
            return false;
        }
        catch (SQLException se) { //Any SQL error
            if (se.getSQLState().equals("X0Y32")) {
                System.out.println("Table Already exists.");
                return false;
            }
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
            return false;
        }
        catch (Exception e) { //Any other mystery error
            System.out.println("An error happened when initializing the database.");
            e.printStackTrace(System.out);
            return false;
        }

        //everything worked out well, no errors happened. 
        return true;
    }

    /**
     * placeholder before I add any real SQL code execution
     */
    public void testTable() {
        System.out.println("Going into testtable");
        try{
            this.stat.execute("""
                INSERT INTO Appointments (description, day, startTime, endTime)
                VALUES ('Danny''s Birthday', '2005-03-18', '05:00:00', '18:00:00')
            """);

            ResultSet result = this.stat.executeQuery("SELECT * FROM Appointments");

            while (result.next()) { // meant to loop over the table
                System.out.println(result.getString("description"));
                System.out.println(result.getString("day"));
                System.out.println(result.getString("startTime"));
                System.out.println(result.getString("endTime"));
            }

            // this.stat.execute("DROP TABLE Appointments");
        }
        catch (SQLException se) {
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
        }
    }

    public static void displayAppointments() {
        try{
            ResultSet result = Database.instance.stat.executeQuery("SELECT * FROM Appointments");

            while (result.next()) { // meant to loop over the table
                System.out.println("~~~~~~~~~~~~~~~~~");
                System.out.println(result.getString("description"));
                System.out.println(result.getString("day"));
                System.out.println(result.getString("startTime"));
                System.out.println(result.getString("endTime"));
            }
        }
        catch (SQLException se) {
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
        }
    }

    public static void addAppointment(FormState newAppointment) {
        System.out.printf("Inserting Appointment: ['Description': %s, 'Date': %s, 'Start': %s, 'End': %s]\n",
            newAppointment.getDescription(),
            newAppointment.getSQLDate(),
            newAppointment.getSQLStart(),
            newAppointment.getSQLEnd()
        );

        String query = """
                INSERT INTO Appointments (description, day, startTime, endTime)
                VALUES (?, ?, ?, ?)
            """;

        try {
            PreparedStatement prepStat = Database.instance.conn.prepareStatement(query);
            prepStat.setString(1, newAppointment.getDescription());
            prepStat.setString(2, newAppointment.getSQLDate());
            prepStat.setString(3, newAppointment.getSQLStart());
            prepStat.setString(4, newAppointment.getSQLEnd());
            prepStat.execute();
        }
        catch (SQLException se) {
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
        }
    }

    public static void removeAppointment(FormState cancelledAppointment) {
        System.out.printf("Removing Appointment: ['Description': %s, 'Date': %s, 'Start': %s, 'End': %s]\n",
            cancelledAppointment.getDescription(),
            cancelledAppointment.getSQLDate(),
            cancelledAppointment.getSQLStart(),
            cancelledAppointment.getSQLEnd()
        );

        String query = """
                DELETE FROM Appointments
                WHERE (
                    description = ? AND
                    day = ? AND
                    startTime = ? AND
                    endTime = ?
                )
            """;

        try {
            PreparedStatement prepStat = Database.instance.conn.prepareStatement(query);
            prepStat.setString(1, cancelledAppointment.getDescription());
            prepStat.setString(2, cancelledAppointment.getSQLDate());
            prepStat.setString(3, cancelledAppointment.getSQLStart());
            prepStat.setString(4, cancelledAppointment.getSQLEnd());
            prepStat.execute();
        }
        catch (SQLException se) {
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
        }
    }

    public static void searchAppointment(FormState newAppointment) {
        System.out.printf("Searching for Appointment: ['Date': %s]\n",
            newAppointment.getSQLDate()
        );

        String query = """
                SELECT * FROM Appointments WHERE day = ?
            """;

        try {
            PreparedStatement prepStat = Database.instance.conn.prepareStatement(query);
            prepStat.setString(1, newAppointment.getSQLDate());
            ResultSet result = prepStat.executeQuery();
            String output = "";

            while (result.next()) { // meant to loop over the table
                System.out.println("~~~~~~~~~~~~~~~~~");
                String description =    result.getString("description");
                String day =            result.getString("day");
                String startTime =      result.getString("startTime");
                String endTime =        result.getString("endTime");

                System.out.println(description);
                System.out.println(day);
                System.out.println(startTime);
                System.out.println(endTime);

                output += String.format("Appointment: \"%s\" on %s from %s to %s\n", description, FormState.readableDate(day), FormState.readableTime(startTime), FormState.readableTime(endTime));
            }
            //use UserInterface.panelSearch.setText()
            UserInterface.panelSearch.outputTextArea.setText(output);
        }
        catch (SQLException se) {
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
        }
    }

    /** 
     * STATIC VERSION: Method that should be run to close down the database connection
     */
    public static void exitInstance() {
        if(Database.instance == null) {
            System.out.println("No instance created yet, cannot exit");
        } else {
            Database.instance.exit();
        }
    }

    /** 
     * Method that should be run to close down the database connection
     */
    public void exit() {
        try {
            // this.stat.execute("DROP TABLE Appointments");
            // ^ TODO: COMMENT ABOVE OUT TO PRESERVE DATA   
            this.stat.close();
            this.conn.close();
            System.out.println("Closed statement and connection");

            //#### DATABASE SHUTDOWN ####
            boolean gotSQLExec = false;
            try {
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
            } catch (SQLException se) {
                if (se.getSQLState().equals("XJ015")) {
                    gotSQLExec = true;
                }
            }

            //output if the database shut down correctly
            if (gotSQLExec) {
                System.out.println("Database shut down normally.");
            } else {
                System.out.println("Database did NOT shut down correctly");
            }
            //#### DATABASE SHUTDOWN END ####
        } catch (SQLException se) { 
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
        }
    }
}

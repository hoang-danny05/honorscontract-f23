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
                VALUES ('Some nerd being born', '2005-03-18', '05:00:00', '18:00:00')
            """);

            ResultSet result = this.stat.executeQuery("SELECT * FROM Appointments");

            while (result.next()) { // meant to loop over the table
                System.out.println(result.getString("description"));
                System.out.println(result.getString("day"));
                System.out.println(result.getString("startTime"));
                System.out.println(result.getString("endTime"));
            }

            this.stat.execute("DROP TABLE Appointments");
        }
        catch (SQLException se) {
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
        }
    }

    public static void addAppointment(FormState newAppointment) {
        System.out.printf("Inserting Appointment: ['Description': %s, 'Date': %s, 'Start': %s, 'End': %s]",
            newAppointment.getDescription(),
            newAppointment.getSQLDate(),
            newAppointment.getSQLStart(),
            newAppointment.getSQLEnd()
        );

        String query = String.format("""
                INSERT INTO Appointments (description, day, startTime, endTime)
                VALUES ('%s', '%s', '%s', '%s')
            """,
            newAppointment.getDescription(),
            newAppointment.getSQLDate(),
            newAppointment.getSQLStart(),
            newAppointment.getSQLEnd()
        );

        try {
            instance.stat.execute(query);
        }
        catch (SQLException se) {
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
        }
    }


    /** 
     * Method that should be run to close down the database connection
     */
    public void exit() {
        try {
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

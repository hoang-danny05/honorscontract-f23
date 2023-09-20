/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
// import java.io.IOException;

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

    public Database() {
        this.props = new Properties();
    }

    /**
     * Starts the Apache Derby database.
     * @return if the database successfully launched. If not, exit the app
     */
    public boolean init() {
        String databaseName;
        String connectionUrl;
        try {
            this.in = new FileInputStream("./database.conf");
            this.props.load(this.in);
            databaseName = props.getProperty("database.name");
            connectionUrl = "jdbc:derby:" + databaseName + ";create = true";
            //create == true may not be needed, might have to be removed
            this.conn = DriverManager.getConnection(connectionUrl);
            System.out.println("Successfully connected to " + databaseName);

            this.stat = this.conn.createStatement();
            if (! Utils.checkForTable(conn)) {
                System.out.println("Table does not exist, creating a table");
                stat.execute("CREATE TABLE Test (Name VARCHAR(20))");
            } else {
                System.out.println("Table check passed.");
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("No configuration file found. Please input config in \"database.conf\"");
            return false;
        }
        catch (SQLException se) {
            System.out.println("SQL Error: " + se.getSQLState());
            se.printStackTrace(System.out);
            return false;
        }
        catch (Exception e) {
            System.out.println("An error happened when initializing the database.");
            e.printStackTrace(System.out);
            return false;
        }


        return true;
    }

    public void testTable() {
        try{
            this.stat.execute("INSERT INTO Test VALUES ('UrMom')");

            ResultSet result = this.stat.executeQuery("SELECT * FROM Test");

            while (result.next()) { // meant to loop over the table
                System.out.println(result.getString("Name"));
            }

            this.stat.execute("DROP TABLE Test");
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

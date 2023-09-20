/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package databaseapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
/**
 *
 * @author Bajan
 */
public class DatabaseApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //INITIAL VALUES
        String dbName = "testdb";
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";
        Connection conn = null;
        // Statement s;
        // PreparedStatement psInsert;
        String createTable = "CREATE TABLE Test" 
        +   "(Name VARCHAR(20))";
        // +   "Hours INT)";
        //TODO: Create more complex table. Hours, Major, and even a Birthday/Graduation date (work with different types)

        //JDBC Code Sections
        System.out.println("Entering Try Loop");
        try {
          conn = DriverManager.getConnection(connectionURL);
          System.out.println("Connected to database " + dbName);

          Statement s = conn.createStatement();

          //check if table exists
          if (! Utils.checkForTable(conn)) {
            System.out.println("Creating table Test");
            s.execute("CREATE TABLE Test (Name VARCHAR(20))");
          }
          //make statements now
          //execute statements
          // s.execute("CREATE TABLE Test (Name VARCHAR(20))");
          s.execute("INSERT INTO Test VALUES ('Romeo')");

          ResultSet result = s.executeQuery("SELECT * FROM Test");
          result.next();

          System.out.println(result.getString("Name"));

          s.execute("DROP TABLE Test");

          s.close();
          conn.close();
          System.out.println("Closed the connection");
          
          // ##### DATABASE SHUTDOWN #####
          boolean gotSQLExec = false;
          try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
          } catch (SQLException se) {
            if (se.getSQLState().equals("XJ015")) {
              gotSQLExec = true;
            }
          }

          if (!gotSQLExec) {
            System.out.println("Database did not shut down normally");
          } else {
            System.out.println("Database shut donw normally");
          }
          // end database portion
        } 
        catch (Throwable e) {
          System.out.println("An exception has been thrown");
          e.printStackTrace(System.out);
        }
        // catch (Exception e) {
        //   System.out.println("An error occured");
        //   e.printStackTrace(System.out);
        // } 
    }
    
}

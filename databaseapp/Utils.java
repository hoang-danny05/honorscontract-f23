/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bajan
 */
public class Utils {

  /**
   * Checks if the Test table already exists
   * @param conn
   * @return boolean if the table exists 
   * @throws SQLException
   */
  public static boolean checkForTable(Connection conn) throws SQLException{
    boolean check = true;
    boolean isExisting = false;
    try {
      Statement s = conn.createStatement();
      // s.execute("update Test set Name = 'Danny', Hours = 18 where 1=3");
      s.execute("update Test set Name = 'Danny' where 1=3");
      //never actually runs, just checks for the table's existence
    } catch (SQLException sqle) {
      String error = sqle.getSQLState();
      //If the table exists we get Warning 02000: no row was found
      if (error.equals("42X05")) {
        return false;
      } else if (error.equals("42X14")) {
        System.out.println("checkForTable: Incorrect table definition");
        throw sqle;
      } else {
        System.out.println("checkForTable: Unhandled SQLException");
        throw sqle;
      }
    }
    return true;
  }
}
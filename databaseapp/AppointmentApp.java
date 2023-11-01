/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package databaseapp;

// import java.sql.Connection;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.sql.ResultSet;
// import java.sql.DriverManager;
/**
 *
 * @author Bajan
 */
public class AppointmentApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
      Database db = new Database();
      UserInterface.init();
      UserInterface.show();
      db.init();
      // db.testTable(); // TODO: comment out to remove entry 1
      // db.exit();
    }
    
}

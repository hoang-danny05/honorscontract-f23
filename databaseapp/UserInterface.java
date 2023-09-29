/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package databaseapp;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 * User Interface class for the database app
 * @author Bajan 
 */
public class UserInterface {
    private static JFrame frame;
    private static JPanel panel;
    final static int FRAME_WIDTH = 700;
    final static int FRAME_HEIGHT = 250;
    private static UI_STATE state;

    /**
     * initialize an instance of the UI
     */
    public static void init() {
        changeState(UI_STATE.ADD_APPOINTMENT);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create abstract class for the exit method (runs when we exit)
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                System.out.println("Closing the program!!!!");
            }
        });
        //other stuff
        // ActionListener listener = new ClickListener();
        // button.addActionListener(listener);   
    }

    /**
     * shows the UI
     */
    public static void show() {
        frame.setVisible(true);
    }

    /**
     * reloads/loads the User Interface based on how the state has changed
     * @param newState
     */
    public static void changeState(UI_STATE newState) {
        //reload components based on the state
        UI_STATE oldState = UserInterface.state;
        UserInterface.state = newState;
        if (oldState == newState) {
            return;
        }
        // System.out.println("Oh crap its different");
        //#####CONSTRUCT
        //initialize containers
        frame = new JFrame("Test UI");
        JTabbedPane tabbedPane = new JTabbedPane();

        //############ Container Panels
        panel = new JPanel();
        panel.setLayout(new CardLayout(10,10));
        CentralPanel add = new CentralPanel(UI_STATE.ADD_APPOINTMENT); 
        tabbedPane.add("Add Appointment", add);

        CentralPanel remove = new CentralPanel(UI_STATE.REMOVE_APPOINTMENT); 
        tabbedPane.add("Remove Appointment", remove);

        CentralPanel search = new CentralPanel(UI_STATE.SEARCH_APPOINTMENT); 
        tabbedPane.add("Search Appointments", search);
        //########## OTHER
        // this.label = new JLabel("Hello!");
        // panel.add(this.label);
        // frame.removeAll();
        frame.add(tabbedPane);
        frame.pack();
        // frame.validate();
        frame.repaint();
    }

    /**
     * For the Add button
     * @return The method that will run when the "Add Appointment" button is clicked
     */
    public static ActionListener getAddListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("The Add button was pressed");
            }
        };
    }

    /**
     * For the Remove button
     * @return The method that will run when the "Remove Appointment" button is clicked
     */
    public static ActionListener getRemoveListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("The Remove button was pressed");
            }
        };
    }

    /**
     * For the View button
     * @return The method that will run when the "View Appointment" button is clicked
     */
    public static ActionListener getViewListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("The View button was pressed");
            }
        };
    }

    // private class ClickListener implements ActionListener {
    //     public void actionPerformed(ActionEvent event) {
    //         System.out.println("Bum");
    //     }
    // }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package databaseapp;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Bajan
 */
public class UserInterface {
    private JFrame frame;
    private JPanel panel;
    final int FRAME_WIDTH = 600;
    final int FRAME_HEIGHT = 500;

    public UserInterface() {
        //initialize containers
        this.frame = new JFrame("Test UI");
        this.panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //PRESET Panels
        MenuPanel menu = new MenuPanel();
        this.panel.add(menu, BorderLayout.NORTH);

        CentralPanel central = new CentralPanel();
        this.panel.add(central, BorderLayout.CENTER);
        //##########OTHER
        // this.label = new JLabel("Hello!");
        // panel.add(this.label);
        frame.add(this.panel);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                System.out.println("Closing the program!!!!");
            }
        });
        //other stuff
        // ActionListener listener = new ClickListener();
        // button.addActionListener(listener);
    }

    public void show() {
        frame.setVisible(true);
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

    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("Bum");
        }
    }
}

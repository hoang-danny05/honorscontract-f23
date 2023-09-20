/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package databaseapp;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Bajan
 */
public class UserInterface {
    private JFrame frame;
    private JButton button;
    private JPanel panel;
    private JLabel label;
    final int FRAME_WIDTH = 300;
    final int FRAME_HEIGHT = 200;

    public UserInterface() {
        //initialize components
        this.frame = new JFrame("Test UI");
        this.button = new JButton("Press Me!");
        this.label = new JLabel("Hello!");

        this.panel = new JPanel();
        panel.add(this.button);
        panel.add(this.label);
        frame.add(this.panel);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                //place for exiting code to run
                System.out.println("Closing the program!!!!");
            }
        });
        //other stuff
        ActionListener listener = new ClickListener();
        button.addActionListener(listener);
    }

    public void show() {
        frame.setVisible(true);
    }

    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("Bum");
        }
    }
}

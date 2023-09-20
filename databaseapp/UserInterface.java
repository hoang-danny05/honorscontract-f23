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
    private JPanel panel;
    private JLabel label;
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

    private class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("Bum");
        }
    }
}

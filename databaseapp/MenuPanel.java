package databaseapp;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;

class MenuPanel extends JPanel{
    private JComboBox menuItems;
    private String[] menuCombo = {"Add Appointments", "Remove Appointments", "See Appointments"};
    
    public MenuPanel() {
        setLayout(new FlowLayout());
        menuItems = new JComboBox<String>(menuCombo);

        // JTabbedPane tabs = new JTabbedPane()
    }


}

package databaseapp;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CentralPanel extends JPanel{
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    private JLabel dateLabel;
    private JTextField dateField;
    private JLabel startLabel;
    private JTextField startField;
    private JLabel endLabel;
    private JTextField endField;

    public CentralPanel() {
        setLayout(new GridLayout(4,2, 10, 10));
        
        this.descriptionLabel = new JLabel("Apt. Description: ");
        this.dateLabel = new JLabel("Apt. Date: ");
        this.startLabel = new JLabel("Starting Time: ");
        this.endLabel = new JLabel("Ending Time: ");
        this.descriptionField = new JTextField();
        this.dateField = new JTextField();
        this.startField = new JTextField();
        this.endField = new JTextField();

        add(this.descriptionLabel);
        add(this.descriptionField);
        add(this.dateLabel);
        add(this.dateField);
        add(this.startLabel);
        add(this.startField);
        add(this.endLabel);
        add(this.endField);
    }
}

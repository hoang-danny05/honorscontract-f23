package databaseapp;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

enum PANEL_STATES {
    FIELD_ENTER,
    DATE_SEARCH
}

public class CentralPanel extends JPanel{
    private static final int FIELD_ENTER = 1;
    private static final int DATE_SEARCH = 2;
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    private JLabel dateLabel;
    private JTextField dateField;
    private JLabel startLabel;
    private JTextField startField;
    private JLabel endLabel;
    private JTextField endField;

    /**
     * Default Central Panel Constructor -> Always the fields
     */
    public CentralPanel() {
        setFieldEnter();
    }

    /** 
     * Changes to certain states based on the input
     * @param state The input state from CENTRALPANEL.<constant>
    */
    public CentralPanel(int state) {
        switch (state) {
            case 1: 
                setFieldEnter();
                break;
            case 2: 
                setDateSearch();
                break;
            default:
                throw new IllegalArgumentException("Invalid state input");
        }
    }

    private void setFieldEnter() {
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

    private void setDateSearch() {
        setLayout(new GridLayout(1,2, 10, 10));

        this.dateLabel = new JLabel("Apt. Date: ");
        this.dateField = new JTextField();

        add(this.dateLabel);
        add(this.dateField);
    }
}

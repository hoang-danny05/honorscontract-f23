package databaseapp;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * enumeration of all the panel states
 * @author Bajan
 */
enum UI_STATE {
    ADD_APPOINTMENT,
    REMOVE_APPOINTMENT,
    SEARCH_APPOINTMENT
}

public class CentralPanel extends JPanel{
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
     * @param state The input state from PANEL_STATES.<constant>
    */
    public CentralPanel(UI_STATE state) {
        switch (state) {
            case ADD_APPOINTMENT: 
                setFieldEnter();
                break;
            case REMOVE_APPOINTMENT:
                setFieldEnter();
                break;
            case SEARCH_APPOINTMENT: 
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

        setBorder(
            new TitledBorder(
                new EtchedBorder(ABORT, getForeground(), getBackground()), 
                "Enter Appointment Details"
            )
        );
    }

    private void setDateSearch() {
        setLayout(new GridLayout(2,1, 10, 10));

        //SUB PANEL (Search Box)
        JPanel searchBoxes = new JPanel();
        searchBoxes.setLayout(new GridLayout(1,2, 10, 10));
        this.dateLabel = new JLabel("Apt. Date: ");
        this.dateField = new JTextField();
        searchBoxes.add(this.dateLabel);
        searchBoxes.add(this.dateField);

        //SUB PANEL 2 (Output Box)
        JPanel outputBox = new JPanel();
        JLabel outputLabel = new JLabel("Output");
        JScrollPane outputScroll = new JScrollPane(new JTextArea(5, 40));
        outputBox.add(outputLabel);
        outputBox.add(outputScroll);

        add(searchBoxes);
        add(outputBox);

        setBorder(            
            new TitledBorder(
                new EtchedBorder(ABORT, getForeground(), getBackground()), 
                "Enter date to search for:"
            )
        );
    }
}

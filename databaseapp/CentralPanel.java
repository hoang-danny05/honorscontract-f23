package databaseapp;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
// import javax.swing.JDatePicker;
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
    private JButton actionButton;

    /**
     * Default Central Panel Constructor -> Always the fields
     */
    public CentralPanel() {
        setAddForm();
    }

    /** 
     * Changes to certain states based on the input
     * @param state The input state from PANEL_STATES.<constant>
    */
    public CentralPanel(UI_STATE state) {
        switch (state) {
            case ADD_APPOINTMENT: 
                setAddForm();
                break;
            case REMOVE_APPOINTMENT:
                setRemoveForm();
                break;
            case SEARCH_APPOINTMENT: 
                setDateSearch();
                break;
            default:
                throw new IllegalArgumentException("Invalid state input");
        }
    }

    private void setAddForm() {
        setLayout(new GridLayout(5,2, 10, 10));
        
        this.descriptionLabel = new JLabel("Apt. Description: ");
        this.dateLabel = new JLabel("Apt. Date: ");
        this.startLabel = new JLabel("Starting Time: ");
        this.endLabel = new JLabel("Ending Time: ");
        this.descriptionField = new JTextField();
        this.dateField = new JTextField();
        this.startField = new JTextField();
        this.endField = new JTextField();
        //button to submit form
        this.actionButton = new JButton("Add Appointment", null);
        this.actionButton.addActionListener(UserInterface.getAddListener());
        

        add(this.descriptionLabel);
        add(this.descriptionField);
        add(this.dateLabel);
        add(this.dateField);
        add(this.startLabel);
        add(this.startField);
        add(this.endLabel);
        add(this.endField);
        add(this.actionButton);

        setBorder(
            new TitledBorder(
                new EtchedBorder(ABORT, getForeground(), getBackground()), 
                "Enter Details of Appointment to add"
            )
        );
    }

    private void setRemoveForm() {
        setLayout(new GridLayout(5,2, 10, 10));
        
        this.descriptionLabel = new JLabel("Apt. Description: ");
        this.dateLabel = new JLabel("Apt. Date: ");
        this.startLabel = new JLabel("Starting Time: ");
        this.endLabel = new JLabel("Ending Time: ");
        this.descriptionField = new JTextField();
        this.dateField = new JTextField();
        this.startField = new JTextField();
        this.endField = new JTextField();
        //button to submit form
        this.actionButton = new JButton("Remove Appointment", null);
        this.actionButton.addActionListener(UserInterface.getRemoveListener());

        add(this.descriptionLabel);
        add(this.descriptionField);
        add(this.dateLabel);
        add(this.dateField);
        add(this.startLabel);
        add(this.startField);
        add(this.endLabel);
        add(this.endField);
        add(this.actionButton);

        setBorder(
            new TitledBorder(
                new EtchedBorder(ABORT, getForeground(), getBackground()), 
                "Enter details of appointment to remove"
            )
        );
    }

    private void setDateSearch() {
        setLayout(new GridLayout(2,1, 10, 10));
        this.actionButton = new JButton("Search for Appointment", null);
        this.actionButton.addActionListener(UserInterface.getViewListener());
        // this.actionButton.setPreferredSize(new Dimension(50,50));

        //SUB PANEL (Search Box)
        JPanel searchBoxes = new JPanel();
        // searchBoxes.setLayout(new GridLayout(1,3, 10, 10));
        // searchBoxes.setPreferredSize(new Dimension(200, 10));
        this.dateLabel = new JLabel("Apt. Date: ");
        this.dateField = new JTextField();
        this.dateField.setPreferredSize(new Dimension(150, 30));
        searchBoxes.add(this.dateLabel);
        searchBoxes.add(this.dateField);
        searchBoxes.add(this.actionButton);

        //SUB PANEL 2 (Output Box)
        JPanel outputBox = new JPanel();
        JLabel outputLabel = new JLabel("Output");
        JScrollPane outputScroll = new JScrollPane(new JTextArea(5, 40));
        outputBox.add(outputLabel);
        outputBox.add(outputScroll);


        // add(Box.createVerticalStrut(50));
        add(searchBoxes);
        // add(this.actionButton);
        // add(Box.createVerticalStrut(50));
        add(outputBox);

        setBorder(            
            new TitledBorder(
                new EtchedBorder(ABORT, getForeground(), getBackground()), 
                "Enter date to search for:"
            )
        );
    }

    public FormState getFormState() {
        //initially true
        boolean validInput = true;
        String descriptionString = "";
        String dateString = this.dateField.getText();
        String startTime = "";
        String endTime = "";
        //set values here
        if(this.descriptionField != null) {
            descriptionString = this.descriptionField.getText();
        }
        if(this.startField != null) {
            startTime = this.startField.getText();
        }
        if(this.endField != null) {
            endTime = this.endField.getText();
        }
        if (!validInput) {
            return new FormState();
        }
        //validate here
        //return here
        return new FormState(
            descriptionString,
            dateString,
            startTime,
            endTime
        );
    }
}

package databaseapp;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

class MenuPanel extends JPanel{
    private JButton menuAdd;
    private JButton menuRemove;
    private JButton menuView;
    
    public MenuPanel() {
        this.menuAdd = new JButton("Add Appointments");
        this.menuRemove = new JButton("Remove Appointments");
        this.menuView = new JButton("See Appointments");
        
        this.menuAdd.addActionListener(UserInterface.getAddListener());
        this.menuRemove.addActionListener(UserInterface.getRemoveListener());
        this.menuView.addActionListener(UserInterface.getViewListener());
        // this.menuAdd.setBounds(0,0,100,50);
        // this.menuRemove.setBounds(0,0,100,50);
        // this.menuView.setBounds(0,0,100,50);
        setLayout(new GridLayout(1,3));

        add(this.menuAdd);
        add(this.menuRemove);
        add(this.menuView);
    }
}

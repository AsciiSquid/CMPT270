//Dawson Wiebe drw529 11226441
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu Panel that directs to all other system functions in the GUI
 */
public class MenuPanel extends JPanel {

    public MenuPanel() {
        setLayout(new BorderLayout());
        //Opens the Patient Operations Window
        JButton patientButton = new JButton("Patients");
        add(patientButton, BorderLayout.WEST);

        patientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                PatientOpsFrame frame = new PatientOpsFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        //Opens the Doctor Operations Window
        JButton doctorButton = new JButton("Doctors");
        add(doctorButton, BorderLayout.EAST);

        doctorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                DoctorOpsFrame frame = new DoctorOpsFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        //Opens Ward Operations Window
        JButton wardButton = new JButton("Ward");
        add(wardButton, BorderLayout.CENTER);

        wardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WardFrame frame = new WardFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        //Exits the Program
        JButton exitButton = new JButton("Exit");
        add(exitButton, BorderLayout.SOUTH);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                exitButton.getTopLevelAncestor().setVisible(false);
                System.exit(0);
            }
        });
    }
}

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        setLayout(new BorderLayout());

        JButton patientButton = new JButton("Patients");
        add(patientButton, BorderLayout.WEST);

        patientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                PatientOpsFrame frame = new PatientOpsFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        JButton doctorButton = new JButton("Doctors");
        add(doctorButton, BorderLayout.EAST);

        doctorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                DoctorOpsFrame frame = new DoctorOpsFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        JButton wardButton = new JButton("Ward");
        add(wardButton, BorderLayout.CENTER);

        wardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WardFrame frame = new WardFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

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

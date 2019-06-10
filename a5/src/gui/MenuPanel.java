package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        setLayout(new BorderLayout());

        JButton patientButton = new JButton("Patients");
        add(patientButton, BorderLayout.CENTER);

        patientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                PatientOpsFrame frame = new PatientOpsFrame();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}

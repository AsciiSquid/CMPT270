package gui;

import javax.swing.*;

import commands.*;
import entities.Doctor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorPanel extends JPanel {
    /**
     * Label to hold the results of the last performed action
     */
    private JLabel statusLabel;

    public DoctorPanel(Doctor doctor) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel("Name: " + doctor.getName()));

        statusLabel = new JLabel(" ");
        add(statusLabel);

        JPanel addPatientPanel = addPatientPanel(doctor);
        add(addPatientPanel);
        addPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addPatientPanel.setMaximumSize(addPatientPanel.getPreferredSize());

        JPanel searchPatientPanel = searchPatientPanel(doctor);
        add(searchPatientPanel);
        searchPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPatientPanel.setMaximumSize(searchPatientPanel.getPreferredSize());

        JPanel removePatientPanel = removePatientPanel(doctor);
        add(removePatientPanel);
        removePatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        removePatientPanel.setMaximumSize(removePatientPanel.getPreferredSize());

        add(new JLabel("  ")); // blank line in the panel for spacing

        final JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
    }

    private static int parseHealthNum(JTextField inputField) {
        String valueAsString = inputField.getText();
        int healthNum = -1;
        if (valueAsString != null && valueAsString.length() > 0) {
            try {
                healthNum = Integer.parseInt(valueAsString);
            } catch (NumberFormatException e) {
                inputField.setText("Not int: " + inputField.getText());
                inputField.revalidate();
                return healthNum;
            }
        }
        return healthNum;
    }

    private JPanel addPatientPanel(final Doctor doctor) {
        JPanel addPatientPanel = new JPanel();
        addPatientPanel.add(new JLabel("Add patient"));
        final JTextField textField = new JTextField(10);
        addPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int healthNum = parseHealthNum(textField);
                if (healthNum == -1) { return; }
                AssignDoctor addAssoc = new AssignDoctor();
                addAssoc.assignDoctor(doctor.getName(), healthNum);
                if (addAssoc.wasSuccessful()) {
                    // Update status label
                    statusLabel.setText(healthNum + " has been assigned");
                    revalidate();
                } else {
                    statusLabel.setText(addAssoc.getErrorMessage());
                    JOptionPane.showMessageDialog(DoctorPanel.this, addAssoc.getErrorMessage());
                    revalidate();
                }
            }
        });
        return addPatientPanel;
    }

    private JPanel searchPatientPanel(final Doctor doctor) {
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search Patient"));
        final JTextField searchField = new JTextField(10);
        searchPanel.add(searchField);
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int healthNum = parseHealthNum(searchField);
                if (healthNum == -1) { return; }
                if (doctor.hasPatient(healthNum)) {
                    PatientFrame pFrame = new PatientFrame(healthNum);
                    pFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    pFrame.setVisible(true);
                } else {
                    statusLabel.setText("Patient " + healthNum + " not found");
                    revalidate();
                }
            }
        });
        return searchPanel;
    }

    private JPanel removePatientPanel(final Doctor doctor) {
        JPanel removePatientPanel = new JPanel();
        removePatientPanel.add(new JLabel("Remove patient"));
        final JTextField delField = new JTextField(10);
        removePatientPanel.add(delField);
        delField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int healthNum = parseHealthNum(delField);
                if (healthNum == -1) { return; }
                DropDoctor rmvAssoc = new DropDoctor();
                rmvAssoc.dropAssociation(doctor.getName(), healthNum);
                if (rmvAssoc.wasSuccessful()) {
                    // Update status label
                    statusLabel.setText(healthNum + " has been removed");
                    revalidate();
                } else {
                    statusLabel.setText(rmvAssoc.getErrorMessage());
                    JOptionPane.showMessageDialog(DoctorPanel.this, rmvAssoc.getErrorMessage());
                    revalidate();
                }
            }
        });
        return removePatientPanel;
    }
}

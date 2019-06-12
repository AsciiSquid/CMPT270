package gui;

import javax.swing.*;

import commands.*;
import entities.Doctor;
import entities.Patient;
import entities.Person;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorPanel extends JPanel {
    public DoctorPanel(Doctor doctor) {
        build(doctor);
    }

    private void build(Doctor doctor) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel("Name: " + doctor.getName()));

        add(new JLabel("  ")); // blank line in the panel for spacing

        // add an empty panel to force the add doctor and exit components to the bottom
        JPanel emptyPanel = new JPanel();
        add(emptyPanel);
        emptyPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel addPatientPanel = addPatientPanel(doctor);
        add(addPatientPanel);
        addPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addPatientPanel.setMaximumSize(addPatientPanel.getPreferredSize());

        add(new JLabel("  ")); // blank line in the panel for spacing
        final JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
    }

    private JPanel listDoctorPanel(final Person patient, final Doctor doctor) {
        JPanel patientPanel = new JPanel();
        patientPanel.add(new JLabel("  "));
        patientPanel.add(new JLabel(patient.getName()));
        JButton removeButton = new JButton("remove");
        patientPanel.add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                DropDoctor dropAssoc = new DropDoctor();
                dropAssoc.dropAssociation(doctor.getName(), patient.getHealthNumber());
                if (dropAssoc.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(doctor);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(DoctorPanel.this, dropAssoc.getErrorMessage());
                }
            }
        });
        return patientPanel;
    }

    private JPanel addPatientPanel(final Doctor doctor) {
        JPanel addPatientPanel = new JPanel();
        addPatientPanel.add(new JLabel("Add patient"));
        final JTextField textField = new JTextField(10);
        addPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String valueAsString = textField.getText();
                int healthNum = -1;
                if (valueAsString != null && valueAsString.length() > 0) {
                    try {
                        healthNum = Integer.parseInt(valueAsString);
                    } catch (NumberFormatException e) {
                        textField.setText("Not int: " + textField.getText());
                        textField.revalidate();
                        return;
                    }
                }
                AssignDoctor addAssoc = new AssignDoctor();
                addAssoc.assignDoctor(doctor.getName(), healthNum);
                if (addAssoc.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(doctor);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(DoctorPanel.this, addAssoc.getErrorMessage());
                }
            }
        });
        return addPatientPanel;
    }
}

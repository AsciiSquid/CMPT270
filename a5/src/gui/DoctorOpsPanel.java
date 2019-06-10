package gui;

import containers.DoctorMapAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorOpsPanel extends JPanel {

    public DoctorOpsPanel() {
        setLayout(new BorderLayout());

        JButton addDoctor = new JButton("Add Doctor");
        addDoctor.setMaximumSize(addDoctor.getPreferredSize());
        addDoctor.setText("Sample Text");
        add(addDoctor);
        addDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);

        addDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO: Create Add Doctor Window
            }
        });

        JButton exitDoctor = new JButton("Exit");
        addDoctor.setMaximumSize(exitDoctor.getPreferredSize());
        exitDoctor.setText("Example Text");
        add(exitDoctor, BorderLayout.CENTER);

        exitDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                exitDoctor.getTopLevelAncestor().setVisible(false);
            }
        });

        JTextField searchDoctor = new JTextField("");
        searchDoctor.setSize(90, 18);
        add(searchDoctor, BorderLayout.WEST);

        searchDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO: Create Doctor Info Window
            }
        });
//
//        JOptionPane listDoctor = new JOptionPane(DoctorMapAccess.dictionary());
//        add(listDoctor, BorderLayout.CENTER);
    }
}

//Dawson Wiebe drw529 11226441
package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import containers.DoctorMapAccess;

/**
 * Doctor Operations Panel, used by the DoctorOpsFrame
 */
public class DoctorOpsPanel extends JPanel {
    public DoctorOpsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        //button to add a new doctor
        JButton addButton = new JButton("Add Doctor");
        addButton.setMaximumSize(addButton.getPreferredSize());
        add(addButton);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                DoctorAddFrame frame = new DoctorAddFrame();
                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());

        // add a panel with a field to access a specific patient
        DoctorAccessPanel accessPanel = new DoctorAccessPanel();
        add(accessPanel);
        add(Box.createVerticalGlue());

        // add a button to display all the patients
        JButton listAllButton = new JButton("List all");
        listAllButton.setMaximumSize(listAllButton.getPreferredSize());
        add(listAllButton);
        listAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        listAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(null, DoctorMapAccess.dictionary().values());
            }
        });
        add(Box.createVerticalGlue());

        // add a button to exit from the window containing this panel
        final JButton exitButton = new JButton("Exit");
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        add(exitButton);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
        add(Box.createVerticalGlue());
    }
}

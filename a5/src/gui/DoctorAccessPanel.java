//Dawson Wiebe drw529 11226441
package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorAccessPanel extends JPanel {
    JTextField textField;

    public DoctorAccessPanel() {
        JLabel promptLabel = new JLabel("Access Doctor");
        add(promptLabel);
        textField = new JTextField(10);
        add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String name = textField.getText();
                if (name != null && name.length() > 0) {
                    DoctorFrame frame;
                    try {
                        frame = new DoctorFrame(name);
                    } catch (RuntimeException e) {
                        textField.setText("Invalid name: " + textField.getText());
                        textField.revalidate();
                        return;
                    }
                    frame.setLocation(300, 300);
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setVisible(true);
                    textField.setText("");
                    textField.revalidate();
                } else {
                    textField.setText("Empty field: " + textField.getText());
                    textField.revalidate();
                }
            }
        });
    }
}

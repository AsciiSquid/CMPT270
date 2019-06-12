package gui;

import javax.swing.JFrame;

public class DoctorOpsFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    public DoctorOpsFrame(){
        setTitle("Patient Operations");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        PatientOpsPanel panel = new PatientOpsPanel();
        add(panel);
    }
}
